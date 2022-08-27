---
title: "Fourier Series (Complex Representation)"
layout: post
use_math: true
tags: ["Differntial Equation"]
---


2019-2학기, 대학에서 들은 '미분방정식' 수업을 복습하는 차원에서 정리하게 된 글입니다. 지적은 언제나 환영입니다 :)

사실 정규 수업에서 다룬 내용은 아니고, \<푸리에 변환\>을 공부하다보니 이 부분이 필요해서 정리하게 되었다 😆

<br><span class="statement-title">TOC.</span><br>

- (prev) [Fourier Series]({{"/2021/06/08/fourier-series.html" | relative_url}})
- Fourier Series (Complex Representation)

<hr/>

\<푸리에 변환\>을 기술할 때 주로 이 복소지수 형태를 사용하기 때문에, \<푸리에 변환\>을 공부하려면 이 부분을 꼭 알고 있어야 한다 🤯

<div class="statement">

$$
e^{i\theta} = \cos \theta + i \sin \theta
$$

</div>

이번 포스트에서 우리에게 필요한 도구는 \<오일러 공식\>, 단 하나면 충분하다 😎

먼저, \<푸리에 급수\>의 삼각함수 형태를 기술하면 아래와 같다.

$$
f(x) = \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos \frac{m\pi x}{L} + b_m \sin \frac{m\pi x}{L} \right)
$$

시작하기 전에 위의 형태를 약간 다듬어야 한다. 위의 식에서 $\pi / L$를 주파수 $\omega$로 대체한다.

$$
f(x) = \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos m \omega x + b_m \sin m \omega x \right)
$$

사실 아이디어 자체는 간단하다. 삼각함수 $\cos$, $\sin$를 지수 형태로 표현한 후에 \<푸리에 급수\>에 대입해주기만 하면 된다. 복소함수론(MATH210)을 들었다면, 공식은 쉽게 유도할 수 있을 것이다.

<div class="statement" markdown="1">

$$
\cos \theta = \frac{1}{2} (e^{i\theta} + e^{-i\theta})
$$

$$
\sin \theta = \frac{1}{2i} (e^{i\theta} - e^{-i\theta})
$$

</div>

이제 위의 공식에 따라 \<푸리에 급수\>의 식에 지수함수를 대입해주자!!


<div class="math-statement" markdown="1">

$$
\begin{aligned}  
&\frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos m \omega x + b_m \sin m \omega x \right) \\
&= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cdot \frac{1}{2} (e^{im\omega x} + e^{-im\omega x}) + b_m \cdot \frac{1}{2i} (e^{im\omega x} - e^{-im\omega x}) \right)
\end{aligned}
$$

위의 식에서 지수함수를 기준으로 식을 묶어주자.

$$
\begin{aligned}
&= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cdot \frac{1}{2} (e^{im\omega x} + e^{-im\omega x}) + b_m \cdot \frac{1}{2i} (e^{im\omega x} - e^{-im\omega x}) \right) \\
&= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( \left(\frac{a_m}{2} + \frac{b_m}{2i}\right) \cdot e^{im\omega x} + \left(\frac{a_m}{2} - \frac{b_m}{2i}\right) \cdot e^{-im\omega x} \right) \\
&= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( \frac{1}{2} \left( a_m - ib_m \right) \cdot e^{im\omega x} + \frac{1}{2} \left( a_m + ib_m \right) \cdot e^{-im\omega x} \right) \\
\end{aligned}
$$

일단 여기까지 식을 전개해두자. 나중에 다시 방문할 예정이니 식의 형태는 기억해두자.

</div>

<div class="math-statement" markdown="1">

이번에는 \<푸리에 급수\>의 계수에 대한 식을 복소지수 형태로 표현해보자.

1\. $a_0$

바꿀게 없다.

$$
a_0 = \frac{1}{L} \int_{-L}^L f(x) \; dx
$$

2\. $a_n$

$$
\begin{aligned}
a_n 
&= \frac{1}{L} \int_{-L}^L f(x) \cos m \omega x \; dx \\
&= \frac{1}{L} \int_{-L}^L f(x) \frac{1}{2} (e^{im\omega x} + e^{-im\omega x}) \; dx \\
&= \frac{1}{2L} \int_{-L}^L f(x) e^{im\omega x} \; dx + \frac{1}{2L} \int_{-L}^L f(x) e^{-im\omega x} \; dx
\end{aligned}
$$

3\. $b_n$

마찬가지로

$$
b_n = \frac{1}{2iL} \int_{-L}^L f(x) e^{im\omega x} \; dx - \frac{1}{2iL} \int_{-L}^L f(x) e^{-im\omega x} \; dx
$$

위의 형태보다는 아래의 형태가 더 선호된다.

$$
i b_n = \frac{1}{2L} \int_{-L}^L f(x) e^{im\omega x} \; dx - \frac{1}{2L} \int_{-L}^L f(x) e^{-im\omega x} \; dx
$$

</div>

$a_n$과 $ib_n$ 익숙하지 않은가? 우리가 \<푸리에 급수\>를 복소지수 형태로 변환 했을 때 본 계수 부분이다!! 위에서 얻은 푸리에 계수를 식에 대입해보자!

<br/>

<div class="math-statement" markdown="1">

$$
f(x) = \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( \frac{1}{2} \left( a_m - ib_m \right) \cdot e^{im\omega x} + \frac{1}{2} \left( a_m + ib_m \right) \cdot e^{-im\omega x} \right)
$$

1\. $(a_n - ib_n) / 2$

$$
\begin{aligned}
\frac{1}{2} \left( a_n - ib_n \right) 
&= \frac{1}{2} \cdot 2 \cdot \frac{1}{2L} \int_{-L}^L f(x) e^{-in\omega x} \; dx \\
&= \frac{1}{2L} \int_{-L}^L f(x) e^{-in\omega x} \; dx
\end{aligned}
$$

