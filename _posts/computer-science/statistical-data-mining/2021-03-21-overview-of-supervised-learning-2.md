---
title: "Overview of Supervised Learning - 2"
layout: post
use_math: true
tags: ["Statistical Data Mining"]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- The Curse of Dimentionality
- Bias-Variance Decomposition

<hr/>

## The Curse of Dimentionality

(준비중)

<hr/>

## Bias-Variance Decomposition

앞서 살펴본 NN 기법에선 어떤 $k$ 값을 선택하는지에 따라 Test Error의 값이 달라졌다. 본 파트에서는 $k$를 어떻게 선택해야 하는지에 대해 \<bias\>, \<variancce\> 개념을 통해 설명한다.

먼저 우리는 $k$ 값에 따른 prediction error $\text{EPE}(\hat{f}_k)$를 아래와 같이 기술할 수 있다.

$$
\text{EPE}(\hat{f}_k) = E_{X, Y} \left[Y - \hat{f}(X)\right]^2
$$

where $\hat{f}_k$ is the $k$-NN estimator.

$\text{EPE}$를 줄이기 위한 기준으로 training error를 사용할 순 없다.

$$
\sum^n_{i=1} \left(y_i - \hat{f}(x_i) \right)^2
$$

일반적으로 complexity가 높일 수록 Test Erorr는 감소한다. 하지만, complexity가 일정 수준의 이상으로 높아진다면, Test Error는 다시 증가하기 시작한다: NN 기법에서는 $k$가 작아질수록 complexity가 증가한다. 이것은 모든 모델에서 **일반적으로 발견되는 현상**이다. 그리고 이런 현상은 \<bias-variance decomposition\>이라는 이론으로 설명 가능하다!

<span class="statement-title">Properity.</span> Bias-Variance trade-off (for regression)<br>

아래와 같은 regression 문제를 상정해보자.

$$
Y = f(X) + \epsilon, \quad \text{where } E(\epsilon) = 0, \quad \text{Var}(\epsilon) = \sigma^2
$$

그리고 $f$를 추정한 estimator를 $\hat{f}$라고 해보자. 그러면, $x_0$라는 새로운 sample point에 대해 $\hat{f}$는 $\text{Err}(x_0)$의 Test Error를 갖는다.

$$
\begin{aligned}
    \text{Err}(x_0) \overset{\text{def}}{=} E\left[ (Y - \hat{f}(X))^2 \mid X = x_0 \right]
\end{aligned}
$$

이  $\text{Err}(x_0)$에 대한 위의 식을 잘 전개하면 아래의 결과를 얻는다.

$$
\begin{aligned}
    \text{Err}(x_0) &= E\left[ (Y - \hat{f}(X))^2 \mid X = x_0 \right] \\
    &= E \left[ \left( Y - \textcolor{red}{f(X) + f(X) - E[\hat{f}(X)] + E[\hat{f}(X)]} - \hat{f}(X)\right)^2 \mid X = x_0 \right] \\
    &= E \left[ (Y - f(X))^2 + (f(X) - E[\hat{f}(X)])^2 + (E[\hat{f}(X)] - \hat{f}(X))^2 + \textcolor{red}{\cancel{2 \cdots}} \mid X = x_0 \right]
\end{aligned}
$$

마지막 줄의 $\textcolor{red}{\cancel{2 \cdots}}$ 부분은 잘 전개해보면 모두 0이 된다. 이것은 아래의 **펼쳐보기** 통해 확인할 수 있다.

<details class="math-statement" markdown="1">
<summary>펼쳐보기</summary>

1\. $E\left[ (Y - f(X)) \cdot (f(X) - E [\hat{f}(X)]) \mid X = x_0 \right]$

위의 식에서 $(Y-f(X))$와 $(f(x) - E [\hat{f}(X)])$ 서로 독립이다. 따라서 $(Y-f(X))$에 대한 텀을 분리할 수 있다. 이때, $(Y-f(X))$에 대해 평균을 취하면, 그 값은 0이다. 따라서 첫번째 텀의 값은 0이다.

2\. $E\left[ (Y - f(X)) \cdot (E [\hat{f}(X)] - \hat{f}(X)) \mid X = x_0 \right]$

1번과 마찬가지로 독립에 의해 $(Y-f(X))$을 분리할 수 있고, 평균을 취하면 0이 되어서 두번째 텀의 값은 0이다.

3\. $E\left[ (f(X) - E [\hat{f}(X)]) \cdot (E [\hat{f}(X)] - \hat{f}(X)) \mid X = x_0 \right]$

