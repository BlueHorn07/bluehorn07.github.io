---
title: "Proportion Test"
toc: true
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<br><span class="statement-title">TOC.</span><br>

- [Test on Proportion](#test-on-proportion)
  - [Test on One Proportion](#test-on-one-proportion)
  - [Test on Two Proportions](#test-on-two-proportions)

<hr/>

# Test on Proportion

Single Sample에서의 경우와 Two Sample에서의 경우를 모두 다룬다.

<hr/>

## Test on One Proportion

Consider a p-coin, and $p$ is unknown.

We want to test

- $H_0: p=1/3$
- $H_1: p>1/3$

We toss a coin $n$ times independently, and let $x$ be the #. of heads in theses $n$ trials.

Q1. What is the p-value?

A1. $H_1$이 $p > 1/3$ 형태이므로 $x$가 특정값 $C$ 이상일 때, $H_0$를 reject 한다.

$$
P( X \ge C \mid p = 1/3)
$$

그래서 p-value는 $C$ 자리에 $x$를 대입해 p-value를 유도하면 된다!

$$
P(X \ge x \mid p = 1/3) = \text{p-value}
$$

Q2. 만약 $H_1: p < 1/3$ 형태라면?

A2. 위의 p-value 식에서 부호만 반대로 적어주면 된다.

$$
P(X \le x \mid p = 1/3)
$$

Q3. 만약 $H_1: p \ne 1/3$의 형태라면? (two-sided test)

A3. $X \le C_1$이거나 $X \ge C_2$일 때, $H_0$를 기각할 것이다. 따라서

$$
P(X \le C_1 \;\; \text{or} \;\; X \ge C_2 \mid p = 1/3)
$$

그런데 실험에서 하나의 $x$ 값만을 얻었고, 위의 과정에 따르면, 이 값을 $C$에 대입했다. 이것을 위 식에 적용하면,

$$
P(X \le x \;\; \text{or} \;\; X \ge x \mid p = 1/3) = 1
$$

가 되는데, 이 값은 1이다! 😲 보통 하나의 $x$ 값만 있기 때문에, one-side test를 수행하는게 합리적이다.

$X \le C_1$과 $X \ge C_2$ 중 어떤 방향을 취할지 결정하기 위해, expected value $E[X]$를 기준으로 삼자. 이 값은 $np$이다.

- If $x < np$, take $X \le C_1$
- If $x > np$, take $X \ge C_2$

만약 $x < np$라고, 가정하고 $X \le C_1$로 p-value를 구해야 한다. p-value는 아래와 같다. 양측 검정이기 때문에 $2$를 곱해준다.

$$
2 \cdot P(X \le x \mid p = 1/3)
$$

만약, $\alpha$ 값이 p-value 보다 크다면, $H_0$를 기각한다!

<hr/>

## Test on Two Proportions

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/test-on-proportion-1.png" | relative_url }}" width=650>
</div>

<span class="red">두 집합의 비율이 동일한지, 즉 $p_1 = p_2$를 검정</span>하는 문제다. [\<Proportion Estimation\>]({{"/2021/05/14/proportion-estimation-on-bernoulli" | relative_url}})에서 한 것처럼 CLT를 적용해 Test Statistic을 구하면 아래와 같다.

$$
\frac{(\hat{p}_1 - \hat{p}_2) - (p_1 - p_2)}{\sqrt{\dfrac{p_1 q_1}{n_1} + \dfrac{p_2 q_2}{n_2}}} \sim N(0, 1)
$$

만약 "두 집단의 proportion이 동일하다"는 가정이 참이라면, $p = p_1 = p_2$이므로 식을 다시 쓰면,

$$
\frac{(\hat{p}_1 - \hat{p}_2)}{\sqrt{pq (1/n_1 + 1/n_2)}}
$$

그런데, 우리는 population proportion이 $p_1 = p_2$ 라는 것만 알지 $p_1$, $p_2$의 값을 모른다. 그래서, [\<Proportion Estimation\>]({{"/2021/05/14/proportion-estimation-on-bernoulli" | relative_url}})에서 한 것처럼 sample proportion $\hat{p}$을 사용해야 한다!

그런데 sample proportion $\hat{p}_1$과 $\hat{p}_2$ 둘 중 뭘 써야할까? 둘을 종합한 pooled proportion $\hat{p}$을 사용하면 된다!

$$
\hat{p} = \frac{x_1 + x_2}{n_1 + n_2}
$$

식을 다시 쓰면,

$$
\frac{(\hat{p}_1 - \hat{p}_2)}{\sqrt{\hat{p}\hat{q} \left(1/n_1 + 1/n_2\right)}}
$$

위의 공식을 통해 p-value를 구하고, p-value가 $\alpha$ 값보다 작다면, $H_0$를 기각한다!

<hr/>

# 맺음말

이어지는 포스트에서 \<proportion test\>을 일반화한 \<**Chi-square Goodness-of-fit test**\>를 살펴본다. \<chi-square distribution\> $\chi^2$를 사용해 검정을 수행하며, 이를 통해 표본의 독립(independence)와 동질성(homogeneity)에 대한 검정을 할 수 있다!

👉 [Chi-square Goodness-of-fit test]({{"/2021/05/27/chi-square-goodness-of-fit-test"}})