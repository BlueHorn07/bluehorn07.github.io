---
title: "Consumer's Problem"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "예산 집합과 수요 함수가 주어졌을 때, 가장 큰 효용을 내는 결과를 찾는 Consumer's Problem에 대해."
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ
그래도 어영부영 수학과 복수전공을 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# Consumer's Problem

합리적인(rational) 소비자는 자신의 선호가 고정(fixed) 되어 있다고 합니다. 즉, 어떤 번들이 더 좋고 더 좋은지를 항상 일관성 있게 판단하고 행동 합니다.

이 소비자는 예산의 제약 아래에서 자신의 선호에 따라 가장 좋은 번들을 선택합니다. 이때, 가장 좋은 번들(best bundle)은 "**가장 높은 효용을 주는 번들**"을 말합니다! 그리고 이 베스트 번들을 고르는 문제를 "**Consumer's Problem**"라고 부릅니다.

$$
\underset{(x_1, x_2) \in B}{\text{argmax}} \; u(x_1, x_2)
$$

## Solution Existence

소비자 문제의 해가 존재하기 위해서는 아래의 성질들이 만족해야 합니다. 각 성질에 대한 증명도 필요하지만... 일단 스킵 하겠습니다!

### Continuous

<div class="definition" markdown="1">

If the preference relation is "**continuous**", <br/>
then the consumer's problem has a solution

</div>

선호가 연속성을 가진다면, 즉 미소 변화에 대해 소비자의 선호가 급격히 바뀌는게 아니라면, 예산 집합 안에서 최적의 소비 번들에 대한 **Solution이 반드시 존재**합니다. 이것은 해의 존재성을 보장하는 조건 입니다.

### Strictly Convex

<div class="definition" markdown="1">

If the preference relation is "**strictly convex**", <br/>
then the consumer's problem has at most one solution.

</div>

$$
\begin{gather*}
\lambda a + (1 - \lambda) b \, {\color{red} \succ} \, a \\
\text{and} \\
\lambda a + (1 - \lambda) b \, {\color{red} \succ} \, b
\end{gather*}
$$

번들 선호가 "엄격하게 볼록"하다는 성질은 소비자가 선형 결합으로 만든 "혼합된 번들"을 더 선호한다는 의미 입니다.

번들 선호가 convexity 성질을 가진다면, Consumer's Problem의 **해가 유일하게 존재**한다는 것을 보장 합니다.

### Monotone

<div class="definition" markdown="1">

If the preference relation is "**monotone**", <br/>
then any solution of the consumer's problem is on the budget line.

</div>

번들 선호가 "단조적"이라는 건, 더 많이 소비할수록 좋다는 것을 말합니다. 다르게 표현하면 재화가 "정상재(normal goods)"라는 것을 말합니다.

이때는 소비자가 모든 예산을 다 써버리는 것이 효용을 가장 높이는 방법이 됩니다!
그래서 최적의 소비는 "**Budget Line 위에**" 존재합니다.

## Examples

### Complementary Goods

두 재화가 "보완재"라면, 두 재화가 같은 수량 만큼 있어야 가치가 있습니다. 그래서, 효용 함수는 $u(x) = \min(x_1, x_2)$로 표현 됩니다.

그리고 보완재에서의 선호는 "단조성(monotone)"을 갖습니다. 더 많이 소비할 수록 이득입니다. 따라서, CP의 해는 버짓 라인 위에 존재합니다.

$$
p_1 x_1 + p_2 x_2 = w
$$

그리고, 두 재화가 정확히 같은 수량만큼 존재해야 하므로, $x_1 = x_2$가 성립합니다. 따라서,

$$
p_1 x^{\ast} + p_2 x^{\ast} = w
$$

따라서, 솔루션은

$$
x^{\ast} = \left(\frac{w}{p_1 + p_2}, \frac{w}{p_1 + p_2}\right)
$$

<br/>

수업 자료에서는 보완재 상황이 유일해르 갖지만, convex 성질만 만족하지, strictly convex 조건을 만족하는 것은 아니라고 합니다. (뭔가 convex 조건만 만족해도 유일해가 존재할 수 있는 것 같습니다. strictly convex라면, 반드시 유일해이고요!)

### Substitutable Goods

이 소비자는 두 재화의 총합이 최대가 되는 것을 최고로 생각합니다.
그래서 이 소비자의 효용 함수는 $u(x) = x_1 + x_2$로 표현 됩니다.

- $p_1 < p_2$
  - 소비자는 수량을 극대화 하기 위해 재화1만 구매합니다.
  - $x^{\ast} = (w/p_1, 0)$
- $p_1 > p_2$
  - 이번에는 재화2만 구매합니다.
    - $x^{\ast} = (0, w/p_2)$
- $p_1 = p_2$
  - 버짓 라인 위에 있다면, 재화1과 재화2를 어떤 조합으로 구매해도 상관 없습니다.
  - 왜냐하면 가격이 같기 때문에, $x_1 + x_2 = w/p$로 고정 되기 때문입니다!
  - 버짓 라인 위의 모든 점이 솔루션이 됩니다.
  - 다중해가 발생하는 대표적인 예시 입니다!

# 맺음말

저번 포스트부터 새로운 용어가 정말 많이 나오는 포스트 였지만, 그렇게 어려운 것들은 아니었습니다.

- Budget Set $B$
- Demand Function $x(B)$
- Consumer's Problem

이번 포스트에서 가장 중요한 것은 마지막에 있던 "Consumer's Problem" 입니다!
앞으로 이어지는 내용은 계속해서 소비자의 최적 선택을 어떻게 찾을 것인지, 언제 소비자의 최적 선택이 되는지를 살펴봅니다!
