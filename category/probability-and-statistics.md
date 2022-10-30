---
title: "Probability and Statistics"
layout: post
tags: ["probability", "statistics"]
hidden: true
---

2021-1학기에 수강한 POSTECH 김건우 교수님의 **"확률과 통계(MATH230)"** 수업에서 배운 것과 공부한 것을 정리한 지킬 블로그입니다. 현실을 적당한 확률 분포로 모델링 하고, 또 확률을 기반으로 검정을 진행해 의사 결정을 수행하는 접근법을 배울 수 있었습니다 😁

<div class="math-statement" style="padding: 10px 20px" markdown="1">

[목차]

1. Probability
2. Random Variables & Probability Distributions
3. Mathematical Expectation
4. Some Discrete Probability Distributions
5. Some Continuous Probability Distributions
6. Functions of Random Variables
7. Fundamental Sampling Distributions
8. One- and Two-Sample Estimation Problems
9. One- and Two-Sample Tests of Hypotheses
10. Simple Linear Regression

\* [Problem Solving](#problem-solving)

</div>

<hr/>

# Probability Theory

- [Sample Space]({{"2021/02/27/sample-space.html" | relative_url}})
  - Sample Space & Event
  - Permutation & Combination
- [Probability of an Event]({{"2021/02/28/probability-of-an-event.html" | relative_url}})
  - Probability
  - Conditional Probability
    - Independent Events
- [Bayes' Rule]({{"2021/03/02/Bayes-Rule.html" | relative_url}})
  - Law of Total Probability
  - Meaning of Bayes Rule
- [Monti Hall Problem]({{"2021/03/02/Monti-Hall-Problem.html" | relative_url}})[^1]

<br/>

- [Random Variables and Probability Distriubtions]({{"2021/03/05/random-variable-and-prob-distribution.html" | relative_url}})
  - Random Variable $X$
  - Probability Distributions
    - PMF $f(x)$ & CDF $F(x)$ for Discrete RV
    - PDF $f(x)$ & CDF $F(x)$ for Continuous RV
- [Joint Probability Distribution]({{"/2021/03/09/joint-probability-distribution.html" | relative_url}})
- [Mean, Variance and Covariance]({{"/2021/03/16/mean-variance-covariance.html" | relative_url}})
- [Chebyshev's Inequality]({{"/2021/03/17/chebyshev's-inequality.html" | relative_url}})

<br/>

- [Discrete Probability Distribution - 1]({{"/2021/03/17/discrete-probability-distributions-1.html" | relative_url}})
  - (Discrete) Uniform Distribution
  - Bernoulli Distribution
  - Binomial Distribution
    - Multinomial Distribution
- [Discrete Probability Distribution - 2]({{"/2021/03/24/discrete-probability-distributions-2.html" | relative_url}})
  - HyperGeometric Distribution
    - Multivariate HyperGeometric Distribution
  - Geometric Distribution
    - Negative Binomial Distribution
      - [Negative Binomial Theorem]({{"/2022/10/30/negative-binomial-theorem.html" | relative_url}}) 🎈
- [Poisson Distribution]({{"/2021/03/25/poisson-distribution.html" | relative_url}})
  - Law of Rare Events
  - Bernoulli Process & Poisson Process

<br/>

- Continuous Probability Distribution
  - [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution.html" | relative_url}})
  - [Exponential Distribution]({{"/2021/03/31/exponential-distribution.html" | relative_url}})
  - [Gamma Distribution]({{"/2021/04/05/gamma-distribution.html" | relative_url}})
  - [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution.html" | relative_url}})
  - [Beta Distribution]({{"/2021/04/07/beta-distribution.html" | relative_url}})
  - [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution.html" | relative_url}})
  - [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})

<br/>

- [Transformations of Random Variable - 1]({{"/2021/04/11/transformations-of-random-variable-1.html" | relative_url}})
- [Transformations of Random Variable - 2]({{"/2021/04/12/transformations-of-random-variable-2.html" | relative_url}})
- [Momemtum Generating Function]({{"/2021/04/13/momemtum-generating-function.html" | relative_url}})

<hr/>

# Statistics

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/sampling-distribution-table-1.png" | relative_url }}" width=550>
</div>

- [Sampling Distribution]({{"/2021/04/25/sampling-distribution.html" | relative_url}})
  - statistic; 통계량
    - sample distribution
- [Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt.html" | relative_url}})
  - WLLN; Weak Law of Large Numbers
  - CLT; Central Limit Theorem
    - *proof*
- [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance.html" | relative_url}})
- [Student's t-distribution]({{"/2021/04/27/student-t-distribution.html" | relative_url}})
  - Population Variance $\sigma^2$ is unknown
- [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}})
- [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile.html" | relative_url}})

<br/>

- [Point Estimation]({{"/2021/05/05/point-estimation.html" | relative_url}})
  - unbiased estimator: $E(\hat{\Theta}) = \theta$
  - variance of estimator: $\text{Var}(\hat{\Theta})$
  - the most efficient estimator
  - Mean Squared Error of estimator
- [Interval Estimation]({{"/2021/05/06/interval-estimation.html" | relative_url}})
  - z-value: Estimate $\mu$ when $\sigma^2$ is known
  - t-value: Estimate $\mu$ when $\sigma^2$ is unknown
- [Prediction & Tolerance Estimation]({{"/2021/05/13/prediction-and-tolerance-interval.html" | relative_url}})
- [Two Samples Estimation: Diff Btw Two Means]({{"/2021/05/13/two-samples-estimation-diff-btw-two-means.html" | relative_url}})
- [Two Samples Estimation: Paired Observations]({{"/2021/05/13/two-samples-estimation-paired-observations.html" | relative_url}})
- [Proportion Estimation]({{"/2021/05/14/proportion-estimation-on-bernoulli.html" | relative_url}})
  - Single Sample Estimation: Proportion Estimation
  - Two Samples Estimation: Diff Btw Two Proportions
- [Variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})
  - Single Sample Estimation: Variance Estimation
  - Two Samples Estimation: The ratio of two variances

- [Maximum Likelihood Estimation]({{"/2021/05/17/maximum-likelihood-estimation.html" | relative_url}})

<br/>

- [Introduction to Hypothesis Tests]({{"/2021/05/18/introduction-to-hypothesis-tests.html" | relative_url}})
  - Null Hypothetsis $H_0$ & Alternative Hypothesis $H_1$
  - Test Statistic
    - Rejection Region or Critical Region; $X \ge C$
    - Critical Value; $C$
  - [Type 1 Error & Type 2 Error]({{"/2021/05/18/introduction-to-hypothesis-tests.html#t1-erorr--t2-error" | relative_url}})
    - [Significance level; Size of Test; 유의 수준 $\alpha$]({{"/2021/05/18/introduction-to-hypothesis-tests.html#significance-level-alpha" | relative_url}}) 🔥
    - [Power of Test; 검정력 $\gamma(\theta)$]({{"/2021/05/18/introduction-to-hypothesis-tests.html#power-of-test-gammatheta" | relative_url}}) 🔥
    - [p-value; 유의 확률]({{"/2021/05/18/introduction-to-hypothesis-tests.html#p-value" | relative_url}}) 🔥🔥
- [Sample Mean Test]({{"/2021/05/19/sample-mean-test.html" | relative_url}})
- [Choice of Sample Size for Testing Mean]({{"/2021/05/20/choice-of-sample-size-for-testing-mean.html" | relative_url}})
- [Proportion Test]({{"/2021/05/26/proportion-test.html" | relative_url}})
- [Variance Test]({{"/2021/05/26/variance-test.html" | relative_url}})
- [Chi-square Goodness-of-fit Test]({{"/2021/05/27/chi-square-goodness-of-fit-test.html" | relative_url}})
  - Chi-square-Test; 카이제곱-검정
  - Test on Independence
  - Test on Homogeneity

<br/>

- [Introduction to Linear Regression]({{"/2021/06/06/introduction-to-linear-regression.html" | relative_url}})
  - Simple Linear Regression
  - Least Square Method
    - unbiased regression coefficients
  - R-square $R^2$; 결정 계수
- [Test on Regession]({{"/2021/06/09/test-on-regression.html" | relative_url}})
  - Distribution of Regression Coefficients $B_0$ and $B_1$
  - Estimator of $\sigma^2$
- [Prediction on Regression]({{"/2021/06/10/prediction-on-regression.html" | relative_url}})
  - Estimate on Mean Response $\mu_{Y \mid x_0}$
  - Prediction Interval

<hr/>

# Problem Solving

## Probability

(준비중입니다! 😉)

## Statistics

- [PS1]({{"/2021/06/06/statistics-ps1.html" | relative_url}})
  - sample variance $S^2$ is not the minimal variance estimator
  - MSE(Mean Squared Error) is sum of variance and square of bias
  - Compare $S^2$ and $\hat{S}^2$ using MSE
- [PS2]({{"/2021/06/09/statistics-ps2.html" | relative_url}})
  - $\sum e_i = 0$
  - $\sum x_i e_i = 0$
  - $\text{SST} = \text{SSR} + \text{SSE}$
- [PS3]({{"/2021/06/10/statistics-ps3.html" | relative_url}})
  - Variance of estimator $B_0$
  - unbiased estimator of $\sigma^2$ is $s^2$
  - (not yet) $s^2 \perp B_1$, and $s^2 \perp B_0$
  - (not yet) the distribution of $s^2$

<hr/>

# Eassays

- [Degree of Freedom in Statistics]({{"/2022/10/09/degree-of-freedom-in-statistics.html" | relative_url}})

<hr/>

# Study Materials
- 『Probability & Statistics for Engineers & Scientists』 Walpole·Myers·Myers·Ye, 9th ed. 

<hr/>

[^1]: 정규수업 내용은 아니지만, 교수님께서 수업 시간에 잠깐 언급하셨습니다 😊
