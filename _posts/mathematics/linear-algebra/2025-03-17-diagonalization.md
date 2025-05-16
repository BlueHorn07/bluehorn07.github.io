---
title: "Diagonalization"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: ""
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

우리가 행렬 $A$에서 고유값과 고유벡터를 구하는 이유를 잘 알고 있으신가요? 🤨

그 이유는 고유값과 고유벡터는 행렬을 표현하는 또 다른 방법이 되기 떄문입니다! 즉, 고유값과 고유벡터를 제공하기만 해도, 그것을 뽑아낸 원본 행렬을 만들어 낼 수 있습니다!

이번 포스트는 이걸 어떻게 수행하는 방법에 대해서 다룹니다!

# Diagonalizable

<div class="definition" markdown="1">

행렬 $A$는 아래와 같이 분해할 수 있다면, "**대각화 가능한(diagonalizable)**" 행렬이라고 한다.

$$
A = P D P^{-1}
$$

이떄, 각 항목은

- 대각행렬 $D$
  - 행렬 $A$의 고유값이 대각 성분으로 들어갑니다.
- 대각화 행렬 $P$
  - 행렬 $A$의 고유벡터를 열로 가지는 행렬

</div>

행렬의 대각화는 $A$를 분해(decompose)하는 것이고, 이를 통해 그 행렬을 더 잘 이해하고 활용할 수 있습니다.

예를 들어,

$$
A = \left[\begin{matrix}
2 & 1 \\
0 & 1 \\
\end{matrix}\right]
$$

라는 행렬을 대각화 하여,

$$
A = \left[\begin{matrix}
2 & 1 \\
0 & 1 \\
\end{matrix}\right]
=
\left[\begin{matrix}
1 & 1 \\
0 & -1 \\
\end{matrix}\right]

\left[\begin{matrix}
{\color{red} 2} & 0 \\
0 & {\color{red} 1} \\
\end{matrix}\right]

\left[\begin{matrix}
1 & 1 \\
0 & -1 \\
\end{matrix}\right]
$$

이렇게 대각화 해서 보면, $A$의 고유값과 대응되는 고유 벡터를 한번에 볼 수 있습니다 ㅎㅎ 표기가 좀더 간단해지죠!

## Exponential Matrix

행렬을 대각화 하면, 같은 행렬을 반복해서 곱하는 지수곱 $A^n을 쉽게 구할 수 있습니다! 왜냐하면,

$$
\begin{aligned}
A^n
&= (P D P^{-1}) \cdot (P D P^{-1}) \cdots (P D P^{-1}) \\
&= P \cdot D \cdot (P^{-1} P) \cdot D \cdot (P^{-1} P) \cdot D \cdots D \cdot P^{-1} \\
&= P \cdot (D \cdot I \cdots I \cdot D) \cdot P^{-1} \\
&= P D^n P^{-1}
\end{aligned}
$$

이렇게 행렬의 지수곱을 정말 쉽게 구할 수 있습니다! 게다가 가운데의 $D^n$은 그냥 대각 성분에 $\lambda_i^{n}$ 하기만 하면 됩니다 ㅎㅎ

# Condition of Diagonalizable

그런데, "**모든 행렬 $A$가 대각화 할 수 있는게 아닙니다!**" 나이스한 성질을 가진 일부 행렬만 대각화 가능한 것 입니다!

행렬이 대각화 가능하려면, 아래 조건을 만족해야 합니다.

- $A$는 정사각 행렬이어야 합니다.
- $A$는 $n$개의 선형 독립인 고유 벡터를 가져야 합니다.

이때, 고유 벡터에 대한 2번째 조건에서, 고려해야 할 점이 2가지 있습니다!

고유값을 구할 때, $n$차 다항식을 풀게 되는데, 이때 중근이 발생할 수 있습니다!

만약 **중근**을 갖는 경우더라도, 고유 벡터가 다르다면 괜찮습니다! 대각화 가능 합니다.

그런데, 중근인 고유값을 갖는데, 고유 벡터도 중복 된다면, 이때는 다른 방법으로 대각화를 해야 합니다.
이때, 등장하는 개념이 바로 **Jordan Form** $J$ 입니다! $A = P J P^{-1}$ 요건 별도 포스트에서 다루겠습니다!

