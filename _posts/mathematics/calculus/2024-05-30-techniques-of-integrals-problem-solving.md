---
title: "Techniques of Integrals: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
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

