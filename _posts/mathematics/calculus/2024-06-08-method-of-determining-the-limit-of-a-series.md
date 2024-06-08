---
title: "급수의 극한을 판정하는 법"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다.
{: .notice--info}

개인적으로 미적1의 마지막 챕터인 수열과 급수에 대한 부분이 미적1에서 제일 헷갈리는 부분이라고 생각합니다. 이번 기회에 해당 내용을 블로그 포스트로 꼼꼼히 정리해보았습니다 😁

# 수열의 극한으로 급수의 극한 판단하기

<div class="theorem" markdown="1">

만약 수열 $\{ a_n \}$가 발산하거나 $0$이 아닌 다른 값으로 수렴한다면, 급수는 발산한다.

</div>

# 적분 판정법

<div class="theorem" markdown="1">

급수의 partial sum이 증가 수열일 때, 즉 모든 텀이 positive 하다면, 그 수열 $\{ a_n \}$을 대응하는 함수 $f(x)$로 바꾼 후 $[1, \infty)$까지 적분을 해본다!!

만약 적분 값이 결정된다면, 그 값을 해당 급수의 Upper Bound로 삼는다.

그러면 그 급수가 monotonic sequence에 bounded이니 "단조 수렴 정리"를 적용해 해당 급수가 수렴함을 보일 수 있다.

만약 적분이 발산한다면, 급수도 발산한다.

</div>

# $p$-급수 판정법

<div class="theorem" markdown="1">

$$
\sum_{n=1}^{\infty} \frac{1}{n^p} = \frac{1}{1^p} + \frac{1}{2^p}
 + \dots + \frac{1}{n^p} + \dots
$$

$p$-급수는 $p > 1$일 때는 수렴하고, $p \le 1$일 때는 발산한다.

</div>

사실 적분 판정법으로 유도되는 성질임.

# 급수에 대한 샌드위치 정리

<div class="theorem" markdown="1">

세 급수 $\sum a_n$, $\sum b_n$, $\sum c_n$가 있을 때, 어떤 큰 $n$에 대해서 $a_n \le b_n \le c_n$를 만족한다고 하자. 그러면,

- 만약 오른쪽의 급수 $\sum c_n$가 수렴한다면, $\sum b_n$도 수렴한다.
- 만약 왼쪽의 급수 $\sum a_n$가 발산한다면, $\sum b_n$도 발산한다.

</div>

# 극한 비교 판정법

<div class="theorem" markdown="1">

두 수열 $\{ a_n \}$, $\{ b_n \}$이 어떤 큰 $n$에서 $a_n, b_n > 0$라고 할 때,

- 만약 $\lim_{n\rightarrow\infty}a_n/b_n = c > 0$라면, $\sum a_n$과 $\sum b_n$을 둘다 수렴하거나 아님 둘다 발산한다.
- 만약 $\lim_{n\rightarrow\infty}a_n/b_n = 0$라면, $\sum b_n$가 수렴할 때, $\sum a_n$도 수렴한다.
- 만약 $\lim_{n\rightarrow\infty}a_n/b_n = \infty$라면, $\sum b_n$가 발산할 때, $\sum a_n$도 발산한다.

</div>

이 방식은 (1) 두 수열의 비율에 대한 극한과 (2) 급수 $\sum b_n$의 극한을 가지고 급수 $\sum a_n$의 극한의 수렴/발산을 판정하는 방식이다. (1), (2) 둘다 구해야 함에 유의하자.

<hr/>

# 교대 급수의 극한

수열 $\{ a_n \}$가 양수와 음수가 번갈아가며 나오는 수열을 "교내 수열"이라고 한다. 이런 수열의 급수를 "교대 급수"라고 하고, 이를 다루기 위한 급수 판정법이 따로 존재한다.

물론 교대 급수의 판정법은 앞에서 봤던 positive 급수들의 판정에도 그대로 사용할 수 있다!!!


## 절대 수렴이란

<div class="definition" markdown="1">

만약 교대 수열에 절댓값을 씌운 수열에 대한 급수 $\sum \| a_n \|$가 수렴한다면, 급수 $\sum a_n$는 "절대 수렴(Absolutely Convergent)"한다고 말한다.

</div>

예시. $\sum \frac{\sin n}{n^2}$라는 교대 급수를 생각해보자. 이 수열은 절대 수렴 하는가?

대응하는 절대 급수 $\sum \| \frac{\sin n}{n^2} \|$가 수렴하는지 확인하면 된다. 이때, 비교를 위한 다른 급수 $\sum \frac{1}{n^2}$와 비교하면, $\| \sin n \| \le 1$이므로 $\sum \| \frac{\sin n}{n^2} \| < \sum \frac{1}{n^2}$가 되는데, 우항의 급수가 수렴하므로 급수 $\sum \frac{1}{n^2}$는 절대 수렴한다.

# 비교 판정법

수열의 증가율을 얼마나 되는지를 판단하기 위한 방법이다. 기하 급수 $\sum ar^n$의 극한 판정에서 $ar^{n+1}/ar^n = r$을 기준으로 극한을 판단하는 것과 비슷한 접근이다.

<div class="theorem" markdown="1">

교대 수열의 증가율 $\rho$을 아래와 같이 정의하자.

$$
\lim_{n\rightarrow\infty} \left| \frac{a_{n+1}}{a^n} \right| = \rho
$$

- $\rho < 1$라면, 절대 수렴한다.
- $\rho > 1$라면, 발산한다.
- $\rho = 1$라면, 판단할 수 없다.

</div>

# 루트 판정법

교대 수열에 루트를 씌워서 판단할 수도 있다.

<div class="theorem" markdown="1">

교대 수열에 대해서 $\rho$를 아래와 같이 정의하자.

$$
\lim_{n\rightarrow\infty} \sqrt[n]{\left| a_n \right|} = \rho
$$

- $\rho < 1$라면, 절대 수렴한다.
- $\rho > 1$라면, 발산한다.
- $\rho = 1$라면, 판단할 수 없다.

</div>

주로 수열에 $n$-th power가 있는 경우에 사용하면 효과적이다.

예시. 아래와 같이 정의된 교대 수열의 급수가 수렴하는지 판단하라.

$$
a_n = \begin{cases}
n/2^n, & n \text{ odd} \\
1/2^n, & n \text{ even}
\end{cases}
$$

$n$이 even인 경우는 쉽게 $\rho = 1/2$임을 보일 수 있고, odd인 경우는 $\lim_{n\rightarrow\infty} \sqrt[n]{\left\| n/2^n \right\|} = \lim_{n\rightarrow\infty} \sqrt[n]{\|n\|} / 2 = 1/2$로 두 경우의 $\rho$ 값이 같다!

