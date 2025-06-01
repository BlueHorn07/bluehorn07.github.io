---
title: "Adams-Moulton Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "이전 몇단계의 값을 조합해 수치적으로 미방을 푸는 방법에 대해. AB 방법과 달리 구하려는 미지수가 방정식에 포함된 Implicit 방법입니다."
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# Multi-step Method

[Euler 방식](/2025/05/12/euler-method/)과 [RK 방식](/2025/05/13/runge-kutta-method/)은 다음 단계 $u_{n+1}$를 구하기 위해 현재의 $u_n$와 미분방정식의 함수값 $f(t_n, u_n)$을 기반으로 계산 했습니다.

"**Multi-step Method**"은 한 단계가 아니라 이전의 여러 단계 $u_{n-1}, u_{n-2}, \dots$의 값을 함께 사용해 다음 단계를 계산합니다. 이를 통해 정확도를 높이고, 이미 계산한 값을 사용해 계산량을 줄입니다.

"[**Adams-Bashborth Method**](/2025/05/14/adams-bashforth-method/)"에서 명시적(Explicit)으로 Multi-step Method를 수행하는 방법을 살펴보았습니다!
이번 포스트에선 AB Method을 확장해 암시적(Implicit)으로 수행해 안정성을 높인 "Adams-Moulton Method"에 대해 살펴봅니다!

# Adams-Moulton Method

AB 방식을 확장한 기법으로, $u_{n+r}$의 값을 계산하기 위해서 $f_{n+r}$의 값을 계산합니다! 그런데,

$$
f_{n+r} = f(t_{n+r}, {\color{red} u_{n+r}})
$$

로 우리가 구하고 싶은 미지수인 $u_{n+r}$이 우변에 포함되어 있습니다! 그래서, Implicit Form이라고 하고, 이런 경우는 중간 고사 전에 배웠던 고정반복법(FPI)이나 Newton's Method를 사용해야 합니다.


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

그래서 결과를 바로 적으면, 적분 부분이 이렇게 바뀝니다.

$$
\int_{t_n}^{t_{n+1}} f(t, u(t)) \, dt \approx
\frac{k}{12} (5f_{n+2} + 8 f_{n+1} - f_n)
$$

최종적으로 이걸 맨 처음의 근사식에 대입하면,

$$
u_{n+2} = u_{n+1} + \frac{k}{12}(5f_{n+2} + 8 f_{n+1} - f_n)
$$

2차 AM 근사식을 얻었습니다! $\blacksquare$

</div>

## Simple Example

가장 간단한 형태의 미분방정식을 AM 방식으로 풀어봅시다.

$$
u' = - u, \quad u(0) = 1
$$

아주 기본적인 미분방정식이고 해석적 솔루션은 $u(t) = e^{-t}$ 입니다.

### AM1 Method

AM1의 식을 적으면,

$$
\begin{aligned}
u_{n+1}
&= u_n + k f_{n+1} \\
&= u_n + k f(u_{n+1}, t_{n+1}) \\
\end{aligned}
$$

이제 $u' = f$를 대입하면,

$$
\begin{aligned}
u_{n+1}
&= u_n + k u'(u_{n+1}, t_{n+1}) \\
&= u_n - k u_{n+1}
\end{aligned}
$$

따라서,

$$
u_{n+1} = \frac{u_n}{1+k}
$$

### AM2 Method

AM2의 공식은 아래와 같습니다.

$$
\begin{aligned}
u_{n+1}
&= u_n + \frac{k}{12} (5 f_{n+1} + 8 f_n - f_{n-1})
\end{aligned}
$$

이때, $f_i = f(u_i, t_i) = -u_i$입니다.

AM2는 2개의 초기값이 필요합니다. 현재 $u(0) = 1$에 대한 것만 알고 있으므로, $u_1$에 대한 값이 필요합니다! 이것은 AM1 방법으로 구하면 됩니다.

$$
u_1 = \frac{u_0}{1+k} = \frac{1}{1+k}
$$

이제, $u_2$를 구하면

$$
\begin{aligned}
u_2
&= u_1 + \frac{k}{12} (5 f_2 + 8 f_1 - f_0) \\
&= \frac{1}{1+k} + \frac{k}{12} \left(5 \cdot (-u_2) + 8 \cdot \frac{1}{1+k} - 1\right)
\end{aligned}
$$

이제, 미지수 $u_2$를 암시적 방법에 의해 계산하면 됩니다!


## General Form of AM Method

$$
u_{n+r} = u_{n+r - 1} + k \cdot \sum_{j=0}^{\color{red} r} \beta_j f(t_{n+j}, u_{n+j})
$$

# AB Method vs. AM Method

AB 방식과 AM 방식을 비교해봅시다!

[공통]

둘다 다단계로 수행되는 수치적 미방 기법 입니다.

그리고 다음 단계의 값 $u_{n+r}$를 구하기 위해서 이전에 계산했던 $f_{n+r-i}$ 값을을 **재활용**할 수 있습니다!

[차이]

- Explicit vs. Implicit
  - AB는 명시적이고,
  - AM은 암시적인 식을 풀어야 합니다.
  - 저는 아래의 성질로 차이를 받아드렸는데
  - AB1는 Forward Euler이고, AM1는 Backward Euler 입니다.
- Fast vs. Slow
  - AB는 공식에 대입하기만 하면 되기 때문에, 쉽게 계산할 수 있고 구현도 쉽습니다.
  - AM은 공식에 대입하고, 미지수에 대한 방정식을 풀거나, 반복법(ex. FPI, Newton's Method)으로 해를 찾아야 하기 떄문에 느립니다.
- Prediction vs. Correction
  - AB는 Prediction 단계에서 사용하고,
  - AM은 Correction 단계에서 사용합니다.
  - 이 부분은 이어지는 포트스인 "[Predictor-Corrector Method](/2025/05/15/predictor-corrector-method/)"에서 자세히 다룹니다!

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

# 맺음말

이어지는 포스트에선 AB Method와 AM Method를 조합해 사용하는, "**Predictor-Corrector Method**"에 대해 살펴봅니다!

➡️ [Predictor-Corrector Method](/2025/05/15/predictor-corrector-method/)
