---
title: "Statistics - PS2"
layout: post
use_math: true
tags: ["statistics", "Problem Solving"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

이 글은 "[Introduction to Linear Regression]({{"/2021/06/06/introduction-to-linear-regression.html" | relative_url}})" 포스트에서 제시한 숙제들을 풀이한 포스트입니다.

<span class="statement-title">TOC.</span><br>

- $\sum e_i = 0$
- $\sum x_i e_i = 0$
- $\text{SST} = \text{SSR} + \text{SSE}$

<hr/>

<div class="statement" markdown="1">

<span class="statement-title">Theorem.</span><br>

The sum of residuals is zero.

$$
\sum_{i=1}^n e_i = \sum_{i=1}^n (y_i - \hat{y}_i) = 0
$$


</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

$$
\begin{aligned}
\sum_{i=1}^n e_i 
&= \sum_{i=1}^n (y_i - \hat{y}_i) \\
&= \sum_{i=1}^n (y_i - (b_0 + b_1 x_i)) \\
&= \sum_{i=1}^n \left(y_i - (\bar{y} + b_1 (x_i - \bar{x})) \right) \\
&= \sum_{i=1}^n (y_i - \bar{y} - b_1 (x_i - \bar{x})) \\
&= \cancelto{0}{\sum_{i=1}^n (y_i - \bar{y})} - b_1 \cancelto{0}{\sum_{i=1}^n (x_i - \bar{x})} \\
&= 0
\end{aligned}
$$

$\blacksquare$

</div>

<hr/>

<div class="statement" markdown="1">

<span class="statement-title">Theorem.</span><br>

The sum of product of residual and $x_i$s is zero.

$$
\sum_{i=1}^n x_i e_i = \sum_{i=1}^n x_i (y_i - \hat{y}_i) = 0
$$


</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

$$
\begin{aligned}
\sum_{i=1}^n x_i e_i 
&= \sum_{i=1}^n x_i (y_i - \hat{y}_i) \\
&= \sum_{i=1}^n x_i (y_i - \bar{y} - b_1 (x_i - \bar{x})) \\
&= \sum_{i=1}^n x_i (y_i - \bar{y}) - b_1 \sum_{i=1}^n x_i (x_i - \bar{x}) \\
&= \sum_{i=1}^n (x_i - \bar{x}) (y_i - \bar{y}) - b_1 \sum_{i=1}^n (x_i - \bar{x_i}) (x_i - \bar{x}) \\
&= S_{xy} - \frac{S_{xy}}{\cancel{S_{xx}}} \cdot \cancel{S_{xx}} \\
&= 0
\end{aligned}
$$

$\blacksquare$

</div>

<hr/>

<div class="statement" markdown="1">

<span class="statement-title">Theorem.</span><br>

$$
\begin{aligned}
\sum_{i=1}^n (y_i - \bar{y})^2 &= \sum_{i=1}^n (\hat{y}_i - \bar{y})^2 + \sum_{i=1}^n (y_i - \hat{y}_i)^2 \\
\text{SST} &= \text{SSR} + \text{SSE}   
\end{aligned}
$$

</div>

<div class="proof" markdown="1">

<span class="statement-title">*proof*.</span><br>

(스포) 증명 과정에서 위에서 증명했던 두 명제를 사용한다!

$$
\begin{aligned}
\sum_{i=1}^n (y_i - \bar{y})^2 
&= \sum_{i=1}^n (y_i - \hat{y}_i + \hat{y}_i - \bar{y})^2 \\
&= \sum_{i=1}^n \left((y_i - \hat{y}_i) + (\hat{y}_i - \bar{y})\right)^2 \\
&= \sum_{i=1}^n (y_i - \hat{y}_i)^2 + 2 \sum_{i=1}^n (y_i - \hat{y}_i)(\hat{y}_i - \bar{y}) + \sum_{i=1}^n (\hat{y}_i - \bar{y})^2 \\
\end{aligned}
$$

이때, 위의 식에서 중간의 텀만 따로 떼어보자. 그리고 $\hat{y}_i$에 대한 식을 대입하면,

$$
\begin{aligned}
\sum_{i=1}^n (y_i - \hat{y}_i)(\hat{y}_i - \bar{y})  
&= \sum_{i=1}^n (y_i - \hat{y}_i)(b_0 + b_1 x_i - \bar{y}) \\
&= \sum_{i=1}^n (y_i - \hat{y}_i)((\cancel{\bar{y}} - b_1 \bar{x}) + b_1 x_i - \cancel{\bar{y}}) \\
&= \sum_{i=1}^n (y_i - \hat{y}_i) \cdot b_1 (x_i - \bar{x}) \\
&= b_1 \cdot \left( \cancelto{0}{\sum_{i=1}^n (y_i - \hat{y}_i) x_i} - \bar{x} \cdot \cancelto{0}{\sum_{i=1}^n (y_i - \hat{y}_i)} \right) \\
&= 0
\end{aligned}
$$

</div>