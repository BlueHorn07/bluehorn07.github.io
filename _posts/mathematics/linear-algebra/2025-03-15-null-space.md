---
title: "Null Space, and Nullity"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: '동차 시스템을 풀어서 널공간을 찾는 방법에 대해. 그리고 널공간의 차원인 "nullity"에 대해. Unique Solution과 Trivial Solution에 대해서도!'
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

기초 대수가 일차 방정식 $ax + b = 0$이 되는 $x$를 찾고 싶어 하듯이,
선형 대수에서는 행렬 $A$가 주어졌을 때, $A \mathbf{x} = 0$을 만족하는 벡터 $\mathbf{x}$를 찾고 싶어 합니다.

이때, $A \mathbf{x} = 0$를 만족하는 벡터는 여러 개 존재할 수 있는데(아님 아예 없을 수도 있습니다!), 이들을 모아서 정의한 집합(공간)이 "Null Space" 입니다.

<div class="definition" markdown="1">

$$
\text{Null Space} = \left\{ \mathbf{x} \in \mathbb{R}^{m} : A \mathbf{x} = \mathbf{0} \right\}
$$

</div>

Null Space는 벡터 공간 입니다! 그리고 이 공간의 차원을 "**nullity**"라고 부릅니다! 이 용어를 자주 볼일은 없지만, 이후에 선형 대수의 가장 중요한 정리인 "[Rank-Nullity Theorem](/2021/03/12/rank-nullity-theorem/)"에 대해 다룰 때, 다시 봅니다 🙂


# Case Study

## Unique Solution

가장 쉬울 것 같은 Identity Matrix의 경우부터 살펴봅시다!

$$
A = \left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & 1
\end{matrix}\right]
$$

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & 1
\end{matrix}\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
= \mathbf{0}
$$

이것을 만족하는 $\mathbf{x}$는 $x_1 = x_2 = x_3 = 0$인 경우 밖에 없습니다!
그리고, 널공간은 아래와 같이 정의 됩니다.

$$
\text{Null Space}
= \left\{
  \mathbf{0}
\right\}
$$

이때의 널공간의 차원인 nullity는 "0"이 됩니다. (1이 아닙니다!) 차원은 자유도(dof) 같은 개념입니다. Unique Solution이 있다면, 솔루션이 고정된 것이기 때문에 자유도가 없습니다. 그래서 nullity는 0이 됩니다.

## One freedom

이번에는 마지막 행을 전부 0으로 바꾸고 널공간을 구해봅시다!

$$
A = \left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & {\color{red} 0}
\end{matrix}\right]
$$

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & 0
\end{matrix}\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
= \mathbf{0}
$$

이때는 $x_1 = x_2 = 0$이 되고, $x_3$의 값은 결정 되지 않습니다. 그래서 널공간은 아래와 같습니다.

$$
\text{Null Space}
= \left\{ \;
  [0, 0, x_3]^T \quad \text{where} \quad x_3 \in \mathbb{R}
\; \right\}
$$

이때, 널공간의 차원인 nullity는 "1"이 됩니다! 이것은 $x_3$가 어떤 값을 가져도 해가 성립하기 때문 입니다!

## No Solution (Vacant)

참고로 $A \mathbf{x} = \mathbf{0}$인 경우, 항상 Trivial Solution인 $\mathbf{x} = \mathbf{0}$이 존재합니다. 이런 경우를 "동차(homogeneous) 방정식"라고 합니다. (이때, Unique Solution과 Trivial Solution을 구분해서 사용해야 합니다!)

단, $A \mathbf{x} = \mathbf{b}$인 비동차 방정식의 상황에서는 $\mathbf{x}$에 대한 솔루션이 존재하지 않을 수도 있습니다!

## Rectangular Form

지금까지는 행렬 $A$가 $n\times n$으로 정사각 행렬인 경우를 살펴보았습니다. 만약 행렬 $A$가 $n \times m$인 직사각 행렬이라면 어떻게 될까요?

$$
A = \left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
\end{matrix}\right] \in \mathbb{R}^{2 \times 3}
$$

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0
\end{matrix}\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
=
\left[\begin{matrix}
  x_1 \\
  x_2
\end{matrix}\right]
= \mathbf{0}
$$

이때도 $x_1 = x_2 = 0$이 되지만, $x_3$의 값은 결정 되지 않습니다. $x_3$는 솔루션에 아무 영향을 주지 않기 때문에 어떤 값을 가져도 괜찮습니다! 따라서 널공간은 아래와 같습니다.

$$
\text{Null Space}
= \left\{ \;
  [0, 0, x_3]^T \quad \text{where} \quad x_3 \in \mathbb{R}
\; \right\}
$$

nullity는 1이 됩니다.



# Bound of nullity

nullity 값은 행렬 $A$가 어떤 값을 갖는지에 따라 바뀝니다.

행렬 $A$의 열벡터가 모두 일차 독립이라면, Trivial Solution만 남아서 nullity가 0이 되었고,

반대로 행렬 $A$의 열벡터가 모두 종속이라면, 극단적으로는 $A = O$인 영행렬이라면, 가장 큰 자유도를 갖게 됩니다. 이때는 $\mathbf{x} = \mathbb{R}^m$가 되기에, nullity는 $m$이 됩니다.

결국, 정리하면 nullity 값은 아래의 범위를 가집니다.

$$
0 \le \text{nullity} \le m
$$


# 맺음말

널공간에 대해 설명하면서, $A\mathbf{x} = \mathbf{0}$인 동차 방정식을 풀었습니다. 동차 상황을 봤으니, 자연스럽게 "비동차(non-homogeneous)" 경우를 살펴봐야 합니다 😛

➡️ [Solve Non-homogeneous System](/2025/03/15/non-homogenoues-system/)
