---
title: "Extended Cauchy's Integral Formula"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Extended Cauchy's Integral Formula
- Applications
  - Cauchy's Inequality
  - Liouville's Theorem
  - Fundamental Theorem of Algebra
  - Morera's Theorem

<br/>
<hr/>


<span class="statement-title">Theorem.</span> [Review] Cauchy's Integral Formula<br/>

<div class="statement" markdown="1">

Let $D$ be a simply connected domain, and $f$ be an analytic function in $D$.

Let $z_0 \in D$, and $C$ be any simple closed contour in $D$ that encloses $z_0$.

Then

$$
\oint \frac{f(z)}{z-z_0} dz = 2\pi i f(z_0) \iff f(z_0) = \frac{1}{2\pi i} \oint \frac{f(z)}{z-z_0} dz
$$

</div>

## Extension of Cauchy's Integral Formula

<span class="statement-title">Theorem.</span><br/>

Let $f(z)$ be **<u>analytic</u>** in $D$.

Then it **<u>has derivatives of all orders</u>** in $D$, which **<u>are then also analytic</u>** in $D$.

The derivatives are given by

$$
f^{(n)}(z_0) = \frac{n!}{2\pi i} \oint_{C} \frac{f(z)}{(z-z_0)^{n+1}} dz
$$

where $C$ is any simple closed CCW contour in $D$ encloseing $z_0$ such that its interior also lies in $D$.

<span class="statement-title">Motivation.</span><br/>

analytic function $f(z)$가 아래와 같이 주어져 있다고 하자.

$$
f(z) = \frac{1}{2\pi i} \oint \frac{f(w)}{w-z} dz
$$

이때, $f(z)$를 미분한 $\dfrac{d}{dz}f(z)$를 구할 때, 아래와 같이 적분 내부에 미분 연산자 $\dfrac{d}{dz}$를 끼워넣을 수 있을까??

$$
\frac{d}{dz} f(z) \overset{?}{=} \frac{1}{2\pi i} \oint \frac{d}{dz} \frac{f(w)}{w-z} dw
$$

일반적으로 적분 연산자와 미분 연산자의 교환은 불가능하다! 하지만, 만약 이것이 가능하다면, 우리는 아래와 같은 결과를 얻는다.

$$
\begin{aligned}
\frac{d}{dz} f(z) &\overset{?}{=} \frac{1}{2\pi i} \oint \frac{d}{dz} \frac{f(w)}{w-z} dw \\
&= \frac{1}{2\pi i} \oint \frac{f(w)}{(w-z)^2} dw
\end{aligned}
$$

만약 $n$번 미분 한다면,

$$
\left(\frac{d}{dz}\right)^n \frac{1}{w-z} = n! \frac{1}{(w-z)^{n+1}}
$$

이기 때문에 아래와 같은 결과를 얻을 것이다.

$$
\begin{aligned}
f^{(n)}(z) &\overset{?}{=} \frac{1}{2\pi i} \oint \left(\frac{d}{dz}\right)^n \frac{f(w)}{w-z} dw \\
&= \frac{1}{2\pi i} \oint n! \frac{f(w)}{(w-z)^{n+1}} dw \\
&= \frac{n!}{2\pi i} \oint \frac{f(w)}{(w-z)^{n+1}} dw
\end{aligned}
$$

다시 한번 말하지만, **<u>일반적으로 적분 연산자와 미분 연산자는 교환되지 않기 때문에</u>** 위와 같이 증명하면 안 된다! 아래에 극한의 개념을 이용해 이것을 제대로 증명한다.

<br/><span class="statement-title">*Proof*.</span><br/>

\<Extended Cauchy Integral\>를 증명하기 위해 $f(z)$를 한번 미분한 결과가 아래가 됨을 보이자. 두번, 세번 미분에 대한 결과는 $f'(z)$를 증명한 방식을 그대로 사용하면 된다.

Let assume $C$ a circle with radius $r$.

$$
\begin{equation}
f'(z_0) = \frac{1}{2\pi i} \oint_{C} \frac{f(z)}{(z-z_0)^2} dz
\end{equation}
$$

먼저 정의에 따라 $f'(z_0)$는 아래와 같다.

$$
f'(z) = \lim_{h \rightarrow 0} \frac{f(z_0 + h) - f(z_0)}{h}
$$

이때, 식 (1)에서 우변이 적분이니 좌변인 $f'(z)$도 적분의 형태로 바꿔주자. 이 과정에서 \<Cauchy Integral\>을 사용한다.

$$
\begin{equation}
    f'(z) = \frac{1}{h} \left( \frac{1}{2\pi i} \oint_{C} \frac{f(z)}{z-(z_0 + h)} dz - \frac{1}{2\pi i} \oint_{C} \frac{f(z)}{z-z_0} dz \right)
\end{equation}
$$

(이때, $h$는 충분히 작아서 $\left\| h \right\| < r/2$이다.)

식 (2)의 적분식을 조금 다듬어보자.

