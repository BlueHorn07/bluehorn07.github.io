---
title: "Time Series Component: Trend, Seasonality, Cycle"
layout: post
tags: ["time_series_analysis"]
---

# Time Series Component

시계열 데이터의 패턴, 특징을 얘기할 때 3가지에 대해 말한다: 추세(Trend), 계절성(Seasonality), 주기(Cycle)

## Trend

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/time-series-components-1.png" | relative_url }}" width="480px">
</div>

시계열이 **"장기적으로 증가 또는 감소하는 경향"**을 말한다. 추세가 일정할 필요는 없다. 증가 추세 였다가 감소 추세로 변한다면, 그것은 추세의 "방향이 변했다"라고 말한다.

## Seasonality

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/time-series-components-2.png" | relative_url }}" width="480px">
</div>

짧은 주기로 예외 없이 반복해서 나타나는 패턴을 말한다. 예를 들어, 도심 교통이 낮에는 증가하고, 밤에는 줄어는 것을 계절성(Seasonality)이라고 한다. 여름에 에어컨 때문에 전기세가 많이 나오고, 겨울에 난방비가 많이 나오는, 주말에 서울 외곽의 교통량이 증가하는 것 모두 계절성이다.

## Cycle

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/time-series-components-3.png" | relative_url }}" width="480px">
</div>

주기(Cycle)은 거시적인 관점에서 반복해서 일어나는 추세의 변동이다. 예를 들어, 위의 그림에서는 6-10년을 주기로 거래량이 한 사이클을 돌고 있다.

처음에는 계절성(Seasonality)와 주기(Cycle)이 잘 구분하지 못 했다. 그런데, 계절성은 그 주기성이 짧고, 주기는 그 주기성이 크다는 사실, 또 주기성이 찾아오면 그때의 변동성은 계절성보다 훨씬 크다는 사실을 구분해야 한다.

## Irregularity

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/time-series-components-4.png" | relative_url }}" width="480px">
</div>

규칙성을 인지할 수 없는 변동 유형을 말한다. 천재지변, 전쟁, 질병과 같이 패턴이 없고, 우연적인 요인에 의한 사건들을 총칭한다.

<hr/>

# (Next) Time Sereis Decomposition

다음 포스트에서 시계열 데이터를 분해(Decompose)하는 접근을 살펴볼 것이다. 시계열 데이터를 추세(T), 계절성(S), 주기(C)로 분해하여 정규화 하여, 원하는 관점에서 원하는 분석과 예측을 진행할 수 있다.

<hr/>

# Reference

- [Forecasting: Principles and Practices: 시계열 패턴](https://otexts.com/fppkr/tspatterns.html)
