---
title: "Continuous Least-squared Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

[이전 포스트](/2025/05/17/least-square-method/)에서 $n$개 데이터 노드 $\left\\{ (x_i, y_i) \right\\}$가 주어졌을 때, Least-square Method를 수행하는 방법을 살펴보았습니다.

이번 포스트에서는 데이터 노드가 유한한 갯수가 아니라 구간 $(a, b) \subseteq \mathbb{R}$로 주어집니다. 이 구간 위에서 Least-square Method를 수행하는 것을 "Continuous LS"(이하 CLS)라고 합니다!

# Introduction

CLS의 목표는 어떤 함수 $f(x)$를 함수 집합 $\left\\{ \phi_0(x), \phi_1(x), \dots, \phi_n(x) \right\\}$의 선형 결합으로 근사하는 것 입니다. 보통은 이 함수 집합은 다항 함수의 집합 $\left\\{ 1, x, x^2, \dots, \right\\}$ 입니다.

$$
f(x) \approx \sum_{j=0}^n c_j \phi_j(x)
$$

이때, 계수 $c_j$는 아래의 제곱 오차의 적분을 최소화 하는 계수를 사용합니다.

$$
SSE(\mathbf{c}) = \int_a^b \left(f(x) - \sum_{j=0}^n c_j \phi_j(x) \right)^2 w(x) \, dx
$$

이때, $w(x)$는 가중치 함수인데, 보통 $w(x) = 1$로 두고 풉니다.

## Normal Equation

최소 SSE를 얻기 위해, 적분식을 각 계수 $c_i$에 대해 편미분한 값을 0으로 두고 방정식을 구성합니다.

$$
\begin{aligned}
\frac{\partial SSE}{\partial c_i}
&=
\int_a^b \left(f(x) - \sum_{j=0}^n c_j \phi_j(x) \right)^2 \, dx \\
&=
2 \int_a^b \left(f(x) - \sum_{j=0}^n c_j \phi_j(x) \right) \left(- c_i \phi_i(x)\right) \, dx
= 0
\end{aligned}
$$

이제 이 등식을 정리하면,

$$
\int_a^b \left(f(x) - \sum_{j=0}^n c_j \phi_j(x) \right) \cdot \phi_i(x) \, dx = 0
$$

좌우를 맞춰주면,

$$
\int_a^b f(x) \cdot \phi_i(x) \, dx
= \int_a^b \left(\sum_{j=0}^n c_j \phi_j(x)\right) \cdot \phi_i(x) \, dx
$$

그리고 여기에서 식을 좀더 다듬어주면,

$$
\int_a^b f(x) \cdot \phi_i(x) \, dx
= \sum_{j=0}^n c_j \cdot \left(\int_a^b \phi_j(x) \cdot \phi_i(x) \, dx\right)
$$

좌/우변의 의미를 살펴보면,

- 좌변
  - $f(x)$와 기저 함수 $\phi_i(x)$의 내적
- 우변
  - 계수 $c_j$와 기저 함수들 사이의 내적으로 만들어진 선형 시스템

우변이 왜 선형 시스템이냐면,

$$
\sum_{j=0}^n c_j \cdot \left(\int_a^b \phi_j(x) \cdot \phi_j(x) \, dx\right)
=
\begin{bmatrix}
c_0 \\
c_1 \\
\vdots \\
c_n
\end{bmatrix}
\begin{bmatrix}
\int_a^b \phi_0(x) \cdot \phi_i(x) \, dx \\
\int_a^b \phi_1(x) \cdot \phi_i(x) \, dx \\
\vdots \\
\int_a^b \phi_n(x) \cdot \phi_i(x) \, dx
\end{bmatrix}
$$

이게 이걸 행렬 형태로 정리하면,

$$
X \theta = \mathbf{y}
$$

- $X_{ij} = \int_a^b \phi_i(x) \phi_j(x) \, dx$
- $\theta_i = \int_a^b f(x) \phi_i(x) \, dx$
- $\mathbf{y} = [c_0, c_1, \dots, c_n]^T$

직관적으로 알 수 있는 성질은 기저 행렬의 내적으로 만들어지는 행렬 $X$는 항상 "대칭 행렬"입니다. (함수) 내적은 대칭적인 성질을 가지고 있기 때문입니다.

# Example

예제를 통해 익숙해져봅시다. $f(x) = e^x$를 $[0, 1]$ 구간에서 1차 다항식으로 근사해보겠습니다. 즉, $\tilde{f}(x) = c_0 + c_1 x$. 가중치는 가장 단순한 $w(x) = 1$로 하겠습니다.

가장 먼저 기저 함수로 만드는 행렬을 계산합니다.

$$
X_{ij} = \int_0^1 \phi_i(x) \phi_j(x) \, dx
$$

$$
X
=
\begin{bmatrix}
\int_0^1 1 \cdot 1 \, dx & \int_0^1 1 \cdot x \, dx \\
\int_0^1 1 \cdot 1 \, dx & \int_0^1 x \cdot x \, dx
\end{bmatrix}
=
\begin{bmatrix}
1 & 1/2 \\
1/2 & 1/3
\end{bmatrix}
$$

기저 함수와 원본 함수의 내적인 벡터 $\theta$도 구해봅시다.

$$
\theta_i = \int_0^1 f(x) \phi_i(x) \, dx
$$

$$
\theta
=
\begin{bmatrix}
\int_0^1 e^x \cdot 1 \, dx \\
\int_0^1 e^x \cdot x \, dx
\end{bmatrix}
=
\begin{bmatrix}
e - 1 \\
1
\end{bmatrix}
$$

이제 선형 시스템을 만들어봅시다.

$$
\begin{bmatrix}
1 & 1/2 \\
1/2 & 1/3
\end{bmatrix}
\begin{bmatrix}
c_0 \\
c_1
\end{bmatrix}
=
\begin{bmatrix}
e - 1 \\
1
\end{bmatrix}
$$

시스템은 기존의 이산 LS 방식으로 풀어내면 됩니다. 그러면 계수는 $c_0 = 6e-8$, $c_1 = -6e+14$로 나오고, 함수 $f(x) = e^x$의 구간 $[0, 1]$위에서 근사한 1차 함수는

$$
f(x) \approx (-6e+14) x + (6e-8)
$$

