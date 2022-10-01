---
title: "Linear Regression - 1-2"
layout: post
use_math: true
tags: [applied_statistics]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

<hr/>

<span class="statement-title">Motivation.</span><br>

estimator에서 independent vector $\mathbf{x}$에서 어떤 feature가 response vector $\mathbf{y}$에 영향을 미치는지 확인하려면 어떻게 해야할까? 간단하게 생각해본다면, 추정한 $\hat{\beta}$에서 $\hat{\beta}_i$의 값이 0인지 아닌지를 통해서 판단할 수 있을 것이다. 이렇게 어떤 feature가 결과에 영향을 미친다 안 미친다를 찾아내는 작업을 \<**통계적 추론 statistical inference**\>라고 한다.

아래의 가정은 \<statistical inference\>를 수행할 때에 시행하는 고전적인 가정이다.

<span class="statement-title">Assumption.</span> Classical Assumption<br>

Assume that the true distribution of the data is 

$$
Y = X^T \beta + \epsilon, \quad \epsilon \sim N(0, \sigma^2)
$$

이것을 다시 쓰면,

$$
(Y \mid X = x) \sim N(x^T \beta, \; \sigma^2)
$$

만약 위와 같은 가정을 만족한다면, 아래의 성질이 성립함을 증명할 수 있다.

<span class="statement-title">Property.</span><br>

Supp. that the classical assumption holds. Then,

$$
\hat{\beta} \sim N(\beta, \; (\mathbf{X}^T \mathbf{X})^{-1} \sigma^2)
$$

그리고 $\hat{\sigma}^2$를 적당히 scaling 해준다면,

$$
\frac{(n-p) \hat{\sigma}^2}{\sigma^2} \sim \chi^2_{n-p}
$$

그리고, $\hat{\beta}$, $\hat{\sigma}^2$는 서로 independent하다.

$$
\hat{\beta} \perp \hat{\sigma}^2
$$

이 부분은 추후에 좀더 보충하도록 하겠다.

<hr/>

