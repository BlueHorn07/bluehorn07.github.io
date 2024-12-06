---
title: "Banach Space"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "완비성(Completeness)와 힐베르트 공간, 그리고 바나흐 공간에 대해"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 완비성

완비성(Completeness)은 빈틈이 없는 공간을 말합니다. 이를 보이기 위해서는 코시(Cauchy) 수열이라는 것을 정의하고, 그것의 수렴값이 공간에 다시 포함되는지를 살펴보아야 합니다.

<div class="definition" markdown="1">

**[Cauchy Sequence]**

A Sequence that each terms getting closer.

For any $\epsilon$, there always exist large enough numbers $n$ and $m$, that satisfies

$$
d(x_n, x_m) < \epsilon
$$

where $d$ is a distance function defined on the given space.

</div>

코시 수열의 정의는 결국 그 수열이 어떤 값이 수렴한다는 것을 말한다: $\lim_{n\rightarrow \infty} a_n = \alpha$. 그리고 완비성은 그 수렴값 $\alpha$가 수렴이 정의된 공간에 다시 포함된다는 것을 말한다.

$$
\lim_{n\rightarrow \infty} a_n = \alpha \in S
$$

## 실수의 완비성

실수 공간 $\mathbb{R}$ 위에서 정의되는 모든 실수 코시 수열은 $\mathbb{R}$에 수렴한다는 성질입니다. 요 개념은 유리수 집합 $\mathbb{Q}$ 위에서 완비성을 생각해보면 쉽게 이해가 됩니다.

유리수의 집합 $\mathbb{Q}$ 완비성을 갖추지 못합니다. 왜냐하면, 아래와 같이 정의한 코시 수열이 $\mathbb{Q}$에 수렴하지 않고, 무리수에 수렴하기 때문입니다.

$$
x_1 = 1, \quad x_2 = 1.4, \quad x_3 = 1.41, \quad x_4 = 1.414, ...
$$

위 수렴은 $\sqrt{2}$에 수렴하지만, $\sqrt{2} \notin \mathbb{Q}$이므로 유리수 집합은 완비성 집합이 아닙니다. 반면에 같은 수열을 $\mathbb{R}$ 위에서 정의했다면, 그 수렴값은 다시 $\mathbb{R}$에 속하므로 완비성 집합라고 볼 수 있습니다.

완비성을 엄밀히 정의하고 증명하는 건 요 포스트의 목표가 아니기 때문에, 다음 주제로 넘어가겠습니다 ㅎㅎ


# 힐베르트 공간

벡터 공간 $V$와 그 위에서 정의된 내적 연산 $\cdot$이 있다고 합시다. 이것을 "**내적 공간(inner product space)**"라고 합니다. 내적 공간에서는 벡터의 덧셈, 스칼라곱, 벡터 길이와 각도에 대한 개념이 정의됩니다.

힐베르트 공간은 내적 공간이면서 완비성을 갖춘 공간입니다. 즉, 힐베르트 공간 위에서 정의된 코시 수열의 수렴값이 항상 힐베르트 공간 내에 수렴하는 성질을 갖습니다.

## 내적 공간이지만, 완비성을 갖추지 못한 공간

완비성에 대해 살펴보던 것처럼 반례를 먼저 살펴봅시다. 가장 간단히 생각할 수 있는 예시는 $\mathbb{Q}^n$ 공간입니다. 이 공간은 내적 공간으로 내적에 대한 성질은 만족합니다만, $\mathbb{Q}^n$에서 정의한 벡터 수열의 수렴값이 $\mathbb{Q}^n$에 속하지 않을 수도 있습니다.

<br/>

구간 $[a, b]$에서 정의된 모든 연속 함수들의 집합 $C[a, b]$를 생각해봅시다. 이 집합은 내적 공간이면서 비-힐베르트 공간입니다. 이 공간에서는 함수 내적을 정의합니다. 이는 아래와 같이 정의됩니다.

$$
<f, g> = \int_a^b f(x) g(x) \, dx
$$

그러나 이 공간은 완비성을 갖추지 않습니다. 예시를 들어보면, $C[-1, 1]$ 공간 위에 아래와 같은 코시 수열 $\\{ f_k \\}_k$을 정의하면, 이 함수의 수렴값이 불연속 함수로 수렴하기 때문입니다.

$$
f_k(t) = \begin{cases}
0 & t \in [-1, 0] \\
1 & t \in [\frac{1}{k}, 1] \\
kt & t \in (0, \frac{1}{k})
\end{cases}
$$

