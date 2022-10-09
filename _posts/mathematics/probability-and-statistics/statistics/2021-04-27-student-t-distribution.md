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

<hr/>

Sample Mean $\bar{X}$에 대한 분포를 계속 살펴보자. 이전의 "[Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt.html" | relative_url}})" 포스트에선 population variance $\sigma^2$에 대한 값을 정확히 알고 있었다. 

$$
Z = \frac{\bar{X} - \mu}{\sigma / \sqrt{n}} \sim N(0, 1)
$$


이번에는 $\sigma^2$를 모르는 상태에서 Sample Mean $\bar{X}$의 분포를 모델링 해보자.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Student's t-distribution<br>

Let $Z \sim N(0, 1)$, and $V \sim \chi^2(n)$, and $Z \perp V$.

Define $T$ as

$$
T := \frac{Z}{\sqrt{V / n}}
$$

Then, the distribution of $T$ is called \<student's t-distribution of $n$ degrees of freedom\>.

</div>

<span class="statement-title">Remark.</span><br>

1\. The pdf is 

$$
f(x) 
= \frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)} \left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2} \quad \text{for} \quad x \in \mathbb{R}
$$

(걱정하지 마라, 우리가 \<t-distribution\>의 분포를 외워서 적용할 일은 절대 없다!)

2\. As $n \rightarrow \infty$, 

$$
\begin{aligned}
f(x) 
&= \cancelto{\frac{1}{\sqrt{2\pi}}}{\frac{\Gamma\left(\dfrac{n+1}{2}\right)}{\sqrt{n\pi} \cdot \Gamma\left( \dfrac{n}{2} \right)}} \cancelto{\; \exp(-x^2 / 2)}{\left( 1 + \frac{x^2}{n} \right)^{-(n+1)/2}} \\
&= \frac{1}{\sqrt{2\pi}} \exp(-x^2 / 2)
\end{aligned}
$$

<div class="img-wrapper" style="margin: 10px">
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20200525113955/f126.png" height="250">
<p>$n$이 커질 수록 점점 Z-distribution에 가까워진다!</p>
</div>

3\. We define $t_\alpha$ as the number $x$ s.t. $P(T \ge x) = \alpha$.

<br/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X_1, \dots, X_n$ be a random sample from $N(\mu, \sigma^2)$[^1].

Let $T := \dfrac{\overline{X} - \mu}{S / \sqrt{n}}$, then $T$ has a t-distribution with $(n-1)$ dof.

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$$
\begin{aligned}
T &= \frac{\overline{X} - \mu}{S / \sqrt{n}} \\
  &= \frac{\overline{X} - \mu}{\sigma / \sqrt{n}} \cdot \frac{\sigma / \cancel{\sqrt{n}}}{S / \cancel{\sqrt{n}}} \\
  &= \dfrac{\left(\dfrac{\overline{X} - \mu}{\sigma / \sqrt{n}}\right)}{S / \sigma}
\end{aligned}
$$

이때, 분자인 $\dfrac{\overline{X} - \mu}{\sigma / \sqrt{n}}$는 $N(0, 1)$의 분포를 따르고, 

분모인 $S / \sigma$는

$$
\frac{S}{\sigma} = \sqrt{\frac{(n-1) \cdot S^2}{\sigma^2}\cdot \frac{1}{(n-1)}}
$$

인데 이때, $\dfrac{(n-1)\cdot S^2}{\sigma^2}$가 $\chi^2(n-1)$를 따르므로, 식을 정리하면,

$$
T = \frac{Z}{\sqrt{V/(n-1)}} \quad \text{where} \quad Z \sim N(0, 1) \quad \text{and} \quad V \sim \chi^2(n-1)
$$

그리고 Sample Variance와 Sample Mean을 서로 독립이므로, $Z \perp V$이다.

따라서, $T$는 dof가 $n-1$인 t-distribution이다. $\blacksquare$

</div>

<hr/>

### Examples

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

이어지는 포스트에서는 두 sample variance를 비교할 때 쓰는 \<F-distribution\>에 대해 다루도록 하겠다.

👉 [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}})

<br/>

\<t-distribution\>은 뒤에 나오는 \<Interval Estimation\>에서 유용하게 사용된다. 

👉 [t-test: Estimate $\mu$ when $\sigma^2$ is unknown]({{"/2021/05/06/interval-estimation.html#estimate-mu-when-sigma2-is-unknown" | relative_url}})

<br/>

개인적으로 여기가 \<t-value\>, \<z-value\>, \<p-value\>가 헷갈리는 지점이라고 생각한다. 만약, 두 개념이 어떻게 다르고, 또 언제 등장하는지 비교하고 싶다면, 아래의 포스트를 참고하길 바란다.

👉 [Values in Statistics]({{"/2021/06/05/values-in-statistics.html"}})

<hr/>

[^1]: \<t-distribution\>을 쓰기 위해선, 샘플이 반드시 normal 분포로부터 추출되어야 한다!! 💥