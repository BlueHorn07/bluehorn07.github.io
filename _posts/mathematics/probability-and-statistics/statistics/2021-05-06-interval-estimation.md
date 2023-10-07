---
title: "Interval Estimation"
toc: true
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<br><span class="statement-title">TOC.</span><br>

- [Interval Estimation](#interval-estimation)
  - [z-value: Estimate $\mu$ when $\sigma^2$ is known](#z-value-estimate-mu-when-sigma2-is-known)
    - [Error of Interval Estimation](#error-of-interval-estimation)
    - [one-sided confidence bounds](#one-sided-confidence-bounds)
  - [t-value: Estimate $\mu$ when $\sigma^2$ is unknown](#t-value-estimate-mu-when-sigma2-is-unknown)
- [Compare Point Estimator and Interval Estimator](#compare-point-estimator-and-interval-estimator)

<hr/>

# Introduction to Interval Estimation

Let $X_1, X_2, \dots, X_n$ be a random sample with $X_i \sim f(x; \theta)$, and $x_1, x_2, \dots, x_n$ be the values of the sample.

Here $\theta$ is unknown. The \<**interval estimation**\> is to find $(\hat{\theta}_L, \hat{\theta}_U)$ in which we expect to find the true value of the parameter $\theta$.

<br/>

Q. How to find the interval?

A1. $\theta \in (-\infty, \infty)$ → bad! 🤬

A2. We would construct two estimators $\hat{\theta}_L$ and $\hat{\theta}_U$ from the random sample such that $P(\hat{\theta}_L < \theta < \hat{\theta}_U) = 1 - \alpha$.

Here, $(\hat{\theta}_L, \hat{\theta}_U)$ is called an \<interval estimator\> of $\theta$, <span class="half_HL">$(\hat{\theta}_L, \hat{\theta}_U)$ is called a $100 \cdot (1 - \alpha)$% confidence interval</span>. 🔥

Also, $1-\alpha$ is called the \<**confidence coefficient**\> or \<**confidence level**\>. 🔥

We usually take $\alpha = 0.01, \; 0.05, \; 0.1$.

💥 Note that $(\hat{\theta}_L, \hat{\theta}_U)$ is not unique!! (꼭 대칭일 필요는 없다는 말)

<hr/>

# Interval Estimation

이제 상황에 따른 \<Interval Estimation\> 방법을 살펴보겠다!

- Estimate $\mu$ when $\sigma^2$ is known
- Estimate $\mu$ when $\sigma^2$ is unknown

<hr/>

## z-value: Estimate $\mu$ when $\sigma^2$ is known

<span class="statement-title">Example.</span><br>

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/interval-estimation-example-1.png" | relative_url }}" width=550>
</div>

- $n=100$, $\bar{X} = 170$.
- $\sigma = 20$

<div class="math-statement" markdown="1">

1\. We use $\bar{X}$ as a point estimator for $\mu$.

2\. We can use CLT here.

$$
\frac{\bar{X} - \mu}{\sigma / \sqrt{n}} \overset{D}{\approx} N(0, 1)
$$

$$
\begin{aligned}
0.95
&= P(\hat{\mu}_L < \mu < \hat{\mu}_U) \\
&= P(-z_{0.025} \le z \le z_{0.025}) \\
&\approx P \left(-1.96 \le \frac{\bar{X} - \mu}{\sigma / \sqrt{n}} \le 1.96 \right) \\
&= P \left( \bar{X} - 1.96 \frac{\sigma}{\sqrt{n}} \le \mu \le \bar{X} + 1.96 \frac{\sigma}{\sqrt{n}} \right)
\end{aligned}
$$

Here, we have $\hat{\mu}_L := \bar{X} - 1.96 \dfrac{\sigma}{\sqrt{n}}$, $\hat{\mu}_U := \bar{X} + 1.96 \dfrac{\sigma}{\sqrt{n}}$

$\therefore$ A 95% confidence interval would be $(170 - 3.92, 170 + 3.92)$.

</div>

<div class="notice" markdown="1">

<span class="statement-title">Remark.</span> Confidence Interval on $\mu$ when $\sigma^2$ is known<br>

Let $x_1, \dots, x_n$ be given data points from a random sample $X_1, \dots, X_n$ with known population variance $\sigma^2$ and unknown population mean $\mu$.

If $\bar{x}$ is the sample mean, a $100(1-\alpha)\%$ confidence interval for $\mu$ is given by

$$
\left( \bar{x} - z_{\alpha/2} \frac{\sigma}{\sqrt{n}} , \; \bar{x} + z_{\alpha/2} \frac{\sigma}{\sqrt{n}} \right)
$$

💥 Note that this is an approximate interval unless $X_i \sim N(\mu, \sigma^2)$.

</div>


Q1. Is it true that $P \left( \mu \in (\hat{\mu}_L, \hat{\mu}_U) \right) \overset{?}{=} 0.95$? 🔥

A1. No!! $\mu$ is not a random variable!

<br/>


Q2. Then what does the confidence interval really mean?

A2. Its the number of counts that $\mu$ is actually belong to sampled interval! 🔥

<div class="math-statement" markdown="1">

Let $x_1, \dots, x_n$ be a random sample. Supp. we obtain 1,000 samples and each sample has size $n$. Then, we have the following:

1st sample: $x_{11}, x_{12}, \dots, x_{1n}$ → $\bar{x}_1$ → confidence $$(\bar{\mu}_{1L}, \bar{\mu}_{1U})$$

2nd sample: $x_{21}, x_{22}, \dots, x_{2n}$ → $\bar{x}_1$ → confidence $$(\bar{\mu}_{2L}, \bar{\mu}_{2U})$$

$\vdots$

1000th sample: $x_{1000, 1}, x_{1000, 2}, \dots, x_{1000, n}$ → $\bar{x}_1$ → confidence $$(\bar{\mu}_{1000, L}, \bar{\mu}_{1000, U})$$

이렇게 얻은 1,000개의 interval estimation에 대해, 정확히 95%의 비율, 즉 950개의 interval에 true parameter $\mu$가 실제로 포함되어 있다는 말!

</div>

<hr/>

### Error of Interval Estimation

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Error of estimation<br>

Now, let's consider the error $\| \bar{x} - \mu \|$.

For an estimated interval $\left( \bar{x} - z_{\alpha/2} \frac{\sigma}{\sqrt{n}}, \bar{x} + z_{\alpha/2} \frac{\sigma}{\sqrt{n}}\right)$, the error is

$$
\left| \bar{x} - \mu \right| \le z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}}
$$

</div>

<br/>

<div class="theorem" markdown="1">

<span class="statement-title">Thereom.</span><br>

If $\bar{x}$ is used as an estimate of $\mu$, we can be $100(1-\alpha)\%$ confident that the <u>error</u> will not exceed $z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}}$.

</div>

<div class="theorem" markdown="1">

<span class="statement-title">Thereom.</span><br>

Q. How large can the sample size be if the error is at most $\epsilon$?

A. We want $\text{Err} = z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}}$ to be less than $\epsilon$.

