---
title: "Bundle Preference: Monotonicty"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: "번들 상품에 대해 개인이 갖는 선호에 대해."
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ
그래도 어영부영 수학과 복수전공을 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# 들어가며

[이전 포스트](/2025/04/12/bundles-of-goods/)에서는 번들의 정의와 번들 위에서 정의한 선호의 사례들을 살펴보았습니다.

- Constant Tradeoff
- Only Preference
- Stepwise Preference
- Complementary Goods
- Ideal Bundle
- Lexicographic Preference

이번 포스트에서는 번들에 대한 선호를 더 깊이 이해하기 위한 핵심 성질들을 살펴봅니다. 이 특성을 바탕으로 소비자 행동에 대한 기초를 다지게 됩니다.

참고로 여기서 다루는 "단조성", "연속성"은 앞에서 [복권에 대한 선호](/2025/03/19/lotteries/)를 다룰 때도 등장 했었습니다. 복권 선호에서의 성질과 번들 선호에서의 성질을 비교해보는 것도 좋은 접근 입니다.

# Monotonicity

> 더 많이 가지면 더 좋다

번들 선호가 "단조성(Monotonicity)"를 가지면, 아래의 성질을 만족 한다는 것 입니다.

$$
\begin{gather*}
(x_1, x_2) \succcurlyeq (y_1, y_2) \\
\text{when} \\
x_1 \ge y_1
\quad \text{and} \quad
x_2 \ge y_2
\end{gather*}
$$

이때, Strictly prefer $\succ$를 정의하는 방법이 2가지가 있는데,

<div class="proof" markdown="1">

[일반 단조성]

모든 재화가 더 많아야 엄격히 더 좋습니다.

$$
\begin{gather*}
(x_1, x_2) \succ (y_1, y_2) \\
\text{when} \\
x_1 > y_1
\quad \text{and} \quad
x_2 > y_2
\end{gather*}
$$

</div>


<div class="proof" markdown="1">

[강한 단조성]

다른 모든 재화의 양은 같지만, 하나의 이상의 재화가 더 많이 있다면,
그 재화가 엄격히 더 좋습니다.

$$
\begin{gather*}
(x_1, x_2) \succ (y_1, y_2) \\
\text{when} \\
x_1 \ge y_1
\quad \text{and} \quad
x_2 \ge y_2
\quad \text{and} \quad
\mathbf{x} \ne \mathbf{y}
\end{gather*}
$$

</div>

일반 단조성과 강한 단조성 둘다 "완비성"은 갖추지 못합니다. $x_1 > y_1, x_2 < y_2$인 상황에서는 선호를 매길 수 없거든요.

## Examples

이전 포스트에서 살펴봤던 번들 선호가 "단조성"을 만족하는지 살펴봅시다.

| Example | Monotonicity | Strong Monotonicity |
| - |:-:|:-:|
| Constant tradeoff | o | △ |
| Only Preference | o | x |
| Stepwise Preference | o | x |
| Complementary Goods | o | x |
| Ideal Bundle | x | x |
| Lexicographic | o | o |

하나씩 살펴봅시다.

<div class="proof" markdown="1">

[Constant tradeoff]

효용함수가 $u(x_1, x_2) = v_1x_1 + v_2x_2$이므로, 단조성을 만족 합니다.

단, 강한 단조성은 $v_1, v_2 > 0$일 때만 만족합니다. 왜냐하면, 한 재화라도 가치가 0이면 그쪽으로 아무리 늘려도 선호가 변하지 않기 때문입니다.

$v_1 = 0$인 상황이라면, $(1000, 4) \sim (1, 4)$를 만족 합니다.
그러나 이것은 강한 단조성을 위배하는데,

- $1000 > 1$
- $4 \ge 4$
- $(1000, 4) \ne (1, 4)$

이므로 강한 단조성에 의하면 $(1000, 4) \succ (1, 4)$가 되어야 하는데,
$v_1 = 0$이라면 둘이 무차별 하다는 결과가 나옵니다.
따라서, $v_1, v_2 > 0$ 둘다 양수가 되어야 $\succ$ 선호가 "강한 단조성"을 만족한다고 할 수 있습니다.

</div>

<div class="proof" markdown="1">

[Only Preference]

단조성은 만족하지만, 강한 단조성은 만족하지 못합니다. 그 이유는 위의 "Constant tradeoff"에서 $v_1 = 0$ 또는 $v_2 = 0$인 경우가 "Only Preference" 경우이기 때문입니다.

</div>

나머지 번들 선호에 대한 설명은 스킵 하겠습니다... ㅎㅎ

# 맺음말

이제 나머지 번들 선호의 성질들도 살펴봅시다!

- [Monotonicity](/2025/04/13/bundle-preference-monotonicity/)
- [Continuity](/2025/04/14/bundle-preference-continuity/)
- [Convexity](/2025/04/14/bundle-preference-convexity/)
- [Differentiability](/2025/04/14/bundle-preference-differentiability/)
