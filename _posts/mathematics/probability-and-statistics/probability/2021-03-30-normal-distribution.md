---
title: "Normal Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform Distribution](/2021/03/29/uniform-distribution)
2. [Normal Distribution](/2021/03/30/normal-distribution) 👀
3. [Exponential Distribution](/2021/03/31/exponential-distribution)
4. [Gamma Distribution](/2021/04/05/gamma-distribution)
5. [Chi-square Distribution](/2021/04/06/chi-square-distribution)
6. [Beta Distribution](/2021/04/07/beta-distribution)
7. [Log-normal Distribution](/2021/04/08/log-normal-distribution)
8. [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

</div>

# Normal Distribution (or Gaussian Distribution)

<span class="statement-title">Definition.</span> Gaussian Distribution<br>

Let $\mu \in \mathbb{R}$ and $\sigma > 0$. We say that $X$ has a \<**normal distribution**\> with mean $\mu$ and variance $\sigma^2$ if its pdf $f(x; \mu, \sigma^2)$ is given by

$$
f(x; \mu, \sigma^2) = \frac{1}{\sqrt{2\pi\sigma^2}} \exp \left( - \frac{(x-\mu)^2}{2\sigma^2}\right) \quad \text{for} \; x \in \mathbb{R}
$$

And we denote $X \sim N(\mu, \sigma^2)$.

이때, 만약 $\mu = 0$, $\sigma^2 = 1$이라면, 우리는 $X$는 \<**<u>standard</u> normal RV**\>라고 부른다.

$$
f(x; 0, 1) = \frac{1}{\sqrt{2\pi}} \exp \left( -\frac{x^2}{2} \right)
$$

이제 Normal Distribution의 pdf $f(x; \mu, \sigma^2)$가 유효한 pdf인지 검증해보자.

$$
\int^{\infty}_{-\infty} \frac{1}{\sqrt{2\pi\sigma^2}} \exp\left( -\frac{(x-\mu)^2}{2\sigma^2}\right) \; dx \overset{?}{=} 1
$$

<span class="statement-title">*Proof*.</span><br>

<div class="proof" markdown="1">

Let $A$ as

$$
A = \int^{\infty}_{-\infty} f(x) \; dx = \frac{1}{\sqrt{2\pi\sigma^2}} \int^{\infty}_{\infty} \exp\left( -\frac{(x-\mu)^2}{2\sigma^2}\right) dx
$$

Let $z = \dfrac{x-\mu}{\sigma}$, then

$$
A = \frac{1}{\sqrt{2\pi}} \int^{\infty}_{-\infty} \exp\left( -\frac{z^2}{2}\right) dx
$$

then,

$$
\begin{aligned}
A^2 &= \frac{1}{2\pi} \int^{\infty}_{-\infty} \int^{\infty}_{-\infty} f(x) f(y) \; dxdy \\
&= \frac{1}{2\pi} \int^{\infty}_{-\infty} \int^{\infty}_{-\infty} \exp\left( -\frac{x^2 + y^2}{2}\right) \; dxdy
\end{aligned}
$$

여기에서 적분 방식을 $xy$-coordinate에서 $r\theta$-coordinate로 바꿔보자.

$$
\begin{aligned}
    x &= r \cos \theta \\
    y &= r \sin \theta
\end{aligned}
$$

then,

$$
A^2 = \frac{1}{2\pi} \int^{2\pi}_0 \int^{\infty}_0 \exp \left( - \frac{r^2}{2}\right) \cdot r \;  drd\theta
$$

위의 적분은 쉽게 해결할 수 있다.

$$
\begin{aligned}
A^2 &= \frac{1}{2\pi} \int^{2\pi}_0 \left[ - \exp \left( - \frac{r^2}{2} \right) \right]^{\infty}_0 \; d\theta \\
&= \frac{1}{2\pi} \int^{2\pi}_0 1 \; d\theta \\
&= \frac{1}{2\pi} \cdot 2\pi = 1
\end{aligned}
$$

</div>

두번째 질문은 \<normal distribution\>에서의 CDF를 구하는 것이다. 논의의 편의를 위해 $N(\mu, \sigma^2)$ 대신에 $Z \sim N(0, 1)$로 대신 살펴보자.

$$
F(x) = P(Z \le x) = \int^x_{-\infty} \frac{1}{\sqrt{2\pi}} \exp \left( - \frac{z^2}{2}\right) \; dz
$$

우선 확실하게 알 수 있는 사실은

- $F(0) = P(Z \le 0) = 0.5$
- $F(-\infty) = P(Z \le -\infty) = 0$
- $F(\infty) = P(Z \le \infty) = 1$

라는 점이다.

\<normal distribution\>가 연속확률분포이기 때문에 확률을 구하기 위해선 반드시 CDF를 알아야 한다. 그러나, 우리는 \<normal distribution\>의 CDF를 직접 적분해서 구하지 않는다. 교재 뒤편의 Appendix의 표를 보고 구하면 된다!! 🤩 아래의 링크에 이 표의 링크를 달아놨다. \<normal distribution\>의 이런 표를 \<**standard normal table**\> 또는 \<**Z table**\>이라고 한다.

👉 [Wikiepeida/Standard normal table](https://en.wikipedia.org/wiki/Standard_normal_table)

<br/>

<span class="statement-title">Theorem.</span><br>

Let $X \sim N(\mu, \sigma^2)$, then

- $E[X] = \mu$
- $\text{Var}(X) = \sigma^2$

위의 명제를 증명해야 하지만, 쉽게 할 수 있을 것 같아서 생략하겠다.

<br/>

이번에는 \<normal distribution\>과 \<standard normal distribution\>의 관계를 좀 살펴보자.

<span class="statement-title">Theorem.</span><br>

1\. If $X \sim N(\mu, \sigma^2)$, then $Z := \dfrac{X - \mu}{\sigma} \sim N(0, 1)$.

2\. If $Z \sim N(0, 1)$, then $X := \sigma Z + \mu ~ N(\mu, \sigma^2)$

이 부분은 간단하게 증명을 살펴보자. 1번 명제는 $Z$가 normal 분포를 가지는 걸 유도하면 된다.

<div class="proof" markdown="1">

CDF of $Z$ is $P(Z \le z) = P\left( \dfrac{X - \mu}{\sigma} \le z \right)$, then we can shift and scaling $Z$ as

$$
P\left( \dfrac{X - \mu}{\sigma} \le z \right) = P ( X \le \sigma z + \mu)
$$

Let's say cdf of $Z$ as $F_Z (z) = F_X (\sigma z + \mu)$, then to get pdf of $Z$, take derivative

$$
\begin{aligned}
f(z) &= \frac{d}{dz} F_X (\sigma z + \mu) = \sigma f_x (\sigma z + \mu) \\
&= \sigma \cdot \left( \frac{1}{\sqrt{2\pi\sigma^2}} \exp \left( - \frac{(\sigma z + \mu - \mu)^2}{2\sigma^2}\right) \right) \\
&= \frac{1}{\sqrt{2\pi}} \exp \left(  -\frac{z^2}{2} \right)
\end{aligned}
$$

$Z$의 pdf가 $N(0, 1)$이므로 $Z \sim N(0, 1)$이다. $\blacksquare$

</div>

<span class="statement-title">Remark.</span><br>

1\. If $Z \sim N(0, 1)$, the \<standard normal\>, then its **pdf** and **cdf** are commonly denoted by $\varphi(z)$ and $\Phi(z)$.

2\. The value of $\Phi(z)$ is listed on the Appendix table.

3\. $$\Phi(-z) = 1 - \Phi(z)$$

4\. If $X \sim N(\mu, \sigma^2)$, then we can normalize $X$ to $Z$.

<hr/>

## Normal Approximation to the Binomial

우리는 \<Binomial Distribution\>이 충분히 작은 확률 $p \ll 1$과 충분히 큰 trial $1 \ll n < \infty$라면, 이것을 \<Poisson Distribution\>으로 근사해서 사용할 수 있었다.

<span class="statement-title">Example.</span><br>

Let $X \sim \text{BIN}(100, 0.02)$, then get the value of $P(X = 39)$ is hard. (0.02를 39번 곱하면 0에 가까워짐 등등)

However, if we approximate it to $\text{POI}(2)$, then $P(x = 39) = e^{-2} \frac{2^{39}}{39!}$.

그런데, 이런 \<Binomial Distribution\>을 좀더 확장해 \<Normal Distribution\>으로 근사할 수 있음을 기술하는 정리가 있다!! 🤩 이 경우는 "충분히 큰 trial"이라는 조건만 충족하면 된다!

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> De Moivre-Laplace Central Limit Theorem<br>

Let $X \sim \text{BIN}(n, p)$, then we have

$$
\lim_{n \rightarrow \infty} P\left( \frac{X - np}{\sqrt{npq}} \le x \right) = \Phi(x)
$$

where $\Phi(x)$ is CDF of normal $N(0, 1)$.

※ Note that this is one special case of CLT.

</div>

<br/>

이 부분은 예제를 통해 감을 익히는 걸 추천한다. 2-3 문제만 풀어봐도 금방 감을 잡을 수 있다.

<hr/>

이어지는 포스트에서는 좀더 다양하고, 엄청난 분포들을 만나게 된다.

- [Exponential Distribution](/2021/03/31/exponential-distribution)
- [Gamma Distribution](/2021/04/05/gamma-distribution)
- [Chi-square, Beta and Log-normal Distribution]({{"/2021/04/06/chi-and-beta-and-lognormal-distribution" | relative_url}})


