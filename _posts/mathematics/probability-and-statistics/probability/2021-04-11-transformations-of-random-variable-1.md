---
title: "Transformations of Random Variable - 1"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

이번 챕터 "Ch07. Functions of Random Variables"에서는 RV $X$에 어떤 함수 $f(x)$를 씌워 $Y = f(X)$라는 새로운 RV를 만들때, 이 RV $Y$에 대한 분포를 살펴본다. 즉, $f(X)$에 대한 pdf, cdf를 구한다는 말이다.

쉬운 경우부터 조금 복잡한 경우까지 순서대로 살펴볼 것이며, $f(x)$가 1-1 function일 때, $f(x)$가 not 1-1일 때를 살펴본다. 뒷부분에서는 RV의 momemtum을 쉽게 구하는 도구인 \<MGF; Momentum Generating Function\> $M_X(t)$에 대해 살펴본다.

<hr/>

## 1-1 Transformation

### Discrete Case

<span class="statement-title">Example.</span><br>

Let $X$ be a RV with $P(X = \pm 1) = 1/2$.

Let $Y = 3X - 5$, find the pmf of $Y$.

$$
P(Y = y) = \begin{cases}
    1/2 & y=-2 \\
    1/2 & y=-8 \\
    \; 0 & \text{else}
\end{cases}
$$

위의 과정을 좀더 풀어서 살펴보자. 그러면,

$$
\begin{aligned}
P(Y = y) &= P(3X - 5 = y)\\
&= P(f(X) = y) \\
&= P(X = f^{-1}(y))
\end{aligned}
$$

즉, $f(X)$에서 역함수 $f^{-1}$를 이용해 $X$의 pmf로부터 손쉽게 $Y$의 pmf를 유도할 수 있다는 것이다!

$$
P(Y = y) = P(X = f^{-1}(y))
$$

<br/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> Discrete Case<br>

1\. Supp. $X$ has pmf $f_X (x)$.

Let $Y=g(X)$ where $g$ is 1-1 function with the inverse $x = g^{-1}(y)$.

Then,

$$
f_Y (y) = f_X (g^{-1}(y))
$$

<br/>

2\. Supp. $(X_1, X_2)$ has joint pmf $f_{X_1, X_2} (x_1, x_2)$.

Let $Y_1 := u_1 (X_1, X_2)$ and $Y_2 := u_2 (X_1, X_2)$, and $X_1 := w_1(Y_1, Y_2)$ and $X_2 := w_2 (Y_1, Y_2)$.

Then,

$$
f_{Y_1, Y_2} (y_1, y_2) = f_{X_1, X_2} \left( w_1(y_1, y_2), w_2(y_1, y_2) \right)
$$

</div>

즉, $X_1$, $X_2$를 이용해 $Y_1$, $Y_2$를 정의한 식을 잘 풀어서, $Y_1$, $Y_2$를 이용해 $X_1$, $X_2$를 기술한 식 $w_1$, $w_2$를 정의하고, 그것으로 pmf를 유도한다는 말이다! 당연한 접근을 수식으로 formal하게 기술한 것 정도라고 생각하면 된다.

<br/>

<span class="statement-title">Example.</span><br>

Let $X \sim \text{Poi}(\lambda)$ and $Y \sim \text{Poi}(\mu)$, and $X \perp Y$.

Find the distribution of $X + Y$.

<div class="math-statement" markdown="1">

만약 $P(X+Y = 5)$라고 한다면, 이것을 계산하기 위해 $P(X = 0, Y=5)$, $P(X=1, Y=4)$, ..., $P(X=5, Y=0)$의 확률을 구해서 더할 것이다. 이 아이디어를 바탕으로 아래와 같이 식을 전개해보자!

$$
\begin{aligned}
P(X+Y = n) &= \sum^{\infty}_{k=0} P(X=n-k, Y = k) \\
           &= \sum^{\infty}_{k=0} P(X=n-k) P(Y = k) \quad (\text{independence}) \\
           &= \sum^{\infty}_{k=0}  e^{-\lambda} \frac{\lambda^{n-k}}{(n-k)!} \cdot e^{-\mu} \frac{\mu^k}{k!} \\
           &= \frac{e^{-(\lambda + \mu)}}{n!} \sum^{\infty}_{k=0} \frac{n!}{(n-k)!k!} \lambda^{n-k} \mu^k \\
           &= \frac{e^{-(\lambda + \mu)}}{n!} \cdot (\lambda + \mu)^n = e^{-(\lambda + \mu)} \frac{(\lambda + \mu)^n}{n!}
