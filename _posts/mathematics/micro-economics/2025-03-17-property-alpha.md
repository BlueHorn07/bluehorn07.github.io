---
title: "Property α"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "일관된 선택을 하는 선택 함수가 갖는 성질. Satisficing 결정 모델과 비합리적인 선택으로 인한 결과에 대해서"
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ 그래도 수학과 복수전공도 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# Property $\alpha$

개인의 선호를 "[선택 함수](/2025/03/12/choice-functions/)"를 통해 정의하는 것까지 살펴보았고, 그중에서 나이스만 선택 함수는 아래의 "Property $\alpha$"를 만족합니다. 이 성질을 가진다면, 선택 함수가 일관된 선택을 보장합니다.

<div class="definition" markdown="1">

선택 함수 $c$가 아래 조건을 만족한다.

> 어떤 집합 $A$에서 $c(A) = \alpha$를 선택 했다. <br/>
> 만약, $\alpha$가 포함된 $A$의 부분집합 $B$가 있다면, 거기에 대해서도 $c(A) = c(B) = \alpha$가 만족해야 한다.

</div>

즉, 선택한 원소가 포함된 부분집합에서도, 그 원소를 계속 선택해야 한다는 성질 입니다.

# Rationalizable and Property $\alpha$

> 합리적인 선택 함수는 항상 Property $\alpha$를 만족해야 한다.

## proof

# Property $\alpha$ and Rationalizable

> 선택 함수가 Property $\alpha$를 만족한다면, 그 선택 함수는 합리적인 선택 함수이다.

## proof

TODO


# Satisficing

"Satisfy(만족하다) + Suffice(충분하다)"의 합성어. "만족화"라고 번역하는 것 같습니다.

완벽한 최적화 보다는 어느 정도 만족할 만한 수준에서 선택을 멈추는 방식임.

완벽한 최선을 찾기 보다는, 주어진 기준(aspiration level $v$)를 충분하는 첫 번째 옵션을 선택하는 전략

## Example

|선택지|가치 $v(x)$|
|-|-|
|A|50|
|B|70|
|C|85|
|D|40|

만약 "최적화 방식"으로 선택을 한다면, C(85점)을 선택합니다.

그러나 "만족화 방식"으로 선택을 한다면, 기준값 $v^\ast = 60$에 대해 순서대로 검토 하면서, 처음으로 $v(x) \ge 60$인 항목을 선택하게 됩니다. 즉, B(70점)을 선택합니다.

만족화 선택은 항상 최선의 선택을 보장하지는 않습니다. 하지만, 합리적인 목표를 달성하는데 충분한 선택을 "**빠르게**" 결정할 수 있습니다.

## Definition

<div class="definition" markdown="1">

$X$가 유한 집합이고, 그 집합 위에 정의된 value function $v: X \rightarrow \mathbb{R}$이 있음.

그리고 aspiration level $v^{\ast}$가 있고, 집합 위에는 어떤 순서 관계 $O$가 존재함 (쉽게 생각하면 사전 순서 같은거)

그럼 satisficing choice function $c$는 아래와 같이 정의됨.

$$
c(X) = \begin{cases}
x_k & \text{if } v(x_k) \ge v^{\ast} \text{ and } v(a_l) < v^{\ast} \text{ for } l = 1, ..., k-1 \\
x_K & \text{if } v(x_l) < v^{\ast} \text{for } l = 1, ..., K
\end{cases}
$$

</div>

$v(x) \ge v^{\ast}$를 만족하는 모든 대상 $x$는 만족스러울(satisfactory) 수 있음. 그러나 반대 조건에 대해서는 불만족스러움.

## Rationalizable

> A satisficing choice function is rationalizable.

### proof

TODO

# The money pump argument

비합리적인 선택을 반복적으로 하게 되면, 타인이 이를 이용해 지속적으로 돈을 빼앗을 수 있다는 주장

핵심 아이디어는 어떤 개인의 선택이 일과적이기 않고 순환적(cyclic) 하다면, 누군가 이를 이용해 계속해서 돈을 빼앗을 수 있다는 것. 개인은 같은 상태로 돌아오지만, 돈만 계속 잃게 됨.

<div class="problem" markdown="1">

선택 가능한 세 가지 대안 $a, b, c$가 존재합니다. 개인의 선택 규칙은 다음과 같습니다.

