---
title: "Degree of Freedom in Statistics"
layout: post
tags: ["statistics"]
preview: "통계학에서 자유도(Degree of Freedom)란 무엇인가? 왜 보통 자유도로 $(n-1)$ 값을 쓰는가?"
---

통계학을 공부하면서 들었던 의문과 생각들을 에세이로 적어보았습니다 🙏 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다🎲

이번 포스트는 통계학에서 나오는 "자유도(Degree of Freedom)"와 "왜 통계학에선 DOF를 $n-1$로 설정하는지"에 대한 생각을 다룹니다. 🙌

<hr/>

# 통계학에서 자유도(Degree of Freedom)란?

통계학에서 \<자유도; Degree of Freedom\>는 아래의 의미로 통한다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Degree of Freedom<br>

The number of independent variates which make up the statistic.

</div>

즉, \<통계량(Statistic)\>을 정의하기 위한 독립 변량(variate)의 수가 \<자유도; DOF\>인 셈이다. 또는 "Total number of observations"라고도 한다.

여기에 제약(constraint)을 포함한 정확한 정의는 아래와 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Degree of Freedom<br>

$$
\text{DOF} = (\text{# of independent variates}) - (\text{# of constraints})
$$

</div>

즉, 어떤 \<Statistic\>의 자유도는 독립 변량의 수에서 제약의 수를 뺸 값이다! 👏

왜 이런 설명이 나오게 되었는지 좀더 살펴보자.

<div class="example" markdown="1">

포항공대의 확통 기말고사는 과목 평균이 $80$점이 되어야 한다는 규칙이 있다. 

이번 학기 확통을 듣는 학생은 총 5명이다. 대머리 교수 블혼은 학생 4명의 기말고사 시험지를 채점했다.

어라? 그런데 뒤늦게 과목 평균 $80$점을 맞춰야 한다는 사실이 기억이 난 블혼 교수는 남은 학생 한 명의 점수를 $80$이 되도록 반.드.시 맞춰야 한다!

블혼 교수는 어쩔 수 없이 마지막 학생의 점수를 $400$점을 주고 말았다! ~~4명이 빵점이었다...~~

</div>

위의 상황에선 $5$명의 학생의 시험점수라는 $5$개의 Variates가 있지만, 과목 평균 $80$점이라는 Constraint가 하나 있기 때문에 오직 $4$의 DOF만 가능했다. 즉, 제약(Constraint)가 \<Statistic\>의 자유도를 낮추는 것이다!


# 확률 분포의 자유도

앞에서 자유도는 통계량(Statistic)에 대해서 정의되는 것이라고 말했다. 그런데 왜 확률 분포에 자유도라는 개념이 존재하는 것일까? 이것에 대한 대답은 <span class="red">확률 분포에서 DOF는 단순히 함수 개형을 결정하는 인자에 불과</span>하다. 우리가 아는 DOF는 모두 Positive Integer이다. 그러나 확률 분포의 DOF는 어떤 값이든 넣어도 상관없다! 심지어 $\pi$ 같은 값을 DOF로 넣어도 된다! ~~아무 의미도 없지만~~

<br/>

자유도 개념이 들어간 확률 분포를 살펴보자.

- [Chi-square distribution $\chi^2(n)$]({{"/2021/04/06/chi-square-distribution.html" | relative_url}})

$$
\chi^2(n) = \text{Gamma}\left(\frac{n}{2}, 2\right)
$$

- [Student's t-distribution $t(n)$]({{"/2021/04/27/student-t-distribution.html" | relative_url}})

$$
T := \frac{Z}{\sqrt{V / n}} \quad 
(Z \sim N(0, 1), V \sim \chi^2(n), Z \perp V)
$$ 

- [F-distribution $F(n_1, n_2)$]({{"/2021/05/04/F-distribution.html" | relative_url}})

$$
F := \frac{V_1^2 / \sigma_1^2}{V_2^2 / \sigma_2^2} = F(n_1, n_2) \quad
(V_1 \sim \chi^2(n_1), V_2 \sim \chi^2(n_2))
$$


# 통계량과 자유도

자유도 개념의 본질인 통계량(Statistic)으로 돌아오자.

자유도를 개념은 통계량(Statistic)에서 존재하는 개념이고, 통계량은 통계적 실험(Statistics Experiment)에서 등장한다. 통계량의 대표적인 예가 sample variance $s^2$이다.

$$
s^2 = \frac{1}{n-1} \sum_i^n \left( x_i - \bar{x} \right)^2
$$

어떤 통계량(Statistic)들은 자유도의 개념을 가지고 있다. 위에서 나온 3개 확률 분포를 따르는 녀석들: "chi-square value", t-value", "f-value"은 자유도를 가진다. 각각은 추정(Estimation)과 검정(Test)에서 활용된다.

$$
\begin{aligned}
\chi^2 &:= \sum_{i=1}^k \frac{(o_i - e_i)^2}{e_i} \\
t &:= \frac{\bar{x} - \mu}{s / \sqrt{n - 1}} \\
f &:= \frac{s_1^2 / \sigma_1^2}{s_2^2 / \sigma_2^2} 
\end{aligned}
$$

<br/>

아니 그래서 자유도(DOF)란 도대체 무엇인가? 이걸 어떻게 해석하고, 어떻게 받아들어야 하는가? 🤔

$$
s^2 = \frac{1}{n-1} \sum_i^n \left( x_i - \bar{x} \right)^2
$$

Sample Variance $S^2$에서 왜 분모에 $n$ 대신 $n-1$이 들어가는지 기억하는가? [Sample Variance에 대해 다뤘던 포스트]({{"/2021/04/25/sampling-distribution.html" | relative_url}})에선 $E[S^2] = \sigma^2$가 되기 위해서라고 수식으로 설명했다. 자유도를 겉들인 직관적인 설명은 <span class="red">"Sample Variance의 자유도가 $n-1$이기 때문"</span>라고 할 수 있다.

통계량(Statistic)을 정의하는 이유는 여러 샘플에서 추출한 값들을 종합해 그것들을 대표하는 하나의 값을 만들기 위해서다. 이때, 통계량(Statistic)에 함께 따라오는 DOF는 그 대표값에 실질적으로 얼만큼의 독립적인 요소가 있는지를 표현한다: "How many numbers in your statistic are *actually* independent."

다시 Sample Variance $S^2$의 경우를 보자. $S^2$는 $n$개 Sample로부터 유도되는 값이다. 그러나 Sample Mean $\bar{X}$의 값이 $\bar{x}$로 정해져 있다면, 이것은 통계량 Sample Variance를 구하는 데에 제약(Constraint)가 된다. $n-1$ Sample의 값이 정해진 이후에 마지막 한 Sample의 값이 고정되기 떄문이다. 따라서 처음에 살펴본 DOF에 대한 수식에 따라 $S^2$의 자유도는

$$
\begin{aligned}
\text{DOF} 
&= (\text{# of independent variates}) - (\text{# of constraints}) \\
&= n - 1
\end{aligned}
$$

<br/>

이렇듯 Sampling Statistic 중에선 통계량을 유도하는데 쓰인 Sample 수 $n$과 통계량이 갖는 실제 independent variability가 다를 수 있다.

상황별로 살펴보면,

- Single Sample
  - $n$ observations & $1$ constraint: the mean → $n - 1$ variability
- Two Samples
  - $n_1 + n_2$ oberservations & $2$ constraints: each mean → $n_1+ n_2 - 2$ variability

<br/>

여기서 깜짝 질문! z-value는 왜 자유도 개념이 없을까? 🤔

$$
z := \frac{\bar{x} - \mu}{\sigma / \sqrt{n}}
$$

그 이유는 애초에 z-value가 따르는 분포인 Normal Distribution이 sample size $n$에 의존하지 않기 때문이다. 반면에 z-value에서 population variance $\sigma^2$가 sample variance $s^2$로 바뀐 t-value는 자유도 $n-1$를 갖는데,

$$
t := \frac{\bar{x} - \mu}{s / \sqrt{n - 1}}
$$

이것은 t-value 자체가 자유도 개념이 있는 t-distribution을 따르기 때문이기도 하고, 분모에 사용한 sample variance $s^2$가 sample size $n$에 의존하는 통계량(Statistic)이기 때문이기도 하다.

<hr/>

# 맺음말

이 글을 작성하기 전에는 무지성으로 Sample Size $n$에 $-1$한 값을 사용했다. 그러나 이번에 내용을 정리하면서, 자유도(DOF)가 도대체 무슨 의미인지, 그리고 왜 $-1$을 빼줄 수 밖에 없는지를 이해할 수 있었다. 👏

자유도(DOF) 개념이 중요한 영역은 **추정(Estimation)**과 **검정(Test)**이다. Sample Statistic의 자유도에 따라 추정에선 \<significance\>가 검정에선 \<p-value\>가 달라지기 때문이다.

자유도 개념이 있는 대표적인 추정과 검정의 예시들이다.

- [Interval Estimation: Estimate $\mu$ is unknown]({{"/2021/05/19/sample-mean-test.html" | relative_url}})
- [Variance Estimation]({{"/2021/05/16/variance-estimation.html" | relative_url}})
- [Goodness-of-fit Test]({{"/2021/05/27/chi-square-goodness-of-fit-test.html" | relative_url}})

<hr/>

# Reference

- [Why are the degrees of freedom in statistics always subtracted by 1?](https://qr.ae/pvcTeZ)
- [Tutorial: Pearson's Chi-square Test for Independence](https://www.ling.upenn.edu/~clight/chisquared.htm)
- [Gerard E. Dallal - Degrees of Freedom](http://www.jerrydallal.com/LHSP/dof.htm)