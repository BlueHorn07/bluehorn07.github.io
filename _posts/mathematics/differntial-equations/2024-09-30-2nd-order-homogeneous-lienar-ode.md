---
title: "2nd order Homogeneous Linear ODE"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "2차 방정식을 풀어서 2nd order ODE의 해를 구하는 방법. Reduction of Method로 2nd order ODE를 1st order ODE로 변환하는 방법에 대해."
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

아래와 같이 생긴 2nd order homogeneous linear ODE의 해를 구하는 일반적인 방법에 대해 다룬다.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = 0
$$

</div>

## with constant coefficients

처음엔 계수 $p(x) = a$, $q(x) = b$로 상수인 경우를 먼저 살펴보자.

<div class="definition" markdown="1">

$$
y'' + a y' + b y = 0
$$

</div>

계수가 상수일 때는 ODE의 해를 결정하는 방법이 아주 쉽다!

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

첫번째 basis $y_1$에 $x$를 곱하면 두번째 basis $y_2 = x \cdot y_1$를 구하는 과정이 뭔가 "뿅!"하고 튀어나온 것 같지만 그렇지 않다. "Reduction of Order"라는 방법을 적용해서 구한 것이고, 구체적인 방법은 $y'' + p(x) y' + q(x) y = 0$ ODE를 푸는 방법을 다룰 때 자세히 볼 것이다.

### 두 허근

$\lambda$에 대한 2차 방정식을 풀었을 때, 아래와 같이 허근이 나올 수 있다.

$$
\lambda = - \frac{a}{2} \pm \frac{\sqrt{a^2 - 4b}}{2} i
$$

$e^{\lambda x}$는 오일러 공식을 사용해 아래와 같이 쓸 수 있다.

$$
\begin{aligned}
y
&= e^{\lambda x}
= e^{\left( - \frac{a}{2} \pm \frac{\sqrt{a^2 - 4b}}{2} i \right) x} \\
&= e^{- ax / 2} \cdot e^{\pm \frac{\sqrt{a^2 - 4b}}{2} i \cdot x} \\
&= e^{- ax / 2} \cdot \left( \cos \omega x \pm i \sin \omega x \right)

\end{aligned}
$$

이때, $\omega = \sqrt{a^2 - 4b} / 2$이다.

위와 같이 허근 $i$가 표함된 형태 그대로 ODE의 해라고 얘기해도 되지만, 아래와 같이 실수 부분만 남겨서 표현하는 경우가 많다.

$$
\begin{aligned}
y_1 &= \frac{1}{2} \cdot (e^{\lambda_1 x} + e^{\lambda_2 x}) = e^{- ax / 2} \cdot \cos \omega x \\
y_2 &= \frac{1}{2i} \cdot (e^{\lambda_1 x} - e^{\lambda_2 x}) = e^{- ax / 2} \cdot \sin \omega x
\end{aligned}
$$

정리하면 허근일 때는 해가 주기 $\omega$를 갖는 $\cos$, $\sin$의 주기 함수의 일차 결합의 꼴이 된다.

$$
y(t) = e^{- ax / 2} \cdot \left( C_1 \cos \omega x + C_2 \sin \omega x \right)
$$



# General Case: Reduction of Order Method

위에서는 상수 계수인 2nd order ODE를 살펴봤고, 요걸 다시 일반적인 형태의 $p(x)$, $q(x)$를 가진 ODE에선 어떻게 접근해야 하는지 살펴보자.

<div class="definition" markdown="1">

$$
y'' + p(x) y' + q(x) y = 0
$$

</div>

결론부터 말하면 "Reduction of Order"란 방법을 쓸 수 있는데 한번 살펴보자.

## Find a 1st basis

일단 ODE를 만족하는 첫번째 Solution $y_1$을 찾아야 한다.

띠용?? 아니 첫번째고 나발이고 $y_1$을 어케 찾으란 말임?? 생각할 수 있는데, 그냥 센스나 직관을 발휘해서 찾으라고 한다 ㅋㅋ 그래도 요 "Reduction or Order" 방법에 따르면 $y_1$을 찾기만 하면 $y_2$를 무조건 찾을 수 있다.

예를 들어서, 아까 위에서 봤던 상수 계수의 ODE에서 중근이 나왔던 경우를 생각해보자. 이때는 해가

- $y_1 = e^{\lambda x}$
- $y_2 = x e^{\lambda x}$

였는데, 이 경우도 첫번째 해 $y_1$는 근의 공식으로 쉽게 찾았지만, 두번째 해 $y_2$는 $y_2 = x \cdot y_1$가 된다고만 하고 넘어갔었다. 두번째 해에서 $x$가 붙게 되는 이유를 "Reduction of Order" 방식으로 유도할 수 있다.


## Substitute

암튼 $y_1$는 찾았고, $y_2$는 뭔지는 모르겠지만, 아래와 같이 설정한다.

$$
y_2 = u(x) \cdot y_1(x)
$$

그리고 본래의 2nd order ODE에 $y_2$를 대입한다. 이를 위해 $y_2'$, $y_2^{\prime\prime}$를 구한다.

$$
\begin{aligned}
y_2' &= u' y_1 + u y_1' \\
y_2'' &= u'' y_1 + u' y_1' + u' y_1' + u y_1'' = u'' y_1 + u' \cdot 2 y_1' + u y_1''
\end{aligned}
$$

요걸 식에 대입한다. 그리고 식을 $y_1$가 아니라 $u$에 대해서 정리한다.

$$
\begin{aligned}
&y'' + p(x) y' + q(x) y \\
&= \left(u'' y_1 + u' \cdot 2 y_1' + u y_1''\right) + p(x) \cdot \left(u' y_1 + u y_1'\right) + q(x) u y_1 \\
&= (y_1) \cdot u'' + (2y' + p y_1) \cdot u' + \cancel{(y_1'' + p(x) y_1' + q(x) y_1 )} u \\
&= y_1 \cdot u'' + (2y' + p y_1) \cdot u' \\
&= 0
\end{aligned}
$$

## Solve 1st order ODE

$u$에 대해 정리했더니 $(y_1'' + p(x) y_1' + q(x) y_1)$ 부분이 $0$으로 소개 되면서, $u''$, $u'$에 대한 텀만 남게 되었다!! 그리고 ODE에 대한 식도 $u'$에 대한 **1st order ODE로 바뀌었다**!!

이렇게 $y_2 = u y_1$로 대입하면 ODE의 차수가 2에서 1로 떨어지기 때문에 "Reduction of Order"라는 이름이 붙은 것이다.

그 다음부터는 $u'$에 대한 1st order ODE를 풀고(Separable ODE라서 별로 안 어려울 것이다), $u = \int u'$로 적분을 차례차례 하면 된다. EzEz


# 맺음말

뭔가 처음에 요 방식을 볼 때는 좀 어렵게 느껴졌는데, 뒤에 2nd order "non"-homo. linear ODE를 푸는 방법을 먼저 보고 오니까 요 방식이 쉬워보인다 ㅋㅋ

뭔가 미분방정식이란 과목이나 분야 자체가 패턴과 풀이법에 대해 주구장창 배우는 과목인 것 같다. 뭔가 께름직 하더라도 '아~~ 그렇구나~'하며 어느 정도 넘어가는 것도 필요하고, 안 까먹게 문제도 좀 풀어주고 하는게 필요한 것 같다.
