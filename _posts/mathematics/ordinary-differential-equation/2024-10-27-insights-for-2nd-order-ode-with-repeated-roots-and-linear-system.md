---
title: "Insights for Solving 2nd Order ODE System with Repeated Roots"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "2nd order ODE를 Systems of ODE로 표현 했을 때, 특성 방정식이 중근을 가진다면 그 결과를 어떻게 해석해야 할까?"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

미분방정식을 살펴볼 때는 늘 나이스한 상황만 있지 않습니다. 예를 들면, 2차 동차 미분방정식을 풀 때, **중근(repeated root)**이 나오는 경우가 그렇고, 행렬 $A$의 고유값이 **중복도**를 갖는 경우가 있습니다. 그러나, 시험이나 문제는 늘 저런 상황에서 나오는 법... ㅠㅠ

[Generalized Eigen Values](/2024/10/26/generalized-eigen-values/) 포스트에서 고유값이 중복도를 갖는 경우를 살펴보았습니다. 그리고, 이를 활용해 행렬 $A$가 Jordan block인 $\mathbf{x}' = J \mathbf{x}$ 케이스도 [포스트](/2024/10/16/jordan-block-case/)로 정리해 살펴보았습니다.

이번에는 이것을 2nd order homogeneous linear ODE 경우에서 살펴보고자 합니다!!

## 2nd order homogeneous linear ODE

요 부분은 2학년 수업인 미방 수업에서 경험 했습니다. [#](/2024/09/30/2nd-order-homogeneous-lienar-ode/)

<div class="definition" markdown="1">

$$
x'' + a x' + b x = 0
$$

</div>

그리고 위와 같은 ODE를 풀기 위해 특성 방정식의 해를 구했습니다.

$$
\lambda^2 + a \lambda + b = 0
$$

요런 특성방정식을 풀었죠.

그런데, 여기에서도 중근인 경우가 있었습니다. 예를 들면, $x'' - 2 x' + 1 = 0$라는 ODE 였다면 $\lambda = 1$로 중근이었고, solution은 아래와 같은 형태 였습니다.

$$
x(t) = c_1 e^{t} + c_2 t e^{t}
$$

그런데, 이런 $te^{t}$가 있는 형태! 4학년 과목인 상미방의 $\mathbf{x}' = J \mathbf{x}$에서도 볼 수 있었습니다...!

## Jordan Form ODE

이에 대한 경우는 [별도 포스트](/2024/10/16/jordan-block-case/)에서 충분히 다뤘으니 결론만 보겠습니다.

$$
\mathbf{x}' = J\mathbf{x}, \qquad J = \left(
\begin{matrix}
1 & 1 \\
0 & 1
\end{matrix}
\right)
$$

위와 같은 ODE 행렬이 있을 때, 솔루션은

$$
\mathbf{x}(t) = c_1 \left(
\begin{matrix}
1 \\
0
\end{matrix}\right)
e^{t}
+
c_2 \left(
\begin{matrix}
t \\
1
\end{matrix}
\right)
e^{t}
$$

위의 솔루션에서 $x_1(t)$ 성분에 대한 식이 2nd order homogeneous linear ODE와 동일하다고, 생각했고, 둘이 어떤 관계가 있을까?? 고민하게 되었습니다.

# Systems of ODEs

2학년 미방의 끝부분에는 기존의 n차 미분 방정식을 n 차원 행렬 $A \in \mathbb{R}^{n\times n}$으로 표현하여 아래와 같은 행렬 미방으로 표현하는 방법에 대해 배웁니다.

$$
\mathbf{x}' = A\mathbf{x}
$$

이것을 그대로 2nd order homogeneous linear ODE에 적용해보겠습니다. 적용할 2nd order linear ODE는 아래와 같습니다.

$$
x''(t) - 2 x'(t) + x(t) = 0
$$

그리고 미방 시스템에서의 표기는 $x_1(t) = x(t)$, $x_2(t) = x'(t)$로 하겠습니다.

