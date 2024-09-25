---
title: "Bernoulli Differential Equations"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "치환을 통해 해결할 수 있는 대표적인 non-linear ODE 패턴"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}


# 들어가며

베르누이 방정식은 아래와 같은 형태의 non-linear ODE이다.

<div class="definition" markdown="1">

$$
y' + p(x) y = g(x) y^a
$$

$a$ is any real number.

If $a=0$ or $a = 1$, then above equation becomes linear. We will handle the non-linear case.

</div>

미분방정식이 위와 같은 형태의 non-linear ODE라면, 베르누이가 개발한 아래의 치환법을 이용해 linear ODE로 변환하고 ODE를 풀어낼 수 있다!!

## Bernoulli Equation's Substitution

<div class="definition" markdown="1">

Let $u(x) = [y(x)]^{1 - a}$, then apply it to give ODE,

first, differentiate it.

$$
u'(x) = y' \cdot (1-a) \cdot y^{-a}
$$

then, we know $y' = g(x) y^a - p(x) y$, so

$$
u' = (1-a) y^{-a} \cdot (g(x) y^a - p(x) y)
$$

clean up right side

$$
u' = (1 - a) g(x) - (1-a)p(x) y^{1-a}
$$

in the begging, we set $u(x) = y^{1-a}$, so

$$
u'(x) = (1-a) g(x) - (1-a) p(x) u(x)
$$

Then, the above equation is linear ODE of $u(x)$

$$
u' + (1-a)p(x) u = (1-a)g(x)
$$

</div>

# 맺음말

베르누이 방정식 자체는 non-linear ODE를 linear ODE로 변환하는 치환 테크닉이다. 뭔가 이것 자체로 더 의미있는 해석이나 활용을 찾을 순 없을 것 같다.

베르누이의 치환 테크닉을 활용할 수 있는 대표적인 사례가 "Logistic Population Model"에 대한 식이다.

<div class="definition" markdown="1">

[Logistic Population Model]

$$
y' = ky(M-y)
$$

</div>

요 녀석의 경우, ODE 식에서 $y$의 최고차항이 $a=2$이기 때문에, $u = y^{-1}$로 바꾸는 치환 테크닉으로 linear ODE로 변환할 수 있다.

Logistic Population Model에 대한 자세한 내용은 아래에 포스트에 자세히 기술해두었다 🙂

👉 [Logistic Population Model](/2024/09/24/logistic-population-model/)
