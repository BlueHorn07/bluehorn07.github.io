---
title: "Cauchy-Riemann Equation & Harnomic function"
layout: post
use_math: true
tags: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>
- Cauchy-Riemann Equation
  - Polar Coordinate
- Harmonic functions & Laplace's Equation
  - harmonic conjugate

<hr/>

### Cauchy-Riemann Equations

"주어진 복소함수 $f(z) = u(x, y) + i v(x, y)$에 대해, 실수부 $u(x, y)$와 허수부 $v(x, y)$를 보고 analytic 함수임을 보장할 수 있을까?"

<div class="statement" markdown="1">

$f$ is **<u>analytic</u>** in a domain $D$

$\iff$ the first partial derivatives of $u$ and $v$ satisfy

**the Cauchy-Riemann equations**: $u_x = v_y$, $u_y = -v_x$

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

$f$ is differentiable $\implies$ $u$, $v$ satisfy the Cauchy-Riemann Equations.

For 

$$
f'(z) = \lim_{\Delta z \rightarrow 0} \frac{f(z+\Delta z) - f(z)}{\Delta z}
$$

Think of two direction $\Delta x$ and $i \Delta y$, then

$$
\begin{aligned}
f'(z) &= \lim_{\Delta x \rightarrow 0} \frac{f(z+\Delta x) - f(z)}{\Delta x} \\
&= \lim_{\Delta x \rightarrow 0} \frac{u(x+\Delta x, y) + v(x+\Delta x, y) i - u(x, y) - v(x, y) i}{\Delta x} \\
&= \lim_{\Delta x \rightarrow 0} \frac{u(x+\Delta x, y) - u(x, y) + v(x+\Delta x, y) i - v(x, y) i}{\Delta x} \\
&= u_x + v_x i 
\end{aligned}
$$

마찬가지로 $i \Delta y$에 대해서 $u(x, y)$, $v(x, y)$에 대해 미분하면 아래와 같다.

$$
\begin{aligned}
f'(z) &= \lim_{\Delta y \rightarrow 0} \frac{f(z+\Delta y) - f(z)}{\Delta y} \\
&= \lim_{\Delta y \rightarrow 0} \frac{u(x, y+\Delta y) - u(x, y) + v(x, y+\Delta y) i - v(x, y) i}{i \Delta y} \\
&= \frac{u_y}{i} + v_y 
\end{aligned}
$$

이때, differentiable하기 위해선 극한이 존재해야 하므로, 

$$
u_x + u_y = \frac{u_y}{i} + v_y
$$

따라서

$$
\begin{aligned}
u_x &= v_y \\
u_y &= -v_x
\end{aligned}
$$

</div>

<div class="math-statement" markdown="1">

$u, v$ have continuous partial derivatives satisfy the Cauchy-Riemann equations $\implies$ $f$ is analytic.

Let $\Delta u = u(x_0 + \delta x, y_0 + \delta y) - u(x_0, y_0)$.

Then, because the first order partial derivative of $u$ are continuous at $(x_0, y_0)$,

$$
\Delta u = u_x (x_0, y_0) \Delta x + u_y (x_0, y_0) \Delta y + \epsilon_1 \Delta x + \epsilon_2 \Delta y
$$

where $\epsilon_1$ and $\epsilon_2$ tend to zero as $\Delta x$, $\Delta y$ approach to zero.

Likewise, we can think of $\Delta v$, then

$$
\begin{aligned}
f'(z) &= \lim_{\Delta z \rightarrow 0} \frac{f(z+\Delta z) - f(z)}{\Delta z} \\
&= \lim_{(\Delta x + i \Delta y) \rightarrow 0} \frac{\Delta u + i \Delta v}{\Delta x + i \Delta y} \\
&= \lim_{(\Delta x + i \Delta y) \rightarrow 0} \frac{(u_x \Delta x + u_y \Delta y) - i(v_x \Delta x + v_y \Delta y) + E}{\Delta x + i \Delta y} \\
&= \lim_{(\Delta x + i \Delta y) \rightarrow 0} \frac{(u_x \Delta x + u_y \Delta y) - i(-u_y \Delta x + u_x \Delta y) + E}{\Delta x + i \Delta y} \quad (\textrm{C-R equation}) \\
&= \lim_{(\Delta x + i \Delta y) \rightarrow 0} \frac{u_x (\Delta x + i \Delta y) + u_y (-i\Delta x + \Delta y)}{\Delta x + i \Delta y} \\
&= u_x - i u_y \quad (\textrm{C-R equation}) \\
&= u_x + i v_x
\end{aligned}
$$

</div>

<br/>

<span class="statement-title">example.</span><br>

Supp. that $f(z)$ is analytic in a domain $D$, and $\left\| f(z) \right\| = k = \textrm{constant}$ in $D$. Show that $f(z)$ is constant.

<br/>

#### Polar Coordinate

<div class="statement" markdown="1">

$$
f(z) = u(r, \theta) + i v(r, \theta), \quad z=r e^{i\theta}
$$

Cauchy-Riemann eqaution for polar coordinate

$$
u_r = \frac{1}{r}u_\theta, \quad v_r = - \frac{1}{r} u_{\theta}
$$

</div>

<br/>
<hr/>

### Harmonics functions & Laplace's Equation

<div class="statement" markdown="1">

A real-valued function $H(x, y)$ is harmonic in a domain $D$, if it satisfies "Laplace equation"

$$
\nabla^2 H = H_{xx} + H_{yy} = 0
$$

</div>

<br/>

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

If $f(z)=u(x, y) + i v(x, y)$ is analytic in a domain $D$, <br/>
then both $u$ and $v$ are Harmonic functions.

</div>
<div class="statement" markdown="1">

If two harmonic functions $u$ and $v$ satisfy the Cauchy-Riemann equations in a domain $D$, <br/>
then $f(z) = u(x, y) + i v(x, y)$ is analytic in $D$.

In this case, $v$ is called a "**harmonic conjugate** function of $u$" in $D$.

</div>