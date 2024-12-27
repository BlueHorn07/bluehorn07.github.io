---
title: "Two Samples Estimation: Paired Observations"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

[Interval Estimation]({{"/2021/05/06/interval-estimation" | relative_url}}) 포스트에서 다룬 \<Interval Estimation\>을 특정 상황에 어떻게 적용할 수 있는지를 다루는 포스트입니다.

아래의 문제를 살펴보자!

<div class="img-wrapper">
<img src= "{{"/images/mathematics/probability-and-statistics/paired-observations-example-1.png" | relative_url }}" width=550>
</div>

우리는 학생1부터 학생30까지 그들의 TOEIC 점수의 before-after를 가지고 있다. 우리는 과연 MATH230 수업이 학생들의 TOEIC 수업에 어떤 영향을 미치는지 알기 위해 $\mu_1 - \mu_2$를 추정하고자 한다!!

<div class="light-margine" markdown="1">

Q. Can we find a 95% confidence interval for the true mean of the differences btw the scores before and after the MATH230?

Supp. $X_1, \dots, X_n$ and $Y_1, \dots, Y_n$ are random samples and $\sigma_1^2$ and $\sigma_2^2$ are known.

이전 포스트 "[Two Samples Estimation: Diff Btw Two Means]({{"/2021/05/13/two-samples-estimation-diff-btw-two-means" | relative_url}})"에서 만약 두 샘플의 분산을 정확히 안다면, 아래와 같이 구간을 추정할 수 있다고 하였다.

$$
\left| \bar{x} - \bar{y} \right| \le z_{\alpha/2} \cdot \sqrt{\frac{\sigma_1^2}{n} + \frac{\sigma_2^2}{n}}
$$

💥 하!지!만! 위의 방법은 올바른 접근이 아니다! 왜냐하면, <span class="half_HL">현재 우리가 가진 샘플 $X_i$, $Y_i$에 대해 그 둘이 서로 dependent 하기 때문이다!!</span> 위의 접근은 $X_i$와 $Y_i$가 independent 할 때만 가능하다!!

</div>

그래서 우리는 각 $X_i$, $Y_i$를 개별적으로 생각하는 것이 아니라 그들을 paring한 Difference $D_i = X_i - Y_i$로 접근하고자 한다!

이렇게 할 경우, 각 Pair는 서로 independent하게 된다!

Assume that $D_1, \dots, D_n$ are normal random samples: $D_i \sim N(\mu_D, \sigma_D^2)$

To find the confidence interval for $\mu_1 - \mu_2$, we use $\bar{D} := \bar{X} - \bar{Y}$.

Then, by CLT

$$
\frac{\bar{D} - \mu_D}{\sigma_D / \sqrt{n}} \; \sim \; N(0, 1)
$$

이때, 우리는 $\sigma_D^2$를 알지 못하므로 이것을 sample variance $s_D^2$으로 교체하면 분포는 아래와 같다.

$$
\frac{\bar{D} - \mu_D}{s_D / \sqrt{n}} \; \sim \; t(n-1)
$$

<hr/>

지금까지는 \<Normal Distribution\>에서 뽑은 random sample에서 추정(Estimation)을 진행했다. 다음 포스트에서는 \<Bernoulli Distribution\>에서 수행하는 추정인 \<Proportion Estimation\>에 대해 살펴본다!! <small>(Binomial Distribution에서의 평균은 Proportion이다!! 😁)</small>

👉 [Proportion Estimation on Bernoullid Distribution]({{"/2021/05/14/proportion-estimation-on-bernoulli" | relative_url}})