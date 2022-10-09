---
title: "Linear Classification - 2"
layout: post
tags: [applied_statistics]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Binary Logistic Regression
  - MLE; Maximum Likelihood Estimation
- Multi-class Logistaic Regression

<hr/>

## Binary Logistic Regression

LDA와 QDA에서는 $f_k(x)$, 즉 "the conditional density of $X$ given $Y=k$"를 모델링하였다. 하지만, \<**Logistic Regression**\> 모델은 Regression output $x^T \beta$를 \<Logistic Function\> $\dfrac{e^x}{1 + e^x}$에 적용한 결과를 사용한다!! 한번 살펴보자. 🤩

<span class="statement-title">Definition</span><br>

Assume that $\mathcal{Y} = \\{0, 1\\}$. Then the \<**logistic regression model**\> assumes

$$
P(Y=1 \mid X=x) = \frac{\exp (x^T \beta)}{1 + \exp (x^T \beta)}
$$

<br/>

<big><b>Q1. 왜 Logistic "<u>Regression</u>" 인가?</b></big>

평범한 Linear Regression Model에서 $P(Y = 1 \mid X = x)$는

$$
P(Y = 1 \mid X=x) = x^T \beta
$$

의 결과를 뱉는다. 하지만, 이렇게 모델링할 경우, $x^T \beta$의 값이 확률의 정의인 $[0, 1]$ 값을 갖는다는 조건을 잘 만족하지 못 했다. "The linear regression model vilostes that $x^T \beta \in [0, 1]$"

그래서 이 $[0, 1]$ 사이에 들어온다는 조건을 만족하기 위해 $x^T \beta$의 값에 Transformation을 적용한다. 그 중 하나가 이번에 사용하는 \<**logistic function**\>, 다른 이름으로 \<**sigmoid function**\>인 것이다.

$$
\text{sigmoid}(x) = \frac{e^x}{1+e^x}
$$

사실 \<sigmoid function\> 외에도 \<Gaussian cdf\>나 \<Gompertz function\>을 써도 된다고 하며, 이 경우 좀더 특별한 상황, 예를 들면 '보험' 등의 분야에서 좋은 성능을 보인다고 한다.

<br/>

<big><b>Q2. 왜 "<u>Logistic</u>" Regression인가?</b></big>

Linear Regression과 LDA/QDA 모두 classification을 수행하기 위해 적절한 **decision boundary**를 찾는 것을 목표로 했다. 

$$
\left\{ x : \log \frac{P(Y=1\mid X=x)}{P(Y=0 \mid X = x)} \right\}
$$

이때, 특별하게도 **decision boundary**가 "linear"한 상황이라면, 아래의 식을 만족하며 classification을 위한 **hyper-plain**이 정의된다.

$$
\log \frac{P(Y=1 \mid X=x)}{P(Y=0 \mid X = x)} = x^T \beta
$$

그리고 위의 식에서 로그를 풀고, 확률의 성질을 잘 이용하면 아래의 식이 유도된다.

$$
P(Y=1 \mid X= x) = \frac{\exp (x^T \beta)}{1 + \exp (x^T \beta)}
$$

놀랍게도 이때 유도된 식이 바로 \<**Logistic Function**\>인 것이다!! 🤩

<hr/>

### MLE; Maximum Likelihood Estimation

\<**Logistic Regression**\> 모델도 결국은 Regression을 위해 $\beta$ 값을 추정해야 한다. 이것은 \<MLE; Maximum Likelihood Estimation\>을 통해 추정한다. 그 과정을 살펴보자.

먼저 \<**Likelihood function**\> $L(\beta)$를 정의하자.

$$
L(\beta) = \prod^n_{i=1} P(Y = y_i \mid X = x_i)
$$

$L(\beta)$가 왜 이렇게 정의되었는지 살펴보자. 

<div class="img-wrapper">
  <img src="https://raw.githubusercontent.com/angeloyeo/angeloyeo.github.io/master/pics/2020-07-17-MLE/pic2.png" width="600px">
  <p>
  (출처: <a href="https://angeloyeo.github.io/2020/07/17/MLE.html">공돌이의 수학정리노트</a>)
  </p>
</div>

