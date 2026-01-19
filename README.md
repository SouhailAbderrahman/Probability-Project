# IE 221 Probability - Teamwork 5: Stress Testing SLLN and CLT

This repository contains the final experimental work for the IE 221 Probability course. The project focuses on verifying the limits of the **Strong Law of Large Numbers (SLLN)** and the **Central Limit Theorem (CLT)** by testing them against distributions with finite, infinite, and undefined moments.

## Team Members
* **Batuhan Kara** - 2311021051
* **Souhail Oulad Abderrahman** - 2411013082
* **Mohamed Elkousy** - 2311021077
* **Mohamed Ali Chtibi** - 2311021078

---

## Repository Structure
The project is organized to allow for easy access to code, figures, and analysis:

* 📂 **src/**: Source code for Java (SLLN simulations) and Python (CLT & Q-Q plot generation).
* 📂 **results/**: Organized simulation figures (Histograms & Q-Q Plots) for each distribution:
    * `Uniform/`, `Exponential/`, `Pareto_3/`, `Pareto_1_5/`, `Cauchy/`
* 📂 **reports/**: Final comprehensive project report in PDF format.

---

## Project Overview

### 1. SLLN Verification (Task 2)
Verification of the Strong Law of Large Numbers using cumulative mean plots. We observe how the sample mean converges to the theoretical expected value as $n$ increases to 10,000.

### 2. CLT Analysis (Task 3)
Verification of the Central Limit Theorem through:
* **Histograms:** Visualizing the shape of standardized sums for $n = 5, 30, 100$.
* **Normal Q-Q Plots:** Statistically assessing the normality of the sample mean distributions.

### 3. Final Comparison & Anomalies (Task 5)
A deep dive into why these theorems fail when theoretical assumptions are violated. We analyze five distinct cases:

| Distribution | $E[X]$ | $Var(X)$ | Theorem Status |
| :--- | :--- | :--- | :--- |
| **Uniform(0,1)** | 0.5 | 0.0833 | Success ✅ |
| **Exponential(1)** | 1.0 | 1.0 | Success ✅ |
| **Pareto ($\alpha=3$)** | 1.5 | 0.75 | Success ✅ |
| **Pareto ($\alpha=1.5$)** | 3.0 | $\infty$ | **CLT Fails** ⚠️ |
| **Cauchy** | Undefined | Undefined | **Both Fail** ❌ |

---

## Key Findings (Anomalies)
* **Pareto ($\alpha=1.5$):** The SLLN holds because the mean is finite, but the **CLT fails** because the variance is infinite. This results in heavy tails and significant deviations in Q-Q plots.
* **Cauchy:** This distribution has no defined mean or variance. Consequently, the sample mean never converges, and the distribution never becomes normal, violating both SLLN and CLT.

---

## Submission Checklist
* [x] Java & Python source code uploaded to `src/`.
* [x] Histograms and Q-Q Plots organized in `results/`.
* [x] README cleaned and professionally documented.
* [X] Final PDF report uploaded to `reports/`.
