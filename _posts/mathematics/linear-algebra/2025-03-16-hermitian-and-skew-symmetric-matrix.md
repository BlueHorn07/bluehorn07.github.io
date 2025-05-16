---
title: "Hermitian Matrix, and Skew-symmetric Matrix"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: ""
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

지난 포스트에서 이차 형식에 대해 살펴보았습니다 ㅎㅎ 이번 포스트도 이차 형식에 대해 살펴보는데요!
이차형식에서 보이는 2가지 특수 케이스를 살펴봅니다!

# Does Complex Matrix

지금까지는 이차형식 $Q(\mathbf{x})$를 실수 행렬에 대해 정의하고 살펴보았습니다.
이것을 "복소 행렬"로 확장하고, 여기 위에서 Positive Definite를 정의 합시다!

<div class="definition" markdown="1">

**Positive Definite**, when for $A \in \mathbb{C}^{n\times n}$ and for all non-zero $\mathbf{x} \in \mathbb{R}^n$,

$$
Q(\mathbf{x}) = \mathbf{x}^{T} A \mathbf{x} > 0
$$

</div>

저는 처음에 개념을 이해할 때, 잘못 이해해서 입력 벡터도 복소 벡터인 줄 알았습니다 ㅠㅠ **입력 벡터는 여전히 실수 벡터** 입니다!

어떤 복소 행렬이 Positive Definite를 만족할까요? 실수 영역에서 Positive Definite 였던 행렬들은 복소 영역에서도 Positive Definite 일까요?

## Hermitian Matrix

복소 행렬 $A$이 Positive Definite를 만족하기 위해선 아래 조건이 필요 합니다.

- 행렬 $A$가 정사각 행렬
- 행렬 $A$가 Hermitian Matrix

이때, 에르미트 행렬은 아래와 같이 정의하는 복소 행렬 입니다.

<div class="definition" markdown="1">

$$
A = A^{\ast}
$$

</div>

이것은 "켤레 전치"가 같은 행렬을 말합니다! 예시를 들어보면,

1\. 실수 대칭 행렬은 자동으로 에르미트 행렬이 됩니다!

$$
A = \begin{bmatrix}
2 & 3 \\
3 & 5
\end{bmatrix}
$$

2\. 켤레 전치가 같아야 합니다.

$$
A = \begin{bmatrix}
2 & 1 + i \\
1 - i & 5
\end{bmatrix}
$$

## When inputs complex vector

행렬 $A$가 Positive Definite는 입력이 실수 벡터인 경우에 정의하는 것 입니다.
입력 벡터가 복소 벡터라면, 이차 형식의 값이 음수가 될 수도 있습니다!

예를 들어, 이런 Positive Definite 행렬 $A$가 있을 때,

$$
A = \left[\begin{matrix}
2 & 0 \\
0 & 1 \\
\end{matrix}\right]
$$

이 행렬의 이차 형식은 모든 실수 벡터에 대해 $Q(\mathbf{x}) > 0$을 만족하지만, 복소 벡터의 경우

$$
Q([i, 0]^T)
= [i, 0]
\left[\begin{matrix}
2 & 0 \\
0 & 1 \\
\end{matrix}\right]
\left[\begin{matrix}
i \\
0 \\
\end{matrix}\right] = i \cdot 2 \cdot i = -2
$$

음수가 나옵니다. 또는 복소 벡터에서는 복소수가 나옵니다.

그래서 Positive Definite는 행렬 $A$는 복소 행렬일 수도 있지만, 오직 실수 벡터를 입력으로 받는 경우를 기준으로 정의합니다!



# Skew-symmetric Matrix

이 행렬은 행렬 $A$가 아래 조건을 만족합니다.

<div class="definition" markdown="1">

$$
A^T = - A
$$

</div>

뭔가 신기하죠?? 그런데 행렬이 이것을 만족하기 위해서는 모든 대각 성분이 0이 되어야 합니다! 왜냐하면, 대각 성분은 전치 되어도 그 자리에 남아 있으니까요!

$$
A = \left[\begin{matrix}
0 & 3 \\
-3 & 0 \\
\end{matrix}\right]
$$

이런 행렬을 Skew-symmetric, "반대칭 행렬"이라고 합니다.

<br/>

행렬 $A$가 반대칭 행렬이라면, 이차형식 $Q(\mathbf{x})$의 값이 항상 0이 됩니다!

<div class="theorem" markdown="1">

$$
\begin{aligned}
Q(\mathbf{x})
&= \mathbf{x}^T A \mathbf{x} \\
&= \mathbf{x}^T (-A^T) \mathbf{x} \\
&= \mathbf{x}^T (-A) \mathbf{x} \quad (\text{symmetric})\\
&= - Q(x)
\end{aligned}
$$

</div>

$Q(\mathbf{x}) = -Q(\mathbf{x})$를 만족하려면, 이차형식의 값이 항상 0이 되어야 합니다.

이차 형식의 결과가 항상 0이 되어버리기 때문에, Skew-symmetric 행렬로 이차 형식을 해석하지는 않는다고 합니다!

<br/>

반대칭 행렬은 "**회전 변환**"을 수행합니다! 나머지 내용은 To be continued...
