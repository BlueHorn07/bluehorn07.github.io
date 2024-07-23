---
title: "Mean, Variance, and Covariance"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

## Mean

<br><span class="statement-title">Definition.</span><br>

The \<**expectation**\> or \<**mean**\> of a RV $X$ is defined as

$$
\mu := E[x] := \begin{cases}
    \displaystyle \sum_x x f(x) && X \; \text{is a discrete with pmf} f(x) \; \\
    \displaystyle \int^{\infty}_{\infty} x f(x) dx  && X \; \text{is a continuous with pdf} \; f(x)
\end{cases}
$$

만약 RV $X$에 함수 $g(x)$를 취한다면, \<Expectation\>은 아래와 같이 구할 수 있다.

<span class="statement-title">Theorem.</span><br>

Let $X$ be a random variable with probability distribution $f(x)$. The expected value of the random variable $g(X)$ is

$$
\mu_{g(X)} = E\left[g(X)\right] = \sum_x g(x) f(x) \quad \text{if } X \text{ is discrete RV}
$$

and

$$
\mu_{g(X)} = E\left[g(X)\right] = \int^{\infty}_{\infty} g(x) f(x) \quad \text{if } X \text{ is continuous RV}
$$

<small>($g(x)$를 취하도 여전히 $x$의 정의역은 유지되므로, 위와 같이 $g(x) f(x)$를 사용하는 것은 타당하다.)</small>

ps) 수업 시간에 교수님께서 이산 RV에 대한 증명은 쉽게 할 수 있지만, 연속 RV에 대한 증명은 좀 까다롭다고 하셨다.

<br/>

이번에는 joint distributions에 대한 \<Expectation\>을 살펴보자.

<span class="statement-title">Definition.</span><br>

Let $X$ and $Y$ be RVs with joint probability distribution $f(x, y)$. The expected value of the RV $g(X, Y)$ is

$$
\mu_{g(X, Y)} = E\left[g(X, Y)\right] = \sum_x \sum_y g(x, y) f(x, y) \quad \text{if } X \text{ and } Y \text{ is discrete RV}
$$

$$
\mu_{g(X, Y)} = E\left[g(X, Y)\right] = \int^{\infty}_{-\infty} \int^{\infty}_{-\infty} g(x, y) f(x, y) \; dx dy \quad \text{if } X \text{ and } Y \text{ is continuous RV}
$$

<br/>

Conditional Distribution에 대해서도 \<Expectation\>을 생각해볼 수 있다.

<span class="statement-title">Definition.</span><br>

$$
E\left[ X \mid Y = y \right] = \begin{cases}
    \displaystyle \sum_x x f(x \mid y) && X \; \text{is a discrete with joint pmf} f(x, y) \; \\
    \displaystyle \int^{\infty}_{\infty} x f(x \mid y) \; dx  && X \; \text{is a continuous with joint pdf} \; f(x, y)
\end{cases}
$$

### Linearity of Expectation

\<Expectation\>은 \<**Linearity**\>라는 아주 좋은 성질을 가진다.

<span class="statement-title">Theorem.</span><br>

Let $a, b \in \mathbb{R}$, then $E\left[aX + b\right] = aE[X] + b$.

위의 정리가 말해주는 것은 \<Expectation\>이 **Linear Operator**임을 말해준다!! 🤩

좀더 확장해서 기술해보면,

<span class="statement-title">Theorem.</span><br>

$$
E\left[g(X) + h(X)\right] = E\left[g(X)\right] + E\left[h(X)\right]
$$

<br><span class="statement-title">Theorem.</span><br>

$$
E\left[g(X, Y) + h(X, Y)\right] = E\left[g(X, Y)\right] + E\left[h(X, Y)\right]
$$

### Expectation with Independence

만약 두 RV $X$, $Y$가 서로 \<독립\>이라면, 두 RV의 곱에 대한 \<Expectation\>을 쉽게 구할 수 있다.

<span class="statement-title">Theorem.</span><br>

If $X$ and $Y$ are independent, then

$$
E[XY] = E[X]E[Y]
$$

<hr/>

## Variance and Covariance

두 RV $X$, $Y$가 동일한 평균을 가지더라도; $E[X] = \mu = E[Y]$ RV의 개별 값들이 평균 $\mu$로부터 떨어져 있는 정도는 다를 수 있다. \<분산 Variance\>는 이런 평균으로부터의 퍼진 정도를 측정하는 지표로 아래와 같이 정의한다.

<br><span class="statement-title">Definition.</span><br>

The \<**variance**\> of a RV $X$ is defined as

