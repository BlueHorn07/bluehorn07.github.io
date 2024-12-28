---
title: "Duhamel's Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "Systems of ODEs를 일반적인 방법에 대해."
---

복수전공하고 있는 수학과의 학부 졸업시험에 미분방정식이 있는 줄 알고, 시험 준비도 할 겸 복학할 때 “상미분방정식” 과목을 신청했습니다. 나중에 알고보니 미분방정식은 졸업시험 과목이 아니었습니다… OTL… 그래도 이왕 시작한 것 포기란 없습니다!! 💪 으랏차!!
[상미분방정식 포스트 전체 보기](/categories/ordinary-differential-equations)
{: .notice--info}

# 들어가며

이제 전체 범위의 1/4 정도 지난 것 같은데, 벌써 내용이 어렵다...;; 그래도 어쩌겠는가 졸업을 해야 하니까... 악으로 깡으로 버텨본다!!

![](/images/meme/i-must-study.jpeg){: .align-center style="max-width: 300px" }

# To solve ODE...

지금까지 $X' = AX$ 꼴의 ODE System을 풀기 위해 했던 방법들을 생각해보자.

1. 행렬 $A$에 대한 eigen value와 eigen vector를 찾는다.
2. $X(t)$를 $X_1(t) = v_1 e^{\lambda_1 t} + v_2 e^{\lambda_2 t}$로 표현한다.
3. 만약 $A$의 canonical form이 Jordan Form $J$라면, generalized eigen value를 도입한다.

그런데 귀찮게 Eigen value 구하고 할 필요 없이 아주 쉽게 솔루션을 표현하는 방법이 있었으니...

<div class="theorem" markdown="1">

Let $A$ be an $n \times n$ matrix. Then the initial value problem $X' = AX$ and $X(0) = X_0$ has an unique solution

$$
X(t) = e^{tA} X_0
$$

</div>

정리에 대한 증명은 Existence와 Uniqueness를 보이면 되는데...

<div class="proof" markdown="1">

[Existence]

Solution이라고 제시한 $X(t) = e^{tA} X_0$가 미분방정식을 만족하는지 체크하면 된다. 만약 미분방정식을 만족한다면, Solution 중 하나를 찾은 것이다!

$$
X' = \frac{d}{dt} X(t) = \frac{d}{dt} \left(e^{tA} X_0\right)
= A e^{tA} X_0 = A X
$$

</div>

<div class="proof" markdown="1">

[Uniqueness]

미분방정식에 또다른 solution이 존재하고, 그것을 $Y(t)$라고 가정해보자. 그리고 $Z(t) = e^{-tA} Y(t)$인 행렬을 또 정의한다.
그리고 $Z'(t)$를 구해보면...

$$
Z'(t) = \frac{d}{dt} \left( e^{-tA} Y(t) \right)
= -A e^{-tA} Y(t) + e^{-tA} Y'(t)
= -A e^{-tA} Y(t) + e^{-tA} \cdot \left( A Y(t)\right)
= 0
$$

$Z'(t) = 0$라는 결과는 $Z(t)$ 함수가 constant 함수라는 것이고, 이는

$$
Z(t) = e^{-tA} Y(t) = Z_0 = Y(0) = X_0
$$

라는 결과로 이어진다. 즉, $Y(t) = Z(t) e^{tA} = X_0 e^{tA}$가 되고, 이것은 처음에 가정했던 $X(t)$에 대한 solution과 일치한다.
따라서, solution은 유일하게 존재한다. $\blacksquare$

</div>

# Non-autonomous Linear System

지금까지는 살펴본 $X' = AX$ 경우는 정말 나이스한 경우다. 하지만 현실은 가혹한 법... ㅠㅠ 아래와 같은 형태의 Linear System을 살펴보자.

<div class="definition" markdown="1">

$$
X' = AX + G(t)
$$

이때, $G(t)$는 시간 $t$에 의존하는 "forcing term"으로 $G: \mathbb{R} \rightarrow \mathbb{R}^n$이다.

</div>

위와 같은 경우, 미분방정식이 시간에 의존하는 텀 $G(t)$가 있기 때문에, "non-autonomous"이면서, 미방을 정리하면, $X' - AX = G(t)$로 우변이 $0$이 아닌 값이기 때문에 "non-homogeneous" Linear System이다.

2학년 [미방(MATH200)](/categories/differential-equations)에서도 2차 미방에서 이런 경우가 있었다.

$$
y'' + p(x) y' + q(x) y = r(x)
$$

