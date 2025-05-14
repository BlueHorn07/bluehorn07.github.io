---
title: "급수의 극한을 판정하는 법"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

개인적으로 미적1의 마지막 챕터인 수열과 급수에 대한 부분이 미적1에서 제일 헷갈리는 부분이라고 생각합니다. 이번 기회에 해당 내용을 블로그 포스트로 꼼꼼히 정리해보았습니다 😁

미적분학 교재에서 소개한 급수 판정법만을 다룹니다.
{: .notice }

# 수열의 극한으로 급수의 발산 판단하기

<div class="theorem" markdown="1">

만약 수열 $\{ a_n \}$가 발산하거나 $0$이 아닌 다른 값으로 수렴한다면, 급수는 발산한다.

</div>

단, <span class="red">수열이 $a_n \rightarrow 0$를 만족하더라고 급수는 발산할 수도 있다.</span> 그 예시가 바로 **조화(harmonic) 급수**!!

$$
\sum_{n=1}^{\infty} \frac{1}{n} = \infty
$$

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

이 결과는 적분 판정법으로 유도되는 성질이다. 뒤에 나오는 비율 판정법과 루트 판정법에서는 $\rho = 1$로 나오기 때문에, 무조건 적분 판정법을 기반으로 유도해야 합니다.


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

수렴/발산을 확인하고 싶은 급수를 $\sum a_n$으로 두고 문제를 풀면 된다.

<div class="example" markdown="1">

$\sum \frac{2n+1}{(n+1)^2}$의 수렴/발산을 판단하라.

$\sum b_n = \sum 1/n$로 두고, 두 수열을 비교해보자. $\sum 1/n$은 조화 수열의 합으로 $\infty$로 발산한다.

$$
\lim_{n \rightarrow \infty} \frac{a_n}{b_n} = \lim_{n \rightarrow \infty} \frac{(2n+1)n}{(n+1)^2} = 2
$$

즉, 극한 비교 판정법에서의 1번 케이스에 해당한다. 이 경우는 두 급수의 수렴/발산이 동일해진다.
비교에 사용한 급수 $\sum b_n$이 발산하므로, 확인하려는 급수 $\sum a_n$도 발산한다. $\blacksquare$

</div>


# 절대 수렴 판정법

"절대 수렴"이라는 개념은 양수/음수가 번갈아가며 나오는 "교대 급수"에서 다루는 개념이다. 교대 급수에 대해서는 별도의 블로그 포스트에서 다루었다. [링크](/2024/06/08/determine-the-limit-of-an-alternating-series/)

그런데, 교대 급수에 대한 모든 절대 수렴 판정법은 일반 급수에도 그대로 적용할 수 있다. 왜냐하면, 어떤 급수가 절대 수렴하면, 그 급수가 수렴하기 때문이다.

<div class="theorem" markdown="1">

If $\sum_{n=1}^{\infty} \| a_n \|$ converges, then $\sum_{n=1}^{\infty} a_n$ converges too.

</div>

아래부터는 교대 급수의 절대 수렴 판정에서 사용하는 것들이다.

## 비율(ratio) 판정법

수열의 증가율(ratio)의 극한으로 급수의 수렴/발산을 판단하는 방법이다.
기하 급수 $\sum ar^n$의 극한 판정에서 $ar^{n+1}/ar^n = r$을 기준으로 극한을 판단하는 것과 비슷한 접근이다.

<div class="theorem" markdown="1">

교대 수열의 증가율 $\rho$을 아래와 같이 정의하자.

$$
\lim_{n\rightarrow\infty} \left| \frac{a_{n+1}}{a^n} \right| = \rho
$$

- $\rho < 1$라면, "절대 수렴"한다.
- $\rho > 1$라면, 발산한다.
- $\rho = 1$라면, 판단할 수 없다.

</div>

## 루트 판정법

교대 수열에 루트를 씌워서 판단할 수도 있다.

<div class="theorem" markdown="1">

교대 수열에 대해서 $\rho$를 아래와 같이 정의하자.

$$
\lim_{n\rightarrow\infty} \sqrt[n]{\left| a_n \right|} = \rho
$$

- $\rho < 1$라면, "절대 수렴"한다.
- $\rho > 1$라면, 발산한다.
- $\rho = 1$라면, 판단할 수 없다.

</div>
