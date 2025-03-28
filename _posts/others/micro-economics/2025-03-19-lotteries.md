---
title: "Lotteries"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
---

# Lotteries

확률적인 결과를 포함하는 선택지 입니다. 사람이 선택을 하면, 보상이 확률적으로 결정 됩니다.

어떤 보상의 집합을 $Z$라고 할 때,

Lottery $p$는 $z \in Z$에 확률 $p(z)$를 할당 합니다.

로터리에서 확률이 0보다 큰 보상의 집합을 support $\text{supp}(p)$라고 합니다.

$$
\text{supp}(p) = \left\{ z \in Z | p(x) > 0 \right\}
$$

- $L(Z)$
  - set of all lotteries over $Z$
- $[z]$
  - the lottery that yields the prize $z$ with probability 1
  - Deterministic Lottery, Unity Lottery

그리고 Lottery에 대한 표기는 아래와 같이 할 수 있습니다.

each $\alpha_k = p(z_k)$

$$
\alpha_1 \cdot z_1
\oplus
\cdots
\oplus
\alpha_n \cdot z_n
$$

## Visualize set of lotteries

![](/images/others/micro-economics/lottery-1.png){: .fill .align-center style="width: 300px" }

만약 $Z$가 두 개의 상품 $z_1, z_2$로 구성 되어 있다고 한다고, 각 상품에 대한 확률 $p_1, p_2$가 할당 된다고 하자.

확률의 합은 항상 1이어야 하기 때문에 $p_1 + p_2 = 1$을 만족하고, 이는 위의 그림처럼 평면 위에 표현할 수 있습니다.

![](/images/others/micro-economics/lottery-2.png){: .fill .align-center style="width: 300px" }

만약, $Z$가 3개의 상품으로 구성 되어 있었다면, 비슷하게 3차원의 공간 뒤에 그릴 수 있습니다.


# Preference over lotteries

어떤 사람 앞에 여러 종류의 복권이 있습니다. 사람마다 선호하는 복권이 다를 테죠. 아래에서는 사람들의 복권에 대한 선호에 대해 얘기해봅니다.

## Pessimist

비관주의자의 예시 입니다. 이 사람은 복권을 평가할 때, 그 안에서 일어날 수 있는 결과들 중 "가장 나쁜 결과"를 기준으로 판단 합니다.

그 나쁜 결과가 아무리 작은 확률을 갖더라도, 비관주의자는 가장 나쁜 결과를 기준으로 그 복권을 평가 합니다.

비관주의자는 각 복권을 가장 나쁜 결과 $w(p)$로 대체해서 생각 합니다. 그리고, 그 나쁜 결과 중에서 가장 큰 효용을 가지는 복권을 선호 합니다.


## Good and Bad

이 사람은 복권은 좋은것(good)과 나쁜것(bad) 두 집단으로 분할 하여 생각 합니다.

그리고, 좋은것의 집단의 확률 모두 계산해 $G(p)$로 둡니다.

$$
G(p) = \sum_{z\in\text{good}} p(z)
$$

이 사람은 각 복권에 대한 좋은 걸 얻을 확률 $G(p)$를 구하고, 이것이 높은 복권을 선호 합니다.

<br/>

이 사람은 좋은 결과의 가치는 무시하고, 좋은 결과가 나올 확률만을 기준으로 합니다.


## Minimizing Options

이 사람은 일어날 수 있는 경우의 수가 작은 걸 선호 하는 사람 입니다. 이유를 들어보니, 가능한 경우의 수가 적을수록 대비하기 쉽고 안심 된다고 하네요. 즉, 불확실성이 적을수록 선호하는 사람 입니다.

$$
p > q \iff \| \text{supp}(p) \| \ge \| \text{supp}(p) \|
$$

이런 경우는 어떤 상황을 준비하는게 중요한 상황, 예를 들면 의료 수술이나 군사 작전에선 이 선호가 유용할 수 있습니다.


## Summary

