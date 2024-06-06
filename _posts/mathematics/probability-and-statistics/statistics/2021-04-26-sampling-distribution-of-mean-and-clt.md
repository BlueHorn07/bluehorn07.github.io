---
title: "Sampling Distribution of Mean, and CLT"
toc: true
toc_sticky: true
categories: ["Statistics"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Sampling Distributions**

1. [Sampling Distribution]({{"/2021/04/25/sampling-distribution" | relative_url}})
2. [Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt" | relative_url}}) 👀
3. [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance" | relative_url}})
4. [Student's t-distribution]({{"/2021/04/27/student-t-distribution" | relative_url}})
5. [F-distribution]({{"/2021/05/04/F-distribution" | relative_url}})
6. [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile" | relative_url}})

</div>

<br><span class="statement-title">TOC.</span><br>

- Sampling Distribution of Mean & CLT
- [Weak Law of Large Numbers](#weak-law-of-large-numbers)
- [CLT; Central Limit Theorem](#clt-central-limit-theorem)
  - [*proof*](#proof-of-clt)

<hr/>

# Sampling Distribution of Mean

Let $X_1, \dots, X_n$ be a random sample with $E[X_i] = \mu$ and $\text{Var}(X_i) = \sigma^2$.

Then,

- $E[\overline{X}] = \mu$
- $\text{Var}(\overline{X}) = E\left[\left(\overline{X} - E[\overline{X}]\right)^2 \right] = \dfrac{\sigma^2}{n}$

\<LLN; Law of Large Numbers\>에 따르면, $n$이 무한으로 갈때, 분산 $\text{Var}(\overline{X}) = \sigma^2/n$가 0으로 수렴한다. 따라서 $\overline{X} \rightarrow \mu$가 된다!

<hr/>

# Weak Law of Large Numbers

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span> WLLN<br>

Let $X_1, \dots, X_n$ be a random sample with $E[X_i] = \mu$ and $\text{Var}(X_i) = \sigma^2$.

Let $\overline{X}$ be a sample mean.

For any $\epsilon > 0$, we have

$$
\lim_{n\rightarrow\infty} P\left(\left| \overline{X} - \mu \right| > \epsilon\right) = 0
$$

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

[\<Chebyshev's Inequality\>]({{"/2021/03/17/chebyshev's-inequality" | relative_url}})를 사용하면 아주 쉽게 증명할 수 있다!

$$
\begin{aligned}
P\left(\left| \overline{X} - \mu \right| > \epsilon\right) &\le \frac{\text{Var}(\overline{X})}{\epsilon^2} \\
&= \frac{1}{\epsilon^2} \cdot \frac{\sigma^2}{n} \rightarrow 0 \quad \text{as} \quad n \rightarrow \infty
\end{aligned}
$$

$\blacksquare$

</div>

<big>"WLLN says that as the sample size $n$ gets larger, then the sample mean is close to the true mean in probability!"</big>

이때, WLLN과 같은 형태의 수렴을 <span class="half_HL">"the convergence in probability"</span>라고 한다.

cf) 참고로 \<Strong Law of Large Numbers\>도 존재한다. 그러나 이 정리를 증명하려면, 측도(measure)에 대한 개념이 필요하기 때문에 소개만 하고 넘어가겠다.

$$
P\left(\lim_{n\rightarrow\infty} \overline{X} = \mu \right) = 1
$$

<hr/>

# CLT; Central Limit Theorem

<div class="example" markdown="1">

<span class="statement-title">Example.</span><br>

Q. What is the probability of $P\left( \overline{X} > 7\right)$?

Note that $X_1, \dots, X_n \sim \text{Ber}(p)$, and then $(X_1 + \cdots + X_n) \sim \text{BIN}(n, p)$.

When $n$ is larget, then $(X_1 + \cdots + X_n) \rightarrow N(\mu, \sigma^2)$.

Let's standardize it, then

$$
P\left( \frac{(X_1 + \cdots + X_n) - np}{\sqrt{npq}} \le z \right) \approx P(Z \le z)
$$

이때, 좌변의 분모/분자에 $n$를 나줘주면

$$
\begin{aligned}
P\left( \frac{((X_1 + \cdots + X_n) - np)/n}{(\sqrt{npq})/n} \le z \right)
&= P\left( \frac{(X_1 + \cdots + X_n)/n \; - \; p}{\sqrt{pq/n}} \le z \right) \\
&= P\left( \frac{\overline{X} - E[\overline{X}]}{\sqrt{\text{Var}(\overline{X})}} \le z \right) \\
&\approx P(Z \le z)
\end{aligned}
$$

결론은, 원래 문제였던 $P\left(\overline{X} > 7\right)$을 잘 정규화해서 normal 분포로 근사하여 풀면 된다는 것이다.

</div>

그런데, 지금의 경우는 $\overline{X}$가 BIN 분포였기 때문에 \<Normal Approximation\>에 의해 자연스럽게 유도된 것이었다. 과연 $\overline{X}$ 또는 $X_i$가 다른 분포를 가져도 위와 같은 방식을 풀 수 있을까? 이 의문에 대한 답을 제시하는 것이 바로 \<CLT; Central Limit Theorem\>이다 🤩

<br/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> CLT; Central Limit Theorem<br>

Let $X_1, \dots, X_n$ be a random sample with $E[X_i] = \mu$ and $\text{Var}(X_i) = \sigma^2$.

Let $\overline{X}_n := (X_1 + \cdots + X_n)/n$, sample mean.

Let $Z_n := \dfrac{\overline{X}_n - E[\overline{X}]}{\sqrt{\text{Var}(\overline{X})}} = \dfrac{\overline{X}_n - \mu}{\sigma/\sqrt{n}}$.

then, for any $z \in \mathbb{R}$, we have

$$
P(Z_n \le z) \rightarrow P(Z \le z) \quad \text{as} \quad n \rightarrow \infty
$$

where $Z \sim N(0, 1)$.

</div>

즉, <span class="half_HL">모집단에서 추출한 표본 $n$이 충분히 크다면, "표본평균" $\bar{X}$의 분포는 정규 분포에 근사한다!</span>

<span class="statement-title">Remark.</span><br>

1\. As long as iid, RVs have finite second moment[^1], then we have CLT.

이것이 의미하는 바는 아주 강력하다💥 $X_i$가 어떤 분포를 따르는 상관없이 CLT를 적용할 수 있다는 말이기 때문이다!! 이런 점 때문에 CLT를 <span class="half_HL">"universal result"</span>라고 한다!

<br/>

2\. We call $z: = \dfrac{\overline{x} - \mu}{\sigma / \sqrt{n}}$ as \<$z$-value\> or \<$z$-score; $z$-점수, 표준 점수\>, we define $z_\alpha$ as the number $z$ s.t. $P(Z \ge z) = \alpha$ when $Z \sim N(0, 1)$.

<div class="img-wrapper">
<img src="https://upload.wikimedia.org/wikipedia/commons/2/25/The_Normal_Distribution.svg" height="400px">
</div>

## Proof of CLT

<div class="notice" markdown="1">

<span class="statement-title">*Proof*.</span> CLT<br>

\<CLT\>를 증명하기 위해 아래의 정리를 사용한다.

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> Uniqueness of MGF<br>

If the mgf of $X_n$ converges to the mgf of $X$ for $t \in (-\delta, \delta)$ for some $\delta > 0$,

i.e.

$$
M_{X_n} (t) \rightarrow M_{X} (t) \quad \text{for} \quad t \in (-\delta, \delta)
$$

and $X$ is continuous, then CDF $F_{X_n}(x)$ converges to $F_{X}(x)$ for all $x \in \mathbf{R}$.

$$
F_{X_n}(x) \rightarrow F_{X}(x)
$$

</div>

✨ Goal: Show that the MGF of $Z = \dfrac{\bar{X} - \mu}{\sigma / \sqrt{n}}$ converges to the MGF of $N(0, 1)$ as $n \rightarrow \infty$.

Let $W = \dfrac{\bar{X} - \mu}{\sigma / \sqrt{n}}$, and then multiply $n$ both to the numerator and denominator.

$$
W = \frac{\bar{X} - \mu}{\sigma / \sqrt{n}} = \frac{\displaystyle \sum_{i=1}^n X_i - n \mu}{\sqrt{n} \cdot \sigma}
$$

The mgf of $W$ is

$$
\begin{aligned}
M_W (t)
&= E [e^{tW}]
= E\left[\exp \left( \frac{t}{\sqrt{n} \sigma}\sum^n_{i=1} X_i - n\mu \right) \right] \\
&= E \left[ \exp \left( \frac{t}{\sqrt{n}} \cdot \frac{X_1 - \mu}{\sigma}\right) \cdot \exp \left( \frac{t}{\sqrt{n}} \cdot \frac{X_2 - \mu}{\sigma}\right) \cdots \exp \left( \frac{t}{\sqrt{n}} \cdot \frac{X_n - \mu}{\sigma}\right) \right] \\
&= E \left[ \exp \left( \frac{t}{\sqrt{n}} \cdot \frac{X_1 - \mu}{\sigma}\right) \right] \cdots E \left[ \exp \left( \frac{t}{\sqrt{n}} \cdot \frac{X_n - \mu}{\sigma}\right) \right] \quad \text{iid} \\
&= M_{Z_1} (t / \sqrt{n}) \cdots M_{Z_n} (t / \sqrt{n}) \\
&= \left[ M_{Z} (t / \sqrt{n}) \right]^n
\end{aligned}
$$

이제 $M_W(t)$에 $\log$를 취하고, 극한 $n\rightarrow \infty$를 취하면,

$$
\begin{aligned}
\lim_{n\rightarrow \infty} \log M_W(t) \\
= \lim_{n\rightarrow \infty} \log \left[ M_{Z} (t / \sqrt{n}) \right]^n \\
&= \lim_{n\rightarrow \infty} n \cdot \log M_Z (t / \sqrt{n})
\end{aligned}
$$

여기서 $y = 1 / \sqrt{n}$로 치환해주면, 위의 극한식은 아래와 같다.

$$
\lim_{n\rightarrow \infty} n \cdot \log M_Z (t / \sqrt{n})
= \lim_{y \rightarrow 0} \frac{\log M_Z (yt)}{y^2}
$$

이때, $\displaystyle \lim_{y\rightarrow 0} M_Z(yt) = M_Z(0) = 1$이므로 극한식이 $\dfrac{0}{0}$ 꼴의 부정형이 된다. 이를 해결하기 위해 "로피탈 정리"를 사용한다.

$$
\begin{aligned}
\lim_{y \rightarrow 0} \frac{\log M_Z (yt)}{y^2}
&= \lim_{y \rightarrow 0} \frac{t \cdot \dfrac{M_z(yt)'}{M_Z(yt)}}{2y} \\
&= \frac{t}{2} \cdot \lim_{y \rightarrow 0} \frac{M_z(yt)'}{y \cdot M_Z (yt)} \\
&= \frac{t}{2} \cdot \lim_{y \rightarrow 0} \frac{M_z(yt)'}{y} \quad \left(\because \; \lim_{y\rightarrow 0} M_z(yt) = 1\right)
\end{aligned}
$$

이때, 위의 식에서도 $\displaystyle \lim_{y\rightarrow 0} M_Z(yt)' = M_Z(0)' = 0 = \mu$이므로, 다시 "로피탈 정리"를 적용하면,

$$
\begin{aligned}
\frac{t}{2} \cdot \lim_{y \rightarrow 0} \frac{M_z(yt)'}{y}
&= \frac{t}{2} \cdot \lim_{y \rightarrow 0} \frac{t M_z (yt) ''}{1} \\
&= \frac{t^2}{2} \cdot \lim_{y \rightarrow 0} M_z (yt) '' \\
&= \frac{t^2}{2} \quad \left(\because \; \lim_{y \rightarrow 0} M_z (yt) '' = 1 = \sigma^2\right)
\end{aligned}
$$

따라서,

$$
\lim_{n\rightarrow \infty} \log M_W(t) = \frac{t^2}{2}
$$

위의 식에서 $\log$를 시켜서 $\bar{X}$의 정규화인 $W$의 mgf $M_W(t)$를 얻으면 아래와 같다.

$$
\lim_{n\rightarrow \infty} M_W(t) = e^{t^2/2}
$$

이때, 표준정규분포 $N(0, 1)$의 mgf가 $e^{t^2/2}$이고, 두 분포의 mgf가 같으므로, "Uniqueness of mgf"에 의해 아래의 명제가 성립한다.

"$n$이 충분히 커지면, $\bar{X}$의 정규화인 $\dfrac{\bar{X} - \mu}{\sigma/\sqrt{n}}$는 표준정규분포 $N(0, 1)$을 따른다!"

$\blacksquare$

</div>

<hr/>

### Sampling Distribution of the difference btw two mean

이번에는 두 개의 서로 population에서 뽑은 두 independent sample을 생각해보자!

Let $X_1, \dots, X_{n_1}$, and $Y_1, \dots, Y_{n_2}$ be two independent random samples with $E[X_1] = \mu_1$, $\text{Var}(X_1) = \sigma_1^2$, and  $E[X_2] = \mu_2$, $\text{Var}(Y_2) = \sigma_2^2$.

우리는 "두 샘플 평균의 차" $\mu_1 - \mu_2$에 대한 분포를 모델링하고자 한다. 이때, $\overline{X} - \overline{Y}$를 사용하면 "두 샘플 평균의 차"에 대해 추론할 수 있다!!

By CLT,

$$
\begin{aligned}
    \frac{\overline{X} - \mu_1}{\sigma_1/\sqrt{n_1}} \sim N(0, 1) \quad & \iff \quad \overline{X} \sim N\left(\mu_1, \sigma_1^2/n_1\right) \\
    \frac{\overline{Y} - \mu_2}{\sigma_2/\sqrt{n_2}} \sim N(0, 2) \quad & \iff \quad \overline{Y} \sim N\left(\mu_2, \sigma_2^2/n_2\right)
\end{aligned}
$$

따라서, $\overline{X} - \overline{Y}$에 대한 분포는 independent한 두 normal distribution에 대한 덧셈으로 쉽게 유도할 수 있다!

$$
\overline{X} - \overline{Y} \sim N\left( \mu_1 - \mu_2, \; \frac{\sigma_1^2}{n_1} + \frac{\sigma_2^2}{n_2} \right)
$$

위의 사실을 이용해서, "두 샘플 평균의 차"에 대한 추론도 쉽게 수행할 수 있다 😉

<hr/>

# 맺음말

이번 포스트에서는 표본평균 $\bar{X}$에 대한 분포인 "Sampling Distribuion of Mean"을 보았다. 또, 표본평균 $\bar{X}$의 분포를 파악하고, 활용하는데 필요한 \<WLLN\>과 \<CLT\>를 살펴보았다.

이어지는 포스트에서는 "평균"과 함께, 확률 분포의 특성을 결정하는 parameter인 **"분산(Variance)"**이 Random Sample에서 어떻게 유도되는지 살펴볼 예정이다.

👉 [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance" | relative_url}})

<hr/>

# references

- ['알토'님의 포스트](http://blog.naver.com/psggoma/220899911971)