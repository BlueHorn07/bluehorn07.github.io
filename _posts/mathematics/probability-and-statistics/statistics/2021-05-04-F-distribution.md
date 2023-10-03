---
title: "F-distribution"
toc: true
toc_sticky: true
categories: ["Statistics"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<div class="proof" markdown="1">

**시리즈: Sampling Distributions**

1. [Sampling Distribution]({{"/2021/04/25/sampling-distribution.html" | relative_url}})
2. [Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt.html" | relative_url}})
3. [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance.html" | relative_url}})
4. [Student's t-distribution]({{"/2021/04/27/student-t-distribution.html" | relative_url}})
5. [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}}) 👀
6. [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile.html" | relative_url}})

</div>

<hr/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> F-distribution<br>

If $V_1 \sim \chi^2(n_1)$ and $V_2 \sim \chi^2(n_2)$ are independent,

then $F := \dfrac{V_1/n_1}{V_2/n_2}$ is called **\<Snedecor's F-distribution\>**[^1] with degrees of freedom $n_1$ and $n_2$, and denoted as

$$
F \sim F(n_1, n_2)
$$

</div>

ps) 일반적으로, $F(n_1, n_2) \ne F(n_2, n_1)$이다. <small>F-distribution은 non-symmetric이라는 말.</small>


<div class="img-wrapper">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/F-distribution_pdf.svg/488px-F-distribution_pdf.svg.png" width="300px">
  <p>
  Image from <a href="https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/F-distribution_pdf.svg/488px-F-distribution_pdf.svg.png">Wikipedia</a>
  </p>
</div>

<br/>

<div class="notice" markdown="1">

<span class="statement-title">Remark.</span><br>

1\. The order of $n_1$ and $n_2$ is very important.

In fact we have $F(n_1, n_2) \overset{D}{=} \dfrac{1}{F(n_2, n_1)}$.

</div>

<div class="notice" markdown="1">

2\. Let $f_\alpha (n_1, n_2)$ be the number $x$ such that $\alpha = P\left(F(n_1, n_2) \ge x\right)$.

Here, we have $f_{1-\alpha}(n_1, n_2) = \dfrac{1}{f_{\alpha}(n_2, n_1)}$

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Quick Proof*.</span><br>

$$
\begin{aligned}
  1 - \alpha
  &= P \left( F(n_1, n_2) \ge f_{1-\alpha}(n_1, n_2) \right) \\
  &= P \left( \frac{1}{f_{1-\alpha}(n_1, n_2)} \ge \frac{1}{F(n_1, n_2)}\right) \\
  &= P \left( \frac{1}{f_{1-\alpha}(n_1, n_2)} \ge F(n_2, n_1) \right) \\
  &= 1 - P \left( F(n_2, n_1) > \frac{1}{f_{1-\alpha}(n_1, n_2)} \right)
\end{aligned}
$$

따라서,

$$
\begin{aligned}
  \alpha
  &= P \left( F(n_2, n_1) > \frac{1}{f_{1-\alpha}(n_1, n_2)} \right) \\
  &= P \left( F(n_2, n_1) > f_{\alpha}(n_2, n_1) \right)
\end{aligned}
$$

따라서,

$$
f_\alpha (n_1, n_2) = \frac{1}{f_{1-\alpha}(n_2, n_1)}
$$

$\blacksquare$

</div>

<br/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Supp. we have two independent random samples $X_1, \dots, X_{n_1}$ from $N(\mu_1, \sigma_1^2)$ and $Y_1, \dots, Y_{n_2}$ from $N(\mu_2, \sigma_2^2)$.

Let $S_1^2 = \dfrac{\sum^{n_1}_{i=1} (X_i - \bar{X})^2}{n_1 - 1}$ and $$S_2^2 = \dfrac{\sum^{n_2}_{i=1} (Y_i - \bar{Y})^2}{n_2 - 1}$$.

Note that $(n_1 - 1)S_1^2/\sigma_1^2 \sim \chi^2 (n_1 - 1)$ and $(n_2 - 1)S_2^2/\sigma_2^2 \sim \chi^2 (n_2 - 1)$.

Then,

$$
F := \frac{S_1^2 / \sigma_1^2}{S_2^2 / \sigma_2^2} \sim F(n_1 - 1, n_2 - 1)
$$

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$$
F := \frac{S_1^2 / \sigma_1^2}{S_2^2 / \sigma_2^2} = \frac{\frac{(n_1 - 1) S_1^2}{\sigma_1^2} / (n_1 - 1)}{\frac{(n_2 - 1) S_2^2}{\sigma_2^2} / (n_2 - 1)}
$$

이때, $\dfrac{(n_1 - 1) S_1^2}{\sigma_1^2} \sim \chi^2 (n_1 - 1)$이므로 \<F-distribution\>의 정의에 따라

$$
F := \frac{S_1^2 / \sigma_1^2}{S_2^2 / \sigma_2^2} = \frac{V_1 / (n_1 - 1)}{V_2 / (n_2 - 1)} \sim F(n_1 - 1, n_2 - 1)
$$

</div>

<hr/>

### Examples

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/F-distribution-example-1.png" | relative_url }}" width=550>
</div>

<div class="math-statement" markdown="1">

$n_1 = 21$, $n_2 = 31$

Claim: $\sigma_1^2/\sigma_2^2 = 2$ but, for sample variances, $S_1^2/S_2^2 = 4 > 2$.

$$
\begin{aligned}
&P\left(S_1^2/S_2^2 \ge 4 \quad \text{when} \quad \sigma_1^2/\sigma_2^2 = 2\right) \\
&= P \left( \frac{S_1^2 / \sigma_1^2}{S_2^2 / \sigma_2^2} \ge 4 \cdot \frac{1}{2} = 2 \right) \\
&= P(F(20, 30) \ge 2)
\end{aligned}
$$

Here, $f_{0.05}(20, 30)=1.93$ and $f_{0.01}(20, 30) = 2.55$.

The the value of $2$ is btw $1.93$ and $2.55$.

Therefore,

$$
P(F(20, 30) \ge 2) \in [0.01, 0.05]
$$

이것의 의미는 sample variance의 비율이 4가 되는 확률은 지극히 낮다는 것이다. 그런데 이것이 실제로 관측되었으므로, 우리의 가정인 $H_0: \sigma_1^2 / \sigma_2^2 = 2$를 기각하고, 둘의 population variance의 비율이 더 커져야 한다는 대립 가설 $H_1: \sigma_1^2 / \sigma_2^2 > 2$를 채택해야 한다. $\blacksquare$

</div>

<hr/>

지금까지 우리는 population distribution의 parameter인 "평균"과 "분산"에 대해 추정했다. 이어지는 포스트에서는 sample로부터 얻는 분포인 **\<EDF; Empirical Distribution Function\>**으로부터 population distribution을 추정해본다. 이 과정에서 쓰는 것이 바로 **\<Quantile; 분위수\>**이다!

👉 [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile.html" | relative_url}})

<hr/>

[^1]: "[세네데커] F-분포"라고 읽는 것 같다.