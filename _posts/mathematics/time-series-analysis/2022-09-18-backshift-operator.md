---
title: "Backshift Operator"
layout: post
use_math: true
tags: ["time_series_analysis"]
---

# 도입

\<후방이동 연산자; backshift operator\>는 시계열의 시차(difference)를 다룰 때 아주 유용한 연산자이자 표기법이다. 정의는 아래와 같다.


<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Backshift Operator<br>

$$
B y_t = y_{t-1}
$$

즉, backshift operator $B$는 시계열의 값 $y_t$를 한 시점 뒤인 $y_{t-1}$로 옮겨준다.

이를 통해 차분(difference)를 유도하면,

$$
\begin{aligned}
y'_t 
&= y_t - y_{t-1} \\
&= y_t - By_t \\
&= (1 - B)y_t  
\end{aligned}
$$

</div>

비슷하게 2차 차분도 쉽게 표현할 수 있다.

$$
\begin{aligned}
y''_t 
&= y'_t - y'_{t-1} \\
&= (1-B)y_t - (1-B)y_{t-1} \\
&= (1 - B) (y_t - y_{t-1}) \\
&= (1 - B)^2 y_t  
\end{aligned}
$$

일반화하여 $d$차 차분은 아래와 같다.

$$
(1 - B)^d y_t
$$

**계절성 차분(seasonal difference)**는 아래와 같이 표현할 수 있다.

$$
(1 - B^m)y_t
$$


# 시계열 모델에 적용

지금까지 살펴본, $\text{AR}$, $\text{MA}$, $\text{ARMA}$, $\text{ARIMA}$, $\text{SARIMA}$ 모델을 모두 backshift operator를 사용해 표현해보자.

<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> AR Model w/ backshift operator<br>

AR Model의 정의는 아래와 같다.

$$
y_t = \phi_0 + \phi_1 y_{t-1} + \phi_2 y_{t-2} + \cdots + \phi_p y_{t - p} + \epsilon_t
$$

backshift operator $B$를 적용하면 아래와 같다.

$$
y_t = \phi_0 + \phi_1 B y_t + \phi_2 B^2 y_t + \cdots + \phi_p B^p y_t + \epsilon_t
$$

항을 정리해서 $y_t$를 왼쪽으로 넘기면,

$$
(1 - \phi_1 B - \phi_2 B^2 - \cdots - \phi_p B^p) y_t = \phi_0 + \epsilon_t
$$

</div>


<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> MA Model w/ backshift operator<br>

MA Model의 정의는 아래와 같다.

$$
y_t = \epsilon_t + \phi_0 + \phi_1 \epsilon_{t-1} + \phi_2 \epsilon_{t-2} + \cdots + \phi_q \epsilon_{t - q}
$$

backshift operator $B$를 적용하면 아래와 같다.

$$
y_t = \epsilon_t + \phi_0 + \phi_1 B \epsilon_{t} + \phi_2 B^2 \epsilon_{t} + \cdots + \phi_q B^q \epsilon_{t}
$$

항을 정리해서 $\epsilon_t$을 모아주면,

$$
y_t = \phi_0 + (1 + \phi_1 B + \phi_2 B^2 + \cdots + \phi_q B^q) \epsilon_t
$$

</div>


<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> ARMA Model w/ backshift operator<br>

ARMA Model은 $y_t$가 AR Model와 MA Model의 합으로 구성된 형태이다.

$$
\begin{aligned}
y_t 
&= 
\left( \phi_0 + \phi_1 y_{t-1} + \phi_2 y_{t-2} + \cdots + \phi_p y_{t - p} + \epsilon_t \right) \\
&+ \left( \epsilon_t + \phi_0 + \phi_1 \epsilon_{t-1} + \phi_2 \epsilon_{t-2} + \cdots + \phi_p \epsilon_{t - p} \right)
\end{aligned}
$$

backshift operator $B$를 적용하고, 식을 정리하면,

$$
(1 - \phi_1 B - \phi_2 B^2 - \cdots - \phi_p B^p) y_t  = \phi_0 + (1 + \phi_1 B + \phi_2 B^2 + \cdots + \phi_q B^q) \epsilon_t
$$

</div>


<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> ARIMA Model w/ backshift operator<br>

ARIMA Model은 $d$차 차분한 시계열에 ARMA 모델링을 한 것이다. 따라서, ARMA 모델의 $y_t$를 $(1 - B)^d y_t$로 바꿔주면 된다.

$$
\underset{\text{AR}}{(1 - \phi_1 B - \cdots - \phi_p B^p)} 
\underset{\text{difference}}{(1 - B)^d} y_t  
= \phi_0 +
\underset{\text{MA}}{(1 + \phi_1 B + \cdots + \phi_q B^q)}\epsilon_t
$$

</div>


<div class="statement" markdown="1">

<span class="statement-title">Definition.</span> SARIMA Model w/ backshift operator<br>

SARIMA Model은 ARIMA 모델에 계절성 ARIMA 모델링을 추가한 것이다.

$$
\begin{aligned}

\underset{\text{non-seasonal AR}}{(1 - \phi_1 B - \cdots - \phi_p B^p)} 
&\cdot
\underset{\text{seasonal AR}}{(1 - \Phi_1 B^s - \cdots - \Phi_{p_s} B^{s\cdot p_s})} \\
\underset{\text{non-seasonal difference}}{(1 - B)^d} 
&\cdot
\underset{\text{seasonal difference}}{(1 - B^s)^{d_s}}
y_t  \\
= \phi_0 + 
\underset{\text{non-seasonal MA}}{(1 + \phi_1 B + \cdots + \phi_q B^q)} 
&\cdot
\underset{\text{seasonal MA}}{(1 + \Phi_1 B^s + \cdots + \Phi_{q_s} B^{s \cdot q_s})}
\epsilon_t
\end{aligned}
$$

</div>
