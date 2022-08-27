---
title: "Multiplication Algorithm"
layout: post
use_math: true
tags: ["Algorithm"]
---


2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br/>
<hr/>

### Multiplication Algorithm

계산적인 관점에서 바라볼 때, 곱셈은 덧셈보다 훨씬 비용이 많이 드는 작업이다. 인간의 관점에서도 지금 당장 $2$자릿수 수를 덧셈하는 것과 곰셉하는 걸 비교해보면, $2$자릿수 곱셈이 더 큰 비용이 든다.

계산적인 관점에서도 마찬가지다. $2$자릿수를 덧셈하는 것은 많아봐야 $3$번 반큼의 iteration을 돌면 된다. 하지만, $2$자릿수 곰셈은 $2 \times 2=4$, 총 4번 만큼의 iteration을 요구한다. 사이즈를 늘려, $n$자릿수 덧셈은 $n+1$번 만큼의 iteration을, $n$자릿수 곰셈은 $n \times n=n^2$ 만큼의 iteration을 요구한다. Big-O notation을 쓰면,

- 덧셈: $O(n)$
- 곱셈: $O(n^2)$

<hr/>

인간은 곱셈을 이중 `for`문의 방식으로 cascade 하게 곱셈을 수행한다. 하지만, 곱셈을 \<재귀 recursion\>을 이용해 접근할 수도 있다. 한번 살펴보자.

<br/>

먼저 "복소수 곱셈"에서 영감을 얻어보자.

For two complex numbers $x=a+by$, $y=c+di$,

$$
xy = (a+bi)(c+di) = (ac - bd) + (ad + bc)i
$$

위의 과정에서 $xy$를 구하기 위해선 $ac$, $bd$, $ad$, $bc$ 총 **4번의 곱셈**을 수행해야 한다.

하지만, 약간의 트릭을 쓰면, 3번만의 곱셈으로 동일한 결과를 얻을 수 있다!!

$$
xy = (a+bi)(c+di) = (ac-bd) + \left( (a+b)(c+d) - ac - bd \right) i
$$

위의 식에선 $ac$, $bd$, $(a+b)(c+d)$ 총 **<u>3번의 곱셈</u>**을 수행해 동일한 결과를 얻었다! 그리고 그 과정에서 $ac$, $bd$를 재활용했다. 몇번의 덧셈이 추가되었지만, 곱셈의 계산 코스트를 생각하면 괜찮은 거래다!

<br/>

이것을 "자연수 곱셈"에도 적용해볼 수 있을까?

두 $n$-bit number $x$, $y$가 있을 때, $xy$는 아래와 같다.

$$
xy = \left(2^{n/2} \cdot x_L + x_R\right) \left(2^{n/2} \cdot y_L + y_R\right)
$$

$n$-bit의 수 $x$를 절반인 $n/2$에서 $x_L$, $x_R$로 \<분할 divide\>했다!!

식을 전개하면,

$$
\begin{aligned}
xy &= \left(2^{n/2} \cdot x_L + x_R\right) \left(2^{n/2} \cdot y_L + y_R\right) \\
&= 2^n \cdot x_L y_L + 2^{n/2} \cdot \left(x_L y_R + x_R y_L \right) + x_R y_R
\end{aligned}
$$

위 식에서 $n/2$-bit 곱셈은 $x_L y_L$, $x_L y_R$, $x_R y_L$, $x_R y_R$ 총 4번을 수행한다. 각각의 $n/2$-bit 곱셈에 대해서도 \<분할 정복\>을 적용할 수 있으므로 점화식으로 표현하면 아래와 같다.

$$
T(n) = 4 \cdot T(n/2) + O(n) = O(n^2)
$$

그런데, 이때 $\left(x_L y_R + x_R y_L \right)$ 부분을 살펴보면 "복소수 곱셈"처럼 계산 결과를 재활용할 수 있는 부분이 존재한다!!

$$
\left(x_L y_R + x_R y_L \right) = (x_L + x_R)(y_L + y_R) - x_L y_L - x_R y_R
$$

즉, $n$-bit 자연수의 곱셈 역시 각 과정에서 **<u>3번의 곱셈</u>**만으로 동일한 결과를 얻을 수 있다!! 😆

