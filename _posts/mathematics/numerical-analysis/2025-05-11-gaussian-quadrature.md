---
title: "Gaussian Quadrature"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

"*Quadrature(구적법)*"라는 처음 보는 단어가 있습니다. 이것은 "적분"을 부르면 옛 표현이라고 합니다.

[뉴턴-코츠 방법](/2025/05/04/numerical-integration/)은 등간격으로 분포한 $(n+1)$개의 점을 사용해 정확도 $n$(홀수) 또는 $(n+1)$(짝수)를 얻었습니다.

이번 포스트에서 살펴보는 Gaussian Quadrature는 똑같이 $(n+1)$개 점을 사용하지만, 정확도는 $(2n+1)$로 꽤 높은 정확도를 얻습니다! 그리고 점의 간격 또한 "비등간격"으로 분포 합니다!

## Setup

가우시안 구적법을 하기 위해 사전 작업이 필요합니다. 먼저, $[a, b]$로 설정된 적분 구간을 $[-1, 1]$로 바꿉니다.

이 과정은 단순히 선형 변환(Linear Transformation)으로 수행하면 됩니다. 이 과정에서 변수를 $dx$에서 $dt$로 치환이 필요할 수도 있습니다.

## One-point Gaussian Quadrature

단 1개 점에서만 함수값을 보고 적분을 근사합니다.

$$
\int_{-1}^{1} f(x) \, dx \approx 2 \cdot f(0)
$$

$[-1, 1]$의 한 가운데 있는 $x = 0$의 함수값 $f(0)$과 그것을 간격의 넓이인 $2$를 곱해서 적분값을 구합니다 ㅋㅋㅋ

당연하게도 1점 구적법은 제대로된 결과를 얻지 못할 것 입니다. 예를 들어 $f(x) = x^2$에 대해 구적법으로 얻은 결과는

$$
\int_{-1}^{1} x^2 \, dx \approx 2 \cdot f(0) = 2 \cdot 0 = 0
$$

그런데, 실제 적분 결과는

$$
\int_{-1}^{1} x^2 \, dx = \left[\frac{x^3}{3}\right]_{-1}^{1} = \frac{1}{3} - \left(- \frac{1}{3}\right) = \frac{2}{3}
$$

하지만, 만약 함수가 $x=0$을 기준으로 대칭인 일차 함수 였거나, 상수 함수 였다면 이 1점 근사로 정확한 적분값을 얻을 수 있었을 것 입니다!

그래서 1점 구적법의 deg of precision $k$는 $k=0$이 됩니다. 상수 함수에 대해서만 항상 정확한 적분 값을 제공 합니다.

## Two-point Gaussian Quadrature

이번에는 2개의 나이스한 점에서의 함수값을 사용해 적분을 합니다.

$$
\int_{-1}^{1} f(x) \, dx \approx f\left(- \frac{1}{\sqrt{3}}\right) + f\left(\frac{1}{\sqrt{3}}\right)
$$

이번에는 두 점에서의 함수값을 사용해 적분을 합니다. 그리고 두 점에서의 가중치는 둘다 $w = 1$로 동일 합니다.

다시 $f(x) = x^2$의 예제를 살펴봅시다.

$$
\int_{-1}^{1} x^2 \, dx \approx \frac{1}{3} + \frac{1}{3} = \frac{2}{3}
$$

와우! 신기하게도 실제 적분값과 정확히 일치 합니다!! 이것은 $f(x) = x^3$에서도 동일하게 정확한 적분값을 제공 합니다.

$$
\int_{-1}^{1} x^3 \, dx \approx - \frac{1}{3\sqrt{3}} + \frac{1}{3\sqrt{3}} = 0
$$

기함수 성질에 의해 적분값이 0이 됩니다.

2점 구적법의 deg of precision $k$는 $k=3$가 됩니다! 3차 다항 함수까지 항상 정확한 적분값을 제공 합니다!

## Three-point Gaussian Quadrature

이번에는 3개의 나이스한 점에서의 함수값으로 적분을 수행 합니다. 공식은 아래와 같습니다.

$$
\int_{-1}^{1} f(x) \, dx
\approx
\frac{5}{9} \cdot f\left( - \sqrt{\frac{3}{5}} \right)
+ \frac{8}{9} \cdot f(0)
+ \frac{5}{9} \cdot f\left( \sqrt{\frac{3}{5}} \right)
$$

