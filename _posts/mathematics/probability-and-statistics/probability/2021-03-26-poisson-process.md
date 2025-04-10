---
title: "Poisson Process"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# Poisson Process

이번에는 <span class="half_HL">BP에서 극한을 취해 time interval의 간격을 아주아주 줄인, 그래서 결국 continuous한 시간축 위에서 시행되는 \<Poisson Process\></span>에 대해 살펴보자. 아래에 기술되는 내용은 아래의 유튜브 영상을 기준으로 작성하였다.

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

# 1st Arrival

<div class="problem" markdown="1">

Let $T$ be the time that the **1st bus arrives**. What is the distribution of $T$? (We know that the average arrival time is $\lambda$)

</div>

주의할 점은 앞에서 살펴본 \<Geometric Distribution\>처럼 1st event case를 구하는 문제이지만, Sample Space가 이산이 아니라 연속인 time axis라는 점이다!!

먼저 cdf $P(T \le t)$를 구해보자. $P(T \le t)$를 직접 구하지 말고, 반대 케이스인 $P(T > t)$를 이용해 유도해보자.

$P(T > t)$, 즉 기다리는 시간 $T$가 $t$보다 커질 확률은 곧 $t$ 시간까지 도착한 버스의 수가 0이 될 확률과 같다. 즉, $N(t) = 0$의 확률과 같다. 따라서,

$$
P(T > t) = P(N(t) = 0) = e^{-\lambda t} \frac{(\lambda t)^0}{0!} = e^{-\lambda t}
$$

따라서, $P(T \le t) = 1 - e^{-\lambda}$이고, 이것은 cdf 함수이다. 이걸 미분하면 pdf $f(t)$를 얻을 수 있다.

$$
f(t) = \frac{d}{dt} P(T \le t) = \frac{d}{dt} (1 - e^{-\lambda t}) = \lambda \cdot e^{-\lambda t}
$$

와우! 연속 함수인 [**Exponential Distribution**](/2021/03/31/exponential-distribution)이 등장 했다 ㅎㅎ


# n-th Arrival

$n$개 버스가 도착하는 순간인 $T_n$의 분포도 생각해볼 수 있습니다.
<div class="proof" markdown="1">

$$
\begin{aligned}
P(T_n > t) = P(N(t) < n - 1)
\end{aligned}
$$

이때, $N(t) \sim \text{POI}(\lambda t)$이므로,

$$
\begin{aligned}
P(N(t) < n - 1) &= \sum^{n-1}_{k=0} P(N(t) = k) \\
            &= \sum^{n-1}_{k=0} e^{-\lambda t} \frac{(\lambda t)^k}{k!}
\end{aligned}
$$

위의 식을 통해 $T_n$의 cdf를 알고 있으니, 이것을 미분해 $T_n$의 pdf를 유도해보자.

$$
\begin{aligned}
f(t) = \frac{d}{dt} P(T_n \le t)
&= - \frac{d}{dt} P(T_n > t) \\
&= - \left( \sum^{n-1}_{k=0} (-\lambda) e^{-\lambda t} \frac{(\lambda t)^k}{k!} + \sum^{n-1}_{k=1} \lambda e^{-\lambda t} \frac{(\lambda t)^{(k-1)}}{(k-1)!}\right) \\
&= \lambda e^{-\lambda t} \cdot \left( \sum^{n-1}_{k=0} \frac{(\lambda t)^k}{k!} - \sum^{n-1}_{k=1} \frac{(\lambda t)^{(k-1)}}{(k-1)!} \right) \\
&= \lambda e^{-\lambda t} \frac{(\lambda t)^{(n-1)}}{(n-1)!} \\
&= \frac{\lambda^n}{(n-1)!} \cdot t^{n-1} e^{-\lambda t} \\
&= \frac{\lambda^n}{\Gamma(n)} \cdot t^{n-1} e^{-\lambda t} \\
&= \frac{1}{\Gamma(n) \beta^n} \cdot t^{n-1} e^{-t/\beta} \\
&= C_{n, \beta} \cdot t^{n-1} e^{-t/\beta} \\
&= f(x; n, \beta)
\end{aligned}
$$

즉, $T_n \sim \text{Gamma}(n, \beta)$이다. $\blacksquare$

</div>

와우 감마 분포가 등장 했다 ㅎㅎ 사실 별로 놀랍지 않은 것이 $n$개의 독립된 지수 분포를 모으면 감마 분포를 유도할 수 있습니다.

각 버스가 도착하는 사건은 독립적으로 발생하고, $n$번째 버스가 도착하는 과정도 독립적인 $n$개 지수 분포가 발생하는 것으로 이해할 수 있기 때문에 동일한 상황으로 이해할 수 있습니다.

# 맺음말

확률론에는 이것 말고도 더 다양한 "랜덤 프로세스"가 존재 합니다. 더 많은 내용은 아래의 포스트들을 방문해봅시다 ㅎㅎ

- [Random Process](/2021/06/30/random-process/)
  - [Gaussian Process]()
  - [Markov Process](/2021/07/03/markov-process/)


