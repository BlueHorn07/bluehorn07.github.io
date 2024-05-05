---
title: "Taylor Series & Macluarin Series"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "실변수 함수 $f(x)$를 다항 함수의 멱급수로 표현하기. $n$차 근사를 무한번 수행한 것과 같다."
---

"미적분학” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Calculus]({{"/category/calculus" | relative_url}}) 페이지에서 확인하실 수 있습니다 📐

- 함수의 멱급수 표현
- Maclaurin Series
- Taylor Series
- Examples
- Why Taylor Series?

<hr/>

# 함수의 멱급수 표현

일반적으로 우리가 만나는 함수들은 아래와 같은 형태다.

$$
f(x) = 1 + 3x + 4x^5
$$

다항식의 합으로 구성되거나

$$
f(x) = \frac{1}{1 + x}
$$

분모의 형태이거나

$$
f(x) = \sqrt{x + 5}
$$

무리수 함수일 것이다. 이 함수들의 공통점은 모두 실변수 함수라는 것이다.

만약 실변수 함수 $f(x)$가 $x = x_0$에서 무한번 미분 가능한 함수라면, 이 함수를 <span class="red">**다항식의 무한합**</span>으로 표현할 수 있다. 예를 들어, 함수 $f(x) = e^x$는 아래와 같이 표현할 수도 있다!

$$
f(x) = e^x = \sum_{k=0}^{\infty} \frac{x^k}{k!}
$$

이렇게 함수 $f(x)$를 <span class="red">**$x^k$의 멱급수**</span> 형태로 표현하는 것을 **\<테일러 급수; Taylor Series\>**라고 한다.

<hr/>

# Maclaurin Series

\<테일러 급수; Taylor Series\>를 살펴보기 전에, 조금더 쉬운 개념인 **\<매크로린 급수; Maclaurin Series\>**를 먼저 살펴보자.

기본적인 아이디어는 '접선'을 통해 함수를 근사하는 <span class="red">**선형 근사(Linear Approximation)**</span>이다.

함수 $f(x)$를 선형 근사한 함수 $L(x)$는 아래와 같다. 표기의 편의를 귀해 $x = 0$ 지점에서 선형 근사를 수행하겠다.

$$
L(x) = f(0) + f'(0) x
$$

2차식으로 근사하는 2차 근사(Quadratic Approximation)도 해보자.

$$
Q(x) = f(0) + f'(0) x + \frac{f''(0)}{2!} x^2
$$

$x^2$의 계수에서 분모에 $2!$가 붙는 이유는 근사식 $Q(x)$를 2차 미분 했을 때, $Q^{\prime\prime}(0) = f^{\prime\prime}(0)$가 되어야 하기 때문이다.

1차, 2차 근사에서 보이는 패턴을 바탕으로 $k$차 근사식을 유도하면 아래와 같다.

$$
f(0) + f'(0) x + \frac{f''(0)}{2!} x^2 + \frac{f'''(0)}{3!} x^3 + \cdots \frac{f^{k}(0)}{k!} x^k
$$

$k$차 근사에서 더 나아가 무한번 근사를 하면 \<Maclaurin Series\>가 된다!

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Maclaurin Series<br>

For real-valued function $f(x)$, if it can be infinitely differential on $x = 0$, then we can represent $f(x)$ as a power series near the $x = 0$.

$$
\begin{aligned}
f(x)
&= f(0) + f'(0) x + \frac{f''(0)}{2!} x^2 + \cdots \frac{f^{k}(0)}{k!} x^k \cdots \\
&= \sum^\infty_{n=0} \frac{f^{(n)}(x_0)}{n!} (x - x_0)^n
\end{aligned}
$$

</div>

<hr/>

# Taylor Series

**\<테일러 급수: Taylor Series\>**는 이런 멱급수 근사를 $x = 0$가 아닌 $x = x_0$에서 수행한 것이다. \<Maclaurin Series\>의 일반화 버전이다. \<테일러 전개; Taylor Expansion\>라고도 한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Taylor Series<br>

For real-valued function $f(x)$, if it can be infinitely differential on $x = x_0$, then we can represent $f(x)$ as a power series near the $x = x_0$.

$$
\begin{aligned}
T(x)
&= f(x_0) + f'(x_0) (x-x_0) + \frac{f''(x_0)}{2!} (x-x_0)^2 + \cdots \frac{f^{k}(x_0)}{k!} (x-x_0)^k \cdots \\
&= \sum^\infty_{n=0} \frac{f^{(n)}(x_0)}{n!} (x - x_0)^n
\end{aligned}
$$

