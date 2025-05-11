---
title: "Budget Sets & Demand Functions & Consumer's Problem"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "예산 집합과 그 안에서 소비자의 최선의 선택을 결정하는 방법(Demand Function), 그리고 이것을 효용 함수로 해석해 가장 큰 효용 함수를 내는 결과를 찾는 Consumer's Problem에 대해."
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ
그래도 어영부영 수학과 복수전공을 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }


# Bundle Sets

"예산 집합"은 주어진 예산 $w$와 각 재화에 대한 고정된 가격 $p_1$, $p_2$가 있을 때 각 재화를 구매할 수 있는 가능한 번들을 모두 모은 집합 입니다.

![](/images/others/micro-economics/budget-sets.png){: .fill .align-center style="width: 500px" }

표기는 아래와 같습니다.

$$
B((p_1, p_2), w) = \left\{
  (x_1, x_2) \in X : p_1 x_1 + p_2 x_2 \le w
\right\}
$$

이때, 예산을 한계치까지 사용하는 번들의 집합을 "**Budget Line**"이라고 합니다.

$$
\left\{
  (x_1, x_2) \in X : p_1 x_1 + p_2 x_2 {\color{red} =} w
\right\}
$$

집합이 볼록(convex)하다는 것은 내부의 어떤 두 점을 잡더라도, 그 사이을 있는 선분 위의 모든 점이 그 집합에 다시 속한다는 것을 말합니다. 모든 budget set은 볼록 성질을 갖습니다.

이것은 두 점 $x, y$가 예산 조건을 만족하는 소비 였다면, 선형 조합(Linear Combination)한 새로운 소비 선택 $z = \lambda x + (1-\lambda) y$도 여전히 예산을 만족한다는 것을 말합니다.

## Exchange Economy

"교환 경제"의 상황을 생각해봅시다. 이 상황에서는 사람들은 돈이 아니라 물물 교환으로 원하는 것을 얻어야 합니다. 그리고 "돈"이라는 개념도 없습니다!

이런 경제 환경에서 각 소비자는 본인의 초기 재화 $e = (e_1, e_2)$를 가지고 있습니다. 그리고 이 재화를 시장에서 교환비에 따라 교환할 수 있습니다.

상품 1 하나를 교환하여 상품 2 $\beta$개로 바꿀 수 있다면, 소비자가 가질 수 있는 번들 집합을 아래와 같이 적을 수 있습니다.

$$
\left\{
  (x_1, x_2) \in X : (e_1 - x_1) \beta \ge x_2 - e_2
\right\}
$$

위의 부등식에서

- 좌변
  - $x_1 \le e_1$ 입니다.
  - 그래서 내가 내놓은 재화 1의 양은 $(e_1 - x_1)$가 됩니다.
  - 시장의 교환비는 $\beta$ 입니다.
  - 즉, 좌변은 내가 얻을 수 있는 상품 2의 양 입니다.
- 우변
  - $x_2 \ge e_2$ 입니다.
  - 물물교환을 통해 추가로 소비하려는 상품 2의 양은 $(x_2 - e_2)$가 됩니다.

부등식이 되는 이유는 "내가 교환으로 얻을 수 있는 상품 2의 갯수 $\ge$ 내가 추가로 얻을 수 있는 상품 2의 갯수"를 의미합니다. 내가 추가로 얻을 수 있는 상품 2의 갯수가 교환으로 얻을 수 있는 상품 2의 갯수로 제한된다고 해석하는게 더 쉬울 것 같네요.

이 부등식을 잘 정리하면 예산 집합을 얻을 수 있습니다.

$$
\left\{
  (x_1, x_2) \in X : \beta x_1 + x_2 \le \beta e_1 + e_2
\right\}
$$

우변의 $\beta e_1 + e_2$는 고정값 입니다. 이걸 예산 $w$라고 봅니다. 좌변은 예산 안에서 내가 가질 수 있는 재화의 조합을 의미 합니다: $B((\beta, 1), \beta e_1 + e_2)$.

그래서 표기를 아래와 같이 하는 경우도 있습니다.

$$
B((p_1, p_2), w) = \left\{
  (x_1, x_2) : p_1 x_1 + p_2 x_2 \le w
\right\}
$$

아까전에는 교환비 $\beta$로 표현했는데, 여기에선 각 재화의 가격 $p_i$로 표기합니다.


