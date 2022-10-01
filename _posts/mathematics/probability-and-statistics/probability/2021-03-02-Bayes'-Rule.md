---
title: "Bayes' Rule"
layout: post
use_math: true
tags: ["Probability"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Law of Total Probability
- Bayes' Rule
  - 선별 검사(Screening Test)

<hr/>

# Law of Total Probability

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Partition<br>

The events $\\{ B_1, \dots, B_n \\}$ form a partition of event space $S$ if 

1. $B_i \cap B_j = \emptyset$ for any $i \ne j$
2. $\cup^n_{i=1} B_i = S$

</div>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> Law of Total Probability<br>

If the events $B_1$, ..., $B_n$ form a partition of $S$ such that $P(B_i) > 0$, 

then for any event $A$

$$
P(A) = \sum^{n}_{i=1} P(A \cap B_i)
$$

<div class="img-wrapper">
<img src= "https://i.stack.imgur.com/Cx0CA.png" style="width:200px;">
</div>

</div>

\<전체 확률의 법칙; Law of Total Probability\>는 \<Rule of Elimination\>라고도 한다.

<hr/>

# Bayes' Rule

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> Bayes' Rule<br>

If the events $B_1$, $B_2$, ..., $B_k$ be a partition fo $S$,

then for any event $A$ with $P(A) > 0$

$$
P(B_k \mid A) = \frac{P(B_k \cap A)}{P(A)} = \frac{P(A \mid B_k)P(B_k)}{\sum^{n}_{i=1} P(A \mid B_i)P(B_i)}
$$

</div>

<div class="proof" markdown="1">

<span class="statement-title">proof.</span><br>

증명은 간단하다. 

[Step 1] Conditional Probability에 따라 아래의 식이 성립한다.

$$
P(B_k \cap A) = P(B_k \mid A) P(A) = P(A \mid B_k) P(B_k)
$$

식을 약간 다음으면 아래를 유도할 수 있다.

$$
P(B_k \mid A) = \frac{P(B_k \cap A)}{P(A)}
$$

[Step 2] Law of Total Probability에 따라 분모의 $P(A)$를 아래와 같이 바꿀 수 있다.

$$
\frac{P(B_k \cap A)}{P(A)} = \frac{P(B_k \cap A)}{\sum^{n}_{i=1} P(A \cap B_i)}
$$

[Step 3] 다시 Conditional Probability의 정의를 이용하면, 최종적으로 아래의 결과를 얻는다.

$$
\frac{P(B_k \cap A)}{\sum^{n}_{i=1} P(A \cap B_i)} = \frac{P(B_k \cap A)}{\sum^{n}_{i=1} P(A \mid B_i)P(B_i)}
$$

</div>

<hr/>

\<베이즈 규칙\>은 예제를 확실히 아는 것이 중요하다. 그리고 \<베이즈 정리\>를 기계적으로 적용하는 것보단, 그 의미와 여기서 이런 수식적 추론을 하는 이유를 체득하는 게 중요하다.

# 선별 검사 <small>Screening Test</small>

건강한 사람과 특정 질병이 있는 사람을 구별하기 위해 시행하는 검사를 \<선별 검사\>라고 한다. 선별 검사에서 이상이 나타나면, 정밀 검사를 통해 질병의 유무를 판단한다.

[이곳](https://ratsgo.github.io/statistics/2017/07/01/bayes/)의 포스트에서 다양한 실제 예제를 다루고 있다. 이곳의 예제를 풀어 보길 바란다.
