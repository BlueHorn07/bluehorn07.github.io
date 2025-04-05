---
title: "Lagrange Interpolation - Vandermonde Matrix"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "보간 문제를 선형 대수로 풀어내는 방법에 대해!"
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

"[라그랑주 보간법](/2025/03/19/lagrange-interpolation/)"의 응용에 대해 다루는 포스트 입니다.

# Linear Equation

주어인 $n$개의 점 $(x_1, y_1), \dots, (x_n, y_n)$을 지나는 다항식 $P(x) \in \mathbb{P_{n-1}}$를 찾고자 합니다.

$$
P(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{n-1} x^{n-1}
$$

이 다항식은 주어진 $n$개의 점을 지나야 하므로, 모든 $(x_i, y_i)$에 대해 아래의 식이 만족해야 합니다.

$$
y_i = P(x_i) = a_0 + a_1 x_i + \cdots a_{n-1} (x_i)^{n-1}
$$

그러면 $n$개의 각 점에 대해 등식을 만족해야 하고, 이것은 $n$개의 선형 방정식을 얻게 합니다. 우리의 목표는 $n$개 선형방정식을 만족하는 $\\{ a_i \\}$를 찾는 것이 목표가 됩니다! 이를 행렬로 표현하면

$$
V a = y
$$

가 됩니다. 각 항목은

- $a = [a_0, a_1, \dots, a_{n-1}]^T$
  - 우리가 찾고자 하는 다항식의 계수에 대한 벡터
- $y = [y_1, y_2, \dots, y_n]^T$
  - 주어진 함수값
- $V \in \mathbb{R}^{n\times n}$
  - 이것을 Vandermonde 행렬이라고 합니다!

# Vandermonde Matrix

$$
V =
\left[
\begin{matrix}
1 & x_1 & x_1^2 & \cdots & x_1^{n-1} \\
1 & x_2 & x_1^2 & \cdots & x_1^{n-1} \\
\vdots & \vdots & \vdots &  & \vdots \\
1 & x_n & x_n^2 & \cdots & x_n^{n-1} \\
\end{matrix}
\right]
$$

각 행은 $x_i$에 대한 다항식 $P(x)$의 항들로 구성 됩니다.

Vandermonde 행렬은 보간 문제를 선형대수적인 문제로 변환할 수 있게 해주는 도구 입니다.

수치해석에 대한 문제를 선형대수에 대한 문제로 접근하는 것은 후의 "Spline" 보간 기법에 대해 다룰 때도 등장합니다!

## Invertible and Uniqueness

Vandermonde 행렬 $V$가 가역(invertible)이라면, 선형 시스템 $Va =y$이 해 $a = V^{-1} y$가 유일하게 존재합니다.

참고로 보간 다항식도 유일하게 결정되는데요. 이것은 선형대수적인 접근과 수치해석의 접근 둘다 "유일성"을 갖는다는 일치된 결과를 말해줍니다.

이때, 중요한 점은 "$n$개의 각 점 $x_i$가 모두 서로 달라야" 합니다. 그래야 행렬 $V$이 가역 행렬이 되기 때문입니다.
