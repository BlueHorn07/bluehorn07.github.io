---
title: "Recommendation Algorithm - Basic"
toc: true
toc_sticky: true
categories: ["Recommendation Algorithm"]
---


2021-1학기, 대학에서 '과제연구' 수업에서 진행하는 개인 프로젝트를 위해 개인적으로 정리한 포스트입니다. 지적과 교류는 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Introduction to Filtering
- Explicit & Implicit Dataset
- Neighbhorhood Model & Latent MF

<hr/>

## Introduction to Filtering

1\. **Contents-based Filtering; CBF**

사용자 또는 아이템에 대한 '**프로필 데이터**'를 가지고, 프로필이 유사하다면 그 대상과 연관된 아이템을 추천하는 방식

만약 유저 프로필을 작성해 유사도를 구한다면, *user-based recommendation*, 아이템의 프로필을 작성해 유사도를 구한다면, *item-based recommendation*.

**단점**: 모든 아이템에 대한 프로필을 만드는 것이 어려우며, 프로필을 만드는 과정에서 개인의 '주관'이 개입한다.

<br/>

2\. **Collaborative Filtering; CF** 🔥

프로필 데이터 없이, 사용자의 '**과거 행동 데이터**'만을 가지고 추천을 진행하는 방식

평점, 예약 기록 등이 과거의 행동 데이터로 수집될 수 있다!

**장점**: 데이터셋을 쌓기가 쉽다! 그리고 일반적으로 CBF보다 더 좋은 성능을 낸다고 알려져 있다.

**단점**: 그러나 과거 데이터가 없는 신규 사용자에 대해선 추천 정확도가 떨어지는 **<u>Cold Start</u>** 문제가 발생한다.

<br/>

**3\. Hybrid Filtering**

CBF와 CF를 함께 사용하는 기법임.

(1) 두 알고리즘을 모두 시행하고, 두 알고리즘의 결과에 대해 Weighted Average를 취해 추천하는 \<**Combining Filtering**\>.

(2) 평점 데이터와 아이템 프로필을 조합해 사용자에 대한 프로필을 만들어 추천을 진행하는 \<**Collaboration via Content**\>.

(3) 처음에는 CBF를 사용하다가 데이터가 일정 수 이상 쌓이길 기다려 이후에는 CF를 적용

<hr/>

## Explicit & Implicit Dataset

<div class="img-wrapper">
  <img src="https://t1.daumcdn.net/cfile/tistory/9995253B5C8FC8EE06" width="300px">
  <p>
  (출처: <a href="https://yeomko.tistory.com/6?category=805638">갈아먹는 추천 알고리즘</a>)
  </p>
</div>

**CF**를 기준으로 위와 같이 아이템에 대한 사용자들의 평점을 정리한 행렬이 존재한다고 하자. 이때, 사용자들이 모든 아이템에 대해 평가를 남겨주는 경우는 드물기 때문에 행렬에는 `NaN` 값들이 꽤 존재한다.

우리는 이 `NaN` 값들이 존재하는지, 다르게 표현하면 모든 아이템에 대해 선호와 비선호에 대한 값이 존재하는지에 따라 \<Explicit Dataset\>과 \<Implicit Dataset\>으로 구분한다.

**1\. Explicit Dataset**

\<Explicit Dataset\>은 <span class="half_HL">사용자가 선호와 비선호를 명확히 구분한 데이터셋</span>을 말한다. 영화 평점의 경우에선 5가 선호, 1이 비선호를 의미한다. 즉, 데이터셋의 정보를 통해 사용자가 모든 개별 아이템에 대한 선호 여부를 파악할 수 있는 데이터셋이다.

그러나 이런 경우는 이상적이고, 특수한 경우로 현실의 데이터에서는 모든 아이템에 대한 선호가 결정되어 있지 않다. 그렇기 때문에 우리는 \<Implicit Dataset\>을 어떻게 처리할지를 더 고민해야 한다.

<br/>

**2\. Implicit Dataset**

\<Implicit Dataset\>의 경우, 오직 사용자가 해당 아이템을 얼마나 소비했는지, 즉 <span class="half_HL">**'빈도'**만을 기록한 데이터셋</span>이다. 쇼핑몰의 클릭 여부, 상품에 대한 구매 횟수가 등이 이것의 좋은 사례이다.

### How to?

앞에서 제시한 평점 행렬로 다시 돌아오자. <span class="half_HL">추천 알고리즘의 목표는 NaN에 어떤 값이 들어갈지 예측하는 것</span>이다.

만약 우리가 평점 행렬을 **Explicit Dataset**로 해석한다면, 우리는 선호도가 명확한 데이터셋들만으로 학습을 시킨다. 즉, <span class="half_HL">NaN 데이터들을 제외하고 알고리즘을 설계하고 학습시킨다</span>는 말이다.