# Demand Function

"수요 함수"는 각 예산 집합 $B((p_1, p_2), w)$ 안에서 소비자 선택한/선택할 번들을 매핑하는 함수 입니다.

$$
x : B \rightarrow (x_1^{\ast}, x_2^{\ast})
$$

$B((p_1, p_2), w)$는 소비자가 주어진 예산 $w$ 안에서 선택 가능한 모든 번들의 집합입니다. $x((p_1, p_2), w) = x(B)$는 소비자가 예산 집합 내에서 선택한 번들 입니다. 이때, 소비자는 자신의 효용(utility)가 극대화 되는 최적의 소비 번들을 선택 합니다.

또, 소비자는 예산을 최대한 사용하도록 소비를 합니다! 이것은 최적의 소비가 항상 "Budget Line" 위에서 일어난다는 것을 말합니다.

## Examples

### Equal Amounts of the Goods

소비자는 두 상품의 항상 같은 갯수가 되도록 구매 합니다. 즉, 예산 집합 안에서 $x_1 = x_2$가 되고, 그 갯수가 최대가 되는 소비를 합니다. 그러면, 수요 함수의 결과는 아래와 같습니다.

$$
p_1 x_1 + p_2 x_2 = (p_1 + p_2) x^\ast = w
$$

따라서,

$$
x(B) = (x^{\ast}, x^{\ast}) = \left(\frac{w}{p_1+p_2}, \frac{w}{p_1+p_2}\right)
$$

### Half Spent on each Good

이번에는 재화의 절반씩을 각 상품에 소비하는 소비자 입니다. 이 경우 수요 함수의 결과는 아래와 같습니다.

$$
\begin{aligned}
p_1 x_1^{\ast} &= w / 2 \\
p_2 x_2^{\ast} &= w / 2 \\
\end{aligned}
$$

따라서,

$$
x(B) = (x_1^{\ast}, x_2^{\ast})
= \left(\frac{w}{2p_1}, \frac{w}{2p_2}\right)
$$


실제로 이런 소비자들이 있을 것 같고, 지금까지의 예시 수요 함수들도 별도 어렵지 않습니다 ㅎㅎ

### All Wealth Spent on the Cheaper Good

이 소비자는 두 상품 중 가격이 더 저렴한 상품만을 모조리 구매하려는 소비 성향을 가지고 있습니다. 그래서 수요 함수의 결과가 이렇게 나옵니다.

$$
x(B) = \begin{cases}
\left(\frac{w}{p_1}, 0\right) & \text{if} \; p_1 < p_2 \\
\left(\frac{w}{2p_1}, \frac{w}{2p_2}\right) & \text{if} \; p_1 = p_2 \\
\left(0, \frac{w}{p_2}\right) & \text{if} \; p_1 > p_2
\end{cases}
$$

### Purchase one good up to limit

이 소비자는 상품 $x_1$을 어떤 한계치(ex: 7개)까지 구매하는 것을 우선시 합니다. 상품 $x_1$에 대한 구매가 충족되면, 이후부터는 남은 금액으로 $x_2$를 구매 합니다.

$$
x(B) = \begin{cases}
\left(\frac{w}{p_1}, 0\right) & \text{if} \; w/p_1 \le 7 \\
(7, \frac{w-7p_1}{p_2}) & \text{otherwise}
\end{cases}
$$

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

번들 선호가 "엄격하게 볼록"하다는 성질은 소비자가 선형 결합으로 만든 "혼합된 번들"을 더 선호한다는 의미 입니다.

번들 선호가 이 성질을 가진다면, Consumer's Problem의 해는 유일하게 하나만 존재한다는 것을 보장 합니다.

### Monotone

<div class="definition" markdown="1">

If the preference relation is "**monotone**", <br/>
then any solution of the consumer's problem is on the budget line.

</div>

번들 선호가 "단조적"이라는 건, 더 많이 소비할수록 좋다는 것을 말합니다. 다르게 표현하면 재화가 "정상재(normal goods)"라는 것을 말합니다.

이 경우에는 소비자가 모든 예산을 다 써버리는 것이 효용을 가장 높이는 방법이 됩니다! 그래서 최적의 소비는 "Budget Line" 위에 존재합니다.

## Examples

### complementary Goods

### Substitutable Goods



