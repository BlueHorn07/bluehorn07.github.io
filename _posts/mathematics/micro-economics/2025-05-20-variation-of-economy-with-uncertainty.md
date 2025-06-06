---
title: "Variation of Economy with Uncertainty"
toc: true
toc_sticky: true
categories: ["Macro Economics"]
excerpt: ""
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ 그래도 수학과 복수전공도 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# 들어가며

[지난 포스트](/2025/05/20/exchange-economy-with-uncertainty/)부터 "불확실성이 있는 교환 경제"에 대해 살펴보고 있습니다!

지난 포스트에서는

- 상태1과 상태2에서의 전체 재화의 수량이 동일하다고 가정 했습니다.
- 시장에 참여하는 모든 사람이 strictly risk-averse 하다고 가정 했습니다.
- 상태1과 상태2가 발생할 확률 $\pi_1$과 $\pi_2$를 모든 개인이 같은 확률로 예상한다고 가정 했습니다.

이번 포스트에선 이 가정들을 하나씩 해체하면서, 이 경우에 시장에서의 균형이 언제 발생하는지 살펴봅니다.


# Collective Uncertainty

현실에서는 지진이나 태풍, 전쟁과 같이 경제 전체의 부(wealth)가 불확실한 사건이 발생할 수 있습니다.
이것을 "**집단적 불확실성(Collective Uncertainty)**"이라고 합니다.

재난이 일어나면, 시장 전체의 총 재화가 줄어듭니다. 이 상황에서도 사람들은 여전히 소비를 원하지만 자원이 절대적으로 부족해졌습니다.

이 상태에서 받는 "1단위의 돈"은 훨씬 더 가치있게 느껴지고, 보험회사 입장에서도 재난 상태에 지급하는 1달러와 평상시에 지급하는 1달러는 다르게 느껴지고, 재난 상태에서 지급하는 돈이 보험사에게 더 부담 입니다.

따라서, 이런 재난 상황에 대한 보험은 일반적으로 더 높은 보험료 $p$가 책정 됩니다.

예를 들어, 상태1이 총 재화를 감소시키는 재난(disaster)이라고 합시다. 그러면 균형 가격이

$$
\frac{p_1}{p_2} > \frac{\pi_1 \cdot u'(x_1)}{\pi_2 \cdot u'(x_2)}
$$

와 같이 MRS 비율보다 더 커지게 됩니다. 이것은 $p_1$의 가격이 비-재난 상태보다 더 커진다는 것을 말합니다.


# Economy with a Risk-neutral Insurer

지금까지 살펴본 것은 시장에 위험 회피자만 있는 경우 였습니다. 만약, 위험 중립적인 보험자가 있다면 시장이 어떻게 작동할까요?

시장의 모든 개인은 재화를 "1개"만 가지고 있고, 상태1이 닥쳤을 때 그 재화가 모두 파괴된다고 합시다.

보험사(insurer)는 위험이 서로 독립적으로 발생하는 시장에서 보험을 판매합니다. 위험이 동시다발적으로 일어나지 않기 때문에, 하나의 시장에서 발생한 위험은 보험사 입장에서는 작은 부분으로 여겨집니다.

보험사는 여러 시장으로 위험을 분산 했습니다. 그래서 개별 시장의 재난은 위험으로 느끼지 않습니다. 이런 경우는 "**위험 중립(rick-neutral)**"이라고 합니다.

<br/>

한 명의 개인은 위험 회피적이므로 보험을 들길 원합니다. 만약 보험사가 시장에 존재한다면, 이 개인들을 완전히 보험하거나 부분적으로 보험하는 상품을 출시합니다.

## Competitive Equilibrium with Insurer

보함사가 존재하는 시장에서의 경쟁 균형은 어떻게 이뤄질까요?

불확실성이 있는 교환 경제 $<N, (u^i)_{i \in N}, (\pi_1, \pi_2), e>$인 시장이 있습니다. 이때, $N = \left\\{ I \right\\} \cup M$으로 구성 되는데요. $I$는 risk-neutral의 보험사, $M$은 strictly risk-averse한 개인들 입니다.

초기 자원 분배가

- $e(I) = (\alpha, \alpha)$
  - 이것은 보험사가 상태 1·2일 때 모두 $\alpha$ 만큼의 자원을 가지고 있음을 말합니다.
- $e(i) = (0, 1)$ for $i \in M$
  - 이것은 개인이 상태 1일 때는 알거지, 상태 2일 때는 "1개" 만큼의 자원을 가지고 있는 상황 입니다.

