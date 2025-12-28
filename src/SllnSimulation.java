import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

class SlnnSimulation {

    public static void main(String[] args) {
        int N = 10000;
        double MU = 0.5;

        double[] cumulativeMeans = new double[N];
        Random rand = new Random();
        double runningSum = 0.0;

        System.out.println("--- SLLN Simulation Starting ---");
        System.out.println("Target Theoretical Mean: " + MU);
        System.out.println("-------------------------------------");

        for (int i = 0; i < N; i++) {
            double x = rand.nextDouble();
            runningSum += x;

            int n = i + 1;
            double currentMean = runningSum / n;
            cumulativeMeans[i] = currentMean;

            if (n <= 5 || n % 2000 == 0 || n == N) {
                System.out.printf("Trial Number (n): %-6d -> Calculated Mean: %.5f\n", n, currentMean);
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Simulation completed. Generating graph...");

        int width = 1000;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        int padding = 50;
        int plotWidth = width - 2 * padding;
        int plotHeight = height - 2 * padding;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(padding, padding, plotWidth, plotHeight);

        int yMu = padding + plotHeight - (int)(MU * plotHeight);
        g2d.setColor(Color.RED);
        g2d.drawLine(padding, yMu, width - padding, yMu);
        g2d.drawString("Theoretical Mean (mu = 0.5)", width - 200, yMu - 5);

        g2d.setColor(Color.BLUE);
        int prevX = padding;
        int prevY = padding + plotHeight - (int)(cumulativeMeans[0] * plotHeight);

        for (int i = 1; i < N; i++) {
            int x = padding + (int)((double)i / N * plotWidth);
            int y = padding + plotHeight - (int)(cumulativeMeans[i] * plotHeight);
            g2d.drawLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("Number of Observations (n)", width / 2, height - 10);
        g2d.drawString("Cumulative Mean", 10, height / 2);
        g2d.drawString("Strong Law of Large Numbers (SLLN) Simulation", width / 3, 30);

        g2d.dispose();

        try {
            String outputDirPaths = "../results/figures";
            File outputDir = new File(outputDirPaths);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(outputDir, "slln_convergence.png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("Graph saved successfully: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}
