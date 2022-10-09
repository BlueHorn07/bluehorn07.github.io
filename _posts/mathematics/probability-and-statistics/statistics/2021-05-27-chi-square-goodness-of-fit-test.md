---
title: "Chi-square Goodness-of-fit Test"
layout: post
use_math: true
tags: ["statistics"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- [Introduction to Goodness-of-fit Test](#introduction-to-goodness-of-fit-test)
  - Test statistics of Goodness-of-fit
  - DOF of Goodness-of-fit
- [Test on Independence](#test-for-independence)
- [Test on Homogeneity](#test-for-homogeneity)

<hr/>

### Introduction to Goodness-of-fit Test

\<**Goodness-of-fit Test; 적합도 검정**\>은 population distribution이 categorical variable을 가지는 경우, 예를 들어 Head-Tail의 동전 던지기, 주사위 던지기 등에서 사용하는 검정 기법이다. \<Goodness-of-fit Test\>는 카테고리 변수에 대한 Sample Distribution <small>(또는 Observed Distribution)</small>이 우리가 가정한 Expected Distribution과 일치하는지를 결정한다.

먼저 아래의 예제를 풀면서, \<**Goodness-of-fit Test; 적합도 검정**\>에 대해 살펴보자.

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/goodness-of-fit-test-1.png" | relative_url }}" width=650>
</div>

1\. 문제 상황

- $H_0: p=0.8$
- $H_1: p \ne 0.8$
- significance level $\alpha$

2\. 샘플링 상황

|-|made|missed|total|
|:---:|:---:|:---:|:---:|
|observed|70|30| 100 | 
|expected <br/>under $H_0$| 80 | 20 | 100 |

3\. Test Statistic

이제 검정을 수행하기 위한 \<Test Statistics\>을 결정하자. 우리는 $\hat{p}$를 사용한다.

그리고 CLT를 적용하면, 아래와 같다.

$$
\frac{\hat{p} - p}{\sqrt{p(1-p) / n}} \sim N(0, 1)
$$

이때, 우리는 이전의 \<Test on Proportion\>에서 수행한 것과 달리 아래의 기준으로 $H_0$를 reject 할 것이다.

$$
\text{reject} \; H_0 \quad \text{if} \quad \left| \frac{\hat{p} - p}{\sqrt{p(1-p) / n}}\right|^2 > \left| z_{\alpha/2}\right|^2 = \chi^2_{\alpha}(1)
$$

여기서 잠깐! 위의 식을 약간 변형해 \<Goodness-of-fit\>의 식을 유도해보자.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\left| \frac{\hat{p} - p}{\sqrt{p(1-p) / n}}\right|^2
&= \frac{(\hat{p} - p)^2}{p(1-p)/n} = \frac{(x/n - p)^2}{p(1-p)/n} \\
&= \frac{(x/n - p)^2 \times n^2}{p(1-p)/n \times n^2} = \frac{(x - np)^2}{np(1-p)}
\end{aligned}
$$

여기서 우변의 식을 $\frac{1}{y(1-y)} = \frac{1}{y} + \frac{1}{1-y}$를 이용해 아래와 같이 분해한다.

$$
\begin{aligned}
\frac{(x - np)^2}{np(1-p)}
&= \frac{(x-np)^2}{np} + \frac{(x-np)^2}{n(1-p)}
\end{aligned}
$$

이때, $np$는 첫번째 expected value인 $e_1 = 80$이고, $n(1-p)$는 두번째인 $e_2 = 20$이다.

마찬가지로, $(x-np)^2$는 observed value와 expected value의 차이로 표현할 수 있다.

$$
(x-np)^2 = (o_1 - e_1)^2
$$

또, $(x-np)^2$의 경우, 아래와 같이 표현하면, 두번째 observed value와 expected value의 차이로 표현할 수 있다.

$$
(x-np)^2 = (x-n + n-np)^2 = (o_2 - e_2)^2
$$

그래서 식을 종합하면 아래와 같다.

$$
\left| \frac{\hat{p} - p}{\sqrt{p(1-p) / n}}\right|^2 = \frac{(o_1 - e_1)^2}{e_1} + \frac{(o_2 - e_2)^2}{e_2}
$$

그래서 rejection criterion을 다시 쓰면,

$$
\text{reject} \; H_0 \quad \text{if} \quad \sum_{i=1}^2 \frac{(o_i - e_i)^2}{e_i} > \chi^2_{\alpha}(1)
$$

</div>

위의 예제에서 살펴본 것을 다시 기술해보자.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Test Statistics for Goodness-of-fit<br>

\<Goodness-of-fit\>의 Test Statistic은 

$$
\chi^2 := \sum_{i=1}^k \frac{(o_i - e_i)^2}{e_i}
$$

where $o_i$ and $e_i$ are the observed and expected frequencies respectively.

💥 NOTE: <span style="color: red">all expected frequencies must be at least 5</span>. <small>만약, 5 이하의 빈도를 가지는 카테고리가 있다면, 우리는 그것을 다른 카테고리에 합치는 **pooling**을 수행할 것이다!</small>

</div>

위의 예제에서는 카테고리가 단 2개인 상황이었다. 하지만, 주사위 굴리기와 같이 카테고리가 여러 개인 경우는 $\chi^2$의 DOF가 달라진다. 그 공식은 아래와 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Degree of Freedom for Goodness-of-fit<br>

The degree of freedom $\nu$ = (#. of categories after pooling - 1) - #. of parameters estimated

</div>

<hr/>

### Test for Independence

우리는 \<Chi-squared goodness-of-fit Test\>를 응용해 두 개의 카테고리가 서로 **독립(independent)**인지 검정할 수 있다!

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/goodness-of-fit-test-2.png" | relative_url }}" width=650>
</div>

우리는 'income'과 'political'이 서로 독립인지를 검정해야 한다. 이에 따라 우리는 아래와 같이 $H_0$와 $H_1$을 설정한다.

- $H_0$: income-politicaa is independent
- $H_1$: they are not independent

$H_0$를 수식으로 표현하면 아래와 같다.

$$
P(\text{party } 1 \; \And \; \text{low}) = P(\text{part } 1) P(\text{low})
$$

위의 공식을 통해 우리는 각 상황에 대한 expected value를 얻을 수 있다.

예를 들어 $e_{11}$에서는

$$
\begin{aligned}
e_{11} &= 1500 \times P(\text{P1} \; \And \; \text{Low}) \\
&= 1500 \times \frac{677}{1500} \times \frac{499}{1500} \\
&= \frac{677 \cdot 499}{1500} = 225.21  
\end{aligned}
$$

이런 방식으로 각 entry에 대해 expected value를 구한후 $\chi^2$-value를 구한다.

$$
\chi^2 = \sum_{i=1}^3 \sum_{j=1}^3 \frac{(o_{ij} - e_{ij})^2}{e_{ij}}
$$

다음으로 DOF를 구해보면,

$$
\begin{aligned}
\nu 
&= (9-1) - ((3-1) + (3-1)) \\
&= 9 - 1 - 4 = 4  
\end{aligned}
$$

이때 (#. of parameters estimated) 부분이 4가 되는 이유는 다음과 같다.

우리가 'party'에 대한 parameter를 구하려면, 세 가지 경우에 대한 확률을 구해야 한다. 그런데, 확률의 경우 合이 1이 되기 때문에 세가지 경우 중 두 가지 경우만 구하면 된다. 따라서, 'part'에 대해서 두 가지 parameter를 estimate 해야 하고, 마찬가지로 'inomce'에 대해서도 두 가지 parameter를 estimate 해야 한다. 따라서, (#. of parameters estimated)는 4개이다.

이것을 공식으로 작성하면 아래와 같다.

$$
\begin{aligned}
\nu
&= r \cdot c - 1 - ((r -1) + (c-1)) \\
&= r(c-1) - (c-1) \\
&= (r-1)(c-1)
\end{aligned}
$$

$\chi^2$-value와 DOF $\nu$를 구했으면 그 다음은 검정을 수행하면 된다.

Reject $H_0$ if $\chi^2 > \chi^2_{\alpha} ((r-c)(c-1))$.

<hr/>

### Test for Homogeneity

이번에는 \<Goodness-of-fit Test\>를 응용해 각 카테고리에서의 분포가 **균일(homogenous)**한지 검정해보겠다.

<div class="img-wrapper">
<img src= "{{"/images/probability-and-statistics/goodness-of-fit-test-3.png" | relative_url }}" width=650>
</div>

먼저 무엇을 검정하고자 하는지 명확히 정의해보자.

"Is the party preference homogenous among various regions?"

이것을 확인하려면, 'part i'을 선호나는 비율이 각 지역마다 모두 동일한지 확인해야 한다. 이것은 곧 아래의 등식 성립함을 말한다.

$$
P(\text{party } i \mid \text{Seoul}) = P(\text{part } i \mid \text{Daejeon}) = P(\text{party } i \mid \text{Gwangju}) = P(\text{party } i \mid \text{Daegu})
$$

우리는 위의 등식을 null hypothesis $H_0$로 삼아 검정을 수행할 것이다!

위의 표를 기준으로 $e_{11}$를 한번 구해보자. 먼저 'Seoul'의 총 인구는 500이다. 그리고 전체 사람 수 중 'party 1'을 선호하는 사람의 비율은 391/1000이다. 따라서, $e_{11}$은

$$
e_{11} = 500 \times \frac{391}{1000}
$$

마찬가지로 $e_{12}$의 경우는 $e_{12} = 100 \times 391 / 1000$로, $e_{21}$은 $e_{21} = 500 \times 537 / 1000$이다.

✨ Homogeneity Test is Equivalent to Independence Test

사실 Homogeneity Test는 앞에서 수행한 Independence Test와 완전 동일하다. Homogeneity Test의 경우 $H_0$의 형태가 직접적으로 Inpendence를 암시하고 있는 형태는 아니지만, 약간 변형하면 Independence로 유도할 수 있다.

<div class="math-statement" markdown="1">

편의를 위해 $\text{party } i = B_i$, $\textit{region } j = A_j$로 표시하겠다.

$$
\begin{aligned}
P(B_i \mid A_1) = P(B_i \mid A_2) 
&= P(B_i \mid A_3) = P(B_i \mid A_4) \\
\frac{P(B_i \cap A_1)}{P(A_1)} = \frac{P(B_i \cap A_2)}{P(A_2)} 
&= \frac{P(B_i \cap A_3)}{P(A_3)} = \frac{P(B_i \cap A_4)}{P(A_4)} = x
\end{aligned}
$$

이때, 위의 식에서 정의한 값 $x$는 

$$
P(B_i \cap A_j) = x P(A_j)
$$

임을 이용해 값을 유도할 수 있다.

by "law of total probability",

$$
P(B_i) = \sum_{j=1}^4 P(B_i \cap A_j) = x \cdot \cancelto{1}{\sum_{j=1}^4 P(A_j)} = x
$$

즉, $x = P(B_i)$인데, 이것은 $B_i$와 $A_j$가 서로 독립임을 의미한다!!! 😲 $\blacksquare$

</div>

다음으로 DOF 역시 \<Independence Test\> 때와 마찬가지로 구하면 된다.

$$
\nu = (r-1) (c-1)
$$

그리고 검정을 수행하면,

Reject $H_0$ if $\chi^2 > \chi^2_{\alpha}((r-1)(c-1))$

<hr/>

검정(Testing)에 대한 내용은 여기까지다!! 👏 이것으로 "통계학(Statistics)"의 기본적인 내용을 모두 살펴본 것이다!! 😆

다음 포스트부터는 \<Simple Linear Regression\>이라는 새로운 챕터를 살펴본다. 주로 주어진 데이터에서 "Linear Regression"의 계수 $\beta_i$들을 어떻게 찾을 수 있을지를 다루는 챕터다!

👉 [Introduction to Linear Regression]({{"/2021/06/06/introduction-to-linear-regression.html" | relative_url}})