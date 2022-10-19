---
title: "Variance Test"
layout: post
tags: ["statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<br><span class="statement-title">TOC.</span><br>

- [Test on Variance](#test-on-variance)


<hr/>

# Test on Variance

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

✨ [Variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})

<hr/>

# 맺음말

이어지는 포스트에서는 \<**Chi-square Goodness-of-fit test**\>에 대해 살펴본다. \<chi-square distribution\> $\chi^2$를 사용해 검정을 수행하며, 이를 통해 표본의 독립(independence)와 동질성(homogeneity)에 대한 검정 할 수 있다!

👉 [Chi-square Goodness-of-fit test]({{"/2021/05/27/chi-square-goodness-of-fit-test.html" | relative_url}})
