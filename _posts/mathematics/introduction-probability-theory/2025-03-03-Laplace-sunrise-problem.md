---
title: "Laplace Sunrise Problem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
excerpt: "내일 해가 뜰 확률을 베이지안으로 구하기 🌞"
---

2025년 마지막 학기 수업인 "확률개론(MATH431)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Introduction to Probability Theory](/categories/introduction-probability-theory)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# Sunrise Problem

> 내일 해가 뜰 확률은 얼마인가?

가장 먼저, 확률 시행에 대해 아래와 같이 가정합니다.

- 태양이 뜨는 건 독립적인 베르누이 시행이다.
- 태양이 뜰지 말지 $n$일 동안 관측해쏙, 모든 날에 태양이 떴다.


해가 뜰 확률 $p$를 unknown parameter로 둡니다. 이때, 그냥 unknown으로 두는게 아니라 "확률 변수"라고 두는데 아래와 같이 Uniform 분포를 따른다고 설정 합니다.

$$
P \sim \text{Unif}(0, 1)
$$

그러면, 랜덤 변수의 $P$의 pdf는 $f(p) = 1$이 됩니다.

위의 Uniform 분포는 "사전 분포(prior)" 입니다. 만약 관측된 데이터가 있다면, 그것을 이용해 $p$에 대한 "사후 분포(posterior)"를 계산해야 합니다.

태양이 $n$일 모두 떴다는 것은 아래와 같은 "우도(Likelikhood)"를 따른다는 것을 말합니다.

$$
L(p) = p^n
$$

우도는 관측 데이터에 대한 확률 입니다. 그래서 $P(\text{data} \, \vert \, p)$라고도 합니다.

이제 "사후 분포(posterior)"를 구해봅시다. 여기에선 베이스 정리를 사용합니다.

$$
f(p \, \vert \, \text{data}) = \frac{f(\text{data} \, \vert \, p) \cdot f(p)}{f(\text{data})}
$$

이때, 분모의 $f(\text{data})$는 "evidence"라고 하는데, 직접 구하지 않고 마지막에 정규화 과정에서 처리 해줍니다. 그래서 일단 이걸 제외하고 아래와 같이 설정하고 식을 전개 합니다.

$$
\begin{aligned}
f(p \, \vert \, \text{data})
&\propto
f(\text{data} \, \vert \, p) \cdot f(p) \\
&\propto
p^n \cdot 1
\end{aligned}
$$

아직은 $f(p \, \vert \, \text{data})$를 제대로 구한 것은 아닙니다. 그래서 정규화를 해줘야 하는데, 이것은

$$
\int_0^1 f(p \, \vert \, \text{data}) \, dp = 1
$$

라는 pdf의 조건을 사용해 구하면 됩니다. 따라서,

$$
\int_0^1 f(p \, \vert \, \text{data})
=
\frac{\int_0^1 p^n \, dp}{f(\text{data})} = 1
$$

따라서, evidence $f(\text{data})$의 확률은

$$
f(\text{data}) = \int_0^1 p^n \, dp
= \frac{1}{n+1}
$$

이 됩니다. 최종적으로 사후 분포 $f(p \, \vert \, \text{data})$를 구해봅시다.

$$
f(p \, \vert \, \text{data})
= \frac{p^n}{\int_0^1 p^n \, dp} = (n+1) \cdot p^n
$$

본래 우리의 목표는 "내일 해가 뜰 사건"을 구하는 것입니다. 이것은

$$
P(\text{Sun rise tomorrow} \, \vert \, \text{data})
$$

사후 분포를 구했지만, 그건 여전히 고정된 값이 아니고 확률 변수 입니다! 그래서 아래와 같이 평균을 구해서 사용해야 합니다.

$$
\begin{aligned}
P(\text{Sun rise tomorrow} \, \vert \, \text{data})
&= E_p[P(\text{Sun rise tomorrow} \, \vert \, p)] \\
&= \int_0^1 P(\text{Sun rise tomorrow} \, \vert \, p) \cdot f(p \, \vert \, \text{data}) \, dp \\
&= \int_0^1 p \cdot f(p \, \vert \, \text{data}) \, dp
\end{aligned}
$$

마지막의 적분식은 정확히 사후분포 $f(p \, \vert \, \text{data})$에 대한 기댓값과 같습니다!

$$
E[p \, \vert \, \text{data}]
= \int_0^1 p \cdot f(p \, \vert \, \text{data}) \, dp
$$

그리고 이걸 계산하면 "내일 해가 뜰 확률"은 아래와 같습니다.

$$
P(\text{Sun rise tomorrow} \, \vert \, \text{data})
= \frac{n+1}{n+2}
$$

# 맺음말

결론만 보면, 정말 당연한 것 같은데 "베이지안"의 관점으로 그 과정을 유도하는게 처음 들을 때 좀 생소 했습니다.

마지막에 "내일 해가 뜰 확률"을 구하는 것도 사후 분포의 "평균"으로 구하는데, 결국 실제 확률 $p$를 모르기 때문에 기댓값으로 구하는 거라고 받아들이는데 시간이 좀 걸렸던 것 같아요.

<br/>

이 문제를 단순히 "내일 해가 뜨는 걸 구했네 ㅎㅎ"라고 생각하기 보다는, 이렇게도 생활에 적용 해볼 수 있습니다.

농부가 씨앗을 심을 때는 그 씨앗이 확률적으로 발아 합니다. 그 확률이 $p$라고 했을 때, 8번 씨앗을 심었을 때, 6번 발아 했습니다. 이제 9번째에 심는 씨앗이 발아할지 "예측" 해야 합니다. 이것을 구하는 과정도 라플라스의 베이지안과 동일한 과정 입니다!

참고로 요건 이 문제를 일반화한 라플라스의 ["Rule of Succession](https://en.wikipedia.org/wiki/Rule_of_succession)"에서 다룹니다!



# Reference

- [Wikipedia: Sunrise Problem](https://en.wikipedia.org/wiki/Sunrise_problem)