2\. $(a_n + ib_n) / 2$

$$
\begin{aligned}
\frac{1}{2} \left( a_n + ib_n \right) 
&= \frac{1}{2} \cdot 2 \cdot \frac{1}{2L} \int_{-L}^L f(x) e^{in\omega x} \; dx \\
&= \frac{1}{2L} \int_{-L}^L f(x) e^{in\omega x} \; dx
\end{aligned}
$$

</div>

<div class="math-statement" markdown="1">

이번엔 [1, 2]에서 얻은 두 식을 $A_n$, $B_n$으로 치환하자. 그러면, 전체 식은 아래와 같이 변한다.

$$
\begin{aligned}
f(x) &= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( A_m \cdot e^{im\omega x} + B_m \cdot e^{-im\omega x} \right) \\
&\text{where} \\
a_0 &= \frac{1}{L} \int_{-L}^L f(x) \; dx \\
A_n &= \frac{1}{2L} \int_{-L}^L f(x) e^{-in\omega x} \; dx \\
B_n &= \frac{1}{2L} \int_{-L}^L f(x) e^{in\omega x} \; dx \\
\end{aligned}
$$

이때, 따로 떨어져 있는 $a_0$를 $A_n$의 식으로 통합하자.

$$
a_0 = A_0 = \frac{1}{2L} \int_{-L}^L f(x) e^{0} \; dx
$$

그러면,

$$
f(x) = \sum_{m=0}^{\infty} A_m \cdot e^{im\omega x} + \sum_{m=1}^{\infty} B_m \cdot e^{-im\omega x}
$$

</div>

<div class="math-statement" markdown="1">

위의 식에서 더 간단하게 만들 수 있다! 😲 $A_m$과 $B_m$을 하나로 합쳐보자!

식에서 $A_n$에 대한 부분합은 $0$부터 $\infty$까지, $B_n$에 대한 부분합은 $1$부터 $\infty$까지 수행한다. 이때, $B_n$에 대한 부분을 $1$부터가 아니라 $-1$부터 $-\infty$까지 수행하도록 식을 바꿀 수 있다!

$$
\begin{aligned}  
\sum_{m=1}^{\infty} B_m \cdot e^{-im\omega x} 
&= \sum_{m=-1}^{-\infty} B_{-m} \cdot e^{-i(-m)\omega x} \\
&= \sum_{m=-1}^{-\infty} B_{-m} \cdot e^{im\omega x}
\end{aligned}
$$

이때, $B_{-n}$은

$$
\begin{aligned}
B_n 
&= \frac{1}{2L} \int_{-L}^L f(x) e^{in\omega x} \; dx \\
B_{-n}
&= \frac{1}{2L} \int_{-L}^L f(x) e^{i(-n)\omega x} \; dx \\
&= \frac{1}{2L} \int_{-L}^L f(x) e^{-in\omega x} \; dx \\
&= A_n
\end{aligned}
$$

즉, $B_{-n}$은 곧 $A_n$이다. 따라서,

$$
\sum_{m=1}^{\infty} B_m \cdot e^{-im\omega x} = \sum_{m=-1}^{-\infty} A_m \cdot e^{im\omega x}
$$

</div>

이제 \<푸리에 급수\>에 대한 식을 최종적으로 기술하면 아래와 같다.

$$
f(x) = \sum_{-\infty}^{\infty} C_m \cdot e^{im\omega x}
$$

부분합의 범위가 $-\infty$부터 $\infty$까지로 바뀌었음을 강조하기 위해 계수를 $A_n$에서 $C_n$으로 바꾸어 줬다. 식 자체는 동일하다.

<br/>

마지막으로 삼각함수 형태와 복소지수 형태를 비교해보자.

1\. 푸리에 급수 (삼각함수)

$$
\begin{aligned}
f(x) &= \frac{a_0}{2} + \sum_{m=1}^{\infty} \left( a_m \cos m \omega x + b_m \sin m \omega x \right) \\
&\text{where} \\
a_0 &= \frac{1}{L} \int_{-L}^L f(x) \; dx \\
a_n &= \frac{1}{L} \int_{-L}^L f(x) \cos n \omega x \; dx \\
b_n &= \frac{1}{L} \int_{-L}^L f(x) \sin n \omega x \; dx \\
\end{aligned}
$$

2\. 푸리에 급수 (복소지수)

$$
\begin{aligned}
f(x) &= \sum_{-\infty}^{\infty} C_m \cdot e^{im\omega x} \\
&\text{where} \\
C_n &= \frac{1}{2L} \int_{-L}^L f(x) e^{-in\omega x} \; dx 
\end{aligned}
$$

<hr/>

푸리에 급수를 복소지수 형태로 표현하기 되면서, 푸리에 급수를 복소원(complex circle)의 모음으로 해석해볼 수도 있다!! 😲 'heejin_park'님의 포스트에서 이 부분을 잘 설명하고 있어 링크를 달아둔다.

👉 ['heejin_park'님의 포스트: 푸리에 급수의 삼각함수 표현 vs. 복소지수 표현](https://infograph.tistory.com/270?category=925501)

<br/>

\<푸리에 변환\>은 \<푸리에 급수\>에서 주기 $L$을 무한대로 극한을 취해 쉽게 얻을 수 있다. 자세한 내용은 아래의 포스트에서 확인해보자.

👉 [Fourier Transform]({{"/2021/06/10/fourier-transform.html" | relative_url}})

<hr/>

#### reference

- ['heejin_park'님의 포스트](https://infograph.tistory.com/247?category=925501)