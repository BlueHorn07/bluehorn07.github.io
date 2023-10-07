---
title: "Fourier Series"
toc: true
toc_sticky: true
categories: ["Differential Equations"]
---


2019-2학기, 대학에서 들은 '미분방정식' 수업을 복습하는 차원에서 정리하게 된 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- [Introduction to Fourier Series](#introduction-to-fourier-series)
  - fundamental period
  - orthogonality for functions
- [Fourier Series](#fourier-series)
  - Fourier Coefficients
- (next) [Fourier Series (Complex Representation)]({{"/2021/06/09/fourier-series-complex-representation" | relative_url}})

<hr/>

### Introduction to Fourier Series

\<푸리에 급수; Fourier Series\>에 대해 다루기 전에 이것과 비슷한 \<테일러 급수; Taylor Series\>에 대해 잠깐 언급하고 넘어가자.

미적분학에서 임의의 함수 $f(x)$는 다항식의 合 형태로 근사(approximate)할 수 있었다.

<div class="statement">

"Every smooth function can be locally approximated by low dimentional polynomials!"<br/>
<small style="color: grey">-- Talyor's Theorem</small>

</div>

이번에 살펴볼 \<푸리에 급수\>에 따르면, 함수 $f(x)$가 **piecewise continuous**하고 **periodic function**일 때, $\sin$, $\cos$ 함수로 적절히 근사할 수 있다!

<div class="statement">

"A periodic function $f(x)$ which is reasonably continuous may be expressed as the sum of a series of sine or cosine terms, each of which has specific AMPLITUDE and PHASE coefficients known as Fourier coefficients."<br/>
<small style="color: grey">-- Fourier's Theorem</small>
</div>

\<푸리에 급수\>를 본격적으로 다루기 전에 몇가지 사전 지식을 살펴보자. 그리 어렵지도 않다 ㅎㅎ

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> fundamental period<br>

If a function $f(x)$ is

$$
f(x) = f(x + T) \quad \text{for some} \; T > 0
$$

then $f(x)$ is a \<**periodic function**\>.

이때, periodic function의 주기(period) $T$ 중 가장 작은 값을 \<**fundamental period**\>라고 한다.

</div>

당연해 보이는 개념이긴 하지만 \<푸리에 급수\>를 정의하는데 \<fundamental period\>의 개념이 사용된다. 😁

삼각함수를 예로 들어 \<fundamental period\>가 어떻게 등장하는지 살펴보자.

<div class="notice" markdown="1">

1\. $\cos(x)$

fundamental period: $2\pi$

2\. $\cos(2\pi x)$

fundamental period: $1$

3\. $\cos(2\pi x / L)$

fundamental period: $L$

세 번째 식의 형태에 익숙해지는 게 좋다. 위의 식을 기준으로 모든 삼각함수의 \<fundamental period\>를 구한다.

ex1: $\cos (5 x)$ → ($5 = 2\pi/L$) → ($L = 2 \pi / 5$)

ex2: $\cos (7\pi x)$ → ($7\pi = 2\pi/L$) → ($L = 2 / 7$)

</div>

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> orthogonality for functions<br>

vector의 직교성(orthogonality)은 "내적(inner product) 값이 0이 됨"으로 정의했었다.

\<푸리에 급수\>에서는 함수 $u$, $v$에 대한 직교성을 아래와 같이 적분에 의해 정의한다!

$$
(u, v) = \int_{\alpha}^{\beta} u(x) v(x) \; dx
$$

이때, $[\alpha, \beta]$는 함수 $u$, $v$가 정의되는 구간이다.

만약, 적분 $(u, v)$의 값이 0이라면, 함수 $u$, $v$가 서로 **직교**한다고 한다.

</div>

삼각함수에서의 직교성(orthogonality)를 확인해보자. \<푸리에 급수\>를 전개하는 데도 자주 사용할 성질들이니 눈여겨 보자!

<div class="notice" markdown="1">

1\. $\cos$ and $\cos$

$$
\int_{-L}^L \cos \frac{m\pi x}{L} \cos \frac{n\pi x}{L} \; dx = \begin{cases}
  0 & m \ne n \\
  L & m = n
\end{cases}
$$

2\. $\sin$ and $\sin$

$$
\int_{-L}^L \sin \frac{m\pi x}{L} \sin \frac{n\pi x}{L} \; dx = \begin{cases}
  0 & m \ne n \\
  L & m = n
\end{cases}
$$

3\. $\cos$ and $\sin$

$$
\int_{-L}^L \cos \frac{m\pi x}{L} \sin \frac{n\pi x}{L} \; dx = 0
$$

즉, $n=m$ 경우만 제외하고는 모두 직교한다고 보면 된다!!

</div>

이제 \<푸리에 급수\>를 이해하기 위한 모든 것을 살펴봤다. 생각보다 그렇게 많은 준비가 필요하진 않았다 😊

<hr/>

### Fourier Series

<div class="notice" markdown="1">

$$
f(x) = \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos \frac{m\pi x}{L} + b_m \sin \frac{m\pi x}{L} \right)
$$

</div>

위의 식은 주기 $2L$을 갖는 주기함수 $f(x)$를 \<푸리에 급수\> 형태로 근사했을 때의 형태다. 지금부터 우리의 목표는 <span style="color: red">위의 근사식에 존재하는 모든 계수 $a_n$, $b_n$의 값을 결정하는 것</span>이다!! 그리고 그 과정에서 쓰는 테크닉은 \<**직교성; Orthogonality**\>, 단 하나 뿐이다! 😉

<div class="math-statement" markdown="1">

1\. $a_0$ 결정

\<푸리에 급수\>의 양변에 주기 $2L$ 만큼 적분을 취한다.

$$
\int_{-L}^L f(x) \; dx = \int_{-L}^L \left[ \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos \frac{m\pi x}{L} + b_m \sin \frac{m\pi x}{L} \right) \right] \; dx
$$

