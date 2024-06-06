---
title: "Statistics - PS3"
toc: true
toc_sticky: true
categories: ["Statistics", "Problem Solving"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

이 글은 "[Test on Regression]({{"/2021/06/09/test-on-regression" | relative_url}})" 포스트에서 제시한 숙제들을 풀이한 포스트입니다.

<span class="statement-title">TOC.</span><br>

- Variance of estimator $B_0$
- unbiased estimator of $\sigma^2$ is $s^2$
- (not yet) $s^2 \perp B_1$, and $s^2 \perp B_0$
- (not yet) the distribution of $s^2$

<hr/>

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

$$
B_0 = \bar{y} - B_1 \bar{x}
$$

The variance of $B_0$, $\text{Var}(B_0)$ is

$$
\text{Var}(B_0) = \frac{\sum_{i=1}^n x_i^2}{n S_{xx}} \cdot \sigma^2
$$

</div>

<div class="notice" markdown="1">

<span class="statement-title">*proof*.</span><br>

$$
\begin{aligned}
\text{Var}(B_0)
&= \text{Var}(\bar{y} - B_1 \bar{x}) \\
&= \text{Var}(\bar{y}) + (\bar{x})^2 \cdot \text{Var}(B_1) - 2 \bar{x} \cdot \text{Cov}(\bar{y}, B_1)
\end{aligned}
$$

(1) $\text{Var}(\bar{y})$

$$
\begin{aligned}
\text{Var}(\bar{y})
&= \text{Var} \left( \frac{1}{n} \sum_{i=1}^n y_i\right) \\
&= \frac{1}{n^2} \sum_{i=1}^n \text{Var}(y_i) \\
&= \frac{1}{n^2} \cdot n \sigma^2 = \frac{\sigma^2}{n}
\end{aligned}
$$

(2) $\text{Var}(B_1)$

We already know that the variance of $B_1$ is

$$
\text{Var}(B_1) = \frac{\sigma^2}{S_{xx}}
$$

(3) $\text{Cov}(\bar{y}, B_1)$

$$
\begin{aligned}
\text{Cov}(\bar{y}, B_1)
&= \text{Cov} \left( \frac{1}{n} \sum y_i, \frac{S_{xy}}{S_{xx}}\right) \\
&= \frac{1}{n} \frac{1}{S_{xx}} \cdot \text{Cov}(\sum y_i, S_{xy}) \\
&= \frac{1}{n S_{xx}} \cancelto{0}{\sum (x_i - \bar{x})} \cdot \text{Cov}(\sum y_i, \sum y_i) \\
&= 0
\end{aligned}
$$

이제 위의 결과를 종합하면,

$$
\begin{aligned}
&= \text{Var}(\bar{y}) + (\bar{x})^2 \cdot \text{Var}(B_1) - 2 \bar{x} \cdot \text{Cov}(\bar{y}, B_1) \\
&= \frac{\sigma^2}{n} + (\bar{x})^2 \cdot \frac{\sigma^2}{S_{xx}} - 2 \bar{x} \cdot 0 \\
&= \frac{\sigma^2}{n} + (\bar{x})^2 \cdot \frac{\sigma^2}{S_{xx}} \\
&= \sigma^2 \left( \frac{1}{n} + \frac{(\bar{x})^2}{S_{xx}}\right) \\
&= \sigma^2 \left( \frac{S_{xx} + n (\bar{x})^2}{n S_{xx}} \right) \\
&= \sigma^2 \left( \frac{\sum(x_i - \bar{x})x_i + n (\bar{x})^2}{n S_{xx}} \right) \\
&= \sigma^2 \left( \frac{\sum x_i^2 - \bar{x} \sum x_i + n (\bar{x})^2}{n S_{xx}} \right) \\
&= \sigma^2 \left( \frac{\sum x_i^2 - \cancel{\bar{x} n (\bar{x})} + \cancel{n (\bar{x})^2}}{n S_{xx}} \right) \\
&= \sigma^2 \cdot \frac{\sum x_i^2}{n S_{xx}}
\end{aligned}
$$

$\blacksquare$

</div>


<hr/>

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

The unbiased estimator of $\sigma^2$ is

$$
s^2 := \frac{\sum_{i=1}^n (y_i - \hat{y}_i)^2}{n-2} = \frac{\text{SSE}}{n-2}
$$

</div>


<div class="notice" markdown="1">

<span class="statement-title">*proof*.</span><br>

First, note that the regression equation is

$$
\hat{y}_i = b_0 + b_1 x_i
$$

Then, the residual squared sum is

$$
\begin{aligned}
\sum_{i=1}^n (y_i - \hat{y}_i)^2
&= \sum_{i=1}^n (y_i - b_0 - b_1 x_i)^2 \\
&= \sum_{i=1}^n (y_i - (\bar{y} - b_1 \bar{x}) - b_1 x_i)^2 \\
&= \sum_{i=1}^n ((y_i - \bar{y}) - b_1(x_i - \bar{x}))^2 \\
&= \sum_{i=1}^n (y_i - \bar{y})^2 - 2b_1 \sum_{i=1}^n (y_i - \bar{y})(x_i - \bar{x}) + b_1^2 \sum_{i=1}^n (x_i - \bar{x})^2 \\
\end{aligned}
$$

이때, $b_1 = S_{xy} / S_{xx}$이므로, 식을 정리하면,

$$
\begin{aligned}
&= \sum_{i=1}^n (y_i - \bar{y})^2 - 2b_1 \sum_{i=1}^n (y_i - \bar{y})(x_i - \bar{x}) + b_1^2 \sum_{i=1}^n (x_i - \bar{x})^2 \\
&= \sum_{i=1}^n (y_i - \bar{y})^2 - 2b_1 S_{xy} + b_1^2 S_{xx} \\
&= \sum_{i=1}^n (y_i - \bar{y})^2 - 2b_1 (b_1 S_{xx}) + b_1^2 S_{xx} \\
&= \sum_{i=1}^n (y_i - \bar{y})^2 - b_1^2 S_{xx} \\
&= \sum_{i=1}^n (y_i^2 - 2 y_i \bar{y} + \bar{y}^2) - b_1^2 S_{xx} \\
&= \left(\sum_{i=1}^n y_i^2\right) - 2 n \bar{y}^2 + n \bar{y}^2 - b_1^2 S_{xx} \\
&= \left(\sum_{i=1}^n y_i^2\right) - n \bar{y}^2 - b_1^2 S_{xx} \\
\end{aligned}
$$

이제 위의 식에 평균을 취해보자.

$$
E \left[ \left(\sum_{i=1}^n y_i^2\right) - n \bar{y}^2 - b_1^2 S_{xx} \right]
= \left(\sum_{i=1}^n E[y_i^2] \right) - n E [\bar{y}^2] - S_{xx} E[b_1^2]
$$

이때, 각 변수의 평균값은 아래와 같이 구할 수 있다.

$$
\begin{aligned}
E[y_i^2]
&= \text{Var}(y_i) + (E[y_i])^2 \\
&= \sigma^2 + (\beta_0 + \beta_1 x_i)^2
\end{aligned}
$$

$$
\begin{aligned}
E[\bar{y}^2]
&= \text{Var}(\bar{y}) + (E[\bar{y}])^2 \\
&= \frac{\sigma^2}{n} + (\beta_0 + \beta_1 \bar{x})^2
\end{aligned}
$$

$$
\begin{aligned}
E[b_1^2]
&= \text{Var}(b_1) + (E[b_1])^2 \\
&= \frac{\sigma^2}{S_{xx}} + \beta_1^2
\end{aligned}
$$

식을 대입하면,

$$
\begin{aligned}
&= \left(\sum_{i=1}^n E[y_i^2] \right) - n E [\bar{y}^2] - S_{xx} E[b_1^2] \\
&= \left(\sum_{i=1}^n (\sigma^2 + (\beta_0 + \beta_1 x_i)^2) \right) - n \left( \frac{\sigma^2}{n} + (\beta_0 + \beta_1 \bar{x})^2 \right) - S_{xx} \left( \frac{\sigma^2}{S_{xx}} + \beta_1^2 \right) \\
&= n \sigma^2 + \left(\sum_{i=1}^n (\beta_0 + \beta_1 x_i)^2 \right) - (\sigma^2 + n (\beta_0 + \beta_1 \bar{x})^2) - (\sigma^2 + S_{xx} \beta_1^2) \\
&= (n-2) \sigma^2 - n (\beta_0 + \beta_1 \bar{x})^2 - S_{xx} \beta_1^2 + \left(\sum_{i=1}^n (\beta_0 + \beta_1 x_i)^2 \right) \\
&= (n-2) \sigma^2 - n (\beta_0^2 + 2 \beta_0 \beta_1 \bar{x} + \beta_1^2 \bar{x}^2) - S_{xx} \beta_1^2 + \left(\sum_{i=1}^n (\beta_0^2 + 2 \beta_0 \beta_1 x_i + \beta_1^2 x_i^2) \right) \\
&= (n-2) \sigma^2 - n (\cancel{\beta_0} + 2 \beta_0 \beta_1 \bar{x} + \beta_1^2 \bar{x}^2) - S_{xx} \beta_1^2 + \left( \cancel{n \beta_0^2} + 2 \beta_0 \beta_1 \sum_{i=1}^n x_i + \beta_1^2 \sum_{i=1}^n x_i^2 \right) \\
&= (n-2) \sigma^2 - n (\cancel{2 \beta_0 \beta_1 \bar{x}} + \beta_1^2 \bar{x}^2) - S_{xx} \beta_1^2 + \left(\cancel{2 \beta_0 \beta_1 \sum_{i=1}^n x_i} + \beta_1^2 \sum_{i=1}^n x_i^2 \right) \\
&= (n-2) \sigma^2 - n \beta_1^2 \bar{x}^2 - S_{xx} \beta_1^2 + \beta_1^2 \sum_{i=1}^n x_i^2 \\
&= (n-2) \sigma^2 - S_{xx} \beta_1^2 + \left( \beta_1^2 \sum_{i=1}^n x_i^2 - n \beta_1^2 \bar{x}^2 \right)\\
&= (n-2) \sigma^2 - \cancel{S_{xx} \beta_1^2} + \left( \cancel{\beta_1^2 S_{xx}} \right)\\
&= (n-2) \sigma^2
\end{aligned}
$$

자! 이제 estimator $s^2$에 평균을 취해보자!

$$
\begin{aligned}
E[s^2]
&= E\left[\frac{\sum_{i=1}^n (y_i - \bar{y})^2}{n-2}\right] \\
&= \frac{1}{n-2} E \left[ \sum_{i=1}^n (y_i - \bar{y})^2 \right] \\
&= \frac{1}{n-2} \cdot (n-2) \sigma^2 \\
&= \sigma^2
\end{aligned}
$$

따라서, $s^2$는 unbiased estimator다! $\blacksquare$

</div>


<hr/>

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

$s^2 \perp B_1$, and $s^2 \perp B_0$

</div>

<div class="notice" markdown="1">

<span class="statement-title">*proof*.</span><br>

(좀더 공부 후, 작성 예정)

</div>


<hr/>

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span><br>

$$
\frac{(n-2)S^2}{\sigma^2} \sim \chi^2 (n-2)
$$

</div>

<div class="notice" markdown="1">

<span class="statement-title">*proof*.</span><br>

(좀더 공부 후, 작성 예정)

</div>