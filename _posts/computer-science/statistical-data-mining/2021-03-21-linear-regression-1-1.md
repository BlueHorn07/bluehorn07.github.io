---
title: "Linear Regression - 1-1"
toc: true
toc_sticky: true
categories: ["Applied Statsitcs"]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>



<hr/>

<span class="statement-title">Goal.</span><br>

Regression의 목표는 아래와 같은 \<regression function\>을 추정하는 것에 있다.

$$
f(x) = E[Y \mid X = x]
$$

위의 관계식은 아래의 식과 동치다. 즉, 위의 함수 $f(x)$를 찾는 것이나 아래의 $f(x)$를 잘 찾으면 \<regression\>의 목표를 성취한 것으로 본다.

$$
Y = f(x) + \epsilon, \quad E[\epsilon \mid X] = 0
$$

\<linear regression\>을 달성하고 싶다면, \<regression function\> $f(x)$를 찾기 위해 $X$, $Y$의 관계식을 아래와 같이 모델링한다.

$$
\hat{Y} = \hat{\beta_0} + \sum^p_{j=1} \hat{\beta}_j X_j
$$

표기의 편의를 위해 \<intercept\> 또는 \<bias\> 텀을 포함해 아래와 같이 기술하기로 한다.

$$
\hat{Y} = \sum^p_{j=0} \hat{\beta}_j X_j = X^T \hat{\beta}
$$

<hr/>

### Least Squared Estimator

\<Linear regression\>의 해를 구하기 위해 **RSS**를 사용해 접근할 수 있다.

$$
\begin{aligned}
\text{RSS}(\beta) &= \sum^n_{i=1} \left( y_i - x_i^T \beta\right)^2 \\
    &= (\mathbf{y} - \mathbf{X}\beta)^T (\mathbf{y} - \mathbf{X}\beta)
\end{aligned}
$$

where $\mathbf{y} = (y_1, \dots, y_n)^T$ (response vector) and $\mathbf{X} = (\mathbf{x}_1, \dots, \mathbf{x}_p)$ (design matrix)

**RSS**에 대한 식을 $\beta$에 대해 미분하면 solution을 구할 수 있다. 정말 미분만 잘 하면 되기 때문에 실제 유도 과정은 여기서는 생략한다.

