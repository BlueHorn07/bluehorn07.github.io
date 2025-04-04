---
title: "Hermite Interpolation"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

에르미트 보간법은 라그랑주 보간법의 확장된 방식 입니다. 라그랑주 보간법이 각 점 $x_i$에서 함수값 $f(x_i)$만을 맞춰주었다면, 에르미트 보간법은 함수값과 함께 도함수의 값 $f'(x_i)$도 모두 맞추어 줍니다.

만약 함수의 미분값도 알고 있는 상황이라면, 에르미트 보간법을 통해 훨씬 더 정확한 보간이 가능합니다. 이는 물리 시뮬레이션과 컴퓨터 그래픽 분야에서 훨씬 부드러운 곡선을 그리기 위해 사용하는 접근 입니다.

<div class="theorem" markdown="1">

Given the distinct interpolation nodes $x_i$, $i=1,\dots,n$<br/>
and two sets of real numbers $y_i$ and $dy_i$ with $n\ge 0$.

we need to find a polynomial $P \in \mathbb{P}_{2n-1}$ s.t.

$$
P(x_i) = y_i \quad P'(x_i) = dy_i
$$

</div>

# Hermite Interpolation

에르미트 보간법에서는 함숫값을 맞춰주는 다항식 $H_i(x)$와 미분값을 맞춰주는 다항식 $K_i(x)$로 구성 됩니다.

- $H_i(x)$
  - $P(x_i) = y_i$가 되도록 합니다.
  - $H_i(x_j) = \delta_{ij}$가 되도록 구성하고,
  - $H_i'(x_j) = 0$이 되도록 합니다.
- $K_i(x)$
  - $P'(x_i) = dy_i$가 되도록 합니다.
  - $K_i(x_j) = 0$이 됩니다.
  - 반면에, $K_i'(x_j) = \delta_{ij}$가 됩니다.

이런 $H_i(x)$와 $K_i(x)$를 찾을 수 있다면, 우리는 아래와 같이 에르미트 보간법을 구성할 수 있습니다.

$$
P(x) = \sum_{i=1}^n \left[
  y_i H_i(x) + dy_i K_i(x)
\right]
$$

그리고 에르미트는 이 다항식 $H_i(x)$와 $K_i(x)$를 특별한 방식으로 제시하는데, 이는 아래의 "존재성 정리"에서 형태를 살펴보겠습니다.

# Existence & Uniqueness

<div class="theorem" markdown="1">

Supp. that $x_i$ are distinct real numbers.

Then, given two sets of real numbers $y_i$ and $dy_i$, there is a "unique" polynomial $P \in \mathbb{P}_{2n-1}$ s.t.

$$
P(x_i) = y_i \quad P'(x_i) = dy_i
$$

</div>

라그랑주 보간법에서도 $n$개 데이터 노드를 지나는 보간 다항식이 유일하게 존재한다는 것을 살펴보았습니다. 에르미트 보간법에서도 함수값과 미분값을 모두 만족하는 보간 다항식이 유일하게 존재한다는 것을 얘기하는 정리가 이것 입니다.

## Existence

에르미트는 $H_i(x)$와 $K_i(x)$를 아래와 같이 쓸 수 있다고 제시 합니다. 이때, $L_i(x)$는 라그랑주 보간법의 기저 다항식 입니다.