우선, $L(\beta)$에서 $X=x_i$에서의 확률을 모두 곱하고 있다. 이렇게 곱할 수 있는 이유는 MLE의 가정인 <span class="half_HL">"각 $x_i$가 모두 i.i.d.하다"</span>에 기반한다. 독립인 사건들이 동시에 발생하는 것이므로 "확률의 곱법칙"에 의해 위와 같이 $\prod$ 를 사용한 것이다.

위의 $L(\beta)$의 식에 \<logistic function\>을 넣어서 조금 풀어서 써보자.

$$
\begin{aligned}
L(\beta) &= \prod^n_{i=1} \left( \frac{\exp (x_i^T \beta)}{1 + \exp (x_i^T \beta)}\right)^{y_i} \left( \frac{1}{1 + \exp (x_i^T \beta)}\right)^{1-y_i} \\
&= \prod^n_{i=1} \left( \frac{\exp (y_i \cdot x_i^T \beta)}{1 + \exp( x_i^T \beta)}\right)
\end{aligned}
$$

딱 봐도 식이 좀 복잡하다. 그래서 계산의 편의를 위해 $L(\beta)$에 $\log$를 취해 \<Log-Likelihood\> $\ell(\beta)$를 사용하자!

$$
\begin{aligned}
\ell (\beta) &= \log \left( \prod^n_{i=1} \left( \frac{\exp (y_i \cdot x_i^T \beta)}{1 + \exp( x_i^T \beta)}\right) \right) \\
&= \sum^n_{i=1} \; \log \left( \frac{\exp (y_i \cdot x_i^T \beta)}{1 + \exp( x_i^T \beta)}\right) \\
&= \sum^n_{i=1} \; \left(  y_i \cdot x_i^T \beta - \log \left( 1 + \exp(x_i^T \beta)\right) \right)
\end{aligned}
$$

Production으로 구성된 기존의 식을 Summation으로 변환했기에 이전보다는 분석하기 훨씬 쉬워졌지만, 여전히 $\ell (\beta)$를 Maximization하는 것은 간단하지 않다.

그러나 $\ell (\beta)$가 \<**concave function**\>이라는 점[^1]은 우리가 <span class="half_HL">nemerical method를 사용해 Maximization을 수행할 수 있음</span>[^2]을 말한다!! 🤩 그래서 \<**Newton-Raphson method**\> 등의 Nemerical Approximation의 방법을 사용해 MLE를 수행한 된다.

<hr/>

<span class="statement-title">Regularization.</span><br>

(아직 잘 와닿질 않네;; 강의 한번 더 듣고, 이 부분 교재 읽고 채울 것)

<hr/>

### LDA vs. Logistic Regression

<table style="text-align: center; table-layout:fixed">
<th>LDA</th>
<th>Logistic Regression</th>
<tr>
    <td colspan="2">linear decision boundary</td>
</tr>
<tr>
    <td>Normal 분포 가정 有</td>
    <td>Normal 분포 가정 無</td>
</tr>
<tr>
    <td>joint distribution인 $P(Y, X)$에 대한 구체적인 값이 필요</td>
    <td>$P(Y=1 \mid X = x)$에 대한 값만 있으면 충분</td>
</tr>
<tr>
    <td>Logistic에 비해 더 많은 '가정'이 들어가기 때문에 Logistic과 비교해 applicability가 떨어짐.</td>
    <td>LDA와 비교해 categorical input을 쓰기 쉬움</td>
</tr>
</table>

<hr/>

## Multi-class Logistic Regression

Let $\mathcal{Y} = \\{ 1, \dots, K \\}$, and assume that

$$
P(Y = k \mid X = x) \propto \exp (x^T \beta_k)
$$

이것은 곧,

$$
P(Y = k \mid X = x) = \frac{\exp(x^T \beta_k)}{\displaystyle \sum^K_{i=1} \exp (x^T \beta_i)}
$$



## references

- ['공돌이의 수학정리노트'님의 포스트](https://angeloyeo.github.io/2020/07/17/MLE.html)

<hr/>

[^1]: $\ell (\beta)$가 왜 concave function이 되는지는 더 조사를 한 후에 추후에 기술하겠ㄷ.

[^2]: 그러나 어떤 특별한 케이스에서는 MLE의 해가 존재하지 않을 수도 있다고 한다. 이것 역시 더 조사를 한 후에 추후에 기술하겠다.