3점 구적법은 5차 다항 함수까지 항상 정확한 적분값을 제공합니다!

이제는 공식이 좀 복잡해졌습니다...;; 그리고 $\sqrt{3/5}$라는 값은 또 어디서 나온 걸까요??

# Gaussian Quadrature

가우시안 구적법의 공식은 아래와 같은 형태를 가집니다.

$$
\int_{-1}^{1} f(x) \, dx \approx \sum_{i=1}^{n} w_i \cdot f(x_i)
$$

이때, 사용하는 $n$개 점의 갯수가 따라 수치 적분의 정확도가 결정되는데, $k = 2n-1$로 결정 됩니다.

## Derivation of two-point case

2점 구적법의 공식을 유도해봅시다!

우리는 구적법이 $k = 3$까지 만족하는 $x_1, x_2$ 그리고 $w_1, w_2$를 찾아야 합니다. 이것은 아래의 선형 방정식을 풀어서 이를 만족하는 $x_1, x_2, w_1, w_2$를 찾는 문제와 동일합니다.

$$
\begin{aligned}
\int_{-1}^{1} x^0 \, dx &= \sum_{i=1}^2 w_i (x_i)^0 \\
\int_{-1}^{1} x^1 \, dx &= \sum_{i=1}^2 w_i (x_i)^1 \\
\int_{-1}^{1} x^2 \, dx &= \sum_{i=1}^2 w_i (x_i)^2 \\
\int_{-1}^{1} x^3 \, dx &= \sum_{i=1}^2 w_i (x_i)^3
\end{aligned}
$$

연립 방정식의 좌변을 먼저 정리해보겠습니다.


$$
\begin{aligned}
2 &= \sum_{i=1}^2 w_i (x_i)^0 \\
0 &= \sum_{i=1}^2 w_i (x_i)^1 \\
\frac{2}{3} &= \sum_{i=1}^2 w_i (x_i)^2 \\
0 &= \sum_{i=1}^2 w_i (x_i)^3
\end{aligned}
$$

그리고 우변을 정리해보겠습니다. $n=2$ 밖에 안 되기 때문에 급수를 풀어내는게 더 보기 좋습니다.

$$
\begin{aligned}
2 &= w_1 + w_2 \\
0 &= w_1 x_1 + w_2 x_2 \\
\frac{2}{3} &= w_1 x_1^2 + w_2 x_2^2 \\
0 &= w_1 x_1^3 + w_2 x_2^3
\end{aligned}
$$

그리고 이 선형 방정식을 풀어내면 됩니다. 각 과정을 따라가면...

가장 먼저 1번째 식에 의해 $w_2 = 2 - w_1$가 됩니다. 그리고 이것을 2번째 식에 대입하면,

$$
0 = w_1 x_1 + (2 - w_1) x_2
$$

그리고 이를 다시 $x_2$에 대해 정리합니다.

$$
x_2 = - \frac{w_1 x_1}{2 - w_1}
$$

이제 이것을 3번째 식에 대입합니다. 그러면, 3번째 식은 $w_1$과 $x_1$로만 이뤄진 식이 됩니다.

$$
\begin{aligned}
\frac{2}{3} &= w_1 x_1^2 + (2 - w_1) \left(- \frac{w_1 x_1}{2 - w_1}\right)^2 \\
\frac{2}{3} &= w_1 x_1^2 + \frac{w_1^2 x_1^2}{(2 - w_1)} \\
2 (2 - w_1) &= 3 (2-w_1) w_1x_1^2 + 3 w_1^2 x_1^2 \\
4 - 2 w_1 &= (6 - 3 w_1) (w_1 x_1^2) + 3 w_1^2 x_1^2 \\
4 - 2 w_1 &= 6 w_1 x_1^2 \cancel{- 3 w_1^2 x_1^2 + 3 w_1^2 x_1^2}
\end{aligned}
$$

이 식을 정리하면, $w_1 = \cdots $가 될 것이고, 이를 마지막 4번째 식에 대입하면, $w_1, w_2, x_1, x_2$의 모든 값을 정확히 얻어낼 수 있습니다!

2점 구적법에서조차 4개의 파라미터를 찾아야 했는데, 3점, 4점, $n$점 구적법이 되면 찾아야 하는 파라미터가 $2n$개가 됩니다. 그리고 손으로 직접 이것을 찾기는 매우 어려워집니다...

