---
title: "Student's t-distribution"
layout: post
use_math: true
tags: ["Statistics"]
---

2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<div class="proof" markdown="1">

**시리즈: Sampling Distributions**

1. [Sampling Distribution]({{"/2021/04/25/sampling-distribution.html" | relative_url}})
2. [Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt.html" | relative_url}})
3. [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance.html" | relative_url}})
4. [Student's t-distribution]({{"/2021/04/27/student-t-distribution.html" | relative_url}}) 👀
5. [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}})
6. [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile.html" | relative_url}})

</div>

[toc]

- Student's t-distribution
- Sampling Distribution of Mean (unknown $\sigma^2$)

<hr/>

# Student's t-distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Student's t-distribution<br>

Let $Z \sim N(0, 1)$, and $V \sim \chi^2(n)$, and $Z \perp V$.

Define $T$ as

$$
T := \frac{Z}{\sqrt{V / n}}
$$

Then, the distribution of $T$ is called \<student's t-distribution of $n$ degrees of freedom\>.

</div>

<div class="statement" markdown="1">

<span class="statement-title">Remark.</span><br>

1\. The pdf of $t$-distribution is 

$$
f(x) 
= \frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)} \left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2}
$$

for $x \in \mathbb{R}$.

(걱정하지 마라, 우리가 \<t-distribution\>의 분포를 외워서 적용할 일은 절대 없다!)

</div>

<div class="statement" markdown="1">

2\. $t$-distribution would converges to normal distribution as $n \rightarrow \infty$.

$$
t(x; n) \rightarrow \frac{1}{\sqrt{2\pi}} \cdot \exp(-x^2 / 2)
$$

<div class="img-wrapper" style="margin: 10px">
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20200525113955/f126.png" height="250">
<p>$n$이 커질 수록 정규 분포에 가까워진다!</p>
</div>

</div>

<div class="proof" markdown="1">

<span class="statement-title">proof.</span><br>

$$
f(x) 
= \frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)} \cdot \left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2}
$$

항목 별로 극한을 생각해보자.

[Step 1]

더 쉬운 녀석인 오른쪽 녀석부터 하겠다.

$$
\left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2}
$$

$\exp(x)$ 함수의 정의를 이용하자.

$$
\left( 1 + \frac{x^2}{n} \right)^{-n/2} \cdot \left( 1 + \frac{x^2}{n} \right)^{-1/2}
$$

$n \rightarrow \infty$일 때, 왼쪽은 

$$
\left( 1 + \frac{x^2}{n} \right)^{-n/2} \rightarrow \exp(-x^2 / 2)
$$

오른쪽은

$$
\left( 1 + \frac{x^2}{n} \right)^{-1/2} \rightarrow (1)^{-1/2} = 1
$$

따라서,

$$
\left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2} \rightarrow \exp(-x^2 / 2)
$$

<br/>

[Step 2]

$$
\frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)}
$$

감마 함수는 아래와 같이 생겼다.

$$
\Gamma(\alpha) = \int^{\infty}_0 t^{\alpha - 1} e^{-t} dt \quad \text{for} \; \alpha > 0
$$

여기서 그냥 받아들여야 하는 부분이 등장하는데, 바로 \<스털링 근사; Stirling's Approximation\>다.

\<스털링 근사\>에 따르면, 큰 $k$에 대해 아래가 성립한다.

$$
\Gamma(k) \approx \sqrt{\frac{2\pi}{k}} \left( \frac{k}{e} \right)^k
$$

이 사실을 바탕으로 수식을 전개하면,

$$
\begin{aligned}
\frac{\Gamma\left(\dfrac{k+1}{2}\right)}{\Gamma\left( \dfrac{k}{2} \right)}
&= \frac{
  \sqrt{\frac{1}{k + 1}} \left( \frac{k + 1}{2e} \right)^{(k+1) / 2}
  }{
    \sqrt{\frac{1}{k}} \left( \frac{k}{2e} \right)^{k/2}
  } \\
&= 
\sqrt{\frac{k}{k+1}}
\cdot \frac{(2e)^{k/2}}{(2e)^{(k+1)/2}}
\cdot \frac{(k+1)^{(k+1)/2}}{(k)^{k/2}} \\
&= 
\sqrt{\frac{k}{k+1}}
\cdot \frac{1}{\sqrt{2e}}
\cdot \left(\frac{k+1}{k}\right)^{k/2}
\cdot \sqrt{k+1} \\
&=
\sqrt{\frac{k}{2e}}
\cdot \left(1 + \frac{1}{k}\right)^{k/2} \\
\end{aligned}
$$

이제 수식을 합치면,

$$
\begin{aligned}
\frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)}
&= 
\frac{1}{\sqrt{n \pi}} \cdot \sqrt{\frac{n}{2e}}
\cdot \left(1 + \frac{1}{n}\right)^{n/2} \\
&\rightarrow
\frac{1}{\sqrt{\pi}} \cdot \sqrt{\frac{1}{2e}}
\cdot \sqrt{e} \\
&= \frac{1}{\sqrt{2\pi}}
\end{aligned}
$$

