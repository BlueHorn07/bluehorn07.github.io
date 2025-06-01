---
title: "Predictor-Corrector Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

Multi-step Method로

- Adams-Bashforth Method
- Adams-Moulton Method

2가지를 살펴보았습니다. 이번에는 2가지 방식을 조합한 Multi-stage Method인 "Predictor-Corrector Method"에 대해 살펴보겠습니다.


# Predictor-Corrector Method

AM 방식은 안정성은 높지만, Implicit 특징 때문에 미지수 $u_{n+r}$에 대한 방정식을 수치적으로 구해야 했습니다. "Predictor-Corrector Method"는 이것을 AB 방식을 먼저 적용하고, 그 결과를 AM 방식에 적용하는 것으로 보완 합니다.

## Predictor

먼저, AB2 방식으로 $u_{n+1}$를 구합니다.

$$
\hat{u}_{n+1} = u_n + \frac{k}{2} (3f_{n} - f_{n-1})
$$

AB2 방식으로 구한 $\hat{u}_{n+1}$를 예측값(prediction)으로 사용합니다!

## Corrector

이제, AM1 방식으로 $u_{n+1}$를 다시 구합니다!

$$
u_{n+1} = u_n + \frac{k}{2} \left(f_{n+1}({\color{red} \hat{u}_{n+1}}, t_{n+1}) + f_n(u_n, t_n)\right)
$$

원래 $f_{n+1}({\color{red} u_{n+1}}, t_{n+1})$에서 $u_{n+1}$은 미지수로 직접 구해야 하는 값이었습니다. 그런데, 이것을 AB2로 구한 예측값을 사용하여 보완 합니다!

## Repeat

AM1으로 구한 $u_{n+1}$ 값을 다시 AM1의 식에 넣어서 보정 합니다. $u_{n+1}$ 값이 수렴할 때까지 반복하면 되는데, 보통 1-2회 정도 수행한다고 합니다.

<br/>

그런데, 왜 AB2와 AM1 방식을 같이 쓰는 건지, (AB2+AM2) 조합으로 하면 안 되는지 궁금해졌다. 찾아보니 (AB2+AM1) 조합이 계산이 간단해서 많이 사용한다고 한다 ^^;

# 맺음말

이것으로 수치적으로 미분방정식을 푸는 모든 방법을 살펴보았습니다!

- Numerical Methods for ODEs
  - [One-step Method](/2025/05/12/one-step-method/)
    - [Forward Euler Method](/2025/05/12/euler-method/)
    - Backward Euler Method
    - Trapzoid Method
  - [Taylor Method](/2025/05/12/taylor-method/)
  - [Runge-Kutta Method](/2025/05/13/runge-kutta-method/)
  - [Adams-Bashforth Method](/2025/05/14/adams-bashforth-method/)
  - [Adams-Moulton Method](/2025/05/14/adams-moulton-method/)
  - [Predictor-Corrector Method](/2025/05/15/predictor-corrector-method/)
- Approximation Theory
  - [Least Square Method](/2025/05/17/least-square-method/)
  - [Continuous Least Square Method](/2025/05/18/continuous-least-square-method/)

정말 많군요... ^^;

이어지는 포스트에선 미분방정식의 **안정성(Stability)**를 분석하는 방법에 대해 살펴봅니다!
