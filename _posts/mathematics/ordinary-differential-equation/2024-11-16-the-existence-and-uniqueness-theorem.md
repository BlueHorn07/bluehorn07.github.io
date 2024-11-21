---
title: "The Existence and Uniqueness Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

![](/images/meme/panic.png){: .align-center style="max-width: 300px" }

경고하는데 여기서부터 진짜 완전히 새로운 내용입니다...;; 지금까지는 미분방정식의 심화 버전을 하는 느낌이었다면, 여기서부터 진짜 `MATH4xx` 과목의 위엄이 뭔지 작살나게 느낄 수 있습니다 ㅋㅋ

이 챕터의 목표는 ODE의 solution이 존재(Existence)하고 그리고 유일(Uniqueness)하다는 것을 보이는 것입니다. 그런데 저는 감자(🥔)니까 그 주변 곁다리부터 다가가보도록 하겠습니다.

<div class="proof" markdown="1">

**[Existence and Uniqueness의 곁다리들]**

순서는 상관없습니다.

- [Lipschitz Constant](/2024/11/14/Lipschitz-constant/)
- [Picard Iteration](/2024/11/14/Picard-iteration/) 👋
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/)

</div>

# Function Spaces

IVT에 대한 정리를 보기 전에, 먼저 아래의 집합을 엄밀히 정의해봅시다.

$C^0$는 모든 연속 함수의 집합입니다. 그리고 $C^1$은 "한 번 Continuously Differentiable Functions의 집합"입니다.

이때, "Differentiable"과 "Continuously Differentiable"의 차이는 "Differential"은 도함수 $f'(x)$가 연속일 필요가 없습니다. $f(x) = \| x \|$ 함수는 $x = 0$에서 도함수가 연속이지 않습니다. 반면에 "**Continuously Differentiable**"인 경우는 도함수 $f'(x)$도 연속을 만족합니다.

이것을 귀납적으로 정의하면, $C^2$는 "두 번 미분 가능하고, $f^{\prime\prime}(x)$가 연속인 함수"라고 정의할 수 있고, $C^{\infty}$는 무한히 미분 가능하고, 모든 도함수는 여전히 미분 가능한 함수"입니다. 즉, 매우 부드러운 함수입니다.

# Compactness

(학부 위상수학이나 해석학에서 나오는 개념이라고 하는데, 둘다 수강한 적이 없어서 이번에 첨 봤습니다;;)

수학에서 "**유계(bounded)**"의 개념을 일반화한 것이다. 예를 들어, 2차원 평면 위의 원이나, 3차원의 구, 토러스는 콤팩트 집합이다. 이들은 직선이나, 평면, 공간에 비해 아주 작은 집합들이다. '콤팩트(compact)'라는 이름은 이런 맥락에서 온 것이다.

1차원에서 가장 쉽게 떠올릴 수 있는 콤팩트 집합은 $[a, b] \subset \mathbb{R}$이다. 양 끝이 모두 닫힌 구간임에 유의하자. 유클리드 공간 위에서 콤팩트 집합은 닫힘성(closed)과 유계성(bounded)만 만족하면 된다.

<div class="theorem" markdown="1">

IF $\Omega$ is compact and $F: \Omega \rightarrow \mathbb{R}^n$ is continuous,

THEN $f$ is has its maximum/minimum on $\Omega$.

</div>

위의 정리의 사례를 살펴보면

- 구간 $[0, 1]$, 함수 $f(x) = x^2$
  - 최대값 $1$
  - 최소값 $0$
- 구간 $[0, \pi]$, 함수 $f(x) = \cos x$
  - 최대값 $1$
  - 최소값 $-1$

그러나 구간 $[-1, 1]$ 위에서 정의된 함수 $f(x) = 1/x$는 이 정리를 만족하지 않는다. 그 이유는 일단 함수가 $x = 0$에서 정의되지 않기 때문에, 함수의 정의역은 엄밀히 말해 $[-1, 1] \setminus \\{ 0 \\}$이다. 이 집합은 열린 집합이므로 콤팩트하지 않다. 만약 정의역을 콤팩트하게 만들기 위해 $f(x=0) = a \in \mathbf{R}$라고 설정한다면, 이것은 함수 $f(x)$가 continuous라는 조건을 위배하게 된다. 즉, 생각보다 "**콤팩트 연속 함수**"라는 조건을 만족하기 어렵다는 것!


# The Existence and Uniqueness Theorem

<div class="theorem" markdown="1">

Consider the initial value problem

$$
X' = F(X), \quad X(0) = X_0
$$

where $X_0 \in \mathbb{R}^n$. Supp. that $F: \mathbb{R}^n \rightarrow \mathbb{R}^n$ is $C^1$.

Then there exists a unique solution of this initial value problem. More precisely, there exists $a > 0$ and a unique solution

$$
X: (-a, a) \rightarrow \mathbb{R}^n
$$

of this differential equation satisfying the initial condition $X(0) = X_0$.

</div>

이때, $C^1$은 "Continuously Differentiable Function"입니다. 그리고 $F(X)$는 벡터 필드로 

$$
F(X) = (f_1, (x_1, ..., x_n), ..., f_n(x_1, ..., x_n))
$$

을 만족합니다.

# Road to the theorem

우리의 목표는 위의 정리를 이해하고, 증명해보는 것입니다. 내용이 어려울 수도 있겠지만, 포기하지 않고 전지해봅시다! 🏃‍♂️‍➡️ 내용을 다 이해하지 못 해도 괜찮다!! (나에게 하는 말 ㅋㅋ)

## Continuous Differential Functions are Locally Lipschitz

<div class="theorem" markdown="1">

Supp. that the function $F: \Omega \rightarrow \mathbb{R}^n$ is $C^1$.

Then $F$ is locally Lipschitz.

\* 이때, 함수의 정의역 $\Omega$는 콤팩트 집합이다.

</div>

<div class="proof" markdown="1">

Let $x_0 \in \Omega$ and let $B_{\epsilon} := \left\\{ x: \| x - x_0 \| \le \epsilon \right\\}$ with small $\epsilon > 0$ s.t. $B_{\epsilon} \subset \Omega$.

</div>


$DF_X$에 대한 내용.... (요건 언제 쓰는 거징?)

존재성 정리가 있고, 유일성에 대한 정리가 따로 있는 건가?

# References

https://youtu.be/Zxr6Wekwxh0?si=k3uo7A_srkM8Us7R

https://people.math.wisc.edu/~aseeger/319/notes2.pdf
^읽어봐야 함