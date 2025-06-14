---
title: "Metric Learning 1"
toc: true
toc_sticky: true
categories: ["Computer Vision"]
---


본 글은 2020-2학기 "컴퓨터 비전" 수업의 내용을 개인적으로 정리한 것입니다. 지적은 언제나 환영입니다 :)

**<u>keywords</u>**<br>
- Metric
  - **semantic distance**
  - **Mahalanobis Distance**
- Metric Learinng: a fist approach
- LMNN
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

즉, Metric Learning가 두 대상의 extracted feature의 값을 최대한 낮추는 것을 목표로 한다는 말이다.

**Loss minimization**과 비슷한 맥락이다.

#### Triplet

반면에 Triplet은 아래와 같은 relation을 학습시킨다.

$$
D(f(x_1), f(x_2)) < D(f(x_1), f(x_3))
$$

즉, 대상 사이의 수치화된 거리값을 줄이거나 늘리는 것이 아니라, 거리값의 **<u>대소 관계</u>**를 학습하는 것을 목표로 한다.

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

우리는 주어진 데이터셋으로부터 $M$을 학습시키게 되는데,[^1] 가장 직관적이고 unsupervised 방법은 데이터셋의 covariance matrix $\Sigma$를 구해 그것의 역행렬을 $M$으로 삼는 것이다. $M={\Sigma}^{-1}$

<hr>

수업에서는 **<u>Mahalanobis distance</u>**을 다루는 방법을 소개하였다.

- A first approach to distance metric learning
- Large Margin Nearest Neighbor(LMNN)

<br>

<hr>

#### A first approach to distance metric learning

데이터셋에서 pair를 바탕으로 두 집합 $S^{+}$, $S^{-}$를 만든다.

$$
\begin{aligned}
  S^{+} &= \textrm{The set of similar pairs} \\
  S^{-} &= \textrm{The set of dissimilar pairs}
\end{aligned}
$$

그리고 아래와 같이 최적화 문제를 구성한다.

$$
M^{*} = \underset{M}{\textrm{argmin}} \sum_{\left(\mathbf{x}_i, \mathbf{x}_j\right) \in S^{+}} (\mathbf{x}_i - \mathbf{x}_j)^{T} \, M \, (\mathbf{x}_i - \mathbf{x}_j)
$$

당연히 $S^{+}$에 속하는 pair $(\mathbf{x}_i, \mathbf{x}_j)$에 대한 거리값은 작아야 한다.

이때, 위의 최적화 문제에서 $S^{-}$를 고려한 **<u>constraint</u>**를 추가해준다!

$$
\begin{aligned}
  M^{*} &= \underset{M}{\textrm{argmin}} \sum_{\left(\mathbf{x}_i, \mathbf{x}_j\right) \in S^{+}} (\mathbf{x}_i - \mathbf{x}_j)^{T} \, M \, (\mathbf{x}_i - \mathbf{x}_j) \\
  & \textrm{s.t.} \; \sum_{\left(\mathbf{x}_i, \mathbf{x}_j\right) \in S^{-}} (\mathbf{x}_i - \mathbf{x}_j)^{T} \, M \, (\mathbf{x}_i - \mathbf{x}_j) \ge 1
\end{aligned}
$$

즉, $S^{-}$에 속하는 pair $(\mathbf{x}_i, \mathbf{x}_j)$에 대한 거리값은 어느 정도 -**<u>Margin</u>**- 만큼은 보장되어야 한다는 것이다.

<br>

이 방식으로 최적화 문제를 풀어서 $M^{*}$을 구했다면, Mahalanobis distance는 데이터셋을 아래와 같이 잘 분할하게 된다.

<div style="text-align:center;">
  <img src={{"/images/computer-science/computer-vision/mahalanobis-dist-result.png" | relative_url}} style="width:60%;">
  <p>"Distance metric learning with application to clustering with side-information", NIPS 2002</p>
</div>

<hr>

#### Large Margin Nearest Neighbor

Large Margin Nearest Neighbor, 줄여서 LMNN의 경우 좀더 복잡한 형태의 Objective function을 채용한다. 한번 살펴보자!

<br>

먼저 $S^{+}$에 대한 부분을 살펴보자. LMNN도 마찬가지로 positive pair의 거리합이 최소가 되는 것을 목표로 한다.

