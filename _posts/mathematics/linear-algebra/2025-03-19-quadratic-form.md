---
title: "Quadratic Form"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: ""
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

이차 형식을 간단히 설명하면, "이차식"을 행렬의 형태로 표현하는 것을 말합니다.

예를 들어, 아래와 같은 이차식이 있을 때,

$$
3 x^2 + 2 xy + 4 y^2
$$

이것을 행렬로 표현하면,

$$
\left[\begin{matrix}
x & y
\end{matrix}\right]

\left[\begin{matrix}
3 & 1 \\
1 & 4 \\
\end{matrix}\right]

\left[\begin{matrix}
x \\
y \\
\end{matrix}\right]
$$

신기하죠? 그래서 이렇게 행렬로 표현한 이차식을 아래와 같이 표현하는 것을 "Quadratic Form"이라고 합니다.

<div class="definition" markdown="1">

$$
Q(\mathbf{x})
= \mathbf{x}^T A \mathbf{x}
$$

</div>

이때, 이차 형식을 이루는 행렬 $A$는 반드시 "**대칭 행렬**"이어야 합니다!

# Diagonalizing the Quadratic Form

이전에 "대각화"와 "직교-대각화"에 대해서 살펴보았던 것을 기억하나요?

$$
A = P D P^{-1} \quad \text{or} \quad A = P D P^T
$$

그리고 행렬 $A$가 대칭 행렬이라면, "**스펙트럴 정리**"에 의해 직교-대각화가 가능했고, 모든 고유값은 실수값을 가집니다.

<br/>

이차형식은 "대칭 행렬"에 대해서만 성립하기 때문에, 이 성질을 바탕으로 "**이차 형식의 간소화**"를 수행할 수 있습니다!

바로 살펴봅시다.

$$
\begin{aligned}
Q(\mathbf{x})
&= \mathbf{x}^T A \mathbf{x} \\
&= \mathbf{x}^T (P D P^T) \mathbf{x} \\
&= (\mathbf{x}^T P) \, D \, (P^T \mathbf{x})
\end{aligned}
$$

여기에서 $\mathbf{y} = P^T \mathbf{x}$로 변수 치환을 하면, 아래와 같고,

$$
Q(\mathbf{x}) = \mathbf{y}^T D \mathbf{y}
$$

위의 식을 전개하면,

$$
Q(\mathbf{x}) = \lambda_1 y_1^2 + \cdots + \lambda_n y_n^2
$$

가 됩니다! ~~정말 쉽죠?!~~

## Principal Axis Theorem

이차형식을 대각화 하여 간소화 한 지금까지의 과정은 사실 "주축 정리"입니다 ㅋㅋ

<div class="theorem" markdown="1">

어떤 실수 대칭 행렬 $A$에 대해서, 직교 행렬 $P$를 사용하여,

그것의 이차 형식 $Q(\mathbf{x}) = \mathbf{x}^T A \mathbf{x}$를 교차항 없는, 대각 형태로 바꿀 수 있다.

</div>

행렬 $A$의 이차 형식은 $\mathbf{x} = [x_1, x_2, \dots, x_n]^T$ 변수를 기준으로 작성 됩니다. 이것을 대각화 하면, 값들이 주축(Principal Axis) 방향의 좌표계로 정렬(=회전) 합니다. 이때, **주축은 고유 벡터의 모음** 입니다(대각화 행렬 $P$)!

즉, 기존의 이차식을 대각화를 통해 고유 벡터라는 주축 방향으로 변환 하고, 이를 통해 식을 가장 단순한 형태로 만들어 줍니다.



# Positive/Negative Definite

이차형식 $Q(x)$을 표현할 때 행렬을 사용하지만, 사실 그 값은 실수값 입니다! (이차식에서 나왔으니까 당연 ㅋㅋ)

$$
Q(\mathbf{x}) \in \mathbb{R}
$$

