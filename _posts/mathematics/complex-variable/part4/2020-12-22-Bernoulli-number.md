---
title: "Bernoulli Number derived by Maclaurin series"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<span class="statement-title">problem</span>.<br>

<div class="statement" markdown="1">
The Maclaurin series

$$
\frac{z}{e^z - 1} = 1 + B_1 z + \frac{B_2}{2!} z^2 + \frac{B_3}{3!} z^3 + \cdots
$$

defines the Bernoulli numbers $B_n$. Evaluate $B_1$, $B_2$, $B_3$, ...

</div>

<br>

<span class="statement-title">solution</span>.<br>

<div class="math-statement" markdown="1">
우리가 확실히 아는 Series에서 출발한다.

$$
\begin{aligned}
  e^z &= 1 + z + \frac{z^2}{2!} + \frac{z^3}{3!} + \cdots \\
  e^z - 1 &= z + \frac{z^2}{2!} + \frac{z^3}{3!} + \cdots \\
  \frac{e^z-1}{z} &= 1 + \frac{z}{2!} + \frac{z^2}{3!} + \cdots
\end{aligned}
$$

이때, 두 Sereis를 곱해주면 아래와 같다.

$$
\begin{aligned}
  \frac{e^z-1}{z} \cdot \frac{z}{e^z-1} = 1
\end{aligned}
$$

위의 식은 두 Seires의 곱이 상수텀을 제외하고는 모두 0의 계수를 가짐을 보여준다. 따라서

$$
\begin{aligned}
  \frac{e^z-1}{z} \cdot \frac{z}{e^z-1} = \left( 1 + \frac{z}{2!} + \frac{z^2}{3!} + \cdots \right) \cdot \left( 1 + B_1 z + \frac{B_2}{2!} z^2 + \frac{B_3}{3!} z^3 + \cdots \right) = 1
\end{aligned}
$$

따라서

$$
\begin{aligned}
  1&: 1 \cdot 1 = 1 \\
  z&: 1 \cdot B_1 + \frac{1}{2!} \cdot 1 = 0 &\implies B_1 = -\frac{1}{2}\\
  z^2&: 1 \cdot \frac{B_2}{2!} + \frac{1}{2!} \cdot B_1 + \frac{1}{3!} \cdot 1 = 0 &\implies B_2 = \frac{1}{6} \\
  \vdots
\end{aligned}
$$

이와 같은 방식을 통해 Bernoulli number $B_n$을 유도할 수 있다! $\blacksquare$

</div>

<br>

이외에도 Bernoulli number $B_n$을 유도하는 다양한 방법들이 있고, 많은 배경들이 있다 :)


