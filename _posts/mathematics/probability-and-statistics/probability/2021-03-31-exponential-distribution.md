---
title: "Exponential Distribution"
toc: true
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution" | relative_url}})
2. [Exponential Distribution]({{"/2021/03/31/exponential-distribution" | relative_url}}) 👀
3. [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})
4. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution" | relative_url}})
5. [Beta Distribution]({{"/2021/04/07/beta-distribution" | relative_url}})
6. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution" | relative_url}})
7. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution" | relative_url}})

</div>

<br><span class="statement-title">TOC.</span><br>

- Exponential Distribution
  - derived from Poisson Process
  - memoeryless property
  - mean & variance

<hr/>

이번 포스트에서는 포아송 프로세스와도 관련있는 \<Exponential Distribution\>에 대해 살펴본다!

### Exponential Distribution

먼저 분포에 대한 식을 먼저 제시하고, 그 상황과 의미를 살펴보자.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Exponential Distribution<br>

Let $\lambda >0$, we say that $X$ has an \<**exponential distribution**\> with parameter $\lambda$ if it has pdf $f(x)$ as

$$
f(x) = \begin{cases}
    \lambda e^{-\lambda x} & \text{for} \; x > 0\\
    \quad 0 & \text{else}
\end{cases}
$$

, and we denote such RV $X$ as $X \sim \text{EXP}(\lambda)$.

</div>


<span class="statement-title">Remark.</span><br>

1\. The cdf of $X$ is given by

$$
\begin{aligned}
P(X \le x) &= 1 - P(X > x) \\
            &= 1 - e^{-\lambda x} \quad \text{for} \; x >0
\end{aligned}
$$

이때 위의 cdf 식에서 주목할 점은 tail probability인 $P(X > x)$이다. \<Exponential Distribution\>의 경우, $P(X > x)$가 $P(X > x) = e^{-\lambda x}$로 계산됨에 주목하자. EXP에 대한 해설은 여기서부터 시작된다.

EXP는 \<Poisson Process\>의 상황에서부터 비롯된다. 먼저 \<Poisson Distribution\>은 $X$: [어떤 사건이 발생하는 횟수]를 대표하며, 그 함수는 아래와 같다.

$$
f(x) = e^{-\lambda} \frac{\lambda^x}{x!}
$$

만약, \<Poisson Process\> $\\{ N(t) \\}$에 대해 생각한다면, $t$ 시간까지 도착한 사건의 숫자인 $N(t)$는 포아송 분포 $N(t) \sim \text{POI}(\lambda t)$를 따른다.

이런 상황을 생각해보자. "어떤 사건이 처음으로 발생하기 까지 걸린 시간"을 RV $T$라고 해보자. 우리가 $P(T > t)$ 즉, 어떤 사건이 $T=t$ 시간 이후에 발생할 확률을 구한다고 해보자. 이것을 Poisson Process의 관점에서 해석하면, $T=t$ 시간까지 어떤 사건도 일어나지 않은 상태, 즉 $N(t) = 0$인 상황이다. 우리는 $N(t)$에 대한 pdf를 알기 때문에 이것을 확률로 표현하면,

$$
P(N(t) = 0) = e^{-\lambda t} \frac{(\lambda t)^0}{0!} = e^{-\lambda t}
$$

가 된다. 즉, $P(T > t) = P(N(t) = 0) = e^{-\lambda t}$인 셈이다! 🤩

우리가 구한 $P(T >t)$는 $T$에 대한 tail probability이다. 그래서 이것을 cdf의 형태로 바꿔주면,

$$
P(T \le t) = 1 - P(T > t) = 1 - e^{-\lambda t}
$$

이다. 우리가 cdf를 알고 있으니, 미분을 통해 pdf도 구할 수 있다.

$$
f(t) = \frac{d}{dt} P(T \le t) = \frac{d}{dt} (1 - e^{-\lambda t}) = \lambda e^{-\lambda t}
$$

