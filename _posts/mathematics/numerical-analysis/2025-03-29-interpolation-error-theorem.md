---
title: "Interpolation Error Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "보간 함수의 오차에 대한 정리. 이걸 바탕으로 보간법의 로컬 오차와 글로벌 오차의 상한을 구할 수 있습니다."
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

"라그랑지 보간법"과 "분할 차분 보간법"은 함수를 보간 다항식으로 근사한 결과 입니다. 이것이 실제 함수의 값과 보간 값이 얼마나 차이가 나는지를 수학적으로 살펴보는 파트 입니다.

# Interpolation Error Theorem

<div class="theorem" markdown="1">

Let $x_1, \dots, x_n$ bet $n$ distinct nodes in the interval $[a, b]$. Assume that $f \in C^{n}[a, b]$.

Given that $x \in [a, b]$, there exists a $\xi = \xi(x)$ in $(a, b)$ s.t.

$$
f(x) - I_n f(x) = \frac{\omega_n(x)}{n!} f^{(n)} (\xi)
$$

where $\omega_n(x)$ is a "nodal polynomial" s.t. $\omega_n(x) = (x-x_1)(x-x_2)\cdots(x-x_n) = \prod (x-x_i)$.

</div>

이 정리의 의미는 실제 함수값과 보간값 사이의 오차를 정확히 위의 수식으로 표현할 수 있다는 것을 말합니다.

이때, 수식 자체로도 몇가지 의미가 유도 되는데,

- 오차는 고차 도함수 $f^{(n)}(\xi)$에 비례한다
  - 함수가 많이 휘어질수록 오차가 커집니다.
- 오차는 노드들과의 거리 곱 $\omega_n(x)$에 비례한다
  - $x$가 노드들과 멀리 떨어질수록 오차가 커짐
- 위의 오차식은 등호가 있는 "정확한" 오차 표현식 입니다.
  - 뭔한다면, 부등호가 있는 오차 상한선(error bound)의 형태로도 쓸 수 있습니다.

한마디로 요약하면 아래와 같겠네요.

> Interpolation error depends on<br/>
> the **nth derivative**(=curvature)<br/>
> and **the distance from the interpolation nodes**.

## Proof

TODO

# Properties

## Local Bound

"Interpolation Error Theorem"에 의해 아래의 부등식이 성립 합니다.

<div class="theorem" markdown="1">

$$
\| f(x) - I_n f(x) \| \le \frac{\| \omega_n(x) \|}{n!} M_n
$$

where $M_n = \max_{x \in [a, b]} \| f^{(n)}(x) \|$.

</div>

이것은 $f^{(n)}(\xi)$가 $M_n$으로 치환되면서 부등식으로 바뀐 것 입니다.

## Global Bound

"Interpolation Error Theorem"에 의해 아래의 부등식이 성립 합니다.

<div class="theorem" markdown="1">

$$
\| f(x) - I_n f(x) \| \le \frac{\max_{x\in[a, b]} \| \omega_n(x) \| }{n!} M_n
$$

where $M_n = \max_{x \in [a, b]} \| f^{(n)}(x) \|$.

</div>

이것은 전역적인 에러 입니다. nodal polynomial인 $\omega_n(x)$의 상한을 구함으로써, 전역 에러를 유도할 수 있습니다.