그래서 등장하는 것이 르장드르 다항식 $P_n(x)$을 사용하는 방법 입니다! 새로운 녀석이 나오는거라 좀 당황스럽긴 하지만 가우시안 구적법을 정말 쉽게 만들어준다고 하니!! 한번 만나봅시다!

# Derivation with Legendre Polynomial $P_n(x)$

르장드르 다항식이 뭔지는 일단 제쳐두고, 르장드르 다항식을 사용하면 어떻게 가우시안 구적법을 구하는게 쉬워지는지 먼저 살펴봅시다.

- 노드 $x_i$
  - 구간 $[-1, 1]$에서 르장드르 다항식 $P_n(x)$의 근(zero)를 찾는다.
- 가중치 $w_i$
  - 해당 노드에서 다음 공식을 이용해 계산한다.
  - $w_i = \dfrac{2}{(1-x_i^2)[P'_n(x_i)]^2}$

과정만 봤을 뿐인데도 이 방식이 더 쉬워보입니다! 르장드르 다항식이 근을 갖는 지점을 찾고, 그 점 위에서 공식을 이용해 가중치 $w_i$를 유도 합니다!

## Legendre Polynomial

이제는 르장드르 다항식이 뭔지 살펴봅시다! 르장드르 다항식은 아래와 같이 정의됩니다.

$$
\begin{aligned}
P_0(x) &= 1 \\
P_1(x) &= x \\
P_2(x) &= \frac{1}{2} (3x^2 - 1) \\
P_3(x) &= \frac{1}{2} (5x^3 - 3x) \\
P_4(x) &= \frac{1}{8}(35x^4 - 30x^2 + 3)
\end{aligned}
$$

다항식을 봐도 어떤 규칙성이 보이지는 않습니다 🤔

일단 바로 보이는 패턴은 모든 $P_n(x)$ 다항식은 $n$차 다항식 입니다. 그리고 짝수 함수는 짝함수로만 이뤄지고, 홀수 함수는 홀함수로만 이뤄집니다.

### Rodrigues' Formula

르장드르 다항식을 만드는 방법도 2가지가 있는데, 수업 때는 "Rodrigues' formula"의 공식 버전을 소개하였습니다.

$$
P_n(x) = \frac{1}{2^n n!} \frac{d^n}{dx^n} \left[(x^2-1)^n\right]
$$

공식이라고는 하지만 다가가기 쉬운 녀석은 아닙니다;; 공식에 따라 다항식을 몇개 유도해봅시다.

$$
\begin{aligned}
P_0(x)
&= \frac{1}{2^0 \cdot 0!} \frac{d^0}{dx^0} \left[ (x^2-1)^0 \right] \\
&= 1 \cdot \frac{d^0}{dx^0} \left[ 1 \right] \\
&= 1
\end{aligned}
$$

$$
\begin{aligned}
P_1(x)
&= \frac{1}{2^1 \cdot 1!} \frac{d}{dx} \left[(x^2-1)\right] \\
&= \frac{1}{2} \cdot 2x \\
&= x
\end{aligned}
$$

$$

\begin{aligned}
P_2(x)
&= \frac{1}{2^2 \cdot 2!} \frac{d^2}{dx^2} \left[(x^2-1)^2\right] \\
&= \frac{1}{8} \cdot \frac{d}{dx} \left[ 2 \cdot 2x (x^2 - 1)\right] \\
&= \frac{1}{8} \cdot 4 \cdot \frac{d}{dx} \left[ x (x^2 - 1)\right] \\
&= \frac{1}{2} \cdot \left[ (x^2 - 1) + 2x^2 \right] \\
&= \frac{1}{2} \left(3x^2 - 1\right)
\end{aligned}
$$

신기하게도 앞에서 적은 르장드르 다항식과 같은 결과를 제공 합니다!

### Recurrence Relation

르장드르 다항식은 점화식으로도 구할 수 있습니다! 점화식은 아래와 같습니다.

$$
(n+1) P_{n+1}(x)
= (2n+1) x P_n(x) - nP_{n-1}(x)
$$

그리고 점화식의 초기값은 $P_0(x) = 1$, $P_1(x) = x$을 사용 합니다.

점화식으로 $P_2(x)$를 유도해봅시다.

$$
\begin{aligned}
2 P_2(x) &= 3 \cdot x \cdot x - 1 \cdot 1  \\
P_2(x) &= \frac{1}{2} \left(3x^2 - 1\right)
\end{aligned}
$$

