---
title: "Rank-Nullity Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: "선형 변환에서 보존되는 정보/차원(Rank)과 손실된 정보/차원(Nullity)의 관계를 말하는 선형 대수의 기본 정리. 그리고 선형변환에서 행공간과 열공간의 관계에 대해. 전혀 관련이 없어 보이는 두 공간은 같은 차원은 가진다!"
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

\<**Rank-Nullity Theorem**\>은 선형 대수학의 중요한 정리인 \<Fundamental Theorem of Linear Algebra\>의 정리 중 하나다. 정리에 **선형 대수**의 핵심과 정수를 담고 있다.

Rank-Nullity Theorem을 바로 보는 건 아니고, 몇가지 중간 정리들을 먼저 살펴보자. 아래에 제시되는 정리들도 어떤 의미를 갖는지 이해할 필요가 있다.

# Row space and Column Space have same dimension

<div class="theorem" markdown="1">

The row space $\mathcal{R}(A)$ and column space $\mathcal{C}(A)$ of a matrix $A$ have the same dimension.

$$
\dim (\mathcal{C}(A)) = \dim (\mathcal{R}(A))
$$

</div>

행렬 $A$에서 row와 column은 정말 별개의 존재다. 그냥 행렬이라서 모여 있을 뿐인 두 공간이 신기하게도 차원이 같다고 한다!!

증명을 2가지 버전으로 진행해보겠다. 하나는 Elementary Row Operation으로 얻은 Row-Echelon Form에서 관찰한 특성을 바탕으로 하고, 하나는 Orthogonality를 이용한다.

## Row-Echelon Form

<div class="proof" markdown="1">

행렬 $A \in \mathbb{R}^{m\times n}$가 있다고 하자. 이 행렬은 $m$개 행과 $n$개 열을 갖는다.

이 행렬을 Elementary Row Operation을 적절히 수행해 Row Echelon Form(이하 REF)로 만들 수 있을 것이다.

$$
\left[ \begin{array}{ccccc}
{\color{red} 1} & a_{12} & a_{13} & a_{14} & a_{15} \\
0 & 0 & {\color{red} 2} & a_{24} & a_{25} \\
0 & 0 & 0 & {\color{red} 1} & a_{35} \\
0 & 0 & 0 & 0 & 0
\end{array} \right]
$$

요런 $4 \times 5$ 행렬이라고 하면, pivot 1, 2, 1이 있는 행이 basis를 이룬다.

여기에서 조금만 더 Elementary Row Operation을 수행하면, 더 심플해진 reduced 형태의 REF를 얻을 수 있다.

$$
\left[ \begin{array}{ccccc}
{\color{red} 1} & a'_{12} & 0 & 0 & a'_{15} \\
0 & 0 & {\color{red} 2} & 0 & a'_{25} \\
0 & 0 & 0 & {\color{red} 1} & a_{35} \\
0 & 0 & 0 & 0 & 0
\end{array} \right]
$$

사실 pivot 위의 값들이 없어졌을 뿐이다 ㅋㅋ 이 상태에서 행렬을 열공간의 관점으로 보면, pivot이 있는 그 열이 그대로 열공간의 basis를 이룬다.

따라서, 행공간과 열공간의 basis 갯수가 같으므로, 두 공간의 차원(dimension)이 같다. $\blacksquare$

</div>


## Orthogonality

