---
title: "Test on Regression"
layout: post
use_math: true
tags: ["statistics"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

[Introduction to Linear Regression]({{"/2021/06/06/introduction-to-linear-regression.html" | relative_url}}) 포스트에서 이어지는 포스트입니다. 

<br><span class="statement-title">TOC.</span><br>

- [Review]
- [Distribution of Regression Coefficients $B_0$ and $B_1$](#distribution-of-regression-coefficients)
- [Estimator of Error Variance $\sigma^2$](#estimator-of-error-variance)

<hr/>

### Review



<hr/>

이번 포스트에서는 아래의 두 질문에 대해 주요하게 살펴볼 예정이다.

Q1. What are the distributions of $B_1$ and $B_0$?

Q2. What can be an estimator for $\sigma^2$?

<hr/>

### Distribution of Regression Coefficients

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Assume $\epsilon_i$s are iid normal random variables; $\epsilon_i \sim N(0, \sigma^2)$.

Then,

$$
B_1 \sim N(\beta_1, \frac{\sigma^2}{S_{xx}})
$$

$$
B_0 \sim N(\beta_0, \frac{\sum x_i^2}{n \; S_{xx}} \cdot \sigma^2)
$$

</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

$$
\begin{aligned}
B_1 
&:= \frac{\sum_{i=1}^n (x_i - \bar{x})(y_i - \bar{y})}{S_{xx}} \\
&= \frac{\sum_{i=1}^n (x_i - \bar{x})y_i}{S_{xx}}
\end{aligned}
$$

is a linear combination of normal random variables $y_i$s, thus $B_1$ is also a normal RV.

Hense, we only need to find the mean and the variance of $B_1$.

<br/>

1\. Mean

$B_1$ is an unbiased estimator, so

$$
E[B_1] = \beta_1
$$

2\. Variance

$$
\begin{aligned}
\text{Var}(B_1) 
&= \text{Var}\left(\frac{\sum_{i=1}^n (x_i - \bar{x})y_i}{S_{xx}}\right)  \\
&= \frac{1}{S_{xx}^2} \cdot \left( \cancelto{S_{xx}}{\sum_{i=1}^n (x_i - \bar{x})} \right)^2 \cdot \cancelto{\sigma^2}{\text{Var}(y_i)} \\
&= \frac{\sigma^2}{S_{xx}}
\end{aligned}
$$

</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

$$
B_0 = \bar{y} - B_1 \bar{x}
$$

is also a linear combination of normal random variables $y_i$s.

1\. Mean

$B_0$ is also an unbiased estimator, so

$$
E[B_0] = \beta_0
$$

2\. Variance

(Homework 🎈)

</div>

<hr/>

### Estimator of Error Variance

Recall that $\sigma^2 = \text{Var}(\epsilon_i)$, and the $\epsilon_i$ was the difference btw response $y_i$ and true regression $\beta_0 + \beta_1 x_i$; $\epsilon_i = y_i - (\beta_0 + \beta_1 x_i)$.

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

The unbiased estimator of $\sigma^2$ is 

$$
s^2 := \frac{\sum_{i=1}^n (y_i - \hat{y}_i)^2}{n-2} = \frac{\text{SSE}}{n-2}
$$

</div>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

$s^2$ is independent of $B_1$ and $B_0$, and 

$$
\frac{(n-2)S^2}{\sigma^2} \sim \chi^2(n-2)
$$

</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

위의 두 정리에 대한 증명은 HW로 남겨둔다.

(Homework 🎈)

</div>

<hr/>

### Inferences for Regression Coefficients

Supp. we have sample points $(x_1, y_1), \dots, (x_n, y_n)$ from $Y_i = \beta_0 + \beta_1 x_i + \epsilon_i$ where $\epsilon_i$s are iid $N(0, \sigma^2)$. Here, $\beta_0$ and $\beta_1$ are unknown parameters.

우리는 위와 같은 상황에서 $\beta_0$, $\beta_1$에 대한 \<confidence interval\>을 찾고 또 그것을 이용해 **검정**을 수행해 볼 것이다!

<div class="statement" markdown="1">

우리는 $\beta_1$에 대한 point estimator로 $B_1 = S_{xy} / S_{xx}$를 사용했고, 이때 $B_1$의 분포는 아래와 같았다.

$$
B_1 \sim N \left( \beta_1, \; \sigma^2/S_{xx} \right)
$$

이때, $B_1$을 적당히 정규화시키면 아래와 같다.

$$
\frac{B_1 - \beta_1}{\sigma / \sqrt{S_{xx}}} \sim N(0, 1)
$$

이때, 우리는 error variance $\sigma^2$에 대한 값을 모른다. 따라서 이를 sample error variance인 $s^2 = \text{SSE}/(n-2)$로 대체해준다! 그 결과 분포는 \<t-distribution\>을 따른다.

$$
\frac{B_1 - \beta_1}{s / \sqrt{S_{xx}}} \sim t(n-2)
$$

이제 위의 분포에서 $\beta_1$에 대한 $100(1-\alpha)\%$ confidence interval을 구하면 아래와 같다.

$$
\left( b_1 - t_{\alpha/2} (n-2) \cdot \frac{s}{\sqrt{S_{xx}}}, \; b_1 + t_{\alpha/2} (n-2) \cdot \frac{s}{\sqrt{S_{xx}}}  \right)
$$

다음은 $B_1$에 대한 분포식을 활용해 검정을 진행하면 된다!! 😆

</div>

<div class="statement" markdown="1">

마찬가지로 $B_0$에 대해서도 검정을 수행해보자. $B_0$의 분포는 아래와 같았다.

$$
B_0 \sim N\left( \beta_0, \; \frac{\sigma^2 \cdot \sum_{i=1}^n x_i^2}{n S_{xx}}\right)
$$

$B_0$를 정규화하고, 또 $\sigma^2$를 $s^2$로 대체해주면 분포는 아래와 같다.

$$
\frac{B_0 - \beta_0}{s \sqrt{\frac{\sum_{i=1}^n x_i^2}{n S_{xx}}}} \sim t(n-2)
$$

마찬가지로 $\beta_0$에 대한 $100(1-\alpha)\%$ confidence interval을 구하고, 적당히 검정을 잘 수행하면 된다! 😆

</div>

<hr/>

이어지는 포스트에선 Linear Regression 모델에서 수행하는 Prediction에서 수행하는 추정에 대해 살펴볼 예정이다. 이번 포스트에서 살펴봤던 $B_1$, $B_0$의 분포를 종합적으로 사용할 예정이며, 이 과정을 통해 Regression으로 얻은 결과(response)의 신뢰도와 그 오차에 대해 더 살펴볼 수 있다.

👉 [Prediction on Regression]({{"/2021/06/10/prediction-on-regression.html" | relative_url}})

이번 포스트에 제시 했던 HW 문제의 풀이는 아래의 포스트에 별도로 정리해두었다.

👉 [Statistics - PS3]({{"/2021/06/10/statistics-ps3.html"}})