$$
\text{Err} = z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}} \le \epsilon
$$

Solve the inequality for $n$.

$$
n \ge \left[ \frac{z_{\alpha/2} \cdot \sigma}{\epsilon} \right]^2
$$

</div>

<hr/>

### One-sided Confidence Bounds

지금까지 우리는 양 끝의 상황을 살펴보는 Two-sided Confidence Interval을 살펴보았다. 그러나 때로는 한쪽의 상황만 관심의 대상이 될 수도 있다! 그래서 아래와 같이 One-side에 대한 Confidence Interval을 구해야 할 수도 있다.

$$
P(\hat{\theta}_L \le \mu) = 1 - \alpha
$$

사실 two-sided에서 약간만 수정해주면 된다. two-sided에서의 Confidence Interval이 아래와 같다면,

$$
\bar{x} - z_{\alpha/2} \frac{\sigma}{\sqrt{n}} \; \le \; \mu \; \le \; \bar{x} + z_{\alpha/2} \frac{\sigma}{\sqrt{n}}
$$

여기에서 한쪽만 취해 $\alpha$를 사용하면 된다. 즉,

$$
\bar{x} - z_{\textcolor{red}{\alpha}} \frac{\sigma}{\sqrt{n}} \; \le \; \mu
$$

이것은 곧

$$
P \left(\bar{x} - z_{\textcolor{red}{\alpha}} \frac{\sigma}{\sqrt{n}} \; \le \; \mu \right) = 1 - \alpha
$$

와 같다!

<hr/>

## t-value: Estimate $\mu$ when $\sigma^2$ is unknown

앞에서 진행했던 과정을 다시 살펴보자. 우리는 CLT를 사용해 sample mean $\bar{X}$를 Normal 분포로 근사했다.

$$
Z = \frac{\bar{X} - \mu}{\sigma / \sqrt{n}}
$$