\end{aligned}
$$

즉, $P(X+Y = n)$는 $\text{Poi}(\lambda+\mu)$의 pmf라는 것을 유도할 수 있다! $\blacksquare$

</div>

<div class="math-statement" markdown="1">

We first find the joint pmf of $(X, X+Y)$, and then find the marginal pmf of $X+Y$.

Let $U = X$, $V = X+Y$, then $X = U$, $Y = V - U$.

따라서, $U$, $V$에 대한 pmf는

$$
\begin{aligned}
f_{U, V} (u, v) &= f_{X, Y} (u, v-u) \\
                &= f_X (u) f_Y(v-u) \quad (\text{independence}) \\
                &= e^{-\lambda} \frac{\lambda^u}{u!} \cdot e^{-\mu} \frac{\mu^{v-u}}{(v-u)!} \\
                &= e^{-(\lambda + \mu)} \frac{\lambda^u}{u!} \frac{\mu^{v-u}}{(v-u)!}
\end{aligned}
$$

$U$, $V$에 대한 joint pmf를 구했으니, 이번에는 $V$에 대한 marginal pmf를 구해보자.

$$
\begin{aligned}
f_V (v) &= \sum_u f_{U, V} (u, v) \\
        &= \sum_u e^{-(\lambda+\mu)} \frac{\lambda^u}{u!} \frac{\mu^{v-u}}{(v-u)!} \\
        &= e^{-(\lambda+\mu)} \sum_u \frac{\lambda^u}{u!} \frac{\mu^{v-u}}{(v-u)!} \\
        &= \frac{e^{-(\lambda+\mu)}}{v!} \sum_u \frac{v!}{u!(v-u)!} \lambda^u \mu^{v-u} \\
        &= \frac{e^{-(\lambda+\mu)}}{v!} \cdot (\mu + \lambda)^v \\
        &= e^{-(\lambda+\mu)} \frac{(\mu + \lambda)^v}{v!}
\end{aligned}
$$

즉, $V = X+Y$에 대한 pmf는 결국 $\text{Poi}(\lambda + \mu)$인 것이다! $\blacksquare$

</div>

<hr/>

### Continuous Case

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X$ be a continuous RV with pdf $f_X (x)$.

Let $Y := g(X)$ wgere $g$ is 1-1 with inverse $x = h(y)$.

Then,

$$
f_Y (y) = f_X (h(y)) \cdot \left| h'(y) \right|
$$

💥 Continuous의 경우, $\left\| h'(y) \right\|$ 텀이 곱해진다는 사실에 주목하자! 🔥

</div>

<br/>

<span class="statement-title">Example.</span><br>

1\. Let $X \sim N(\mu, \sigma^2)$ and let $Y := \dfrac{X - \mu}{\sigma}$.

Then,

$$
\begin{aligned}
f_Y (y) &= f_X (h(y)) \cdot \left| h'(y) \right| \\
        &= \frac{1}{\sqrt{2\pi} \sigma} \cdot \exp \left({- \frac{(h(y)-\mu)^2}{2\sigma^2}}\right) \cdot \left| \sigma \right|\\
        &= \frac{1}{\sqrt{2\pi} \cancel{\sigma}} \cdot \exp \left( - \frac{(\cancel{\sigma} y + \cancel{\mu} - \cancel{\mu})^2}{2\cancel{\sigma^2}} \right) \cdot \cancel{\sigma} \\
        &= \frac{1}{\sqrt{2\pi}} \cdot \exp \left( - y^2 / 2\right)
\end{aligned}
$$

<hr/>

2\. Let $X \sim \text{Gamma}(\alpha, 1)$, and let $Y := \beta X$.

**Claim**: $Y \sim \text{Gamma}(\alpha, \beta)$

$$
y = \beta x \iff x = \frac{y}{\beta} = h(y)
$$

and

$$
f_X (x) = \frac{1}{\Gamma(\alpha)} x^{\alpha - 1} e^{-x}
$$

then,

