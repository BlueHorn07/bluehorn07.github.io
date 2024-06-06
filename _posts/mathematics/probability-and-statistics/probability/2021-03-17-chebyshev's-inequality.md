---
title: "Chebyshev's Inequality"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

\<체비쇼프의 부등식; Chebyshev's Inequality\>은 평균 $\mu$로부터 $\lambda$ 거리 이상 멀어진 경우, 즉 tail 상황에 대한 확률의 상한을 제시한다. 즉, "The upper bound of tail probability"인 셈이다. 식은 아래와 같이 정의되어 있다.

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span> Chebyshev's Theroem<br>

Let $X$ be a RV with $\text{Var}(X) < \infty$ and let $\lambda > 0$, then

$$
P \left( \left| X - \mu \right| \ge \lambda \right) \le \frac{\text{Var}(X)}{\lambda^2}
$$

</div>

사실 \<Chebyshev's inequality\>는 평균으로부터 바깥쪽보다는 평균 안쪽에 대한 확률을 구할 때 주로 사용한다.

<div class="example" markdown="1">

<span class="statement-title">Example.</span><br>

Supp. a RV $X$ has $\mu = 8$ and $\sigma^2 = 9$. Show that $P(0 < X < 16) \ge \dfrac{55}{64}$.

</div>

Sol.

$$
\begin{aligned}
P(0 < X < 16) &= P(-8 < X -\mu <8) \\
&= 1 - P(\left| X - \mu \right| \ge 8) \\
&\ge 1 - \frac{\sigma^2}{8^2} = 1 - \frac{9}{64} = \frac{55}{64}
\end{aligned}
$$

<br/>

\<Chebyshev's Theorem\>의 증명은 생각보단 간단하다.

<div class="notice" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$$
P \left( \left| X - \mu \right| \ge \lambda \right) = \int_{\{ x \; : \; \left| x - \mu \right| \ge \lambda \}} 1 \cdot f(x) dx \le \int^{\infty}_{-\infty} 1 \cdot f(x) dx
$$

이때, $P \left( \left\| X - \mu \right\| \ge \lambda \right)$에서 $\left\| X - \mu \right\| \ge \lambda$라는 조건이 있으므로

$$
\left| X - \mu \right| \ge \lambda \iff \left| \frac{X - \mu}{\lambda} \right| \ge 1 \iff \left( \frac{X - \mu}{\lambda} \right)^2 \ge 1
$$

따라서 이를 위의 적분식에 적용하면,

$$
\begin{aligned}
P \left( \left| X - \mu \right| \ge \lambda \right) &\le \int^{\infty}_{-\infty} 1 \cdot f(x) dx \\
&\le \int^{\infty}_{-\infty} \left( \frac{X - \mu}{\lambda} \right)^2 \cdot f(x) dx \\
&= \frac{\text{Var}(X)}{\lambda^2}
\end{aligned}
$$

$\blacksquare$

</div>

<hr/>

\<체비쇼프 부등식\>은 이후에 통계(Statistics) 파트에서 \<Weak Law of Large Numbers\>를 증명할 때, 활용한다. 자세한 내용은 아래의 포스트로 고고~

👉 [Sampleing Distribution of Mean: Weak Law of Large Numbers]({{"/2021/04/26/sampling-distribution-of-mean-and-clt" | relative_url}})