그런데! population variance $\sigma$를 모르는 지금 상황에서는 위와 같이 접근할 수 없다!! 😲 <span class="half_HL">$\sigma$를 모르기 때문에 CLT 근사식에서 분모 부분에 $\sigma$를 쓸 수 없기 때문이다!</span>

우리가 그나마 $\sigma^2$와 비슷하다고 생각하는 것이 있다. 바로 "sample variance" $S^2$! 이 녀석으로 $\sigma$를 대체해 식을 다시 써보자.

$$
\frac{\bar{X} - \mu}{S / \sqrt{n}}
$$

어라! 이 식은 [student's t-distribution]({{"/2021/04/27/student-t-distribution" | relative_url}})에서 이미 살펴보았다.

$$
\frac{\bar{X} - \mu}{S / \sqrt{n}} \; \overset{D}{\sim} \; t(n-1)
$$

그래서 $t(n-1)$ distribution에서 $100(1-\alpha)\%$ confidence interval을 구하면,

$$
P \left( -t_{\alpha/2} (n-1) < \frac{\bar{X} - \mu}{S / \sqrt{n}} < t_{\alpha/2}(n-1) \right) = 1 - \alpha
$$

가 된다!

<div class="notice" markdown="1">

<span class="statement-title">Remark.</span> Confidence Interval on $\mu$ when $\sigma^2$ is unknown<br>

Let $x_1, \dots, x_n$ be given data points from a <span class="half_HL"><u>normal random sample</u></span> $X_1, \dots, X_n$ with mean $\mu$ and variance $\sigma^2$.

Here, the population mean $\mu$ and the population variance $\sigma$ are both unknown.

If $\bar{x}$ is the sample mean and $s^2$ is the sample variance, then a $100(1-\alpha)\%$ confidence interval for $\mu$ is given by

$$
\left( \bar{x} - t_{\alpha/2}(n-1) \frac{s}{\sqrt{n}} , \; \bar{x} + t_{\alpha/2}(n-1) \frac{s}{\sqrt{n}} \right)
$$

</div>

<span class="statement-title">Remark.</span><br>

1\. The width of the interval is random!

$$
\left| \bar{x} - \mu \right| < t_{\alpha/2}(n-1) \cdot \frac{s}{\sqrt{n}}
$$

2\. This confidence interval is not an approximation, since we assume sample $X_i$ as iid. normal $\mu$, $\sigma^2$.

<hr/>

# Compare Point Estimator and Interval Estimator

Q. Does confidence interval give us more information about $\mu$ than a point estimator $\bar{x}$?

A. <span class="half_HL">Not really... 🤔</span>

<div class="light-margin" markdown="1">

[Point Estimator]

For a point estimator $\bar{x}$,

by LLN, $\bar{x} \rightarrow \mu$ as $n\rightarrow\infty$.

And the variance $\text{Var}(\bar{x}) = \sigma^2/n$.

</div>

<div class="light-margin" markdown="1">

[Interval Estimator]

For an interval estimator $\left(\bar{x} - z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}}, \; \bar{x} + z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}} \right)$,

the error is $\left\| \bar{x} - \mu \right\| \le z_{\alpha/2} \cdot \frac{\sigma}{\sqrt{n}}$.

</div>

💥 This means the width of the confidence interval is determined by the standard deviation of the point estimator!! 이것은 둘 중 한 Estimator가 개선되면, 다른 하나의 성능도 개선됨을 말한다.

<hr/>

이어지는 포스트들에서는 상황별로 \<Interval Estimation\>을 수행하는 방법을 살펴볼 예정이다! 🤩

- [Prediction & Tolerance Estimation]({{"/2021/05/13/prediction-and-tolerance-interval" | relative_url}})
- [Two Samples Estimation: Diff Btw Two Means]({{"/2021/05/13/two-samples-estimation-diff-btw-two-means" | relative_url}})
- [Two Samples Estimation: Paired Observations]({{"/2021/05/13/two-samples-estimation-paired-observations" | relative_url}})
- [Proportion Estimation]({{"/2021/05/14/proportion-estimation-on-bernoulli" | relative_url}})
  - Single Sample Estimation: Proportion Estimation
  - Two Samples Estimation: Diff btw Two Proportions
- [Variance Estimation]({{"/2021/05/16/variance-estimation" | relative_url}})
  - Single Sample Estimation: Variance Estimation
  - Two Samples Estimation: The Ratio of Two Variances