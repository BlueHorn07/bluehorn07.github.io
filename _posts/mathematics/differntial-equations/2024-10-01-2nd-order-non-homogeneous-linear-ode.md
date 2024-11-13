---
title: "2nd order Non-homogeneous Linear ODE"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "$r(x)$ 항이 있는 ODE의 해를 구하는 방법. 미정계수법과 매개변수 변환법"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

앞선 포스트에서 2nd order Homogeneous linear ODE의 해를 어떻게 구해야 하는지는 살펴봤었다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = 0
$$

</div>

- $\lambda$에 대한 2차 방정식 풀기 (for constant coefficient)
- $y_1$를 구하고 Reduction of Order Method 사용해서 $y_2$ 유도하기

어찌저찌 $y_1$, $y_2$를 구했다고 해보자.

<br/>

그러나 세상의 모든 2nd order linear ODE가 homogeneous일리는 없다. 분명 $r(x) \ne 0$인 경우가 있어서 non-homogeneous linear ODE를 풀어야 할 수도 있을 것이다.

# Non-homogeneous linear ODE의 경우

암튼 이번 포스트에서는 non-homogeneous linear ODE를 푸는 방법을 살펴볼 것이다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = r(x)
$$

</div>

non-homogeneous의 경우는 해가 아래와 같이 구성된다고 한다.

<div class="definition" markdown="1">

$$
y = y_h + y_p
$$

</div>

위의 식에서 $y_h$와 $y_p$의 의미는 아래와 같다.

- $y_h$: non-homo ODE 식에서 $r(x) = 0$인 homogeneous linear ODE에서의 general solution
- $y_p$는 non-homo. ODE 식을 만족하는 어떤 구체적인 함수

이다. 이게 가능한 이유는 "$L(y) = y'' + p(x) y' + q(x) y$"라고 둘 때, 아래의 식을 만족하기 때문이다.

$$
L(y_h) + L(y_p) = L(y_h + y_p) = r + 0 = r
$$

암튼 핵심은 "non-homo ODE의 일반해"가 "homo ODE의 일반해" + "non-homo ODE의 특정해"로 구성된다는 것이다.


# How to solve non-homogeneous linear ODE

## Method of undetermined coefficients

"미정계수법"라고 불리는 방법이다. non-homo ODE의 우항인 $r(x)$가 아래의 함수이거나 그들의 linear combination 일 때 요 방법을 쓸 수 있다.

- exponential: $e^x$
- power of $x$ = polynomial: $x^n$
- cosine or sine

위의 함수들의 특징은 미분이나 적분이 그 카테고리 안에서 노는 아주 나이스한 함수라는 것이다.

요 "미정계수법"은 예제 몇문제 풀어보면 금방 감을 잡을 수 있다.

### Example 1

<div class="problem" markdown="1">

Solve the given ODE

$$
y'' + y = 0.001 x^2
$$

</div>

<div class="proof" markdown="1">

미정계수법에 따라 $y_p$를 최고차항 2의 polynomial로 설정한다.

$$
y_p = a_2 x^2 + a_1 x + a_0
$$

그러고, $y_p'$, $y_p^{\prime \prime}$를 구해서 대입하면...

$$
2a_2 + (a_2 x^2 + a_1 x + a_0) = 0.001 x^2
$$

각 $x^n$별로 계수를 비교해 구하면...

- $a_2 = 0.001$
- $a_1 = 0$
- $a_0 = - 0.002$

</div>

### Example 2: Modification Rule

<div class="problem" markdown="1">

Solve the given ODE

$$
y'' - 6 y' + 9 y = 12 e^{3x}
$$

</div>

<div class="proof" markdown="1">

요 ODE의 homogeneous solution $y_h$는 아래와 같다.

$$
y_h = (c_1 + c_2 x) e^{3x}
$$