$$
\hat{\beta} = \underset{\beta \in \mathbb{R}^p}{\text{argmin}} \; \text{RSS}(\beta) = (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \mathbf{y}
$$

이것을 앞에서 언급한 $\hat{Y} = X^T \hat{\beta}$에 대입해주면 아래와 같다.

$$
\hat{Y} = X^T \hat{\beta} = \mathbf{X}^T (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \mathbf{y} = \left( \mathbf{X}^T (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \right) \mathbf{y} = \mathbf{H} \mathbf{y}
$$

이때의 $\mathbf{H}$를 \<hat matrix\>라고 부른다.

<hr/>

#### Design Matrix

\<design matrix\> $\mathbf{X}$에는 두 가지 타입이 있다.

(1) \<**Random Design**\>: $x_i$'s are regarded as i.i.d. realization

(2) \<**Fixed Design**\>: $x_i$'s are fixed (non-random)

두 개념이 \<regression estimation\>에는 큰 차이가 없다고 한다. 우리는 앞으로도 대부분의 경우에서 $\mathbf{X}$를 \<fixed design\>으로 취급할 것이다.

<hr/>

앞에서 RSS 방식을 사용해 $\hat{\beta}$를 구했다. 이때, 이 모델이 얼마나 좋은지를 논하기 위해 \<prediction error\>를 구해야 한다. 이때 필요한 개념이 \<**bias**\>와 \<**variance**\>이다. 이 두 개념에 무엇인지는 별도의 포스트에 정리해두었다. 만약 bias도 작고 variance도 작다면, 우리는 그 모델이 좋다고 평가한다.<br/>
👉 [bias & variance]({{"/2021/03/21/overview-of-supervised-learning-2.html#bias-variance-decomposition" | relative_url}})

$$
\text{Err}(x_0) = \sigma^2 + \left\{ \text{Bias}(\hat{f}(x_0)) \right\}^2 + \text{Var}(\hat{f}(x_0))
$$

$Y = X^T \beta + \epsilon$라고 가정하자.

만약, $\text{Var}(Y) = \text{Var}(\epsilon) = \sigma^2$라면,

$$
\begin{aligned}
\text{Var}(\hat{\beta}) &= \text{Var}\left( (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \mathbf{y} \right) \\
&= \left((\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \right) \text{Var}(\mathbf{y}) \left((\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \right)^T \quad (\because \text{Var}(A\mathbf{x}) = A \text{Var}(\mathbf{x})A^T) \\
&= (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \cdot \text{Var}(\mathbf{y}) \cdot X (X^TX)^{-1} \\
&= (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T \cdot \textcolor{red}{\sigma^2 I_n} \cdot X (X^TX)^{-1} \\
&= \sigma^2 (\mathbf{X}^T \mathbf{X})^{-1} \mathbf{X}^T X (X^TX)^{-1} \\
&= \sigma^2 (\mathbf{X}^T \mathbf{X})^{-1}
\end{aligned}
$$

위의 식에서 $X^TX$를 \<gram matrix\>라고 한다.

이번에는 bias를 살펴보자. $\hat{\beta}$의 평균인 $E[\hat{\beta}]$를 구해보자.

만약, $E[Y] = X^T \beta$라면,

$$
\begin{aligned}
E[\hat{\beta}] &= E\left[ (\mathbf{X}^T\mathbf{X})^{-1}\mathbf{X}^T \mathbf{y} \right] = (\mathbf{X}^T\mathbf{X})^{-1}\mathbf{X}^T E [\mathbf{y}] \\
&= (\mathbf{X}^T\mathbf{X})^{-1}\mathbf{X}^T (X \beta) = \beta
\end{aligned}
$$

<details class="math-statement" markdown="1">
<summary>$E[\mathbf{y}]$ 유도</summary>

$\mathbf{y} = (y_1, \dots, y_n)^T$에 대해 $E[\mathbf{y}]$는

$$
E[\mathbf{y}] = \begin{pmatrix}
    E[y_1] \\
    \vdots \\
    E[y_n]
\end{pmatrix} = \begin{pmatrix}
    x_1^T \beta \\
    \vdots \\
    x_n^T \beta
\end{pmatrix} = \mathbf{X} \beta
$$

</details>

$E[\hat{\beta}] = \beta$이기 때문에 ***unbiased estimator***라고 할 수 있다. 이것의 의미는 이 estimator의 성능이 평균적인 관점에서는 정말 잘 추정한다는 말이다.

종합하면, LS estimator는 bias의 경우 unbiased였다. 하지만, variance의 경우 행렬의 형태로 나왔다. 전체의 관점에서 봤을 때, LS estimator는 분산이 큰 편이기 때문에 아주 좋은 estimator는 아니라고 한다.

이번에는 estimator에서 오차에 대한 variance인 $\sigma^2$도 추정해보자.

$$
\hat{\sigma} = \frac{1}{n} \sum^n_{i=1} (y_i - \hat{y_i})^2 = \frac{1}{n} \sum^n_{i=1} (y_i - x_i \hat{\beta})^2
$$

그런데 여기에서 $n$이 아니라 $n-p$로 나두도록 한다.

$$
\hat{\sigma} = \frac{1}{n-p} \sum^n_{i=1} (y_i - x_i \hat{\beta})^2 = \frac{1}{n-p} \| \mathbf{y} - \hat{\mathbf{y}} \|
$$

이때, $(n-p)$는 \<자유도\>를 의미하는데, 이 부분은 솔직히 아직 잘 모르는 부분이라 자세한 설명은 생략한다.

일단 만약 저렇게 $\sigma^2$를 추정한다면, 이것이 ***unbiased estimaor*** 임을 유도할 수 있다고 한다.

$$
E[\hat{\sigma^2}] = \sigma^2
$$