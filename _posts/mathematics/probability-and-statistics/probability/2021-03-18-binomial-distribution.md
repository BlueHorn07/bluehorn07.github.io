---
title: "Binomial Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

몇몇 Distribution의 경우 현실을 모사하고 잘 설명하기 때문에 유용하게 사용된다. 이번 포스트에선 Discrete RV에서 볼 수 있는 유명한 Distributions을 살펴본다. 각 Distribution이 다른 분포에 대한 Motivation이 되기 때문에 그 의미를 곱씹고, 충분히 연습해야 한다.

# Binomial Distribution

\<Bernoulli Trial\>은 동전을 딱 한번 던지는 시행이었다. 만약 동전을 $n$번 만큼 여러번 던진다면, 몇번 성공(success) 했는지 세어 볼 수 있다. 만약 성공의 횟수를 RV $X$로 둔다면, \<**Binomial Distribution**\>라는 새로운 분포를 얻게 된다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br>

When a RV $X$ has a pmf

$$
f(x) = b(x;n, p) = \binom{n}{x} p^x q^{n-x}
$$

We call $X$ as a \<binomial random variable\> and denote it as

$$
X \sim \text{Binomial}(n, p) \quad \text{or} \quad X \sim \text{BIN}(n, p)
$$

</div>

확인할 점은 \<Binomial Distribution\>의 pmf $f(x)$가 정말로 pmf인지이다. 이것을 확인하려면 pmf $f(x)$의 합이 1이 됨을 보이면 된다. 이것은 \<**이항 정리 Binomial Theorem**\>을 통해 쉽게 보일 수 있다. 이 분포가 \<**Binomial**\>라는 이름인 이유가 이것 때문이다.

$$
\sum_x f(x) = \sum^n_{k=0} \binom{n}{k} p^k (1-p)^{n-k} = \left(p + (1-p)\right)^n
$$

이번에는 \<Binomial Distribution\>에서의 평균과 분산을 살펴보자.

- $\displaystyle E[X] = np$
- $\displaystyle \text{Var}(X) = npq$

먼저 평균 $E[x]$가 $np$가 되는 이유를 수학적 증명 없이 설명해보자. RV $X$는 전체 성공의 횟수를 의미한다. 이것은 곧 개별 시행 $X_i$에 대해 아래가 성립함을 말한다.

$$
X = X_1 + X_2 + \cdots + X_n
$$

이때, 개별 시행 $X_i$가 Bernoulli Distribution을 따르고, 서로가 독립으므로 \<expectation\>의 Linearity에 의해

$$
\begin{aligned}
E[X]
&= E[X_1 + \cdots + X_n] \\
&= E[X_1] + \cdots + E[X_n] \\
&= p + \cdots + p \\
&= n \cdot p
\end{aligned}
$$

좀더 엄밀하게 증명하면 아래와 같다.

<div class="proof" markdown="1">

$$
\begin{aligned}
E[X] &= \sum k f(k) = \sum k \binom{n}{k} p^k q^{n-k} \\
    &= \sum^{n}_{k=0} k \frac{n!}{k! (n-k)!} p^k q^{n-k} \\
    &= \sum^{n}_{k=1} k \frac{n!}{k! (n-k)!} p^k q^{n-k} \\
    &= \sum^{n}_{k=1} \frac{n!}{(k-1)! (n-k)!} p^k q^{n-k} \\
    &= n \cdot \sum^{n}_{k=1} \frac{(n-1)!}{(k-1)! (n-k)!} p^k q^{n-k} \\
    &= np \cdot \sum^{n}_{k=1} \frac{(n-1)!}{(k-1)! (n-k)!} p^{k-1} q^{n-k} \\
    &= np \cdot \sum^{n-1}_{k=0} \frac{(n-1)!}{k! ((n-1)-k)!} p^{k} q^{(n-1)-k} \\
    &= np \cdot (p + (1-p))^{n-1} = np
\end{aligned}
$$

$\blacksquare$

</div>

분산 $\text{Var}(X)$을 증명하는 건 조금 쉽지 않다. 증명은 Exercise로 남기지만, 반드시 직접 증명해봐야 하는 명제다 🎈

# 맺음말

이어지는 포스트에선 좀더 복잡한 형태의 이항 분포를 다룬다. 🤩

- [HyperGeometric Distribution](/2021/03/24/hypergeometric-distribution)
- [Geometric Distribution](/2021/03/24/geometric-distribution)
- [Poisson Distribution](/2021/03/25/poisson-distribution)