Wikipedia의 [Rank(linear algebra)](https://en.wikipedia.org/wiki/Rank_%28linear_algebra%29#Proof_using_orthogonality)의 증명을 참고하고 다듬었음을 밝힌다.

<div class="proof" markdown="1">

행렬 $A \in \mathbb{R}^{m\times n}$가 있다고 하자. 이 행렬은 $m$개 행과 $n$개 열을 갖는다.

그리고 $r$을 행공간 $\mathcal{R}(A)$의 dimension이라고 하자. 그러면, $r$개의 basis를 정의할 수 있다. 이를 $\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r \in \mathcal{R}(A) \subseteq \mathbb{R}^{n}$라고 하자.

행렬 $A$에 행 basis를 곱한 것들은 $A \mathbf{x}_1, A \mathbf{x}_2, \dots, A \mathbf{x}_r \in \mathcal{C}(A) \subseteq \mathbb{R}^{m}$, 결과 벡터가 열공간에 속합니다.<br/>
<small>(처음에 왜 열공간에 속하는지가 헷갈렸는데, $A \mathbf{x}_i$는 $n$개 열벡터의 선형 결합을 하는 것이기 때문에, 그 결과가 열공간 $\mathcal{C}(A)$에 속합니다.)</small>

만약 $A\mathbf{x}_i$ 열벡터들이 서로 선형 독립임을 보일 수 있다면, 열공간의 차원이 최소한 행공간의 차원 $r$보다는 크거나 같다는 것을 의미합니다. 즉, $\dim (\mathcal{C}(A)) \ge r = \dim (\mathcal{R}(A))$.

그리고 같은 과정을 반대로 $A^T$에 대해서도 적용하면, 반대의 부등식 $\dim (\mathcal{C}(A)) \le \dim (\mathcal{R}(A))$를 얻고, 최종적으로 $\dim (\mathcal{C}(A)) \ge r = \dim (\mathcal{R}(A))$인 결과를 얻습니다.


<br/>

열공간의 벡터가 된 $A \mathbf{x}_i$들이 선형 독립인지 확인해봅시다. 만약, 선형 독립이라면 $r$개의 선형 독립인 벡터를 찾았으므로 열공간의 차원은 적어도 $r$보다는 큽니다. <br/>
<small>(등호 $=$가 아니라 $\ge$가 되는 이유는 열공간에 $A \mathbf{x}_i$로 유도되지 않는 기저가 존재할 수 있기 때문입니다.)</small>

선형 독립을 확인하기 위해, 선형 결합이 영벡터가 될 때의 계수 $c_i$가 어떻게 되는지 살펴봅시다.

$$
c_1 A \mathbf{x}_1 + c_2 A \mathbf{x}_2 + \cdots + c_r A \mathbf{x}_r = \mathbf{0}
$$

위의 선형 독립의 식을 잘 정리하면, $A \mathbf{v} = \mathbf{0}$가 되는, $\mathbf{v}$를 정의할 수 있습니다. $\mathbf{v}$는 $\mathbf{x}_i$의 선형결합으로 만들어진 벡터 입니다.

$$
\mathbf{v} = c_1 \mathbf{x}_1 + \cdots c_r \mathbf{x}_r
$$

1. $A \mathbf{v} = \mathbf{0}$라는 것은 벡터 $\mathbf{v}$가 $A$의 모든 행과 직교(orthogonal) 합니다.
   - $A \mathbf{v} = \mathbf{0}$이 되려면, 각 행과 내적한 값이 0이 되어야 하기 때문입니다.
2. 모든 행과 직교한다는 것은, 행공간 $\mathcal{R}(A)$ 전체와 직교한다는 것을 말합니다.
3. 벡터 $\mathbf{v}$는 행 basis의 선형 결합이기 때문에, 다시 $A$의 행공간에 속하는 벡터 입니다.
4. 따라서, $\mathbf{v}$는 자기 자신과도 직교 해야 합니다!
   - 자기 자신도 행공간에 속하는 벡터이기 때문입니다.

결론인 4번에서 "**자기 자신과 직교**"한다는 건 영벡터임을 의미합니다. 따라서, $\mathbf{v} = \mathbf{0}$입니다.

$$
\mathbf{v} = c_1 \mathbf{x}_1 + c_2 \mathbf{x}_2 + \cdots + c_r \mathbf{x}_r = \mathbf{0}
$$

그런데, $\mathbf{x}_i$는 행공간의 basis이므로, 기저에 대한 정의에 의해 모든 계수 $c_i$가 $0$이 되어야 합니다.

이것은 $A \mathbf{x}_i$ 사이의 선형 독립을 확인하기 위해 처음에 세웠던 식에 대해서,

$$
c_1 A \mathbf{x}_1 + c_2 A \mathbf{x}_2 + \cdots + c_r A \mathbf{x}_r = \mathbf{0}
$$

$r$개 열벡터 $A \mathbf{x}_i$가 서로 선형 독립임을 말합니다!

<br/>

$r$개 열벡터 $A \mathbf{x}_i$가 선형 독립인 것을 확인했고, 실제 열공간의 기저 갯수는 $r$보다 크거나 같을 것 입니다. (우리가 발견하지 못한 기저가 남았을 수 있기 때문입니다.) 따라서 아래 식이 성립합니다.

$$
\dim (\mathcal{R}(A)) = r \le \dim (\mathcal{C}(A))
$$

이 과정을 $A^{T}$에도 동일하게 수행하면, 아래의 식을 얻습니다.

$$
\dim (\mathcal{R}(A)) \ge \dim (\mathcal{C}(A))
$$

따라서, 두 부등식에 의해

$$
\dim (\mathcal{R}(A)) = \dim (\mathcal{C}(A))
$$

$\blacksquare$

</div>

요 증명은 기본행연산으로 유도하는 증명보다 좀더 길고, 선형대수에 대한 단단한 이해가 필요합니다. 저도 처음에 이 증명을 적고, 잘 이해가 안 되어서 몇 번씩 고쳐 적은게 지금의 형태 입니다.

손으로 직접 증명을 써내려가면서 익히고, 각 과정의 의미를 곱씹으면서 이해하면, 정리도 이해할 수 있고 선형대수에 대한 이해도 더 깊어지는 그런 좋은 정리 입니다 ㅎㅎ



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

