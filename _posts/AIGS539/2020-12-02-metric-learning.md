---
title: "Metric Learning"
layout: post
use_math: true
tags: [AIGS539]
---

## 서론
본 글은 2020-2학기 컴퓨터 비전 수업의 내용을 개인적으로 정리한 것입니다. 지적은 언제나 환영입니다 :)

**<u>keywords</u>**<br>
- Metric
  - **semantic distance**
- Mahalanobis Distance

<hr>

### What is a `Metric`?

**<u>Metric</u>**은 집합 안에 있는 모든 원소 사이의 거리<small>distance</small>를 수치화하는 함수<small>function</small>이다.

일반적으로 Euclid distance, Manhattan distance, cosine similarity 등이 **<u>Metric function</u>**으로 쓰인다.

**<u>Metric</u>** 두 대상 사이의 distance를 측정하는 것이지만, 반대로 생각하면 두 대상 사이의 **<u>유사도</u>**<small>similarity</small>를 따지는 데에도 사용할 수 있다!

<br>

일반적인 **<u>Metric</u>**이 두 대상 사이의 기하학적 특징을 이용해 거리를 부여한다면, 컴퓨터 비전, 그 중에서도 **<u>Metric Learning</u>**은 두 대상 사이의 **<u>semantic distance</u>**에 주목한다!

<hr>

### Pairwise vs. Triplet

Metric을 학습 방법에는 크게 두 가지 접근이 있다.

#### Pairwise

논의의 편의를 위해 대상 $\\{ x_1, x_2, x_3 \\}$에 대한 Metric을 학습한다고 가정하자.

우리의 목표는 두 함수 $D$와 $f$를 학습하는 것이다.

함수 $D$는 두 입력을 받아 두 입력 사이의 거리를 반환한다.

$$
D(x, y) = \textrm{distance btw two objects}
$$

함수 $f$는 대상에서 feature를 추출하는 **<u>feature extractor</u>**이다.

$$
f(x) = \textrm{feature vector}
$$

이제 두 함수 $D$, $f$를 잘 조합해 다음의 Pairwise relation을 학습시킨다고 하자.

$$
D(f(x_1), f(x_2)) \downarrow \quad D(f(x_1), f(x_3)) \uparrow
$$

즉, Metric Learning가 두 대상의 extraced feature의 값을 최대한 낮추는 것을 목표로 한다는 말이다.

**Loss minimization**과 비슷한 맥락이다.

#### Triplet

반면에 Triplet은 아래와 같은 relation을 학습시킨다.

$$
D(f(x_1), f(x_2)) < D(f(x_1), f(x_3))
$$

즉, 대상 사이의 절대적인 거리값을 줄이거나 늘리는 것이 아니라, 거리값의 대소 관계를 학습하는 것을 목표로 한다.

Triplet relation을 학습시키는 것은 단순히 다소 관계만 만족시키면 되기 때문에 Pairwise relation을 학습시키는 것보다 더 유연<small>**<u>flexible</u>**</small>하다고 한다.

<hr>

종합하자면, **<u>Metric Learning</u>**은 주어진 데이터셋에서 함수 $D$, $f$가 pairwise relation 또는 triplet relation을 잘 출력하도록 학습시키는 분야다.

<br>

<hr>

## Classical Metric Learning

Deep Learning 이전의 Metric Learning에 대한 부분이다.

실제 연구에서는 안 쓰겠지만, 고전적인 방법은 지금의 DL 기반 방법들을 이끌어내는 동기를 부여하고, DL 기반 접근에 영감을 준다.

### Mahalanobis Distance

두 점에 대한 **<u>Euclidean metric</u>** $D_E$는 아래와 같이 정의한다.

$$
D_E(\mathbf{x}, \mathbf{y}) = \sqrt{(\mathbf{x}-\mathbf{y})^T (\mathbf{x}-\mathbf{y})}
$$

이때, **<u>Mahalanobis distance</u>** $D_M$은 아래와 같이 정의한다.

$$
D_M(\mathbf{x}, \mathbf{y}) = \sqrt{(\mathbf{x}-\mathbf{y})^T M (\mathbf{x}-\mathbf{y})}
$$

<br>

자료를 더 찾아보니, **<u>Mahalanobis distance</u>**는 multi-variate distribution에서 거리를 재는 좋은 도구라고 한다.

multi-variate distribution 상의 한 점을 $\mathbf{x}$라고 하고, distribution의 평균을 $\mu$, 분산을 $\Sigma$라고 했을 때 **<u>Mahalanobis distance</u>**는 아래와 같다.

