---
title: "Exact Differential Equation"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "Total Differential과 Implicit Equation의 관계에 대해 🌀"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# Total Differential

미적분학에서 배웠던 "Total Differential"이 기억이 나는가? 기억 안 나도 괜찮다 아주 직관적이니까! Total Differential은 2차원 음함수 $f(x, y) = c$에 대해 아래와 같이 정의한 편미분 방정식이다.

<div class="definition" markdown="1">

$$
df = f_x \, dx + f_y \, dy = 0
$$

</div>

Total Differential은 2차원 함수가 $x$와 $y$ 축에 대한 방향 편미분과 모든 방향에서의 미분이 가능한지를 가늠하는 기준이다.

<br/>

예를 들어, $f(x, y) = x + x^2 y = c$라는 음함수를 생각해보자. 요것의 Total Differential을 구해보면

$$
df = (1 + 2xy) \, dx + x^2 \, dy = 0
$$

이 된다. Differential의 꼴로 보면 익숙하지 않을 수도 있는데, 요걸 미분방정식의 꼴로 표현하면 아래와 같다.

$$
2xy + x^2 \frac{dy}{dx} = 0
$$

미분텀 $y'$이 생겼다!! 즉, 어떤 음함수의 Total Differential은 ODE를 유도한다.

# Exact ODE

자! 여기에서 지금까지 이어온 흐름을 "반대로" 생각 해보자. 만약 주어진 ODE가 어떤 음함수 $f(x, y)$의 Total Differential이란 걸 안다면, 원본 음함수를 쉽게 찾을 수 있을까?? 🧐 정답은 Yes!

아래와 같은 형태의 ODE를 생각해보자.

$$
M(x, y) \, dx + N(x, y) \, dy = 0
$$

단, 위와 같은 형태라고 **모두 Total Differential가 되는 건 아니다!** Total Differential가 되려면 $M_y = N_x$ 조건을 만족해야 한다. 이유는 Total Differential인 경우, $f_{xy} = f_{yx}$가 성립하기 때문이다.

만약 주어진 ODE가 Total Differential이라는 것을 안다면, 원본 함수를 역으로 추정할 수 있다. 먼저 $f_x = M(x, y)$임을 활용해 원본 함수를 유도한다.

$$
f(x, y) = \int M(x, y) \, dx + g(y)
$$

그리고 위의 식을 $y$에 대해서 편미분 하여 $g(y)$를 찾는다.

$$
\frac{\partial}{\partial y} \left(\int M(x, y) \, dx + g(y)\right)
= N(x, y)
$$

적분과 미분이 섞여서 식은 좀 복잡해보이지만, 실제 식으로 접해보면 괜찮을 것이다 ㅎㅎ 시작할 떄 봤던 음함수 $f(x, y)$의 "Total Differential"로 요걸 구해보면,

<div class="proof" markdown="1">

$$
M(x, y) = 1 + 2xy \rightarrow f(x, y) = x + x^2y + g(y)
$$

그리고 $y$에 대해 편미분 하면,

$$
x^2 + g'(y) = x^2
$$

따라서, $g'(y) = 0$이고, $g(y) = C$이다. 따라서, 원본 함수 $f(x, y)$는

$$
f(x, y) = x + x^2 y = C
$$

</div>

요런 $M(x, y) \, dx + N(x, y) \, dy = 0$의 꼴의 ODE를 "**Exact ODE**"라고 부른다.

# Integrating Factor

세상에 "Exact ODE"만 있진 않는 법. 편미분 테스트를 했는데, $M_y \ne N_x$인 경우도 있다. 예를 들어, 아래의 ODE는 Exact가 아니다.

$$
-y \, dx + x \, dy = 0
$$

그런데, 요 ODE의 양변에 $1/x^2$를 곱해주면, 놀랍게도 Exact ODE가 된다!!

$$
- \frac{y}{x^2} \, dx + \frac{1}{x} \, dy = 0
$$

이렇듯 $M(x, y) \, dx + N(x, y) \, dy = 0$ 꼴의 ODE는 어떤 함수 $F(x, y)$를 곱해서 다시 "Exact ODE"로 만들 수 있다. 이 함수 $F$를 "**Integrating Factor**"라고 한다.

단, Int. Factor $F(x, y)$는 유일하게 존재하진 않는다. 위의 non-exact ODE에 $1/y^2$를 곱했어도 Exact ODE가 된다.

$$
- \frac{1}{y} \, dx + \frac{x}{y^2} \, dy = 0
$$

<br/>

## How to Find Integrating Factor?

일단 어떤 Int. Factor $F$를 찾았다고 가정하고, 요걸 non-exact ODE에 적용해보자.

$$
FM \, dx + FN \, dy = 0
$$

Exact ODE가 되었으니, 자연스럽게 아래의 Exact Test에 대한 식이 성립한다.

$$
F_y M + F M_y = F_x N + F N_x
$$

앞에서 말하길 Int. Factor는 유일하게 결정되지 않는다. 따라서 우리가 원하는대로 Int. Factor를 잡을 수 있는데, 이번에는 Int. Factor가 오직 $x$에만 의존하는 함수라고 가정해보자: "Let $F(x, y) = F(x)$".

그러면, $F_y = 0$이 되므로, 위의 Exact Test에 대한 식을 아래와 같이 쓸 수 있다.

$$
F M_y = F_x N + F N_x
$$

이걸 $F$와 $F_x$에 대한 식으로 정리하면...

$$
\begin{aligned}
F_x N &= F (M_y - N_x) \\
\frac{F_x}{F} &= \frac{1}{N} (M_y - N_x) \\
\ln F &= \int \left( \frac{1}{N} (M_y - N_x) \right) \, dx \\
F &= \exp \left[ \int \left( \frac{1}{N} (M_y - N_x) \right) dx \right]
\end{aligned}
$$

암튼 요 $F(x, y)$를 non-exact ODE에 곱해주면, exact ODE가 되고, 그런 ODE는 쉽게 해결할 수 있다 ㅎㅎ


# 맺음말

이어지는 포스트에서는 "Homogeneous ODE"에 대해서 다룬다. 요것도 Exactness처럼 Homogeneous와 non-homogeneous가 있는데, Integrating Factor를 사용해 non-homo.를 homo. ODE로 변환하여 문제를 풀 수 있다.

non-homogeneous ODE의 꼴은 아래와 같은데

$$
y' + p(x) y = r(x)
$$

Total Differential의 꼴로 바꿔서 보면...

$$
\left( p(x) y - r(x) \right) \, dx + 1 \, dy = 0
$$

요렇게 생겼는데 $f_y = N(x, y) = 1$이기 때문에, Int. Factor $F(x, y)$를 구하는 식이 더 간단해진다. 그래서 문제를 풀게 된다면, Exact ODE에서의 Int. Factor의 공식을 외우는 것 보다 Homo. ODE에서의 Int. Factor의 공식을 외우는게 더 간단하다.

👉 [Homogeneous Linear ODE](/2024/09/11/homogeneous-linear-ODE/)