이때는 그래도 좀 할만 했다.

- 미정계수법(method of undetermined coefficients)
- 매개변수 변환법(method of variation of parameters)

를 통해서 해결했다.

<br/>

다행히도 Systems ODE에서는 General Solution에 대한 공식이 존재한다!! 이를 "**Duhamel's principle**"라고 한다.

<div class="theorem" markdown="1">

Consider the non-autonomous ODE $X' = AX + G(t)$ and $X(0) = X_0$ where $A$ is an $n \times n$ matrix. $G(t)$ is a continuous function of $t$. Then,

$$
X(t) = e^{tA} \left( X_0 + \int_0^t e^{-sA} G(s) ds \right)
$$

is the solution.

</div>

얼핏 보면 1st order non-homo. ODE에서 Integrating Factor를 도입했던 것과 비슷한 모습이다.

<div class="statement" markdown="1">

**[[1st order non-homo. ODE]](/2024/09/11/1st-order-homogeneous-linear-ode/#integrating-factor-1)**

$$
x' = p(t) x + r(t)
$$

I.F.는 $F(t) = \exp (\int - p(t) \, dt)$이고, 이것을 solution은 아래와 같다.

$$
x(t) = F(t)^{-1} \left(x_0 + \int r(t) \, F(t)^{-1} \, dt \right)
$$

<hr/>

Duhamel의 결과와 쉽게 비교하기 위해 식을 조금 단순화 하자. $h = \int p(t) dt$로 두면...

$$
x(t) = e^{h} \left( x_0 + \int_0^t e^{-h} r(s) \, ds \right)
$$

</div>

즉, Duhamel's principle은 1st order에서의 공식을 행렬 버전으로 확장한 것이다!!

<div class="proof" markdown="1">

증명은 주어진 공식을 실제로 미분방정식에 대입해보면 된다.

$$
\begin{aligned}
X'(t)
&=
A \cdot \left(e^{tA} \left( X_0 + \int_0^t e^{-sA} G(s) ds \right) \right)
+ \cancel{e^{tA}} \left( \cancel{e^{tA}} G(t) \right) \\
&= A \cdot X(t) + G(t)
\end{aligned}
$$

</div>

## Example: Forced Harmonic Oscillation

Linear ODE에 대해서는 [Harmonic Oscillation](/2024/11/12/harmonic-oscillation/) 포스트에 자세히 정리해둔 적이 있다. 이번에는 이것을 Systems of ODEs의 관점에서 해석해존다.

$$
X'(t)
=
\left(\begin{matrix}
0 & 1 \\
-k & -b
\end{matrix}\right)
X
+ \left(\begin{matrix}
0 \\
f(t)
\end{matrix}\right)
$$

이때, 외부힘 $f(t)$를 주기함수 $\cos t$라고 하자.

(아래 내용은 수업 때 배웠는데, 아직 내용을 완전히 이해하지 못 했다. 교수님께 이해 안 되는 부분을 여쭤보고 이어서 작성할 예정이다. [24/11/13])

<div class="proof" markdown="1">

[periodic solution]

가장 먼저 solution이 주기가 $2\pi$인 unique periodic solution을 가진다는 것을 보일 것이다. 이를 보이기 위해선 $X(0) = X_0 = X(2\pi)$임을 보여야 한다.

만약 $X(0) = X(2\pi)$라면 Duhamel's Principle 공식에 따라 아래의 식이 성립할 것이다.

$$
X_0 = e^{2\pi A} X_0 + e^{2\pi A} \int_0^{2\pi} e^{-sA} \, G(s) ds
$$

이때, 우변에서 아래에 대한 부분은 constant vector이고, 이를 $W$라고 이름 붙이자.

$$
W = e^{2\pi A} \int_0^{2\pi} e^{-sA} \, G(s) ds = \text{const.}
$$

위의 식을 $X_0$에 대해 정리하면

$$
\left(e^{2\pi A} - I\right) X_0 = -W
$$

이때 $\left(e^{2\pi A} - I\right)$ 행렬이 invertible 할 수도 있고, non-invertible 할 수도 있다.

(a) If $\left(e^{2\pi A} - I\right)$ is invertible

Straightforward,

$$
X_0 = (e^{2\pi A} - I)^{-1} (- W)
$$


(a) If $e^{2\pi A} - I$ is non-invertible

</div>

# Meaning of Method of Variation of Parameters


왜 Duhamel's Theorem이 variaiton of parameter 기법인지

