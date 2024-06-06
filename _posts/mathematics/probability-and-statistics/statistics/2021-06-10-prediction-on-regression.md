---
title: "Prediction on Regression"
toc: true
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

우리는 이전 포스트 "[Test on Regression]({{"/2021/06/09/test-on-regression" | relative_url}})"에서 regression coefficient $B_1$, $B_0$의 분포를 확인했다. 이번 포스트에서는 이 결과를 종합해 우리가 regression 모델로부터 얻는 response의 분포를 추정해보는 과정을 진행한다.

개인적으로는 "$B_1$와 $B_0$이 estimated regression coefficient이기 때문에 모델로부터 얻는 response $y$ 역시 estimated response로 어느정도의 불확실성을 가지고 있다. 이를 $B_1$과 $B_0$의 불확실성을 모델링한 이들의 분포를 이용해 추정한다!"라고 이해했다.

우리는 mean response $\mu_{Y\mid x_0}$을 통해 모델이 뱉는 response의 불확실성을 추정할 것이며, 또 new data $X_0 = x_0$에 대해 수행하는 prediction의 불확실성을 추정할 것이다.

<hr/>

### Estimate on Mean Response

Supp. we have sample points $(x_1, y_1), \dots, (x_n, y_n)$ from $Y_i = \beta_0 + \beta_1 x_i + \epsilon_i$ where $\epsilon_i$s are iid $N(0, \sigma^2)$. Here, $\beta_0$ and $\beta_1$ are unknown parameters.

Q. Given data $x=x_0$, what can be the mean response $\mu_{Y\mid x_0}$?

이때, $x_0$는 sample point에서 유래하거나 미리 설정한 값이 아니라, varialbe $Y_0$의 값 $y_0$를 predict하는 용도의 값이다.

$$
\mu_{Y \mid x_0} = E[Y_0] = E[\beta_0 + \beta_1 x_0 + \epsilon_i] = \beta_0 + \beta_1 x_0 + \cancelto{0}{E[\epsilon_i]}
$$

그러나 우리는 $\beta_0$, $\beta_1$의 값을 모르기 때문에 샘플로부터 적당한 point estimator $\hat{Y}_0$를 정의할 것이다.

$$
\hat{Y}_0 = B_0 + B_1 x_0
$$

이제, $\hat{Y}_0$의 분포에 대해 살펴보자. 이때, $B_0$, $B_1$가 normal 분포이므로, $\hat{Y}_0$ 역시 normal 분포를 따른다.

<div class="math-statement" markdown="1">

1\. Mean

$$
\begin{aligned}
E[\hat{Y}_0]
&= E[B_0 + B_1 x_0] \\
&= \beta_0 + \beta_1 x_0 = \mu_{Y \mid x_0}
\end{aligned}
$$

이때 위의 사실을 통해 $\hat{Y}_0$가 unbiased estimator임도 알 수 있다!

</div>

<div class="math-statement" markdown="1">

2\. Variance

$$
\begin{aligned}
\text{Var}(\hat{Y}_0)
&= \text{Var}(\bar{y} + B_1 (x_0 - \bar{x})) \\
&= \text{Var}(\bar{y}) + \text{Var}(B_1 (x_0 - \bar{x})) + \text{Cov}(\bar{y}, B_1)
\end{aligned}
$$

이때, $\bar{y} \perp B_1$이므로, $\text{Cov}(\bar{y}, B_1) = 0$이 된다. (Homework 🎈)

따라서,

$$
\begin{aligned}
&= \text{Var}(\bar{y}) + \text{Var}(B_1 (x_0 - \bar{x})) + \cancelto{0}{\text{Cov}(\bar{y}, B_1)} \\
&= \frac{\sigma^2}{n} + (x_0 - \bar{x})^2 \cdot \text{Var}(B_1) \\
&= \frac{\sigma^2}{n} + (x_0 - \bar{x})^2 \cdot \frac{\sigma^2}{S_{xx}} \\
&= \sigma^2 \left( \frac{1}{n} + \frac{(x_0 - \bar{x})^2}{S_{xx}}\right)
\end{aligned}
$$

</div>

