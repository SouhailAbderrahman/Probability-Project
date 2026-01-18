# IE 221 Probability - Complete Project Repository (Fall 2024-2025)

This repository contains the experimental verification of fundamental probability theorems, conducted as part of the IE 221 course[cite: 1]. The project transitions from basic simulations to a deep dive into the limits of statistical laws across various distributions[cite: 3, 5].

## Team Members
* **Batuhan Kara** - 2311021051
* **Souhail Oulad Abderrahman** - 2411013082
* **Mohamed Elkousy** - 2311021077
* **Mohammed Ali Chtibi** - 2311021078

---

## Project Structure
The repository is organized to ensure all figures and code are easily accessible for grading[cite: 46, 51]:

* 📂 **src/**: Java and Python source files for all tasks[cite: 47].
* 📂 **results/**: Simulation figures organized by distribution type[cite: 49].
    * 📁 `Uniform/`, `Exponential/`, `Pareto_3/`, `Pareto_1_5/`, `Cauchy/`
* 📂 **reports/**: Final comprehensive PDF reports[cite: 48].

---

## Detailed Task Overview

### Task 3: Central Limit Theorem (CLT) Analysis
The objective of Task 3 is to experimentally verify the Central Limit Theorem[cite: 21]. 
* **Methodology:** For different sample sizes ($n=2, 5, 10, 30, 50, 100$), we perform $m=1000$ replications[cite: 22].
* **Visualization:** The convergence to normality is assessed using:
    * **Histograms:** To visualize the shape of the standardized sums[cite: 23].
    * **Normal Q-Q Plots:** To statistically assess how closely the distribution follows a normal curve[cite: 24, 45].
* **Goal:** To observe how fast different distributions converge to a Normal Distribution as $n$ increases[cite: 26, 35].

### Task 5: Final Phase - Distribution Comparison & Anomalies
This is the final stage where we test the limits of SLLN and CLT using five distinct distributions[cite: 2, 3, 10]:
1. **Uniform & Exponential**: Baseline distributions where theorems hold perfectly.
2. **Pareto ($\alpha=3$)**: A heavy-tailed distribution that still satisfies theorem assumptions.
3. **Pareto ($\alpha=1.5$)**: A critical case where the variance is **infinite**, causing the CLT to behave differently.
4. **Cauchy**: A distribution with **undefined mean and variance**, where both SLLN and CLT fail to converge.

**Key Discovery:** We analyze why theorems fail when theoretical assumptions (like finite variance) are not met [cite: 38-40, 51].

---

## Installation & Usage

### Dependencies
* **Java**: Required for `src/*.java` files.
* **Python 3.x**: Required for `src/*.py` files.
    * Libraries: `numpy`, `matplotlib`.

### Running the Final Simulation (Task 5)
To execute the comprehensive analysis of all five distributions:
```bash
javac src/Teamwork5Simulation.java
java src/Teamwork5Simulation
