---
title: "2nd order Non-Homogeneous Linear ODE"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "r(x) 항이 있는 2차 미분방정식을 풀어서 ODE의 해를 구하는 방법."
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

앞선 포스트에서 2nd order Homogeneous linear ODE의 해를 어떻게 구해야 하는지는 살펴봤었다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = 0
$$

</div>

- $\lambda$에 대한 2차 방정식 풀기 (for constant coefficient)
- $y_1$를 구하고 Reduction of Order Method 사용해서 $y_2$ 유도하기

어찌저찌 $y_1$, $y_2$를 구했다고 해보자.

<br/>

그러나 세상의 모든 2nd order linear ODE가 homogeneous일리는 없다. 분명 $r(x) \ne 0$인 경우가 있어서 non-homogeneous linear ODE를 풀어야 할 수도 있을 것이다.

# Non-homogeneous linear ODE의 경우

암튼 이번 포스트에서는 non-homogeneous linear ODE를 푸는 방법을 살펴볼 것이다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = r(x)
$$

</div>

non-homogeneous의 경우는 해가 아래와 같이 구성된다고 한다.

<div class="definition" markdown="1">

$$
y = y_h + y_p
$$

</div>

위의 식에서 $y_h$는 원래의 non-homo ODE에 대응하는 $r(x) = 0$인 homogeneous linear ODE에서의 general solution이고, $y_p$는 non-homo. ODE를 만족하는 어떤 구체적인 함수이다.

암튼 핵심은 "non-homo ODE의 일반해"가 "homo ODE의 일반해" + "non-homo ODE의 특정해"로 구성된다는 것이다.


# How to solve non-homogeneous linear ODE

## Method of undetermined coefficients

## Method of variation of parameters