$$
\begin{aligned}
f_Y (y) &= f_X (h(y)) \cdot \left| h'(y) \right| \\
        &= \frac{1}{\Gamma(\alpha)} h(y)^{\alpha - 1} e^{-h(y)} \cdot \left| \frac{1}{\beta} \right| \\
        &= \frac{1}{\Gamma(\alpha) \beta} \cdot \left( \frac{y}{\beta}\right)^{\alpha-1} e^{-y/\beta} \\
        &= \frac{1}{\Gamma(\alpha) \beta^{\alpha}} \cdot y^{\alpha-1} e^{-y/\beta}
\end{aligned}
$$

따라서, $X \sim \text{Gamma}(\alpha, 1)$에서 $Y = \beta X$의 transformation을 취하면, $Y \sim \text{Gamma}(\alpha, \beta)$의 분포를 얻는다.

<br/>

<hr/>

3\. Let $\theta \sim \text{Unif}(-\pi/2, \pi/2)$, and let $X = \tan \theta$.

Find the pdf of $X$.

$$
h(x) = \arctan x \quad \text{and} \quad h'(x) = \frac{1}{1+x^2}
$$

위의 규칙에 맞춰 $X$의 분포를 유도해보면,

$$
\begin{aligned}
f_X (x) &= f_\theta (h(x)) \cdot \left| h'(x) \right| \\
        &= \cancelto{\frac{1}{\pi}}{f_\theta (\arctan x)} \cdot \frac{1}{1+x^2} \quad (\text{Uniform distribution})\\
        &= \frac{1}{\pi} \frac{1}{1+x^2}
\end{aligned}
$$

참고로 위와 같은 분포를 \<Cauchy Distribution\>라고 한다.

<br/>

<hr/>

<span class="statement-title">Theorem.</span><br>

Let $X$ be a RV with cdf $F_X (x)$ which is strictly increasing.

Let $U \sim \text{Unif}(0, 1)$ Then,

1\. $Y := F_X^{-1}(U)$ has the same distribution of $X$.

2\.$Z := F_X(X)$ has the same distribution of $U$.

개인적으로 명제에 대해 잘 이해가 되지 않아, 증명을 먼저 이해했다.

<br/>

<span class="statement-title">*Proof*.</span> 1 <br>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
P(Y \le y) &= P(F_X^{-1} (U) \le y) \\
           &= P(U \le F_X (y)) \\
           &= F_X (y) \quad (\text{by cdf of unfiorm $U$})
\end{aligned}
$$

따라서, $Y = F_X^{-1}(U)$는 $X$의 분포를 갖는다. $\blacksquare$

</div>

<span class="statement-title">*Proof*.</span> 2 <br>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
P(Z \le x) &= P(F_X(X) \le x) \\
           &= P(X \le F_X^{-1}(x)) \\
           &= F_x \left( F_X^{-1} (x) \right) = x
\end{aligned}
$$

따라서, $Z = F_X(X)$는 uniform distribution $U(0, 1)$을 갖는다. $\blacksquare$

</div>

<span class="statement-title">**Exmaple**.</span><br>

Let $X \sim \text{Exp}(\lambda)$, then cdf is $F_X(x) = 1 - e^{-\lambda x}$.

그러면 우리는 $F_X^{-1}$와 $U$를 이용해 $X$의 분포를 갖는 RV를 유도할 수 있다!!

By above theorem,

$$
F_X^{-1}(U) = \frac{-\ln (1-U)}{\lambda} \sim X
$$

덧붙이면, $U$에서 $X$로 가는 Transformation을 찾고 싶은데, 그걸 $F_X^{-1}$로 설정하면 아주 쉽게 $U$에서 $X$로 가는 Transform을 찾는게 된다는 말이다!! 🤩

<hr/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> Continuous case - Two Random Variables<br>

Let $(X, Y) \mapsto \left( u(X, Y), v(X, Y) \right)$ with the inverse $(U, V) \mapsto \left(w_1(U, V), w_2(U, V)\right)$.

If $(X, Y)$ has joint pdf $f_{X, Y}(x, y)$, then $(U, V)$ has joint pdf

$$
f_{U, V} (u, v) = f_{X, Y} \left( w_1(U, V), w_2(U, V) \right) \cdot \left| J \right|
$$

where $J$ is *Jaccobian Matrix*

$$
J = \begin{pmatrix}
        \frac{\partial w_1}{\partial u} & \frac{\partial w_1}{\partial v} \\
        \frac{\partial w_2}{\partial u} & \frac{\partial w_2}{\partial v} \\