[Final]

종합하면,

$$
\begin{aligned}
f(x) 
&= \frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)} \cdot \left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2} \\
&\rightarrow \frac{1}{\sqrt{2\pi}} \cdot \exp(-x^2 / 2)
\end{aligned}
$$

</div>

<div class="statement" markdown="1">

3\. We define $t_\alpha$ as the number $x$ s.t. $P(T \ge x) = \alpha$.

</div>

<hr/>

# Sampling Distribution of Mean (unknown $\sigma^2$)

Sample Mean $\bar{X}$에 대한 분포를 계속 살펴보자. 이전의 "[Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt.html" | relative_url}})" 포스트에선 population variance $\sigma^2$에 대한 값을 정확히 알고 있었다. 

$$
Z = \frac{\bar{X} - \mu}{\sigma / \sqrt{n}} \sim N(0, 1)
$$

이번에는 $\sigma^2$를 모르는 상태에서 Sample Mean $\bar{X}$의 분포를 모델링 해보자.

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X_1, \dots, X_n$ be a random sample from $N(\mu, \sigma^2)$[^1].

Let $T := \dfrac{\overline{X} - \mu}{S / \sqrt{n}}$, then $T$ has a t-distribution with $(n-1)$ dof.

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$$
\begin{aligned}
T 
&= \frac{\overline{X} - \mu}{S / \sqrt{n}} \\
&= \frac{\overline{X} - \mu}{\sigma / \sqrt{n}} \cdot \frac{\sigma / \cancel{\sqrt{n}}}{S / \cancel{\sqrt{n}}} \\
&= \dfrac{\left(\dfrac{\overline{X} - \mu}{\sigma / \sqrt{n}}\right)}{S / \sigma}
\end{aligned}
$$

이때, 분자인 $\dfrac{\overline{X} - \mu}{\sigma / \sqrt{n}}$는 $N(0, 1)$의 분포를 따르고, 

분모인 $S / \sigma$는

$$
\frac{S}{\sigma} = \sqrt{\frac{(n-1) \cdot S^2}{\sigma^2}\cdot \frac{1}{(n-1)}}
$$

인데 이때, $\dfrac{(n-1)\cdot S^2}{\sigma^2}$가 $\chi^2(n-1)$를 따르므로.

식을 정리하면 분포 $T$는 아래와 같은데,

$$
T = \frac{Z}{\sqrt{V/(n-1)}}
$$

$Z \sim N(0, 1)$이고 $V \sim \chi^2(n-1)$이다. 그리고 Sample Variance와 Sample Mean이 서로 독립이므로, $Z \perp V$이다.

따라서, $T$는 dof가 $n-1$인 t-distribution이다. $\blacksquare$

</div>

<hr/>

## Examples

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/t-distribution-example-1.png" | relative_url }}" width=550>
</div>

<div class="math-statement" markdown="1">

[population] $X$ follows Normal Distribution, $\mu = 500$, $\sigma$: unknown

[sample] $n=25$, $\bar{x} = 518$, $s^2 = 40^2$

[t-test] check weather or not $t \in [-t_{0.05}, t_{0.05}]$

Let $T := \dfrac{\bar{x} - \mu}{S / \sqrt{n}} \overset{D}{=} t(n-1) = t(24)$

t-value is 

$$
\frac{\bar{x} - \mu}{s/\sqrt{n}} = \frac{518-500}{40/5} = 2.25
$$

Here, $t_{0.05}(24) = 2.172$, and $t_{0.05} < 2.25$.

t-value가 $t_{0.05}$보다 크므로 유의하다. 그래서 population mean $\mu$는 500보다 더 클지도 모른다. $\blacksquare$

</div>

<hr/>

# 맺음말

이어지는 포스트에서는 두 sample variance를 비교할 때 쓰는 \<F-distribution\>를 살펴본다.

$$
F := \frac{S_1^2 / \sigma_1^2}{S_2^2 / \sigma_2^2} = F(n_1 - 1, n_2 -1)
$$

👉 [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}})

<br/>

\<t-distribution\>은 뒤에 나오는 \<Interval Estimation\>에서 다시 볼 예정이다.

👉 [t-test: Estimate $\mu$ when $\sigma^2$ is unknown]({{"/2021/05/06/interval-estimation.html#t-test-estimate-mu-when-sigma2-is-unknown" | relative_url}})

<br/>

개인적으로 여기가 \<t-value\>, \<z-value\>, \<p-value\>가 헷갈리는 지점이라고 생각한다. 만약, 두 개념이 어떻게 다르고, 또 언제 등장하는지 비교하고 싶다면, 아래의 포스트를 참고하길 바란다.

👉 [Values in Statistics]({{"/2021/06/05/values-in-statistics.html"}})

# References

- [Convergence of Student's t-distribution to a standard normal](https://math.stackexchange.com/questions/3240536/convergence-of-students-t-distribution-to-a-standard-normal)

<hr/>

[^1]: \<t-distribution\>을 쓰기 위해선, 샘플이 반드시 normal 분포로부터 추출되어야 한다!! 💥