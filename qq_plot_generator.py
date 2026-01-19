import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats
import os

# Kayıt konumu (OneDrive üzerindeki tam klasör yolun)
output_base = r'C:\Users\nejla\OneDrive\Masaüstü\Yeni klasör\results'

# Analiz edilecek 5 kritik dağılım (IE 221 Yönergesine Uygun)
distributions = {
    "Uniform": lambda n: np.random.uniform(0, 1, n),
    "Exponential": lambda n: np.random.exponential(1, n),
    "Pareto_alpha_3": lambda n: (np.random.pareto(3, n) + 1),
    "Pareto_alpha_1_5": lambda n: (np.random.pareto(1.5, n) + 1),
    "Cauchy": lambda n: np.random.standard_cauchy(n)
}

n_values = [5, 30, 100] # Örneklem boyutları
m = 1000 # Replikasyon sayısı

def generate_and_save_plots():
    print("Q-Q Plotlar oluşturuluyor, lütfen bekleyin...")
    
    for dist_name, dist_func in distributions.items():
        # Her dağılım için alt klasör oluştur
        dist_path = os.path.join(output_base, dist_name)
        if not os.path.exists(dist_path):
            os.makedirs(dist_path)
            print(f"Klasör oluşturuldu: {dist_name}")

        for n in n_values:
            # Örneklem ortalamalarını hesapla
            means = [np.mean(dist_func(n)) for _ in range(m)]
            
            # Veriyi standardize et (Z-score hesaplama)
            # Not: Cauchy ve Pareto 1.5'te standart sapma aşırı büyük çıkabilir
            standardized_means = (means - np.mean(means)) / np.std(means)
            
            # Q-Q Plot Çizimi
            plt.figure(figsize=(8, 6))
            stats.probplot(standardized_means, dist="norm", plot=plt)
            
            plt.title(f"Normal Q-Q Plot: {dist_name} (n={n})")
            plt.xlabel("Theoretical Quantiles")
            plt.ylabel("Sample Quantiles")
            plt.grid(True, linestyle='--', alpha=0.7)
            
            # Dosyayı kaydet
            file_name = f"QQPlot_{dist_name}_n{n}.png"
            save_path = os.path.join(dist_path, file_name)
            plt.savefig(save_path)
            plt.close()
            print(f"Başarıyla kaydedildi: {dist_name} (n={n})")

if __name__ == "__main__":
    # Çıktı klasörünün varlığını kontrol et
    if not os.path.exists(output_base):
        os.makedirs(output_base)
        
    generate_and_save_plots()
    print("\n" + "="*40)
    print("İŞLEM TAMAMLANDI!")
    print(f"Grafikler şurada: {output_base}")
    print("="*40)
