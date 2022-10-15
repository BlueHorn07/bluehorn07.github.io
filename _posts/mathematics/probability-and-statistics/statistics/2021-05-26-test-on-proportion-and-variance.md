---
title: "Test on Proportion and Variance"
layout: post
tags: ["statistics"]
---

학교에서 “확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다🎲

<br><span class="statement-title">TOC.</span><br>

- [Test on Proportion](#test-on-proportion)
  - [Test on One Proportion](#test-on-one-proportion)
  - [Test on Two Proportions](#test-on-two-proportions)
- [Test on Variance](#test-on-variance)

<hr/>

### Test on Proportion

Single Sample에서의 경우와 Two Sample에서의 경우를 모두 다룬다.

<hr/>

#### Test on One Proportion

Consider a p-coin, and $p$ is unknown.

We want to test

- $H_0: p=1/3$
- $H_1: p>1/3$

We toss a coin $n$ times independently, and let $x$ be the #. of heads in theses $n$ trials.

Q1. What is the p-value?

A1. $H_1$이 $p > 1/3$의 형태이므로 우리는 $X$가 특정값 $C$ 이상일 때, $H_0$를 reject 한다. 따라서, 이때 우리가 얻은 $x$값으로 p-value를 유도하려면, $C$에 $x$를 대입해 p-value를 유도하면 된다!

$$
P( X \ge C \mid p = 1/3) = P(X \ge x \mid p = 1/3)
$$

Q2. 만약 $H_1: p < 1/3$ 형태라면?

A2. 위의 p-value 식에서 부호만 반대로 적어주면 된다.

$$
P(X \le x \mid p = 1/3)
$$

Q3. 만약 $H_1: p \ne 1/3$의 형태라면? (two-sided test)

A3. 우리는 $X \le C_1$이거나 $X \ge C_2$라면, $H_0$를 기각할 것이다. 따라서

$$
P(X \le C_1 \;\; \text{or} \;\; X \ge C_2 \mid p = 1/3)
$$

그런데 우리는 실험에서 하나의 $x$ 값만을 얻었고, 위의 과정에 따르면, 이 값을 $C$에 대입했다. 이것을 위 식에 적용하면,

$$
P(X \le x \;\; \text{or} \;\; X \ge x \mid p = 1/3) = 1
$$

가 되는데, 이 값은 1이다! 😲 그래서 우리는 오직 하나의 $x$ 값을 갖기 때문에, one-side test를 수행하는게 합리적이다.

$X \le C_1$과 $X \ge C_2$ 중 어떤 방향을 취할지 결정하기 위해 expected value인 $E[X]$를 기준으로 삼자. 이 값은 $np$이다.

- If $x < np$, take $X \le C_1$
- If $x > np$, take $X \ge C_2$

지금의 예시에서는 $x < np$라고, 가정하고 $X \le C_1$를 취하면, p-value는 아래와 같다.

$$
2 \cdot P(X \le x \mid p = 1/3)
$$

만약, $\alpha$ 값이 p-value 보다 크다면, $H_0$를 기각한다!

<hr/>

#### Test on Two Proportions

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/test-on-proportion-1.png" | relative_url }}" width=650>
</div>

두 집합에서의 비율이 동일한지 검정하는 문제다. CLT를 적용하면 아래와 같다.

$$
\frac{(\hat{p}_1 - \hat{p}_2) - (p_1 - p_2)}{\sqrt{\dfrac{p_1 q_1}{n_1} + \dfrac{p_2 q_2}{n_2}}} \sim N(0, 1)
$$

그런데, 이때 우리는 population의 $p_1$, $p_2$의 값을 모른다. 그래서, 아래와 같이 pooled proportion $\hat{p}$을 대신 사용해야 한다. 이것은 우리가 검정하고자 하는 것이 두 집단의 proportion이 동일하다는 것을 검정하는 것이기 때문에 null hypothesis $H_0$의 가정을 충족하는 자연스러운 접근이다.

$$
\hat{p} = \frac{x_1 + x_2}{n_1 + n_2}
$$

식을 다시 쓰면,

$$
\frac{(\hat{p}_1 - \hat{p}_2) - (p_1 - p_2)}{\sqrt{\hat{p}\hat{q} \left(\dfrac{1}{n_1} + \dfrac{1}{n_2}\right)}} \sim N(0, 1)
$$

위의 공식을 통해 p-value를 구하고, p-value가 $\alpha$ 값보다 작다면, $H_0$를 기각한다!

💥 주목할 점은 이전의 [\<Proportion Estimation\>]({{"/2021/05/14/proportion-estimation-on-bernoulli.html" | relative_url}})의 "Two Samples Estimation: Diff Btw Two Proportions"과 공식이 약간 다르다는 점이다. 위의 Estimation에서는 두 샘플의 sample proportion을 그대로 사용했고, 이번의 경우에는 pooled sample proportion $\hat{p}$을 사용했다.

<hr/>

### Test on Variance

Variance에 대한 검정은 추정에서 다뤘던 내용에서 크게 달라지지 않는다. \<significance interval\>을 벗어난다면, $H_0$를 기각한다.

<div class="light-margin"></div>

1\. Test on Variance

ex: $H_0: \sigma^2 = \sigma_0^2$ vs. $H_1: \sigma^2 \ne \sigma_0^2$를

$S^2$를 Test Statistics로 잡고 $(n-1)S^2 / \sigma^2 \sim \chi^2 (n-1)$를 이용해서 \<chi-suqare distribution\>으로 검정 수행

<div class="light-margin"></div>

2\. Test on Two Variances

ex: $H_0: \sigma_1^2 = \sigma_2^2$ vs. $H_1: \sigma_1^2 \ne \sigma_2^2$

$S_1^2 / S_2^2$를 Test Statistics로 잡고 $\frac{S_1^2/\sigma_1^2}{S_2^2/\sigma_2^2} \sim F(n_1 - 1, n_2 - 2)$임을 이용해 \<F-test\>로 검정 수행

<div class="light-margin"></div>

✨ [variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})

<hr/>

이어지는 포스트에서는 \<**Chi-square Goodness-of-fit test**\>에 대해 살펴본다. \<chi-square distribution\> $\chi^2$를 사용해 검정을 수행하며, 이를 통해 표본의 독립(independence)와 동질성(homogeneity)에 대한 검정 할 수 있다!

👉 [Chi-square Goodness-of-fit test]({{"/2021/05/27/chi-square-goodness-of-fit-test.html"}})