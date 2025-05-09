---
title: "Lagrange Interpolation - Nodal Polynomial"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "라그랑지 보간법의 다른 표현법"
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

"[라그랑주 보간법](/2025/03/19/lagrange-interpolation/)"의 응용에 대해 다루는 포스트 입니다.

# Nodal Polynomial

주어진 $n$개의 데이터 노드에 대해 아래와 같은 $n$차 다항식을 정의합니다.

$$
\omega_n(x) = \prod_{j=1}^n (x - x_j)
$$

이것을 "Nodal Polynomial"이라고 합니다. 이 다항식은 모든 $x_j$를 근으로 갖습니다.

<br/>

그리고 이 다항식을 미분하면 아래와 같습니다.

$$
\omega_n'(x)
= \sum_{k=1}^{n} \left[
\prod_{j=1, j \ne k} (x - x_j)
\right]
$$

사실 단순히 "곱의 미분법"을 적용한 결과입니다. 이 도함수에 $x_j$를 대입하면

$$
\omega_n'(x_j) = \prod_{i=1, i \ne j} (x_j - x_i)
$$

이렇게 $x_j$를 제외하고 만든 Nodal Polynomial을 얻을 수 있습니다.

# Lagrange Polynomial

신기하게도 "[라그랑주 보간법](/2025/03/19/lagrange-interpolation/)"를 구성하는 라그랑주 다항식 $L_i(x)$을 Nodal Polynomial로 표현할 수 있습니다!

$$
L_i(x) = \frac{\omega_n(x)}{(x-x_i) \cdot \omega_n'(x_i)}
$$

이 표현이 좋은 이유는 모든 $L_i(x)$에 대해서 $\omega_n(x)$ 부분이 공통이기 때문입니다! 그래서 분자 파트에 대한 반복 계산을 줄일 수 있습니다.

그리고 $\omega_n'(x_i)$는 상수 입니다! 그래서 처음 구성할 때 한번만 계산하면 됩니다!

$$
\omega_n(x) = (x - x_i) \cdot \prod_{i \ne j} (x - x_j)
$$

를 만족하기 때문에 $L_i(x)$는 여전히 $\delta_{ij}$ 성질을 만족 합니다.

$$
L_i(x)
\begin{cases}
 \frac{\omega_n(x_i)}{(x_i-x_i) \cdot \omega_n'(x_i)}
 = \cancelto{1}{\frac{(x_i-x_i)}{(x_i-x_i)}} \cdot \cancelto{1}{\frac{\prod_{i \ne j} (x_i - x_j)}{\omega_n'(x_i)}} & x = x_i \\
\frac{\omega_n(x_j)}{(x_j-x_i) \cdot \omega_n'(x_i)} = \frac{0}{...} = 0 & x \ne x_i
\end{cases}
$$

그래서 이걸 종합해 보간 함수 $I_n f(x)$를 적어보겠습니다.

$$
\begin{aligned}
I_n f(x)
&= \sum_i L_i(x) \cdot f(x_i) \\
&= \sum_i \frac{\omega_n(x)}{(x-x_i)\omega_n'(x_i)} \cdot f(x_i)
\end{aligned}
$$

# Newton's Divided Difference

"[뉴턴의 나눗셈 차분](/2025/03/27/newton-divided-differences/)"도 Nodal Polynomial을 통해 표현할 수 있습니다.

$$
P_n(x) = f[x_0] + \sum_{k=1}^n \left[ f[x_0, x_1, \dots, x_k] \cdot \prod_{j=1}^{k-1} (x - x_j) \right]
$$

이때, 라그랑지 보간법의 결과와 뉴턴 보간법의 최고차항의 계수를 비교해보면

- 뉴턴 보간법
  - $f[x_1, \dots, x_n]$
- 라그랑지 보간법
  - $\sum_i \frac{f(x_i)}{\omega_n'(x_i)}$

가 됩니다. 따라서 아래의 식이 성립 합니다.

$$
f[x_1, \dots, x_n] = \sum_i \frac{f(x_i)}{\omega_n'(x_i)}
$$