이차형식은 $Q(\mathbf{x})$ 일종의 함수이기 때문에, 입력되는 $\mathbf{x}$의 값에 따라서, 각각 다른 값이 나올 것 입니다.
예를 들어, 처음 예시 사용한 $A$를 기반으로 하는 이차 형식은

$$
Q([0, 0]^T) = 0, \quad Q([1, 1]^T) = 9
$$

이때, 어떤 이차식(이차형식)은 어떤 $\mathbf{x} \ne \mathbf{0}$을 넣어도 그 값이 양수가 나오거나, 음수가 나오는 경우가 있습니다!
선형대수에서는 이런 경우에 대해 "Positive Definite(영의 정부호)"라고 분류 합니다.

<div class="definition" markdown="1">

**Positive Definite** when for all $\mathbf{x} \ne \mathbf{0}$,

$$
Q(\mathbf{x}) = \mathbf{x}^T A \mathbf{x} > 0
$$

</div>

"행렬 $A$가 Positive Definite다", 또는 "이차형식이 Positive Definite다"라고 합니다. (둘다 상관 x) Negative Definite는 반대로 어떤 값이 입력 되던지 상관 없이 음수가 나오는 경우를 말합니다.<br/>
<small>(사실 Positive Definite $Q(\mathbf{x}) > 0$인 $A$에 $-1$을 곱해주면, 바로 Negative Definite가 됩니다.)</small>

<br/>

행렬 $A$가 Positive Definite 되려면 아래의 조건을 만족해야 합니다.

- 행렬 $A$가 정사각 행렬이고,
- 행렬 $A$가 대각화 가능해야 하고,
- 행렬 $A$가 대칭 행렬이고,
  - = 직교-대각화 가능
- 행렬 $A$의 **모든 고유값이 양수**여야 함.

결국, 행렬 $A$가 Positive Definite 인지 판단하려면 귀찮게 이차형식 $Q(\mathbf{x})$를 만들어서 지지고 볶고 할 것 없이 고유값의 부호만 체크하면 된다는 것 입니다! 😲

## Semi-definite, and Indefinite

이차 형식의 값이 항상 양수가 아니라 0을 포함하여 양수가 될 수도 있습니다.

<div class="definition" markdown="1">

**Positive semi-definite** when for all $\mathbf{x} \ne \mathbf{0}$,

$$
Q(\mathbf{x}) {\color{red} \ge} 0
$$

</div>

이런 경우를 "Positive semi-definite"(양의 반정부호)라고 하며,
이때는 모든 고유값이 $\lambda_i \ge 0$인 경우에 이렇게 됩니다.
semi-definite일 때는 어떤 벡터를 잘 고르면, $Q(\mathbf{x}) = 0$가 될 수 있습니다.

<br/>

마지막으로 고유값이 한 방향으로 정렬되지 않고, 양수/음수가 둘다 존재할 수도 있습니다. 이 경우는 "Indefinite"라고 분류 합니다.

<div class="definition" markdown="1">

**Indefinite** when

for some $\mathbf{x}_1 \ne \mathbf{0}$,

$$
Q(\mathbf{x}_1) > 0
$$

and for some $\mathbf{x}_2 \ne \mathbf{0}$,

$$
Q(\mathbf{x}_2) < 0
$$

</div>

# Geometric Interpretation

요 부분도 이차 형식에서 중요한 파트이긴 한데... 이제 좀 지쳐서 스킵 하겠습니다...  ㅋㅋ


# 맺음말

![](/images/meme/oh-that-is-not-easy.png){: .fill .align-center style="min-width: 300px; width: 60%" }

학부 수업 때, 처음 이차 형식을 만났을 때는 잘 이해가 안 되는 파트 였습니다!
그래도 당시에 어영부영 열심히 인터넷을 뒤져가면서 이해해보려고 노력 했던 기억이 나는데요 ㅋㅋ
다시 돌아와서 이 부분을 보니까 옛날 생각이 문득 났네요 ㅎㅎ
