---
title: "Non-elementaryIntegrals"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "무한개의 초등 함수의 급수로 표현되는 함수들: Error Function, Sinc Function, Epllitical Integrals, Gamma Function"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. 공부하면서 재밌어 보였던 문제들과 풀이들을 모아서 정리한 포스트 입니다.
{: .notice--info}

# Elementary Function이란?

Elementary Functions, 초등 함수란 "다항함수, 지수함수와 그 역함수인 로그함수, 그리고 이를 **함수의 합성**과 **사칙연산**을 통해 얻는 모든 함수"를 초등함수라고 한다.

여기에 삼각함수(Trionometric Functions)도 초등 함수에 포함되는데, $\cos x = \frac{e^{ix} + e^{-ix}}{2}$, $\sin x = \frac{e^{ix} - e^{-ix}}{2}$로 표현되기 때문이다. 즉, 초등 함수를 분류할 때, 함수가 복소함수인지 여부는 중요하지 않은 것.

만약 ElementaryFunction을 미분한다면, 그 도함수는 여전히 ElementaryFunction이다. 그러나, **ElementaryFunction을 적분한 적분 함수는 ElementaryFunction이 안 될 수도 있다!** (즉, 적분 연산은 ElementaryFunction에 대해서 닫힌 연산이 아닌 것.)

# Non-elementaryIntegrals

Non-elementaryIntegrals의 정의는 아래와 같다.

주어진 ElementaryFunction의 적분이 ElementaryFunction이 되지 않는 경우를 말한다. 즉, 적분 함수가 무한 급수 꼴로 표현되거나, 어떤 초등함수의 조합으로도 표현할 수 없다.
{: .notice}

생각보다 이런 경우는 많다!! 이번 포스트에선 요런 non-elementaryintegral의 몇몇 사례를 살펴본다. 구체적인 내용은 별도의 포스트에서 다루겠다.

# List of non-elementaryIntegrals

## Error Function

<div class="notice" markdown="1">

$$
\textbf{erf}(x) = \frac{2}{\sqrt{\pi}} \int_0^x e^{-t^2} \, dt
$$

</div>

요 함수의 improper integral(이상 적분)의 값은 몇가지 테크닉을 사용해 쉽게(?) 계산할 수 있다.

$$
\int_0^{\infty} e^{-t^2} \, dt = \sqrt{\pi} / 2
$$

<!-- TODO -->
<!-- ### 왜 에러 함수라고 불리는가? -->

### Gaussian Distribution

Error Function $\textbf{erf}(x)$는 Gaussian Distribution의 특수한 형태다.

$$
f(x; \mu, \sigma^2) = \frac{1}{\sqrt{2\pi\sigma^2}} \exp \left( - \frac{(x-\mu)^2}{2\sigma^2}\right)
$$

자세한 내용은 "확률과 통계(MATH230)" 수업 들을 때 정리했던 블로그 포스틀 참고하자. [링크](https://bluehorn07.github.io/2021/03/30/normal-distribution/)

## Sine-Integral Function

<div class="notice" markdown="1">

$$
\textbf{Si}(x) = \int_{0}^{x} \frac{\sin t}{t} \, dt
$$

</div>

평범한 $\sin x$ 함수는 쉽게 적분할 수 있지만, $x$로 나눈 $\textbf{Si}(x)$ 함수는 쉽게 적분되지 않는다고 한다. 읽을 때는 싱크(sinc) 함수라고 읽는다.

푸리에 변환을 할 때 정말 많이 보게 될 함수라고 한다.

## Elliptical Integrals

<div class="notice" markdown="1">

$$
\int \sqrt{1 - k^2 \sin^2 x} \, dx \;\; (0 \le k^2 \le 1)
$$

</div>

본인은 처음에 요 적분을 **$\sin x$ 함수의 arc length**를 구하려고 할 때 만났다. Arc Length 공식에 따라 $\sin x$의 적분을 유도하면 아래와 같다.

$$
\begin{aligned}
\int \sqrt{1 + f'(x)^2} \, dx &= \int \sqrt{1 + \cos^2 x} \, dx \\
= \int \sqrt{1 + (1 - \sin^2 x)} \, dx &= \sqrt{2} \cdot \int \sqrt{1 - \frac{1}{2} \sin^2 x} \, dx
\end{aligned}
$$

즉, 적분 부분이 위에 제시된 Elliptical Integrals의 형태가 된다.

요 적분은 본래 타원 $x^2/a^2 + y^2/b^2 = 1$의 길이를 구하기 위해 처음 제안 되었다. 실제로 타원에 대한 Arc Length를 구하려고 하면, 저런 적분식을 얻을 수 있다.

## Gamma Function

<div class="notice" markdown="1">

$$
\Gamma(x) = \int_0^{\infty} t^{x-1}e^{-t} \, dt \; (x > 0)
$$

</div>

$\Gamma(x) = x \cdot \Gamma(x-1)$라는 성질을 만족하는 함수다. 팩토리얼 $n!$을 자연수에서 실수와 복소수 영역으로 확장시킨 함수다.

요 감마 함수를 기반으로 하는 연속 확률 분포인 "Gamma Distribution"라는 것도 있다. 자세한 내용은 "확률과 통계(MATH230)" 수업 들을 때 정리 했던 블로그 포스트를 참고. [Link](https://bluehorn07.github.io/2021/04/05/gamma-distribution/)