$$
\begin{aligned}
  M^{*} &= \underset{M}{\textrm{argmin}} \sum_{i, j} \eta_{ij} \cdot {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 \\
  & \textrm{where} \\
  & \eta_{ij} =
  \begin{cases}
    1, \quad (\mathbf{x}_i, \mathbf{x}_j) \in S^{+}\\
    0, \quad (\mathbf{x}_i, \mathbf{x}_j) \in S^{-}
  \end{cases}
\end{aligned}
$$

$\eta_{ij}$라는 indicator variable을 도입해 positive pair의 거리합을 최소화하도록 디자인 했다.

<br>

여기서 끝나는게 아니라 $S^{-}$에 대한 부분도 고려한 텀을 추가해준다. 해당 텀만 따로 작성해보면 아래와 같다.

$$
\sum_{i, j, k} \eta_{ij}(1-\eta_{ij}) \cdot h\left[ 1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \right]
$$

위 텀은 세 점 $\\{\mathbf{x}_i, \mathbf{x}_j, \mathbf{x}_k\\}$에 대한 **<u>Triplet relation</u>**을 고려하는 텀으로 $(\mathbf{x}_i, \mathbf{x}_j) \in S^{+}$이고, $(\mathbf{x}_i, \mathbf{x}_k) \in S^{-}$일 때를 고려한다.

이때, $h\left[ 1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \right]$는 **<u>hinge function</u>**으로 아래와 같다.

$$
h\left[ 1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \right] \\
= \max \left(0, 1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \right)
$$

이 hinge function의 값을 최소화하려면 $1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \le 0$ 이 되어야 한다. 그래야 hinge function의 값이 0이 되기 때문이다.

이것을 다시 쓰면

$$
\begin{aligned}
  1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 & \le 0 \\
  1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 & \le {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2
\end{aligned}
$$

의 조건을 만족해야 하는 것이 된다.

이것은 negative-pair dist가 positive-pair dist보다 Margin $1$ 만큼 더 멀리있도록 만든다. 즉,

$$
\begin{aligned}
  1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 & \le {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \\
  \textrm{Margin} + \left(\textrm{positive-pair dist}\right)^2 & \le \left(\textrm{negative-pair dist}\right)^2
\end{aligned}
$$

이제 Pairwise 텀과 Triplet 텀을 종합하면 아래와 같다.

$$
M^{*} = \underset{M}{\textrm{argmin}} \left\{ \sum_{i, j} \eta_{ij} \cdot {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 \right\} + c \left\{ \sum_{i, j, k} \eta_{ij}(1-\eta_{ij}) \cdot h\left[ 1 + {D_M\left(\mathbf{x}_i, \mathbf{x}_j\right)}^2 - {D_M\left(\mathbf{x}_i, \mathbf{x}_k\right)}^2 \right] \right\}
$$

즉,

1\. pair $(\mathbf{x}_i, \mathbf{x}_j)$가 positive-pair라면, 두 점의 거리값을 줄여 서로 가까워지도록 끌어당긴다; **<u>Pull</u>**

2\. pair $(\mathbf{x}_i, \mathbf{x}_k)$가 negative-pair라면, 두 점의 거리값을 positive-pair의 가장 큰 거리값보다 1 만큼의 여유<small>**<u>Margin</u>**</small>를 두고 멀어지도록 밀어낸다; **<u>Push</u>**

<br>

<div style="text-align:center;">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Lmnn.png/1200px-Lmnn.png" style="width:60%;">
  <p><a href="https://en.wikipedia.org/wiki/Large_margin_nearest_neighbor" target="_blank">Wikipedia/Large margin nearest neighbor</a></p>
</div>

처음의 시도와 비교했을 때, LMNN는 좀더 **동적**이라고 말할 수 있다.

앞의 시도에선 negative-pair에 대해 거리값이 $1$이라는 지정된 값보다 크기만 하면 충분했다. 그러나 이런 접근은 **허점**이 있는데, **<u>positive-pair의 거리값이 도저히 1보다 좁혀지지 않을 수도 있다</u>**는 것이다; positive-pair에 대해선 constraint를 만족하면서 거리합이 줄어들기만 하면 된다는 점을 상기하라.

그래서 **<u>negative-pair가 positive-pair보다 더 가까이 위치하는 상황이 충분히 가능하다</u>**는 점이 허점으로 지적받는다.

<br>

**<u>LMNN</u>**에선 이것을 극복해 negative-pair가 positive-pair보다 Margin $1$만큼 떨어지도록 최적화한다.

이때, negative-pair의 거리값의 기준이 되는 positive-pair의 거리값이 고정된 것이 아니라 동적으로 변하기 때문에 **<u>LMNN</u>**은 더 **<u>동적으로 작동한다</u>**고 평가한다.

<br>

<hr>

#### Metric Learning + DL

$$
D_M (\mathbf{x}_i, \mathbf{x}_j) = \sqrt{(\mathbf{x}_i - \mathbf{x}_j)^T M (\mathbf{x}_i - \mathbf{x}_j)}
$$

고전적인 Metric Learning에선 **<u>Mahalanobis distance</u>**의 $M$ 값을 구하는 최적화 문제에 집중했다.

$$
D\left(f(\mathbf{x}_i), f(\mathbf{x}_j)\right)
$$

고전적인 방법에서도 **<u>feataure extractor</u>** $f$를 사용하기는 했지만, 이미지 데이터를 그대로 사용하거나, 직접 디자인한 Image descriptor를 사용했다.

<br>

Metric Learning에서 DL이 도입되고부터는 distance metric을 학습하는 게 아니라, **<u>feature extractor</u>** $f$를 학습시키는 방향으로 발전해왔다.

다음 포스트에선 DL을 바탕으로 하는 Metric Learning에 대해 정리한다.

<hr>

[^1]: 유의할 점은 $M$는 **<u>positive semi-definite matrix</u>**여야 한다는 점이다. 이 조건을 만족하지 않는다면, 복소수인 Mahalanobis dist를 얻는다...