먼저, $\sum$ 안에 들어있는 식은 적분 과정에서 모두 0이 된다. 따라서, 아래와 같이 간단한 식을 얻는다.

$$
\int_{-L}^L f(x) \; dx = \int_{-L}^L \frac{a_0}{2} \; dx
$$

식을 정리하면,

$$
\int_{-L}^L \frac{a_0}{2} \; dx = 2L \cdot \frac{a_0}{2} = a_0 L = \int_{-L}^L f(x) \; dx
$$

따라서, 계수 $a_0$는

$$
a_0 = \frac{1}{L} \int_{-L}^L f(x) \; dx
$$

$\blacksquare$

</div>

<div class="math-statement" markdown="1">

2\. $a_n$ 결정

양변에 $\cos (n\pi x/L)$를 곱한 후, 적분

$$
\begin{aligned}
&\int_{-L}^L f(x) \cos \frac{n\pi x}{L} \; dx \\
&= \int_{-L}^L \left[ \frac{a_0}{2} \cos \frac{n\pi x}{L} + \sum_{m=1}^{\infty} \left( a_m \cos \frac{m\pi x}{L} \cos \frac{n\pi x}{L} + b_m \sin \frac{m\pi x}{L} \cos \frac{n\pi x}{L} \right) \right] \; dx
\end{aligned}
$$

이때, introduction에서 살펴본 삼각함수의 직교성에 의해 아래와 같이 간단한 식을 얻는다.

$$
\int_{-L}^L f(x) \cos \frac{n\pi x}{L} \; dx
= \int_{-L}^L a_n \cos^2 \frac{n\pi x}{L} \; dx = a_n L
$$

따라서, 계수 $a_n$은

$$
a_n = \frac{1}{L} \int_{-L}^L f(x) \cos \frac{n\pi x}{L} \; dx
$$

$\blacksquare$

</div>

<div class="math-statement" markdown="1">

3\. $b_n$ 결정

양변에 $\sin (n\pi x / L)$을 곱한 후, 적분

$$
\begin{aligned}
&\int_{-L}^L f(x) \sin \frac{n\pi x}{L} \; dx \\
&= \int_{-L}^L \left[ \frac{a_0}{2} \sin \frac{n\pi x}{L} + \sum_{m=1}^{\infty} \left( a_m \cos \frac{m\pi x}{L} \sin \frac{n\pi x}{L} + b_m \sin \frac{m\pi x}{L} \sin \frac{n\pi x}{L} \right) \right] \; dx
\end{aligned}
$$

$a_n$의 경우와 마찬가지로, 삼각함수의 직교성을 적용하면

$$
b_n = \frac{1}{L} \int_{-L}^L f(x) \sin \frac{n\pi x}{L} \; dx
$$

따라서, 계수 $b_n$은

$$
b_n = \frac{1}{L} \int_{-L}^L f(x) \sin \frac{n\pi x}{L} \; dx
$$

$\blacksquare$

</div>

야호! \<푸리에 급수\>로 근사하기 위해 필요한 계수에 대한 공식을 모두 얻었다. 이제 이 공식에 맞춰서 주기함수 $f(x)$에 대한 \<푸리에 급수\>를 유도하면 된다 😁

<hr/>

<div class="example" markdown="1">

<span class="statement-title">Example.</span><br>

(바빠서 패-스)

</div>

<hr/>

이어지는 포스트에서는 \<푸리에 급수\>를 복소지수 형태로 표현하는 방식에 대해 살펴볼 것이다. \<푸리에 변환\>을 기술할 때 주로 이 복소지수 형태를 사용하기 때문에, \<푸리에 변환\>을 공부하려면 꼭 미리 알고 있어야 한다.

👉 [Fourier Series (Complex Representation)]({{"/2021/06/09/fourier-series-complex-representation" | relative_url}})


