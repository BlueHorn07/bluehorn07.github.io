---
title: "Poisson Distribution"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<br><span class="statement-title">TOC.</span><br>

- [Poisson Distribution](#poisson-distribution)
  - Law of Rare Events
- [Bernoulli Process & Poisson Process](#bernoulli-process--poisson-process)
  - [Bernoulli Process](#bernoulli-process)
  - [Poisson Process](#poisson-distribution)

<hr/>

# Poisson Distribution

\<푸아송 분포 Poisson Distribution\>는 [이항 분포]({{"/2021/03/17/discrete-probability-distributions-1.html#binomial-distribution" | relaitve_url}}) $\text{BIN}(n, p)$의 특수한 경우이다. $\text{BIN}(n, p)$에서 $n$이 무한대로 커지고, $p$가 아주아주 작아질 때, 분포는 푸아송 분포를 만족하게 된다!

그렇다면 본래 BIN이던걸 왜 푸아송 분포로 해석하는 걸까? 이 질문에 대한 답은 아래의 유튜브 영상에서 정말 잘 설명하고 있다. 한번 보고 오자.

👉 [Youtube - 푸아송분포 소개](https://youtu.be/JOWYEDwqAtY?t=79)

즉, <span class="half_HL">$n$과 $p$의 값을 다룰 수도 없고 정의할 수도 없을 때</span>, 푸아송은 $np$를 $\lambda$로 두고 새로운 형태의 분포를 유도한 것이다. 또는 평균값인 $\lambda$를 아는 상태에서 유도한 분포라고 볼 수 있을 것 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Poisson Distribution<br>

A Poisson random variable $X$ with parameter $\lambda > 0$, denoted as  $X \sim \text{POI}(\lambda)$, and it has a pmf $f(x)$ as

$$
f(x) = e^{-\lambda} \frac{\lambda^x}{x!} \quad \text{for} \quad x=0, 1, \dots
$$

</div>

이 푸아송 분포가 정말 pmf인지 검증해보자. 확률의 合이 1이 됨을 보이면 된다.

$$
\begin{aligned}
\sum f(x) &= e^{-\lambda} \sum_{x=0} \frac{\lambda^x}{x!} \\
&= e^{-\lambda} e^\lambda = 1
\end{aligned}
$$

앞에서 푸아송 분포는 이항분포의 특수한 경우라고 소개했다. 이것을 확인해보자.

<div class="math-statement" markdown="1">

<span class="statement-title">Derivation.</span><br>

Let $X \sim \text{BIN}(n, p) = \text{BIN}(n, \frac{\lambda}{n})$, then pmf $f_n (x)$ is

$$
f_n (x) = \binom{n}{x} p^x (1-p)^{n-x} = \binom{n}{x} \left( \frac{\lambda}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{n-x}
$$

위의 식에서 $\binom{n}{x}$를 풀어서 써보면 아래와 같고, 이것을 잘 정리해보자.

$$
\begin{aligned}
f_n (x) &= \binom{n}{x} \left( \frac{\lambda}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{n-x} \\
&= \frac{n!}{x!(n-x)!} \frac{\lambda^x}{n^x} \left( 1 - \frac{\lambda}{n}\right)^n \left( 1 - \frac{\lambda}{n}\right)^{-x} \\
&= \frac{\lambda^x}{x!} \cdot \left( 1 - \frac{\lambda}{n}\right)^n \cdot \frac{n!}{(n-x)!} \left(\frac{1}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{-x}
\end{aligned}
$$

이제 위의 식에서 $n \rightarrow \infty$를 취하자!

$$
\begin{aligned}
\lim_{n \rightarrow \infty} f_n (x) &= \lim_{n \rightarrow \infty} \frac{\lambda^x}{x!} \cdot \left( 1 - \frac{\lambda}{n}\right)^n \cdot \frac{n!}{(n-x)!} \left(\frac{1}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{-x} \\
&= \lim_{n \rightarrow \infty} \frac{\lambda^x}{x!} \cdot e^{-\lambda} \cdot \frac{n(n-1)\cdots(n-x+1)}{n^x} \cdot \frac{(n-\lambda)^{-x}}{n^{-x}} \\
&= \frac{\lambda^x}{x!} \cdot e^{-\lambda} \cdot 1 \cdot 1 \\
&= \frac{\lambda^x}{x!} e^{-\lambda}
\end{aligned}
$$

$\blacksquare$

</div>

위의 유도 과정에서는 이항 분포를 사용했지만, 미분방정식으로도 푸아송 분포를 유도할 수 있다고 한다. 유도 과정에 대한 영상을 링크로 걸어둔다. 👉 [YouTube - 푸아송 분포, 미분방정식으로 유도](https://youtu.be/vGExuMJRSyU)

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span> Law of Rare Events<br>

$n$이 무한히 커지게 되면, 자연스럽게 확률 $p=\dfrac{\lambda}{n}$는 작아지게 된다. 하지만, 이항 분포의 성질에 따라 여전히 평균과 분산은 아래와 같을 것이다.

- $\displaystyle E[X] = \lim_{n\rightarrow\infty} np = \lambda$
- $\displaystyle \text{Var}(X) = \lim_{n\rightarrow\infty} n \frac{\lambda}{n} \left( 1 - \frac{\lambda}{n}\right) = \lambda$

이런 상황에 대해 기술한 정리가 바로 \<**Law of rare event**\>이다 😎

</div>

위의 명제에 대한 증명은 평균과 분산의 정의에 입각해 식을 전개하면 된다. 증명은 추후에 기술하겠다.

<hr/>

# Bernoulli Process & Poisson Process

## Bernoulli Process

\<Poission Process\>를 다루기 위해선 먼저 \<Bernoulli Process\>에 대해 알아야 한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Bernoulli Process<br>

The \<**Bernoulli process**\> is a **<u>sequence</u>** of independent Bernoulli trials.

At each trial $X_i$,

- $P(H) = P(X_i = 1) = p$
- $P(T) = P(X_i = 0) = 1-p$

즉, 베르누이 시행은 Bernoulli RV Sequence $X = \\{ X_n : n=1, 2, \dots \\}$라고 볼 수 있다.

$$
X_i \sim \text{Ber}(p) \quad \text{and} \quad X \sim \text{BP}(p)
$$

</div>

이런 베르누이 프로세스의 예로는

- 매일 코스피 지수의 상승/하락에 대한 binary sequence
- 주어진 time interval에 신호가 수신되는지 아닌지에 대한 binary seq.

<hr/>

## Poisson Process

이번에는 <span class="half_HL">BP에서 극한을 취해 time intervel의 간격을 아주아주 줄인, 그래서 결국 continous한 시간축 위에서 시행되는 \<Poisson Process\></span>에 대해 살펴보자. 아래에 기술되는 내용은 아래의 유튜브 영상을 기준으로 작성하였다.

👉 [YouTube - Definition of the Possion Process](https://youtu.be/D_EGYzqmapc)

<br/>

<span class="half_HL">먼저 $N(t)$ 또는 $N_t$를 정의하자. 이것은 $t$시간까지 도착한 사건의 갯수를 의미하는 RV이다.</span> BP에서의 성질들을 바탕으로 \<Poisson Process\>를 잘 정의해보자.

<div class="notice" markdown="1">

1\. 각 time slot은 서로 독립이다.

Poisson Process도 이 성질을 가지므로, 아래의 명제가 성립한다.

"# of arrivals in disjoint time inteverals are **independent**."

이것을 수식으로 표현하면 아래와 같다.

$$
\left( N(t_2) - N(t_1) \right) \perp \left( N(t_4) - N(t_3) \right)
$$

</div>

<div class="notice" markdown="1">

2\. (Time homogeneity) 각 time slot에서 arrival이 발생할 확률이 동일하다.

마찬가지로 BP에서 각 time slot마다 모두 확률 $p$를 가졌기 때문에 Poission Process도 이 성질을 가진다. 이것을 기술하면,

<div style="text-align: center; margin: 4px;" markdown="1">

"$P(k, \tau)$, the prob. of $k$ arrivals in interval of duration $\tau$ is **<u>constant</u>**"

</div>

그리고 $P(k, \tau)$에 대해 이것을 $k$에 대해 모두 더하면, 그 확률의 合은 1이 된다.

$$
\sum^{\infty}_{k=0} P(k, \tau) = 1
$$

수업에선 이걸 조금 다르게 기술한 것 같다. "The distribution of $N(t) - N(s)$ only depends on $(t-s)$"

$$
N(t) - N(s) = N(t-s)
$$

</div>

<div class="notice" markdown="1">

3\. small interval probability

"두 arrival이 동일한 시간에 동시에 발생했다." 이런 경우를 생각할 수 있을까? 현실에서도 이런 "Same Time, Same place, Same Event"가 일어나는 건 불가능하다. Poission Process는 이런 동시에 발생하는 사건을 없애기 위해 아주 작은 interval $\delta$에 대해 아래와 같이 정의한다.

$$
P(k, \delta) \approx \begin{cases}
    1 - \lambda \delta & \text{if} \quad k=0 \\
    \lambda \delta & \text{if} \quad k=1 \\
    0 & \text{if} \quad k > 1
\end{cases}
$$

</div>

정리하면, 위와 같은 3가지 조건을 만족한다면 우리는 그 과정을 \<**Poisson Process**\>라고 한다!

<br/>

잠깐 다시 \<Bernoulli Process\>의 시각으로 돌아와보자. $[0, t]$ 간격을 가지는 확률 변수 $X$가 있다고 하자. 그러면, 이것의 확률은


$$
\begin{cases}
  P(X = 1) = \lambda t + o(h) \\
  P(X = 0) = 1 - \lambda t + o(h)
\end{cases}
$$

이때 $X_i$를 "# of buses that arrive in $[t_i, t_{i+1}]$"라고 정의한다면, $X_i$에 대한 분포는 Bernoulli Distribution을 따른다.

$$
\begin{cases}
  P(X = 1) = \lambda \cdot \dfrac{t}{n} + o(h) \\
  P(X = 0) = 1 - \lambda \cdot \dfrac{t}{n} + o(h)
\end{cases}
$$

$$
X_i \sim \text{Bernoulli}\left( \frac{\lambda t}{n} \right)
$$



이때, $N(t) = X_1 + \cdots + X_n$로 둔다면, $N(t)$는 Binomial Distribution $\text{BIN}(n, \lambda t/n)$을 따르게 된다.

$$
X_1 + \cdots + X_n = N(t) \sim \text{BIN}(n, \lambda t/n)
$$

이때, 우리가 $n \rightarrow \infty$로 보내고 $[t_i, t_{i+1}] \rightarrow 0$가 된다면, 앞에서 언급한 \<Law of Rare event\>에 의해 Binomial Distribution이 Poisson Distribution이 된다.

$$
\text{BIN}(n, \lambda t/n) \approx \text{POI}(\lambda t)
$$


정리하면, $N(t)$를 모은 sequence $\\{ N(t) : t \ge 0\\}$는 \<Possion Process\>다. 그리고 개별 $N(t)$는 \<Poission Distribution\>을 따른다. 🤩

$$
N(t) \sim \text{POI}(\lambda t)
$$

<hr/>

<div class="example" markdown="1">

<span class="statement-title">Example.</span><br>

Let $T$ be the time that the **1st bus arrives**. What is the distribution of $T$? (We know that the average arrival time is $\lambda$)

</div>

주의할 점은 앞에서 살펴본 \<Geometric Distribution\>처럼 1st event case를 구하는 문제이지만, Sample Space가 이산이 아니라 연속인 time axis라는 점이다!!

먼저 cdf $P(T \le t)$를 구해보자. $P(T \le t)$를 직접 구하지 말고, 반대 케이스인 $P(T > t)$를 이용해 유도해보자.

$P(T > t)$, 즉 기다리는 시간 $T$가 $t$보다 커질 확률은 곧 $t$ 시간까지 도착한 버스의 수가 0이 될 확률과 같다. 즉, $N(t) = 0$의 확률과 같다. 따라서,

$$
P(T > t) = P(N(t) = 0) = e^{-\lambda t} \frac{(\lambda t)^0}{0!} = e^{-\lambda t}
$$

따라서, $P(T \le t) = 1 - e^{-\lambda}$이다. 이것을 미분하면 pdf $f(x)$를 얻을 수 있다.

$$
\frac{d}{dt} P(T \le t) = \frac{d}{dt} (1 - e^{-\lambda t}) = \lambda \cdot e^{-\lambda t}
$$

뒤에서 다루겠지만, 위와 같은 pdf를 가지는 continuous distribution을 [**\<Exponential Distribution\>**]({{"/2021/03/31/exponential-distribution.html" | relative_url}})이라고 한다.

<hr/>

이번 포스트에서 다룬 \<Poisson Distribution\>을 끝으로 교재에서 다루는 모든 이산 확률 분포를 살펴보았다. 다음 포스트부터는 연속 RV가 갖는 \<연속 확률 분포; Continuous Distribution\>에 살펴보겠다.

- Continuous Probability Distribution
  - [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution.html" | relative_url}})
  - [Exponential Distribution]({{"/2021/03/31/exponential-distribution.html" | relative_url}})
  - [Gamma Distribution]({{"/2021/04/05/gamma-distribution.html" | relative_url}})
  - [Chi-square, Beta and Log-normal Distribution]({{"/2021/04/06/chi-and-beta-and-lognormal-distribution.html" | relative_url}})
  - [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})

