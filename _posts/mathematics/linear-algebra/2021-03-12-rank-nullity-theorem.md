---
title: "Rank-Nullity Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
---

# 들어가며

\<**Rank-Nullity Theorem**\>은 선형 대수학의 중요한 정리인 \<Fundamental Theorem of Linear Algebra\>의 정리 중 하나다. 정리에 **선형 대수**의 핵심과 정수를 담고 있다.

Rank-Nullity Theorem을 바로 보는 건 아니고, 몇가지 중간 정리들을 먼저 살펴보자. 아래에 제시되는 정리들도 어떤 의미를 갖는지 이해할 필요가 있다.

# Column Rank = Row Rank

<div class="theorem" markdown="1">

The row space $\mathcal{R}(A)$ and column space $\mathcal{C}(A)$ of a matrix $A$ have the same dimension.

$$
\dim (\mathcal{C}(A)) = \dim (\mathcal{R}(A))
$$

</div>

행렬 $A$에서 row와 column은 정말 별개의 존재다. 아무 관련도 없는 둘이 신기하게도 행공간과 열공간의 차원이 같다고 한다!!

증명을 2가지 버전으로 진행해보겠다. 하나는 Elementary Row Operation으로 얻은 Row-Echelon Form에서 관찰한 특성을 바탕으로 하고, 하나는 Orthogonality를 이용한다.

## Row-Echelon Form

<div class="proof" markdown="1">

행렬 $A \in \mathbb{R}^{m\times n}$가 있다고 하자. 이 행렬은 $m$개 행과 $n$개 열을 갖는다.

이 행렬을 Elementary Row Operation을 적절히 수행해 Row Echelon Form(이하 REF)로 만들 수 있을 것이다.

$$
\left[ \begin{array}{ccccc}
1 & a_0 & a_1 & a_2 & a_3 \\
0 & 0 & 2 & a_4 & a_5 \\
0 & 0 & 0 & 1 & a_6 \\
0 & 0 & 0 & 0 & 0
\end{array} \right]
$$

요런 $4 \times 5$ 행렬이라고 하면, pivot 1, 2, 1이 있는 행이 basis를 이룬다.

여기에서 조금만 더 Elementary Row Operation을 수행하면, 더 심플해진 reduced 형태의 REF를 얻을 수 있다.

$$
\left[ \begin{array}{ccccc}
1 & a_0 & 0 & 0 & a_3 \\
0 & 0 & 2 & 0 & a_5 \\
0 & 0 & 0 & 1 & a_6 \\
0 & 0 & 0 & 0 & 0
\end{array} \right]
$$

사실 pivot 위의 값들이 없어졌을 뿐이다 ㅋㅋ 이 상태에서 행렬을 열공간의 관점으로 보면, pivot이 있는 그 열이 그대로 열공간의 basis를 이룬다.

따라서, 행공간과 열공간의 basis 갯수가 같으므로, 두 공간의 차원(dimension)이 같다. $\blacksquare$

</div>


## Orthogonality

