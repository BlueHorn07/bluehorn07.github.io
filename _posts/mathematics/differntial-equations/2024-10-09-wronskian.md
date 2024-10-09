---
title: "Wronskian"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "함수의 일차 독립을 판단하는 도구."
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가기 전에

오늘 내용에는 행렬식이 나온다! 선형대수를 맨날 하는게 아니기에 본인도 Wronskian을 처음 볼 때, 의미가 제대로 이해 되지 않았었다.

2차원의 Unit Vector $x = (1, 0)$, $y = (0, 1)$가 일차 독립(linearly independent)라는 걸 기억하는가? 그리고 행렬식(det)를 구하는 공식이 기억난다면, Identity 행렬 $I$에 대해 $\text{det}(I) = 1$라는 것도 쉽게 구할 수 있을 것이다.

여기에서 자연스럽게(?) 유추하면, $\text{det} A \ne 0$이면, 행렬을 구성하는 벡터 $\mathbf{x}_1, ..., \mathbf{x}_n$이 일차 독립이다.

선형대수를 다 까먹었어도 Wronskian을 이해하기 위해 기억해낼 것은 이 정도면 충분하다 :)


# 함수의 일차 독립

두 함수 $y_1(x)$, $y_2(x)$가 있다고 하자. 이 둘을 도함수 $y'(x)$와 함께 열벡터로 기술하면...

$$
\mathbf{y}_1 = [y_1(x), y_1'(x)]^T
$$

그리고 이것을 $2 \times 2$ 행렬로 만들어 행렬식을 구해볼 수 있다.

$$
\text{det}
\left(
\begin{matrix}
y_1 & y_2 \\
y_1' & y_2'
\end{matrix}
\right)
= y_1 y_2' - y_1' y_2
$$

행렬에서 행렬식으로 일차독립을 파악하는 것처럼 "함수의 일차독립도 함수 행렬식의 값"으로 판단할 수 있다. 만약 행렬식 $\text{det} = 0$이면 두 함수가 일차 종속이고, $\text{det} \ne 0$이면 일차 독립이다.

<br/>

그런데 함수의 값은 행렬의 원소와 달리 고정된 값이 아니다. 그래서 $y(x)$의 $x$ 값에 따라 행렬식의 값도 달라질 것이다.

그래서 함수의 일차 독립에 대한 행렬식의 정의에는 아래의 조건이 추가된다.

<div class="definition" markdown="1">

만약 범위 $I$ 내의 모든 $x$에 대해서 $\text{det} \ne 0$이면, 함수들은 일차 독립이다.

</div>

Wronskian $W$이란 바로 이런 것으로 함수 행렬에 행렬식을 말한다.

$$
W_2(y_1, y_2) =
\text{det}
\left(
\begin{matrix}
y_1 & y_2 \\
y_1' & y_2'
\end{matrix}
\right) = y_1 y_2' - y_1' y_2
$$

대표적인 함수 일차 독립의 예시로 $y_1 = \cos x$, $y_2 = \sin x$가 있다. 이것의 Wronskian을 구해보면...

$$
W = \text{det}
\left(
\begin{matrix}
\cos x & \sin x \\
-\sin x & \cos x
\end{matrix}
\right)
= \cos^2 x + \sin^2 x = 1
$$

따라서 두 삼각 함수는 함수 일차 독립이다.


# 2nd Order Linear ODE

$$
y'' + p(x) y' + q(x) y = r
$$

오늘 2차 미분방정식은 두 개의 solution 함수 $y_1$, $y_2$을 가진다.

미분방정식의 solution 함수도 general solution을 만들기 위한 basis가 되기 때문에, 함수 일차 독립에 대한 성질이 필요하다.

그래서 2차 선형 미분방정식의 해는 Wronskian 값이 0이 아니어야 한다.

만약, 하나의 solution 함수 $y_1$를 찾고, 이어서 $y_2$를 찾았는데... 이럴수가!! $W = 0$인 경우가 있었다! 그렇다면, 그 $y_2$는 2차 미분방정식의 basis 함수가 아닌 것이다.


# Higher Order Linear ODE

이런 접근은 Higher Order Linear ODE에서도 성립한다.

$$
y^{(n)} + p_{n-1}(X) y^{n-1} + \cdots + p_1(x) y' + p_0(x) y = 0
$$

라는 nth-order linear ODE가 있다고 하자. 그러면, 여기에서도 n개의 solution 함수 $y_1, ..., y_n$가 존재할 것이고 이들이 general solution의 basis를 이룬다. 따라서,

$$
W(y_1, .., y_n) \ne 0
$$
