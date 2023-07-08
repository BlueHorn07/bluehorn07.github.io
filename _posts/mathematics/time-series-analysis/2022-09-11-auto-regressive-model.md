---
title: "Auto-Regressive Model"
toc: true
toc_sticky: true
categories: ["Time Series Analysis"]
---

# 과거가 미래를 예측한다

\<Auto-Regressiave Model\>, "Auto"가 붙은 것에서 알 수 있듯, 시계열 $\\{ X(t) \\}$에서 과거 자신의 값인 $X(t-1)$, $X(t-2)$를 사용하는 모델이다. **"과거가 미래를 예측한다"**를 모델링 한 것이다.식은 아래와 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Auto-Regressive Model<br>

$$
X(t) = \phi_0 + \phi_1 X(t-1) + \phi_2 X(t-2) + \cdots + \phi_p X(t-p) + \epsilon(t)
$$

</div>

Multiple Regression Model이지만, 자신의 과거 값을 사용하기 때문에 "Auto"라는 표현이 붙었다.

Hyper-parameter는 몇개의 Lag를 쓸 것인지에 대한 $p$ 값이다. 이를 기준으로 $p$차 AR 모델은

$$
\text{AR}(p)
$$

라고 표현한다.

# AR(1): 1차 AR 모델

$$
X(t) = \phi_0 + \phi_1 X(t-1) + \epsilon(t)
$$

위의 수식에서 $\phi_0$, $\phi_1$의 값에 따라 다양한 모델들이 파생되는데,

<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> White Noise Model<br>

$\phi_0 = 0$ and $\phi_1 = 0$

$$
X(t) = \epsilon(t)
$$

과거의 값으로 현재의 값을 예측할 수 없다.

</div>

<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> Random Walk Model<br>

$\phi_0 = 0$ and $\phi_1 = 1$

$$
X(t) = X(t-1) + \epsilon(t)
$$

오늘의 값 $X(t)$는 어제의 값 $X(t-1)$과 예측할 수 없는 변량 $\epsilon(t)$로 결정된다. 어쩌면 어제의 값이 오늘의 값을 예측하는데, 베스트 값이다.

[\<Markove Process\>]({{"/2021/07/03/Markov-process.html" | relative_url}})는 확률이 직전 상태에만 의존하는 확률 과정이다.

$$
p(S_{t+1} \mid S_0, S_1, \dots, S_t) = p(S_{t+1} \mid S_t)
$$

따라서 AR(1)은 \<Markov Property\>를 만족한다고 할 수 있다.

</div>

<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> Random Walk Model with drift<br>

$\phi_0 \ne 0$ and $\phi_1 = 1$

$$
X(t) = \phi_0 + X(t-1) + \epsilon(t)
$$

Drift $\phi_0$가 존재하는 경우임. 시계열이 뚜렷한 추세를 가지고 있음.

</div>

<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> AR(1) with Stationarity<br>

$\left\| \phi_1 \right\| < 1$

$$
X(t) = \phi_0 + \phi_1 X(t-1) + \epsilon(t)
$$

만약 $\left\| \phi_1 \right\| < 1$라면, 시계열은 정상성을 가진다.

</div>

# AR model with Stationarity

AR(1)의 마지막에서 $\left\| \phi_1 \right\| < 1$라면, 시계열이 정상성을 가진다고 했다. 해당 경우를 좀더 살펴보자.

만약, $\left\| \phi_1 \right\| > 1$라고 해보자. 구체적으론 $\phi_1 = 2$라고 해보자. 그러면, 매스텝마다 $X(t)$의 값은 2배씩 늘어날 것이다: 2 → 4 → 8 → 16 ... 그러면, 시계열에 증가 추세(T)가 있는 것이기에 시계열이 정상성을 띄지 않게 된다.

<div class="img-wrapper">
  <img src="https://otexts.com/fppkr/fpp_files/figure-html/arp-1.png" width="640px">
  <p markdown="1">
    [Forecasting: Principles and Practices: 자귀회귀 모델](https://otexts.com/fppkr/AR.html)
  </p>
  <p arkdown="1">
    두 모델 다 $\left| \phi_1 \right| < 1$를 만족한다.
  </p>
</div>

AR(1)이 $\left\| \phi_1 \right\| < 1$라면 어떻게 되겠는가? 시계열이 시계열의 평균을 중심으로 진동할 것이다. 위의 그림이 이를 잘 표현하고 있다.

<br/>

그럼 정상성을 가진 AR(1)이 정말로 정상성을 가진 건지 정상성 조건을 체크해보자.

만약, AR(1) 모델의 시계열 $Z(t)$이 정상성을 가진다면, 그때의 분산은 언제든 동일할 것이다.

$$
\text{Var}(Z(t)) = \text{Var}(Z(t-1))
$$

이를 이용해 AR(1)의 수식에 분산을 유도해보자.

$$
\begin{aligned}
  \sigma_Z^2
  &= \text{Var}(Z(t)) \\
  &= \phi_1^2 \cdot \text{Var}(Z(t)) + \text{Var}(\epsilon(t)) \\
  &= \phi_1^2 \cdot \sigma_Z^2 + \sigma^2 \\
\end{aligned}
$$

이제 식을 정리하면 아래와 같다.

$$
\sigma_Z^2 = \frac{\sigma^2}{1 - \phi_1^2}
$$

이때, $Z(t)$의 분산 $\sigma_Z^2$는 양수여야 하므로, 분모의 $1 - \phi_1^2 > 0$여야 한다. 따라서

$$
\phi_1^2 < 1
$$

여야 정상성을 만족한다!

<br/>

단, $\phi_1^2 < 1$ 조건은 AR(1) 모델에서의 정상성 조건이다. 다른 차수 $p$의 AR 모델에서는 조건이 다르다.

- AR(1) 모델
  - $-1 < \phi_1 < 1$
- AR(2) 모델
  - $-1 < \phi_2 < 1$
  - $\phi_1 + \phi_2 < 1$
  - $\phi_2 - \phi_1 < 1$

# Reference

- [Wikipedia: Auto-regressive Model](https://en.wikipedia.org/wiki/Autoregressive_model)
- [Forecasting: Principles and Practices: 자기회귀 모델](https://otexts.com/fppkr/AR.html)

