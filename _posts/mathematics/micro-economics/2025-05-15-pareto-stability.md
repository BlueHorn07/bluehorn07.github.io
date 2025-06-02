---
title: "Pareto Stability"
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

# 들어가며

분배의 공정성?

파레토 안정이 경쟁 균형은 아닐 수 있음?

후생경제학?

# Welfare Economics

"후생 경제학"은 자원 배분이 얼마나 "좋은지" 평가하는 경제학의 분야 입니다.

주어진 자원이 있을 때, 이것이 사람들 사이에 어떻게 배분 되는지, 그리고 어떤 배분이 사회 전체의 행복에 기여하는지를 탐구 합니다.

"후생(厚生)"이란 표현은 '삶이 풍요롭고 나아짐'라는 뜻이라고 합니다. 일본 경제학 용어가 그대로 한국에 적용된 것이라고 합니다.

<br/>

후생 경제학은 크게 두가지 질문을 중심으로 돌아갑니다.

- 효율성
  - 현재의 자원 배분 상태가 누구도 효율을 높일 수 없는 상태에 도달했는가?
  - = **파레토 안정** 상태에 도달 했는가?
- 형평성
  - 자원이 공정하게 분배 되고 있는가?

이전 포스트에서 살펴본 "**[경쟁 균형(Competitive Equilibrium)](/2025/05/15/competitive-equilibrium/)**"도 후생경제학의 핵심적인 한 부분 입니다.

# Pareto Stability

"파레토 안정"은 현재 도달한 균형 상태에서 벗어날 자발적 유인이 없는 안정적인 상태 입니다.

"파레토 효율(Efficient)", "파레토 최적(Optimal)" 모두 같은 표현이자 상태이지만, 맥락에 따라 다르게 사용하는 용어 입니다. 여기서는 "파레토 안정"으로 통일해 적겠습니다!

## Example

두 명의 사람과 두 종류의 재화가 있습니다. 각 사람의 초기 재화는

$$
\begin{aligned}
w_a &= (4, 8) \\
w_b &= (7, 3)
\end{aligned}
$$

이고, 각 사람의 효용 함수는

$$
\begin{aligned}
u_a(x) &= \min (x_1, x_2) \\
u_b(x) &= x_1^{1/2} \cdot x_2^{1/2}
\end{aligned}
$$

이런 상황에서 "파레토 안정"을 찾아봅시다! 아직 어떻게 접근해야 하는지는 다루지 않았으니 여기서는 문제만 제시하고 넘어가겠습니다!

# Edgeworth Box

파레토 안정에 대한 문제를 시각적인 접근으로 푸는 방법 입니다!

예제에서 다룬 문제 상황을 그려보면 아래와 같습니다.

![](/images/mathematics/micro-economics/egdeworth-box-yt.png){: .fill .align-center style="width: 100%" }
[Understanding the Edgeworth Box: Step-by-Step Example Problem Explained](https://youtu.be/QAHEdAW9PlI?si=Octu8SSKAJdrx7G9)
{: .small .gray .text-center }

모서리에 각 사람을 배치합니다. 그리고 초기 재화를 바탕으로 두 사람의 효용 곡선을 그립니다.

이때, 두 무차별 곡선이 겹치는 영역이 있는데 이것은 현재의 초기 배분에서 출발해, "**양쪽 소비자 모두 더 나아질 수 있는 배분**"이 있는 영역 입니다! 이것을 "**파레토 개선 가능 영역(Region of Pareto Improvements)**"라고 합니다.

캡처에 있는 주황색의 초기 배분 상태 $w$가 해당 영역에 있는 점으로 이동하면, 그 누구도 손해 보지 않고, 적어도 한명은 더 좋은 효용을 얻습니다.

## Contract Curve

![](/images/mathematics/micro-economics/edgeworth-box-yt-contract-curve.png){: .fill .align-center style="width: 100%" }
[Understanding the Edgeworth Box: Step-by-Step Example Problem Explained](https://youtu.be/QAHEdAW9PlI?si=Octu8SSKAJdrx7G9)
{: .small .gray .text-center }

"계약 곡선(Contract Curve)"는 두 소비자의 무차별 곡선이 접하는 점들의 집합 입니다. 예제에서 $u_a(x) = \min(x_1, x_2)$이기 때문에, 접하는 지점은 항상 $y = x$ 직선 위에서 발생합니다.

두 소비자는 교환을 통해 각자의 효용을 더 높이려고 합니다. 그들은 수렴할 때까지 교환을 계속하는데요. 이때, 수렴 결과는 항상 Contract Curve 위의 어떤 점이 됩니다.

왜냐하면, Contract Curve는 모든 파레토 안정적인 배분이 모여 있기 때문입니다.
그 이유는 Contract Curve의 정의에서 나오는데, Contract Curve가 두 소비자의 MRS가 일치하는 지점이기 때문입니다. (사실 이 부분 제대로 이해 못 했습니다...;;)

## Core

"코어(핵)"은 Contract Curve의 "일부분" 입니다. Core는

- "**파레토 안정적**"이면서
  - Contract Curve 위에 있음.
- 각 소비자가 자신의 초기 배분보다 낫다고 느낌

조건을 만족하는 점들의 집합 입니다. 위의 그림에서는 초록색으로 그린 선분이 코어에 해당 합니다!

# Solution

이제 주어진 문제의 답을 구해봅시다!

$u_a(x)$는 완전 보완재이기 때문에, 효용 극대화는 항상 $x_1 = x_2$인 지점에서 발생합니다.
이것은 $x_1 = x_2 = t$라고 둡니다. 그리고 $t$는 초기 자원량에 따라 $0 \le t \le 11$의 범위를 갖습니다.

$a$의 초기 효용은 $u_{a}(x_0) = \min(4, 7) = 4$ 였습니다. 그래서 거래 후 효용은 최소한 4보다는 커야 합니다. 따라서, 범위가 아래와 같이 갱신 됩니다.

$$
4 \le t \le 11
$$

이제, $u_b(x)$에 대해서 살펴봅시다. B 사람의 효용 함수는

$$
u_b(x_t) = \sqrt{(11 - t)(11 - t)} = 11 - t
$$

가 됩니다. 이때, $(11-t)(11-t)$가 되는 이유는 두 재화의 자원값이 둘다 $11$이기 때문입니다.

B 사람은 거래후 효용이 처음의 효용 $u_b(x_0) = \sqrt{7 \cdot 3} = \sqrt{21}$보다는 크기를 바랍니다. 따라서,

$$
\sqrt{21} \le 11 - t \quad \rightarrow t \le 11 - \sqrt{21} \approx 6.41
$$

이제 두 구간 범위를 교집합하여 가능한 Core 범위를 구하면,

$$
\text{Core}: 4 \le t \le 11 - \sqrt{21} \approx 6.41
$$

이론적으로 Core에 속하는 모든 점이 가능하기 때문에, 해가 범위 형태로 계산 될 수도 있습니다!

# 맺음말

Edgeworth Box로 파레토 안정 문제를 접근하는 방법과 예제를 통해 그 방법을 살펴보았습니다!

Edgeworth Box는 시각적으로 해가 대략 어떻게 만들어질지 가늠하는 도구이기 때문에, 실제 파레토 안정이 이뤄지는 해를 구하려면 몇가지 수학적 계산을 좀 해줘야 합니다.


# References

- [Understanding the Edgeworth Box: Step-by-Step Example Problem Explained](https://youtu.be/QAHEdAW9PlI?si=h5vDLCdoT03ksv4C)
