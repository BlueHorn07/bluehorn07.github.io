---
title: "2nd order Homogeneous Linear ODE"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

아래와 같이 생긴 2nd order homogenous linear ODE의 해를 구하는 일반적인 방법에 대해 다룬다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = 0
$$

</div>

## with constant coefficients

$$
y'' + a y' + b y = 0
$$

2nd order homogenous linear ODE인데, 만약 계수 $p(x) = a$, $q(x) = b$로 상수인 경우는 해의 형태를 쉽게 결정하는 방법이 존재한다.

$y = e^{\lambda x}$를 대입해서 나오는 $\lambda$에 대한 2차식을 풀어서

$$
\lambda^2 + a \lambda + b = 0
$$

그것이 (1) 두 실근인지, (2) 중근인지, (3) 두 허근인지에 따라 ODE의 해를 구할 수 있다.

### 두 실근

아주 쉬운 케이스로 그냥

- $y_1(x) = e^{\lambda_1 x}$
- $y_2(x) = e^{\lambda_2 x}$

로 결정된다.

### 중근

이 경우가 조금 복잡한데, 일단 중근 $\lambda = - a / 2$를 해로 갖는 solution $y_1(x) = e^{- a x / 2}$를 구한다.

그리고 요 $y_1(x)$에 $x$를 곱해서 $y_2(x)$를 구하면, 그게 2번째 basis가 된다.

$$
y_2(x) = x \cdot y_1(x) = x e^{- a x/ 2}
$$

실제로 그런지 체크 해보면...


<div class="definition" markdown="1">

$$
\begin{aligned}
y_2'
&= 1 \cdot e^{\lambda x} + x \cdot \lambda \cdot e^{\lambda x} \\
&= \left(1 + \lambda x \right) \cdot e^{\lambda x} \\
\end{aligned}
$$

$$
\begin{aligned}
y_2''
&= \lambda \cdot e^{\lambda x} + (1 + \lambda x) \lambda \cdot e^{\lambda x} \\
&= \left( \lambda^2 x + 2 \lambda \right) \cdot e^{\lambda x}
\end{aligned}
$$

계수 $a$, $b$를 $\lambda$ 기준으로 다시 작성하고 식에 대입해보면...

- $a = - 2 \lambda$
- $b = a^2 / 4 = \lambda^2$

$$
\begin{aligned}
\left( \lambda^2 x + 2 \lambda \right) \cdot \cancel{e^{\lambda x}} - 2 \lambda \cdot \left(1 + \lambda x \right) \cdot \cancel{e^{\lambda x}} + \lambda^2 \cdot x \cdot \cancel{e^{\lambda x}} &= 0 \\
\left( \lambda^2 x + 2 \lambda \right) - 2 \lambda \cdot \left(1 + \lambda x \right) + \lambda^2 x &= 0 \\
\cancelto{0}{\left( \lambda^2 - 2 \lambda^2 + \lambda^2 \right)} \cdot x + \cancelto{0}{\left( 2 \lambda - 2 \lambda \right)} &= 0
\end{aligned}
$$

따라서, 식이 성립하므로 $x e^{\lambda x}$는 ODE의 basis이다. $\blacksquare$

</div>

첫번째 basis $y_1$에 $x$를 곱하면 두번째 basis $y_2 = x \cdot y_1$를 구하는 과정이 뭔가 "뿅!"하고 튀어나온 것 같지만 그렇지 않다. "Reduction of Order"라는 방법을 적용해서 구한 것이고, 구체적인 방법은 $y'' + p(x) y' + y = 0$ ODE를 푸는 방법을 다룰 때 자세히 볼 것이다.

### 두 허근

허수 $i$에 대한 개념이 들어간다.


# General case: Reduction of Order Method


