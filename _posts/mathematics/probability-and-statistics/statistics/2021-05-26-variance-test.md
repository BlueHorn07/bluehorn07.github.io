---
title: "Variance Test"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

[\<Variance Estimation\>]({{"/2021/05/16/variance-estimation" | relative_url}})의 내용을 먼저 살펴보고 오는 것을 추천한다.

# Test on Variance

Variance에 대한 검정은 추정에서 다뤘던 내용에서 크게 달라지지 않는다. \<significance interval\>을 벗어난다면, $H_0$를 기각한다.

## Variance Test

ex: $H_0: \sigma^2 = \sigma_0^2$ vs. $H_1: \sigma^2 \ne \sigma_0^2$를

$S^2$를 Test Statistic로 잡고 $(n-1)S^2 / \sigma^2 \sim \chi^2 (n-1)$를 이용해서 \<chi-suqare distribution\>으로 검정 수행

## Ratio of Two Variance Test

ex: $H_0: \sigma_1^2 = \sigma_2^2$ vs. $H_1: \sigma_1^2 \ne \sigma_2^2$

$S_1^2 / S_2^2$를 Test Statistic로 잡고 $\frac{S_1^2/\sigma_1^2}{S_2^2/\sigma_2^2} \sim F(n_1 - 1, n_2 - 2)$임을 이용해 \<F-test\>로 검정 수행

<hr/>

# 맺음말

\<f-distribution\>이 등장하는 파트는 이번에 살펴본 \<Variance Test\>가 마지막이다! \<chi-square distribution\> $\chi^2(n)$은 이어지는 \<Chi-square Goodness-of-fit Test\> 포스트에서 또 등장한다.

\<chi-square Goodness-of-fit Test\>는 이전에 살펴본 [\<proportion test\>]({{"/2021/05/26/proportion-test" | relative_url}})의 일반화이다. 카테고리 변수의 확률에 대한 검정을 수행한다.

👉 [Chi-square Goodness-of-fit test]({{"/2021/05/27/chi-square-goodness-of-fit-test" | relative_url}})
