---
title: "Discrete Probability Distributions - 2"
layout: post
use_math: true
tags: ["Probability"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- [HyperGeometric Distribution]({{"/2021/03/24/discrete-probability-distributions-2.html#hypergeometric-distribution" | relative_url}})
  - Multivariate HyperGeometric Distribution
- [Geometric Distribution]({{"/2021/03/24/discrete-probability-distributions-2.html#geometric-distribution" | relative_url}})
  - Memoryless Property
  - Negative Binomial Distribution

<hr/>

이전 포스트에서 이산 분포의 기본이 되는 \<Bernoulli Distribution\>, \<Binomial Distribution\> 등등을 살펴봤다. 이번 포스트에서는 좀더 재미있는 분포들이 등장한다!

<hr/>

## HyperGeometric Distribution

\<HyperGeometric Distribution\>은 앞에서 살펴본 \<Binomial Distribution\>과 상황이 정말 비슷하다. 하지만, **Sampling 방식**에서 \<Binomial Distribution\>은 각 trial이 독립적이고, **with replacement**인 반면에 <span class="half_HL">\<HyperGeometric Distribution\>은 각 trial이 dependent하고 **w/o replacement**로 진행</span>된다!

**w/o replacement** 방식으로 샘플링하는 것의 예에는 \<acceptance sampling\>이 있다. 물품을 품질을 검수하는 이 작업선 테스팅 후에 물품이 파괴되거나 더이상 쓰지 못하게 될 수 있기 때문에 replacement를 할 수가 없다. 그렇기 때문에 **w/o replacement**를 바탕으로 하는 샘플링에 대한 논의는 꼭 필요하다.

<span class="statement-title">Definition.</span> HyperGeometric Distribution<br>

성공으로 표시된 $K$개의 샘플과 실패로 표시된 $N-K$개의 샘플이 있는 $N$개의 샘플에서, 무작위로 $n$개의 샘플을 **w/o replacement**로 뽑는다고 하자. 이것을 \<HyperGeometric Experiement\>라고 한다. 이때, RV $X$는 \<HyperGeometric Experiment\>에서 성공을 뽑은 횟수이다. 이 RV $X$를 \<HyperGeometric RV\>라고 한다.

\<HyperGeometric RV\> $X$의 pmf는 아래와 같이 정의된다.

$$
h(x; N, K, n) = \frac{\binom{K}{x} \binom{N-K}{n-x}}{\binom{N}{n}} \quad \text{where} \quad 0 \le x \le K \quad \text{and} \quad 0 \le n-x \le N-K
$$

위와 같은 pmf를 \<**HyperGeometric Distribution**\>라고 하며, $X \sim \text{HyperGeo}(N, K, n)$로 표기한다.

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

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{HyperGeo}(N, K, n)$, then

- $\displaystyle E[X] = n \frac{K}{N}$
- $\displaystyle \text{Var}(X) = n \frac{K}{N}\left( 1 - \frac{K}{N} \right) \cdot \frac{N-n}{N-1}$

지금 당장 \<HyperGeometric Distribution\>에 대한 평균과 분산에 대한 정리를 증명하지는 않을 것이다. 그러나 위의 식을 좀더 직관적으로 이해해보면, \<Binomial Distribution\>의 경우와 정말 유사함을 발견할 수 있다.

HyperGeo의 $\dfrac{K}{N}$를 Binomial의 $p$로 해석한다면, Binomail의 평균인 $np$와 HpyerGeom의 $n\dfrac{K}{N}$는 그 형태가 꽤 비슷하다. 분산의 경우에도 HyperGeo의 경우 $n \dfrac{K}{N}\left( 1 - \dfrac{K}{N} \right) \cdot \dfrac{N-n}{N-1}$로 Binomial의 경우처럼 $npq$의 형태가 보이지만, 마지막 부분에 $\dfrac{N-n}{N-1}$에 대한 텀이 붙는다.

<span class="statement-title">Theorem.</span><br>

특정 경우에서는 HyperGeo를 Binomial로 취급할 수도 있다.

If $N \gg n$ and $K \gg n$, then

$$
h(x; N, K, n) \approx \text{BIN}(x; n, \frac{K}{N})
$$

위의 정리와 마찬가지로 증명은 뒤에서 따로 제시하겠다.

<hr/>

### Multivariate HyperGeometric Distribution

\<Multivariate HyperGeometric Distribution\>은 HyperGeo에서 가능한 outcome이 2개에서 여러개로 늘어난 상황이다. Multivariate HyperGeo의 pmf는 아래와 같이 기술할 수 있다.

<span class="statement-title">Definition.</span> Mutlivariate HyperGeometric Distribution<br>

If $N$ items can be partitioned into the $k$ cells $A_1, A_2, \dots, A_k$ with $a_1, a_2, \dots, a_k$ elements, repectively, then the probability distribution of the RVs $X_1, X_2, \dots, X_k$, representing the number of elements selected from $A_1, A_2, \dots, A_k$ in a random sample of size $n$, is

$$
f(x_1, \dots, x_k\;  ; \; a_1, \dots, a_k, N, n) = \frac{\binom{a_1}{x_1} \cdots \binom{a_k}{x_k}}{\binom{N}{n}}
$$

with $\displaystyle \sum^k_{i=1} x_i = n$ and $\displaystyle \sum^k_{i=1} a_i = N$.

<hr/>

## Geometric Distribution

\<Geometric Distribution\>의 경우는 앞에서 제시된 Distribution들과 조금 상황이 다르다. 

<span class="statement-title">Definition.</span> Geometric Distribution<br>

$p$-coin을 독립적으로 tossing 하는 상황을 생각해보자. 이때, 우리는 <span class="half_HL">처음으로 Head가 나올 때까지 $p$-coin을 던질 것이다.</span> 이때, 첫 Head가 나오기까지 시도한 Tossing 횟수를 Random Variable $X$라고 하면, 이것의 pmf는 아래와 같다.

$$
g(x; p) = pq^{x-1}, \quad x = 1, 2, 3, \dots
$$

그리고 이때의 RV $X$를 \<Geometric RV\>라고 하며, $X \sim \text{Geo}(p)$로 표기한다.

여기서 왜 \<Geometric Distribution\>에 "Geometric"이라는 이름이 붙었는지 궁금증이 생긴다. 그 이유는 Geo에서 확률의 合이 1이 됨을 확인하면서 알 수 있다.

$$
\begin{aligned}
\sum_x g(x) &= \sum^{\infty}_x p \dot q^{x-1}\\
&= p \; (1 + q + q^2 + \cdots + q^n + \cdots ) \\
&= \lim_{n \rightarrow \infty} p \; \frac{1-q^n}{1-q} = \frac{p}{1-q} = \frac{p}{p} = 1
\end{aligned}
$$

위와 같이 확률 合이 1이 됨을 보이는 과정에서 "Geometric Series"가 등장하기 때문에 "Geometric" Distribution이라는 이름이 붙었다!!

<span class="statement-title">Property.</span> Memeryless property 🔥<br>

Geometric Distribution의 경우, 재미있는 성질을 가지고 있다. 바로 \<Memoryless Property\>라는 건데, 수식으로 기술하면 아래와 같다.

$$
P(X = x+k \mid x > k) = P(X = x)
$$

또는

$$
P(X > k) = q^{k}
$$

두번째 식을 잘 사용해보면, 첫번째 식을 쉽게 유도할 수 있다 😊

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{Geo}(p)$, then

- $\displaystyle E[X] = \frac{1}{p}$
- $\displaystyle \text{Var}(X) = \frac{1-p}{p^2}$

위의 식에 대한 증명은 간단하다. 지금 유도해보자.

<span class="statement-title">*Proof*.</span><br>

<details class="math-statement" markdown="1">
<summary>펼쳐보기</summary>

1\. $E[X]$

$$
\begin{aligned}
  E[X] &= \sum k f(k) = p \sum^{\infty}_{k=1} k q^{k-1} \\
  &= p \; (1 + 2q + 3q^2 + \cdots ) \\
\end{aligned}
$$

(1) 멱급수로 유도

$$
\begin{aligned}
S &= (1 + 2q + 3q^2 + \cdots ) \\
qS &= (0 + q + 2q^2 + \cdots) \\
(1-q)S &= 1 \\
(1-q)S &= \frac{1}{1-q} \\
S &= \frac{1}{(1-q)^2} \\
\end{aligned}
$$

(2) 미분으로 유도

$$
\begin{aligned}
S &= (1 + 2q + 3q^2 + \cdots ) \\
&= (1 + q + q^2 + \cdots) ' \\
&= \left( \frac{1}{1-q} \right)' \\
&= \frac{1}{(1-q)^2}
\end{aligned}
$$

따라서, $\displaystyle E[X] = p S = p \frac{1}{(1-q)^2} = \frac{p}{p^2} = \frac{1}{p}$

<hr/>

2\. $\text{Var}(X)$

$\text{Var}(X)$를 구하기 위해 $E[X^2]$를 구해야 한다. 이때, 계산의 편의를 위해 $E[X^2]$ 대신 $E[X(X-1)]$를 구하는 테크닉을 사용하자.

$$
\begin{aligned}
E[X(X-1)] &= p \sum k(k-1)q^{k-1} \\
&= pq \sum^{\infty}_{i=2} k(k-1) q^{k-2} \\
&= pq \left( \frac{1}{(1-q)^2} \right)' \\
&= pq \left( \frac{2}{(1-q)^3}\right) \\
&= pq \frac{2}{p^3} = \frac{2q}{p^2}
\end{aligned}
$$

이제 위의 결과를 이용해서 잘 정리하면,

$$
\begin{aligned}
\text{Var}(X) &= E[X(X-1)] + E[X] - \left(E[X]\right)^2 \\
&= \frac{2q}{p^2} + \frac{1}{p} - \frac{1}{p^2} \\
&= \frac{1-p}{p^2}
\end{aligned}
$$

</details>

<hr/>

### Negative Binomial Distribution

이번에는 Geo와 비슷한 상황이지만, <span class="half_HL">$k$개의 Head가 나올 때까지 동전을 던진다.</span> 이때 Tossing 횟수를 Random Variable $X$라고 하면, 이것은 \<Negative Binomial Distribution\>을 따른다.

<span class="statement-title">Definition.</span> Negative Binomial Distribution<br>

$p$-coin을 독립적으로 tossing 한다고 해보자. 이때 $k$개 Head가 나올 때까지 동전을 던진 횟수를 RV $X$로 잡자. 그러면 이것의 pmf는 아래와 같다.

$$
b^{*}(x; k,p) =\binom{x-1}{k-1} p^k q^{x-k} \quad \text{for} \quad x = k, k+1, \dots
$$

이것의 유도는 $(x-1)$ 시도까지 $(k-1)$번 만큼의 Head가 나와야 한다고 생각하면, $(x-1)$번의 시도는 \<Binomial Distribution\>을 따르게 된다. 

$$
\binom{x-1}{k-1} p^{k-1} q^{x-k}
$$

마지막에는 반드시 Head가 나와야 하므로 위의 Binomial에 $p$를 곱해주면, Negative Binomial을 얻게 된다!

Negative Binomial은 서로 독립인 $n$개의 Geometric RV라고 생각해볼 수도 있다. 그래서 NegBIN $Y$는 Geo $X_i$에 대해

$$
Y = X_1 + \cdots X_n
$$

인 셈이다.

그런데 왜 "Negative" Binomial이라는 이름이 붙었을까? 그것은 Geometric Distribution 때와 마찬가지로 확률의 合이 1이 됨을 보이는 과정에서 유래한다.

$$
\begin{aligned}
\sum f(x) &= \sum^{\infty}_{x=k} \binom{x-1}{k-1} p^k q^{x-k} \\
&= p^k \sum^{\infty}_{x=k} \binom{x-1}{k-1}  q^{x-k} \\
&= p^k \left( \sum^{\infty}_{y=0} \binom{y+k-1}{k-1} q^y \right) \quad \text{by} \; y = x-k \\
&= p^k \cdot (1-q)^{-k} \quad \text{by general binomial theorem} \\
&= p^k \cdot p^{-k} = 1
\end{aligned}
$$

즉, 유도 과정에서 Negative Binomial이 등장하기 때문에 지금의 Negative Binomial이라는 이름이 붙었다고 한다.

<span class="statement-title">Theorem.</span><br>

If $X \sim \text{Neg BIN}(k, p)$, then

- $\displaystyle E[X] = \frac{1}{p}k$
- $\displaystyle \text{Var}(X) = \left(\frac{1-p}{p^2}\right) k$

위의 결과를 잘 살펴보면, Geometric Distribution과 연관성을 찾을 수 있다. Geo에서는 평균이 $E[X] = \dfrac{1}{p}$였는데, NegBIN를 $k$개의 Geo가 모인 것으로 해석한다면, Geo의 평균 $\dfrac{1}{p}$가 $k$개 모인 셈이니 $\dfrac{1}{p}k$가 된다. 마찬가지로 분산에 대해서도 동일한 시각으로 접근해볼 수 있다. 😎

<hr/>

이어지는 포스트에서는 \<Poisson Distribution\>라는 이산 확률 분포의 보스가 등장한다!! Poisson은 상당히 중요하니 눈여겨 살펴보도록 하자!

👉 [Poisson Distribution]({{"/2021/03/25/poisson-distribution.html" | relative_url}})