익숙한 형태이지 않은가?? 바로 우리가 정의한 \<Exponential Distribution\>이다!! 😎

<span class="half_HL">즉, EXP는 어떤 사건이 처음으로 일어날 시간에 대한 확률 분포라고 할 수 있다!</span>

<br/>

\<Exponential Distribution\>은 $\lambda$, $\beta$ 두 가지 형태로 기술할 수 있다. 이때, $\lambda$는 Poisson Process에서 유래한 것으로 Time Unit(=interval) 당 발생하는 Event의 평균적인 **횟수**를 의미한다. EXP는 $\beta$로도 모델링할 수 있는데, 이때 $\beta$는 $\lambda$의 역수(reciprocol)이다. 따라서, $\beta$는 첫 Event가 발생하는게 걸리는 평균적인 **시간**을 의미한다.

$$
X \sim \text{EXP}(\lambda) \iff f(x) = \lambda e^{-\lambda x}
$$

- <span class="half_HL">$\lambda$는 Unit time 동안 Event가 일어날 평균 **횟수**</span>를 의미한다.

$$
X \sim \text{EXP}(\beta) \iff f(x) = \frac{1}{\beta} e^{-x/\beta}
$$

- $\lambda$의 역수인 <span class="half_HL">$\beta$는 한 번의 Event가 일어날 평균 **시간**</span>을 의미한다.

<hr/>

2\. If $X \sim \text{EXP}(1)$, then $Y := \dfrac{X}{\lambda} \sim \text{EXP}(\lambda)$.

$$
P(Y > y) = P(\frac{X}{\lambda} > y) = P(X > \lambda y) = e^{-\lambda y}
$$

본인은 위의 상황을 (minute - second) 변환을 바탕으로 이해했다. 만약 $X$가 분 단위에서 처음 도착하는 버스의 시간을 모델링하고, 그 때의 parameter가 $\lambda = 1$라고 하자. 우리는 이것을 초 단위인 $60X$로 변환할 수 있다. 이때의 tail probability는

$$
P(60X > x) = P(X > x/60) = e^{- x/60}
$$

따라서, $60X \sim \text{EXP}(1/60)$이 된다. 이것은 $60X$에서 $\lambda$가 $\lambda = 1/60$이 됨을 의미한다. 이때, $\lambda$는 Poisson Process의 parameter로, Time Unit 당 도착하는 버스의 수를 모델링한다. 따라서 1초 당 평균적으로 1/60 대의 버스가 도착함을 의미한다. 이것을 $\beta = 1 / \lambda$로 해석하면, 버스가 한번 도착하는 시간이 평균적으로 60초가 됨을 의미한다!

<hr/>

3\. (**Memoryless Property**) 우리는 앞서 어떤 사건이 처음으로 발행하는 시행 횟수 $X$를 모델링한 \<Geometric Distribution\>을 살펴본 적이 있다. 어떤 분포가 \<Memoryless Property\>를 가진다면, 아래의 식을 만족한다.

$$
P(X > a + t \mid X >a) = P(X > t)
$$

EXP가 위의 Memoryless Property를 가지는지 확인해보자.

$$
\begin{aligned}
P(X > a + t \mid X > a) &= \frac{P(X > a + t)}{P(X > a)} \\
                        &= \frac{e^{-\lambda (a+t)x}}{e^{-\lambda a x}} \\
                        &= e^{-\lambda tx} = P(X > t)
\end{aligned}
$$

따라서, EXP 역시 **Memoryless Property**를 가진다!

\<Geometric Distribution\>으로부터 \<Exponential Distribution\>을 유도해볼 수도 있는데, 아래의 펼쳐보기에 기술하였다.

<details class="math-statement" markdown="1">
<summary>펼쳐보기</summary>