이 함수는 $1/k \rightarrow 0$으로 수렴하면서, $x = 0$ 지점에서 불연속이 발생합니다.


## 힐베르트 공간의 예시

이번에는 내적 공간이면서 완비성을 갖춘 힐베르트 공간의 예시를 살펴봅시다.

가장 쉽게 떠올릴 수 있는 건 $\mathbb{R}^n$ 공간입니다. $\mathbb{Q}^n$와 달리 수열의 수렴값이 여전히 다시 $\mathbb{R}^n$에 속합니다.

또, 아래와 같이 정의한 함수 공간 $L^2$도 힐베르트 공간입니다.

<div class="definition" markdown="1">

**[$L^2$ Lebesgue Space]**

함수의 제곱이 적분 가능한 함수들의 집합입니다. 어떤 함수 $f(x)$가 아래 조건을 만족한다면, $L^2$ 공간에 속합니다. $f(x) \in L^2[a, b]$ when ...

$$
\int_a^b \| f(x) \|^2 \, dx < \infty
$$

이것은 구간 $[a, b]$가 아니라 실수 전체 $\mathbb{R}$에서도 정의할 수 있습니다.

$$
\int_{-\infty}^{\infty} \| f(x) \|^2 \, dx < \infty
$$

이런 함수는 표기는 $f(x) \in L(\mathbb{R})$로 표기합니다.

</div>

$L^2$가 힐베르트 공간인지 엄밀히 증명하는 건 스킵 하겠습니다!! ~~나는 컴공과니까!!~~


# 바나흐 공간

바나흐 공간의 정의는 "완비성을 갖춘 노름(norm) 공간"입니다. 그리고 모든 힐베르트 공간은 바나흐 공간이라고 합니다. 그런데 두 공간의 정의가 아주 비슷해 보입니다!

- 바나흐 공간: 완비성을 갖춘 노름 공간
- 힐베르트 공간: 완비성을 갖춘 내적 공간

## 노름 공간과 내적 공간

처음에는 이게 조금 헷갈렸습니다만, 쉽게 생각하면 쉽습니다.

"노름(norm)"은 단순히 벡터의 "크기"를 측정하는 함수입니다. 몇가지 성질을 갖는데 당연한 써오던 것들입니다.

- Positive sign: $\| x \| > 0$
- Zero sign means zero vector: $\| x \| = 0 \iff x = 0$
- Scala Product: $\| ax \| = \| a \| \cdot \| x \|$
- Triangular Inequality: $\| x + y \| \le \| x \| + \| y \|$

<br/>

내적은 벡터 간의 "관계"(각도, 직교성)을 측정하는 함수이자 도구입니다.

우리는 내적을 이용해 항상 노름을 정의할 수 있으므로, 모든 내적 공간은 노름 공간입니다.

$$
\| x \| = \sqrt{<x, x>}
$$

반면에, 모든 노름 공간이 내적 공간이 되는 것은 아닙니다.


## 바나흐 공간이지만, 힐베르트 공간은 아닌 예시

아무튼 내적 공간이 더 강한 조건이기 때문에, 모든 힐베르트 공간은 노름 공간인 바나흐 공간이라고 할 수 있습니다. 다만, 바나흐 공간 중에서는 힐베르트 공간이 되지 못하는 예시들도 있습니다.

<div class="definition" markdown="1">

**[$L^1[a, b]$ Lebesgue Space]**

이 함수 공간에서의 노름은 아래와 같이 정의됩니다.

$$
\| f \|_1 = \int_a^b \| f(x) \| \, dx
$$

그러나 함수 내적이 정의되지 않습니다.

</div>

<div class="definition" markdown="1">

**[$L^{\infty}[a, b]$ Lebesgue Space]**

이 함수 공간에서의 노름은 아래와 같이 정의됩니다.

$$
\| f \|_{\infty} = \sup x \in \| f(x \in [a, b]) \|
$$

그러나 함수 내적이 정의되지 않습니다.

</div>

노름 공간에서 어떤 내적도 정의할 수 없다는 것을 보이려면 노름이 아래의 "**평행사변형 법칙**"을 만족하는지를 보이면 됩니다. 노름으로 내적을 정의할 수 있었다면, 평행사변형 법칙을 만족하기 때문입니다.

$$
\| f(x) + g(x) \|^2 + \| f(x) - g(x) \|^2
= 2 \| f(x) \|^2 + 2 \| g(x) \|^2
$$


# References

- [Wikipedia: Inner Product Space](https://en.wikipedia.org/wiki/Inner_product_space)