위에서 복권에 대한 3가지의 선호 방식을 살펴보았습니다...만 복권에 대한 선호는 정말 다양하게 정의할 수 있습니다.

앞으로는 이런 복권 선호들 중에서 "좋은 성질"을 만족하는 특별한 복권 선호들만 골라서 좀더 살펴보고자 합니다.

# Properties

## Continuity

> 상품권 100만원, 70만원, 40만원이 있습니다. 만약 100만원이 0.5 확률, 40만원이 0.5 확률인 복권이 있다면, 그 사람은 70만원과 그 복권을 무차별하게 선호 한다고 합니다.

위와 같은 상황이 복권에 대한 선호가 Continuity를 만족한다고 합니다. 형식을 갖춰서 적어보면,

<div class="definition" markdown="1">

복권에 대한 보상 $Z$에 대해 아래와 같이 연속적인 선호가 있습니다.

$$
[a] \succ [b] \succ [c]
$$

그리고, $b$와 동등한 가치로 여겨지는 어떤 복권을 만들어낼 수 있다면,

$$
[b] \sim \alpha \cdot a \oplus (1-\alpha) \cdot c
$$


"선호 관계가 복권에 대해 연속성을 갖는다"고 말할 수 있습니다.

</div>

비관주의자가 갖는 선호는 연속성을 갖지 못합니다. 왜냐하면, 비관주의자는 $a$와 $c$를 섞은 복권이 있으면 항상 $c$를 기준으로 판단하기 때문입니다.

"Good and Bad" 선호는 vacuously 연속이라고 합니다. 그 이유는 좋은 결과가 나올 확률의 총합 $G(p)$만 보고 판단하기 때문에 보상에 대한 조합이 의미가 없기 때문입니다. 이 선호에서는 연속성을 테스트할 상황 자체가 없다고 합니다.

"Minimizing Options"도 vacuously 연속이라고 합니다. 왜냐하면, $[a] \succ [b] \succ [c]$와 같은 보상 간의 선호가 아예 정의되지 않기 때문입니다. 그래서 애초에 연속성에 대한 전재가 성립하지 않고, 연속성이 vacuously 만족한다고 봅니다.



# Compound Lottery

복합 복권, 복권 안의 복권. 두 단계 이상의 무작위성이 있을 때를 모델링 하는 방법 입니다.


<div class="definition" markdown="1">

보상에 대한 집합 $Z$가 있고, 그 위에 정의된 복권 $p_1, ..., p_k$가 있습니다. 이들은 $L(Z)$의 원소 입니다.

복합 복권은 아래와 같이 정의 됩니다.

$$
\alpha_1 p_1 \oplus \alpha_2 p_2 \oplus \cdots \oplus \alpha_k p_k
$$

이것을 각 재화 $z \in Z$에 대한 확률을 풀어쓰면 이렇게 됩니다.

$$
\text{Prob}(z) = \sum_{i=1}^{k} \alpha_k \cdot p_k(z)
$$

</div>

# Independence

복권의 부분에 대한 일관성이 복권 전체에 대한 일관성과 일치해야 한다는 성질 입니다.

복권 내의 특정 항목을 다른 걸로 바꿨을 때, 복권 전체에 대한 선호도 그에 맞게 일관되게 바뀌어야 한다는 성질 입니다.

수식으로 이해하는게 좀더 편합니다.

<div class="definition" markdown="1">

두 복권 상이에 아래와 같은 선호가 성립 합니다.

$$
[z_k] \succeq \beta a \oplus (1-\beta) b
$$

그러면, 아래의 선호도 성립 한다면, 그 선호가 복권에 대해 독립성을 갖습니다.

$$
\alpha_1 z_1 \oplus \cdots \oplus \alpha_k z_k \oplus \cdots \oplus a_n z_n

\succeq

\alpha_1 z_1 \oplus \cdots \oplus \alpha_k (\beta a \oplus (1-\beta) b) \oplus \cdots \oplus a_n z_n
$$

그리고 이 명제에 대한 역도 성립 합니다.

</div>

