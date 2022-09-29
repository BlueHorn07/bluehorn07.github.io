---
title: "Moving-Average Model"
layout: post
use_math: true
tags: ["time_series_analysis"]
---

# 오차로 미래를 예측한다

\<Moving-Average Model\>은 쌍둥이 같은 존재인 \<Auto-Regressive Model\>보다는 이해하기 어려웠다. 😥 그래도 최대한 쉽게 풀어서 설명해보겠다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Moving-Average Model<br>

$$
X(t) = \epsilon(t) + \phi_0 + \phi_1 \epsilon(t-1) + \phi_2 \epsilon(t-2) + \cdots + \phi_q \epsilon(t-q)
$$

where all $\epsilon(t)$ are white noise.

보통 $\phi_0$는 시계열의 평균 $\mu = E \left[ X(t) \right]$로 둔다.

</div>

\<MA Model\>의 수식을 보면, 현재와 과거의 오차 $\epsilon(t)$의 Multiple Regression으로 구성되어 있다. 

Hyper-parameter는 몇개의 Lagged Error를 쓸 것인지에 대한 $q$ 값이다. 이를 기준으로 $q$차 MA 모델은

$$
\text{MA}(q)
$$

라고 표현한다.

<br/>

그러나 일반적인 Multiple Regression과 다르게, Regression을 구성하는 lagged error term의 값은 확정적으로 정해진 형태가 아니다. 그렇기에 일반적인 LS method의 Regression Fitting을 하는 것이 아니라 **Iterative Fitting**으로 Fitting을 수행한다고 한다.

## 왜 이름이 Moving-Average 인가?

"Moving Average"라는 이름은 혼란을 준다. 시계열 분석 기법 중에 "Moving Average"라는 이름이 붙은 \<Moving Average Smoothing\> 기법이 있고, 이게 더 유명하기 때문이다.😐

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Moving Average Smoothing<br>

$$
\text{MA}(t) = \frac{1}{m} \sum^k_{j = -k} = X(t + j)
$$

</div>

<div class="img-wrapper">
  <img src="https://otexts.com/fppkr/fpp_files/figure-html/elecequip2-1.png" width="640px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 이동평균](https://otexts.com/fppkr/moving-averages.html)
  </p>
</div>

<br/>

그런데 왜 굳이, 헷갈리게 이 녀석도 "Moving Average"라는 이름이 붙은 걸까?🤔

> 먼저 "이동"은 모형의 평균 $\mu$를 중심으로 백색잡음과정을 따르는 $\epsilon(t)$들로 인해 시계열이 위아래로 이동한다는 의미입니다.

> 그리고 "평균"은 위아래로 움직이는 정도를 백색잡음과정 $\epsilon(t)$의 $t$시점의 값과 과거 시점의 값들의 **"가중합"**했다는 의미로 이해하시면 됩니다.

[간토끼님의 블로그](https://datalabbit.tistory.com/121)에서 설명을 가져왔는데, 그럴듯하다!😀

## AR vs. MA

- You would choose an AR model if you believe that "previous observations have a direct effect on the time series".
- You would choose an MA model if you believe that "the weighted sum of lagged errors have a direct effect on the time series".

사실 Lagged Error를 사용해 Fitting을 한다는게 잘 와닿지 않는다. 그러나 그것이 당연한 것이 뒤에서 살펴볼 것이지만, Lagged Error 하나만 고려해서는 시계열 데이터를 모델링하기 어렵기 때문이다! 

결국엔 AR과 MA을 함께 쓰는 \<ARMA 모델\>과 같이 MA 모델을 다른 기법과 함께 쓰게 된다. 그러니 지금은 MA 모델의 콘셉트만 확인하고 다음 모델로 얼른 넘어가자 👏

# MA models have stationairty

$-1 < \phi_1 < 1$ 조건 아래에서 정상성을 만족하던 $\text{AR}(p)$ 모델과 다르게, $\text{MA}(q)$ 모델을 항상 정상성을 만족한다.

이것은 $\text{MA}(q)$가 정상성을 만족하는 white noise $\epsilon(t)$의 유한한 합이기 때문이다 😀

# Invertible

([Forecasting: Principles and Practices: 이동평균](https://otexts.com/fppkr/moving-averages.html)의 내용으로 대체. 이하 생략 😉)

# Reference

- [Wikipedia: Moving-Average Model](https://en.wikipedia.org/wiki/Moving-average_model)
- [Forecasting: Principles and Practices: 이동평균](https://otexts.com/fppkr/moving-averages.html)
- [[시계열분석] 이동평균모형(Moving Average Model; MA Model)](https://datalabbit.tistory.com/121)
- [When to use AR and when to use MA model?](https://stats.stackexchange.com/a/488447/283988)