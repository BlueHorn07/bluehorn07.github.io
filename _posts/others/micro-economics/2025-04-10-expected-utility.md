---
title: "Expected Utility"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: ""
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ
그래도 어영부영 수학과 복수전공을 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# Definition

## Bernoulli Function

우리는 많은 경우, 복권에서 각 상품의 당첨 확률 $p(z)$와 당첨 상금 $v(z)$를 종합해 평균을 매겨서 그 복권의 가치를 측정합니다. 이것을 정의하고 이름 붙인 것이 경제학에서의 "Bernoulli Function" 입니다.

<div class="definition" markdown="1">

There exist a utility function $v: Z \rightarrow \mathbb{R}$,

and another utility function $U$ defined by

$$
U(p) = \sum_{z \in Z} p(z) v(z)
$$

for each $p \in L(Z)$. Then the function $U$ is the "Bernoulli Function".

</div>

## Expected Utility

<div class="definition" markdown="1">

어떤 복권 $p \in L(Z)$가 있을 때, 그 복권의 기대 효용은 Bernoulli Function $U(p)$에 의해 계산 됩니다.

그리고 이를 바탕으로 각 복권에 대한 선호 관계 $\succcurlyeq$를 전체 복권 집합 $L(Z)$에 대해 구성할 수 있습니다.

</div>

# Properties

## Continuity and Independence

복권에 대한 기대 효용으로 만든 선호 관계는 연속성과 독립성을 만족한다는 성질 입니다.

<div class="proof" markdown="1">

[Continuity]

Let $a, b, c \in Z$, and satisfies $[a] \succ [b] \succ [c]$.

For every $z \in Z$, $U([z]) = v(z)$, so $v(a) > v(b) > v(c)$.

Then, we can define $\alpha$ as follows,

$$
\alpha = \frac{v(b) - v(c)}{v(a) - v(c)}
$$

It belongs to $\alpha \in (0, 1)$, and $\alpha \cdot v(a) + (1 - \alpha) \cdot v(c) = v(b)$.

Then,

$$
\alpha \cdot a \oplus (1 - \alpha) \cdot c \sim [b]
$$

</div>

<div class="proof" markdown="1">

[Independence]

TODO... (좀 길다...)

</div>

## Continuity and Independence implies Expected Utility

복권의 집합과 그 복권들 사이의 선호 관계가 있을 것 입니다.
이 문단에서는 복권의 기대 효용으로 매긴 복권 사이 선호 관계는, 복권 자체에 대한 선호 관계와 동치라는 명제 입니다.

<div class="theorem" markdown="1">

A preference relation on a set of lotteries with a finite set of prizes that satisfies the **continuity** and **independence** properties is **consistent** with expected utility.

</div>

<div class="proof" markdown="1">

TODO... 길다...

</div>

# Allais Paradox

"알레의 역설"

내용은 [유튜브 영상](https://youtu.be/nvQF5dWu8-Q?si=c2FEOH60Av1mTU9P)으로 대체 합니다 ㅎㅎ

사람들은 종종 기대 효용에 따른 선택을 하지 않고, 낮은 확률이더라도 높은 보상이 있는 복권을 선택하게 된다는 역설 입니다.


# Risk Aversion and Neutrality

> 무조건 $50를 받는 복권과, 50%로 $100를 받고 $50로 $0원을 받는 복권이 있다면, 사람들은 어떤 복권을 더 선호할까요?

사실 이는 사람의 선호마다 다릅니다. 어떤 사람은 확정적인 보상이 오는 첫번째 복권을 선호할 수 있습니다. 그리고 그 사람은 기대 효용이 더 큰 복권이 만들어지더라도, 확정적인 보상을 주는 선택지를 항상 더 선호할 수도 있습니다. 이런 사람을 "위험 회피적(rick-averse)" 선호를 한다고 합니다.

<div class="definition" markdown="1">

For $p \in L(Z)$ and $E(p)$ is an expected utility of the given lottery $p$.

"Risk-averse" person prefer lottery with definite result over the lottery with uncertainty.

$$
[E(p)] \succcurlyeq p
$$

Moreover, there's preference that strictly prefer the definite result. $[E(p)] \succ p$, strictly rick-averse.

Also, there's preference that prefer both lottery regardless the certainty and uncertainty: $[E(p)] \sim p$, "risk-neutral".

</div>

위험 회피 특성을 가진 사람은 복권을 기댓값 만큼의 가치로 바꾸기 위해 "기꺼이" 돈을 지불합니다.

"보험"을 가입하는 행위는 위험 회피 특성을 반영하는 행동 입니다. 교통 사고나 질병은 확률적으로 낮지만 아주 큰 손해를 입힙니다. 사람들은 이것을 회피하기 위해 "보험료"라는 확정 비용을 내고, 이 큰 손해를 보장금으로 회피 합니다. 사고가 발생하지 않으면 보험료만 날리는 셈이지만, 사람들은 보험료 만큼의 손해를 감수하고서라도, 큰 위험을 회피하고 싶어 합니다.

누군가는 확정적인 돈을 포기하고, 기대값이 더 낮은 복권(도박)을 선택하기도 합니다. 이런 경우는 위험을 회피하는게 아니라 위험을 탐색(rick-seeking)하는 선호를 가진 사람입니다.

## Concavity and Rick aversion

효용 함수가 오목(concave)하다는 것은 돈이 많아질수록 느끼는 "추가 만족감"이 점점 줄어든다는 것입니다.

예를 들어, 0원에서 100만원이 되면 행복도가 크게 증가하지만, 1억에서 1억 100만원이 되면 거의 느낌이 없게 됩니다. 만약 어떤 사람이 이런 방식으로 행복감을 느낀다면, 그 사람이 "위험 회피" 특성을 가진 사람이라는 성질 입니다.

<div class="proof" markdown="1">


TODO...

젠슨 부등식을 사용해 증명하는데... 복잡하니 패스!

</div>

