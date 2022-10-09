---
title: "SARIMA & SARIMAX Model"
layout: post
tags: ["time_series_analysis"]
---

# SARIMA Model

SARIMA 모델은 ARIMA 모델에 계절적 요소를 결합한 모델이다. 수식은 크게 비계절 요인과 계절 요인에 대한 부분으로 나뉜다.

$$
\text{SARIMA}(p, d, q)(P, D, Q)_m
$$

여기에서 $(p, d, q)$는 비계절 요인에 대한, $(P, D, Q)_m$은 계절 요인에 대한 Hyper-parameter이다.

$(P, D, Q)_m$에서 $(P, D, Q)$는 기존 ARIMA의 Hyper-parameter와 모두 동일한 의미이다. $m$은 계절 요소의 주기로 월별 시계열에서 $m=12$라면, 1년 주기의 계절 요인을 모델링하게 된다.

Backshift 연산자로 표현한 $\text{SARIMA}(1, 1, 1)(1, 1, 1)_4$의 수식은 아래와 같다.

$$
(1 - \phi_1 B) (1 - \Phi_1 B^4) (1 - B) (1  - B^4) Z(t) = (1 + \theta_1 B)(1 + \Theta_1 B^4) \epsilon(t)
$$

## Example: Datadog Anomaly Detection

인프라 리소스 모니터링 서비스인 ![Datadog](https://img.shields.io/badge/datadog-%23632CA6.svg?style=for-the-badge&logo=datadog&logoColor=white)은 SARIMA 모델을 통해 Anomaly Detection을 수행한다고 한다.

<div class="img-wrapper">
  <a href="https://www.datadoghq.com/blog/introducing-anomaly-detection-datadog/" target="_blank">
  <img src="{{ "/images/time-series-analysis/datadog-anomaly-detection.png" | relative_url }}" width="100%">
  </a>
</div>

자세한 내용이 궁금하다면, 위의 캡쳐를 클릭해보자. 👏

# SARIMAX Model

SARIMAX는 SARIMA 모델에 **"외생변수(eXogenous variable)"**을 추가로 고려하는 모델이다. 외생변수란 실험 대상이 되는 변수 이외의 기타 부수적인 변수를 말한다.

이제까기 살펴봤던 시계열 모델이 $\\{ Z(t) \\}$ 하나를 가지고 모델링 했다면, SARIMAX 모델은 외생변수 $\\{ E(t) \\}$를 함께 고려해 시계열 $\\{ Z(t) \\}$를 예측하는 모델이다.

<hr/>

# 맺음말

고전적인 시계열 분석과 모델링은 여기 SARIMAX 모델까지 알면 적당한 것 같다. 핵심이 되는 콘셉트는 모두 커버했기 때문에 이 정도면 충분하다고 생각한다.

아직 시계열 분석에 대한 많은 접근과 연구, 논문이 있기 때문에 최신 연구를 커버하려면 이 정도에서 다음 주제로 넘어가자 😉

# Reference

- [Forecasting: Principles and Practices: 계절성 ARIMA 모델들](https://otexts.com/fppkr/seasonal-arima.html)