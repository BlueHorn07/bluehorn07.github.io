---
title: "Confidence Interval, and Parameter"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Statistics"]
excerpt: "구간 추정으로 얻는 Confidence Interval을 어떻게 해석해야 할까?"
---

통계학을 공부하면서 들었던 의문과 생각들을 에세이로 적어보았습니다 🙏 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다🎲
{: .notice--info}

# 들어가며

통계를 공부하면서 Confidence Interval과 모수(parameter) 사이의 관계가 늘 헷갈렸다. 이번 포스트에서는 그걸 어떻게 해석해야 하는지 짧게 정리 하였다.

# How to Interpret the given Confidence Interval

![](/images/mathematics/probability-and-statistics/confidence-interval.png){: .align-center}

95% 신뢰구간이 $[a, b]$라고 주어졌다고 합시다.

그럼 [1]과 [2]번 해석에 대해 살펴봅시다.

<div class="notice" markdown="1">

[1] 주어진 신뢰구간이 모평균 $\mu$를 포함할 확률이 95%다. (O/X)

[2] 신뢰구간을 반복해서 만들었을 때, 그 구간들 중에서 모평균 $\mu$를 포함할 확률이 95%다. (O/X)

</div>

정답은 [1]번 해석은 틀렸고, [2]번이 올바른 해석 입니다.

하나의 신뢰구간은 주어진 **샘플**(실험)에 기반한 추정일 뿐이고, 해당 실험에서의 불확실성이 포함되어 있습니다. 그래서 이것만 가지고 모평균 $\mu$을 추정하거나 추측하는 것은 위험합니다.

신뢰구간의 개념 자체가 "같은 실험을 반복해서 여러 신뢰구간을 얻었을 때, 그 중 일정 비율(ex: 95%)는 모평균을 포함할 것'라는 반복 실험을 기반으로 하고 있습니다.
따라서, 신뢰구간의 의미를 경험적으로 이해하려면 동일 실험을 여러 번 수행해 나온 신뢰구간들을 비교하는 것이 필요합니다.

## What CI should we choose?

실험을 여러 번 해서 몇 개의 CI를 얻었다면, 그중에서 어떤 걸 선택해야 하는가?

신뢰구간은 그 실험을 대변하는 대푯값 입니다. 그래서 어떤 걸 선택할 수 없습니다. 그리고 사실 실험을 여러 번 수행하는게 꼭 필요한 것도 아닙니다.
한번의 실험에서 얻은 하나의 CI를 사용해도 아무 문제 없고, 여러번 실험 했다면 그중에서 하나의 CI를 선택해서 사용해도 됩니다.

## But, I want to choose one CI!

그럼에도 불구하고 하나의 CI를 골라야 하겠다면?

실험 세팅이 완전히 동일했다면, 어떤 CI를 골라도 전혀 상관 없습니다!
그러나 실험마다 세팅이 달랐다면, 데이터 품질과 표본 수를 기준으로 가장 좋은 것에서 나온 CI를 선택하는 것이 좋습니다.
