---
title: "Cauchy-Goursat Theorem"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Cauchy's proof
- Goursat's proof

<br/>
<hr/>

### Cauchy-Goursat Theorem

<span class="statement-title">Theorem.</span> The Cauchy-Goursat Theorem<br/>

<div class="statement" markdown="1">

If $f(z)$ is analytic in s imply connected domain $D$,

then for every simple closed contour $C$ in $D$,

$$
\oint_{C} f(z) dz = 0
$$

</div>

복소함수론에서 정말 아주아주아주 중요하고, 유용한 정리이다!! 🔥

<br/><span class="statement-title">Definition.</span> Connected domains<br/>

<div class="img-wrapper">
<img src="https://i.imgur.com/9aEdle0.png" width="50%">
</div><br/>

doubly connected domain에서는 어떤 contour $C$에 대해, $\textrm{Int}\; C \notin D$가 된다.

<br/>
<hr/>

### Cauchy's proof

<span class="statement-title">Theorem.</span> Cauchy's Theorem<br/>

<div class="statement" markdown="1">

If $f(z)$ is **<u>analytic</u>** in a **<u>simply connected domain</u>** $D$, and $f'(z)$ is **<u>continuous</u>** in $D$,

then for every simple closed contour $C$ in $D$

$$
\oint_{C} f(z) dz = 0
$$

</div>

Cauchy의 정리에는 "$f'(z)$이 continuous"라는 조건이 붙는다. Cauchy는 이를 이용해 적분을 2차원의 real integral로 바꾸어 접근한다.

<span class="statement-title">Theorem.</span> Green's Theorem<br/>
<div class="statement" markdown="1">

만약 $Q_x$, $P_y$가 연속 함수라면,

$$
\oint_{C} (Pdx + Q dy) = \int \int_{R} (Q_x - P_y) \; dA = \int \int_{R} (Q_x - P_y) \; dx dy
$$

</div>

Green's Theorem을 이용해 Cauchy's Theorem을 증명해보자.

<span class="statement-title">proof.</span><br/>

<div class="math-statement" markdown="1">


$$
\begin{aligned}
f(z) &= f(x+iy) = u(x, y) + i v(x, y), \\
z(t) &= x(t) + i y(t), \quad a \le t \le b
\end{aligned}
$$

$$
\begin{aligned}
\oint_{C} f(z) \; dz &= \int^{b}_{a} (u+iv)(x'+iy') \; dt \\
&= \int^{b}_{a} (ux'-vy') + i (uy' + vx') \; dt
\end{aligned}
$$

위의 식에서 실수 부분만 분리해서 생각해보자.

$$
\int^{b}_{a} (ux'-vy') \; dt
$$

이때, $x' dt$를 $dx$로 취급해 식을 다시 쓰면 아래와 같다.

$$
\int_{C} (u dx -v dy)
$$

이것은 Green's Thm의 $P dx + Q dy$의 꼴이다. 따라서 Green's Thm을 적용하면,

$$
\int_{C} (u dx -v dy) = \int \int_{R} (-v_x -u_y) \; dA
$$

이때, $f(z)$가 analytic 함수이므로, Cauchy-Riemann에 의해 $u_y = -v_x$다. 따라서 "(준적분) = 0"

동일한 방식으로 허수부에 대해서도 "(준적분) = 0"이라는 결과를 얻는다.

따라서

$$
\oint_{C} f(z) \; dz = 0
$$

$\blacksquare$

</div>

### Goursat's proof

Cauchy's Thm은 analytic function의 적분에 대해 좋은 결과를 보여준다. 하지만, Cauchy's Thm에서 가정한 "$f'(z)$ is continuous"라는 조건이 추가되었기 때문에, analytic function의 성질을 설명하는 데에 충분치 않았다.

[Édouard Goursat](https://en.wikipedia.org/wiki/%C3%89douard_Goursat)은 Cauchy's Theorem의 continuous 조건을 제거하고 증명을 완성한다.

<span class="statement-title">Theorem.</span> Goursat's Theorem<br/>

<div class="statement" markdown="1">

Let $D$ be an open set in $\mathbb{C}$.

Let $T$ be triangle such that $T$ and its interior lie in $D$.

If $f(z)$ is analytic in $D$, then

$$
\oint_{T} f(z) dz = 0
$$

</div>

증명이 너무 길어서 파일로 대체합니다!!

[Goursat proof](https://github.com/BlueHorn07/mathematics/tree/master/_posts/complex_variable/part2-complex-integration/Goursat-proof.pdf)

