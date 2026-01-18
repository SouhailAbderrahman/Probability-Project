# IE 221 Probability - Complete Project Repository (Fall 2024-2025)

This repository contains the experimental verification of fundamental probability theorems, conducted as part of the IE 221 course. The project transitions from basic simulations to a deep dive into the limits of statistical laws across various distributions.

## Team Members
* **Batuhan Kara** - 2311021051
* **Souhail Oulad Abderrahman** - 2411013082
* **Mohamed Elkousy** - 2311021077
* **Mohammed Ali Chtibi** - 2311021078

---

## Project Structure
The repository is organized to ensure all figures and code are easily accessible for grading:

* 📂 **src/**: Java and Python source files for all tasks.
* 📂 **results/**: Simulation figures organized by distribution type.
    * 📁 `Uniform/`, `Exponential/`, `Pareto_3/`, `Pareto_1_5/`, `Cauchy/`
* 📂 **reports/**: Final comprehensive PDF reports.

---

## Detailed Task Overview

### Task 3: Central Limit Theorem (CLT) Analysis
The objective of Task 3 is to experimentally verify the Central Limit Theorem. 
* **Methodology:** For different sample sizes ($n=2, 5, 10, 30, 50, 100$), we perform $m=1000$ replications.
* **Visualization:** The convergence to normality is assessed using:
    * **Histograms:** To visualize the shape of the standardized sums.
    * **Normal Q-Q Plots:** To statistically assess how closely the distribution follows a normal curve.
* **Goal:** To observe how fast different distributions converge to a Normal Distribution as $n$ increases.

### Task 5: Final Phase - Distribution Comparison & Anomalies
This is the final stage where we test the limits of SLLN and CLT using five distinct distributions:
1. **Uniform & Exponential**: Baseline distributions where theorems hold perfectly.
2. **Pareto ($\alpha=3$)**: A heavy-tailed distribution that still satisfies theorem assumptions.
3. **Pareto ($\alpha=1.5$)**: A critical case where the variance is $\infty$ (infinite), causing the CLT to behave differently.
4. **Cauchy**: A distribution with undefined mean and variance, where both SLLN and CLT fail to converge.

---

## Installation & Usage

### Running the Final Simulation (Task 5)
```bash
javac src/Teamwork5Simulation.java
java src/Teamwork5Simulation
