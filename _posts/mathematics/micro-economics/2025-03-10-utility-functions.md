---
title: "Utility Functions"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "가치 함수로 개인의 선호를 나타내는 방법에 대해. 효용 함수로 표현할 수 있는 개인의 선호가 있고, 그렇지 않은 선호가 있다."
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ 그래도 수학과 복수전공도 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# What is utility

지난 포스트에서는 [개인의 선호(preference)](/2025/03/05/preferences/)에 대해 살펴보았습니다.
그런데, 경제학에서는 개인의 선호를 직접 다루기 보다는 가치 함수(value function)을 사용해 표현하는 경우가 많습니다.

그런데, 가치 함수의 부등호 방향이 선호의 부등호 방향과 일치하지는 않을 수 있습니다. 예를 들어, 회사로부터 떨어진 거리 $d(x)$는 가치 함수지만, 이 값이 작을수록 사람들이 선호하게 됩니다.

만약 어떤 가치함수가 개인의 선호를 정확히 나타낼 수 있다면, 이 가치 함수를 "**효용 함수(utility function)**" $u(x)$라고 합니다. 위의 회사 거리 예시에서는 $u(x) = - d(x)$로 표현할 수 있습니다. 또는 $u(x) = 1/d(x)$로 표현할 수도 있습니다.

## Definition

<div class="definition" markdown="1">

For any set $X$ and preference relation $\succcurlyeq$ on $X$,

the function $u: X \rightarrow \mathbb{R}$ represents $\succcurlyeq$ iff $u(x) \ge u(y)$.

</div>

효용 함수에서는 부등호의 방향과 선호의 부등호 방향이 일치 합니다!

# Minimal/Maximal Alternative

어떤 대안 $x \in X$가 "**minimal alternative**"라고 한다면, $x$는 다른 모든 $y \in X$에 대해서 $y \succcurlyeq x$ 관계를 만족합니다.

반대로 어떤 대안 $x \in X$가 "**maximal alternative**"라고 한다면, $x$는 다른 모든 $y \in X$에 대해서 $x \succcurlyeq y$ 관계를 만족합니다.

## Existence of Minimal/Maximal Alternatives

<div class="theorem" markdown="1">

Let $X$ be a nonempty-finite set and let $\succcurlyeq$ be a preference relation on $X$.

At least one member of $X$ is minimal w.r.t $\succcurlyeq$ in $X$<br/>
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

유한 집합 $X$ 위에서 정의된 선호 관계 $\succcurlyeq$가 있습니다.

집합 $X$의 원소 중, 선호 관계 $\succcurlyeq$에 따라 최소인 원소들의 집합 $M_1$을 정의합니다. minimal alternative인 원소는 하나 이상 존재할 수 있기 때문에 집합으로 표현 합니다.

이 집합 $M_1$은 공집합 $\emptyset$이 아니며, 이 원소들에 대한 효용 함수의 값을 $u(x) = 1$로 정의합니다.

<br/>

새로운 집합 $Y_1 = X \setminus M_1$을 정의하고, 남아있는 원소 중에서의 최소 원소들의 집합 $M_2$를 찾습니다. 그리고 이 원소들에는 $u(x) = 2$의 값을 부여 합니다.

이 과정을 계속 반복하여 $M_k$를 정의하고, 해당 집합의 원소에 $u(x) = k$의 값을 부여 합니다.

<br/>

집합 $X$는 유한 집합 이므로, 이 과정은 유한번 시행된 후에 종료 됩니다.

</div>


## Lexicographic Preference Do Not Have Utility Function

<div class="theorem" markdown="1">

The lexicographic preference relation is not represented by any utility function

</div>

TODO: proof

참고로 사전식 선호에 대한 이 성질은 나중에 "번들(Bundle) 선호"에 대해 다룰 때 한번더 등장합니다! [[link](/2025/04/12/bundles-of-goods/#lexicographic-preference)]
{: .notice }



## Monotone Transformation of Utility Function

<div class="theorem" markdown="1">

Let $f$ is a real-valued increasing function.

If $u$ represents the preference relation $\succcurlyeq$ on $X$,

then so does the monotone transformation $w$ defined by $w(x) = f(u(x))$ for all $x \in X$.

</div>

이 명제의 의미는 효용 함수의 값 자체는 절대적인 의미를 갖는 것이 아니고, 순서만 중요할 뿐이라는 것 입니다. 효용 함수를 스케일링 하든 이리저리 변환을 하든, 그 함수가 단조 증가하는 함수라면 선호 순서가 보존 됩니다.


# 맺음말

효용 함수를 사용해 개인의 선호를 정의하는 방법을 살펴보았습니다.
지금까지는 개인의 선호가 이항관계 $\succcurlyeq$로 명확히 제시되거나, 효용 함수를 통해 수치적으로 계산하였습니다.

이어지는 포스트에서는 개인의 선호가 "[선택 함수(Choice Function)](/2025/03/12/choice-functions/)"의 형태로 표현 됩니다. 이 함수는 부분집합이 주어졌을 때, 그 안에서 어떤 원소를 하나 선택 하는 함수로 개인의 선호를 표현하는 표현 방법 중 하나 입니다.
