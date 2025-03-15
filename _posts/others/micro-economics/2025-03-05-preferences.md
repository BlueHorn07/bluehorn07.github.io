---
title: "Preference"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "경제학에서 개인의 선호를 모델링 하는 방법에 대해"
---

# What is preference

개인(individual)의 결정을 모델링 하는 첫걸음.

선호(Preference)는 어떤 대상에 대해 한 사람이 매기는 순위(ranking)에 대한 것임.

경제학에서는 개인의 선호를 "이항 관계(binary relation)"으로 표현함.

- $x \succeq y$ := prefer $x$ than $y$
- $x \sim y$ := prefer $x$ and $y$ equally

$x \sim y$는 풀어서 작성하면, $x \succeq y$ and $x \preceq y$인 상황임. 이걸 두 대상이 "indifference"하다고 함.

$x \succ y$는 $x \succeq y$ but not $x \preceq y$인 상황임. 이 상황은 "strict preference"라고 함.


## Binary Relation

전체 대상을 모은 집합 $X$에 대해서 모든 $(x, y)$ 쌍에 대해 이항 관계(선호)가 존재해야 함(completeness).

그리고 Complete Binary Relation은 "**reflexive**" 성질을 만족한다. 모든 $x \in X$에 대해 자기 자신과의 이항 관계가 성립한다.

Binary Relation이 $x R y$ and $y R z$ 일 때, $x R z$를 만족하면, "**transitive**"라고 한다.

<br/>

위에서 정의한 **"Preference relation"은 complete와 transitive 성질을 만족해야 한다.**

<br/>

추가로 "symmetric" 성질은 $x R y$이면, $y R x$을 만족한다는 것임. indifference relation $\sim$이 이 속성을 만족함.

그리고 Binary relation이 "reflexive", "symmetric", "transitive"를 모두 만족하면, "**equivalence relation**"라고 부름.

# Value Function

개인이 대상에 가치를 매기는 함수 $v$를 말함. 그래서 $x \succeq y$ 선호 관계가 성립한다면, $v(x) \succeq v(y)$가 성립함.

만약 회사에서 집을 구하려는데, 사람들은 통근 시간을 줄이기 위해 회사와 가까울수록 그 집을 더 선호한다고 합니다. 이 경우, $d(x)$라는 거리 함수를 정의할 수 있고, 사람들의 선호는 아래와 같이 모델링 됩니다.

$$
x \succeq y \iff d(x) \le d(y)
$$

이 경우, value function $v(x)$는 $v(x) = - d(x)$가 됩니다.

# Lexicographic preferences

사람들이 어떤 상품을 고를 때, 여러 속성을 비교하여 구매를 결정할 수 있습니다. 만약 맥북을 산다고 하면 CPU 코어 수와 Memory 사이즈를 비교할 것입니다.

"Lexicographic preference"는 (1) 첫번째 속성에서 우위를 보이면 그것을 선택하고, (2) 만약 첫번째 속성값이 동일하다면 두번째 속성을 비교하여 우위를 보이는 걸 선택하는 선호 입니다.

이걸 수식으로 적어본다면...

2가지 complete and transitive 이항 관계 $\succeq_1$과 $\succeq_2$가 있다고 합시다. 이때, 대상에 대한 선호 $\succeq$는 아래와 같이 결정 됩니다.

- $x \succeq y$, if $x \succeq_1 y$
- $x \succeq y$, if $x \sim_1 y$ and $x \succeq_2 y$

complete하고 transitive한 2가지 이항 관게를 활용해 새로운 이항 관계를 만들었습니다. 이 이항 관계도 complete와 transitive를 만족할까요?

# Unanimity Rule

만장일치(Unanimity) 규칙 입니다. 여러 개의 선호 관계를 하나의 이항 관계로 통합하는 방법 입니다.

아이디어는 $n$개의 고려 요소에 대해 모든 요소에 대해 $x$가 $y$보다 선호되거나 적어도 동등하다면, $x$가 $y$보다 낫다고 판단합니다.

$$
x \succeq y \quad \text{if} \quad x \succeq_i y \quad \text{for all } i = 1, 2, …, n.
$$

이 관계는 Transitivity를 만족하지만, Completeness는 보장하지 않습니다. 일부 쌍에 대해서는 만장일치 선호가 발생하지 않을 수 있습니다.

# Majority Rule

다수결 규칙은 여러 개의 선호 관계를 종합하여 최종적인 선택을 내리는 방법 입니다. $n$가지 기준이 있을 때, 절반 이상의 조건에 대해 선호가 성립해야 $x$를 $y$보다 선호하게 됩니다.

이런 선호는 Completeness를 만족합니다. 모든 $(x, y)$ 쌍에 대한 선호를 구성할 수 있습니다. 그러나 Transitivity는 보장되지 않습니다.

## Condorcet paradox

다수결 투표가 비일관성을 가질 수 있음을 보여주는 사례 입니다.

...

