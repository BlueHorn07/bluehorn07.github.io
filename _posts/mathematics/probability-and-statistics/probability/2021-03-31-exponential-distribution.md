---
title: "Exponential Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform Distribution](/2021/03/29/uniform-distribution)
2. [Normal Distribution](/2021/03/30/normal-distribution)
3. [Exponential Distribution](/2021/03/31/exponential-distribution)
   1. [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process) 👀
   1. [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution)
4. [Gamma Distribution](/2021/04/05/gamma-distribution)
5. [Chi-square Distribution](/2021/04/06/chi-square-distribution)
6. [Beta Distribution](/2021/04/07/beta-distribution)
7. [Log-normal Distribution](/2021/04/08/log-normal-distribution)
8. [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

</div>

# 들어가며

어떤 사건이 평균적으로 “5분에 1번” 발생한다고 가정해보자. 예를 들어, 출퇴근 시간에 2호선 지하철이 평균적으로 5분마다 한 대씩 도착한다고 생각할 수 있다.

> 블루혼은 사당역에서 2호선 지하철을 기다리고 있습니다. 수많은 출퇴근 경험에 의해 블루혼은 이 시간대에 평균적으로 5분 정도 기다리면 지하철이 온다는 것을 알고 있습니다. 어떨 때는 2호선을 눈앞에서 놓쳐도 다른 다음 열차가 들어와서 3분도 안 기다릴 때가 있지만, 어떨 때는 2호선을 하염없이 기다릴 때도 있습니다.

평균적인 대기 시간을 알고 있을 때, 지하철을 기다리기 위해 쓰는 시간은 "지수 분포"라는 연속 확률 분포를 따릅니다!

# Distribution for waiting time

다음에 들어올 2호선을 기다리는데 걸리는 시간은 아래의 지수 분포를 따릅니다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Exponential Distribution (waiting time)<br>

Let $\beta >0$ is an average waiting time, and we say that $X$ has an \<**exponential distribution**\> with parameter $\beta$ if it has pdf $f(x)$ as

$$
f(x) =
\begin{cases}
\frac{1}{\beta} e^{- \frac{1}{\beta} x} & \text{for} \; x > 0\\
\quad 0 & \text{else}
\end{cases}
$$

and we denote such RV $X$ as $X \sim \text{EXP}(\beta)$.

</div>

## Expectation and Variance

$\beta$를 **평균 대기 시간(average waiting time)**으로 정의했으므로, 확률 변수 $X$의 기댓값(즉, 평균)은 $\beta$와 같습니다. 즉,

$$
E[X] = \beta
$$

분산의 경우는 지수 확률 분포에 대한 식을 잘 정리하면 아래의 결과를 얻을 수 있습니다.

$$
\text{Var}(X) = \beta^2
$$

이때 $\beta$는 사건이 발생하기 위해 평균적으로 대기 하는 시간을 의미 합니다. "5분에 1건씩" 발생하는 경우라면, $\beta = 5$가 되고, 분포는 아래와 같습니다.

$$
P(X = x) = \frac{1}{5} e^{-\frac{1}{5} x}
$$


## Distribution for event rate

> 블혼은 출퇴근 시간에 지하철역에서 다음 열차를 기다리는 동안, 전광판을 보면서 "1시간 동안 열차가 몇 대나 지나갔을까?"라는 궁금증이 생겼습니다. 대기 시간에 대한 분포를 살펴봤던 것처럼, 지하철이 평균 5분대 1대씩 도착한다고 가정하면, 1시간 동안 도착하는 열차의 갯수는 12대가 될 것 입니다. <br/>
> 그러나 어떤 날은 스크린도어 고장으로 열차가 지연 되어 1시간 동안 10대가 올 수 있고, 어떤 날은 운행이 순조로워서 1시간에 15대가 올 수도 있습니다.

블루혼은 일정한 시간 동안 열차가 몇 대 도착하는지를 확률적으로 모델링하고 싶어졌습니다. 사건 발생 횟수(count)에 대한 확률 분포가 바로 “**푸아송 분포**”입니다. 푸아송 분포에 대해서는 [별도 포스트](/2021/03/25/poisson-distribution/)에 정리한 것도 있습니다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Poisson Distribution (event rate)<br>

Let $\lambda >0$ is an event occurring rate, and we say that $X$ has an \<**Poisson distribution**\> with parameter $\lambda$ if it has pdf $f(x)$ as

$$
f(x) = \frac{\lambda^x \cdot e^{-\lambda}}{x!}
$$

for $x = 0, 1, ...$ and we denote such RV $X$ as $X \sim \text{POI}(\lambda)$.

</div>

예시의 상황을 가져와서 함수를 모델링 하면, 5분당 1대의 열차가 들어온다면 1분당 0.2대의 열차가 들어오는 것과 같습니다. 즉, $\lambda = 0.2$ 이것을 포아송 분포의 함수로 적으면

$$
P(X = x) = \frac{(0.2)^x \cdot e^{-0.2}}{x!}
$$

와 같습니다.

# Expectation and Variance

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{EXP}(\lambda)$, then

- $E[X] = \dfrac{1}{\lambda}$
- $\text{Var}(X) = \dfrac{1}{\lambda^2}$

</div>

분산에 대한 것만

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$\lambda = \beta = 1$인 표준 지수 분포에 대해 생각해봅시다.

$$
Y \sim \text{EXP}(1)
$$

이 표준 지수 분포의 평균과 분산은 어떻게 될까요?

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

<br/>

이제, $X \sim \text{EXP}(\beta = 1 / \lambda)$를 살펴 봅시다. 대기 시간이 $\beta$ 만큼 늘어났으므로 $X = \beta \cdot Y = \dfrac{Y}{\lambda}$를 만족합니다. 따라서

$$
E[X]
= E\left[\beta \cdot Y \right]
= E\left[\frac{Y}{\lambda}\right]
= \frac{1}{\lambda}
$$

그리고 분산은

$$
\text{Var}(X)
= \text{Var}\left( \beta \cdot Y \right)
= \text{Var}\left( \frac{Y}{\lambda} \right)
= \frac{1}{\lambda^2}
$$

</div>

# Unit Conversion

If $X \sim \text{EXP}(1)$, then $Y := \dfrac{X}{\lambda} \sim \text{EXP}(\lambda)$.

$$
P(Y > y) = P(\frac{X}{\lambda} > y) = P(X > \lambda y) = e^{-\lambda y}
$$

본인은 위의 상황을 (minute - second) 변환을 바탕으로 이해했다. 만약 $X$가 분 단위에서 처음 도착하는 버스의 시간을 모델링하고, 그 때의 parameter가 $\lambda = 1$라고 하자. 우리는 이것을 초 단위인 $60X$로 변환할 수 있다. 이때의 tail probability는

$$
P(60X > x) = P(X > x/60) = e^{- x/60}
$$

따라서, $60X \sim \text{EXP}(1/60)$이 된다. 이것은 $60X$에서 $\lambda$가 $\lambda = 1/60$이 됨을 의미한다. 이때, $\lambda$는 Poisson Process의 parameter로, Time Unit 당 도착하는 버스의 수를 모델링한다. 따라서 1초 당 평균적으로 1/60 대의 버스가 도착함을 의미한다. 이것을 $\beta = 1 / \lambda$로 해석하면, 버스가 한번 도착하는 시간이 평균적으로 60초가 됨을 의미한다!

# 더 나아가기

## Duality: Exponential Distribution and Poisson Process

푸아송 과정에서 연속적인 두 사건 사이의 간격은 지수 분포를 따릅니다. 내용이 길어져서 별도 포스트로 분리하였습니다 ㅎㅎ

👉 [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process)


## Duality: Exponential Distribution and Geometric Distribution

연속확률분포인 지수 분포는 이산확률분포인 기하 분포에서 시행 간격을 극한으로 줄인 버전입니다. 이것도 내용이 길어져서 별도 포스트로 분리하였습니다 ㅎㅎ

👉 [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution)

# 맺음말

이어지는 포스트에서는 연속 확률 분포에서 \<정규 분포\>만큼이나 중요한 분포인 \<**감마 분포; Gamma Distribution**\>에 대해 살펴 보겠습니다 🤩

👉 [Gamma Distribution](/2021/04/05/gamma-distribution)

# References

- ['soohee410'님의 포스트](https://soohee410.github.io/exponential_dist)