$$
\left(
\begin{matrix}
x_1 \\
x_2
\end{matrix}
\right)'
= \left(
\begin{matrix}
0 & 1 \\
-1 & 2
\end{matrix}
\right)
\left(
\begin{matrix}
x_1 \\
x_2
\end{matrix}
\right)
$$

첫번째 행은 $x_1'(t) = x_2(t) = x'(t)$임을 표현한 것이고, 두번째 행은 $x_2'(t) = x^{\prime\prime}(t) = - x_1(t) + 2 x_2(t)$를 표현한 것입니다.

미분 시스템을 풀기 위해, 위 행렬의 고유값과 고유벡터를 구합니다. 그러면, $\lambda = 1$ 중근과 고유벡터 $v_1 = (1, 1)^T$를 얻게 됩니다. 하지만, 중근이기 때문에, Generalized Eigen Vector를 구해야 하고, 이를 형식에 맞춰서 구해보면... $w = (0, 1)^T$를 얻게 됩니다.

이제 솔루션을 적어보면...

$$
\mathbf{x}(t) = \left(
\begin{matrix}
x_1 \\
x_2
\end{matrix}
\right)
= c_1 e^{t} \left(
\begin{matrix}
1 \\
1
\end{matrix}
\right)
+ c_2 e^{t} \left(
\begin{matrix}
0 \\
t
\end{matrix}
\right)
$$

를 얻게 됩니다. 제가 이해가 안 되었던 부분은 여기서 부터입니다 😵‍💫

## 왜 제대로된 솔루션을 안 주는 걸까?

위의 솔루션에 따르면, 해는 $x(t) = x_1(t) = c_1 e^t$입니다. 그러나, 우리는 2nd order linear ODE가 중근을 가질 때의 해에는 $te^{t}$ 텀이 들어간다는 것을 알고 있습니다.

반면에, $te^{t}$ 텀은 $x_2(t) = x'(t)$에 들어가있습니다. 이게 무슨일 일까요??

## 사실 제대로된 게 맞습니다.

제대로 된 게 맞습니다...!! 😮

일단 $x(t) = c_1 e^{t}$라는 결과, 이것 자체도 이미 미분방정식의 솔루션입니다. 미분방정식에 대입 해보면 식을 만족하죠. 다만, General Solution이 아니고, $te^{t}$ 텀이 부족합니다.

$x'(t) = (c_1 + c_2) e^{t} + c_2 t e^{t}$라는 것도 틀린 말이 아닙니다!! 실제로 우리가 알고 있는 2nd order ODE의 솔루션 $x(t)$를 미분하면...

$$
\begin{aligned}
x'(t) &= (c_1 e^{t} + c_2 t e^{t})' \\
&= c_1 e^{t} + c_2 e^{t} + c_2 t e^{t} \\
&= (c_1 + c_2) e^{t} + c_2 t e^{t}
\end{aligned}
$$

즉, 미분 시스템을 풀어서 나온 결과, 그대로 나옵니다!!

그래서 결론은 우리는 미분 시스템의 $x_2(t) = x'(t)$에 대한 것을 적분하여, $x(t)$의 솔루션을 제대로 얻을 수 있습니다.

$$
x(t) = \int x'(t) \, dx = \int \left( (c_1 + c_2) e^{t} + c_2 t e^{t} \right) \, dx
= c_1 e^{t} + c_2 t e^{t}
$$

## 정리하면

미분 시스템의 해에서 $x_1(t) = x(t)$에 대한 부분은 General Solution의 일부만을 제공했습니다. 그리고 이것은 첫번째 고유 벡터에 대한 정보만이 담겨있다고 볼 수 있습니다.

완전한 해를 얻기 위해서는 $x_2(t) = x'(t)$를 적분하여 얻어야 했습니다. 일반화된 고유 벡터에 대한 정보는 $x_2(t)$의 해에 담겨있기 때문입니다.

# 맺음말

이 글에서는 2차 선형 미분 방정식과 선형 시스템에 대해 고찰해보았습니다. 미분 시스템의 해에서 General Solution을 유도했고, 그 과정에서 고유 벡터와 일반화된 고유 벡터가 어떻게 등장하는지 살펴보았습니다.