\end{pmatrix}
$$

참고로 *Jaccobian* $J$는 적분 변수를 바꾸는 과정에서 등장했다.

$x = w_1(u, v)$, $y = w_2(u, v)$라고 하고, 적분 변수를 $u$, $v$로 변환한다면,

$$
\int \int_{X, Y} f(x, y) \, dxdy = \int \int_{U, V} f\left( w_1 (u, v), w_2(u, v) \right) \left| J \right| \, dudv
$$

그래서 잘 살펴보면, $(U, V)$에 대한 pdf $f_{U, V}(u, v)$는 위의 식의 우변에서 적분 내부의 함수를 그대로 가져온 것임을 쉽게 확인할 수 있다!!

</div>

<br/>

<span class="statement-title">**Example**.</span> [1]<br>

Let $X \sim N(0, 1)$ and $Y \sim N(0, 1)$, and $X \perp Y$.

Let $U := X + Y$ and $V := X - Y$.

1\. Find the joint pdf of $(U, V)$

먼저 $w_1(u, v)$, $w_2(u, v)$를 구한다.

$$
\begin{aligned}
x &= \frac{u+v}{2} = w_1(u, v) \\
y &= \frac{u-v}{2} = w_2(u, v)
\end{aligned}
$$

이것을 그대로 적용해보면,

$$
\begin{aligned}
f_{U, V}(u, v) &= f_{X, Y} \left( w_1(u, v), w_2(u, v) \right) \cdot
\cancelto{1/2}{\left| \begin{matrix}
  1/2 & 1/2 \\
  1/2 & -1/2
\end{matrix}\right|} \\
&= f_{X, Y} \left( \frac{u+v}{2}, \frac{u-v}{2} \right) \frac{1}{2} \\
&= f_X \left( \frac{u+v}{2} \right) f_Y \left(\frac{u-v}{2}\right) \frac{1}{2} \qquad (X \perp Y) \\
&= \frac{1}{\sqrt{2\pi}} \exp \left( - \frac{(u+v)^2}{8} \right) \cdot \frac{1}{\sqrt{2\pi}} \exp \left( - \frac{(u-v)^2}{8} \right) \cdot \frac{1}{2} \\
&= \frac{1}{4\pi} \exp \left( - \frac{u^2 + v^2}{4}\right)
\end{aligned}
$$

<br/>

2\. Are $U$ and $V$ independent?

A. Yes!

$$
\begin{aligned}
f_{U, V} (u, v) &= \frac{1}{4\pi}  \exp \left( - \frac{u^2 + v^2}{4}\right) \\
\end{aligned}
$$

우리는 $f_{U, V} (u, v)$의 식에서 $f_U (u)$를 유도해보면,

$$
\begin{aligned}
f_U(u) = \frac{1}{\sqrt{2\pi} \cdot (\sqrt{2})^2} \cdot \exp \left( - \frac{u^2}{2 \cdot (\sqrt{2})^2}\right)
\end{aligned}
$$

즉, $U$는 $N(0, (\sqrt{2})^2)$의 분포를 가짐을 확인할 수 있다!

<br/>

3\. Let $Z := \dfrac{Y}{X}$. Find the pdf of $Z$.

Let $U := X$, and $V := \dfrac{Y}{X}$, then

$$
\begin{aligned}
X &= U \\
Y &= UV
\end{aligned}
$$

and *Jaccobian* is

$$
\left| J \right| = \left| \begin{matrix}
1 & 0 \\
\cdot & u
\end{matrix}\right| = \left| u \right|
$$

재료는 다 갖춰졌으니, 이제 pdf $f_{U, V}(u, v)$를 구해보자.

$$
\begin{aligned}
f_{U, V}(u, v) &= f_{X, Y} (u, uv) \cdot \left| u \right| \\
&= f_X (u) \cdot f_Y (uv) \cdot \left| u \right| \\
&= \frac{1}{\sqrt{2\pi}} \exp \left( - \frac{u^2}{2} \right) \cdot \frac{1}{\sqrt{2\pi}} \exp \left( - \frac{u^2v^2}{2}\right) \cdot \left| u \right| \\
&= \frac{1}{2\pi} \cdot \exp \left( - \frac{u^2(1+v^2)}{2}\right) \cdot \left| u \right|
\end{aligned}
$$