따라서, $\hat{Y}_0$의 분포는 아래와 같다.

$$
\hat{Y}_0 \sim N \left( \mu_{Y \mid x_0}, \; \sigma^2 \left( \frac{1}{n} + \frac{(x_0 - \bar{x})^2}{S_{xx}}\right) \right)
$$

이때 error variance $\sigma^2$의 값을 모르므로, sample error variance $s^2$를 사용하면,

$$
\frac{\hat{Y}_0 - \mu_{Y \mid x_0}}{s \sqrt{\dfrac{1}{n} + \dfrac{(x_0 - \bar{x})^2}{S_{xx}}}} \sim t(n-2)
$$

이에 위의 분포를 사용해, data $x_0$에 대한 mean response $\mu_{Y \mid x_0}$의 "confidence interval"을 구할 수 있다! 😆

<hr/>

### Prediction Interval

앞에서 구한 "mean response $\mu_{Y \mid x_0}$"는 우리에게 $x=x_0$라는 값이 주어졌을 때 모델의 불확실성을 추정하는 과정이었다. 이번에는 모델에 new data $X_0 = x_0$가 주어졌을 때, 이에 대한 prediction의 불확실성을 추정하는 과정을 수행한다. 이것은 $X_0$의 response $Y_0$가 기존의 $Y_i$와 independent 하기 때문에 - 심지어 $x_0 = x_i$ 일지라도 $Y_0 \perp Y_i$이다 - 앞의 "mean response"와는 다르게 접근해야 한다!

$Y_0$는 $Y_0 = \beta_0 + \beta_1 x_0 + \epsilon_0$ where $\epsilon_0 \sim N(0, \sigma^2)$ and iid.

따라서, $Y_0$의 분포는 아래와 같다.

$$
Y_0 \sim N(\beta_0 + \beta_1 x_0, \; \sigma^2)
$$

이때, $Y_0 \perp Y_i$이고, 마찬가지로 $Y_0 \perp \hat{Y}_0$이다.

이때, $\hat{Y}_0$에 대한 분포는 위에서 구한 적이 있다. 그대로 사용하면,

$$
\hat{Y}_0 \sim N \left( \beta_0 + \beta_1 x_0, \; \sigma^2 \left( \frac{1}{n} + \frac{(x_0 - \bar{x})^2}{S_{xx}}\right) \right)
$$

이때 $Y_0$는 $\hat{Y}_0$와 독립이므로 아래가 성립한다.

$$
Y_0 - \hat{Y}_0 \sim N \left( 0, \; \sigma^2 \left( 1 + \frac{1}{n} + \frac{(x_0 - \bar{x})^2}{S_{xx}}\right) \right)
$$

이때 error variance $\sigma^2$의 값을 모르므로, sample error variance $s^2$를 사용하면,

$$
\frac{Y_0 - \hat{Y}_0}{s \sqrt{1 + \dfrac{1}{n} + \dfrac{(x_0 - \bar{x})^2}{S_{xx}}}} \sim t(n-2)
$$

<hr/>

이때, 주목할 점은 일반적으로 "response interval"이 "prediction interval"보다 더 좁다는 것이다. 개인적으로 해석해보자면, "prediction interval"의 경우, 새롭게 추가되는 data $X_0$이 기존의 데이터와 독립이기 때문에 이런 차이가 발생하는 것 같다. 또, 애초에 "response interval"과 "prediction interval"은 추정의 대상 자체가 다르다! 😁

본인 말고도 두 개념이 헷갈리는 사람이 많은 것 같아. 구글에 검색해보니 둘을 비교하는 포스트가 꽤 있었다. 아래는 그 중에서 둘을 한 문장을 비교한 문구를 가져온 것이다.

<div class="notice" markdown="1">

A **mean response interval** is a confidence interval for the mean of all Y’s at a given X value.

A **prediction interval** is a prediction interval for one single Y at a given X value.

-- from a [post of 'Carsten Grube'](https://dataz4s.com/statistics/mean-single-response-intervals/)

</div>

<hr/>

이것으로 "확률과 통계(MATH230)"의 정규수업에서 다룬 모든 내용을 살펴봤다!! 😁