---
title: "Values in Statistics"
layout: post
tags: ["statistics"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

이번 포스트는 \<Statistics; 통계학\>을 공부하면서, 계속 등장하는 용어인 "OO-value"에 대해 개인적으로 정리한 포스트입니다 😁

✨ "OO-value"는 "OO-score"랑 똑같은 용어 입니다!

<br><span class="statement-title">TOC.</span><br>

- z-value
- t-value
- f-value
- $\chi^2$-value
- p-value

<hr/>

### z-value vs. t-value

먼저 \<z-value\>에 대해 살펴보자. \<z-value\>는 단순히 raw data를 표준화하여 제시한 값에 불과하다.

$$
z = \frac{x-\mu}{\sigma}
$$

이것은 mean과 scale에서 free하게 두 raw data를 비교하려고 할 때, 사용한다. 일종의 정규화(noarmalize)이다.

<br/>

반면에 \<t-value\>는 애초에 sampling distribution에서 쓰는 개념이다. \<z-value\>와 식의 형태는 비슷하지만, 그 내용은 전혀 다르다.

$$
t = \frac{\bar{x} - \mu}{s / \sqrt{n}}
$$

\<t-value\>는 그 식에서부터 sample mean $\bar{x}$과 population mean $\mu$의 차이인 $(\bar{x} - \mu)$가 들어가기 때문에, \<t-value\>는 sample mean과 population mean의 차이에 대한 지표라고 볼 수 있다.

만약 \<t-value\>의 값이 크다면, $\bar{x}$와 $\mu$의 차이가 크다는 것을 의미하며, 이 결과에 따라 population mean $\mu$를 다시 설정해야 할 수도 있다!

이 과정을 수행하는 것이 바로 \<t-test\>이며, 아래와 같이 $(1-\alpha)\%$ confidence interval을 구해, 만약 \<t-value\>가 이 confidence interval 안에 있다면 채택, 밖에 있다면 기각한다!

$$
P \left( -t_{\alpha/2} (n-1) < \frac{\bar{X} - \mu}{S / \sqrt{n}} < t_{\alpha/2}(n-1) \right) = 1 - \alpha
$$

$$
\left( \bar{x} - t_{\alpha/2}(n-1) \frac{s}{\sqrt{n}} , \; \bar{x} + t_{\alpha/2}(n-1) \frac{s}{\sqrt{n}} \right)
$$

\<t-value\>는 \<t-test\>에서 쓰는 지표이니, 더 확실하게 기억하고 싶다면 \<t-test\>의 상황을 살펴보고 익히는 것을 추천한다.

- [[Interval Estimatation] Estimate $\mu$ when $\sigma^2$]({{"/2021/05/06/interval-estimation.html#estimate-mu-when-sigma2-is-unknown" | relative_url}})
- [[Two Samples Estimation] Difference btw Two Means]({{"/2021/05/13/two-samples-estimation-diff-btw-two-means.html" | relative_url}})
  - $\sigma_1^2$ and $\sigma_2^2$ are unknown, but know that $\sigma_1^2 = \sigma_2^2$
    - pooled sample variance
  - $\sigma_1^2$ and $\sigma_2^2$ are unknown and unequal
    - Wlech's t-test
- 그외에도 popluation variance $\sigma^2$를 모르는 상황이라면 언제나!!

#### f-value & $\chi^2$-value

\<t-value\>를 잘 이해했으면, \<f-value\>는 그냥 상황이 달라진 것 뿐이다. \<f-value\> 역시 검정(Test)에 쓰는 지표 중 하나이며, 아래와 같이 구한다.

$$
f = \frac{s_1^2 / \sigma_1^2}{s_2^2 / \sigma_2^2}
$$

보통 두 샘플이 있고, 두 샘플의 sample variance를 통해 population variance의 비율 $\sigma_1^2 / \sigma_2^2$에 대해 추론하고자 할 때  \<f-value\>를 계산해 사용한다.

👉 [[Two Samples Estimation] The ratio of two variances]({{"/2021/05/16/variance-estimation.html#two-samples-estimation-the-ratio-of-two-variances" | relative_url}})

<br/>

마지막으로, \<$\chi^2$-value\>는 single sample 상황에서 sample variance $s^2$로 population variance $\sigma^2$에 대해 추정할 때, 또는 \<Goodness-of-fit\>에 대해 검정을 수행할 때 사용한다.

$$
\chi^2 = \frac{(n-1)s^2}{\sigma^2}
$$

👉 [[Single Sample Estimation] Variance Estimation]({{"/2021/05/16/variance-estimation.html#single-sample-estimation-variance-estimation" | relative_url}})

👉 [Chi-square Goodness-of-fit Test]({{"/2021/05/27/chi-square-goodness-of-fit-test.html" | relative_url}})

<hr/>

### p-value

\<p-value; 유의확률\>은 검정(Test)를 수행할 때 $H_0$를 기각하도록 만드는 가장 작은 significance level $\alpha$ 값을 의미한다. 우리는 이 p-value의 값을 $\alpha$ 값과 비교해 $H_0$를 기각할지를 결정한다.

<div align="center" style="font-size: larger; margin: 8px">

"Reject $H_0$ is the p-value is less than $\alpha$."

</div>

이것은 p-value가 critical region에 포함된다는 말과 동치다!

p-value는 분포에서 reject이 가능하도록 하는 넓이를 의미하기 때문에, Normal 분포를 포함해 t-distribution, F-distribution 등 모든 분포 모형에서 수행하는 검정(Test)에서 정의할 수 있다.

참고로 p-value는 우리가 지금까지 살펴본 z-value, t-value 등의 값과 전혀 다른 성격인데, p-value는 분포 상의 어떤 값이 아니라 넓이를 의미하기 때문이다. 그래서 p-value는 늘 $\alpha$ 값과 함께 사용된다.


👉 [Introduction to Hypothesis Tests: p-value]({{"/2021/05/18/introduction-to-hypothesis-tests.html#p-value" | relative_url}})

<hr/>

#### reference

- [statisticshowto](https://www.statisticshowto.com/probability-and-statistics/hypothesis-testing/t-score-vs-z-score/)

