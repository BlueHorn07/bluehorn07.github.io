---
title: "Proportion Estimation on Bernoulli Distribution"
layout: post
tags: ["statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

[Interval Estimation]({{"/2021/05/06/interval-estimation.html" | relative_url}}) 포스트에서 다룬 \<Interval Estimation\>을 특정 상황에 어떻게 적용 하는지를 다루는 포스트이다. 지금까지의 추정(Estimation)은 모두 \<Normal Distribution\>에서 추출한 샘플에 대해 시행했다. 이번에는 \<Bernoulli Distribution\>의 샘플에서 추정을 수행한다. 즉, \<Bernoulli Distribution\>의 parameter인 확률 $p$가 추정의 대상인 것이다!

<br><span class="statement-title">TOC.</span><br>

- [Single Sample Estimation: Proportion Estimation](#singe-sample-estimation-proportion-estimation)
- [Two Samples Estimation: Diff Btw Two Proportions](#two-samples-estimation-diff-btw-two-proportions)

<hr/>

# Single Sample Estimation: Proportion Estimation

Supp. we have a p-coin. We want to verify that the coin is really a p-coin.

<div class="statement" markdown="1">

✨ Goal: Estimate $p$ based on the sample points!

</div>

<div class="light-margin" markdown="1">

Q1. Which coin estimator can we use for $p$?

A1. Here, we can use $\hat{p} = \dfrac{\text{# of heads}}{\text{# of toss}} = \dfrac{X_1 + \cdots + X_n}{n} = \bar{X}$.

</div>

<div class="light-margin" markdown="1">

Q2. How about 95% confidence interval for $p$?

A2. We can use CLT!!

$$
\frac{\bar{X} - \mu}{\sigma / \sqrt{n}} = \frac{\hat{p} - p}{\sqrt{p(1-p)} / \sqrt{n}} \approx N(0, 1)
$$

then, the confidence interval is

$$
\hat{p} - z_{\alpha/2} \cdot \sqrt{\frac{p(1-p)}{n}} 
\; \le \; p \; \le \; 
\hat{p} + z_{\alpha/2} \cdot \sqrt{\frac{p(1-p)}{n}}
$$

💥 하지만!!! 위의 식은 문제가 있다!! 바로 <span class="half_HL">$p$를 추정하기 위해 interval을 잡았는데, interval의 좌우변에 또 $p$가 등장한다</span>는 것이다!! 😲

[Solution 1] solve the inequality for $p$.

[Solution 2] replace $p$ by $\hat{p}$ <small>// if $n$ is large, $\hat{p} \rightarrow p$ by LLN</small>

Therefore, we the $n$ is large, 

$$
\hat{p} - z_{\alpha/2} \cdot \sqrt{\frac{\hat{p}(1-\hat{p})}{n}} 
\; \le \; p \; \le \; 
\hat{p} + z_{\alpha/2} \cdot \sqrt{\frac{\hat{p}(1-\hat{p})}{n}}
$$

</div>

<hr/>

이번에는 \<Proportion Estimation\>의 **Error**에 대해 살펴보자. Error는 이전의 Estimation과 마찬가지로 아래와 같이 제시된다.

<div align="center" markdown="1">

The error $\left\| \hat{p} - p \right\|$ will not exceed $z_{\alpha/2} \cdot \sqrt{\dfrac{\hat{p}\hat{q}}{n}}$

</div>

Q. How large should the sample size be so that the error is at most $\epsilon$?

이것은 Error에 대한 식을 $n$으로 다시 풀어서 쉽게 유도할 수 있었다.

$$
\begin{aligned}
z_{\alpha/2} \cdot \sqrt{\frac{\hat{p}\hat{q}}{n}} 
&\le \epsilon \\
z_{\alpha/2}^2 \cdot \frac{\hat{p}\hat{q}}{n}
&\le \epsilon^2 \\
\frac{(z_{\alpha/2})^2 \cdot \hat{p}\hat{q}}{\epsilon^2}
&\le n
\end{aligned}
$$

이때, 위의 식은 문제가 있다!! 바로 sample proportion $\hat{p}$는 우리가 $n$을 결정해 샘플링하기 전에는 그 값을 알 수 없다는 것이다!!!

[Solution 1] Guess $\hat{p}$, or use small size of sample to estimate $p$. From this, we get $\hat{p}$, and then use it!

[Solution 2] Consider the worst case, maximum error situation.

$$
z_{\alpha/2} \cdot \sqrt{\frac{\hat{p}(1-\hat{p})}{n}} \le z_{\alpha/2} \cdot \sqrt{\frac{1}{4}\frac{1}{n}} \le \epsilon
$$

$$
n \ge \left( \frac{z_{\alpha/2}}{2\epsilon} \right)^2
$$

<hr/>

# Two Samples Estimation: Diff Btw Two Proportions

[Two Samples Estimation: Diff Btw Two Means]({{"/2021/05/13/two-samples-estimation-diff-btw-two-means.html" | relative_url}}) 포스트에서 이것과 비슷한 상황을 접한 적이 있다. 그때는 Normal Distribution에서 수행했고, sample variance $s^2$를 쓰게 되면서, pooled sample variance $S_p^2$나 \<Welch's t-test\>를 수행했다. 위의 상황과 \<Proportion Estimation\>이 어떻게 다른지 비교하면서 살펴보자!

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/difference-btw-two-proportions-1.png" | relative_url }}" width=650>
</div>

Select $n_1$: math major, and $n_2$: physics major, independently.

Assume $X_1, \dots, X_{n_1}$ and $Y_1, \dots, Y_{n_2}$ are two independent random samples.

From the sample, we get $\hat{p_1} = \bar{x}$, $\hat{p_2} = \bar{y}$. Then, $\hat{p_1} - \hat{p_2}$ can be estimator for $p_1 - p_2$.

By CLT,

$$
\frac{(\hat{p_1} - \hat{p_2}) - (p_1 - p_2)}{\sqrt{p_1q_1/n_1 + p_2q_2/n_2}} \; \approx \; N(0, 1)
$$

이때, 식에서 population proportion $p_1$, $p_2$ 부분을 sample proportion $\hat{p}_1$, $\hat{p}_2$로 대체하면,

$$
\frac{(\hat{p_1} - \hat{p_2}) - (p_1 - p_2)}{\sqrt{\hat{p}_1\hat{q}_1/n_1 + \hat{p}_2\hat{q}_2/n_2}}
$$

Then, the $100(1-\alpha)\%$ confidence interval for $p_1 - p_2$ is 

$$
\left( (\hat{p_1} - \hat{p_2}) - z_{\alpha/2} \cdot \sqrt{\dfrac{\hat{p}_1\hat{q}_1}{n_1} + \dfrac{\hat{p}_2\hat{q}_2}{n_2}}, \;
(\hat{p_1} - \hat{p_2}) + z_{\alpha/2} \cdot \sqrt{\dfrac{\hat{p}_1\hat{q}_1}{n_1} + \dfrac{\hat{p}_2\hat{q}_2}{n_2}} \right)
$$

<hr/>

# Proportion Estimation and t-distribution

[\<Mean Estimation\>]({{"/2021/05/06/interval-estimation.html" | relative_url}})의 기억을 떠올리면, population variance $\sigma^2$를 모르기에 sample variance $s^2$를 쓰고 normal distribution $N(0, 1)$ 대신 t-distribution $t(n)$로 근사한 기억이 있을 것이다.

$$
\frac{\bar{X} - \mu}{S / \sqrt{n}} \sim t(n-1)
$$

\<Proportion Estimation\>에서도 popualtion proportion $p$의 값을 모르기에 sample proportion인 $\hat{p}$를 대신 사용 했다. 

$$
\frac{\bar{X} - \mu}{\sigma / \sqrt{n}} 
= \frac{\hat{p} - p}{\sqrt{pq} / \sqrt{n}} 
= \frac{\hat{p} - p}{\sqrt{\hat{p}\hat{q}} / \sqrt{n}} 
\approx N(0, 1)
$$

그러나 이번 경우는 t-distribution이 아니라 그대로 normal distribution으로 근사하여 식을 얻었다. 왜 이번 경우엔 t-distribution이 아닌 걸까?

<br/>

\<Mean Estimation\>에선 개별 sample $X_i$가 정규 분포를 따르는 RV라는 가정이 있다.

$$
X_i \sim N(\mu, \sigma^2)
$$

그러나 \<Proportion Estimation>에선 개별 outcome $X_i$가 동전 앞뒤 같은 카테고리 변수이다. 그리고 이것은 bernoulli distribution을 따른다.

$$
X_i \sim \text{Ber}(p)
$$

즉, sample variable $X_i$이 normal distribution sample이 아니기 때문에 sample proportion $\hat{p}$을 쓰더라도 \<t-distribution\>으로 근사하지 않는 것이다라고 이해하고 있다. t-distribution을 생각할 전제가 성립하지는 것이라고 말이다!

<hr/>

# 맺음말

지금까지

- population mean $\mu$
- population proportion $p$

에 대한 추정을 살펴봤다. 다음 포스트에서는 sample variance $S^2$로부터 population variance $\sigma^2$를 추정하는 방법에 대해 살펴보겠다.

👉 [Variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})

# Reference

- [Can you use t-distribution for proportions?](https://stats.stackexchange.com/a/463979)