$$
D_{\Sigma}(\mathbf{x}, \mu) = \sqrt{(\mathbf{x}-\mu)^T \, {\Sigma^{-1}} \, (\mathbf{x}-\mu)}
$$

흥미로운 점은 multi-variate normal distribution $\mathcal{N}(\mathbf{x})$에도 **<u>Mahalanobis distance</u>**가 등장한다.

$$
\begin{aligned}
  \mathcal{N}(\mathbf{x}) &= \frac{1}{\sqrt{(2\pi)^k \lvert \Sigma \rvert}} \exp{\left( - \frac{1}{2} (\mathbf{x}-\mu)^T \, {\Sigma^{-1}} \, (\mathbf{x}-\mu) \right)} \\
  &= \frac{1}{\sqrt{(2\pi)^k \lvert \Sigma \rvert}} \exp{\left( - \frac{1}{2} {\left(D_{\Sigma}\right)}^2 \right)}
\end{aligned}
$$

<br>

**<u>Mahalanobis distance</u>** $D_M$에서 주목할 점은 행렬 $M$을 학습시킬 수 있다<small>**learnable**</small>는 것이다!

우리는 주어진 데이터셋으로부터 $M$을 학습시키게 되는데,[^1] 가장 직관적이고 unsupervised 방법은 데이터셋의 covaraince matrix $\Sigma$를 구해 그것의 역행렬을 $M$으로 삼는 것이다. $M={\Sigma}^{-1}$

<hr>

수업에서는 **<u>Mahalanobis distance</u>**을 다루는 방법을 소개하였다.

- A first approach to distnace metric learning
- Large Margin Nearest Neighbor(LMNN)

<br>

<hr>

#### A first approach to distnace metric learning

데이터셋에서 pair를 바탕으로 두 집합 $S^{+}$, $S^{-}$를 만든다.

$$
\begin{aligned}
  S^{+} &= \textrm{The set of similar pairs} \\
  S^{-} &= \textrm{The set of disimilar pairs}
\end{aligned}
$$

그리고 아래와 같이 최적화 문제를 구성한다.

$$
M^{*} = \underset{M}{\textrm{argmin}} \sum_{\left(\mathbf{x_i}, \mathbf{x_j}\right) \in S^{+}} (\mathbf{x_i} - \mathbf{x_j})^{T} \, M \, (\mathbf{x_i} - \mathbf{x_j})
$$

당연히 $S^{+}$에 속하는 pair $(\mathbf{x_i}, \mathbf{x_j})$에 대한 $D_{M}(\mathbf{x_i}, \mathbf{x_j})$ 값은 작아야 한다.

이때, 위의 최적화 문제에서 $S^{-}$를 고려한 **<u>constraint</u>**를 추가해준다!

$$
\begin{aligned}
  M^{*} &= \underset{M}{\textrm{argmin}} \sum_{\left(\mathbf{x_i}, \mathbf{x_j}\right) \in S^{+}} (\mathbf{x_i} - \mathbf{x_j})^{T} \, M \, (\mathbf{x_i} - \mathbf{x_j}) \\
  & \textrm{s.t.} \; \sum_{\left(\mathbf{x_i}, \mathbf{x_j}\right) \in S^{-}} (\mathbf{x_i} - \mathbf{x_j})^{T} \, M \, (\mathbf{x_i} - \mathbf{x_j}) \ge 1
\end{aligned}
$$

즉, $S^{+}$에 속하는 pair $(\mathbf{x_i}, \mathbf{x_j})$에 대한 $D_{M}(\mathbf{x_i}, \mathbf{x_j})$ 값은 어느 정도 -**<u>Margin</u>**- 만큼은 보장되어야 한다는 것이다.

<br>

이 방식으로 최적화 문제를 풀어서 $M^{*}$을 구했다면, Mahalanobis distance를 데이터셋을 아래와 같이 잘 분할하게 된다.

<div style="text-align:center;">
  <img src="{{site.url}}/assets/img/AIGS539/mahalanobis-dist-result.png" style="width:60%;">
  <p>"Distance metric learning with application to clustering with side-information", NIPS 2002</p>
</div>

<hr>

[^1]: 유의할 점은 $M$는 **<u>positive semi-definite matrix</u>**여야 한다는 점이다. 이 조건을 만족하지 않는다면, 복소수인 Mahalanobis dist를 얻는다...