</div>

<hr/>

# Examples

테일러 함수로 표현되는 대표적인 함수들을 살펴보자.

- $1 / (1-x)$
- $e^x$
- $\sin x$

## Fractional Function

Fractional function $1 / (1-x)$에 대한 테일러 급수는 아래와 같다.

$$
\left(\frac{1}{1-x}\right)^{(n)} = n! \cdot \frac{1}{(1-x)^n}
$$

가 됨을 기억하자.

<div class="notice" markdown="1">

$$
\begin{aligned}
\frac{1}{1-x}
&= 1 + x + x^2 + x^3 + \cdots \\
&= \sum^{\infty}_{n=0} x^k
\end{aligned}
$$

단, 이 근사는 $-1 < x < 1$ 범위에서만 유효하다.

</div>

## Exponential Function

Exponential function $e^x$에 대한 테일러 급수는 아래와 같다.

$$
(e^x)' = e^x
$$

가 됨을 기억하자.

<div class="notice" markdown="1">

$$
\begin{aligned}
e^x
&= 1 + 1 x + \frac{1}{2!}x^2 + \cdots \frac{1}{k!} x^k + \cdots \\
&= \sum^{\infty}_{n=0} \frac{x^n}{n!}
\end{aligned}
$$

</div>

## Sine Function

Sine function $\sin x$에 대한 테일러 급수는 아래와 같다.

<div class="notice" markdown="1">

$$
\begin{aligned}
\sin x
&= 0 + 1 x + 0 - \frac{1}{3!}x^3 + \cdots \\
&= \sum^{\infty}_{n=0} (-1)^{n} \frac{x^{(2n + 1)}}{(2n + 1)!}
\end{aligned}
$$

</div>

<hr/>

# Why Taylor Series?

실변수 함수 $f(x)$를 테일러 전개하면 그 함수의 다항식 표현을 알 수 있습니다. 우리가 다항 함수에 대해선 쉽게 다룰 수 있기 때문에 복잡하고 어려운 함수를 쉬운 버전으로 바꿀 수 있습니다!

예시는 ['다크 프로그래머'님의 포스트](https://darkpgmr.tistory.com/59)를 참고했음을 미리 밝힙니다 🙏

## 적분 계산

적분 계산이 어려운 함수를 테일러 전개할 수 있다면, 적분이 훨씬 쉬워집니다.

$$
\int \sin (x^2) \; dx = \int \left( x^2 - \frac{x^6}{3!} + \frac{x^{10}}{5!} - \cdots \right) \; dx
$$

## 함수의 점근 특성 파악

복잡한 함수들의 점근적(asymptotic) 특성을 쉽게 파악할 수 있습니다.

$$
\lim_{x \rightarrow 0} \frac{\sin x}{x} = \lim_{x \rightarrow 0} \frac{x - x^3 / 3! + x^5 / 5! - \cdots }{x} = \lim_{x \rightarrow 0} \frac{x}{x} = 1
$$

## 컴퓨터의 초월함수 계산

초월함수 $\sin x$, $e^x$ 등을 Bit 계산을 하는 컴퓨터가 계산하는 것은 어렵습니다. 그러나 초월함수를 테일러 전개해 다항 함수로 만들면, 컴퓨터가 계산하기 쉬운 형태가 됩니다. 컴퓨터로 초월함수를 $n$차까지 근사한 수 계산하면 매우 정밀한 결과를 얻을 수 있습니다.

<hr/>

# 맺음말

이번 포스트에선 \<테일러 급수\>와 \<매크로린 급수\>를 소개하는 선에서 마무리하고자 한다. 이런 \<테일러 급수\>가 존재하는지에 대해 논의하는 **\<테일러 정리; Taylor Theorem\>**는 별도의 포스트에서 다루겠다.

주기 함수를 $\sin$, $\cos$의 무한합으로 근사하는 \<푸리에 급수; Fourier Series\>도 있다. \<테일러 급수\>와 마찬가지로 무한합의 계수 $a_n$ 값을 찾는 방식으로 근사를 수행한다.

👉 [Fourier Series]({{"/2021/06/08/fourier-series" | relative_url}})

<hr/>

# References

- ['다크 프로그래머'님의 포스트](https://darkpgmr.tistory.com/59)
- [Brilliant: Maclaurin Series](https://brilliant.org/wiki/maclaurin-series/)
- [Brilliant: Taylor Series](https://brilliant.org/wiki/taylor-series/)