---
title: "Adams-Bashforth Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "이전 몇단계의 값을 조합해 수치적으로 미방을 푸는 방법에 대해."
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# Multi-step Method

[Euler 방식](/2025/05/12/euler-method/)과 [RK 방식](/2025/05/13/runge-kutta-method/)은 다음 단계 $u_{n+1}$를 구하기 위해 현재의 $u_n$와 미분방정식의 함수값 $f(t_n, u_n)$을 기반으로 계산 했습니다.

"**Multi-step Method**"은 한 단계가 아니라 이전의 여러 단계 $u_{n-1}, u_{n-2}, \dots$의 값을 함께 사용해 다음 단계를 계산합니다. 이를 통해 정확도를 높이고, 이미 계산한 값을 사용해 계산량을 줄입니다.

# Adams-Bashforth Method

## 2nd AB Method

$$
u_{n+2} = u_{n+1} + \frac{k}{2}\left( 3 f(t_{n+1}, u_{n+1}) - f(t_n, u_n) \right)
$$

좀더 간단하게는 이렇게 작성할 수도 있습니다.

$$
u_{n+2} = u_{n+1} + \frac{k}{2}(3f_{n+1} - f_n)
$$

이 공식을 유도 해봅시다!

<div class="proof" markdown="1">

먼저 아래의 적분을 작성합니다.

$$
u_{n+1} = u_n + \int_{t_n}^{t_{n+1}} f(t, u(t)) \, dt
$$

이때, 적분 내부의 $f(t, u(t))$ 함수를 보간 합니다. $f(t, u(t))$를 이전 두 시점 $t_n$와 $t_{n-1}$의 값으로 "**라그랑주 보간**" 합니다.

$$
\begin{aligned}
f(t)
&\approx f_n \cdot \frac{t-t_{n-1}}{t_n - t_{n-1}} + f_{n-1} \cdot \frac{t - t_n}{t_{n-1} - t_n} \\
&\approx f_n \cdot \frac{t-t_{n-1}}{k} + f_{n-1} \cdot \frac{t_n - t}{k}
\end{aligned}
$$

이 보간 다항식을 처음의 적분에 대입하고, 적분을 계산합니다.

$$
\begin{aligned}
u_{n+1}
&= u_n + \int_{t_n}^{t_{n+1}} f(t, u(t)) \, dt \\
&= u_n + \int_{t_n}^{t_{n+1}} \left[ f_n \cdot \frac{t-t_{n-1}}{k} + f_{n-1} \cdot \frac{t_n - t}{k} \right] \, dt \\
&= u_n
+ \left. f_n \frac{(t - t_{n-1})^2}{2k} \right]_{t_n}^{t_{n+1}}
- \left. f_{n-1} \frac{(t_n - t)^2}{2k} \right]_{t_n}^{t_{n+1}} \\
&= u_n + f_n \cdot \frac{(2k)^2 - k^2}{2k}
- f_{n-1} \frac{(-k)^2 - 0^2}{2k} \\
&= u_n + f_n \cdot \frac{3}{2k} - f_{n-1} \cdot \frac{1}{2k}
\end{aligned}
$$

2차 AB 근사식을 얻었습니다! $\blacksquare$

</div>

## General Form of AB Method

$$
u_{n+r} = u_{n+r - 1} + k \cdot \sum_{j=0}^{\color{red} r-1} \beta_j f(t_{n+j}, u_{n+j})
$$

$r$차 AB 방식의 형태 입니다. 이때, 계수 $\beta_j$는 사전 계산된 "AB 계수"입니다!

AB 방식은 이전 $t_n$ 시점부터 $t_{n+r-1}$ 시점까지의 $f_i$를 사용하기 때문에, $j$ 인덱스가 $0$부터 $(r-1)$의 값을 가집니다.

# Example

가장 쉬운 미분방정식인 지수 감쇠 케이스를 살펴봅시다.

$$
u' = - u \quad u(0) = 1
$$

해석적 해는 $u(t) = e^{-t}$로 쉽게 구할 수 있습니다!

## AB1 Method

AB1 Method는 (Forward) Euler Method와 동일합니다!

$$
\begin{aligned}
u_{n+1}
&= u_n + k f_n \\
&= u_n + k f(u_n, t_n)
\end{aligned}
$$

이제 $u' = f(u, t)$를 적용하면,

$$
u_{n+1} = u_n - k u_n = (1-k) u_n
$$

## AB2 Method

AB2 Method의 공식에서 시작합니다.

$$
u_{n+2} = u_{n+1} + \frac{k}{2}(3f_{n+1} - f_n)
$$

이때, AB2는 2개의 초기값이 필요합니다. $u_1 = (1-k) u_0 = 1-k$로 구합니다. 그리고 이걸 대입하면,

$$
\begin{aligned}
u_2
&= u_1 + \frac{k}{2} \left( 3 u_1 - u_0 \right) \\
&= (1-k)u_0 + \frac{k}{2} \left( 3 (1-k) u_0 - u_0 \right) \\
&= (1-k) + \frac{k}{2} (3-3k - 1) \\
&= (1-k) + \frac{k}{2} (2 - 3k) \\
&= 1 - k + k - \frac{3}{2}k^2 \\
&= 1 - \frac{3}{2} k^2
\end{aligned}
$$


# 맺음말

이어서 AB Method를 확장한 "Adams-Moulton Method"에 대해 살펴봅니다! 둘은 항상 붙어서 나오는 기법이라고 AM Method도 잘 알아둬야 합니다!

➡️ [Adams-Moulton Method](/2025/05/14/adams-moulton-method/)