- $a, b$ 중에는 $a$ 선택
- $b, c$ 중에는 $b$ 선택
- $c, a$ 중에는 $c$ 선택

이런 선택은 일관성을 만족하지 못하는 비합리적 선택 패턴 입니다. 이 선택 패턴을 이용해 누군가 개인에게 다음과 같이 교환을 제안할 수 있습니다.

1. 처음에 $a$를 가지고 있습니다.
2. $1의 돈을 낸다면, $a$를 $c$로 교환 해주겠다고 제안 합니다.
3. 개인은 $a$보다 $c$를 더 선호하기 때문에 $1를 지불하고 $c$로 교환 합니다.
4. 다시 $1의 돈을 낸다면, $c$를 $b$로 교환 해주겠다고 제안 합니다.
5. 개인은 $c$보다 $b$를 더 선호하기 때문에 $1를 지불하고 $b$로 교환 합니다.
6. 다시 $1의 돈을 낸다면, $b$를 $a$로 교환 해주겠다고 제안 합니다.
7. 개인은 $b$보다 $a$를 더 선호하기 때문에 $1를 지불하고 $a$로 교환 합니다.

결과적으로 개인은 처음과 같은 상태 $a$로 돌아오지만, 돈 $3를 잃은 상태가 됩니다. 이 과정을 반복하면 개인은 무한히 돈을 잃고, 타인이 이를 악용해 개인의 재산을 지속적으로 빼앗을 수 있습니다. ~~나의 돈줄~~

</div>

그래서 합리적인 선택을 하는 것(선호가 일관되고, 순환하지 않는 것)이 왜 중요한지 위의 사례로 알 수 있습니다.

그래서 합리적인 선택을 하는 것이 개인에게 이익이 된다는 것을 설명하는 논증이다.

# Evidence of choices inconsistent with rationality

그러나 현실에서는 사람들이 선택을 할 때, 일관된 합리성을 따르지 않습니다.

## Attention Effect

2개 였던 선택지가 3개로 늘어난다면, 사람들의 선호가 바뀔 수 있다는 이론.

새로 추가된 선택지로 주의(attention)가 분산된다는 현상.

## Framing Effect

현실의 사람들은 두 선택에 대한 기대값과 확률만을 고려하는 것이 아니라, 정보가 어떻게 제공되는지(프레이밍)에 따라 다르게 반응한다.

- 의료 분야:
  - "이 수술의 성공률은 90%입니다." → 긍정적 프레이밍
  - "이 수술의 실패 확률은 10%입니다." → 부정적 프레이밍
  - 같은 정보지만, 첫 번째 표현이 더 긍정적으로 느껴짐.
- 소비자 행동:
  - "지금 구매하면 20% 할인!" → 즉각적인 혜택 강조
  - "할인 없이 사면 20% 더 비쌈." → 손실 회피 심리 자극
  - 두 문장은 같은 의미지만, 첫 번째 표현이 더 구매를 유도함.

## Mental Accounting

현실의 사람들은 돈을 어떻게 획득하거나 사용할지에 따라 다르게 평가한다는 것.

- 할인 vs. 보너스
  - $10를 할인 받으면, 더 절약하려는 성향이 있습니다.
  - $10 보너스를 받으면, 더 쉽게 소비하는 경향이 있습니다.
- 투자 의사 결정
  - 사람들은 급여에서 나온 돈과 보너스로 받은 돈을 다르게 관리하는 경향이 있습니다.
  - 보너스로 받는 것도 노동을 통해 얻은 것이지만, "공짜 돈"처럼 느껴져서 더 쉽게 낭비 합니다.
- 도박
  - 도박에서 번 돈을 "공짜 돈"처럼 여기고 쉽게 다시 도박에 투자하는 경향이 있습니다.
  - 그러나 원래 자기 돈을 잃었을 때는 손실을 회피하려고 합니다.

이를 피하기 위해서는 모든 돈을 같은 가치로 보고, 객관적인 기준에서 의사결정을 하는 것이 중요합니다!

# 맺음말

지금까지 선호(Preference)의 개념, 그리고 선호를 표현하는 2가지 방법인 효용 함수와 선택 함수에 대해서 살펴보았습니다.

다음 포스트부터는 "복권(Lotteries)"라는 확률적인 결과를 포함하는 상품들이 있을 때, 개인이 어떻게 선택을 하는지 살펴보고 모델링 해보겠습니다.
