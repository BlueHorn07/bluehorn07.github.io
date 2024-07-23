---
title: "Stationarity"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Time Series Analysis"]
---

# 정상성 = 특징 없음

\<정상성(定常性); Stationarity\>는 시계열 분석에 중요한 개념 중 하나다. 어떤 시계열이 정상성을 가지려면 추세도, 계절성도, 주기도 존재해선 안 된다. (단, 주기가 불규칙(aperiodic)하다면, (약)정상성을 가질 수 있다.)정말 말 그대로 무성(無性)인 시계열이다. 😶 오히려 "정적인 상태를 가짐"이라고 해석할 수도 있겠다.


<div class="img-wrapper">
  <img src="{{ "/images/computer-science/time-series-analysis/stationarity-1.png" | relative_url }}" width="600px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 정상성과 차분](https://otexts.com/fppkr/stationarity)
  </p>
</div>

이런 무성의 특징을 잘 나타내는 시계열이 백색소음, White Noise이다. 어느 구간을 봐도, $N(0, \sigma^2)$를 만족하는 이 녀석은 정상성을 가진 시계열이라고 하기에 정말 딱이다! 위의 그림에서는 (b)가 이런 백색 소음이다.

(g)의 경우는 주기성이 있지만, 그 주기가 불규칙적(aperiodic)하다. 그래서 약한 정상성을 만족한다고 판단한다.

> 정상성을 가진 시계열을 직관적으로 파악하려면, 연필을 들고 지그재그 모양을 수평으로 막 그리면 그게 바로 정상성입니다! 위 그림에서 보면 (b)와 (g)가 그에 해당합니다.

다른 블로그에서 본 표현인데, 정상성을 표현하는데 적절한 비유인 것 같다 👏

<br/>

정상성은 강한 정상성(Strong Stationarity)와 약한 정상성(Week Stationarity)로 나뉜다. 보통은 약한 정상성만 만족해도, 시계열이 정상성을 가진다고 판단한다.

# Strong Stationarity

강한 정상성은 시계열 $\left\\{ X(t) \right\\}$에 대해 아래가 성립한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Strong Stationarity<br>

joint CDF $F_X (x_{t_1}, ..., x_{t_n})$에 대해 아래가 성립한다.

$$
F_X (x_{t_1}, ..., x_{t_n})
= F_X (x_{t_1 + \tau}, ..., x_{t_n + \tau})
$$

for all $\tau \in \mathbb{R}$ and for all $n \in \mathbb{N}$.

</div>

사실 위의 정의로 이해하는 것은 어렵다. 대충 "동일 데이터가 반복되는 것과 다름 없는" 시계열이라면, 강한 정상성이라고 이해해보자. 따라서 완전히 constant이거나 **백색소음**이라면, 강한 정상성 시계열이다.

# Week Stationarity

강한 정상성을 가진 시계열은 굉-장히 드물다. 그래서 조건을 완화한 약한 정상성 개념이 등장한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Week Stationarity<br>

For a continuous time random process $\left\\{ X(t) \right\\}$, it satisfies the following statements.

1. For every time step $t$, $E\left[ Z(t) \right] = \mu$
   1. $E \left[ Z(t) \right] = E \left[ Z(t + c) \right]$
2. For every time step $t$, $\text{Var}( Z(t) ) = \sigma^2$
3. $\text{Cor}(Z(t), Z(t+k)) = \gamma(k)$
   1. $\text{Cor}(Z(t), Z(t+k)) = \text{Cor}(Z(0), Z(k))$
   2. Auto-Correlation은 시차 $k$에만 의존한다.
4. $E \left[ \left\| X(t) \right\|^2 \right] < \inf$
   1. 2차 적률(momentum)이 존재한다.
   2. Infinite Variance가 존재하지 않는다는 말

</div>

<hr/>

# 정상성은 언제 쓰는 개념인가?

보통의 시계열 데이터는 정상성을 띄지 않을 것이다. 그러나, 정상성이 없는 시계열 데이터에는 앞으로 살펴볼 AR, MA, ARIMA와 같은 고전적인 시계열 분석 방법을 사용할 수 없다. 또, **시계열이 정상성을 가져야 "예측"이라는 걸 할 수 있다.**

그러면 시계열에 정상성이 없으면, "어? 나 분석 못함 수고여"하고 두 손 놓고 있는 건 아니다. \<차분; Differencing\>와 \<로그 변환\>을 통해 시계열 데이터를 변환해, 분석할 수 있는 형태로 만들면 된다!

\<로그 변환\>이야, 기존 데이터에 $\log$를 취해 스케일을 조정하는 것을 말한다. 이를 통해 변동성의 분산을 줄일 수 있다. \<차분\>은 인접한 두 시계열의 차이값을 구하는 것을 말한다. 다음 포스트 ["Differencing"]({{"/2022/09/10/differencing" | relative_url}})에서 자세한 내용을 확인하자 😉

# Reference

- [Forecasting: Principles and Practices: 정상성과 차분](https://otexts.com/fppkr/stationarity)
- [시계열 분석 시리즈 (1): 정상성 (Stationarity) 뽀개기](https://assaeunji.github.io/statistics/2021-08-08-stationarity/)
- [Wikipedia: Stationary Process](https://en.wikipedia.org/wiki/Stationary_process)