$$
\text{Var}(X) = E[(X-\mu)^2]
$$

and $\sigma = \sqrt{\text{Var}(X)}$ is called the \<**standard deviation**\> of $X$.

아래의 공식을 사용하면, $\text{Var}(X)$를 좀더 쉽게 구할 수 있다.

<br><span class="statement-title">Theorem.</span><br>

$$
\begin{aligned}
\text{Var}(X) &= E[(X-\mu)^2] = E\left[ X^2 - 2 \mu X + \mu^2 \right] \\
            &= E[X^2] - 2 \mu E[X]  + \mu^2 \\
            &= E[X^2] - 2 \mu \cdot \mu + \mu^2 \\
            &= E[X^2] - \mu^2 = E[X^2] - \left(E[X]\right)^2
\end{aligned}
$$

"분산 = 제평 - 평제", 고등학교 때 배운 공식이다!

<br/>

\<Expectation\>은 Linearity라는 좋은 성질을 가지고 있었다. \<분산 Variance\>에서는 어떻게 되는지 살펴보자.

<span class="statement-title">Theorem.</span><br>

For any $a, b \in \mathbb{R}$,

$$
\text{Var}(aX + b) = a^2 \text{Var}(X)
$$

<hr/>

## Covariance

\<공분산 Covariance\>는 두 RV 사이에 어떤 \<관계 relation\>이 있는지를 조사하는 지표다. \<공분산\>은 아래와 같이 정의된다.

<span class="statement-title">Definition.</span><br>

The \<**covariane**\> of $X$ and $Y$ is defined as

$$
\begin{aligned}
\sigma_{XY} := \text{Cov}(X, Y) &= E \left[ (X - \mu_X) (Y - \mu_Y) \right]  \\
                                &= E(XY) - E(X)E(Y)
\end{aligned}
$$

- $\text{Cov}(X, X) = \text{Var}(X)$
- $\text{Cov}(aX + b, Y) = a \cdot \text{Cov}(X, Y)$
- $\text{Cov}(X, c) = 0$

앞에서 살펴봤을 때, 두 RV $X$, $Y$가 **독립**이라면, $E(XY) = E(X)E(Y)$가 되었다. 따라서 두 RV가 독립일 때는 $\text{Cov}(X, Y) = 0$이 된다! 그러나 주의할 점은 명제의 역(易)인 <span class="half_HL">$\text{Cov}(X, Y) = 0$일 때, 두 RV가 항상 독립임을 보장하지는 않는다!</span>

\<Covariance\>은 두 RV의 Linear Combination에 대한 분산을 구할 때도 사용한다.

Let $a, b, c \in \mathbb{R}$, then

$$
\text{Var}(aX + bY + c) = a^2 \text{Var}(X) + b^2 \text{Var}(Y) + 2 \text{Cov}(X, Y)
$$

증명은 $\text{Var}(aX + bY + c)$의 의미를 그대로 전개하면 쉽게 유도할 수 있다.

$$
\text{Var}(aX + bY + c) = E\left[ \left( (X+Y) - (\mu_X + \mu_Y) \right)^2 \right]
$$

<hr/>

## Correlation

\<공분산\>을 좀더 보기 쉽게 Normalize 한 것이 \<**Correlation**\>이다.

<span class="statement-title">Definition.</span><br>

The \<**correlation**\> of $X$ and $Y$ is defined as

$$
\rho_{XY} := \text{Corr}(X, Y) = \frac{\text{Cov}(X, Y)}{\sqrt{\text{Var}(X)} \sqrt{\text{Var}(Y)}}
$$

- if $\rho_{XY} > 0$, $X$ and $Y$ are positively correlated.
- if $\rho_{XY} < 0$, $X$ and $Y$ are negatively correlated.
- if $\rho_{XY} = 0$, $X$ and $Y$ are uncorrelated.

만약 두 RV가 완벽한 선형성을 보인다면, $\rho_{XY}$가 아래와 같다.

- if $Y = aX + b$ for $a > 0$, then $\text{Corr}(X, Y) = 1$
- if $Y = aX + b$ for $a < 0$, then $\text{Corr}(X, Y) = -1$

위의 명제는 그 역도 성립한다. 증명은 아래의 Exercise에서 진행하겠다.

\<Correlation\>은 $[-1, 1]$의 값을 갖는다. 이는 \<코시-슈바르트 부등식\>을 통해 유도할 수 있다!

<div class="math-statement" markdown="1">

**Cauchy-Schwarrtz inequality** :