이때, 우리가 목표로 하는 분포인 $Z$, 즉 $V := \dfrac{Y}{X}$를 구하기 위해 $f_{U, V}(u, v)$에서 marginalize out 해준다.

$$
\begin{aligned}
f_Z (z) &= f_V (v) = \int f_{U, V} (u, v) \, du \\
&= \frac{1}{2\pi} \int^{\infty}_{-\infty} \left| u \right| \cdot \exp \left( - \frac{u^2(1+v^2)}{2}\right) \, du \\
&= \frac{1}{2\pi} \cdot 2 \int^{\infty}_0 \left| u \right| \cdot \exp \left( - \frac{u^2(1+v^2)}{2}\right) \, du \\
&= \frac{1}{2\pi} \cdot 2 \int^{\infty}_0 \frac{1}{2} \cdot \exp \left( - \frac{t(1+v^2)}{2}\right) \, dt \qquad (u^2 = t) \\
&= \frac{1}{2\pi} \cdot \left( \left. \frac{2}{-(1+v^2)} \cdot \exp \left( - \frac{t(1+v^2)}{2}\right) \right]^{\infty}_0 \right) \\
&= \frac{1}{2\pi} \cdot \frac{2}{-(1+v^2)} \left\{ 0 - 1\right\} \\
&= \frac{1}{\pi} \cdot \frac{1}{1+v^2}
\end{aligned}
$$

즉, 위와 같이 $Z := \dfrac{Y}{X}$에 대한 분포를 얻을 수 있다!! 참고로 위의 분포는 앞에서 잠간 언급된 \<Cauchy Distribution\>이다!

<br/>

<span class="statement-title">**Example**.</span> [2]<br>

Let $(X, Y)$ have the joint pdf

$$
f_{X, Y}(x, y) = \begin{cases}
  4xy & \text{for } 0 < x < 1 \text{ and } 0 < y < 1\\
  0 & \text{else}
\end{cases}
$$

1\. Find the joint pdf of $(X^2, XY)$.

$$
\begin{aligned}
U &= X^2 \\
V &= XY
\end{aligned}
$$

then, inverse relation is

$$
\begin{aligned}
x &= \sqrt{u} \\
y &= \frac{v}{\sqrt{u}} \\
0 < \sqrt{u} < 1 \quad &\text{and} \quad 0 < \frac{v}{\sqrt{u}} < 1
\end{aligned}
$$

and *Jaccobian* is

$$
\left| J \right| = \left|
\begin{matrix}
  \frac{1}{2\sqrt{u}} & 0 \\
  \cdot & \frac{1}{\sqrt{u}}
\end{matrix}
\right| = \frac{1}{2u}
$$

따라서, pdf $f_{U, V} (u, v)$는

$$
\begin{aligned}
f_{U, V} (u, v) &= f_{X, Y} \left(\sqrt{u}, \frac{v}{\sqrt{u}}\right) \cdot \left| \frac{1}{2u} \right|\\
                &= 4 \cancel{\sqrt{u}} \frac{v}{\cancel{\sqrt{u}}} \cdot \frac{1}{2u} \\
                &= \frac{2v}{u} \qquad \text{for } 0 < u < 1 \text{ and } 0 < v < \sqrt{u}
\end{aligned}
$$

2\. Find the marginal pdf of $X^2$ and $XY$.

(1) $f_U(u) = f_{X^2}(u)$

$$
\begin{aligned}
f_{X^2} (u) &= \int^{\sqrt{u}}_0 \frac{2v}{u} \, dv \\
            &= \frac{1}{u} \cdot (\sqrt{u})^2 = 1
\end{aligned}
$$

따라서, $X^2$은 $\text{Unif}(0, 1)$의 분포를 따른다!

(2) $f_V(v) = f_{XY}(v)$

$$
\begin{aligned}
f_{XY}(v) &= \int^1_{v^2} \frac{2v}{u} \, du \\
          &= 2v \cdot \ln(1-v^2)
\end{aligned}
$$

<hr/>

이어지는 포스트에서는 Random Variable에 대한 Transformation을 이어서 살펴본다. 1-1이 아닌 mapping의 경우를 좀더 살펴볼 예정이다.

👉 [Transformations of Random Variable - 2]({{"/2021/04/12/transformations-of-random-variable-2" | relative_url}})