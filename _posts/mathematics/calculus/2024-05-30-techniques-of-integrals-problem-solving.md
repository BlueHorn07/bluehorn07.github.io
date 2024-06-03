---
title: "Techniques of Integrals: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "Washer Method와 Shell Method는 동치다. 점화식으로 유도되는 적분"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. 공부하면서 재밌어 보였던 문제들과 풀이들을 모아서 정리한 포스트 입니다.
{: .notice--info}

# Equivalence of the washer and shell methods

Wahser 방식과 Shell 방식이 동치임을 밝히는 문제다. Thomas Calculus 연습문제로 나왔는데, 요건 *American Mathematical Monthly*에서 문제를 발췌한 문제였다.

일단 Washer 방식으로 구한 부피는 아래와 같다.

$$
W_v = \int_{f(a)}^{f(b)} \pi \left((f^{-1}(y))^2 - a^2\right) dy
$$

그리고 Shell 방식으로 구한 부피는 아래와 같다.

$$
S_v = \int_{a}^{b} 2 \pi x (f(b) - f(x)) dx
$$

증명해보기 위해 Shell Method의 식에 치환적분을 할 것이다!

Let $f(x) = y$, then

$$
\begin{aligned}
f'(x) dx &= dy \\
\frac{1}{\left(f^{-1}(y)\right)'} dx &= dy \\
dx &= \left(f^{-1}(y)\right)' dy
\end{aligned}
$$

적분을 치환하면...

$$
\begin{aligned}
&\int_{a}^{b} 2 \pi x (f(b) - f(x)) dx \\
&= \int_{f(a)}^{f(b)} 2 \pi \cdot f^{-1}(y) \cdot (f(b) - y) \cdot \left(f^{-1}(y)\right)' dy \\
&= \int_{f(a)}^{f(b)} 2 \pi \cdot f^{-1}(y) \cdot \left(f^{-1}(y)\right)' \cdot (f(b) - y) ' dy
\end{aligned}
$$

이제 $f(b)$ 파트와 $y$가 있는 파트를 각각 계산해보자.

<$f(b) 파트$>

$$
\begin{aligned}
&\int_{f(a)}^{f(b)} 2 f^{-1}(y) \cdot \left(f^{-1}(y)\right) dy \\
&= \left[ \left(f^{-1} (y) \right)^2 \right]_{f(a)}^{f(b)} \\
&= b^2 - a^2
\end{aligned}
$$

<$y$ 파트>

$$
\begin{aligned}
&\int_{f(a)}^{f(b)} 2 f^{-1}(y) \cdot \left(f^{-1}(y)\right) y \, dy \\
&= \left[ (f^{-1}(y))^2 y \right]_{f(a)}^{f(b)} - \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy \\
&= b^2 f(b) - a^2 f(a) - \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy
\end{aligned}
$$

이제 식을 합쳐서 정리해보자! (상수 $\pi$는 생략 했다..)

$$
\begin{aligned}
&\int_{f(a)}^{f(b)} 2 \pi \cdot f^{-1}(y) \cdot \left(f^{-1}(y)\right)' \cdot (f(b) - y) ' dy \\
&= \left(b^2 - a^2\right) f(b) - (b^2 f(b) - a^2 f(a)) + \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy \\
&= a^2 (f(a) - f(b)) + \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy
\end{aligned}
$$

요렇게 정리 된다! 이때, 왼쪽 부분을 적분 형태로 되돌린다면 최종적으로 이련 형태 된다. (상수 $\pi$를 다시 붙임..)

$$
\begin{aligned}
&\int_{a}^{b} 2 \pi x (f(b) - f(x)) dx \\
&= \pi a^2 (f(a) - f(b)) + \pi \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy \\
&= \pi \int_{f(a)}^{f(b)} (f^{-1}(y))^2 \, dy - \int_{f(a)}^{f(b)} \pi a^2 \\
&= \int_{f(a)}^{f(b)} \pi \left((f^{-1}(y))^2 - a^2\right) dy
\end{aligned}
$$

와우! 처음에 Washer 방법으로 유도한 부피 공식과 동일해졌다!! $\blacksquare$


# Integration by Parts

부분 적분을 말한다.

## Integral of $e^x \cos x$

$$
\int e^x \cos x \, dx
$$

를 구해보자.

부분적분을 활용한다.

$$
\int e^x \cos x \, dx
= e^x (- \sin x) + \int e^x \sin x \, dx
$$

이때, $\int e^x \sin x dx$를 구하면 아래와 같다.

$$
\int e^x \sin x \, dx
= e^x \cos x - \int e^x \cos x \, dx
$$

이제 요 식을 대입해보자!!


$$
\begin{aligned}
\int e^x \cos x \, dx
&= e^x (- \sin x) + \int e^x \sin x \, dx \\
&= e^x (- \sin x) + (e^x \cos x - \int e^x \cos x \, dx)
\end{aligned}
$$

좌우의 식을 정리하면 아래와 같다.

$$
\int e^x \cos x \, dx = \frac{e^x(\cos x - \sin x)}{2}
$$

$e^x \cos x$의 적분을 하는데, $e^x \sin x$의 적분이 필요한 재밌는 적분이다. $\blacksquare$

## Integral of $\cos^n x$

$$
\int \cos^n x \, dx
$$

를 구해보자.

일단 부분 적분을 적용한다. 이때, $\cos^n x$를 분해해야 하는데, 아래와 같이 분해한다!

$$
\cos^n x = (\cos^{n-1} x) \cdot (\cos x)
$$

이제 요걸 기준으로 부분적분을 하면 된다. 일단 $\cos^{n-1} x$를 적분하는 건 모르기 때문에 요 걸 미분하는 함수로 두고 적분을 하자.

$$
\int \cos^n x \, dx
= \cos^{n-1} x \cdot \sin x - \int (n-1) \cos^{n-2} x (- \sin x) \cdot \sin x\, dx
$$

우변의 적분 파트를 자세히 보면, $\sin^2 x$가 보인다. 요걸 $1 - \cos^2 x$로 치환해주자.

$$
\begin{aligned}
&\int \cos^n x \, dx \\
&= \cos^{n-1} x \cdot \sin x + \int (n-1) \cos^{n-2} x \cdot \sin^2 x \, dx \\
&= \cos^{n-1} x \cdot \sin x + (n-1) \int \cos^{n-2} x \cdot (1 - \cos^2 x) \, dx \\
&= \cos^{n-1} x \cdot \sin x + (n-1) \int \cos^{n-2} x \, dx - (n-1) \int \cos^n x \, dx
\end{aligned}
$$

우변에 $\cos^{n-2} x$에 대한 적분도 있지만, 우리가 구하려는 $\cos^n x$의 적분이 그대로 있다! 식을 다시 정리해서 우변에 있는 $\cos^n x$의 적분을 정리하면 아래와 같다.

$$
\int \cos^n x \, dx
= \frac{1}{n} \left( \cos^{n-1} x \cdot \sin x + (n-1) \int \cos^{n-2} x \, dx \right)
$$

뭔가 식이 덜 정리된 것 같지만 이제 최종적인 형태다...! 적분이 일종의 점화식(recurrence relation)으로 나오는데, 적분 함수는 $I(n)$으로 두고 정리하면 아래와 같다.

$$
I(n) = \frac{1}{n} \left( \cos^{n-1} x \cdot \sin x + (n-1) \cdot I(n-2) \right)
$$

적분 함수가 점화식으로 유도되는게 신기했던 적분이다. $\blacksquare$

## Intergal of $(1 - x^2)^n$

$$
\int_{-1}^{1} (1 - x^2)^n \, dx
$$

앞에서 봤던 $\cos^n x$의 적분처럼 어떤 함수를 $n$ 제곱한 꼴의 적분이다. 신기한 점은 $x = \sin t$라고 생각하면, 요건 $\cos^{2n+1} t$의 적분이 된다!! ($x = \cos t4$로 두고 풀어도 무방하다.)

$$
\begin{aligned}
&\int_{-1}^{1} (1 - x^2)^n \, dx \\
&= \int_{-\pi/2}^{\pi/2} (1 - \sin^2 t)^n \cdot \cos t \, dt  \\
&= \int_{-\pi/2}^{\pi/2} (\cos^2 t)^n \cdot \cos t \, dt  \\
&= \int_{-\pi/2}^{\pi/2} \cos^{2n+1} t \, dt  \\
\end{aligned}
$$

요건 위에서 식을 구했으니 바로 풀어보자.

$$
I(2n + 1) = \frac{1}{2n+1} \left(\cos^{2n} x \cdot \sin x + 2n \cdot I(2n-1)\right)
$$

이때, 적분 범위가 $(-\pi/2, \pi/2)$로 지정 되었으므로 $\cos^{2n} x \cdot \sin x$ 파트를 계산할 수 있는데, 요 값은 $\cos^{2n} (\pi/2) = \cos^{2n} (- \pi/2) = 0$이므로 $0$이다. 따라서 점화식이 아래와 같이 간결해진다.

$$
I(2n + 1) = \frac{2n}{2n+1} I(2n - 1)
$$

점화식을 전개하면 아래와 같은데,

$$
I(2n + 1) = \frac{2n}{2n+1} \cdot \frac{2n - 2}{2n - 1} \cdots \frac{2}{3} \cdot I(0)
$$

이때, $I(1) = \int_{-\pi/2}^{\pi/2} \cos x \, dx = 2$이므로 점화식은 아래와 같이 표현된다.

$$
I(2n + 1) = \frac{(2n)!!}{(2n+1)!!} \cdot 2
$$

$\cos^n x$에 대한 적분의 점화식을 한번더 만났던 재밌는 문제다 ㅎㅎ 풀이를 찾아보니 무슨 르장드르 다항식 얘기도 나오던데, 아직 해석학을 안 들어서 무슨 말인지 모르겠다...;;

# Differential Equations

## Solve $g'(x) = 1 + [g(x)]^2$

$g(0) = 0$이고, $g'(x) = 1 + [g(x)]^2$인 미분 방정식을 풀어보자.

$y = g(x)$로 두고, 식을 $dy/dx$ 꼴로 바꿔보자.

$$
\frac{dy}{dx} = 1 + y^2
$$

식을 $dy$, $dx$ 기준으로 다시 정리하면

$$
\frac{dy}{1+y^2} = dx
$$

이제 양변을 적분하면

$$
\tan^{-1} (y) = \tan^{-1} g(x) = x + C
$$

이때, $g(0) = 0$이므로 $\tan^{-1}g(0) = 0 + C$, 즉 적분상수 $C = 0$이다.

식을 매끄럽게 정리하면, $g(x) = \tan x$가 된다. $\blacksquare$

아직 미방 수업을 다시 안 봐서 그런지 저런 미분방정식 푸는게 익숙하지 않구먼...;;

