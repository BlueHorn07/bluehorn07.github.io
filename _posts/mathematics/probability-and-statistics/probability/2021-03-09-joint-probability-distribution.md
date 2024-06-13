---
title: "Joint Probability Distribution"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

## Joint Probability Distribution

앞에서는 하나의 RV에 대한 probability distribution을 살펴보았다. 하지만, 현실에서는 둘 이상의 RV에 대한 결과를 동시에 고려해야 하는 경우가 많다. \<Joint Probability Distribution\>은 이런 둘 이상의 RV를 수학적으로 정의한 개념이다.

Joint Probability는 Discrete RV와 Continuous RV에서 각각 \<Joint pmf\>, \<Joint pdf\>로 정의된다.

<br><span class="statement-title">Definition.</span> Joint pmf<br>

The function $f(x, y)$ is a \<joint probability distribution\> or \<joint pmf\> of the discrete RV $X$ and $Y$ if

1. $f(x, y) \ge 0$ for all $(x, y)$.
2. $\displaystyle \sum_x \sum_y f(x, y) = 1$
3. $P(X=x, Y=y) = f(x, y)$

Also, for any region $A$ in the $xy$ plane, $\displaystyle P[(X, Y) \in A] = \sum \sum_A f(x, y)$

<br><span class="statement-title">Definition.</span> Joint pdf<br>

The function $f(x, y)$ is a \<joint density function\> of the continuous RV $X$ and $Y$ if

1. $f(x, y) \ge 0$, for all $(x, y)$.
2. $\displaystyle \int^\infty_\infty \int^\infty_\infty f(x, y) \; dx dy = 1$
3. $\displaystyle P[(X, Y) \in A] = \int \int_A f(x, y) \; dx dy$, for any region $A$ in the $xy$ plane.


### Marginal Distribution

<br><span class="statement-title">Definition.</span> Marginal Distribution<br>

The \<marginal distributions\> of $X$ alone and of $Y$ alone are

$$
g(x) = \sum_y f(x, y) \quad \text{and} \quad h(y) = \sum_x f(x, y)
$$

for the discrete case, and

$$
g(x) = \int^\infty_{-\infty} f(x, y) \; dy \quad \text{and} \quad h(y) = \int^\infty_{-\infty} f(x, y) \; dx
$$

for the continuous case.

**보충**: \<Discrete RV에 대한 Marginal Distribution\>은 그 바탕에 \<Law of Total Probability\>가 깔려있다!


### Conditional Probability Distribution

앞에서 \<Conditional Probability\> $P(Y \mid X)$에 대해 다뤘다. 하지만, 우리는 이 \<Conditional Probability\>에 대한 계산을 좀더 효율적으로 계산하기 위해 아래와 같이 RV $X$, $Y$에 대한 Probability Distribution으로 유도할 수 있다!

$$
P(Y = y \mid X = x) = \frac{P(X=x, Y=y)}{P(X=x)} = \frac{f(x, y)}{f_{X} (x)}, \quad \text{provided} \; f_X (x) > 0
$$

위와 같이 \<Conditional Probability\>를 "분포(Distribution)"의 형태로 기술한 것을 \<Conditional Probability Distribution\>라고 한다!

<br><span class="statement-title">Definition.</span> Conditional Probability Distribution<br>

Let $X$ and $Y$ be two random variables, discrete or continuous. The \<conditional distribution of the RV $Y$ given that $X = x$\> is

$$
f(y \mid x) = \frac{f(x, y)}{f_X (x)}, \quad \text{provided} \; f_X (x) > 0
$$

Similarly, the \<conditional distribution of the RV $X$ given that $Y=y$\> is

$$
f(x \mid y) = \frac{f(x, y)}{f_Y (y)}, \quad \text{provided} \; f_Y (y) > 0
$$

### Statistical Independence

\<Conditional Probability\>에서 정의한 \<Independent Event\>의 개념을 \<Conditional Probability Distribution\>에서도 적용해볼 수 있다!!

<br><span class="statement-title">Definition.</span> Statistical Independence<br>

Let $X$ and $Y$ be two RVs, discrete or continuous, with joint probability distribution $f(x, y)$ and marginal distributions $f_X (x)$ and $f_Y (y)$, respectively.

The RVs $X$ and $Y$ are said to be \<statistically independent\> if and only if

$$
f(x, y) = f_X (x) f_Y (y)
$$

for all $(x, y)$ within their range.

또는 이렇게 생각해볼 수도 있다. 만약 conditional distribution $f(x \mid y)$가 $y\;$에 dependent 하지 않다면 그러니까 independent 하다면, 당연히 $f(x \mid y)$는 $y\;$의 결과에 아무런 영향을 받지 않아야 할 것이다. 그러기 위해서는 $f(x \mid y)$에서 $y$에 대한 텀이 존재하지 않아야 한다!

즉, $\dfrac{f(x, y)}{f_Y (y)}$에서 $y\;$에 대한 텀이 모두 소거 된다는 말이다. 이를 다시 바라보면, $f(x, y)$에서 $f_Y (y)$로 $y$ 텀을 완전히 분리할 수 있다는 말이다.

$$
f(x, y) = f_Y (y) \cdot g(x)
$$

그런데 똑같은 작업을 $f(y \mid x)$에 수행해보면, 이번에는 $f(x, y) = f_X (x) \cdot h(y)$가 나온다. 그래서 이 두 결과를 잘 조합하면, \<독립\>에 대해 위와 같이 \<marginal distribution\>의 곱이 \<probability distribution\>이다라고 정의하는 것이 자연스러운 것이다! 😆

<br/>

이것을 $N$개의 random variable에 대해 일반화하면 아래와 같다.

<br><span class="statement-title">Definition.</span> mutually statistical independence<br>

Let $X_1, X_2, \dots, X_n$ be $n$ random variables, discrete or continuous, with joint probability distribution $f(x_1, x_2, \dots, x_n)$ and marginal distribution $f_1(x_1), f_2(x_2), \dots, f_n (x_n)$, respectively. The random variables $X_1,  X_2, \dots, X_n$ are said to be \<mutually statistically independent\> if and only if

$$
f(x_1, x_2, \dots, x_n) = f_1(x_1) f_2(x_2) \cdots f_n (x_n)
$$

for all $(x_1, x_2, \dots, x_n)$ within their range.

<hr/>

\<Marginal Distribution\>에 대한 다음의 문제를 답해보자.

Q. We know the marginal pmfs $f_X (x)$ and $f_Y (y)$, can you find the joint pmf $f(x, y)$?

<hr/>

<br><span class="statement-title">Example.</span><br>

Let $(X, Y)$ have joint pdf

$$
f(x, y) = \begin{cases}
    1 && (x, y) \in [0,1] \times [0, 1] \\
    0 && \text{otherwise}
\end{cases}
$$

(a) Are $X$ and $Y$ independent?

(b) Let $Z := \max (X, Y)$. Find the distribution of $Z$. (Hint: Find cdf of $Z$)

(c) Let $W := \min (X, Y)$. Find the distribution of $W$. (Hint: Find cdf of $W$)

<hr/>

이번 파트에선 \<Joint Probability\>를 구하기 위해 적분을 조금 해야 했다. 하지만, 그렇게 어려운 적분은 아니기 때문에 몇번만 연습하면 금방 익숙해진다!! 😊

이어지는 포스트에서는 RV의 확률을 이용해 \<평균\>, \<분산\>, \<공분산\>을 유도해본다!

👉 [Mean, Variance, and Covariance]({{"/2021/03/16/mean-variance-covariance" | relative_url}})