반면에 평점 행렬을 **Implicit Dataset**로 해석한다면, 우리는 사용자가 어떤 아이템을 선호하는지 알지만, 어떤 아이템을 비선호하는지는 알지 못한다. <span class="half_HL">또한, 저 NaN에 사용자가 좋아하는 아이템이 있을 수도 있고 없을 수도 있다.</span> 그래서 이 경우는 NaN 데이터들을 포함해 알고리즘을 설계하고 학습을 시킨다.

### How to implement?

그렇담 Explicit이든 Implicit이든, NaN을 어떻게 처리할지는 정했으니, 그럼 어떻게 그리고 어떤 알고리즘을 사용해야 할까? 여기서는 대표적인 두 방법인 \<**Neighborhood Model**\>과 \<**Latent Factor Model**\>을 살펴본다.

<big>**1\. Neighborhood Model**</big>

\<NM; Neighborhood model\>은 평점 데이터를 바탕으로 서로 유사한 유저 혹은 아이템을 찾는다. 이때, 두 대상 사이의 유사도를 측정해야 하는데 그 중 하나로 \<**Person Correlation**\>을 쓸 수 있다.

<span class="statement-title">Definition.</span> Person Correlation<br>

$$
r_{XY} = \frac{\text{cov}(X, Y)}{\sigma_X \sigma_Y}
$$

$r_{XY} = 1$이면, 양(+)의 상관관계를, $r_{XY} = -1$이면, 음(-)의 상관관계를 가진다.

만약 유저 $X$에게 어떤 영화를 추천하고 싶다면 각 유저의 평점 벡터 $\mathbf{x}$와 다른 유저들의 평점 벡터에 대해 모두 Corr를 구한 후에 유사도가 가장 큰 유저를 특정한다. 그리고 그 유저가 높은 평점을 영화를 유저 $X$에게 추천하면 된다. 이 경우를 \<User-oriented Neighborhood model\>이라고 한다.

반대로 아이템 사이의 Corr를 구할 수도 있다. 그리고 이 중 가장 유사한 $K$의 아이템을 묶어줄 수도 있을 것이다. 이런 상황을 생각해보자. 내가 넷플릭스에서 어떤 영화 한편을 선택했다. 그리고 화면 하단에는 그 영화와 유사한 $K$의 영화들이 나열되어 있다. 이때, 영화 추천과 함께 내가 줄 '예상 평점'도 함께 제공된다. 나는 분명이 저 영화를 본적이 없는데, '예상 평점'은 어떻게 유도된 것일까? 이때, 등장하는 것이 \<**Neighborhood Algorithm**\>이다.

<span class="statement-title">Definition.</span> Neighbhorhood Algorithm<br>

- $\hat{s}_{ui}$: **predicted score** that user $u$ will give to movie $i$.
- $r_{ij}$: **similarity** btw movie $i$ and movie $j$.
- $s_{uj}$: score that user $u$ has rated to movie $j$.
- $S(i; u, K)$: $K$ number of neighbors of movie $i$, which are rated by user $u$.

$$
\hat{s}_{ui} = \frac{\displaystyle \sum_{j \in S(i; u, K)} r_{ij} s_{uj} }{\displaystyle \sum_{j \in S(i; u, K)} r_{ij}}
$$

원리는 간단하다. 그냥 영화 $i$와 내가 평점을 매긴 영화들 사이의 similarity를 구하고 이를 바탕으로 가장 유사한 $K$개를 선정한다. 그리고 내가 매긴 평점들을 similarity를 바탕으로 가중합을 해 한번도 보지 않은 영화의 평점을 예측한다! 😎

이런 NM은 Explicit Dataset에 더 적합하며, Implicit Dataset은 NM 보다는 아래에서 설명할 \<**Latent Factor Model**\>이 더 적합하다!

<br/>

<big>**2\. Latent Factor Model**</big>

\<Latent Factor Model\>은 관찰된 데이터와 잠재 데이터의 관계식을 유도하는 모델이다. 그리고 딥러닝 역시 Latent Factor Model의 일종으로 평가된다.

<div class="img-wrapper">
  <img src="https://t1.daumcdn.net/cfile/tistory/99A2523D5C910CD90B" width="400px">
  <p>
  (출처: <a href="https://yeomko.tistory.com/5?category=805638">갈아먹는 추천 알고리즘</a>)
  </p>
</div>

우리는 <span class="half_HL">주어진 평점 데이터 $R$을 사용자와 아이템의 Latent Factor $X$, $Y$로 분해한다.</span> 그리고 이 둘을 각각 학습시키는 \<MF; Matrix Factorization\> 기법을 사용할 것이다.

여기서는 간단히 소개만 하고, 이어지는 포스트에서 \<Latent Factor Model\>에 대해 더 자세히 다루겠다 😉

👉 [Latent Matrix Factorization]({{"/2021/04/08/latent-matrix-factorization.html" | relative_url}})

<hr/>

### references

- [갈아먹는 추천 알고리즘](https://yeomko.tistory.com/3)