$$
\begin{equation}
    \begin{aligned}
    (2) &= \frac{1}{h} \frac{1}{2\pi i} \oint_C f(z) \left(\frac{1}{z-(z_0+h)} - \frac{1}{z-z_0} \right) dz \\
    &= \frac{1}{h} \frac{1}{2\pi i} \oint_C f(z) \left( \frac{(z-z_0) - (z-(z_0+h)))}{\left(z-(z_0+h)\right)(z-z_0)} \right) dz \\
    &= \frac{1}{\cancel{h}} \frac{1}{2\pi i} \oint_C f(z) \frac{\cancel{h}}{\left(z-(z_0+h)\right)(z-z_0)} dz \\
    &= \frac{1}{2\pi i} \oint_C \frac{f(z)}{\left(z-(z_0+h)\right)(z-z_0)} dz
    \end{aligned}
\end{equation}
$$

이제 식 (3)을 식 (1)에 적용하고, 극한을 취하기 편한 꼴로 바꿔주자.

$$
\begin{equation}
   \begin{aligned}
    &f'(z_0) - \frac{1}{2\pi i} \oint_{C} \frac{f(z)}{(z-z_0)^2} dz \\
    &= \frac{1}{2\pi i} \oint_C \frac{f(z)}{\left(z-(z_0+h)\right)(z-z_0)} dz - \frac{1}{2\pi i} \oint_{C} \frac{f(z)}{(z-z_0)^2} dz \\
    &= \frac{1}{2\pi i} \oint_C f(z) \left( \frac{1}{\left(z-(z_0+h)\right)(z-z_0)} - \frac{1}{(z-z_0)^2} \right) dz \\
    &= \frac{1}{2\pi i} \oint_C f(z) \left( \frac{(z-z_0)^2 - ((z-z_0) - h)(z-z_0)}{\left(z-(z_0+h)\right)(z-z_0)(z-z_0)^2} \right) dz \\
    &= \frac{1}{2\pi i} \oint_C f(z) \left( \frac{h\cancel{(z-z_0)}}{\left(z-(z_0+h)\right)\cancel{(z-z_0)}(z-z_0)^2} \right) dz \\
    &= \frac{1}{2\pi i} \oint_C \frac{f(z) \cdot h}{\left(z-(z_0+h)\right)(z-z_0)^2} dz
    \end{aligned}
\end{equation}
$$

이제 식 (4)가 0으로 수렴함을 보이면 \<Extended Cauchy Integral\>을 증명하게 된다! 이것은 \<ML-Inequality\>를 사용하면 된다.

이때, $\left\| z-(z_0+h) \right\| \ge r/2$이고, $\left\| f(z) \right\| \le M$ for some $M$ 이므로,

$$
\begin{equation}
\left| \frac{1}{2\pi i} \oint_C \frac{f(z) \cdot h}{\left(z-(z_0+h)\right)(z-z_0)^2} dz \right| \le \left( \frac{1}{2\pi} \frac{M \cdot h}{(r/2) \; r^2}\right) \cdot \left( 2\pi r \right) = 2 \; \frac{M \cdot h}{r^2}
\end{equation}
$$

$h \rightarrow 0$일 때, 식 (5)가 0으로 수렴하므로 식 (1)이 성립한다! $\blacksquare$

<hr/>

## Application of Cauchy Integral

### Cauchy's Inequality

<span class="statement-title">Theorem.</span><br/>

Let $f(z)$ be an analytic inside and on a positively oriented circle $C$ or radius $r$ and center $z_0$.

If $\left\| f(z) \right\| \le M$ on $C$, then

$$
\left| f^{(n)}(z_0) \right| \le \frac{n!M}{R^n}
$$

<br/><span class="statement-title">*Proof*.</span><br/>

증명은 간단하다. \<Extended Cauchy Integral\>에 바로 \<ML-Inequality\>를 취해주면 된다.

$$
\left| f^{(n)}(z_0) \right| = \left| \frac{n!}{2\pi i} \oint_C \frac{f(z)}{(z-z_0)^n} dz \right| \le \left( \frac{n!}{2\pi} \frac{M}{R^{n+1}} \right) \left( 2\pi R \right) = \frac{n!M}{R^n}
$$

$\blacksquare$

### Liouville's Theorem

<span class="statement-title">Theorem.</span><br/>

If a function $f(z)$ is entire and bounded, then $f(z)$ is constant.

\<리우빌의 정리\>는 대우도 함께 살펴보면 좋다.

(대우) If $f(z)$ is not constant, then $f(z)$ is not bounded (=explode to $+\infty$ or $-\infty$).

<br/><span class="statement-title">*Proof*.</span><br/>

Supp. $f(z)$ is bounded, then $\left\| f(z) \right\| \le M$ for all $z$.

Consider a circle $C_R$ with radius $R$. Then, due to

$$
\left| f'(z_0) \right| \le \frac{M}{R}
$$

$\left\| f'(z_0) \right\| \le 0$ as $R \rightarrow \infty$

