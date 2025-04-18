---
title: "Gamma Distribution"
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
2. [Normal Distribution](/2021/03/30/normal-distribution)
3. [Exponential Distribution](/2021/03/31/exponential-distribution)
4. [Gamma Distribution](/2021/04/05/gamma-distribution) 👀
5. [Chi-square Distribution](/2021/04/06/chi-square-distribution)
6. [Beta Distribution](/2021/04/07/beta-distribution)
7. [Log-normal Distribution](/2021/04/08/log-normal-distribution)
8. [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

</div>

# Gamma Function

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Gamma Function<br>

The \<**Gamma Function**\> $\Gamma(\alpha): (0, \infty) \rightarrow (0, \infty)$ is defined as

$$
\Gamma(\alpha) = \int^{\infty}_0 t^{\alpha - 1} e^{-t} dt \quad \text{for} \; \alpha > 0
$$

</div>

감마 함수는 1730년 오일러가 제시한 함수로 수학계에서 정말 유명한 함수다. 감마 함수는 정수에서 정의되는 \<factorial\> $n!$을 실수, 복소수 영역까지 확장하는 시도에서 등장한 함수이다. 즉, "factorial"을 일반화 한 것이라고 생각하면 된다.

<span class="statement-title">Remark.</span><br>

1\. base case

$$
\begin{aligned}
\Gamma(1) &= \int^{\infty}_0 t^{0} e^{-t} dt = \int^{\infty}_0 e^{-t} dt \\
&= \left.- e^{-t} \right]^{\infty}_0 = 1
\end{aligned}
$$

2\. successive case

$$
\begin{aligned}
\Gamma(\alpha + 1) &= \int^{\infty}_0 t^{\alpha} e^{-t} dt \\
&= \cancel{\left. t^{\alpha} (- e^{-t}) \right]^{\infty}_0} + \alpha \int^{\infty}_0 t^{\alpha - 1} e^{-t} dt \\
&= \alpha \Gamma(\alpha)
\end{aligned}
$$

3\. factorial

$$
\begin{aligned}
\Gamma(n) &= (n-1) \cdot \Gamma(n-1) = (n-1)(n-2) \cdot \Gamma(n-2) = \cdots \\
&= \left((n-1)(n-2) \cdots 1\right) \cdot \Gamma(1) \\
&= (n-1)!
\end{aligned}
$$

4\. (special case) normal distribution

$$
\begin{aligned}
\Gamma(1/2) &= \int^{\infty}_0 t^{-\frac{1}{2}} e^{-t} dt \\
&= \int^{\infty}_0 \frac{e^{-t}}{\sqrt{t}} dt
\end{aligned}
$$

여기서 $t = \dfrac{x^2}{2}$로 설정해보자. 그러면, $dt = x \; dx \iff \dfrac{dt}{\sqrt{2t}} = dx$ 이므로

$$
\begin{aligned}
\Gamma(1/2) &= \int^{\infty}_0 \frac{e^{-t}}{\sqrt{t}} dt \\
&= \sqrt{2} \int^{\infty}_0 e^{-\frac{x^2}{2}} dx \\
&= \sqrt{2} \cdot \sqrt{2\pi} \cdot \left( \frac{1}{\sqrt{2\pi}} \int^{\infty}_0 e^{-\frac{x^2}{2}} dx \right) \\
&= \sqrt{2} \cdot \sqrt{2\pi} \cdot 0.5 = \sqrt{\pi}
\end{aligned}
$$

# Standard Gamma Distribution

신기하게도 Gamma 함수 $\Gamma(\alpha)$로부터 아래와 같은 확률 분포를 정의할 수 있다.

$$
f(x; \alpha) = \frac{x^{\alpha - 1} e^{-x}}{\Gamma(\alpha)} \quad \text{for} \; x \ge 0
$$

이것은 확률 분포가 되는데, 그 이유는 $\int f(x; \alpha) = \Gamma(\alpha) / \Gamma(\alpha) = 1$이 되기 때문이다!

이때의 평균과 분산은 아래와 같다.

- $E[X] = \alpha$
- $\text{Var}(X) = \alpha$


# Gamma Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Gamma Distribution<br/>

Let $\alpha > 0$ and $\beta > 0$. We say that $X$ has a \<**Gamma Distribution**\> with a shape parameter $\alpha$ and a scale parameter $\beta$, if its pdf is given by

$$
f(x; \alpha, \beta)
= \begin{cases}
    C_{\alpha, \beta} \cdot x^{\alpha-1} e^{-\frac{x}{\beta}} & \text{for } x > 0 \\
    \quad 0 & \text{else}
\end{cases}
$$

이때, 계수 $C_{\alpha, \beta}$는

$$
C_{\alpha, \beta} \cdot \int^{\infty}_0 x^{\alpha - 1} e^{-\frac{x}{\beta}} \; dx = 1
$$

이 되도록 하는 $C_{\alpha, \beta}$를 선택한다. 이것을 잘 정리하면,

$$
C_{\alpha, \beta}
= \frac{1}{\displaystyle \int^{\infty}_0 x^{\alpha - 1} e^{-\frac{x}{\beta}} \; dx}
= \frac{1}{\Gamma(\alpha) \cdot \beta^{\alpha}}
$$

