---
title: "Vibration of water in a tube"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "튜브 속 진동하는 물기둥에 대해 🤿"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

"Kreyszig - Advanced Engineering Mathematics(10th ed.)"에 재밌는 문제가 있어서 블로그 포스트로 슬쩍 메모해본다.

# Vibration from Buoyancy Force

![](/images/mathematics/differential-equations/vibration-from-buoyancy-force.png){: style="max-height: 320px" .align-center }
Kreyszig - Advanced Engineering Mathematics(10th ed.)
{: .align-caption .text-center .small .gray }

잔잔한 물 위에 어떤 막대 하나를 y축에 맞춰 담군다고 생각하자. 막대가 물에 들어가면 들어간 만큼 부력을 받는다.

$$
F_B = - \rho g V = - \rho g A y
$$

편의상 막대가 넓이 $A$와 높이 $h$의 원기둥이나 다각기둥의 형태라고 가정하자.

부력은 물체의 운동방향의 반대로 작용하므로 마이너스 부호를 갖는다. 이것을 바탕으로 운동방정식을 세우면 아래와 같다.

$$
F = m a = m y'' = - \rho g A y
$$

식을 조금 다듬으면

$$
y'' + \frac{\rho g A}{m} y = 0
$$

아주 간단한 2nd-order linear ODE가 되었다!! 이걸 풀면 아래와 같이 주기를 갖는 해를 얻게 된다.

$$
y(t) = c_1 \cos wt + c_2 \sin wt
$$

이때, $w = \sqrt{\frac{gpA}{m}}$이다.

<br/>

Kreyszig 교재에서는 원기둥의 지름이 60cm, 진동 주기가 2초로 제시되었다. 이에 따라, 물체의 역산하면...

$$
m = \frac{\rho g A}{w^2} = \frac{1 \cdot 9.8 \cdot 30^2 \cdot 3.14}{2^2} = 6923.7 \, \texttt{gram}
$$

# Vibration of water in an U-shaped tube

이번에는 튜브 안에서 물기둥이 진동하는 케이스이다. 처음에는 이게 진짜 진동하는지 와닿지 않아서 유튜브에서 영상도 찾아봤다 ㅋㅋ

<iframe width="560" height="315" src="https://www.youtube.com/embed/ZI0JWupCqes?si=OHcLHxC6GCMkEmq5&amp;start=69" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

<br/>

이번 경우에는 물기둥이 진동한다. 올라간 물기둥은 그 높이 만큼 중력 $F_g$를 받는다.

$$
F_g = - m g = - A h \cdot g
$$

이때, 중력은 운동방향의 반대로 작용하므로 마이너스 부호를 붙인다.


그런데 이때, 물기둥이 올라간 높이는 물이 평형상태를 기준으로 재는 것이 아니라, 반대편 기둥의 높이를 기준으로 더 추가된 물기둥의 무게 만큼 중력이 작용한다.


![](/images/mathematics/differential-equations/vibration-of-water-in-an-u-shaped-tube.png){: style="max-height: 280px" .align-center }
Kreyszig - Advanced Engineering Mathematics(10th ed.)
{: .align-caption .text-center .small .gray }

위의 그림을 보면 물기둥이 진동하지 않는 평형 위치 $y = 0$를 표시했지만, 오른쪽 기둥에서 $y$ 만큼 위로 상승하면, 왼쪽 기둥에서 물이 $y$ 만큼 하강한다. 따라서, 더 쌓여진 물기둥의 양은 $2y$로 계산되어야 한다. 이를 바탕으로 중력에 대한 식을 다시 쓰면

$$
F_g = - A h \cdot g = - A \cdot 2y \cdot g
$$

이를 바탕으로 운동방정식을 세우면 아래와 같다. 이때, 아래 식의 $m$은 튜브관에 있는 물 전체의 무게다.

$$
F = m y'' = - 2 A y g
$$

그리고 이를 정리하여 2nd-order linear ODE로 만들면

$$
y'' + \frac{2Ag}{m} y = 0
$$

마찬가지로 주기성을 갖는 해를 가지며, 주기는 아래와 같다.

$$
w = \sqrt{\frac{2Ag}{m}}
$$

<br/>

문제에서는 물이 1L, U관의 지름이 2cm로 주어졌다. 이를 바탕으로 주기를 구하면 아래와 같다.

$$
w = \sqrt{\frac{2 \cdot 1^2 \cdot 3.14 \cdot 9.8}{1000}} = 0.07 \, \texttt{sec}
$$

# 맺음말

요런 공학 문제들은 "Fluid Mechanics" 분야의 문제라고 한다. 기계공학과 갔으면 요런 것들과 씨름 했겠지...!? ㅋㅋ
