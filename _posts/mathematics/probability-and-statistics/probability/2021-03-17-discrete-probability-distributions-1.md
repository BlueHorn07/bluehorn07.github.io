---
title: "Discrete Probability Distributions - 1"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

몇몇 Distribution의 경우 현실을 모사하고 잘 설명하기 때문에 유용하게 사용된다. 이번 포스트에선 Discrete RV에서 볼 수 있는 유명한 Distriubtion을 살펴본다. 각 Distribution이 다른 분포에 대한 Motivation이 되고, 각각이 모두 중요성을 갖기 때문에 그 의미를 곱씹고, 충분히 연습해야 한다.

<hr/>

# Discrete Uniform Distriubtion

Discrete RV $X$에 대해 각 sample point $x$의 pmf $f(x)$의 값이 모두 동일한 경우를 대표한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br>

Let $X$ takes values $x_1, \dots, x_N$. We say that $X$ has a \<**discrete uniform disctribution**\> if

$$
f(x) = P(X=x_i) = \frac{1}{N}
$$

</div>

\<uniform distribution\>의 경우, 평균과 분산은 아래의 값을 갖는다.

- $E[X]= \dfrac{\sum x_i}{N}$
- $\text{Var}(X) = \dfrac{\sum x_i^2}{N} - \dfrac{(\sum x_i)^2}{N^2}$ <small>// 그냥 (제평-평제) 공식을 사용했다.</small>

<hr/>

# Bernoulli Distribution

\<Bernolli Distribution\>은 동전 던지기에 대한 Distribution이다. 좀더 일반화해서 말하면, Sample space에서 단 두개의 sample point를 가질 때, Bernoulli Distribution이라고 한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br>

(1) A \<**Bernoulli trial**\> is an experiment whose outcomes are only **<u>success</u>** or **<u>failure</u>**.

(2) A RV $X$ is said to have \<**Bernoulli Distriubtion**\> if its pmf is given by

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

<hr/>

# Binomial Distribution

\<Bernoulli Trial\>은 동전을 딱 한번 던지는 시행이었다. 만약 동전을 $n$번 만큼 여러번 던진다면, 몇번 성공(success) 했는지 세어 볼 수 있다. 만약 성공의 횟수를 RV $X$로 둔다면, 우리는 \<**Binomial Distribution**\>라는 새로운 분포를 얻게 된다.

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
E[X] = E[X_1 + \cdots + X_n] = E[X_1] + \cdots + E[X_n]
$$

좀더 엄밀하게 증명하면 아래와 같다.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
E[X] &= \sum k f(k) = \sum k \binom{n}{k} p^k q^{n-k} \\
    &= \sum k \frac{n!}{k! (n-k)!} p^k q^{n-k} \\
    &= \sum \frac{n!}{(k-1)! (n-k)!} p^k q^{n-k} \\
    &= \sum n \frac{(n-1)!}{(k-1)! (n-k)!} p^k q^{n-k} \\
    &= np \sum^{n-1}_{k=0} \frac{(n-1)!}{(k-1)! (n-k)!} p^{k-1} q^{n-k} \\
    &= np \cdot (p + (1-p))^{n-1} = np
\end{aligned}
$$

$\blacksquare$

</div>

분산 $\text{Var}(X)$을 증명하는 건 조금 쉽지 않다. 증명은 Exercise로 남기지만, 반드시 직접 증명해봐야 하는 명제다 🎈

<hr/>

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

<hr/>

이어지는 포스트에선 좀더 복잡한 형태의 이항 분포를 다룬다. 🤩

- Hypergeometric Distribution
- Geometric Distribution
- Negative Binomial Distribution
- Poisson Random Variable

👉 [Discrete Probability Distribution - 2]({{"/2021/03/24/discrete-probability-distributions-2" | relative_url}})