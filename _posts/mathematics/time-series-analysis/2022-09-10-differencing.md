---
title: "Differencing"
toc: true
toc_sticky: true
categories: ["Time Series Analysis"]
---

# 차분(Differencing)이란?

\<차분; Differencing\>은 간단한다. 인접한 두 시계열의 차이값을 구하면 된다.

$$
\Delta(t) = S(t) - S(t-1)
$$

정상성을 띄지 않던 시계열에 차분을 취하면, 정상성을 띄는 경우가 있다. 이는 각 타임 스텝별로 차이값이 일정한 경우인 것이다.

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/goog200-1st-differencing.png" | relative_url }}" width="480px">
</div>

우상향 추세를 띄던 `goog200` 데이터를 1차 차분하면 위와 같이 패턴이 보이지 않는 정상성 시계열로 변환할 수 있다.

## 2차 차분

경우에 따라선 1차 차분 $\Delta(t)$를 한번더 차분할 수도 있다. 그러면 "변화값의 변화값"을 보게 되는 것이다.

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/goog200-2nd-differencing.png" | relative_url }}" width="480px">
</div>

그러나 2차 차분까지 보는 경우는 거의 없다.

## 계절성 차분(Seasonal Differencing)

계절성(Seasonality) 별 변화값을 보기 위한 차분이다. 바로 인접한 시계열 데이터와의 차이값을 보는게 아니라 이전 계절의 데이터와의 차이값을 계산한다.

$$
\Delta_s(t; m) = S(t) - S(t-m)
$$

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/goog200-seasonal-differencing.png" | relative_url }}" width="480px">
</div>

위의 그림은 $m=7$, 즉 요일에 대한 계절성 차분을 구한 결과다. 기존 데이터에서 추세가 사라진 모습을 볼 수 있다! 😀

## Random Walk Process: 차분과 정상성

만약 시계열의 차분 $\Delta(t)$가 정상성(Stationarity)를 갖는다면, 원래의 시계열을 아래와 같이 기술할 수 있다.

$$
S(t) - S(t-1) = \epsilon(t)
$$

이때, $\epsilon(t)$는 백색 소음이다. 수식을 다시 정리해서 아래와 같이 기술하면 아래와 같은데, 이런 시계열을 **\<확률 보행 과정; Random Walk Process\>**이라고 한다.

$$
S(t) = S(t-1) + \epsilon(t) + c
$$

이때, 끼어든 $c$를 절편(Drift)라고 하는데, 추세(T)를 말한다. $c > 0$이면 우상향, $c < 0$이면 우하향 추세이다.