이라고 합시다. 그리고 $m = \vert M \vert$이라고 합시다.

### Sufficient Insurer Money

보험사가 충분한 자금이 있다고 합시다. 얼마나 충분하냐면, $\alpha \ge m \pi_2$ 만큼 충분 합니다.

개인은 상태 1이 일어나면 재산을 쫄딱 잃습니다. 그래서 $\pi_1$ 만큼의 비용이 들더라도, 보험을 들어서 위험을 회피하고 싶습니다.

이런 개인이 $m$명 존재합니다. 따라서, $m$명의 개인은 재화가 $a_2(u) = \pi_2 = 1 - \pi_1$로 바뀌어도 괜찮으니 보험을 가입합니다. 만약 상태 1이 발생하지 않는다면, 보험사는 $+ m \pi_1$ 만큼의 보험수익을 얻습니다. 따라서, $a_2(I) = \alpha - m \pi_1$가 됩니다.

$m$명 모두 보험을 가입 했습니다. 따라서, 상태 1이 발생하면, 이제 모든 개인은 보험사에게 보장을 받습니다. 이 보험은 보험비를 내고 남은 재산 $\pi_2$를 그대로 보전하는 보험 입니다. 따라서, $a_1(i) = \pi_2$가 됩니다. 보험사는 보험료를 지급 했으니 $a_1(I) = \alpha - m \pi_2$가 됩니다.

종합하면,

$$
\begin{aligned}
a(I) &= (\alpha - m \pi_2, \alpha + m \pi_1) \\
a(i) &= (\pi_2, \pi_2)
\end{aligned}
$$

"완전 보험" 상태로 균형이 이뤄지고, 개인은 어떤 상태가 발생하든 $\pi_2$ 만큼의 재화를 안정적으로 보장 받습니다.

### Insufficient Insurer Money

이번에는 보험사에 충분한 돈이 없어서 모두를 보장하기 어려운 상황 입니다. 얼마나 부족하냐면, $\alpha < m \pi_2$인 상황 입니다.

이런 경우 보험사는 모든 개인이 아니라 일부에 대해서만 부분 보험을 맺습니다.

그리고 보험사의 보장비가 희소하기 때문에, 보험 가격 또한 올라가게 됩니다. 얼마나 오르냐면, $p_1/p_2 > \pi_1/\pi_2$ 비율보다 큰 가격이 책정 됩니다.

그러나 지금 시장의 모든 개인은 위험 회피적이기 때문에, 모든 개인 $m$이 이 보험을 이를 악 물며 ㅠㅠ 가입합니다. 보험사는 $\alpha$라는 보장 대비 금액을 $m$명에게 동등하게 나눠줘야 합니다. 따라서, 상태 1이 발생하면,

$$
\begin{aligned}
a_1(I) &= 0 \\
a_1(i) &= \alpha/m
\end{aligned}
$$

으로 자원을 분배 합니다.

이제 상태2에서의 균형을 살펴봅시다. 상태1에서 $\alpha/m$ 만큼의 보장 금을 받기 위해서는 상태2에서 보장금의 $p_1/p_2$ 만큼의 보험료를 지불 해야 합니다. 반대로 보험사는 상태2에서 $m$명에게 $\alpha/m \cdot p_1/p_2$ 만큼의 보험료를 납부 받습니다. 따라서,

$$
\begin{aligned}
a_2(I) &= \alpha + m \cdot \left(\frac{\alpha}{m} \cdot \frac{p_1}{p_2}\right) \\
a_2(i) &= 1 - \frac{\alpha}{m} \cdot \frac{p_1}{p_2}
\end{aligned}
$$

뭔가 어렵군요...;;


# Heterogeneous Beliefs

기존에는 상태1과 상태2가 발생할 확률 $\pi_1$과 $\pi_2$를 모든 개인이 같은 확률로 예상한다고 가정 했습니다.

이번에는 경제 참여자들이 미래 상태에 대해 서로 다른 확률을 믿고 있다고 가정 합니다! 즉, 확률 인식이 개인 마다 모두 다른 상황 입니다.

각 개인은 믿음의 차이로 인해 서로 다르게 행동 하고, 거래 합니다.

예를 들어서, 어떤 개인 $A$는 상태 1이 발생할 확률 $\pi_1 \approx 1$, 즉 아주 확실하게 일어난다고 생각한다면, 상태 1에 대한 보험을 높은 가격일지라도 꼭 구매할 것 입니다.