(▲ 치환적분을 잘 쓰면 위와 같은 결과가 나온다 ㅎㅎ)

감마분포를 다시 기술해보면,

$$
\text{Gamma}(x; \alpha, \beta) = \frac{1}{\Gamma(\alpha) \beta^{\alpha}} \cdot x^{\alpha - 1} e^{-\frac{x}{\beta}} \quad \text{for } x > 0
$$

</div>

<span class="statement-title">Remarks.</span><br/>

1\. $\text{Gamma}(1, \beta) \overset{D}{=} \text{EXP}(\beta)$

If $\alpha = 1$, then

$$
C_{1, \beta} = \frac{1}{\Gamma(1) \beta} = \frac{1}{\beta}
$$

then

$$
f(x) = \frac{1}{\beta} e^{-\frac{x}{\beta}}
$$

따라서, $\text{Gamma}(1, \beta) \overset{D}{=} \text{EXP}(\beta)$.

<br/>

2\. $\text{Gamma}(n, \beta)$; Generalization of \<Exponential Distribution\>

If $\alpha = n$, then $X \sim \text{Gamma}(n, \beta)$ can be used for the amount of time that $n$ events occur.

In fact, $X$ can be written as $X = X_1 + \cdots + X_n$ where $X_i$'s are independent $\text{EXP}(\lambda)$ RVs.

예를 들어, 사건이 $3$번 일어나기까지 걸린 시간에 대한 분포는 $\text{Gamma}(3, \beta)$를 따른다는 말이다! 그래서 \<Exponential Distribution\>에 대한 일반화라고 볼 수도 있다.

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br/>

If $X \sim \text{Gamma}(\alpha, \beta)$, then

- $E[X] = \alpha \beta$
- $\text{Var}(X) = \alpha \beta^2$

</div>

<div class="notice" markdown="1">

<span class="statement-title">*Proof*.</span><br/>

$$
\begin{aligned}
E[X] &= \int^{\infty}_0 x f(x) dx \\
     &= C_{\alpha, \beta} \int^{\infty}_0 x^{\alpha} e^{-x/\beta} dx \\
     &= C_{\alpha, \beta} \cdot \left( \frac{1}{C_{\alpha+1, \beta}} \cancelto{1}{\int^{\infty}_0 C_{\alpha+1, \beta} \; x^{\alpha} e^{-x/\beta} dx} \right) \\
     &= C_{\alpha, \beta} \cdot \frac{1}{C_{\alpha+1, \beta}} \\
     &= \frac{1}{\Gamma(\alpha) \beta^{\alpha}} \cdot \Gamma(\alpha+1) \beta^{\alpha+1} \\
     &= \frac{\Gamma(\alpha+1)}{\Gamma(\alpha)} \beta
\end{aligned}
$$

이때, 감마 함수에 대한 \<Remark 2\>를 사용하면, 결국 $E[X]$는 아래와 같다.

$$
\begin{aligned}
    E[X] &= \frac{\Gamma(\alpha+1)}{\Gamma(\alpha)} \beta = \alpha \beta
\end{aligned}
$$

분산에 대한 증명에서는 $E[X^2]$를 구해야 한다.

$$
\begin{aligned}
E[X^2] &= \int^{\infty}_0 x^2 f(x) dx \\
       &= C_{\alpha, \beta} \int^{\infty}_0 x^{\alpha + 1} e^{-x/\beta} dx \\
       &= C_{\alpha, \beta} \cdot \left( \frac{1}{C_{\alpha+2, \beta}} \cancelto{1}{\int^{\infty}_0 C_{\alpha+2, \beta} \; x^{\alpha + 1} e^{-x/\beta} dx} \right) \\
       &= C_{\alpha, \beta} \cdot \frac{1}{C_{\alpha+2, \beta}} \\
       &= \frac{\Gamma(\alpha + 2)}{\Gamma(\alpha)} \beta^2 \\
       &= \alpha (\alpha - 1) \beta^2
\end{aligned}
$$

따라서,

$$
\begin{aligned}
\text{Var}(X) &= E[X^2] - (E[X])^2 \\
            &= (\alpha^2 - \alpha) \beta^2 - \alpha^2 \beta^2 \\
            &= \alpha \beta^2
\end{aligned}
$$

</div>

## Relation to Poisson Process

Let $N(t)$ be a \<Poisson process\> with rate $\lambda$. Let $X$ be the time to the $n$-th event in the \<Poisson process\>.

Claim: $X \sim \text{Gamma}(n, \beta)$ where $\beta = 1/\lambda$



# 맺음말

이어지는 포스트에서는 감마 분포의 특수한 경우로 꼽히는 \<Chi-square distribution\>, \<Beta distribution\>과 \<Log-normal distribution\>에 대해 다룬다 🤩

- [Chi-square Distribution](/2021/04/06/chi-square-distribution)
- [Beta Distribution](/2021/04/07/beta-distribution)
- [Log-normal Distribution](/2021/04/08/log-normal-distribution)

# references

- [전파 거북이님의 포스트](https://ghebook.blogspot.com/2011/12/gamma-function)

