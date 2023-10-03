---
title: "Two Samples Estimation: Diff Btw Two Means"
toc: true
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

[Interval Estimation]({{"/2021/05/06/interval-estimation.html" | relative_url}}) 포스트에서 다룬 \<Interval Estimation\>을 특정 상황에 어떻게 적용할 수 있는지를 다루는 포스트입니다.

<br><span class="statement-title">TOC.</span><br>

- [$\sigma_1^2$ and $\sigma_2^2$ are known](#sigma_12-and-sigma_22-are-known)
- [$\sigma_1^2$ and $\sigma_2^2$ are unknown, but known that $\sigma_1^2 = \sigma_2^2$](#sigma_12-and-sigma_22-are-unknown-but-known-that-sigma_12--sigma_22)
  - pooled sample variance; $S_p^2$
- [$\sigma_1^2$ and $\sigma_2^2$ are unknown and unequal](#sigma_12-and-sigma_22-are-unknown-and-unequal)
  - Welch’s t-test

<hr/>

# Two Samples Estimation

Supp. there are two populations and assume that both follow some random distributions with means $\mu_1$ and $\mu_2$ and variances $\sigma_1^2$, $\sigma_2^2$ respectively.

Take random samples $X_1, \dots, X_{n_1}$ and $Y_1, \dots, Y_{n_2}$, and assume that $X_i$s and $Y_j$s are independent.

Supp. that their observed sample means are $\bar{x}$ and $\bar{y}$, and their sample variances are $s_1^2$ and $s_2^2$.

<div class="notice" markdown="1">

✨ Goal: Find $100(1-\alpha)\%$ confidence interval for $\mu_1 - \mu_2$.

</div>

<hr/>

## $\sigma_1^2$ and $\sigma_2^2$ are known

By CLT, $\bar{X} \overset{D}{\approx} N(\mu_1, \sigma_1^2 / n_1)$ and $\bar{Y} \overset{D}{\approx} N(\mu_2, \sigma_2^2 / n_2)$, in addition $\bar{X} \perp \bar{Y}$.

Then,

$$
\bar{X} - \bar{Y} \overset{D}{\approx} N(\mu_1 - \mu_2, \; \sigma_1^2 / n_1 + \sigma_2^2 / n_2)
$$

then, the confidence interval is

$$
\left( \bar{x} - \bar{y} - z_{\alpha/2} \cdot \sqrt{\frac{\sigma_1^2}{n_1} + \frac{\sigma_2^2}{n_2}} , \;
\bar{x} - \bar{y} + z_{\alpha/2} \cdot \sqrt{\frac{\sigma_1^2}{n_1} + \frac{\sigma_2^2}{n_2}} \right)
$$

💥 이때, 주의할 점은 이것은 true interval이 아니라 approximate interval이라는 점이다; by CLT

💥 또, 이 근사는 $X_i$, $Y_j$가 모두 iid normal이여야 가능하다!

<hr/>

## $\sigma_1^2$ and $\sigma_2^2$ are unknown, but known that $\sigma_1^2 = \sigma_2^2$

앞에서 우리는 CLT를 사용해 $\frac{\bar{X} - \mu}{\sigma / \sqrt{n}}$를 사용했었다. 하지만, 이번에는 정확한 $\sigma^2$의 값을 알지 못하기 때문에 $\sigma^2$ 대신 sample variance $s^2$을 사용한다!!

[Previous]

$$
\frac{(\bar{X} - \bar{Y}) - (\mu_1 - \mu_2)}{\sqrt{\dfrac{\color{red}{\sigma_1^2}}{n_1} + \dfrac{\color{red}{\sigma_2^2}}{n_2}}} \;\overset{D}{\sim} \; N(0, 1)
$$

우리는 $\sigma^2$를 대체하기 위해 \<**pooled sample variance**\>라는 두 샘플의 sample variance를 종합한 녀석을 쓸 것이다.

<div class="notice" markdown="1">

<span class="statement-title">Recall.</span> sample variance and chi-square<br>

$$
\frac{(n -1) \cdot S^2}{\sigma^2} = \sum^n \left(\frac{X_i - \bar{X}}{\sigma}\right)^2 \; \sim \; \chi^2 (n-1)
$$

<span class="statement-title">Definition.</span> pooled sample variance<br>

$$
\begin{aligned}
S_p^2
&= \frac{\displaystyle \sum^{n_1}_1 (X_i - \bar{X})^2 + \sum^{n_2}_1 (Y_i - \bar{Y})^2}{n_1 - 1 + n_2 - 1}
\end{aligned}
$$

</div>

위의 식을 잘 변형해보면 아래와 같다.

$$
(n_1 - 1 + n_2 - 1) \cdot S_p^2 = (n_1 - 1) \cdot S_1^2 + (n_2 - 1) \cdot S_2^2
$$

$$
\frac{(n_1 - 1 + n_2 - 1) \cdot S_p^2}{\sigma^2} = \frac{(n_1 - 1) \cdot S_1^2 + (n_2 - 1) \cdot S_2^2}{\sigma^2} \; \overset{D}{\sim} \; \chi^2(n_1 + n_2 - 2)
$$

따라서! pooled sample variance $S_p^2$를 바탕으로 식으로 다시 쓰면,

$$
\frac{(\bar{X} - \bar{Y}) - (\mu_1 - \mu_2)}{S_p \sqrt{\frac{1}{n_1} + \frac{1}{n_2}}} \; \overset{D}{\sim} t (n_1 + n_2 - 2)
$$

그 다음은 지금까지 해온 것처럼 t-distribution을 바탕으로 interval estimation을 진행하면 된다!

<hr/>

## $\sigma_1^2$ and $\sigma_2^2$ are unknown and not equal

먼저 population parameter를 기준으로 식을 세워보자.

$$
\frac{(\bar{X} - \bar{Y}) - (\mu_1 - \mu_2)}{\sqrt{\frac{\sigma_1^2}{n_1} + \frac{\sigma_2^2}{n_2}}} \; \sim \; N(0, 1)
$$

이때, 우리가 $\sigma_1^2$, $\sigma_2^2$를 알지 못하기 때문에 sample variance로 해당 부분을 대체한다.

$$
\frac{(\bar{X} - \bar{Y}) - (\mu_1 - \mu_2)}{\sqrt{\frac{s_1^2}{n_1} + \frac{s_2^2}{n_2}}} \; \overset{D}{\sim} \; ??
$$

이때, 우변이 ??인 이유는 아직까지 위의 경우에 대해 정확한 분포를 모르기 때문이다!! 그래서 이것이 어떤 DOF $d$의 t-distribution을 만족한다고 근사하여 estimation을 진행한다!

$$
\frac{(\bar{X} - \bar{Y}) - (\mu_1 - \mu_2)}{\sqrt{\frac{s_1^2}{n_1} + \frac{s_2^2}{n_2}}} \; \overset{D}{\sim} \; t(d)
$$

where

$$
d = \frac{(s_1^2/n_1 + s_2^2/n_2)^2}{(s_1^2/n_1)^2 / (n_1-1) + (s_2^2/n_2)^2 / (n_2-1)}
$$

이 근사법을 \<**Welch's t-test**\>라고 하며, 이때 dof를 구하기 위해 사용한 식을 \<Welch-Satterthwaite equation\>이라고 한다.

<hr/>

# 맺음말

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/sampling-distribution-table-2.png" | relative_url }}" width=750>
</div>

이어지는 포스트에서는 또다른 Two Samples Estimation인 \<Paired Observation\>의 경우를 살펴본다! 😁

👉 [Two Samples Estimation: Paired Observations]({{"/2021/05/13/two-samples-estimation-paired-observations.html" | relative_url}})

<br/>

지금까지의 관심사는 population mean $\mu$에 대한 추정이었다. population mean $\mu$를 추정하거나 두 population mean $\mu$의 차이값을 추정했다. 그 다음 포스트에서는 population variance $\sigma^2$를 추정한다. $\sigma^2$를 추정하거나, 두 샘플의 population variance의 비율 $\sigma_1^2 / \sigma_2^2$를 추정한다.

👉 [Variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})