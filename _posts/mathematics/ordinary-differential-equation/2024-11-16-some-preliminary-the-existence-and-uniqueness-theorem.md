---
title: "Some Preliminary for The Existence and Uniqueness Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "Function Spaces, Compactness, Differential on Vector Field."
---

복수전공하고 있는 수학과의 학부 졸업시험에 미분방정식이 있는 줄 알고, 시험 준비도 할 겸 복학할 때 “상미분방정식” 과목을 신청했습니다. 나중에 알고보니 미분방정식은 졸업시험 과목이 아니었습니다… OTL… 그래도 이왕 시작한 것 포기란 없습니다!! 💪 으랏차!!
[상미분방정식 포스트 전체 보기](/categories/ordinary-differential-equations)
{: .notice--info}

# 들어가며

![](/images/meme/panic.png){: .align-center style="max-width: 300px" }

경고하는데 여기서부터 진짜 완전히 새로운 내용입니다...;; 지금까지는 미분방정식의 심화 버전을 하는 느낌이었다면, 여기서부터 진짜 `MATH4xx` 과목의 위엄이 뭔지 작살나게 느낄 수 있습니다 ㅋㅋ

이 챕터의 목표는 ODE의 solution이 존재(Existence)하고 그리고 유일(Uniqueness)하다는 것을 보이는 것입니다. 그런데 저는 감자(🥔)니까 그 주변 곁다리부터 다가가보도록 하겠습니다.

<div class="proof" markdown="1">

**[Existence and Uniqueness의 곁다리들]**

순서는 상관없습니다.

- [Lipschitz Constant](/2024/11/14/Lipschitz-constant/)
- [Picard Iteration](/2024/11/14/Picard-iteration/)
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/)
- [Some Preliminaries](/2024/11/16/some-preliminary-the-existence-and-uniqueness-theorem/) 👋

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

처음엔 정리가 당연한 말을 하고 있다고 생각했는데, 생각보다 반례를 쉽게 찾을 수 있다.

구간 $[-1, 1]$ 위에서 정의된 함수 $f(x) = 1/x$는 이 정리를 만족하지 않는다. 그 이유는 함수가 $x = 0$에서 정의되지 않기 때문에, 함수의 정의역이 $[-1, 1] \setminus \\{ 0 \\}$이다. 이 집합은 열린 집합이므로 콤팩트하지 않다.

만약 정의역을 콤팩트하게 만들기 위해 $f(x=0) = a \in \mathbf{R}$라고 함수값을 임의로 정의 한다면, 이것은 함수 $f(x)$가 continuous라는 조건을 위배하게 된다. 즉, 생각보다 "**콤팩트 연속 함수**"라는 조건을 만족하기 어렵다는 것!


# Differential on Vector Field

벡터 필드 $F: \mathbb{R}^n \rightarrow \mathbb{R}^n$가 있을 때, 해당 필드의 Differential을 생각해보자.

먼저, $X = (x_1, ..., x_n)$의 벡터로 정의되고, $F(X)$는 $F(X) = (f_1(X), ..., f_n(X))$로 정의된다. 그리고 미분을 정의하면

$$
DF_X(U) = \lim_{h \rightarrow 0} \frac{F(X + hU) - F(X)}{h}
$$

이때, $U$는 임의의 방향 벡터 입니다. 고차원에서 미분을 정의하기 위해서는 모든 방향에 대한 극한을 확인해야 하는데, $F(X + h)$를 사용하면, $X$ 벡터에 대한 방향만을 판단하게 되기 때문에 $F(X + hU)$를 사용한 것입니다.

<br/>

또는 아래와 같이 Jacobian을 정의할 수도 있습니다.

$$
DF_X = \left( \frac{\partial f_i}{\partial x_j}\right) \in \mathbb{R}^{n\times n}
$$

이때, $DF_X(U)$는 $DF_X \cdot U$로 행렬곱의 결과입니다.

$$
DF_X(U) = \sum^{n}_{j=1} (\frac{\partial f_i}{\partial x_j} \cdot u_j)
$$

그리고 이 Jacobian의 노름(Norm)을 아래와 같이 정의합시다.

$$
\| DF_X \| = \sum_{\| U \| = 1} \| DF_X(U) \|
$$

