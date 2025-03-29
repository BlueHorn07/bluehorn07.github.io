---
title: "Utility Function"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "사람의 선호와 가치 함수에 대해"
---

# What is utility

경제학에서는 개인의 선호(preference)를 직접 다루기 보다는 가치 함수(value function)을 사용해 표현하는 경우가 많습니다.

그러나 가치 함수가 선호로 그대로 이어지지는 않습니다. 예를 들어, 회사로부터 떨어진 거리 $d(x)$는 가치 함수이지만, 이 값이 작을수록 사람들이 선호하게 됩니다.

만약 어떤 가치함수가 개인의 선호를 정확히 나타낼 수 있다면, 이 가치 함수를 효용 함수(utility function) $u(x)$라고 합니다. 위의 회사 거리 예시에서는 $u(x) = - d(x)$로 표현할 수 있습니다. 또는 $u(x) = 1/d(x)$로 표현할 수도 있습니다.

## Definition

For any set $X$ and preference relation $\succeq$ on $X$,

the function $u: X \rightarrow \mathbb{R}$ represents $\succeq$ iff $u(x) \ge u(y)$.

# Alternative

## Minimal Alternative

어떤 대안 $x \in X$가 "minimal alternative"라고 한다면, $x$는 다른 모든 $y \in X$에 대해서 $y \succeq x$ 관계를 만족합니다.

## Maximal Alternative

어떤 대안 $x \in X$가 "maximal alternative"라고 한다면, $x$는 다른 모든 $y \in X$에 대해서 $x \succeq y$ 관계를 만족합니다.

## Existence of minimal/maximal alternatives

<div class="theorem" markdown="1">

Let $X$ be a nonempty-finite set and let $\succeq$ be a preference relation on $X$.

At least one member of $X$ is minimal w.r.t $\succeq$ in $X$<br/>
and at least one member is maximal.

</div>

### Proof

TODO

# Propositions

## Preference relation can be represented by a utility function

<div class="theorem" markdown="1">

Every preference relation on a finite set can be represented by a utility function.

</div>

<div class="proof" markdown="1">

유한 집합 $X$ 위에서 정의된 선호 관계 $\succeq$가 있습니다.

집합 $X$의 원소 중, 선호 관계 $\succeq$에 따라 최소인 원소들의 집합 $M_1$을 정의합니다. minimal alternative인 원소는 하나 이상 존재할 수 있기 때문에 집합으로 표현 합니다.

이 집합 $M_1$은 공집합 $\emptyset$이 아니며, 이 원소들에 대한 효용 함수의 값을 $u(x) = 1$로 정의합니다.

<br/>

이제 새로운 집합 $Y_1 = X \setminus M_1$을 정의하고, 남아있는 원소 중에서의 최소 원소들의 집합 $M_2$를 찾습니다. 그리고 이 원소들에는 $u(x) = 2$의 값을 부여 합니다.

이 과정을 계속 반복하여 $M_k$를 정의하고, 해당 집합의 원소에 $u(x) = k$의 값을 부여 합니다.

<br/>

집합 $X$는 유한 집합 이므로, 이 과정은 유한번 시행된 후에 종료 됩니다.

</div>


## Preference relation not represented by a utility function

<div class="theorem" markdown="1">

The (lexicographic) preference relation is not represented by any utility function

</div>

TODO: proof

## Increasing function of utility function is utility function

<div class="theorem" markdown="1">

Left $f$ is a real-valued increasing function.

If $u$ represents the preference relation $\succeq$ on $X$,

then so does the function $w$ defined by $w(x) = f(u(x))$ for all $x \in X$.

</div>

이 명제의 의미는 효용 함수의 값 자체는 절대적인 의미를 갖는 것이 아니고, 순서만 중요할 뿐이라는 것 입니다. 효용 함수를 스케일링 하든 이리저리 변환을 하든, 그 함수가 단조 증가하는 함수라면 선호 순서가 보존 됩니다.

