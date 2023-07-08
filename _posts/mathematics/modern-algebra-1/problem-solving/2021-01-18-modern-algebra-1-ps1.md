---
title: "Modern Algebra I - PS1"
toc: true
toc_sticky: true
categories: ["Modern Algebra1", "Problem Solving"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<div class="statement" markdown="1">

Let $R$ be an integral domian. A non-zero element $p \in R$ is called prime if $\left< p \right>$ is a prime ideal.

(a) A prime element is irreducible.

(b) In a PID, a non-zero element is prime iff it is irreducible.

(c) Consider $\mathbb{Z}[\sqrt{-5}]$ with norm $N(a+b\sqrt{-5}) = a^2 + 5b^2$. Then $\alpha = 2 + \sqrt{-5}$ is irreducible but not prime. Thus, $\mathbb{Z}[\sqrt{-5}]$ is not a PID.

</div>

<br>

<big>(a) A prime element is irreducible.</big>

<div class="math-statement" markdown="1">

Supp. $p$ is a prime.

Then $p \mid ab$ means "$p \mid a$ or $p \mid b$".

For $p = ab$, $p \mid ab$, and this mean $p \mid a$ or $p \mid b$.

Let assume $p \mid a$, then $a = pr$ for some $r \in R$.

Then

$$
\begin{aligned}
    p &= ab \\
    p &= (pr)b \\
    p(1 - rb) &= 0 \\
    1 &= rb
\end{aligned}
$$

Thus, $b$ is a unit.

In this way, if $p \mid b$, then $a$ is a unit.

Therefore, if $p = ab$, then $a$ or $b$ is a unit. $\blacksquare$

</div>

<br>

<big>(b) In a PID, a non-zero element is prime iff it is irreducible.</big>

<div class="math-statement" markdown="1">

($\implies$)

One side has already been proved.

<br>

($\impliedby$)

Supp. $p$ is irreducible.

Then, $\left< p \right>$ is a principal ideal. ($\because$ PID)

Check if $\left< p \right>$ is a Maximal Ideal.

Let's assume $\left< p \right> < \left< q \right> \trianglelefteq R$.

Because $\left< p \right> < \left< q \right>$, $p \in \left< q \right>$.

This means $p = qr$ for some $r \in R$.

Since $p$ is irreducible, $q$ or $r$ is a unit.

If $r$ is a unit, then $p$ and $q$ are associate, and $\left< p \right> = \left< q \right>$.

If $q$ is a unit, $1 \in \left< q \right>$, then $\left< q \right> = R$.

Therefore, $\left< p \right>$ is a Maximal Ideal.

If $\left< p \right>$ is a Maximal Ideal, then $\left< p \right>$ is a Prime Ideal. [link]({{"2020/12/27/maximal-and-prime-ideal.html#maximal-ideal-implies-prime-ideal" | relative_url}})

If $\left< p \right>$ is a Prime Ideal, $p$ is a prime.

Therefore, if $p$ is irreducible, then $p$ is prime. $\blacksquare$

</div>

<br>

<big>(c) Consider $\mathbb{Z}[\sqrt{-5}]$ with norm $N(a+b\sqrt{-5}) = a^2 + 5b^2$. Then $\alpha = 2 + \sqrt{-5}$ is irreducible but not prime. Thus, $\mathbb{Z}[\sqrt{-5}]$ is not a PID.</big>

<div class="math-statement" markdown="1">

Consider $9 = (2 + \sqrt{-5})(2 - \sqrt{-5})$.

Then, $\alpha \mid 9$.

If $\alpha$ is a prime, then $\alpha$ should divide $3$ because $9 = 3 \times 3$.

But, $3$ and $2 + \sqrt{-5}$ are not associates. So, $\alpha \not\mid 3$.

Therefore, $\alpha$ is not prime.

<br>

Check if $\alpha$ is irreducible.

If $\alpha = \beta \gamma$ for some non-units $\beta, \gamma \in \mathbb{Z}[\sqrt{-5}]$,

then $9 = N(\alpha) = N(\beta)N(\gamma)$.

Because $\beta$ and $\gamma$ are not unit, so $N(\beta)$ and $N(\gamma)$ are 1.

Therefore $N(\beta) = N(\gamma) = 3$.

However, there's no element in $\mathbb{Z}[\sqrt{-5}]$ with norm 3. Therefore $\alpha$ is irreducible. $\blacksquare$

<br>

If PID, irreducible is equivalent to prime, but in $\mathbb{Z}[\sqrt{-5}]$, there exist counter-example of that statement.

Therefore, $\mathbb{Z}[-5]$ is not a PID.

</div>
