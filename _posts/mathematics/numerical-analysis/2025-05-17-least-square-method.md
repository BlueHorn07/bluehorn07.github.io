---
title: "Least-squared Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

드디어 이번 학기 수치해석개론 수업의 마지막 챕터 입니다!
졸업 학기라 공부가 손에 잘 안 잡히긴 하는데... 끝가지 화이팅!!


# Introduction

"**Least-square Method**"(이하 LS Method)는 주어진 데이터 포인트에 가장 잘 맞는 직선(또는 곡선)을 찾는 방법론 입니다.

데이터 노드의 집합 $\left\\{ (x_1, y_1), \cdots, (x_n, y_n) \right\\}$이 주어졌습니다. 이것을 선형으로 근사하는 모델 $y = ax + b$를 찾고자 합니다. 그러면, 우리가 찾아야 할 것은 계수 $a$와 $b$이고, 이 두 계수는 아래의 "오차제곱합(Sum of Squared Errors, SSE)"를 최소로 하는 실수값여야 합니다.

$$
SSE(a, b) = \sum_{i=1}^n \left(y_i - (a x_i + b)\right)^2
$$

## Matrix Form

이것을 좀더 쉽게 표기 하기 위해 행렬로 표현 합니다.

$$
X \theta \approx \mathbf{y}
$$

예를 들어 아래와 같이 데이터 노드가 주어졌고,

$$
\left\{
(x, y) : (1, 2), (2, 3), (3, 4)
\right\}
$$

이것을 $y = ax + b$로 표현하면,

$$
y_i = a x_i + b
$$

가 되는데, 이걸 벡터 내적으로 표현하면,

$$
\begin{aligned}
y_i
&=
\begin{bmatrix}
b & a
\end{bmatrix}
\begin{bmatrix}
1 \\
x_i
\end{bmatrix} \\
&=
\begin{bmatrix}
a & b
\end{bmatrix}
\begin{bmatrix}
x_i \\
1
\end{bmatrix} \\
&=
{\color{red}
\begin{bmatrix}
x_i & 1
\end{bmatrix}
\begin{bmatrix}
a \\
b
\end{bmatrix}
}
\end{aligned}
$$

이렇게 표현할 수 있습니다. 3가지 표현을 가져왔는데, 어떤 걸로 적어도 상관 없습니다. 그러나 저는 마지막 표현을 사용해 데이터 노드를 행렬로 표현하겠습니다.

$$
\begin{bmatrix}
1 & 1 \\
2 & 1 \\
3 & 1
\end{bmatrix}
\begin{bmatrix}
a \\
b
\end{bmatrix}
=
\begin{bmatrix}
2 \\
3 \\
4
\end{bmatrix}
$$

최종적으로 $X \theta = \mathbf{y}$의 형태를 얻었습니다! 맥락에 따라서, $A \mathbf{x} = \mathbf{b}$라고 표현하거나, $A \mathbf{x} = \mathbf{y}$라고 표현하는 것도 있는데, 저는 공부할 때 표기가 너무 헷갈렸어서 $X \theta = \mathbf{y}$로 표현합니다!

# Get Least-squared Solution

이 선형 시스템의 해를 얻기 위해 각 행렬을 정규화(normalize) 하는 과정이 필요 합니다. 별건 아니고, 행렬을 풀기 위해 $n \times n$ 정사각 형태로 바꿔주는 작업 입니다.

$$
X^T X \, \theta = X^T \mathbf{y}
$$

이렇게 양변에 $X^T$를 곱해주면, 행렬이 정사각 행렬로 바뀝니다.

아까의 예시에 적용하면,

$$
X^T X =
\begin{bmatrix}
1 & 2 & 3 \\
1 & 1 & 1
\end{bmatrix}
\begin{bmatrix}
1 & 1 \\
2 & 1 \\
3 & 1
\end{bmatrix}
=
\begin{bmatrix}
14 & 6 \\
6 & 3
\end{bmatrix}
$$

$$
X^T \mathbf{y}
=
\begin{bmatrix}
1 & 2 & 3 \\
1 & 1 & 1
\end{bmatrix}
\begin{bmatrix}
2 \\
3 \\
4
\end{bmatrix}
=
\begin{bmatrix}
20 \\
9
\end{bmatrix}
$$

이제 새롭게 구현 정규 방정식을 풀어줍니다.

$$
\begin{bmatrix}
14 & 6 \\
6 & 3
\end{bmatrix}
\begin{bmatrix}
a \\
b
\end{bmatrix}
=
\begin{bmatrix}
20 \\
9
\end{bmatrix}
$$

간단한 $2 \times 2$ 시스템이니 계산은 생략 하겠습니다. 그럼 최종해로 $a = 1$, $b = 1$을 얻을 수 있습니다.

따라서, LS 방법으로 얻는 솔루션 직선은

$$
y = 1 \cdot x + 1
$$

이 됩니다.

## Residual Vector

"잔차 벡터"는 실제 관측값과 근사값의 차이를 말합니다.

$$
\mathbf{r} = \mathbf{y} - X \mathbf{\theta}_{\text{ls}}
$$

살펴본 예제에 대해서 잔차를 구해봅시다.

$$
\begin{bmatrix}
2 \\
3 \\
4
\end{bmatrix}
-
\begin{bmatrix}
1 & 1 \\
2 & 1 \\
3 & 1
\end{bmatrix}
\begin{bmatrix}
1 \\
1
\end{bmatrix}
=
\begin{bmatrix}
2 \\
3 \\
4
\end{bmatrix}
-
\begin{bmatrix}
2 \\
3 \\
4
\end{bmatrix}
= \mathbf{0}
$$

예제에서 살펴본 경우는 잔차가 영벡터 입니다! 이것은 주어진 3개의 데이터 노드가 직선 $y = x + 1$에 완전히 일치 한다는 것을 말합니다! 이렇게 잔차가 없을 수도 있지만, 대부분의 경우는 잔차가 존재합니다 ㅇㅅㅇ

# 맺음말

이어지는 포스트에서는 "**Continuous Least-square Method**"라는 기법을 살펴봅니다! 이 기법에서는 데이터 노드가 아니라 구간 $(a, b)$만 주어집니다!

