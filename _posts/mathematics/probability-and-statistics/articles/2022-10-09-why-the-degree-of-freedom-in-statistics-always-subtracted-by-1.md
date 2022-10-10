---
title: "Why the degree of freedom in statistics always subtracted by 1?"
layout: post
tags: ["statistics"]
preview: "통계학에서의 자유도(Degree of Freedom)이란 무엇인가? 왜 $(n-1)$로 자유도를 구하는가?"
---

통계학을 공부하면서 들었던 의문과 생각들을 에세이로 적어보았습니다 🙏

이번 포스트에서는 통계학에서 나오는 "Degree of Freedom"과 "왜 늘 통계학에선 DOF를 $n-1$로 설정하는지"에 대한 생각을 다룹니다. 🙌

<hr/>

# 통계학의 자유도(Degree of Freedom)이란?

통계학에서의 \<자유도; Degree of Freedom\>은 아래와 같은 의미를 가진다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Degree of Freedom<br>

The number of independent variates which make up the statistic.

</div>

즉, \<통계량(Statistics)\>를 정의하기 위한 독립 변량(variate)의 수가 \<자유도; DOF\>인 셈이다. 또는 "Total number of observations"라고도 할 수 있겠다.

그러나 좀더 정확하게 말하면 아래와 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Degree of Freedom<br>

$$
\text{DOF} = (\text{# of independent variates}) - (\text{# of constraints})
$$

</div>

즉, 어떤 \<Statistics\>의 자유도는 독립 변량의 수에서 제약의 수를 뺸 값이다! 👏

왜 이런 설명이 나오게 되었는지 좀더 살펴보자.

<div class="example" markdown="1">

포항공대의 확통 기말고사는 과목 평균이 $80$점이 되어야 한다는 규칙이 있다. 

이번 학기 확통을 듣는 학생은 총 5명이다. 대머리 교수 블혼은 학생 4명의 기말고사 시험지를 채점했다.

어라? 그런데 뒤늦게 과목 평균 $80$점을 맞춰야 한다는 사실이 기억이 난 블혼 교수는 남은 학생 한 명의 점수를 $80$이 되도록 반.드.시 맞춰야 한다!

</div>

위와 같은 상황이라면, $5$개의 Variates가 있지만, 과목 평균 $80$점이라는 Constraint가 하나 있기 때문에 오직 $4$의 DOF를 갖게 된다.

# 확률 분포와 자유도

(작성중...)

t분포, 카이 분포 등의 수식을 보여주면서 자유도에 대해서 좀더 설명?

<hr/>

# Reference

- [Why are the degrees of freedom in statistics always subtracted by 1?](https://qr.ae/pvcTeZ)