import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Teamwork5Simulation {

    enum DistType { UNIFORM, EXPONENTIAL, PARETO_3, PARETO_1_5, CAUCHY }

    public static void main(String[] args) {
        System.out.println("--- Teamwork 5 Simulation Started ---");
        
        runAnalysis(DistType.UNIFORM, "Uniform (0,1)", 0.5, Math.sqrt(1.0/12.0));
        runAnalysis(DistType.EXPONENTIAL, "Exponential (lambda=1)", 1.0, 1.0);
        runAnalysis(DistType.PARETO_3, "Pareto (alpha=3)", 1.5, 0.866); 
        runAnalysis(DistType.PARETO_1_5, "Pareto (alpha=1.5)", 3.0, -1); 
        runAnalysis(DistType.CAUCHY, "Cauchy", 0, -1); 

        System.out.println("--- ALL SIMULATIONS COMPLETED ---");
        System.out.println("Check the folder: C:\\Users\\nejla\\OneDrive\\Masaustu\\TW5_Results");
    }

    private static void runAnalysis(DistType type, String name, double theoMean, double theoStd) {
        System.out.println("Running analysis for: " + name);
        runSLLN(type, name, theoMean);
        
        int[] nValues = {5, 30, 100}; 
        for (int n : nValues) {
            runCLT(type, name, n, 1000, theoMean, theoStd);
        }
    }

    private static void runSLLN(DistType type, String name, double theoMean) {
        int N = 10000; 
        double[] means = new double[N];
        double sum = 0;
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            sum += generateValue(type, rand);
            means[i] = sum / (i + 1);
        }
        createSllnPlot(means, name, theoMean);
    }

    private static void runCLT(DistType type, String name, int n, int m, double mu, double sigma) {
        double[] zScores = new double[m];
        Random rand = new Random();

        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += generateValue(type, rand);
            }
            double effectiveMu = (mu == 0 && type == DistType.CAUCHY) ? 0 : mu; 
            double effectiveSigma = (sigma == -1) ? 10.0 : sigma; 

            double z = (sum - n * effectiveMu) / (effectiveSigma * Math.sqrt(n));
            
            if (z > 5) z = 5; 
            if (z < -5) z = -5;
            zScores[i] = z;
        }
        createHistogram(zScores, name, n);
    }

    private static double generateValue(DistType type, Random rand) {
        double u = rand.nextDouble(); 
        switch (type) {
            case UNIFORM: return u;
            case EXPONENTIAL: return -Math.log(1 - u); 
            case PARETO_3: return 1.0 / Math.pow(1 - u, 1.0 / 3.0); 
            case PARETO_1_5: return 1.0 / Math.pow(1 - u, 1.0 / 1.5); 
            case CAUCHY: return Math.tan(Math.PI * (u - 0.5)); 
            default: return 0;
        }
    }

    private static void createSllnPlot(double[] data, String distName, double theoMean) {
        int width = 800, height = 400, padding = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE); g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK); g2d.drawLine(padding, height-padding, width-padding, height-padding); 
        g2d.drawLine(padding, padding, padding, height-padding); 

        if (theoMean != 0 || !distName.contains("Cauchy")) { 
            int yMean = height - padding - (int)(theoMean * 50); 
            if (distName.contains("Pareto")) yMean = height/2; 
            g2d.setColor(Color.RED);
            g2d.drawLine(padding, yMean, width-padding, yMean);
        }

        g2d.setColor(Color.BLUE);
        int prevX = padding, prevY = height - padding;
        double maxVal = 5.0; 
        if(distName.contains("Pareto")) maxVal = 10.0;
        
        for (int i = 0; i < data.length; i+=10) { 
            int x = padding + (int)((double)i / data.length * (width - 2 * padding));
            double val = data[i];
            if(val > maxVal) val = maxVal; if(val < -maxVal) val = -maxVal;
            int y = height/2 - (int)(val * 20); 
            if(distName.equals("Uniform (0,1)")) y = height - padding - (int)(val * (height-2*padding));
            
            g2d.drawLine(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
        
        g2d.setColor(Color.BLACK);
        g2d.drawString("SLLN: " + distName, width/2 - 50, 20);
        saveImage(image, "SLLN_" + distName.replaceAll("[^a-zA-Z0-9]", "") + ".png");
    }

    private static void createHistogram(double[] data, String distName, int n) {
        int width = 600, height = 400, padding = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE); g2d.fillRect(0, 0, width, height);

        int bins = 40; double minX = -4, maxX = 4;
        int[] freqs = new int[bins];
        for(double v : data) {
            if(v >= minX && v < maxX) freqs[(int)((v-minX)/(maxX-minX)*bins)]++;
        }
        
        int maxFreq = 0; for(int f : freqs) maxFreq = Math.max(maxFreq, f);
        
        g2d.setColor(Color.BLUE);
        for(int i=0; i<bins; i++) {
            int h = (int)((double)freqs[i]/maxFreq * (height-2*padding));
            g2d.fillRect(padding + i*((width-2*padding)/bins), height-padding-h, (width-2*padding)/bins -1, h);
        }
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(padding, padding, width-2*padding, height-2*padding);
        g2d.drawString("CLT: " + distName + " (n=" + n + ")", 50, 20);
        saveImage(image, "CLT_" + distName.replaceAll("[^a-zA-Z0-9]", "") + "_n" + n + ".png");
    }

    private static void saveImage(BufferedImage img, String filename) {
        try {
            String basePath = "C:\\Users\\nejla\\OneDrive\\Masaüstü\\TW5_Results";
            
            File dir = new File(basePath);
            if (!dir.exists()) {
                dir.mkdirs(); 
            }
            
            File outputFile = new File(dir, filename);
            ImageIO.write(img, "png", outputFile);
            System.out.println("Saved: " + outputFile.getAbsolutePath());
            
        } catch (IOException e) { e.printStackTrace(); }
    }
}
