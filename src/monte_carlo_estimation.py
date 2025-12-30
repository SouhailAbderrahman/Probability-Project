import numpy as np
import matplotlib.pyplot as plt
import os

# Create the directory path if it doesn't exist
output_dir = 'results/figures'
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Now save the plot
plt.savefig('results/figures/pi_estimation.png')


def simulate_pi(n=10000):
    # 1. Generate random (x, y) points inside the unit square [cite: 80]
    x = np.random.uniform(0, 1, n)
    y = np.random.uniform(0, 1, n)

    # 2. Check the condition x^2 + y^2 <= 1 [cite: 81]
    inside_circle = (x ** 2 + y ** 2) <= 1

    # 3. Calculate cumulative pi estimates [cite: 82]
    # We use cumulative sum to see the convergence over time
    n_values = np.arange(1, n + 1)
    pi_estimates = 4 * np.cumsum(inside_circle) / n_values

    # 4. Plot the "pi estimate vs. number of points (n)" [cite: 83]
    plt.figure(figsize=(10, 6))
    plt.plot(n_values, pi_estimates, label='Monte Carlo Estimate', color='blue')

    # 5. Show the true pi value as a reference line [cite: 84]
    plt.axhline(y=np.pi, color='red', linestyle='--', label=f'True $\pi$ ({np.pi:.5f})')

    plt.title('Monte Carlo $\pi$ Estimation Convergence (SLLN)')
    plt.xlabel('Number of Points (n)')
    plt.ylabel('Estimate of $\pi$')
    plt.legend()
    plt.grid(True)

    # Save as required in the results/figures/ folder
    plt.savefig('results/figures/pi_estimation.png')
    plt.show()


if __name__ == "__main__":
    simulate_pi(10000)  # Recommended n >= 10,000 [cite: 56]