Random Variable $X_n$을 $1/n$초마다 버스가 왔는지 안 왔는지 확인했을 때, 버스가 처음올 때까지 확인한 **횟수**라고 해보자. 또, $X$는 버스가 처음올 때까지 걸린 **시간**이라고 한다면, $X_n$와 $X$ 사이에는 아래의 비례식이 성립할 것이다.

$$
X_n : X = 1 : \frac{1}{n}
$$

또, Geometric Distribution을 따르는 $X_n$의 parameter를 $p$라고 하자; $X_n \sim \text{Geo}(p)$, 그러면 $E[X_n] = 1/p$가 된다. 즉, 평균적으로 $1/p$번 확인한다는 말이다. 이것을 다시 $X$의 관점에서 기술하면, 평균적으로 $1/np$초가 걸린다는 말이다. 즉, $\beta = 1/np$라는 말이고, $\lambda$로 표현하면, $\lambda = np$라는 말이다. 따라서, $X_n \sim \text{Geo}\left( \frac{\lambda}{n} \right)$가 된다.

이에 따라, $X$의 tail probability $P(X > x)$는

$$
\begin{aligned}
    P(X > x) &= P\left(\frac{X_n}{n} > x\right) \\
            &= P(X_n > nx) \\
            &= \left( 1 - \frac{\lambda}{n}\right)^{nx} \\
            &= e^{-\lambda x} \quad \text{as } n \rightarrow \infty
\end{aligned}
$$

즉, \<Geometric Distribution\>에서 극한을 취해 \<Exponential Distribution\>을 유도할 수 있다!

</details>

<hr/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{EXP}(\lambda)$, then

- $E[X] = \dfrac{1}{\lambda}$
- $\text{Var}(X) = \dfrac{1}{\lambda^2}$

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

Let $Y \sim \text{EXP}(1)$, then what are the mean and variacen of $Y$?

$$
\begin{aligned}
E[Y] &= \int^{\infty}_0 y \cdot e^{-y} \; dy = 1
\end{aligned}
$$

Variance를 구해보면,

$$
\begin{aligned}
E[Y^2] = \int^{\infty}_0 y^2 \cdot e^{-y} \; dy = 2
\end{aligned}
$$

따라서, $\text{Var}(Y) = E[Y^2] - E[Y]^2 = 2 - 1^2 = 1$.

이제, $X \sim \text{EXP}(\lambda)$를 살펴보자. 우리는 앞의 \<Remark 2\>를 통해 $X = \dfrac{Y}{\lambda}$임을 알 수 있다. 따라서,

$$
E[X] = E\left[\frac{Y}{\lambda}\right] = \frac{1}{\lambda}
$$

$$
\text{Var}(X) = \text{Var}\left( \frac{Y}{\lambda} \right) = \frac{1}{\lambda^2}
$$

</div>

<hr/>

<span class="statement-title">요약.</span><br>

- 어떤 사건의 발생 횟수가 **포아송 분포**를 따른다면, 사건 사이의 대기 시간은 **지수 분포**를 따르게 된다. (또는 첫 사건이 발생하는 데까지 걸리는 시간은 지수 분포를 따른다.)
- $\lambda$는 Unit time 동안 Event가 일어날 평균 **횟수**를 의미한다. 그리고 그 역수인 $\beta$는 한 번의 Event가 발생하는 데 걸리는 평균 **시간**을 의미한다.
- \<Exponential Distribution\>은 \<Geometric Distribution\>의 극한 버전이다. Geo에서 trial을 시행하는 시간 간격 $1/n$이 0에 가까워질 때, Geo가 EXP를 따르게 되는 것이다.

<hr/>

이어지는 포스트에서는 연속 확률 분포에서 \<정규 분포\>만큼이나 중요한 분포인 \<**감마 분포; Gamma Distribution**\>에 대해 살펴본다! 🤩

👉 [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})

<hr/>

### References

- ['soohee410'님의 포스트](https://soohee410.github.io/exponential_dist)