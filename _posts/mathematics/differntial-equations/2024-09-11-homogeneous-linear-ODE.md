---
title: "Homogeneous Linear ODE"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "Homogeneous Linear ODE를 Exact ODE로 해석하는 방법에 대해... 🔦"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# Linear ODE

<div class="definition" markdown="1">

$$
y' + p(x) y = r(x)
$$

ODE에서 $y$의 최대 차수가 1인 ODE를 Linear ODE라고 한다.

</div>

이떄, $r(x)$ 텀이 0이라면, "Homogeneous Linear ODE"라고 하고, 이 경우는 아주 쉽게 ODE를 풀 수 있다.

# Homogeneous Linear ODE

<div class="definition" markdown="1">

$$
y' + p(x) y = 0
$$

</div>

2가지 방식으로 풀 수 있다.

## Separable Equation

식을 아래와 같이 정리한다.

$$
\frac{y'}{y} = - p(x)
$$

그리고 적분하면...

$$
\ln y = - \int p(x) dx + C
$$

다시 정리하면

$$
y = C \cdot \exp \left( - \int p(x) \, dx \right)
$$

## Integrating Factor

일단 Homogeneous Linear ODE도 "Total Differential"의 꼴로 볼 수 있다.

$$
\left[ p(x) y \right] \cdot dx + 1 \cdot dy = 0
$$

Exactness Test를 해보면, $M_y = N_x$를 만족하지 않는다.

$$
M_y = p(x) \ne 0 = N_x
$$

그래서 Integrating Factor $F(x, y)$를 찾아 적용해야 하는데, Homogeneous Linear ODE의 Int. Factor는 아래와 같이 공식으로 존재한다.

<div class="definition" markdown="1">

[Homogeneous Linear ODE의 Integrating Factor]

$$
F(x) = \exp \left( \int p(x) \, dx \right)
$$

</div>

눈여겨 볼 점은 본래 Integrating Factor는 $F(x)$도 가능하고, $F(y)$도 가능하다. 그런데, Homogeneous Linear ODE에서는 오직 $F(x)$만 생각하면 된다.

사실 Integrating Factor의 공식에 맞춰 유도해도 되지만... 대충 형태만 기억해두면 나중에 non-homogeneous Linear ODE를 풀 때도 그대로 사용하면 된다 ㅎㅎ 🙂

<br/>

암튼 이렇게 하고 non-exact ODE를 exact ODE로 변환해 풀어보자.

$$
\left(F(x) \cdot p(x) y \right) \cdot dx + F(x) \cdot dy = 0
$$

이때, Integrating Factor $F(x)$가 아래의 식을 만족한다. **이 부분, 중요하다.**

$$
F'(x) = p(x) \cdot \exp \left( \int p(x) \, dx \right) = p(x) F(x)
$$

이걸 exact ODE로 변환된 식에 적용하면

$$
F' y \cdot dx + F(x) dy = F'y + Fy' = (Fy)' = 0
$$

훨씬 풀기 쉬운 $Fy$에 대한 ODE가 되었다 ㅎㅎ 이제는 기계적으로 풀기만 하면 된다.

$$
\begin{aligned}
(Fy)' &= 0 \\
Fy &= C \\
y &= C / F \\
y &= C \cdot \exp \left( - \int p(x) \, dx \right)
\end{aligned}
$$

<br/>

처음에 풀었던 Separable Equation 방식과 동일한 결과를 얻었다 ㅎㅎ 풀이 자체는 Separable Equation을 쓰는게 훨씬 쉽기 때문에, Homogeneous Linear ODE라는 걸 알았다면, Separable Equation으로 푸는게 제일 쉽다! 그러나...

# Non-homogeneous Linear ODE

모든 Linear ODE가 $r(x) = 0$인 Homo. Linear ODE는 아니다.

<div class="definition" markdown="1">

$$
y' + p(x) y = r(x)
$$

</div>

위의 ODE를 Total Differential로 표현하면 아래와 같은데, exact ODE가 될 수 없다. 

$$
\left[ p(x)y - r(x) \right] \cdot dx + 1 \cdot dy = 0
$$

요기도 $N_x = 0$이기 때문에, Exactness Test를 통과하지 못한다. 따라서, **이 문제를 풀기 위해선 Integrating Factor를 도입해야 한다.**


## Integrating Factor

Integrating Factor를 구해보면, non-homo.의 경우도 같은 Integrating Factor를 갖는 걸 발견할 수 있다.

<div class="definition" markdown="1">

[**Linear** ODE의 Integrating Factor]

$$
F(x) = \exp \left( \int p(x) \, dx \right)
$$

Homo.인 경우, non-homo.인 경우 상관 없음!!

</div>

이제 Int. Factor를 non-homo. Linear ODE에 적용해서 exact ODE로 바꿔보자.

$$
F (py - r) \cdot dx + F \cdot dy = 0
$$

이떄, $F' = p F$이므로...

$$
(F'y - Fr) \cdot dx + F \cdot dy = 0
$$

그리고 식을 정리해 $Fy$에 대한 ODE로 바꾸면...

$$
Fy' + Fy' = Fr = (Fy)'
$$

위의 ODE를 풀면...

$$
\begin{aligned}
(Fy)' &= Fr \\
Fy &= \int Fr \, dx + C \\
y &= F^{-1} \cdot \left( \int Fr \, dx + C \right)
\end{aligned}
$$

<br/>

$y$를 찾았으니 기존의 non-homo. linear ODE는 풀었다!! 식을 간소화 하기 위해 $h = \int p(x) \, dx$로 두고, 식을 다시 쓰면 아래와 같다.

$$
y = e^{-h} \left( \int r e^h dx + C \right)
$$

# 맺음말

미분방정식의 첫번째 챕터인데, 벌써 이해가 안 되는 부분이 있어서 포스트로 정리 해보았다... 강의에서 왜 Linear ODE를 풀 때, Integrating Factor가 필요한지 설명하는 부분 없이 바로 "*Integrating Factor를 적으면 요래요^^*"라던가, "*Linear ODE의 솔루션은 요래요^^ $r(x)$이 없으면 homogeneous ODE의 솔루션이랑 같아요^^*" 요렇게 결과로 바로 넘어갔던 느낌이 있는 것 같다.
