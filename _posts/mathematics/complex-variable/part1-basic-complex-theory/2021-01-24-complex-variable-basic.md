---
title: "Complex Variable - Basic"
layout: post
use_math: true
tags: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Triangle inequality
- Euler’s formula
- de Moivre’s formula
- $n$-th root of $w$
- $\epsilon$-neighborhood of $z_0$
- Interior / Exteriror / Boundary
- Open / Closed set


<hr>

복소수에 대한 정말 기초적인 내용은 생-략

<br><span class="statement-title">Definition.</span> Triangle inequality<br>

<div class="statement" markdown="1">

For $z_1, z_2 \in \mathbb{C}$,

$$
\left| z_1 + z_2 \right| \le \left| z_1 \right| + \left| z_2 \right|
$$

자주 쓰진 않지만, 또 다른 버전도 있다.

$$
\left| z_1 \right| - \left| z_2 \right| \le \left| z_1 - z_2 \right|
$$

<details markdown="1">
<summary>유도</summary>

$$
\begin{aligned}
    \left| (z_1 - z_2) + z_2 \right| &\le \left| z_1 - z_2\right| + \left| z_2 \right| \\
    \left| z_1 \right| - \left| z_2 \right| &\le \left| z_1 - z_2\right|
\end{aligned}
$$

</details>

</div>

<br>

<br><span class="statement-title">Formula.</span> Euler's formula<br>

<div class="statement" markdown="1">

$$
e^{i \theta} = \cos \theta + i \sin \theta
$$

<details markdown="1">
<summary>유도</summary>

$e^x$를 테일러 전개하면 아래와 같다.

$$
e^x = 1 + x + \frac{x^2}{2!} + \frac{x^3}{3!} + \cdots \frac{x^n}{n!} + \cdots
$$

이때 $x$에 $i\theta$를 대입하면,

$$
e^{i\theta} = 1 + i\theta + \frac{(-1)\theta^2}{2!} + \frac{i \theta^3}{3!} + \frac{\theta^4}{4!} + \cdots + 
$$

위의 식에서 홀수-번째 텀만 모은 것이 $\cos \theta$이고, 짝수-번재 텀만 모은 것이 $i \sin \theta$가 된다. $\blacksquare$

</details>

</div>

<br>

<br><span class="statement-title">Formula.</span> de Moivre's formula<br>

<div class="statement" markdown="1">

$$
e^{in\theta} = (\cos \theta + i \sin \theta)^n = \cos n\theta + i \sin n\theta 
$$

</div>

<br><span class="statement-title">Exercise.</span><br>

<div class="math-statement" markdown="1">

Derive

$$
\cos 5x = 16 \cos^5 x - 20 \cos^3 x + 5 \cos x
$$

</div>

<details markdown="1">
<summary>Solution.</summary>

de Moivre's formula를 사용한다.

$$
(\cos x + i \sin x)^5 = \cos 5x + i \sin 5x
$$

따라서

$$
\cos 5x = \textrm{Re} \left( (\cos x + i \sin x)^5 \right) \\
$$

$$
\begin{aligned}
(\cos x + i \sin x)^5 &= \left((\cos x + i \sin x)^2\right)^2 (\cos x + i \sin x) \\
&= \left(\cos^2 x + 2i \cos x \sin x - \sin^2 x \right)^2 (\cos x + i \sin x) \\
&= (\cos^4 x + 4i \cos^3 x \sin x - 6 \cos^2 x \sin^2 x - 4i \cos x \sin^3 x + \sin^4 x)(\cos x + i \sin x) \\
&= (\textrm{take only real part}) \quad \cos^5 x - 10 \cos^3 x \sin^2 x + 5 \cos x \sin^4 x \\
&= \cos^5 x - 10 \cos^3 x (1-\cos^2 x) + 5 \cos x (1-\cos^2 x)^2 \\
&= 16 \cos^5 x - 20 \cos^3 x + 5 \cos x
\end{aligned}
$$

$\blacksquare$

</details>


<br>

<br><span class="statement-title">Formula.</span> $n$-th root of $w$<br>

<div class="statement" markdown="1">

$w \in \mathbb{C}$의 근호(root)를 취한 결과를 말한다.

Let $w = R e^{i \varphi}$, then

$z = r e^{i\theta}$ for $z^n = w$ is like this

- $r^n = R$ $\iff$ $r = R^{1/n}$
- $n\theta \pm 2\pi k = \varphi$ $\iff$ $\theta = \dfrac{\varphi}{n} \pm \dfrac{2\pi}{n}k$

</div>

$n$-root가 single-value로 정해지는 실수와는 달리 복소수에서의 $n$-root $z^{1/n}$은 $n$-valued function이다. 그래서 $z^{1/n}$는 아래와 같다.

$$
z^{1/n} = \sqrt[n]{r} \exp \left[ i \left( \frac{\theta}{n} + \frac{2k \pi}{n} \right) \right] \quad k = 1, 2, ..., n-1
$$

<br>
<hr>

<br><span class="statement-title">Definition.</span> $\epsilon$-neighborhood of $z_0$<br>

<div class="statement" markdown="1">

$$
\left\{ z : \left| z - z_0 \right| < \epsilon \right\}
$$

- deleted $\epsilon$-neighborhood of $z_0$

$$
\left\{ z : 0 < \left| z - z_0 \right| < \epsilon \right\}
$$

</div>

<br>

<br><span class="statement-title">Definition.</span> Interior / Exterior / Boundary <br>

<div class="statement" markdown="1">

For a set $S \subset \mathbb{C}$

1\. **Interor point**

A point $z_0$ is called an "interor point" if there is an $\epsilon$-neighborhood of $z_0$ s.t.

$$
B(z_0, \epsilon) \subset S
$$

<br>

2\. **Exterior point**

A point $z_0$ is called an "exterior point" if there is a $\epsilon$-neighborhood of $z_0$ s.t.

$$
B(z_0, \epsilon) \cap S = \emptyset
$$

<br>

3\. **Boundary point**

If $z_0$ is neither of these, then it is called a "boundary point".

</div>

<br>

<br><span class="statement-title">Definition.</span> Open / Closed set <br>

<div class="statement" markdown="1">

1\. **Open set**

A set $S$ is "open" if every point of $S$ is an interior point.<br>
<small>(No boundary points)</small>


2\. **Closed set**

A set $S$ is closed if $S^c$ is open, equivalently $S$ contains all baoundary points of $S$.

</div>
