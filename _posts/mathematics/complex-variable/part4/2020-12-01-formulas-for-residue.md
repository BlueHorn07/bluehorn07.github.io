---
title: "Formulas for residue"
layout: post
use_math: true
tags: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

이전까지는 **residue**를 구하기 위해 함수 $f(z)$의 로랑 급수를 구해 $b_1$을 구하는 방법을 사용했다. 

그런데 만약 $f(z)$가 nice한 조건을 가지고 있음이 확인된다면, 로랑 급수를 구하지 않고도 손쉽게 **residue**를 구할 수 있다!!

<hr>

**<u>review.</u>** Cauchy's residue theorem<br>

$$
\oint_{C} {f(z) dz} = 2{\pi}i \left(\sum^{n}_{k=1} \underset{z=z_k}{\textrm{Res}} f(z) \right)
$$

<br>

**<u>review.</u>**<br>
Let $p(z)$ and $q(z)$ be **<u>analytic</u>** functions. Supp. that $q(z)$ has a **<u>zero</u>** of $m$-th order at $z=z_0$ and $p(z_0) \ne 0$. 

THEN

$$
f(z) = \frac{p(z)}{q(z)}
$$

has a **<u>pole</u>** at $z=z_0$ of order $m$

<hr>

### Formulas for residues

#### simple poles at $z_0$

Consider 

$$
f(z) = \frac{p(z)}{q(z)}
$$

where $p(z)$ and $q(z)$ are **<u>analytic</u>** functions. Supp. that $q(z)$ has a **<u>simple zero</u>** at $z=z_0$ and $p(z_0) \ne 0$.

THEN

$$
\underset{z=z_0}{\textrm{Res}} {f(z)} = \lim_{z \rightarrow z_0} {(z-z_0)} \cdot f(z) = \frac{p(z_0)}{q'(z_0)}
$$

**<u>proof.</u>**<br>
We can express $f(z)$ as follows

$$
f(z) = \frac{b_1}{(z-z_0)} + a_1 + a_2 (z-z_0) + \cdots
$$

THEN

$$
(z-z_0)f(z) = b_1 + a_1(z-z_0) + a_2 (z-z_0)^2 + \cdots
$$

THEN, take **limit** for that

$$
\lim_{z \rightarrow z_0} {(z-z_0)f(z)} = b_1
$$

따라서 $\lim_{z \rightarrow z_0} {(z-z_0)f(z)}$를 통해 residue를 손쉽게 구할 수 있다!!

<br>

그 다음 공식은 $\lim_{z \rightarrow z_0} {(z-z_0)f(z)}$에 약간의 trick을 써서 구할 수 있다.

$$
\begin{aligned}
  \lim_{z \rightarrow z_0} {(z-z_0)f(z)} &= \\
  \lim_{z \rightarrow z_0} {(z-z_0) \frac{p(z)}{q(z)}} &= \lim_{z \rightarrow z_0} {\frac{p(z)}{\frac{q(z) - q(z_0)}{z-z_0}}} \\
  &= \frac{p(z_0)}{q'(z_0)}
\end{aligned}
$$

### poles of order $m$

$$
\underset{z=z_0}{\textrm{Res}} {f(z)} = \frac{1}{(m-1)!} \lim_{z \rightarrow z_0} {\left(\frac{d}{dz}\right)^{(m-1)}\left[(z-z_0)^m f(z)\right]}
$$

**<u>proof.</u>**<br>

$$
\begin{aligned}
  f(z) &= \frac{b_m}{(z-z_0)^m} + \cdots + \frac{b_1}{(z-z_0)} + a_0 + a_1 (z-z_0) + \cdots \\
  (z-z_0)^m f(z) &= b_m + \cdots + b_1(z-z_0)
^m + a_0 (z-z_0)^m + \cdots
\end{aligned}
$$

$(z-z_0)^m f(z)$에서 $b_1$을 얻기 위해선 $(m-1)$번 미분해야 한다.

$$
\begin{aligned}
  {\left(\frac{d}{dz}\right)^{(m-1)}\left[(z-z_0)^m f(z)\right]} &= \frac{b_1}{(m-1)!} + \frac{a_0}{(m-1)!}(z-z_0) + \cdots \\
  \lim_{z \rightarrow z_0}{\left(\frac{d}{dz}\right)^{(m-1)}\left[(z-z_0)^m f(z)\right]} &= \frac{b_1}{(m-1)!}
\end{aligned}
$$

따라서 order $m$에 대한 $b_1 = \underset{z=z_0}{\textrm{Res}} {f(z)}$는 위와 같은 식으로 구할 수 있다!

<hr>

**<u>Example.</u>**<br>

$$
f(z) = \frac{9z+i}{z^3+z}
$$

Find residues of $f(z)$.

<br>

**<u>Sol.</u>**<br>
$q(z) = z^3+z = z(z^2+1)$

따라서 $f(z)$의 pole은 

$$
z=0, \; +i, \; -i
$$

이고, 모두 simple pole이다.

(i) residue for $z=0$

$$
\begin{aligned}
  zf(z) &= z \cdot \frac{9z+i}{z(z^2+1)} = \frac{9z+i}{z^2+1} \\
  \lim_{z \rightarrow 0} zf(z) &= \frac{i}{1} = i
\end{aligned}
$$

따라서 $\underset{z=0}{\textrm{Res}} f(z) = i$.

(ii), (iii) residue for $z=+i, \; -i$는 생-략