증명을 시작하기 전에, [Wikipedia - Rank(linear algebra)](https://en.wikipedia.org/wiki/Rank_%28linear_algebra%29#Proof_using_orthogonality)의 증명을 참고하고 다듬었음을 밝힌다.

<div class="proof" markdown="1">

행렬 $A \in \mathbb{R}^{m\times n}$가 있다고 하자. 이 행렬은 $m$개 행과 $n$개 열을 갖는다. 그리고 $r$을 행공간 $\mathcal{R}(A)$의 dimension이라고 하자.

그러면, $r$개의 basis를 $\mathcal{R}(A)$에서 정의할 수 있다. 이를 $\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r \in \mathbb{R}^{n}$라고 하자.

우리는 행렬 $A$에 행 basis를 곱한 것들, $A \mathbf{x}_1, A \mathbf{x}_2, \dots, A \mathbf{x}_r \in \mathbb{R}^{m}$이 서로 일차 독립인지 확인하고자 한다.

<br/>

이를 확인하고자 $A \mathbf{x}_i$ 벡터들을 일차 결합한 값이 영벡터가 될 때의 계수 $c_i$가 어떻게 되는지 살펴보자.

$$
c_1 A \mathbf{x}_1 + c_2 A \mathbf{x}_2 + \cdots + c_r A \mathbf{x}_r = \mathbf{0}
$$

그리고 위의 식을 잘 정리해 $\mathbf{v}$라는 일차결합으로 만들어진 벡터를 이름붙인다.

$$
\mathbf{v} = c_1 \mathbf{x}_1 + \cdots c_r \mathbf{x}_r
$$

그러면 식을 정리해 아래와 같이 된다.

$$
A \mathbf{v} = \mathbf{0}
$$

일단 위의 $A \mathbf{v} = \mathbf{0}$은 2가지 의미를 갖는다.

1. 벡터 $\mathbf{v}$가 $A$의 모든 행과 직교(orthogonal)한다.
   1. $A \mathbf{v} = \mathbf{0}$이 되려면, 각 행과 내적한 값이 0이 되어야 하기 때문
2. 벡터 $\mathbf{v}$가 행 basis의 일차 결합이기 때문에, $A$의 행공간에 다시 속하는 벡터다. 1번의 사실은 벡터 $\mathbf{v}$가 자기 자신과도 직교 한다는 것을 말한다.

1번, 2번의 사실에 의해 $\mathbf{v} = \mathbf{0}$라는 결론이 나온다. (자기 자신과 직교한다는 건 영벡터임을 의미한다.) 따라서, 행 basis에 대해 아래 식이 성립한다.

$$
c_1 \mathbf{x}_1 + c_2 \mathbf{x}_2 + \cdots + c_r \mathbf{x}_r = \mathbf{0}
$$

그런데, $\mathbf{x}_i$는 행공간의 basis이므로 위의 일차결합 식이 성립하려면, 모든 계수가 $0$이 되어야 한다.

처음에 "$A \mathbf{x}_i$ 벡터들을 일차 결합"한 것의 계수도 마찬가지로 $0$이 된다는 것을 말하며, $A \mathbf{x}_i$ 벡터들이 모두 일차 독립임을 말한다.

<br/>

$A \mathbf{x}_i$ 벡터들이 모두 일차 독립인 것을 확인했으므로, 실제 열공간의 basis 갯수는 $r$보다 크거나 같을 것이다. 따라서 아래 식이 성립한다.

$$
\dim (\mathcal{R}(A)) = r \le \dim (\mathcal{C}(A))
$$

이 과정을 $A^{T}$에도 동일하게 수행하면, 아래의 식을 얻는다.

$$
\dim (\mathcal{R}(A)) \ge \dim (\mathcal{C}(A))
$$

따라서, 두 부등식에 의해

$$
\dim (\mathcal{R}(A)) = \dim (\mathcal{C}(A))
$$

$\blacksquare$

</div>

요 증명은 기본행연산으로 유도하는 증명보다 좀 어려운 느낌이었다. 그래서 증명을 다시 읽었을 때, 이해가 너무 안 되어서 한번 다시 다듬기도 했다 ㅋㅋ

그래도 아직 이해가 안 되는 것은 $A \mathbf{x}_i \in \mathcal{C}(A)$은 이해가 잘 안 되는 것 같다.


# Rank-Nullity Theorem: Rank + Nullity = $n$

<div class="theorem" markdown="1">

For any $A \in \mathbb{R}^{m \times n}$,

$$
\dim (\mathcal{R}(A)) + \dim (\mathcal{N}(A)) = n
$$

</div>

<div class="proof" markdown="1">

(1) Supp. $\text{rank}(A) = n$, then the only solution for $A \mathbf{x} = 0$ is $\mathbf{x} = 0$ ($\because$ All rows are linearly independent.)

Therefore, nullity $\dim (\mathcal{N}(A)) = 0$, and given equation holds.

(2) Supp. $\text{rank}(A) = r < n$.

Then $\exists$ $n-r$ free variables in the solution of $A \mathbf{x} = 0$.

Then, we can easily get $n-r$ number of vectors in $\mathcal{N}(A)$, by one-hot at position of only one free variable. These $n-r$ number of vectors are linearly independent. Also, these forms null space of $A$!! Therefore, $\dim (\mathcal{N}(A)) = n-r$.

Thus,

$$
\text{rank}(A) + \dim (\mathcal{N}(A)) = r + (n-r) = n
$$

$\blacksquare$

</div>

