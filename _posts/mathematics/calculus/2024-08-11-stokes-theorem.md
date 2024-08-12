---
title: "Stokes theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---


복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# Stokes' Theorem

![](/images/mathematics/calculus-2/stokes-theorem.png){: .align-center style="max-height: 260px;" }
[CLP Calculus Textbook](https://personal.math.ubc.ca/~CLP/CLP4/)
{: .align-caption .text-center .small .gray }

그린 정리는 "2차원 평면 $S_1$"에서의 이중 적분이, 평면의 경계를 이루는 곡선에 대한 선적분과 관련 있다고 얘기 했다.

스토크스 정리는 "3차원 곡면 $S_2$"에서의 이중 적분이, 곡면의 경계를 이루는 곡선에 대한 선적분과 관련 있다고 얘기한다.

즉, 그린 정리와 스토스크 정리 둘다 평면/곡면에 대한 적분을 경계 곡선에 대한 선적분으로 해석할 수 있다는 것으로 말한다.

<div class="definition" markdown="1">

[스토크스 정리]

$$
\iint_{S_2} \text{curl } \mathbf{F} \cdot d\mathbf{S} = \int_{C} \mathbf{F} \cdot d \mathbf{r}
$$

</div>

이때, 곡선 $C$를 경계 곡선으로 갖는 곡면/평면은 여러 개가 있을 수 있는데, 스토스크 정리에 따라 그들의 curl 적분값 모두 같은 값을 가지게 된다.
<div class="definition" markdown="1">

[by 스토크스 정리]

$$
\iint_{S_2} \text{curl } \mathbf{F} \cdot d\mathbf{S} = \iint_{S_1} \text{curl } \mathbf{F} \cdot d\mathbf{S}
$$

due to having save boundary curve $C$.

</div>

그래서 적분이 어렵다면, 같은 경계를 갖는 더 쉬운 곡면으로 바꾸어 곡면에 대한 curl 적분을 수행할 수도 있다고 한다.

# 닫힌 곡면에 대해


![](/images/mathematics/calculus-2/stokes-theorem-closed-curve.png){: .align-center style="max-height: 260px;" }
[CLP Calculus Textbook](https://personal.math.ubc.ca/~CLP/CLP4/)
{: .align-caption .text-center .small .gray }

이번에는 $S_2$가 윗뚜껑이고, $S_1$이 아랫뚜껑인 닫힌 곡면에서의 곡면의 curl 적분을 생각해보자. 닫힌 곡면의 방향성에 대한 성질에 따라 아랫뚜껑 $S_1$의 방향을 아래를 향한다.

TDB...


