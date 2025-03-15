---
title: "HyperGeometric Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

이전 포스트에서 이산 분포의 기본이 되는 \<Bernoulli Distribution\>, \<Binomial Distribution\> 등등을 살펴봤다. 이번 포스트에서는 좀더 재미있는 분포들이 등장한다!

# HyperGeometric Distribution

\<HyperGeometric Distribution\>은 앞에서 살펴본 \<Binomial Distribution\>과 상황이 정말 비슷하다. 하지만, **Sampling 방식**에서 차이가 있다.

- \<Binomial Distribution\>은 각 trial이 독립적이고, **with replacement** 였다.
- 반면에 \<HyperGeometric Distribution\>은 각 trial이 dependent하고 **w/o replacement**로 진행된다!

**w/o replacement** 방식으로 샘플링하는 것의 예에는 \<Acceptance Sampling\>이 있다. 물품을 품질을 검수하는 이 작업선 테스팅 후에 물품이 파괴되거나 더이상 쓰지 못하게 되기 때문에 교체를 할 수가 없다. 그렇기 때문에 **w/o replacement**를 바탕으로 하는 샘플링에 대한 논의는 꼭 필요하다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> HyperGeometric Distribution<br>

성공으로 표시된 $K$개의 샘플과 실패로 표시된 $N-K$개의 샘플이 있는 $N$개의 샘플에서, 무작위로 $n$개의 샘플을 **w/o replacement**로 뽑는다고 하자. 이것을 \<HyperGeometric Experiment\>라고 한다. 이때, RV $X$는 \<HyperGeometric Experiment\>에서 성공을 뽑은 횟수이다. 이 RV $X$를 \<HyperGeometric RV\>라고 한다.

\<HyperGeometric RV\> $X$의 pmf는 아래와 같이 정의된다.

$$
h(x; N, K, n) = \frac{\binom{K}{x} \binom{N-K}{n-x}}{\binom{N}{n}} \quad \text{where} \quad 0 \le x \le K \quad \text{and} \quad 0 \le n-x \le N-K
$$

위와 같은 pmf를 \<**HyperGeometric Distribution**\>라고 하며, $X \sim \text{HyperGeo}(N, K, n)$로 표기한다.

</div>

이때, \<HyperGeometric Distribution\>에 대한 조건식을 다듬으면 아래와 같다.

$$
\begin{aligned}
\quad 0 \le x \le K \quad &\text{and} \quad 0 \le n-x \le N-K \\
\quad 0 \le x \le K \quad &\text{and} \quad -n \le -x \le N-K-n \\
\quad 0 \le x \le K \quad &\text{and} \quad K+n - N \le x \le n \\
\end{aligned}
$$

$$
\therefore \max \{ 0, n-(N-K) \} \le x \le \min \{ K, n \}
$$

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{HyperGeo}(N, K, n)$, then

- $\displaystyle E[X] = n \frac{K}{N}$
- $\displaystyle \text{Var}(X) = n \frac{K}{N}\left( 1 - \frac{K}{N} \right) \cdot \frac{N-n}{N-1}$

</div>

지금 당장 \<HyperGeometric Distribution\>에 대한 평균과 분산에 대한 정리를 증명하지는 않을 것이다. 그러나 위의 식을 좀더 직관적으로 이해해보면, \<Binomial Distribution\>의 경우와 정말 유사함을 발견할 수 있다.

HyperGeo의 $\dfrac{K}{N}$를 Binomial의 $p$로 해석한다면, Binomial의 평균인 $np$와 HpyerGeom의 $n\dfrac{K}{N}$는 그 형태가 꽤 비슷하다. 분산의 경우에도 HyperGeo의 경우 $n \dfrac{K}{N}\left( 1 - \dfrac{K}{N} \right) \cdot \dfrac{N-n}{N-1}$로 Binomial의 경우처럼 $npq$의 형태가 보이지만, 마지막 부분에 $\dfrac{N-n}{N-1}$에 대한 텀이 붙는다.

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

특정 경우에서는 HyperGeo를 Binomial로 취급할 수도 있다.

If $N \gg n$ and $K \gg n$, then

$$
h(x; N, K, n) \approx \text{BIN}(x; n, \frac{K}{N})
$$

</div>

위의 정리와 마찬가지로 증명은 뒤에서 따로 제시하겠다.



## Multivariate HyperGeometric Distribution

"**다변량 초기하 분포(Multivariate HyperGeometric Distribution)**"는 초기하 분포에서 가능한 Outcome이 2개에서 여러 개로 늘어난 상황이다. pmf는 아래와 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Mutlivariate HyperGeometric Distribution<br>

If $N$ items can be partitioned into the $k$ cells $A_1, A_2, \dots, A_k$ with $a_1, a_2, \dots, a_k$ elements, respectively, then the probability distribution of the RVs $X_1, X_2, \dots, X_k$, representing the number of elements selected from $A_1, A_2, \dots, A_k$ in a random sample of size $n$, is

$$
f(x_1, \dots, x_k\;  ; \; a_1, \dots, a_k, N, n) = \frac{\binom{a_1}{x_1} \cdots \binom{a_k}{x_k}}{\binom{N}{n}}
$$

with $\displaystyle \sum^k_{i=1} x_i = n$ and $\displaystyle \sum^k_{i=1} a_i = N$.

</div>

pmf 함수가 많이 복잡하기는 한데, 초기하 분포를 잘 이해하고 있다면, 다변량으로 확장하는 것도 어렵지 않게 할 수 있다.


# 맺음말

이어지는 포스트에서는 \<Poisson Distribution\>라는 이산 확률 분포의 보스가 등장한다!! Poisson은 상당히 중요하니 눈여겨 살펴보도록 하자!

👉 [Poisson Distribution](/2021/03/25/poisson-distribution)
