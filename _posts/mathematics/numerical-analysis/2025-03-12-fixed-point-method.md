---
title: "Fixed-point Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }
비선형 방정식 $f(x) = 0$의 근을 찾기 위한 수치적 방법 입니다.

# Lipchitz

# Locally Convergence

FPI의 경우는 수렴이 반드시 보장되지 않고, 초기값에 의존할 수 있습니다.

구간 $[a, b]$에서 $\| g'(x) \| < 1$을 만족한다면, 이 구간에서 수렴을 기대할 수 있는 후보 영역입니다. 하지만, 이 구간 내의 모든 점에서 수렴이 보장된다고 말할 수는 없습니다.

중요한 것은

- 초기값이 수렴 영역 $[a, b]$ 내에 존재해야 하고,
- FPI의 반복을 통해 수렴 성질이 유지 되어야 합니다.

즉, $[a, b]$ 수렴값에 대한 중요한 힌트를 줄 뿐 입니다.

그래서 수렴에 대한 조건으로 초기값 $p_0$가 고정점 $r$에 **충분히 가까워야** 선형 수렴이 보장 됩니다.

# Fixed-Point Theorem

<div class="theorem" markdown="1">

Let $g \in C[a, b]$ be s.t. $g(x) \in [a, b]$ for all $x$ in $[a, b]$.

Supp. that $g'$ exists on $(a, b)$ and there is a constant $0 < K < 1$ s.t. for all $x \in (a, b)$,

$$
\| g'(x) \| \le K
$$

Then for any number $p_0$ in $[a, b]$, the sequence defined by $p_n = g(p_{n-1})$ for $n \ge 1$, converges to the unique fixed point $r$ in $[a, b]$.

</div>

아까는 FPI이 국소 수렴 한다고 하였는데, 이 정리에서는 전역적으로 유일하게 수렴 한다고 합니다. 둘이 뭐가 다른 걸까요??

둘의 차이점은 함수 $g(x)$가 자기 사상을 만족하는지 입니다. 둘다 수축 사상인 $\| g'(x) \| < 1$에 대해서는 만족을 하지만, 전역 유일 수렴을 위해서는 자기 사상이 만족 되어야 합니다.

$r$ 근처에서 수축 사상만을 만족한다면, 그것은 $r$ 근처에서만 FPI에 의해 수렴한다는 것을 보장하기 때문에 국소 수렴이 됩니다.

# Error Bound

Fixed-point Iteration이 위의 고정점 정리를 만족하는 상황이라면, 이 근사법에 대한 오차 경계를 유도할 수 있습니다. 이때, 2가지 오차 경계를 유도할 수 있습니다.

## Error Bound - 1

<div class="theorem" markdown="1">

If $g$ satisfies the "Fixed-point Theorem", then bounds for the error involved in suing $p_n$ to approximate the fixed point $r$ are given by

$$
\| p_n - r \| \le K^n \max \left\{ p_0 - a, b - p_0 \right\}
$$

</div>

TODO: 증명

## Error Bound - 2

<div class="theorem" markdown="1">

If $g$ satisfies the "Fixed-point Theorem", then bounds for the error involved in suing $p_n$ to approximate the fixed point $r$ are given by

$$
\| p_n - r \| \le \frac{K^n}{1-K} \| p_1 - p_0 \|
$$

</div>

TODO: 증명

## 둘 중 어떤 것을 쓰는게 좋을까?

TODO

