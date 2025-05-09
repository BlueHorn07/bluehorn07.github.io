---
title: "Interpolation Differentiation"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "보간 함수를 미분해서 도함수를 근사하는 방법에 대해"
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# Differentiation on Interpolation

도함수에 대한 근사를 얻는 가장 쉬운 방법은 다항식 근사한 결과 $f(x) \approx P(x)$를 미분한 도함수 근사를 $f'(x) \approx P'(x)$를 사용하는 것 입니다.

$$
P'(x) = \sum_i f(x_i) L_i'(x)
$$

$L_i(x)$를 미분하는 게 좀 많이 고통스럽겠지만, 정신을 바짝차리면 어찌저찌 해서 얻을 수는 있습니다. 그런데 우리는 이런 도함수의 근사가 얼만큼의 오차를 만들어내는지도 알아야 합니다.

라그랑주 근사에서 오차항은 아래와 같이 정의 됩니다. cc. [Interpolation Error](/2025/03/29/interpolation-error-theorem/)

$$
f(x) - P(x) = \frac{\omega_{n+1}(x)}{(n+1)!} f^{(n+1)} (\xi(x))
$$

도함수의 근사에 대한 오류는 이것을 $x$에 대해 미분하면 얻을 수 있습니다.

$$
f'(x) - P'(x) =
\frac{d}{dx} \left(
  \frac{\omega_{n+1}(x)}{(n+1)!} f^{(n+1)} (\xi(x))
\right)
$$

그러나 이 결과는 전혀 도움이 되지 않습니다! 이것을 구할 때의 가장 큰 장애물은 $\xi(x)$ 입니다. 미분을 하면 체인룰에 의해 $d\xi / dx$가 나오게 되는데, 우리는 $\xi(x)$ 함수가 무엇인지 특정할 수도 없고 이게 미분 가능한지도 모릅니다. 그래서 이 미분 자체가 **의미가 없고 할 수도 없습니다.**

## Lagrange Differentiation Error Formula

그런데, 이걸 좀더 간단한 버전의 보간 오차로 표현하는 정리가 있습니다.

<div class="theorem" markdown="1">

라그랑주 보간법이 존재하기 위한 기본 세팅은 같습니다. 함수 $f(x)$는 연속이어야 하고, 이것은 $n+1$번 미분 가능하고 연속이어야 합니다. 그리고 $n+1$개의 $\left\{ x_0, x_1, \dots, x_n \right\}$ 점들이 존재합니다.

그러면 아래와 같이 $n$개의 서로 다른 점 $\eta_i, i=1, \dots, n$이 있고, 각 $x$에 대응 되는 $\xi(x)$도 존재해 아래의 오차 등식이 성립합니다.

$$
f'(x) - P'(x) =
\frac{f^{(n+1)}(\xi(x))}{n!} \omega_n^\ast (x)
$$

이때, $\omega_n^\ast (x) = (x-\eta_1)\cdots(x-\eta_n)$ 입니다.

</div>

처음에 체인룰 때문에 $d\xi/dx$가 나온다고 했던 그 오차랑은 좀 다른 녀석이 나왔습니다!! 그리고 $\eta_i$라는 처음 보는 점들도 정의되고, 그걸로 $\omega_n^\ast(x)$라는 새로운 다항식도 증가했습니다. 😵‍💫

처음엔 이 정리가 엄청 헷갈리는데, 한번 최대한 설명을 해보겠습니다!

<div class="proof" markdown="1">

일단 $f(x)$와 $P(x)$는 정의에 따라서, $\left\{ x_0, \dots, x_n \right\}$, $n+1$개의 점에 대해서 아래의 등식이 성립한다.

$$
f(x_i) - P(x_i) = 0
$$

잘 보면, $f(x) - P(x)$ 함수는 $n+1$개 점에 대해 서로 함수값이 같은 지점이 존재하는 것 입니다. 그러면, $(x_{i-1}, x_i)$ 사이의 어떤 점에 $\eta_i$가 존재해서, 도함수에 대해 아래의 등식을 만족한다! 이것은 "[롤의 정리](https://ko.wikipedia.org/wiki/%EB%A1%A4%EC%9D%98_%EC%A0%95%EB%A6%AC)"에 따른 결과 입니다.

$$
f'(\eta_i) - P'(\eta_i) = 0
$$

이 $\eta_i$ 점은 $i=1, \dots, n$로 $n$개 존재한다. 이 점들은 원본 도함수 $f'(x)$와 보간 도함수 $P'(x)$가 같게 되는 특별한 점들 입니다.

