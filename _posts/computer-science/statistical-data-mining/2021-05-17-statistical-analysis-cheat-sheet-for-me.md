---
title: "내가 보려고 만든 '통계 분석' Cheat Sheet"
layout: post
use_math: true
tags: [applied_statistics]
---

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> R-square; 결정계수<br>

- 1에 가까울수록 회귀식이 자료를 잘 설명함.

</div>

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> p-value; 유의확률<br>

p-value 값이 **0.05**보다 작으면, 유의한 차이가 있다고 해석한다.

(다르게 표현하자면, p-value 값이 0.05보다 작으면 \<null hypothesis\> $H_0$를 기각하고, 유의한 차이가 있다고 기술하는 $H_1$를 채택한다.)

</div>

\<p-value\>에 대한 개념적인 내용이 알고싶다면, 이 포스트 👉[Introduction to Hypothesis Tests](https://bluehorn07.github.io/mathematics/2021/05/18/introduction-to-hypothesis-tests.html) 를 정독하길 바란다!

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> RMSE; Root Mean Square Error<br>

실험이나 관측에서 나타나나는 "오차(Error)를 제곱(Square)해서 평균(Mean)한 것의 제곱근(Root)"를 의미한다.

$$
\text{Error} \rightarrow \text{Error}^2 \rightarrow E \left[{\text{Error}^2} \right] \rightarrow \sqrt{E \left[\text{Error}^2\right]}
$$

</div>

\<RMSE\>는 \<**regression**\>에서 모델을 평가할 때 쓰는 지표다. RMSE 값이 낮을수록 좋은 모델이라고 평가한다!

그러나, RMSE는 <span class="half_HL">크기(scale)에 의존적</span>라는 문제가 있다. 이 문제를 해결하기 위해 \<MAPE; Mean Absolute Percentage Error\>, \<MACE; Mean Absolute Scaled Error\> 등의 대안이 제시되었다. 자세한 내용은 ['Chris송호연'님의 포스트](https://brunch.co.kr/@chris-song/34)을 읽어보자!



