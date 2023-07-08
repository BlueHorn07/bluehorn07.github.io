---
title: "Negative Binomial Theorem"
toc: true
toc_sticky: true
categories: ["Probability"]
preview: 조합에 음수 $n$이 들어간 $\binom{-n}{k}$를 어떻게 정의할까?
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

- Negative Combination
- Derivation
- Negative Combination (2)
- Negative Binomial Theorem

<hr/>

# 도입말

**\<조합; combination\>**은 아래와 같이 정의된다.

$$
\binom{n}{k} = \frac{n!}{k!(n-k)!}
$$

그러나 이것은 $n$과 $k$가 양수인 경우에 한정되었다. 이번 포스트에선 <span class="red">$n$이 음수</span>인 경우를 살펴보고자 한다.

# Negative Combination

\<Combination\> $\binom{n}{k}$의 해석은 본래 아래와 같다.

<div class="statement" markdown="1">

$n$개 대상에서 $k$개를 '순서없이' 뽑는 경우의 수

</div>

그러나 $\binom{n}{k}$의 실제 값을 계산할 때, 본인은 이렇게 이해하지 않고 아래와 같이 이해하고 계산한다.

<div class="statement" markdown="1">

분모는 $k!$이고, 분자는 $n$부터 시작해서 $(-1)$를 $k$번 뺀 녀석들의 곱.

</div>

<br/>

$n$이 음수인 \<Negative Binomial\>도 크게 다르지 않다. 위의 규칙을 그대로 적용하면 된다.

식을 써보면, (여기서는 $n$이 양수값이다.)

$$
\binom{-n}{k} = \frac{(-n) \cdot (-n-1) \cdots (-n-(k-1))}{k!}
$$

이렇게 된다! 분자 부분에 $(-n)$부터 시작해 $(-1)$를 $k$번 뺀 녀석의 곱을 만들어줬다!

이미 "$n$개 대상에서 $k$개를 '순서없이' 뽑는 경우의 수"라는 해석은 무의미 해졌다. \<Combination\> $\binom{n}{k}$라는 연산이 추상화되고 일반화된 것이다! 👏

# Derivation

아이디어는 실변수 함수 $f(x)$를 다항 함수의 멱급수로 표현하는 [\<매크로린 급수; Macluarin Series\>]({{"/2022/10/29/talyor-series-and-maclaurin-series.html" | relative_url}})에서 출발한다. 예제는 [Brilliant: Negative Binomial Theorem](https://brilliant.org/wiki/negative-binomial-theorem/)에서 빌려왔음을 미리 밝힌다. 아래의 함수를 \<테일러 전개\> 해보자.

<div class="proof" markdown="1">

$$
\frac{1}{(1+x)^3}
$$

\<Macluarin Series\>는 $x = 0$ 주변에서 실변수 함수 $f(x)$를 아래와 같이 표현한 것이다.

$$
f(x) = f(0) + f'(0)x + \frac{f''(0)}{2!} x^2 + \cdots + \frac{f^{(k)}(0)}{k!} x^k + \cdots
$$

$(1+x)^{-3}$의 미분값들을 계산해보자.

$$
\begin{aligned}
  f(x) = (1 + x)^{-3} &\rightarrow f(0) = 1 \\
  f'(x) = -3 (1 + x)^{-4} &\rightarrow f(0) = -3 \\
  f''(x) = (-3 \cdot -4) (1 + x)^{-5} &\rightarrow f(0) = -3 \cdot -4 \\
  &\vdots \\
  f^{(k)}(x) = (-3 \cdot -4 \cdots (-k-2)) (1 + x)^{-3 - k} &\rightarrow f^{(k)}(0) = -3 \cdot -4 \cdots (-k-2) \\
\end{aligned}
$$

<br/>

이제 함수 $f(x)$를 \<Macluarin Series\>로 표현하면

$$
1  -3 x + \frac{-3 \cdot -4}{2!} x^2 + \cdots + \frac{-3 \cdot -4 \cdots (-k - 2)}{k!} x^k + \cdots
$$

</div>

어라? 각 항의 계수를 잘 살펴보면, 앞에서 살펴본 \<Negative Combination\>의 형태가 엿보인다! 식을 바꿔보면,

$$
1 + \binom{-3}{1} x + \binom{-3}{2} x^2 + \cdots + \binom{-3}{k} x^k + \cdots
$$

와! 식을 더 깔끔하게 쓸 수 있다! 😀

<br/>

위의 예제를 일반화하면,

$$
\begin{aligned}
&\frac{1}{(1 + x)^n} \\
&= 1 + \binom{-n}{1} x + \binom{-n}{2} x^2 + \cdots + \binom{-n}{k} x^k + \cdots \\
&= \sum^{\infty}_{k=0} \binom{-n}{k} x^k
\end{aligned}
$$

뒤에서 \<Negative Binomial Theorem\>을 논할 때 다시 볼 것이다.

# Negative Combination (2)

\<Negative Combination\>을 다르게 표현할 수도 있다.

$$
\binom{-n}{k} = \frac{(-n) \cdot (-n-1) \cdots (-n-(k-1))}{k!}
$$

<div class="proof" markdown="1">

식에서 음수를 전부 빼서 $(-1)$의 곱셈으로 압축해보자.

$$
\frac{(-n) \cdot (-n-1) \cdots (-n-(k-1))}{k!} = \frac{n \cdot (n+1) \cdots (n + (k - 1))}{k!} \cdot (-1)^{k}
$$

어라? 이 식의 순서를 바꿔보면...

$$
\begin{aligned}
&\frac{n \cdot (n+1) \cdots (n + (k - 1))}{k!} \cdot (-1)^{k} \\
&= \frac{(n + (k - 1) \cdots n)}{k!} \cdot (-1)^{k} \\
&= \binom{n + k - 1}{k} \cdot (-1)^k
\end{aligned}
$$

</div>

즉, 정리하면

$$
\binom{-n}{k} = \binom{n + k - 1}{k} \cdot (-1)^k
$$

인 것이다!

# Negative Binomial Theorem

\<이항 정리; Binomial Theorem\>에 따르면 아래가 성립한다.

$$
(1 + x)^n = \sum^{n}_{k = 0} \binom{n}{k} x^k
$$

이것을 음수 차수에 대해 기술하면, \<Negative Binomial Theorem\>이 된다.

$$
(1 + x)^{-n} = \sum^{\infty}_{k = 0} \binom{-n}{k} x^k = \sum^{\infty}_{k = 0} \binom{n + k - 1}{k} (-1)^k x^k
$$

<hr/>

# 맺음말

사실 \<Negative Binomial Theorem\>은 정리(Theorem) 수준의 대단한 녀석은 아니다. 그러나 본인은 처음 이 녀석을 봤을 떄, \<Combination\>에 음수 $n$이 들어갈 수 있다는 사실을 받아들이는데 애를 먹었다 😥

이 정리는 \<Negative Binomial Distribution\>의 성질을 증명할 때, 다시 등장한다.

👉 [Discrete Probability Distribution (2): Negative Binomial Distribution]({{"/2021/03/24/discrete-probability-distributions-2.html" | relative_url}})

<hr/>

# References

- [Brilliant: Negative Binomial Theorem](https://brilliant.org/wiki/negative-binomial-theorem/)