위의 식을 전개하면 아래와 같다. 이때, $E[\hat{f}(X)]$가 상수임을 기억하라.

$$
\begin{aligned}
& E\left[ (f(X) - \mu_{\hat{f}(X)}) \cdot (\mu_{\hat{f}(X)} - \hat{f}(X)) \mid X = x_0 \right] \\
&= E\left[ f(X) \mu_{\hat{f}(X)} - \left(\mu_{\hat{f}(X)}\right)^2 - f(X) \hat{f}(X) + \mu_{\hat{f}(X)} \hat{f}(X) \mid X = x_0 \right] \\
&= \mu_{\hat{f}(X)} E[f(X)] - \left(\mu_{\hat{f}(X)}\right)^2 - E[f(X)\hat{f}(X)] + \mu_{\hat{f}(X)} E[\hat{f}(X)] \\
&= \cancel{\left( \mu_{\hat{f}(X)} E[f(X)] - E[f(X)\hat{f}(X)] \right)} + \cancel{\left( - \left(\mu_{\hat{f}(X)}\right)^2 + \mu_{\hat{f}(X)} E[\hat{f}(X)]\right)} = 0
\end{aligned}
$$

</details>

위의 식의 각각의 텀들을 확인해보자.

1\. $E[(Y - f(X))^2]$

첫번째로 $E[(Y - f(X))^2]$텀은 $Y = f(X) + \epsilon$의 관계식을 이용하면, $E[\epsilon^2]$을 구하는 것과 같다. 이때, $E(\epsilon) = 0$, $\text{Var}(\epsilon) = \sigma^2$이므로 

$$
E[\epsilon^2] = \sigma^2
$$

2\. $E[((f(X) - E[\hat{f}(X)])^2]$

이 $E[((f(X) - E[\hat{f}(X)])^2]$텀을 우리는 \<bias\>라고 부른다. 만약 추정한 estimaor $\hat{f}(x_0)$에 대해 그것의 평균인 $E[\hat{f}(x_0)]$의 값이 $f(x_0)$와 같다면, 즉

$$
f(x_0) = E[\hat{f}(x_0)]
$$

라면, 우리는 estimator $\hat{f}$를 ***unbaised*** 되었다고 말한다. 좀더 간단히 말하면, 실제값과 평균값의 차이를 \<bias\>라고 생각하면 된다. 따라서 두번째 텀은 

$$
E \left[ \left(f(x_0) - E[\hat{f}(x_0)] \right)^2 \right] = \left\{ \text{Bias}(\hat{f}(x_0)) \right\}^2
$$

3\. $E[(E[\hat{f}(X)] - \hat{f}(X))^2]$

이것은 비교적 익숙하다. 바로 $\hat{f}(x_0)$의 \<variance\>에 대한 식으로 세번째 텀은 곧

$$
E \left[ \left( E[\hat{f}(x_0)] - \hat{f}(x_0) \right)^2  \right] = \text{Var}(\hat{f}(x_0))
$$

이것을 종합하면 아래와 같다.

$$
\begin{aligned}
    \text{Err}(x_0) &= E \left[ (Y - f(X))^2 + (f(X) - E[\hat{f}(X)])^2 + (E[\hat{f}(X)] - \hat{f}(X))^2 + \textcolor{red}{\cancel{2 \cdots}} \mid X = x_0 \right] \\
    &= \sigma^2 + \left\{ \text{Bias}(\hat{f}(x_0)) \right\}^2 + \text{Var}(\hat{f}(x_0))
\end{aligned}
$$

위의 식에서 $\sigma^2$은 자료로부터 오는 uncertainty이다. 그래서 자료를 아무리 많이 준비한다고 하더라고 이 $\sigma^2$의 값은 줄일 수도, 컨트롤 할 수도 없다.

그러나 Bias 텀과 Variance 텀은 모델링 하는 사람에 의해 컨트롤 할 수 있다. 이것을 **complexity**를 이용해 컨트롤하는데, 일반적인 경향에 따르면 complexity가 크면 클수록 Bias는 줄어들고, Variance를 커진다고 한다.

NN에서의 경우를 떠올려보자. $k$가 커지면 커질수록 모델은 local neighbor를 보는 것이 아니라 global neighbor, 즉 global mean을 따르게 된다. 이것은 sample point에서 굉장히 멀리있는 녀석들까지 가져와서 본다는 말이므로, 곧 Bais가 증가함을 의미한다. 그러나 굉장히 멀리 있는 녀석들도 살펴보기 때문에 Variance는 줄어들 것이다.

이 **model complexity에 따른 Bias-Variance의 경향은 statistical model 전반에 발현되는 일반적인 현상**이다!