$$
\left( \sum a_i b_i \right)^2 \le \sum a_i^2 \sum b_i^2
$$

Correlation 식을 의미에 맡게 풀어쓰면 아래와 같다.

$$
\begin{aligned}
\text{Corr}(X, Y) &= \frac{\text{Cov}(X, Y)}{\sqrt{\text{Var}(X)} \sqrt{\text{Var}(Y)}} = \frac{E[(X-\mu_X)(Y - \mu_Y)]}{\sqrt{E[(X-\mu_X)^2]} \sqrt{E[(Y-\mu_Y)^2]}} \\
&= \frac{\sum (X-\mu_X)(Y - \mu_Y)}{\sqrt{\sum (X-\mu_X)^2} \sqrt{\sum (Y-\mu_Y)^2}}
\end{aligned}
$$

이제 위의 식을 제곱해서 살펴보면

$$
(\rho_{XY})^2 = \left( \frac{\sum (X-\mu_X)(Y - \mu_Y)}{\sqrt{\sum (X-\mu_X)^2} \sqrt{\sum (Y-\mu_Y)^2}} \right)^2 = \frac{\left( \sum (X-\mu_X)(Y - \mu_Y) \right)^2 }{\sum (X-\mu_X)^2 \sum (Y-\mu_Y)^2}
$$

\<코시-슈바르츠 부등식\>에서 우변을 좌변으로 이동하면, 아래와 같은 부등식이 성립한다.

$$
\frac{\left( \sum a_i b_i \right)^2}{\sum a_i^2 \sum b_i^2} \le 1
$$

이를 \<Correlation\>의 제곱식에 적용하면 아래와 같다.

$$
(\rho_{XY})^2 = \frac{\left( \sum (X-\mu_X)(Y - \mu_Y) \right)^2 }{\sum (X-\mu_X)^2 \sum (Y-\mu_Y)^2} \le 1
$$

따라서 $(\rho_{XY})^2 \le 1$이므로

$$
-1 \le \rho_{XY} \le 1
$$

$\blacksquare$

</div>

추가로 \<Correlation\>은 "표준화"한 RV의 공분산으로도 해석할 수 있다.

$Z = \dfrac{X-\mu_X}{\sigma_X}$, $W = \dfrac{Y-\mu_Y}{\sigma_Y}$라고 표준화한다면, 이 둘의 공분산은 $X$, $Y$에 대한 Correlation과 같다.

$$
\text{Var}(Z, W) = \text{Corr}(X, Y)
$$

딱 보면 증명 할 수 있을 것 같아서 따로 유도는 하지 않겠다.

<hr/>

Q1. $\text{Var}(X) = 0$는 무엇을 의미하는가?

A1.

<br/>

Q2. $\text{Cov}(X, Y) = 0$이지만, 두 RV가 독립이 아닌 예를 제시하라.

<br/>

Q3. Prove that $-1 \le \text{Corr}(X, Y) \le 1$.

<br/>

Q4. Prove that if $\text{Corr}(X, Y) = 1$, then there exist $a>0$ and $b\in\mathbb{R}$ s.t. $Y = aX + b$.

<details class="math-statement" markdown="1">

<summary>펼쳐보기</summary>

A1. $p(x)$가 delta-function임을 의미한다.

<hr/>

A2. $Y=X^2$으로 설정하면 쉽게 보일 수 있다. 독립임을 보이기 위해 $p(x, y)$를 구해야 할 수도 있는데, 이것 역시 적절히 잘 설정해주면 쉽게 reasonable하게 디자인 할 수 있을 것이다.

<hr/>

A3. & A4. Q3는 이미 위에서 증명을 했다. 그러나 다른 방식으로도 증명할 수 있다! 👉 [이곳](http://people.math.gatech.edu/~ecroot/3225/rho_notes.pdf)의 [2, 3]p를 참고하라.

</details>

<hr/>

이어지는 내용에서는 \<평균\>과 \<분산\>에 대한 약간의 추가적인 내용을 살펴본다.

👉 [Chebyshev's Inequality]({{"/2021/03/17/chebyshev's-inequality" | relative_url}})

그리고 Discrete RV에서의 기본적인 Probability Distribution을 살펴본다.

- Bernoulli Distribution
- Binomial Distributions
- Multinomial Distribution
- Hypergeometric Distributions
- etc...

👉 [Discrete Probability Distributions - 1]({{"/2021/03/17/discrete-probability-distributions-1" | relative_url}})

👉 [Discrete Probability Distributions - 2]({{"/2021/03/24/discrete-probability-distributions-2" | relative_url}})