도함수 근사의 오차를 구하려는 점 $x$가 $\eta_i$ 중 하나와 같다면 오차는 0이 되겠지만, 우리는 전개를 계속하기 위해 도함수 오차를 구하려는 $x$가 모든 $\eta_i$들과 서로 다른 점이라고 가성 하겠습니다.

<br/>

이때, 새로운 함수 $\chi(t)$를 아래와 같이 정의 합니다.

$$
\chi(t)
= f'(t) - P'(t) - \frac{f'(x) - P'(x)}{\omega_n^\ast(x)} \omega_n^\ast (t)
$$

이 함수는 특별히 정의된 함수로 아래와 같은 성질을 만족 합니다.

[$t = \eta_i$]

정의에 따라 $\omega_n^\ast(\eta_i) = 0$가 되고,

$$
\begin{aligned}
\chi(t = \eta_i)
&= f'(\eta_i) - P'(\eta_i) - \frac{f'(x) - P'(x)}{\omega_n^\ast(x)} \cancel{\omega_n^\ast (\eta_i)} \\
&= f'(\eta_i) - P'(\eta_i) \\
&= 0
\end{aligned}
$$

[$t = x$]

$$
\begin{aligned}
\chi(t = x)
&= f'(x) - P'(x) - \frac{f'(x) - P'(x)}{\omega_n^\ast(x)} \omega_n^\ast (x) \\
&= f'(x) - P'(x) - \left(f'(x) - P'(x)\right) \\
&= 0
\end{aligned}
$$

즉, 모든 $\eta_i$와 $x$에 대해서 $\chi(t) = 0$이 됩니다. 이것은 함수 $\chi(t)$가 서로 다른 $n+1$개의 서로 다른 점 $(\eta_1, \eta_2, \dots, \eta_n, x)$에서 0이 되는 함수라는 걸 말합니다.

<br/>

[롤의 정리를 적용]

함수 $\chi(t)$가 $n+1$개 점에서 0이 되므로 롤의 정리에 따라 $\chi'(t) = 0$이 되는 $t$가 $(\eta_1, \eta_2), (\eta_2, \eta_3), \dots$ 사이에 $n$개 존재합니다.

마찬가지로 $\chi'(t)$에 한번더 롤의 정리를 적용하면,
$\chi''(t) = 0$이 되는 점도 $n-1$개 존재합니다.

이것을 반복하면 최종적으로 $\chi^{(n)}(t) = 0$이 되는 한 점을 $(a, b)$ 안에서 찾을 수 있습니다. 그 한 점을 $\xi$라고 합니다.

$$
\chi^{(n)} (\xi) = 0
$$

<br/>

TODO... 나머지는 추후에... [Hermite 보간법의 유일성을 증명할 때](/2025/03/31/hermite-interpolation/)와 비슷한 논리를 사용하면 된다고 합니다...

</div>

# Convergence of Interpolation Polynomial

TODO... 기말고사 때 다시 옵시다.


# 맺음말

이 포스트에서는 보간(interpolation)으로 얻은 함수를 미분하는 방식으로 수치적 미분을 달성 했습니다.
다음 포스트에서는 수치적 미분과 수치적 적분의 방법들을 살펴봅니다.

수치적 미분은 "테일러전개 + 미정게수법(Method of undermined coefficient)"을 사용해 유도할 수 있고,

수치적 적분은 "뉴턴-코츠 방법"라고 이름 붙은 방식으로 하는데, 쉽게 보면 함수를 1차, 2차, 3차 함수로 근사하고 적분하는 방식 입니다.

<br/>

마무리 하기 전에! 뒤에서 수치적 미분을 배울건데, 왜 보간 함수에서 미분을 살펴봤는지 궁금 했었는데요.

보간 함수에서의 미분은 함수값이 많이 주어졌고, 원본 함수가 매끄럽고 부드러우며 정밀한 미분값이 필요한 경우에 사용합니다.

바면, 미분값을 $x_i$ 주변의 값만 알고 있고, 수치적 미분이 아주 간단한 형태로 유도되기 때문에 도함수 계산을 실시간으로 수행해야 하는 경우에 적합하다고 합니다.

<br/>

그럼 바로 수치적 미분과 적분을 만나봅시다!

➡️ [Numerical Differentiation](/2025/04/28/numerical-differentation/)

➡️ [Numerical Integration](/2025/05/04/numerical-integration/)


# Reference

- [[Youtube] Lecture 11 Numerical Differentiation - 2 Polynomial Interpolation Method](https://www.youtube.com/watch?v=yC2h7yMt3DI)
