---
title: "Geometric Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

이전 포스트에서 이산 분포의 기본이 되는 \<Bernoulli Distribution\>, \<Binomial Distribution\> 등등을 살펴봤다. 이번 포스트에서는 좀더 재미있는 분포들이 등장한다!

# Geometric Distribution

\<Geometric Distribution\>의 경우는 앞에서 제시된 Distribution들과 조금 상황이 다르다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Geometric Distribution<br>

$p$-coin을 독립적으로 tossing 하는 상황을 생각해보자. 이때, 우리는 <span class="half_HL">처음으로 Head가 나올 때까지 $p$-coin을 던질 것이다.</span> 이때, 첫 Head가 나오기까지 시도한 Tossing 횟수를 Random Variable $X$라고 하면, 이것의 pmf는 아래와 같다.

$$
g(x; p) = pq^{x-1}, \quad x = 1, 2, 3, \dots
$$

이 RV $X$를 \<Geometric RV\>라고 하며, $X \sim \text{Geo}(p)$로 표기한다.

</div>

여기서 왜 \<Geometric Distribution\>에 "Geometric"이라는 이름이 붙었는지 궁금증이 생긴다. 그 이유는 확률의 合이 1이 되는지 확인해보면 알 수 있다.

$$
\begin{aligned}
\sum_x g(x) &= \sum^{\infty}_x p \dot q^{x-1}\\
&= p \; (1 + q + q^2 + \cdots + q^n + \cdots ) \\
&= \lim_{n \rightarrow \infty} p \; \frac{1-q^n}{1-q} = \frac{p}{1-q} = \frac{p}{p} = 1
\end{aligned}
$$

위와 같이 확률 合이 1이 됨을 보이는 과정에서 "Geometric Series"가 등장하기 때문에 "Geometric" Distribution이라는 이름이 붙었다!!

<div class="notice" markdown="1">

<span class="statement-title">Property.</span> Memeryless property 🔥<br>

\<Geometric Distribution\>은 "Memoryless Property"라는 재미있는 성질을 가지고 있다.

예를 들어, 로또를 1년 전부터 사기 시작한 사람과, 로또를 오늘부터 사기 시작한 사람의 당첨 확률은 같다!
이것은 1년 전부터 로또를 사기 시작했고, 그것들이 모두 낙첨이었다는 사실이 로또에 언제 처음 당첨될지와 아무 관련이 없기 때문이다.

수식으로 기술하면 아래와 같다.

$$
P(X = x+k \mid x > k) = P(X = x)
$$

즉, 내가 이전에 $k$번 시도 했다는 사실이 현재 확률에 아무런 영향을 끼치지 않는다.

Geometric Distribution 기준으로 작성하면 아래와 같다.

$$
P(X > k) = q^{k}
$$

</div>

두번째 식을 잘 사용해보면, 첫번째 식을 쉽게 유도할 수 있다 😊

<br/>

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{Geo}(p)$, then

- $\displaystyle E[X] = \frac{1}{p}$
- $\displaystyle \text{Var}(X) = \frac{1-p}{p^2}$

</div>

위의 식에 대한 증명은 간단하다. 지금 유도해보자.

<details class="proof" markdown="1">
<summary>펼쳐보기</summary>

<span class="statement-title">*Proof*.</span><br>

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



## Negative Binomial Distribution

이번에는 \<Geometric Distribution\>과 비슷하지만, <span class="half_HL">$k$개의 Head가 나올 때까지 동전을 던진다.</span> 이때 Tossing 횟수를 Random Variable $X$라고 하면, 이것은 \<Negative Binomial Distribution\>을 따른다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Negative Binomial Distribution<br>

$p$-coin을 independently tossing 한다고 해보자. 이때 $k$개 Head가 나올 때까지 동전을 던진 횟수를 RV $X$로 잡자. 그러면 이것의 pmf는 아래와 같다.

$$
b^{*}(x; k,p) =\binom{x-1}{k-1} p^k q^{x-k} \quad \text{for} \quad x = k, k+1, \dots
$$

</div>

이것의 유도는 $(x-1)$ 시도까지 $(k-1)$번 만큼의 Head가 나와야 한다고 생각하면, \<Binomial Distribution\>에서 $(x-1)$ 시도, $(k-1)$만큼 성공한 것과 같다.

