---
title: "Taylor Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

지금까지 살펴본, "Euler Method"는 1차 미분값 $y'(t)$의 값을 기반으로 다음 스텝의 값을 근사 하였습니다.
그런데, 꼭 1차 미분값만 사용해야 할까요? 2차 미분값 $y''(t)$, 3차 미분값 $y{(3)}(t)$과 그 이상을 사용해 근사한다면 더 좋은 결과를 얻을 수 있지 않을까요?

이번 포스트에서는 1차 이상의 미분값을 활용해 미분방정식을 근사하는 "Taylor Method"에 대해 살펴봅니다!

# (review) Euler Method

오일러 방법에서는 미분방정식을 기울기 근사를 통해 아래와 같이 표현하였습니다.

$$
u'_n \approx \frac{u_{n+1} - u_n}{k} = f(t_n, u_n)
$$

그리고 식을 정리하면,

$$
u_{n+1} = u_n + k f(t_{n+1}, u_{n+1})
$$

사실 위의 식은 아래의 테일러 전개에서 얻은 것 입니다.

$$
u(t+k) = u(t) + \frac{k}{1!} u'(t) + \frac{k^2}{2!} u''(t) + \cdots
$$

오일러 방식이 위의 테일러 전개에서 1차 미분값까지 사용한 것이라면, 테일러 방식은 여기에서 2차 미분값까지 사용합니다!

# Taylor Method

테일러 방식은 오일러 방식을 일반화한 접근 입니다. 테일러 전개에서 $p$-th 항까지 사용합니다.

$$
u(t+k) = u(t) + \frac{k}{1!} u'(t) + \frac{k^2}{2!} u''(t) + \cdots
+ \frac{k^p}{p!} u^{(p)}(t)
$$

이것을 미분방정식 $f(\cdot)$과 함께 표현하면 아래와 같습니다.

$$
u_{n+1}
= u_n + k f(t_n, u_n) + \frac{k^2}{2!} f'(t_n, u_n) + \cdots
+ \frac{k^p}{p!} f^{(p-1)}(t_n, u_n)
$$

미분방정식 $f(t, u) = u'$를 여러 번 미분하여 활용합니다.

단, 더 많은 항을 포함하게 되면서, 정확도는 높아지지만 각 미분항에 대한 계산이 복잡해집니다.

## Example

$$
u' = t^2 \sin u
$$

위와 같은 미분방정식이 있을 때, 이를 오일러 방식과 2차 테일러 방식으로 근사 해봅시다.

<div class="proof" markdown="1">

[Euler Method]

$$
\begin{aligned}
u_{n+1}
&= u_n + k \cdot u'(t_n, u_n) \\
&= u_n + k \cdot (t^2 \sin u_n)
\end{aligned}
$$

</div>

<div class="proof" markdown="1">

$$
\begin{aligned}
u_{n+1}
&= u_n + k \cdot u'(t_n, u_n) + \frac{k^2}{2} u''(t_n, u_n) \\
&= u_n + k \cdot (t^2 \sin u_n) + \frac{k^2}{2} \cdot ?
\end{aligned}
$$

이때, $u^{\prime\prime}(t, u(t))$를 구하려면 전미분을 구해야 합니다.

$$
\begin{aligned}
u''(t, u(t))
&= \frac{\partial}{\partial t} (t^2 \sin u) + \frac{\partial}{\partial u} (t^2 \sin u) \\
&= (2t \sin u) + (t^2 \cos u \cdot u') \\
&= 2t \sin u + t^2 \cos u \cdot (t^2 \sin u) \\
&= 2t \sin u + t^4 \cos u \sin u
\end{aligned}
$$

따라서 식을 최종 정리하면,

$$
\begin{aligned}
u_{n+1}
&= u_n + k \cdot u'(t_n, u_n) + \frac{k^2}{2} u''(t_n, u_n) \\
&= u_n + k \cdot \left(t^2 \sin u_n\right) + \frac{k^2}{2} \cdot ? \\
&= u_n + k \cdot \left(t^2 \sin u_n\right) + \frac{k^2}{2} \cdot \left( 2t \sin u_n + t^4 \cos u_n \sin u_n \right)
\end{aligned}
$$

</div>


## Total Difference

2차 미분값을 얻기 위해서는 $f(t, u)$를 미분해야 합니다. 그런데, 이 함수는 $t$와 $u$, 두 변수에 의존하는 2변수 함수 입니다! 그래서 미분한 $f'(t, u)$는 두 편미분을 더한 전미분(total difference)를 계산해서 사용해야 합니다!

$$
\begin{aligned}
f'(t, u)
&= f_t(t, u) + f_u(t, u) \cdot u'(t) \\
&= f_t(t, u) + f_u(t, u) \cdot f(t, u)
\end{aligned}
$$

