---
title: "Multinomial Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

몇몇 Distribution의 경우 현실을 모사하고 잘 설명하기 때문에 유용하게 사용된다. 이번 포스트에선 Discrete RV에서 볼 수 있는 유명한 Distributions을 살펴본다. 각 Distribution이 다른 분포에 대한 Motivation이 되기 때문에 그 의미를 곱씹고, 충분히 연습해야 한다.

# Multinomial Distribution

지금까지 모두 동전 던지기에서 변주된 Distribution들을 살펴봤다. 그러나 현실에선 앞/뒤 두 결과만 있지 않듯이 \<Outcome\>이 여러 개인 경우의 분포도 생각해볼 수 있다! 6면의 주사위 던지기가 그런 경우다! 우리는 이것을 \<**Multinomial Distribution**\>라고 한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br>

The \<**multinomial experiment**\> consists of independent repeated $n$ trials and each trial results in $k$ possible outcomes $E_1, \dots, E_k$.

- $P(E_i) = p_i$ and $\displaystyle \sum^k_{i=1} p_i = 1$

Let $X_i$ be the number of $E_i$'s in $n$ trials, then

$$
P(X_1=x_1, \cdots, X_k = x_k)
= \frac{n!}{x_1! x_2! \cdots x_k!} \cdot p_1^{x_1}  p_2^{x_2} \cdots p_k^{x_k} \quad \text{where} \quad x_1 + \cdots + x_k = n
$$

</div>

\<Multinomail distribution\>의 pmf $f(x_1, \dots, x_k)$는 일종의 joint pmf로 해석할 수 있다. 그래서 \<Multinomail distribution\>에 대해 아래의 margnial distribution들을 생각해볼 수 있다.

- $X_k \sim \text{BIN}(n, p_k)$
- $X_i + X_j \sim \text{BIN}(n, p_i + p_j)$

# 맺음말

이어지는 포스트에선 좀더 복잡한 형태의 이항 분포를 다룬다. 🤩

- [HyperGeometric Distribution](/2021/03/24/hypergeometric-distribution)
- [Geometric Distribution](/2021/03/24/geometric-distribution)
- [Poisson Distribution](/2021/03/25/poisson-distribution)