그런데, 미정계수법에 따라 $y_p$를 잡으면, $y_p = C e^{3x}$가 되는데, 요게 $y_h$와 linearly dependent 하기 때문에 particular solution으로 사용할 수 없다.

지금처럼 미정계수법의 규칙에 따라 설정한 $y_p$이 $y_h$와 일차 종속이 되는 경우, $y_p$이 $x$ 또는 $x^2$를 곱하여 일차 종속이 되지 않도록 만든 후, 미정계수법을 적용한다. 이번에는 $x^2$를 곱해서 $y_p$를 설정하고 ODE를 풀어보자.

$$
y_p = C x^2 e^{3x}
$$

$$
\left(9C x^2 e^{3x} + 12 C x e^{3x} + 2C e^{3x}\right)
+ -6 \cdot \left(3Cx^2 e^{3x} + 2C x e^{3x}\right)
+ 9 \cdot C x^2 e^{3x} = 12 e^{3x}
$$

계수를 비교해 $C$를 구해보면...

- $0 \cdot x^2 e^{3x}$
- $0 \cdot x e^{3x}$
- $2 C e^{3x} = 12 e^{3x}$

따라서 $C = 6$이 되고, particular solution $y_p = 6 x^2 e^{3x}$가 된다.

</div>


## Method of variation of parameters

"매개변수 변환법"라고 불리는 방법이다. 앞에서 살펴본 미정계수법은 $r(x)$의 도함수가 자기 자신과 유사한 녀석인 경우에만 쓸 수 있었다. 요 매개변수 변환법은 $r(x)$가 좀더 일반적인 상황에서 쓸 수 있는 방법이다. 매개변수 변환법에서는 $r(x)$가 연속 함수이기만 하면 된다!

매개변수 변환법에서는 particular solution $y_p$를 아래의 공식으로 구한다.

<div class="definition" markdown="1">

$$
y_p = - y_1 \int \frac{y_2 r}{W} dx + y_2 \int \frac{y_1 r}{W} dx
$$

- $y_1$, $y_2$는 대응하는 homogeneous ODE의 basis
- $W$는 Wronskian of $y_1$, $y_2$: $W = y_1 y_2' - y_2 y_1'$

</div>

왜 이런 공식이 나오는지는... 생략한다!! (나는 컴공과니까!!)

### Example 1

요것도 간단한 예제를 살펴보자.

<div class="problem" markdown="1">

Solve the given ODE

$$
y'' + y = \sec x
$$

</div>

<div class="proof" markdown="1">

basis는 아래와 같다.

- $y_1 = \cos x$
- $y_2 = \sin x$

Wroskian을 구하면 아래와 같다.

$$
W(y_1, y_2) = \cos x \cos x - \sin x (- \sin x) = 1
$$

이제 매개변수 변환법의 공식에 맞게 대입하면

$$
\begin{aligned}
y_p
&= - \cos x \cdot \int \sin x \frac{1}{\cos x} \, dx
+ \sin x \cdot \int \cos x \frac{1}{\cos x} \, dx \\
&= \cos x \ln \left| \cos x \right|
+ x \sin x

\end{aligned}
$$

</div>

# 맺음말

어떻게 보면, 2차 미방을 푸는 테크닉을 습득하는 파트였다. 미정계수법은 뭔가 쉽게 받아들인 것 같은데, 매개변수 변환법은 아직 $y_p$ 공식이 눈에 잘 안 들어오는 것 같다. 생각해보면 아직 Wronskian $W(y_1, y_2)$ 공식도 익숙하지 않으니 ㅋㅋ

이제 슬슬 미방과 행렬을 같이 쓰는 단계가 다가오는 것 같다...! 이번 24-2학기에 듣는 상미분방정식 수업에서 교수님이 진짜 하루도 빠짐없이 행렬의 eigenvalue에 따라 미방이 어떻게 움직이는지 설명하시는 것 같다... ㅋㅋ

아무튼...! 홧팅!!
