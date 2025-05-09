---
title: "Numerial Integration"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

수치적 적분은 우리가 함수 $f(x)$와 데이터 포인트 $(x_i, y_i)$들만 알고 있을 때, 이것들을 사용해 정적분 $\int f(x)$의 값을 근사하는 것 입니다.

이번 포스트에서는 주어진 함수 $f(x)$의 부정적분 $\int_a^b f(x)\,dx$를 수치적 적분으로 구하는 방법을 살펴봅니다. 수치적으로 부정적분은 아래와 같은 꼴을 갖게 됩니다.

$$
\int_a^b f(x) \, dx
= \sum_{i=0}^n a_i f(x_i)
$$

이때, 데이터 포인트의 집합 $\left\\{ x_0, \dots, x_n \right\\}$은 $[a, b]$ 사이의 서로 다른 점들 입니다.

# Trapezoid Rule

![](/images/mathematics/numerical-analysis/trapezoid-rule.png){: .fill .align-center style="width: 400px" }

"Trapezoid"는 사다리꼴이라는 뜻 입니다. 이 방식은 데이터 포인트를 단 2개만 사용합니다.

그래서 

- $x_0 = a$
- $x_1 = b$
- $h = (b-a)$

로 구성 됩니다. 그리고 이걸로 Langrange 보간 함수를 구하면

$$
P(x) = \frac{(x-x_1)}{(x_0 - x_1)} f(x_0) + \frac{(x-x_0)}{(x_1 - x_0)} f(x_1)
$$

이런 Linear Lagrange Polynomial이 나옵니다. 그리고 이걸 적분하면,

$$
\int_a^b f(x) \, dx
= \int_{x_0 = a}^{x_1 = b} 
\left[ 
\frac{(x-x_1)}{(x_0 - x_1)} f(x_0) + \frac{(x-x_0)}{(x_1 - x_0)} f(x_1)
\right] \, dx + \text{Err Term}
$$

에러 텀은 나중에 분석하고 일단 계속 작성해봅시다.

$$
\begin{aligned}
\int_a^b f(x) \, dx \approx
&= \left[ 
\frac{(x-x_1)^2}{2(x_0 - x_1)} f(x_0) + \frac{(x-x_0)^2}{2(x_1 - x_0)} f(x_1)
\right]_{x_0}^{x_1} \\
&= \frac{x_1 - x_0}{2} [f(x_0) + f(x_1)] \\
&= \frac{f(x_0) + f(x_1)}{2} h
\end{aligned}
$$

즉, 사다리꼴의 넓이로 수치적 적분을 구합니다! 정말 심플한 접근법!! ㅋㅋㅋ

# Simpson's Rule

![](/images/mathematics/numerical-analysis/simpson-rule.png){: .fill .align-center style="width: 400px" }

이번에는 균등하게 분포된 3개의 점을 사용해 적분 근사를 수행합니다.

- $x_0 = a$
- $x_1 = a + h$
- $x_2 = b$
- $h = (b-a)/2$

마찬가지로 라그랑주 근사를 합니다.

$$
P_2(x) = 
\frac{(x-x_1)(x-x_2)}{(x_0-x_1)(x_0-x_2)}f(x_0)
+ \frac{(x-x_0)(x-x_2)}{(x_1-x_0)(x_1-x_2)}f(x_1)
+ \frac{(x-x_0)(x-x_1)}{(x_2-x_0)(x_2-x_1)}f(x_2)
$$

이때, 각 $(x-x_i)(x-x_j)$에 대한 적분을 구해서 식을 정리하려고 하면... 지옥이 펼쳐집니다.. ㅋㅋ (직접 해봄;;)


결론부터 적으면, 심프슨 방식의 적분 근사는 아래와 같이 유도 됩니다.

$$
\int_{x_0}^{x_2} f(x) \, dx
\approx \frac{h}{3} (f(x_0) + 4 f(x_1) + f(x_2))
$$

그래서 일반성을 잃지 않고(w.l.o.g), $x_1 = 0$를 설정하고, $x_0 = -h$, $x_1 = h$가 되는 수평 이동한 함수 $g(x)$에 대해서 적분 근사를 대신 수행합니다.

<div class="proof" markdown="1">

$P_2(x)$는 아래와 같이 다시 작성 됩니다.

$$
P_2(x) = 
\frac{(x)(x-h)}{(-h)(-2h)}f(x_0)
+ \frac{(x+h)(x-h)}{(+h)(-h)}f(x_1)
+ \frac{(x+h)(x)}{(2h)(h)}f(x_2)
$$


$$
\begin{aligned}
\int_{-h}^{h} f(x) \, dx 
&\approx \int_{-h}^{h} P_2(x) \, dx \\
&= \int_{-h}^{h} \frac{1}{2h^2} \left[
x(x-h)f(x_0) - 2 (x^2 - h^2) f(x_1) + x(x+h) f(x_2)
\right] \, dx \\
&= \int_{-h}^{h} \frac{1}{2h^2} \left[
x^2 f(x_0) - xhf(x_0) - 2x^2 f(x_1) + 2h^2 f(x_1) + x^2 f(x_2) + xh f(x_2)
\right] \, dx \\
\end{aligned}
$$

여기에서 기함수는 대칭성으로 인해 적분값이 0이 됩니다. 이것들을 버려내면

$$
\begin{aligned}
\int_{-h}^{h} f(x) \, dx 
&\approx \int_{-h}^{h} P_2(x) \, dx \\
&= \int_{-h}^{h} \frac{1}{2h^2} \left[
x^2 f(x_0) - 2x^2 f(x_1) + 2h^2 f(x_1) + x^2 f(x_2)
\right] \, dx \\
\end{aligned}
$$

그리고 실제로 적분을 수행해봅시다! 이 적분은 (비교적) 간단합니다!

$$
\begin{aligned}
\int_{-h}^{h} f(x) \, dx 
&\approx \int_{-h}^{h} P_2(x) \, dx \\
&= \frac{1}{2h^2} \left[
\frac{2}{3}h^3 f(x_0) - \frac{4}{3}h^3 f(x_1)
+ 4 h^3 f(x_1) + \frac{2}{3}h^3 f(x_2)
\right] \\
&= \frac{h}{3} f(x_0) - \frac{2h}{3}f(x_1) + 2hf(x_1) + \frac{h}{3}f(x_2) \\
&= \frac{h}{3}\left[
f(x_0) + 4f(x_1) + f(x_2)
\right]
\end{aligned}
$$

</div>

와우!! 심프슨 공식이 유도 되었습니다 ㅎㅎ 적분은 수평 이동한 함수에서 수행하든 원래 위치에서 수행하든 상관 없습니다! 그래서 이것 그대로 결과로 사용하면 됩니다!


## 4-points case

데이터 포인트가 2개, 3개로 늘어났는데, 4개인 경우는 어떻게 될까요? 이 경우도 공식이 있습니다.

이것은 "Simpson's 3/8 Rule"라고 하며, 공식은 아래와 같습니다.

$$
\int_{x_0}^{x_3} f(x) \, dx \approx
\frac{3h}{8} (f(x_0) + 3f(x_1) + 3f(x_2) + f(x_3))
$$

공식에 대한 유도는 3-point에서 했던 것처럼 함수를 평행이동 한 후에 수행해주면 된다고 합니다.


# Error Analysis

## Trapezoie Rule

## Simpson's Rule