$$
\begin{aligned}
L_i(x) &= \prod_{j=1, j \ne i} \frac{x-x_j}{x_i - x_j} \\
H_i(x) &= \left[ 1 - 2 L_i'(x_i) (x - x_i) \right] \left[L_i(x)\right]^2 \\
K_i(x) &= (x-x_i)\left[L_i(x)\right]^2\\
\end{aligned}
$$

먼저, 두 다항식이 $H_i(x_j) = \delta_{ij}$와 $K_i(x_j) = 0$을 만족하는지 살펴봅시다.

<div class="proof" markdown="1">

$$
H_i(x_j) = \delta_{ij}
$$

[$i=j$]

$$
H_i(x_i) = \left[ 1 - 2 L_i'(x_i) \cancel{(x_i - x_i)} \right] \left[L_i(x_i)\right]^2 = 1 \cdot \left[L_i(x_i)\right]^2 = 1 \cdot 1^2 = 1
$$

[$i\ne j$]

$$
H_i(x_j) = \left[ 1 - 2 L_i'(x_i) (x_j - x_i) \right] \left[\cancel{L_i(x_j)}\right]^2 = \left[ \cdots \right] \cdot 0^2 = 0
$$

</div>

<div class="proof" markdown="1">

$$
K_i(x_j) = 0
$$

[$i=j$]

$$
K_i(x_i) = \cancel{(x_i-x_i)}\left[L_i(x_i)\right]^2 = 0 \cdot 1^2 = 1
$$

[$i\ne j$]

$$
K_i(x) = (x_j-x_i)\left[\cancel{L_i(x_j)}\right]^2 = (x_j-x_i) \cdot 0^2 = 0
$$

</div>

<br/>

그리고 두 다항식이 $H_i'(x_j) = 0$와 $K_i'(x_j) = \delta_{ij}$을 만족하는지 살펴봅시다. 먼저 두 다항식을 미분한 값은 아래와 같습니다.

$$
\begin{aligned}
H_i'(x)
&= -2 L_i'(x_i) [L_i(x)]^2
+ \left( 1 - 2 L_i'(x_i) (x-x_i) \right) \cdot 2 L_i(x) L_i'(x)
K_i'(x)
&= [L_i(x)]^2
+ (x-x_i)\cdot 2 L_i(x) L_i'(x)
\end{aligned}
$$

<div class="proof" markdown="1">

$$
H_i'(x_j) = 0
$$

[$i=j$]

$$
\begin{aligned}
H_i'(x)
&= -2 L_i'(x_i) \cancelto{1}{[L_i(x_i)]^2}
+ \left( 1 - 2 L_i'(x_i) \cancel{(x_i-x_i)} \right) \cdot 2 \cancelto{1}{L_i(x_i)} L_i'(x_i) \\
&= -2 L_i'(x_i) + 2 L_i'(x_i) = 0

\end{aligned}
$$

[$i\ne j$]

$$
\begin{aligned}
H_i'(x)
&= -2 L_i'(x_i) [\cancel{L_i(x_j)}]^2
+ \left( 1 - 2 L_i'(x_i) (x_j-x_i) \right) \cdot 2 \cancel{L_i(x_j)} L_i'(x_j) \\
&= 0 + 0 = 0
\end{aligned}
$$

</div>

<div class="proof" markdown="1">

$$
K_i'(x_j) = \delta_{ij}
$$

[$i=j$]

$$
K_i'(x_i)
= \cancelto{1}{[L_i(x_i)]^2}
+ \cancel{(x_i-x_i)} \cdot 2 L_i(x_i) L_i'(x_i)
= 1 + 0 = 1
$$

[$i\ne j$]

$$
K_i'(x_j)
= \cancel{[L_i(x_j)]^2}
+ (x_j-x_i)\cdot 2 \cancel{L_i(x_j)} L_i'(x_j)
= 0 + 0 = 0
$$

</div>

## Uniqueness

이 부분의 증명도 라그랑주 보간법의 유일성을 증명할 때와 동일하게 "귀류법"으로 증명 합니다.

<div class="proof" markdown="1">

Supp that there exists a polynomial $Q \in \mathbb{P}_{2n-1}$ with $Q \ne P$ s.t.

$$
Q(x_i) = y_i \quad Q'(x_i) = dy_i
$$

Let $R(x) = P(x) - Q(x)$. Then $R \in \mathbb{P}_{2n-1}$ and thus $R' \in \mathbb{P}_{2n-2}$.

Since $R(x_i) = 0$, then $R$ has $n$ distinct zeros.

"Rolle's Theorem" implies that $R'$ has at least $n-1$ zeros which interlace the $x_i$.

<div class="theorem" markdown="1">

[Rolle's Theorem]

함수 $f(x)$가 닫힌 구간 $[a, b]$에서 다음 조건을 만족한다면,

- $f$는 연속 함수이다.
- $f$는 미분 가능하다.
- $f(a) = f(b)$이다.

그러면, 열린 구간 $(a, b)$ 안에 어떤 점 $c$가 적어도 하나 존재해서 $f'(c) = 0$을 만족한다.

</div>

.... TODO!


</div>


# 맺음말

Hermite Polynomial을 처음 접한다면, 반드시 연습 문제를 하나는 풀어보길 바란다... ㅋㅋ

만약, 직접 손으로 해보면 정말 귀찮다...!! 그래서 다음 내용이 나온 것인데, Hermite Polynomial을 approximation을 대신 구한다.

방법은 [Newton's Divided Difference](/2025/03/27/newton-divided-differences/) 공식으로 구하는 것이다.

자세한 내용은 다음 포스트에서 ㅎㅎ