Therefore, $f'(z_0) = 0$ for all $z_0 \in \mathbb{C}$.

This means $f(z)$ is constant. $\blacksquare$

### Fundamental Theorem of Algebra

<span class="statement-title">Theorem.</span><br/>

Any polynomial

$$
P(z) = a_n z^n + a_{n-1} z^{n-1} + \cdots + a_1 z + a_0, \quad (a_n \ne 0)
$$

of degree $n$ ($n \ge 1$) has a solution.

<br/><span class="statement-title">*Proof*.</span><br/>

(귀류법) Supp. that $P(z)$ has no solution.

Then $\dfrac{1}{P(z)}$ is entire, becuase there's no $z_0$ s.t. $p(z_0) = 0$

It is also **<u>bounded</u>** in the complex plane!

$$
\begin{aligned}
\left| P(z) \right| &= \left| a_n z^n + a_{n-1} z^{n-1} + \cdots + a_1 z + a_0 \right| \\
& \ge \left| a_n z^n \right| - \left| a_{n-1} z^{n-1} + \cdots + a_1 z + a_0 \right|
\end{aligned}
$$

이때, $\left\| a_{n-1} z^{n-1} + \cdots + a_1 z + a_0 \right\|$에 삼각 부등식을 적용하면 아래와 같다.

$$
\left| a_{n-1} z^{n-1} + \cdots + a_1 z + a_0 \right| \le \left| a_{n-1} \right| \left|z \right|^{n-1} + \cdots + \left| a_1 \right| \left| z \right| + \left| a_0 \right|
$$

이것을 적용하면,

$$
\begin{aligned}
\left| P(z) \right| &\ge \left| a_n z^n \right| - \left| a_{n-1} z^{n-1} + \cdots + a_1 z + a_0 \right| \\
& \ge \left| a_n \right| \left| z \right|^n - \left| a_{n-1} \right| \left|z \right|^{n-1} - \cdots - \left| a_1 \right| \left| z \right| - \left| a_0 \right|
\end{aligned}
$$

이때, 맨앞에 있는 $\left\| a_n \right\| \left\| z \right\|^n$의 power에 의해 어떤 양수 $R$가 존재해 $\left\| z \right\| \ge R$에선 $\left\| P(z) \right\| \ge 1$가 성립한다.

그리고 이것은 $\left\| \dfrac{1}{P(z)} \right\|$을 bounded 시킨다.

$$
\left| P(z) \right| \ge 1 \iff \left| \frac{1}{P(z)} \right| \le 1
$$

반대로 $\left\| z \right\| < R$에선 $\dfrac{1}{P(z)}$가 continuous하고, $P(z) = 0$이 되는 지점이 존재하지 않기 때문에 $\left\| \dfrac{1}{P(z)} \right\|$도 bounded 되어 있다!

따라서, $\dfrac{1}{P(z)}$는 bounded 된 entire function이다. 그렇다면, 앞에서보인 \<리우빌의 정리\>에 의해 $\dfrac{1}{P(z)}$는 constant function이 된다. 하지만, $n \ge 0$이므로 $\dfrac{1}{P(z)}$는 명백히 constant function이 아니다!!

따라서, "$P(z)$ has no solution."라는 처음의 가정은 거짓이다. $P(z)$는 적어도 하나의 solution을 가진다!

만약 $P(z)$가 적어도 하나의 solution을 가진다면, $P(z)$를 아래와 같이 기술할 수 있다.

$$
P(z) = (z-z_1) Q_1(z)
$$

이때, $Q_1(z)$는 $n-1$ 차수를 가지는 polynomial이다. $Q_1(z)$에 대해 앞선 논의를 다시 적용하면, $Q_1(z)$이 적어도 하나의 solution을 가짐을 알 수 있다. 따라서

$$
P(z) = (z-z_1)(z-z_2) Q_2(z)
$$

이것을 반복하면, $P(z)$가 $n$개 solution을 가짐을 확인할 수 있다!! $\blacksquare$

### Morera's Theorem

<span class="statement-title">Theorem.</span><br/>

Let $f(z)$ be a continuous function in a domain $D$.

If

$$
\oint_C f(z) dz = 0
$$

for any closed contour in $D$, then $f(z)$ is analytic in $D$.

\<모레라의 정리\>는 \<Cauchy-Goursat Theorem\>의 역을 기술한 정리다.

<br/><span class="statement-title">*Proof*.</span><br/>

$$
\oint_C f(z) dz = 0 \implies f(z) \; \text{is primative.}
$$

따라서, $f(z)$에 대한 Anti-Derivative Function $F(z)$가 존재해 아래가 성립한다.

$$
F'(z) = f(z)
$$

이때, $F(z)$의 derivative인 $f(z)$가 continuous 하므로 $F(z)$는 analytic function이다. Anti-Derivative $F(z)$가 analytic이므로 derivative인 $f(z)$ 역시 analytic이다! $\blacksquare$



