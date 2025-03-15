---
title: "Bernoulli Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

몇몇 Distribution의 경우 현실을 모사하고 잘 설명하기 때문에 유용하게 사용된다. 이번 포스트에선 Discrete RV에서 볼 수 있는 유명한 Distributions을 살펴본다. 각 Distribution이 다른 분포에 대한 Motivation이 되기 때문에 그 의미를 곱씹고, 충분히 연습해야 한다.


# Bernoulli Distribution

\<Bernolli Distribution\>은 동전 던지기에 대한 Distribution이다. 좀더 일반화해서 말하면, Sample space에서 단 두개의 sample point를 가질 때, Bernoulli Distribution이라고 한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br>

(1) A \<**Bernoulli trial**\> is an experiment whose outcomes are only **<u>success</u>** or **<u>failure</u>**.

(2) A RV $X$ is said to have \<**Bernoulli Distributions**\> if its pmf is given by

$$
f(x) = p^x \cdot (1-p)^{1-x}
$$

We denote it as

$$
X \sim \text{Bernoulli}(p)
$$

</div>

여기서 주의할 점은 \<Bernoulli Trial\>은 **딱 한번만** 시행하는 것이다! Trial을 여러번 한다면, 뒤에 나올 \<Binomial Distribution\>이 된다.

<div class="notice" markdown="1">

<br><span class="statement-title">Theorem.</span><br>

If $X$ is a **Bernoulli RV**, then

- $\displaystyle E[X] = \sum x f(x) = 1 f(1) = p$
- $\displaystyle \text{Var}(X) = E[X^2] - (E[X])^2 = p - p^2 = p (1-p) = pq$

</div>

# 맺음말

이어지는 포스트에선 좀더 복잡한 형태의 이항 분포를 다룬다. 🤩

- [HyperGeometric Distribution](/2021/03/24/hypergeometric-distribution)
- [Geometric Distribution](/2021/03/24/geometric-distribution)
- [Poisson Distribution](/2021/03/25/poisson-distribution)
