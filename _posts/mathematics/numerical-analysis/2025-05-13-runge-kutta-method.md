---
title: "Runge-Kutta Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "One-step Method를 분할해 적용하는 기법!"
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

미분방정식의 수치적으로 풀 때, 높은 정확도를 얻기 위해서 고차 미분을 포함하는 [Taylor Method](/2025/05/12/taylor-method/)를 살펴보았습니다. 그러나 복잡한 전미분을 계산해 고차 미분을 얻어야 한다는 단점이 있었습니다.

그래서 등장한 것이 "**Runge-Kutta Method**" 입니다. 이것은 1차 미분값만 사용하는, One-step 방식이지만 1차 미분값을 여러개 계산하고 이것에 가중치를 부여해 사용함으로써 높은 정확도를 얻게 합니다.

# Improved Euler Method

$$
\begin{aligned}
{\color{red} k_1} &= f(t_n, u_n) \\
k_2 &= f(t_n + h, u_n + h \cdot {\color{red} k_1}) \\
u_{n+1} &= u_n + \frac{h}{2} \left({\color{red} k_1} + k_2 \right)
\end{aligned}
$$

기존의 오일러 방식은 현재 지점($t_n, u_n$)에서의 기울기인 $k_1$만을 사용했습니다. 개선된 오일러 방식은 현재 지점에서의 기울기와 함께, 이동한 지점에서의 기울기 $k_2$를 계산합니다. $k_2$를 구할 때, $k_1$에서 구한 값을 활용해 이동해야 함에 유의 합니다.

그리고 두 기울기 값의 평균을 구하고, 이를 사용해 다음 스텝으로 나아갑니다!

이 방식을 "개선된 오일러 방식" 또는 "Heun's Method"라고 합니다. 이 방식은 One-step Method를 2번 수행합니다.
이렇게 여러번의 One-step Method의 결과를 모아서 사용한다는 아이디어에서 출발한게 Runge-Kutta 방식 입니다!

# Runge-Kutta Method

바로 식부터 살펴봅시다!

$$
\begin{aligned}
k_1 &= f(t_n, u_n) \\
k_2 &= f(t_n + \frac{h}{2}, u_n + \frac{h}{2} k_1) \\
u_{n+1} &= u_n + h \cdot k_2
\end{aligned}
$$

이것을 오일러 방식의 절반만 가서 기울기 $k_2$를 얻고 그걸로 전체 업데이트를 수행 합니다. 이것을 2차 RK 방식 또는 RK2 방식이라고 합니다. 참고로 앞에서 살펴본 "Heun's Method"와 살짝 다릅니다!

<br/>

$$
\begin{aligned}
k_1 &= f(t_n, u_n) \\
k_2 &= f\left(t_n + \frac{h}{2}, u_n + \frac{h}{2} k_1\right) \\
k_3 &= f\left(t_n + h, u_n - h k_1 + 2h k_2\right) \\
u_{n+1} &= u_n + \frac{h}{6} \left( k_1 + 4 k_2 + k_3 \right)
\end{aligned}
$$

이번에는 3번의 기울기를 계산하고 이를 종합해 사용합니다. 이를 RK3라고 합니다.

<br/>

$$
\begin{aligned}
k_1 &= f(t_n, u_n) \\
k_2 &= f\left(t_n + \frac{h}{2}, u_n + \frac{h}{2} \cdot k_1\right) \\
k_3 &= f\left(t_n + \frac{h}{2}, u_n + \frac{h}{2} \cdot k_2\right) \\
k_4 &= f\left(t_n + h, u_n + h \cdot k_3\right) \\
u_{n+1} &= u_n + \frac{h}{6} \left( k_1 + 2 k_2 + 2 k_3 + k_4 \right)
\end{aligned}
$$

마지막으로 4번의 기울기를 계산하는 RK4 방식 입니다. 보통 RK4를 자주 사용한다고 합니다.

# 맺음말

이어지는 포스트에선 $u_{n+1}$를 구하기 위해 $u_{n}$ 그리고 그 이전의 $u_{n-1}$, $u_{n-2}$를 사용해 미분방정식을 근사하는 "Multi-step Method"에 대해서 살펴봅니다!