이를 바탕으로 점화식을 다시 쓰면 아래와 같다.

$$
T(n) = 3 \cdot T(n/2) + O(n) = O(?)
$$

\<Master Theorem\>을 적용하기 전에 재귀를 이용한 \<Mutliplication Algorithm\>이 어떻게 동작하는지부터 코드-레벨에서 살펴보자.

<div class="math-statement" markdown="1">

function **multiply**($x$, $y$)<br/>
Input: Two $n$-bit integers $x$ and $y$<br/>
Output: Their product $xy$

<hr/>

**if** $n=1$ **then**<br/>
&emsp;&emsp; return $xy$<br/>
**end if**

$x_L$, $x_R$ = leftmost $\lceil n/2 \rceil$, rightmost $\lfloor n/2 \rfloor$ bits of $x$<br/>
$y_L$, $y_R$ = leftmost $\lceil n/2 \rceil$, rightmost $\lfloor n/2 \rfloor$ bits of $y$<br/>

$P_1$ = **multiply**($x_L$, $y_L$)<br/>
$P_2$ = **multiply**($x_R$, $y_R$)<br/>
$P_3$ = **multiply**($x_L + x_R$, $y_L + y_R$)

**return** $P_1 \times 2^n + \left(P_3 - P_1 - P_2\right) \times 2^{n/2} + P_2$

</div>

이제 \<Master Theorem\>을 이용해 시간 복잡도를 유도하자.

매 과정마다 문제가 $3$개의 하위 문제로 "분할"되고, 분할된 하위 문제는 본래 문제의 $n/2$ 만큼의 크기를 가진다. "at $k$-th level, there are $3^k$ subproblems, each of size $n/2^k$"

$$
3^k \cdot O(n/2^k)
$$

문제가 매번 절반씩 분할되므로, 분할은 총 $\log_2 n$번 이뤄진다.

따라서

$$
\sum^{\log_2 n}_{k=0} {3^k \cdot O(n/2^k)}
$$

식을 전개하면,

$$
\begin{aligned}
\sum^{\log_2 n}_{k=0} {3^k \cdot O(n/2^k)} &= \sum^{\log_2 n}_{k=0} {(3/2)^k \cdot O(n)} \\
&= O(n) \cdot \sum^{\log_2 n}_{k=0} {(3/2)^k } \\
&= O(n) \cdot \frac{(3/2)^{\log_2 {n} + 1} - 1}{3/2-1} \\
&= O(n) \cdot O\left( (3/2)^{\log_2 {n}} \right) \\ 
&= O(n) \cdot O\left( \frac{3^{\log_2 {n}}}{2^{\log_2 {n}}} \right) \\
&= O(n) \cdot O\left( \frac{n^{\log_2{3}}}{n} \right) \\
&= O(n^{\log_2 {3}})
\end{aligned}
$$

즉, \<분할 정복\>을 이용해 곱셈할 경우, 기존의 $O(n^2)$보다 개선된 $O(n^{\log_2{3}})$만에 곱셈을 누릴 수 있다!! 👍

<hr/>

이 빠른 곱셈 알고리즘을 \<카라츠바의 알고리즘; Karatsuba Algorithm\>이라고 한다. 1960년에 카라츠바가 제시한 알고리즘으로 행렬 곱셈 알고리즘인 [슈트라센(Stressen) 알고리즘]({{"/2021/10/19/matrix-multiplication-strassen-algorithm.html" | relative_url}})도 카라츠바 알고리즘과 비슷한 맥락으로 문제를 병합(merge) 하는 단계에서 시간 복잡도를 줄인 케이스이다.

이 \<카라츠바 알고리즘\>보다 더 빠른 알고리즘도 존재한다! \<FFT; 고속 푸리에 변환\>을 쓰면 더 빠르게 두 수의 곱셈을 구할 수 있다. 이 내용은 \<FFT; 고속 푸리에 변환\> 포스트에서 다시 다루도록 하겠다 🙌

<hr/>

### references

- 알고리즘 문제해결전략: 카라츠바의 빠른 곱셈 알고리즘
