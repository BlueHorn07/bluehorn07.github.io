---
title: "Bundle Preference"
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

다른게 같더라도,
한 재화에 대해서 더 많은 번들이라면
엄격히 더 좋습니다.

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


# Continuity

> 선호는 끊어지지 않고 매끄럽게 이어진다

이것은 어떤 번들 $\mathbf{x}$가 $\mathbf{y}$보다 선호된다면, $\mathbf{x}$ 근처의 번들도 $\mathbf{y}$ 근처의 번들보다 선호되어야 한다는 성질 입니다. 이것은 미소변화량에 대해서 선호가 역전되지 않고 유지된다는 것을 의미합니다.

<div class="definition" markdown="1">

번들 선호 $\succcurlyeq$가 "연속성"을 가진다면,

모든 $\mathbf{x} \succ \mathbf{y}$에 대해 아래가 성립해야 합니다.

There exists an $\epsilon > 0$ s.t. for all bundle $\mathbf{a}, \mathbf{b}$

if $\| \mathbf{a} - \mathbf{x} \| < \epsilon$ and $\| \mathbf{b} - \mathbf{y} \| < \epsilon$,

then $\mathbf{a} \succ \mathbf{b}$.

\* 이때, 거리는 L2 거리를 사용합니다.

</div>

정의를 풀어서 설명하면, $\mathbf{x}$ 근처에 있는 모든 $\mathbf{a}$는 $\mathbf{y}$ 근처에 있는 모든 $\mathbf{b}$ 보다 더 좋다는 것을 말합니다.


## Non-continuous: Lexicographic preference

참고로 사전식 선호는 "불연속성"을 갖는 선호 방식입니다. 아무리 $\mathbf{x}$와 비슷한 $\mathbf{a}$를 잡아도, 아주 미세한 차이만으로 갑자기 선호 순서가 뒤집히는 일이 발생할 수 있기 때문입니다.

예를 들어, 두 재화가 $x_1 = y_1$이고, $x_2 > y_2$인 이유로 $\mathbf{x} \succ \mathbf{y}$ 였다면, $\mathbf{y}$ 재화에 미소변화 $y_1 + h$만 일어나도 두 재화의 선호 관계는 역전 됩니다.


## Continuous preference and Continuous utility

> 연속적인 효용 함수로 표현 가능한 번들 선호는 항상 연속성을 갖습니다.

이 정리는 직접 번들 선호에 대한 연속성을 확인하는 대신, 번들 선호를 표현할 수 있는 연속적인 효용 함수가 존재하는지를 확인하면 된다는 것을 말합니다.

<div class="proof" markdown="1">

TODO... 길다...

</div>

<br/>

참고로 역명제도 성립 합니다.

> 모든 연속성을 갖는 번들 선호는 연속적인 효용 함수로 표현 가능 합니다.

## Existence of Intermediate Indifference Point

> 선호가 연속일 때, $a \succ b \succ c$라면,
> $a$와 $c$를 연결하는 직선 상 어딘가에 $b$와 **무차별한 번들**이 반드시 존재한다

수학적으로 표현하면,

<div class="theorem" markdown="1">

There exists $\lambda \in (0, 1)$ s.t.

$$
\lambda a + (1 - \lambda) c \sim b
$$

</div>

이것이 가능하려면 선호가 끊기지 않고 이어져 있는 경우만 가능 합니다. 그리고 이것은 미적분학의 "중간값 정리(Intermediate Value Theorem)"과도 비슷한 성질인데요, 중간값 정리도 연속 함수가 되기 위해, 또는 연속 함수라면 갖는 성질 입니다.

<div class="theorem" markdown="1">

[중간값 정리]

함수 $f$가 구간 $[a, c]$에서 연속이고, $f(a) > f(b) > f(c)$라면,

아래의 등식을 만족하는 어떤 $\lambda \in (0, 1)$가 존재한다.

$$
f(\lambda a + (1 - \lambda) c) = f(b)
$$

</div>

# Convexity

> 중간이 더 낫다

내용이 길어서 별도 포스트로 분리 했습니다!

➡️ [Bundle Preference: Convexity](/2025/04/14/bundle-preference-convexity/)

# Differentiability

> 선호가 부드럽게 변화한다

요것도 내용이 길어서 별도 포스트로 분리 했습니다!

➡️ [Bundle Preference: Differentiability](/2025/04/14/bundle-preference-differentiability/)
