---
title: "Choice Functions"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "선택 함수란 무엇인가? 그리고 합리적인 선택은 어떻게 결정 되는가?"
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ
그래도 어영부영 수학과 복수전공을 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# What is Choice Problem

지금까지는 개인의 선호가 이항관계 $\succeq$로 명확히 제시되거나, 효용 함수를 통해 수치적으로 계산하였습니다.

이제부터는 개인의 선호를 "선택 함수(Choice Function)"를 통해 표현하는 방법을 살펴봅니다.

<div class="definition" markdown="1">

Choice Function $c$ is a function that select one element from subsets of $X$.

$$
c: \mathsf{P}(X) \setminus \emptyset \rightarrow X
$$

For any subset $A \subseteq X$, $c(A) \in A$ should be hold.

</div>

예시를 들어보자면, 선택 함수는 아래와 같이 정의할 수 있습니다.

- $c(\left\\{a, b, c\right\\}) = a$
- $c(\left\\{a, b\right\\}) = a$
- $c(\left\\{b, c\right\\}) = b$
- $c(\left\\{a, c\right\\}) = a$

# Rationality

어떤 집합 $X$가 주어졌을 때, 그 위에서는 가능한 선택 함수가 아주 많습니다. 그런데, 이 선택 함수는 합리적(rational) 하기도 하고, 비합리적(irrational) 하기도 합니다. "합리적"이라는 표현은 선택 함수가 어떤 패턴을 따른 것이지, 그 선택이 옳다는 것을 말하지는 않습니다.


## Example - rational

아래의 선택 함수는 합리적 입니다.

- $c(\left\\{a, b, c\right\\}) = a$
- $c(\left\\{a, b\right\\}) = a$
- $c(\left\\{b, c\right\\}) = b$
- $c(\left\\{a, c\right\\}) = a$

부분 집합에 대해 일괄된 선호 관계를 보입니다.

## Example - irrational

아래의 선택 함수는 "비합리적"입니다.

- $c(\left\\{a, b\right\\}) = a$
- $c(\left\\{b, c\right\\}) = c$
- $c(\left\\{a, c\right\\}) = b$

이 경우, 두 원소 사이에 존재하는 preference가 일관 되지 않기 때문에, $c(\left\\{a, b, c\right\\})$의 값을 결정할 수 없습니다.

## Rationalizable

<div class="definition" markdown="1">

A choice function is "**rationalizable**" if there is a preference relation <br/>
s.t. for every choice problem the alternative specified by the choice function is the *best* alternative according to the preference relation.

</div>

합리적인 선택 함수는 그 결과가 일정한 기준(ex: 선호 관계)에 따라 항상 일관된 방식으로 선택을 수행 합니다. 이때는 선택 함수의 어떤 선택 결과가 다른 선택의 결과와 모순되지 않습니다. 선택 함수가 일관성을 갖는다면 아래 조건을 만족해야 합니다.

- **Transitivity**
  - If $c(\left\\{a, b\right\\}) = a$ and $c(\left\\{b, c\right\\}) = c$,
  - then $c(\left\\{a, c\right\\}) = a$가 되어야 한다.
- **Completeness**
  - 어떤 두 개의 대안 $a$, $b$가 주어지면 둘 중 하나를 반드시 선택해야 합니다.
- **Consistency** in Choice
  - 만약 value(or distance) 큰 것을 선택한다고 한다는 규칙이 있으면, 이것이 **모든 부분집합에 대해 일관되게 적용** 되어야 합니다.

# In Reality

TODO

현실에서 실제 인간의 선택은 항상 고정된 선호에 의해 이뤄지는 것이 아니고 맥작적인 정보를 반영해야 함. -> 연어와 스테이크 문제

맥락이 바뀌면, 사람의 선택도 바뀔 수 있다.

# 맺음말

여기에서 살펴본 일관된 선택을 하는 아주 나이스한 "선택 함수"를 모아서, 그들이 "Property $\alpha$" 성질을 갖는다고 합니다.

이어지는 포스트에서는 선택 함수가 나이스하도록 하는 성질인 "[Property $\alpha$](/2025/03/17/property-alpha/)"에 대해 살펴보겠습니다.
