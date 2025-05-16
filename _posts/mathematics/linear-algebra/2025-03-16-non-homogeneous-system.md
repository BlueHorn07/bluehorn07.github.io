---
title: "Solve Non-homogeneous System"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: ""
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

널공간(null space)에 대해 살펴보면서, 몇가지 동차 방정식 $A\mathbf{x} = \mathbf{0}$을 풀고, 그것의 널공간과 nullity를 구해보았습니다.

자연스럽게! 이번에는 비동차 방정식의 상황을 살펴봅시다. 이번 포스트에서는 아래의 경우를 살펴봅니다!

$$
A \mathbf{x} = \mathbf{b}
$$

# Case Study

## Unique Solution

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & 1
\end{matrix}
\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
=
\left[\begin{matrix}
  1 \\
  2 \\
  3
\end{matrix}\right]
$$

정말 간단한 경우를 가져왔습니다 ㅋㅋ 이 경우, $\mathbf{x} = [1, 2, 3]^T$으로 구해집니다. 비동차 방정식에서도 Unique Solution인 경우가 존재할 수 있습니다 ㅎㅎ

## No Solution

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & {\color{red} 0}
\end{matrix}
\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
=
\left[\begin{matrix}
  1 \\
  2 \\
  3
\end{matrix}\right]
$$

이번에는 $A$의 마지막 행을 전부 0으로 채웠습니다. 그런데, 이 경우는 마지막 행에 대해서 $0 = 3$라는 결과가 나옵니다! 이 경우, 이 선형 시스템은 잘못 구성 되었다는 것을 말하며, 이 때 $A \mathbf{x} = \mathbf{b}$를 만족하는 솔루션 $\mathbf{x}$는 존재하지 않는다!고 마무리 합니다.

참고로 $A\mathbf{x} = \mathbf{b}에 대한 $방정식을 귀찮게 풀지 않아도 바로 Solution의 존재하는지 아닌지를 판단할 수 있는데요! 그건 뒤에서 나옵니다 😛

## One Nullity

$$
A \mathbf{x} =
\left[\begin{matrix}
1 & 0 & 0 \\
0 & 1 & 0 \\
0 & 0 & {\color{red} 0}
\end{matrix}
\right]
\left[\begin{matrix}
  x_1 \\
  x_2 \\
  x_3
\end{matrix}\right]
=
\left[\begin{matrix}
  1 \\
  2 \\
  {\color{red} 0}
\end{matrix}\right]
$$

이번에는 방정식을 풀었을 때, $x_3$ 변수가 자유 변수가 됩니다!

이제, 이 시스템의 널공간을 구해보면, $\mathbb{R}^m$이 됩니다. 그래서 nullity는 "1"이 됩니다! (동차 시스템의 경우가 비슷하죠? ㅎㅎ)

<br/>

그런데 여기에서 솔루션을 표현하는 중요한 표기가 등장합니다!

비동차 방정식의 솔루션 $\mathbf{x}$는 아래와 같은 형태로 작성해야 합니다.

<div class="definition" markdown="1">

$$
\mathbf{x} = \mathbf{x}_p + \mathbf{x}_h
$$

</div>

이때, $\mathbf{x}_p$는 특정해(particular solution), 그리고 $\mathbf{x}_h$는 동차해입니다. 시스템의 솔루션은 "특정해와 동차해"의 결합해 구성 합니다.

이제 살펴본 비동차 시스템을 기준으로 최종해를 작성해보면,

$$
\mathbf{x} =
\left[\begin{matrix}
  1 \\
  2 \\
  0
\end{matrix}\right]
+
t \cdot \left[\begin{matrix}
  0 \\
  0 \\
  1
\end{matrix}\right]
\quad \text{where} \quad t \in \mathbb{R}
$$


# Condition of Solution Existence

비동차 시스템에서는 방정식을 전개하기도 전에 솔루션의 존재 여부를 바로 판단하는 방법이 있습니다!

<div class="theorem" markdown="1">

There's no solution if $\mathbf{b} \notin \text{Col}(A)$.

<hr/>

Non-homogeneous system has solutions when $\mathbf{b} \in \text{Col}(A)$

</div>

즉, 비동차 시스템의 $\mathbf{b}$가 행렬 $A$의 열벡터의 선형 결합으로 표현할 수 없는 벡터라면 솔루션이 존재하지 않게 됩니다!

정리를 증명하는 것도 별로 어렵지 않습니다! (1학년 때는 어려웠던 것 같은데... ㅋㅋ)

<div class="proof" markdown="1">

행렬 $A$를 열벡터의 형태로 표현하면, $A = [\mathbf{a}_1, \mathbf{a}_2, \dots, \mathbf{a}_n]$이 됩니다. 그리고 이 표기로 비동차 시스템을 표현하면,

$$
x_1 \mathbf{a}_1  + x_2 \mathbf{a}_2  + \cdots
x_n \mathbf{a}_n  = \mathbf{b}
$$

즉, 비동차 시스템은 결국 열벡터의 선형 결합에 대한 방정식 입니다.

여기에서 해가 존재한다, 즉 등식이 성립하려면, 벡터 $\mathbf{b}$가 열벡터들의 선형결합으로 표현 가능해야 하고, 솔루션을 찾는 것은 등식을 만족하는 어떤 결합의 값 $\mathbf{x}$를 찾는 것 입니다.

</div>

# 맺음말

![](/images/meme/perfect-understand.png){: .fill .align-center }

1학년 때 선형 대수를 들을 때는, 동차니 비동차니 용어도 생소하고 경험치도 적어서 진짜 머리에 어떻게든 집어 넣어서 이해 했던 것 같은데요 ㅋㅋ

이제는 수학과 과목을 좀 들어서 그런지 초반 부분은 금방 적응이 되네요 ㅎㅎ 하지만, 아직 무시무시한 뒷부분이 남았으니... 좀더 화이팅 해봅시다!

