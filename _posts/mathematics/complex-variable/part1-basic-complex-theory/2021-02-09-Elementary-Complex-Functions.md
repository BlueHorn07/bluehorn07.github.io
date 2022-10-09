---
title: "Elementary Complex Functions"
layout: post
tags: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Exponential Functions; $\exp z$
- Trigonometric Functions; $\cos z$, $\sin z$, $\tan z$
- Hyperbolic Functions; $\cosh z$, $\sinh z$
- Logarithm; $\log z$
- Power Functions; $z^c$

<hr/>

### Exponential Functions

$$
e^z \quad \textrm{or} \quad \exp z
$$

<div class="statement" markdown="1">

For $z = x + iy$,

$$
e^z = e^x (\cos y + i \sin y)
$$

</div>

- $e^z = e^{z + 2\pi i}$

<br/>

### Trigonometric Functions

$$
\begin{aligned}
    \cos z &= \frac{1}{2} (e^{iz} + e^{-iz}) \\
    \sin z &= \frac{1}{2i} (e^{iz} - e^{-iz})
\end{aligned}
$$

<div class="statement" markdown="1">

- $\cos z = \cos x \cosh y - i \sin x \sinh y$

Let $z = x + iy$

$$
\begin{aligned}
\cos z &= \frac{1}{2} (e^{iz} + e^{-iz}) \\
&= \frac{1}{2} (e^{ix - y} + e^{-ix + y}) \\
&= \frac{1}{2} (e^{-y}(\cos x + i \sin x) + e^{y} (\cos x - i \sin x)) \\
&= \cos x \frac{(e^y + e^{-y})}{2} - i \sin x \frac{(e^y - e^{-y})}{2} \\
&= \cos x \cosh y - i \sin x \sinh y
\end{aligned}
$$

</div>

- $\cos^2 x + \sin^2 z = 1$

<br/>

### Hyperbolic Functions

$$
\begin{aligned}
    \cosh z = \frac{e^z + e^{-z}}{2} \\
    \sinh z = \frac{e^z - e^{-z}}{2}
\end{aligned}
$$

<div class="statement" markdown="1">

Some interesting, unexpected relations

$$
\begin{aligned}
\cosh iz = \cos z, \quad \sinh iz = i \sin z \\
\cos iz = \cosh z, \quad \sin iz = i \sinh z
\end{aligned}
$$

In real domain, there is no relation between $\cos x$ and $\cosh x$!!

</div>

<br/>

### (Complex) Logarithm

$$
w = \log z \iff e^w = z \quad (z \ne 0)
$$

실수 영역에서는 $e^x$가 1-1 함수이기 때문에 real logarithm은 함수로서 well-defined된다.

하지만, 복소 영역에서는 $e^w = e^{w + 2n\pi i}$이기 때문에, $e^w = z$를 만족하는 $w$가 무수히 많다. 따라서 complex loarithm인 $\log z$는 single-valued function이 아니라 **<u>multi-valued function</u>**이 된다!

$$
\log z = \log \left| z \right| + i \arg z \quad (z\ne0)
$$

<div class="statement" markdown="1">

- $e^{\log z} = z$
- $\log e^z = z \pm i 2n\pi$

<br/>

- $\log 1 = 0 \pm 2n \pi i$
- $\log -1 = 0 + (\pi \pm 2n \pi) i $

</div>

#### Principal value of $\log z$

$$
\textrm{Log} \; z = \log \left| z \right| + i \; \textrm{Arg} \; z
$$

#### branch

(1) Restric $\theta$ as $-\pi < \theta < \pi$. Then

$$
\log z = \log r + i \theta \quad \textrm{for} \quad z = r e^{i \theta}
$$

is single-valued and it is continuous function!

1. A *<u>brach</u>* of multi-valued function $f$ is any single-valued function $F(z)$ that is analytic in some domain.
2. A *<u>branch cut</u>* is a partition of a line or a curve that is introduced in order to define a branch $F$.
3. A *<u>branch point</u>* is any point that is ccommon to all branch cuts of $f$.

<br/>

### Power Functions

$$
z^c = e^{c \log z}, \quad c \; : \; \textrm{complex number}
$$

Principal value of $z^c$ is defined by

$$
\textrm{P.V.} \; z^c = e^{c \; \textrm{Log} \; z}
$$

<div class="statement" markdown="1">

1. If $c$ is natural number, then $z^n$ is sinle-valued.
2. If $c = 1/n$, then $z^n$ is $n$-th valued.
3. If $c$ is irrational or not real, then $z^c$ is infinitely many-valued.

- $i^{-i}$

$$
\begin{aligned}
    i^{-i} &= e^{-i \log i} \\
    &= e^{-i (\log 1 + (\frac{\pi}{2} \pm 2 n \pi)i)} \\
    &= e^{\frac{\pi}{2} \pm 2 n \pi} \\
    &= e^{\pi / 2}
\end{aligned}
$$

</div>