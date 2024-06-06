---
title: "Overview of Supervised Learning - 1"
toc: true
toc_sticky: true
categories: ["Applied Statsitcs"]
---

2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

### Set-up

- **Input Variables**: $X = (X_1, \dots, X_p)^T$
  - a.k.a. Covariates, features, $p$-dim random vector, independent variables
- **Output Variables**: $Y$
  - a.k.a. Responses, $y$-values random variable, dependent variables
- **Data**: $\\{(y_1, x_1), \dots, (y_n, x_n)\\}$
  - Realization of $(X, Y)$ (often regarded as i.i.d. <small>*independent and identically distributed*</small>)

### Variable types

- **Qauntitive Variables**
  - Continuous variables
- **Qualitive Variables**
  - Discrete variables or Categorical Variables
- **Ordinal Variables**
  - ex: small < medium < large

<hr/>

- Two Supervised Learnings Tasks
  - **Regression**
    - Output $Y$ is <u>continuous</u>
    - Often, modeled as $Y = f(X) + \epsilon$
    - **Goal**: construct good model $f$ with low $\epsilon$
  - **Classification**
    - Output $Y$ is <u>categorical</u>
    - Often, we model $P(Y=k \mid X)$ (= 확률 추정)
    - **Goal**: construct good model to determine output value with given $X$.s

<hr/>

\<Supervised Learning\>에선 문제를 해결하기 위해, 아래의 두 가지 접근을 취한다.

- Least Squared Estimator
- Nearest Neighbor

이 두 접근법은 \<Supervised Learning\> 문제를 해결하는 가장 기본이 된다.

## Regression

<br><span class="statement-title">Definition.</span> Linear Model for regression<br>

Given input $X = (X_1, \dots, X_p)^T$, we predict $Y$ as

$$
\hat{Y} = \hat{\beta_0} + \sum^{p}_{i=1} {\hat{\beta_i} X_i}
$$

- 일반적으로 hat($\hat{x}$)이 있으면, predicted value로 취급한다.
- $\beta_i$는 'regression coefficient'라고 한다. 특히, $\beta_0$를 \<intercept\> 또는 \<bias\>라고 한다.
  - 편의를 위해 $X_0=1$라고 설정할 수도 있다.
  - 이 경우, 식은 $\hat{Y} = X^T \hat{\beta}$가 된다.

### Least Squared Estimator

그럴듯한 $\hat{\beta}$를 추정하기 위해 "residual sum of squares"를 최소화하는 **LSE**의 접근을 취할 수 있다.

$$
\begin{aligned}
  \mbox{RSS}(\beta) &= \sum^n_{i=1} (y_i - {x_i}^T \beta)^2 \\
  &= (\mathbf{y} - \mathbf{X}\beta)^T(\mathbf{y} - \mathbf{X}\beta)
\end{aligned}
$$

위 식에서 $\mathbf{y}$, $\mathbf{X}$는 각각 아래의 의미를 가진다.

- $\mathbf{y}$는 정답 레이블을 모은 vector로 \<response vector\>라고 한다.

$$
\mathbf{y} = \left( y_1, \dots, y_n \right)^T
$$

- $\mathbf{X}$는 입력되는 feature vector를 모은 matrix로 \<design matrix\>라고 한다.

$$
\mathbf{X} = \left( x_1, \dots, x_n \right)^T
$$

\<design matrix\>를 다르게 표현하면 아래와 같다.

$$
\mathbf{X} = \left( x_1, \dots, x_n \right)^T = \left( \mathbf{x}_1, \dots, \mathbf{x}_p \right)
$$

첫번째 표기는 $p$-dim feature vector $n$개를 차곡차곡 표현한 것이고, 두번째 표기는 $n$개 feature vector에서 feature $x_i$ 하나에 대한 값을 모두 모아 vector $\mathbf{x}_i$로 표현했다.

<br/>

LS 접근에서는 $\hat{\beta}$를 아래와 같이 추정한다.

$$
\begin{aligned}
\hat{\beta} &= \underset{\beta \in \mathbb{R}^p}{\text{argmin}} \; \mbox{RSS}(\beta) \\
&= \left( \mathbf{X}^T \mathbf{X}\right)^{-1} \mathbf{X}^T \cdot \mathbf{y}
\end{aligned}
$$

이것은 $\text{RSS}(\beta)$에 대한 미분으로 쉽게 유도할 수 있다.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\text{RSS}(\beta) &= \sum^n_{i=1} (y_i - x^T_i \beta)^2 \\
  &= (Y - X\beta)^T (Y - X\beta) \\
  &= (Y^T - \beta^T X^T) (Y - X \beta) \\
  &= Y^T Y - \beta^T X^T Y - Y^T X \beta + \beta^T X^T X \beta \\
  &= Y^T Y - 2 Y^T X \beta + \beta^T X^T X \beta
\end{aligned}
$$

이제 $\text{RSS}(\beta)$를 $\beta$에 대해 미분해보자.

$$
\begin{aligned}
\frac{\partial}{\partial\beta} \text{RSS}(\beta) &= \frac{\partial}{\partial\beta} \left(Y^T Y - 2 Y^T X \beta + \beta^T X^T X \beta \right) \\
&= 0 - 2 Y^T X + 2 X^T X \beta
\end{aligned}
$$

$\displaystyle \frac{\partial}{\partial\beta} \text{RSS}(\beta) $가 0이 되는 지점에서 극소값이 발생한다. 따라서,

$$
\begin{aligned}
\frac{\partial}{\partial\beta} \text{RSS}(\beta) &= - 2 Y^T X + 2 X^T X \beta = 0  \\
&\Updownarrow \\
2 X^TX \beta &= 2 Y^T X \\
&\Updownarrow \\
\hat{\beta} &= \left( X^T X \right)^{-1} Y^T X
\end{aligned}
$$

$\blacksquare$

</div>

위의 방법으로 구한 $\hat{\beta}$를 바탕으로 Linear Regressor $\hat{f}(x)$를 기술하면 아래와 같다.

$$
\hat{f}(x) = x^T \hat{\beta}
$$

### Nearest-Neighbor Methods

\<Nearest-Neighbor Method\>로 Regression 문제를 접근해볼 수도 있다.

<span class="statement-title">Definition.</span> Nearest-Neighbor Methods for regression<br>

Let $N_k(x)$ be the set of points which are the top-$k$ closest to $x$.

$$
\hat{f}(x) = \frac{1}{k} \sum_{x_i \in N_k(x)} y_i
$$

\<NN\>으로의 접근도 좋은 성능을 낸다. 그러나 Estimation의 경계 부근을 보면, \<NN\>의 경우 Boundary에서 Estimation 성능이 떨어지는 것을 볼 수 있다.

<hr/>

## Classification

\<분류 Classification\> 문제에서도 \<Least Squared Method\>와 \<Nearest Neighbor Method\>를 적용해볼 수 있다. \<분류 Classification\>에 대한 주제는 수업의 뒷부분에서 자세히 다루기 때문에 본 포스트에서는 간단하게 모형만 제시한다.


