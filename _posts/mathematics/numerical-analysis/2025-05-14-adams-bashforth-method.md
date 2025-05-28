---
title: "Adams-Bashforth Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며


# Multi-step Method

Euler 방식과 RK 방식은 다음 단계 $u_{n+1}$를 구하기 위해 현재의 $u_n$와 미분방정식의 함수값 $f(t_n, u_n)$을 기반으로 계산 했습니다.

Multi-step 방법은 한 단계가 아니라 이전의 여러 단계 $u_{n-1}, u_{n-2}, \dots$의 값을 함께 사용해 다음 단계를 계산합니다. 이를 통해 정확도를 높이고, 이미 계산한 값을 사용해 계산량을 줄입니다.

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

이때, 적분 내부의 $f(t, u(t))$ 함수를 보간 합니다. $f(t, u(t))$를 이전 두 시점 $t_n$와 $t_{n-1}$의 값으로 보간 합니다.

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
u_{n+r} = u_{n+r - 1} + k \sum_{j=0}^{r} \beta_j f(t_{n+j}, u_{n+j})
$$

# Adams-Moulton Method

## 2nd AM Method

$$
u_{n+2} = u_{n+1} + \frac{k}{12}(5f_{n+2} + 8 f_{n+1} - f_n)
$$

AM 방법을 AB와 다르게 $u_{n+2}$를 계산하기 위해 $f_{n+2}$ 값을 사용합니다. 그리고, 공식을 유도하는 과정에서도 AB 2차는 2개 점을 Lagrange 보간하여 구했다면, AM 2차는 3개 점을 Lagrange 보간하여 구합니다.


<div class="proof" markdown="1">

먼저 아래의 적분을 작성합니다.

$$
u_{n+1} = u_n + \int_{t_n}^{t_{n+1}} f(t, u(t)) \, dt
$$

이때, 적분 내부의 $f(t, u(t))$ 함수를 보간 합니다. $f(t, u(t))$를 3가지 시점 $t_{n+1}$, $t_n$, $t_{n-1}$의 값으로 보간 합니다.

$$
f(t, u(t)) \approx f_{n+1} \cdot L_{n+1}(t) + f_{n} \cdot L_{n}(t) + f_{n-1} \cdot L_{n-1}(t)
$$

이걸 잘 적분하면 되는데... 직접 해보니 쉽지 않더라구요... 😅 HW만 아니었다면 직접 계산하려고 하지도 않았...

그래서 결과를 바로 적으면, 이걸 적분식에 대입하면 아래와 같습니다.

$$
\int_{t_n}^{t_{n+1}} f(t, u(t)) \, dt \approx
\frac{k}{12} (5f_{n+2} + 8 f_{n+1} - f_n)
$$

최종적으로 이걸 맨 처음의 적분식에 대입하면,

$$
u_{n+2} = u_{n+1} + \frac{k}{12}(5f_{n+2} + 8 f_{n+1} - f_n)
$$

2차 AM 근사식을 얻었습니다! $\blacksquare$

</div>

# AB Method vs. AM Method

(여기서부터 진행하기)


# General Form of Multi-step Method

$$
\sum_{j=0}^r \alpha_j u_{n + j} = k \sum_{j=0}^{r} \beta_j f(t_{n+j}, u_{n+j})
$$

식이 조금 복잡해보이지만, 좌/우변을 나눠서 생각해보면

- 좌변
  - 과거 및 현재의 근사 해들을 선형 결합한 것
- 우변
  - 대응되는 시간 지점에서의 함수값을 선형 결합한 것

# One-step vs. Multi-step

One-step 방법은 수치적 적근을 위해 초기값 $u_0$ 하나만 있으면 됩니다. 이것을 "self-staring"이 가능하다라고 말합니다. 그런데, Multi-step 방법은 여러 개의 초기값이 필요합니다. 그래서 보통은 One-step 방법을 몇 번 먼저 적용해 초기 시작값을 만들어줍니다.

시간 간격을 $k$로 잡다가 이것을 추정값에 따라 더 작은 스텝 $k' < k$로 변경하려고 합니다. One-step 방법은 별다른 제약 없이 바로 이 간격을 조정할 수 있습니다. 반면에 Multi-step 방법은 스텝을 바꿀 수는 있지만, 더 조심히 바꿔야 합니다. 왜냐하면 Multi-step 방법은 이전 값들이 동일한 시간 간격 $k$를 사용해 계산 되었다고 가정하기 때문입니다. 그래서 시간 간격을 조정하면, 계산의 정확성과 안정성이 영향을 받습니다.

