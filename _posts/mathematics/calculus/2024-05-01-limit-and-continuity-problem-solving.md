---
title: "Limit and Continuity: Problem Solving"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Calculus"]
excerpt: "입델 논법이 만들어지는 과정에 대해서, 그리고 재밌었던 문제들"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. 공부하면서 재밌어 보였던 문제들과 풀이들을 모아서 정리한 포스트 입니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# 입델 논법이 수립된 과정

극한을 잘못된 명제로 정의 했을 때 발생하는 문제를 살펴본다. 과정을 따라가다보면 입델 논법에서 $\epsilon$과 $\delta$가 왜 필요한지 자연스럽게 알게 된다.

## Wrong statement about limits

<div class="notice" markdown="1">
Show by example that the following statement is wrong.

> The number $L$ is the limit of $f(x)$ as $x$ approaches $c$ if $f(x)$ gets closer to $L$ as $x$ approaches $c$.

Explain why the function in your example does not have the given value of $L$ as a limit as $x \rightarrow c$.
</div>

반례는 의외로 쉬운데, $f(x) = x^2$일 때, $f(x)$는 $x \rightarrow 0$으로 가면 $-1$에 가까워진다. 그런데 극한을 위와 같이 정의하면 극한값 $L$을 $-1$로 잡아도 그것이 성립한다.

이것은 표현의 모호성 때문인데, "$f(x)$ gets closer to $L$"라는 표현이 $d(x) = \|f(x) - L\|$의 값이 $x \rightarrow c$에 따라 줄어들기만 하면 되기 때문이다. 간격 함수 $d(x)$의 값이 꼭 0에 가까워질 필요가 없다. 이렇게 정의할 경우 함수의 극한값이 유일하지 않고 여러개 존재하는 꼴이 된다.

그래서 정확하게 표현하려면, 간격 $d(x) \rightarrow 0$로 극한과 함숫값이 거의 일치해서 0에 가까워져야 함을 언급 해야 한다.

cc. 사실 뭐가 틀렸는지 도저히 생각을 못해서 검색해서 찾았다 ㅠㅠ [출처](https://math.stackexchange.com/questions/3435671/wrong-definition-of-limit)

## Another wrong statement about limits

<div class="notice" markdown="1">

Show by example that the following statement is wrong.

> The number $L$ is the limit of $f(x)$ as $x$ approaches $c$ if, given any $\epsilon > 0$, there eixsts a value of $x$ for which $\| f(x) - L \| < \epsilon$.

Explain why the function in your example does not have the given value of $L$ as a limit as $x \rightarrow c$.
</div>

이번엔 간격 극한값과 함수 값이 거의 일치해야 한다는 조건이 붙었다. 그러나 안타깝게도 $\| f(x) - L \| < \epsilon$을 만족할 때의 $x$ 값에 대한 조건이 빠져있다...

함수 $f(x)$를 $\sin x$로 잡고, $c = 0$, 극한값 $L$은 $1/2$로 잡아보자. 그러면 임의의 $\epsilon > 0$에 대해서 $\| \sin x - 1/2 \| < \epsilon$를 만족하는 $x$가 항상 존재하는데, 그건 바로 $x = \pi/6$ 일 때다!

어?! 그런데 $x = \pi/6$은 $c = 0$과 너무 멀지 않은가?? 안타깝게도 함숫값과 극한값이 가까울 때의 $x$ 값에 대한 조건을 정하지 않았다... "$x$ approaches $c$"라고 표현 했지만, 이런 표현은 엄밀하지 않다. 그래서 $x$ 값이 $\pi/6$이더라도 위의 명제가 만족하는 것이다...!

그래서 입델-논법은 $\| x - c \| < \delta$ 범위의 모든 $x$가 $\epsilon$에 대한 부등식을 만족하는 $\delta > 0$의 개념이 추가된 것이다.

cc. 요 문제도 어디가 잘못된 건지 도저히 못 찾겠어서 솔루션 책을 보고야 이해했다 ㅠㅠ

## 그래서 우린 입델-논법을 극한의 정의로 사용합니다

위의 두 문제는 입델-논법에서 $\epsilon$와 $\delta$가 없으면 벌어지는 끔찍한 일들을 말해준다. 역시 입델은 짱이야... 그래서 입델을 극한의 엄밀한 정의라고 하나보다.

<div class="notice" markdown="1">

Let $f(x)$ be defined on an poen interval about $c$, except possible at $c$ itself. (함수값이 $x=c$에서 정의되지 않아도 상관 없기 때문.)

We say that the limit of $f(x)$ as $x$ approaches $c$ is the number $L$, and write

$$
\lim_{x \rightarrow c} f(x) = L
$$

if for every number $\epsilon > 0$, there exists a corresponding number $\delta > 0$ s.t. for all $x$,

$$
0 < | x - c | < \delta \Longrightarrow | f(x) - L | < \epsilon
$$

</div>

입델-논법에서 유도되는 유명한 성질이 바로 "Uniqueness of Limit"다. 입델-논법이 극한을 엄밀하게 정의해준 덕분에 "극한이 여러개 있으면 어떻하지?"라는 불안감 없이 극한은 하나야!라고 말할 수 있다.

<hr/>

# A Fixed point Theorem

<div class="notice" markdown="1">

Suppose that a function $f$ is continuous on the closed interval $[0, 1]$ and that $0 \leq f(x) \leq 1 $ for every $x$ in $[0, 1]$. Show that there must exist a number $c$ in $[0, 1]$ s.t. $f(c) = c$ ($c$ is called a fixed point of $f$).

</div>

**풀이.** $g(x) = f(x) - x$라고 하자. 양끝에 대해서 $0 \leq g(0) \leq 1$, $-1 \leq g(1) \leq 0$을 만족한다.

만약 $g(0) = 0$이거나 $g(1) = 0$이면 $f(0) = 0$, $f(1) = 1$이기 때문에 위의 명제를 만족한다.

만약, $g(0) \neq 0$이고 $g(1) \neq 0$라면 $0 <g(0)$이고, $g(1) < 0$이 되는데, 중간값 정리(Intermediate Value Theorem)을 사용하면 $(0, 1)$ 범위 사이에 어떤 상수 $c$가 있어 $g(c) = 0$을 만족하는 $c$가 존재함을 보장할 수 있다. 그런데 $g(c) = 0$은 곧 $f(c) = c$가 됨을 의미하므로, 위의 명제가 성립한다. $\blacksquare$

<hr/>

# Assigning a value to zero power of zero

$$
0^0
$$

의 값으로 아래 둘 중 어떤 값이 맞을지에 대한 문제다.

- $a^0 = 1$이므로 $0^0$도 $1$이다.
- $0^n = 0$이므로 $0^0$도 $0$이다.

일단 통상적으로는 $0^0 = 1$로 정의한다. 그 이유는 $f(x) = x^x$ 함수를 정의해 아래 케이스를 계산해보면

- $f(0.1) = 0.794$
- $f(0.01) = 0.955$
- $f(0.001) = 0.993$

으로 점점 $1$로 수렴하기 때문이다. 어쩌면 $0^0$은 $f(x) = x^x$에서 정의되지 않는 값일 수도 있고, 우리는 $0^0 = 1$라고 얘기하는 것도 정확한 함숫값이 아니라 그 극한값이 그렇다는 걸 얘기하는 걸지도 모르겠다.