$$
\binom{x-1}{k-1} p^{k-1} q^{x-k}
$$

마지막에는 반드시 Head가 나와야 하므로 위의 식에 $p$를 곱해주면, \<Negative Binomial Distribution\>을 얻게 된다!

Negative Binomial은 서로 독립인 $n$개의 Geometric RV라고 생각해볼 수도 있다. 그래서 NegBIN $Y$는 Geo $X_i$에 대해

$$
Y = X_1 + \cdots X_n
$$

인 셈이다.

그런데 왜 "Negative" Binomial이라는 이름이 붙었을까? 그것은 \<Geometric Distribution\> 때와 마찬가지로 확률의 合이 1이 됨을 보이는 과정에서 유래한다.

<div class="notice" markdown="1">


$$
\begin{aligned}
\sum f(x) &= \sum^{\infty}_{x=k} \binom{x-1}{k-1} p^k q^{x-k} \\
&= p^k \sum^{\infty}_{x=k} \binom{x-1}{k-1}  q^{x-k} \\
\end{aligned}
$$

여기에서 $y = x - k$로 치환하자. 이때, $y$는 $k$번째 성공을 얻기 위해 걸린 실패 횟수 $Y$이다. 표기의 편의를 위해 지금부터는 멱급수 부분만 표현하겠다.

$$
\sum^{\infty}_{x=k} \binom{x-1}{k-1} q^{x-k}
= \sum^{\infty}_{y=0} \binom{y + k - 1}{k-1} q^{y}
$$

이때, 조합(combination)의 성질에 의해 아래가 성립한다.

$$
\binom{y + k - 1}{k-1} = \binom{y + k - 1}{y}
$$

따라서,

$$
\sum^{\infty}_{y=0} \binom{x-1}{k-1} q^{x-k}
= \sum^{\infty}_{y=0} \binom{k + y - 1}{y} q^{y}
$$

여기에 [\<Negative Binomial Theorem\>]({{"/2022/10/30/negative-binomial-theorem" | relative_url}})을 적용해보자.

$$
(1 + x)^{-n} = \sum^{\infty}_{k = 0} \binom{-n}{k} x^k = \sum^{\infty}_{k = 0} \binom{n + k - 1}{k} (-1)^k x^k
$$

위의 정리에서 $x$에 $-q$를 대입하면,

$$
\sum^{\infty}_{y=0} \binom{k + y - 1}{y} q^{y}
= (1 - q)^{-k}
$$

식을 정리하면,

$$
\begin{aligned}
\sum f(x) &= \sum^{\infty}_{x=k} \binom{x-1}{k-1} p^k q^{x-k} \\
&= p^k \sum^{\infty}_{x=k} \binom{x-1}{k-1}  q^{x-k} \\
& p^k \sum^{\infty}_{y=0} \binom{k + y - 1}{y} q^{y} \\
&= p^k \cdot (1 - q)^{-k} \\
&= p^k \cdot p^{-k} \\
&= 1
\end{aligned}
$$

$\blacksquare$

</div>

즉, 유도 과정에서 Negative Binomial이 등장하기 때문에 지금의 Negative Binomial이라는 이름이 붙었다.

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

If $X \sim \text{Neg BIN}(k, p)$, then

- $\displaystyle E[X] = \frac{1}{p}k$
- $\displaystyle \text{Var}(X) = \left(\frac{1-p}{p^2}\right) k$

</div>

위의 결과를 잘 살펴보면, Geometric Distribution과 연관성을 찾을 수 있다. Geo에서는 평균이 $E[X] = \dfrac{1}{p}$였는데, NegBIN를 $k$개의 Geo가 모인 것으로 해석한다면, Geo의 평균 $\dfrac{1}{p}$가 $k$개 모인 셈이니 $\dfrac{1}{p}k$가 된다. 마찬가지로 분산에 대해서도 동일한 시각으로 접근해볼 수 있다. 😎

# 맺음말

이어지는 포스트에서는 \<Poisson Distribution\>라는 이산 확률 분포의 보스가 등장한다!! Poisson은 상당히 중요하니 눈여겨 살펴보도록 하자!

👉 [Poisson Distribution](/2021/03/25/poisson-distribution)
