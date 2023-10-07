---
title: "Time Series Decomposition"
toc: true
toc_sticky: true
categories: ["Time Series Analysis"]
---

시계열 데이터를 성분별: 추세(T), 계절성(S), 주기(C)로 분해함으로써 분석하려는 시계열 데이터의 특징을 이해할 수 있다.

<div class="img-wrapper">
  <img src="https://otexts.com/fppkr/fpp_files/figure-html/elecequip-trend-1.png" width="640px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 시계열 성분](https://otexts.com/fppkr/components)
  </p>
</div>

보통은 추세(T)와 주기(C)를 묶어, 하나의 추세 요소로 두고 분해하여 시계열의 전박적인 움직임만을 분리한다. 그러나, 이것은 상황에 따라 달라질 수도 있다.

시계열 데이터를 분해하는 구체적인 방법은 뒤에서 살펴보기로 하고, 지금은 시계열을 분해하면 어떤 모습인지만 살펴보자.

# Additive Decomposition

시계열 데이터를 (1) 추세-주기, (2) 계절, (3) 나머지 성분의 덧셈으로 분해한다.

$$
y(t) = T(t) + S(t) + R(t)
$$

<div class="img-wrapper">
  <img src="https://otexts.com/fppkr/fpp_files/figure-html/elecequip-stl-1.png" width="640px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 시계열 성분](https://otexts.com/fppkr/components)
  </p>
</div>

시계열 데이터를 Additive Decomposition한 모습이다. 시계열 분해는 \<STL Decomposition\>을 사용했다.

## Sesaonlly Adjusted Data

원본 데이터에서 계절 성분을 제거한 시계열을 말한다. 덧셈 분해를 했을 경우, 단순히 $y(t) - S(t)$를 하면 얻을 수 있다.

$$
y'(t) = y(t) - S(t)
$$

<div class="img-wrapper">
  <img src="https://otexts.com/fppkr/fpp_files/figure-html/elecequip-sa-1.png" width="640px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 시계열 성분](https://otexts.com/fppkr/components)
  </p>
</div>

계절성에 의한 변동은 시계열 분석의 목표가 아니라면, 시계열을 제거한 데이터를 보는게 유용할 수 있다. 예를 들어, "실업률"과 같은 경제 지표를 볼 때는 계절적 변동보다는 거시적인 흐름, 경기 침체와 경기 활성을 보는게 더 중요하기 때문이다.

그러나 시계열 분석의 목표가 "물류 분석/예측"과 같이 계절성을 고려해야만 하는 작업이라면, 계절성을 포함해 분석을 진행해야 할 것이다.

# Multiplicative Decomposition

이번에는 시계열 데이터를 (1) 추세-주기, (2) 계절, (3) 나머지 성분의 곱셈으로 분해한다.

$$
y(t) = T(t) \times S(t) \times R(t)
$$

<div class="img-wrapper">
  <img src="{{ "/images/time-series-analysis/time-series-decomposition-1.png" | relative_url }}" width="720px">
</div>

Additive에선 추세와 계절성이 별개의 요소였다. 그러나 Multiplicative에선 계절성의 변동성이 추세에 영향을 받았다. 위의 그림을 보면, **증가하는 추세의 영향으로 계절성이 더욱 크게 변동하고 있다!**

## Log Additive Decomposition

Mutliplicative Decomposition한 시계열에 로그($\log$)를 씌우면 다시 Additive Decomposition의 형태로 바꿀 수 있다!

$$
\log y(t) = \log T(t) + \log S(t) + \log R(t)
$$


# Reference

- [Forecasting: Principles and Practices: 시계열 성분](https://otexts.com/fppkr/components)