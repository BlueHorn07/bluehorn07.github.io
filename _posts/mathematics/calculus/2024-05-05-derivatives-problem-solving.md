---
title: "Derivatives: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "모든 점에서 미분 불가능한 연속 함수, Cissoid, 드립 커피는 언제 다 내려갈까에 대한 문제 ☕️"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. 공부하면서 재밌어 보였던 문제들과 풀이들을 모아서 정리한 포스트 입니다. [미적분학 포스트 전체 보기](https://bluehorn07.github.io/categories/calculus)
{: .notice--info}

# 모든 점에서 미분 불가능한 연속 함수

일단 연속 함수더라도 몇몇 점에서는 미분이 불가능할 수도 있다. 뾰족점이 있다면 해당 점에서 미분이 불가능하기 때문이다. 만약 뾰족점이 없더라도 $f(x) = \sin (1/x)$같이 무한번 진동하는 함수는 $x=0$에서 미분 불가능이다.

<div>
  <img class="align-center" src="/images/mathematics/calculus/sin-1-x.png" width="400px">
</div>

그런데 연속함수인데 특정 위치만 미분 불가능한게 아니라 모든 점에서 미분 불가능한 함수도 있을까?? 이 질문은 전공책 연습 문제에 있던 건데, 연속 함수면 당연히 모든 점에서 미분 가능할 줄 알았던 직관을 깨는 엄청난 명제와 그 예시 였다. 예제는 이런 함수를 소개했다.

<div class="notice" markdown="1">

<big><b>Weierstrass's function</b></big>.

$$
f(x) = \sum_{n=0}^{\infty} a^n \cos (b^n \pi x)
$$

- $0 < a < 1$
- $b$는 양의 홀수
- $ab > 1 + 3\pi/2$

</div>

<p><a href="https://commons.wikimedia.org/wiki/File:WeierstrassFunction.svg#/media/File:WeierstrassFunction.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/6/60/WeierstrassFunction.svg" alt="WeierstrassFunction.svg" width="360"></a><br>By <a href="//commons.wikimedia.org/w/index.php?title=User:Eeyore22&amp;action=edit&amp;redlink=1" class="new" title="User:Eeyore22 (page does not exist)">Eeyore22</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=5075959">Link</a></p>

함수 모양은 대충 이렇게 생겼다...

요 함수의 연속성과 미분 불가능성에 대해서 증명해야 하는데, 아직 증명을 위해 필요한 파트까지 공부를 못 해서... 일단 증명은 패스 하겠다!!

<hr/>

# Generalizing the Product Rule

두 함수의 곱을 미분하면 아래와 같다.

$$
\frac{d}{dx} (f(x)g(x)) = f(x)g'(x) + f'(x)g(x)
$$

만약 함수가 3개, 4개 곱해져 있는 꼴이라면 그것의 미분은 어떻게 될까?? 답은 간단한데, 아래와 같다.

$$
\frac{d}{dx} (uvw) = uvw' + uv'w + u'vw
$$

이렇게 함수 곱에서 하나씩만 미분되고 그걸 모두 합한 꼴이 된다. 증명은 별로 안 어려움~~

<hr/>

# About the Chain Rule

<div class="notice" markdown="1">

Supp. that $f(x) = x^2$ and $g(x) = \|x\|$. Then the composite

- $(f \circ g)(x) = \|x\|^2 = x^2$
- $(g \circ f)(x) = \|x^2\| = x^2$

are both differentiable at $x=0$ even though $g(x)$ itself is not differentiable at $x=0$. Does the contradict the Chain Rule?

</div>

결론부터 말하면, Chain Rule과 상충 되지 않는다. 먼저 Chain Rule을 기술하면 아래와 같다.

<div class="notice" markdown="1">

If $g(x)$ is a function that is differentiable at a point $c$, i.e. the derivative $g'(c)$ exists, and $f(x)$ is a function that is differentiable at $g(c)$, then the composite function $f \circ g$ is differentiable at $c$ ...

</div>

Chain Rule은 $g'(c)$가 존재하는 상황에서 합성함수가 미분 가능한지를 설명하는 명제다. 이번 경우는 $g'(c=0)$가 미분 불가능하기 때문에 전제가 거짓이다. 따라서 Chain Rule을 적용할 수 없는 상황이다.

어라? Chain Rule을 적용할 수 없다면 합성함수의 도함수를 어떻게 구해야 할까? 만약 그렇다면 도함수의 정의를 바탕으로 합성함수의 도함수를 유도하면 된다. 이번 경우는 $(g \circ f)'(x) = 2x$가 될 것이다.

Chain Rule은 $g'(c)$가 존재할 빠르게 합성함수의 도함수를 구하는 방법일 뿐이다.

# The cissoid of Diocles

<p><a href="https://commons.wikimedia.org/wiki/File:Cissoide2.svg#/media/File:Cissoide2.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/a/a3/Cissoide2.svg" alt="Cissoide2.svg" width="200"></a><br>By HB - <span class="int-own-work" lang="en">Own work</span>, <a href="https://creativecommons.org/licenses/by-sa/4.0" title="Creative Commons Attribution-Share Alike 4.0">CC BY-SA 4.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=6955969">Link</a></p>

교재 연습 문제 중에 이렇게 생긴 곡선의 접선과 직교선을 구하는 문제가 있었다.

요걸 디오클레스의 시소이드라고 부르며 Implit Function 식은 아래와 같이 나온다.

$$
y^2(2-x) = x^3
$$

일단 이 식이 어떻게 나왔는지를 이해하려면 시소이드(Cissoid)가 뭔지 이해해야 한다.

## Cissoid

<p class="img-wrapper"><a href="https://commons.wikimedia.org/wiki/File:Allgemeine_zissoide_english.svg#/media/File:Allgemeine_zissoide_english.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/3/3d/Allgemeine_zissoide_english.svg" alt="Allgemeine zissoide english.svg" width="300"></a><br>By <a href="//commons.wikimedia.org/wiki/User:Kmhkmh" title="User:Kmhkmh">Kmhkmh</a> - <span class="int-own-work" lang="en">Own work</span>, <a href="https://creativecommons.org/licenses/by/4.0" title="Creative Commons Attribution 4.0">CC BY 4.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=114410773">Link</a></p>

좌표계 위에 원점 $O$과 두 곡선(또는 선분) $C_1$, $C_2$가 있는 상황을 생각해보자.

원점 $O$에서 시작해 무한히 뻗어나는 두 곡선 $C_1$, $C_2$을 모두 지나는 선분 $\overrightarrow{OP}$를 잡을 수 있다. 이때 곡선 $C_1$, $C_2$을 지나며 두 곡선 사이의 거리 $\overrightarrow{P_1 P_2}$를 계산할 수 있는데, 그것을 원점 $O$에서 투영한 걸 Cissoid라고 부른다.

## 식 유도

<p><a href="https://commons.wikimedia.org/wiki/File:Cissoid_of_Diocles.gif#/media/File:Cissoid_of_Diocles.gif"><img src="https://upload.wikimedia.org/wikipedia/commons/a/a3/Cissoid_of_Diocles.gif" alt="Cissoid of Diocles.gif" height="384" width="748"></a><br>By <a href="//commons.wikimedia.org/w/index.php?title=User:Dasha_Mic&amp;action=edit&amp;redlink=1" class="new" title="User:Dasha Mic (page does not exist)">Dasha Mic</a> - <span class="int-own-work" lang="en">Own work</span>, <a href="https://creativecommons.org/licenses/by-sa/4.0" title="Creative Commons Attribution-Share Alike 4.0">CC BY-SA 4.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=48970941">Link</a></p>

일단 원을 $C$, 원에 접하는 선분을 $L$라고 두자. 그리고 원 $C$의 반지름을 $a$라고 두고 $C$와 $L$을 극좌표로 표현 하면 아래와 같다.

$$
\begin{aligned}
C: r = 2a \cos \theta \\
L: r = 2a \sec \theta
\end{aligned}
$$

Cissoid의 정의에 의해 곡선의 극 방정식은 $L - C$가 된다.

$$
r = 2a (\sec \theta - \cos \theta)
$$

요 식을 조금 변형하면

$$
\begin{aligned}
r &= 2a \left( \frac{1}{\cos \theta} - \cos \theta \right) \\
&= 2a \frac{1 - \sin^2 \theta}{\cos \theta} \\
&= 2a \sin^2 \theta / \cos \theta \\
&= 2a \sin \theta \tan \theta
\end{aligned}
$$

이제 극 방정식을 직교 좌표계로 변환하면 아래와 같다.

$$
\begin{aligned}
x &= r \cos \theta = 2a \sin^2 \theta \\
y &= r \sin \theta = 2a \sin^2 \tan \theta
\end{aligned}
$$

이때, $t = \tan \theta$로 두고 parametric equation(매개 방정식)으로 바꿔보면...

$$
\sin^2 \theta = \frac{t^2}{1 + t^2}
$$

이므로

$$
\begin{aligned}
x &= 2a \sin^2 \theta = \frac{2a t^2}{1 + t^2} \\
y &= 2a \sin^2 \tan \theta = \frac{2a t^3}{1 + t^2}
\end{aligned}
$$

이제 요 식을 정리해보자. $y = xt$이기 때문에 $t = y/x$로 두고 식을 정리하면 된다.

$$
\begin{aligned}
x
&= \frac{2a y^2 / x^2}{1 + y^2 / x^2} \\
&= \frac{2a y^2}{ x^2 + y^2}
\end{aligned}
$$

최종적으로 아래의 식이 도출 된다.

$$
x(x^2 + y^2) = 2ay^2
$$

$\blacksquare$

# Applications

## Making Coffee

<div class="img-wrapper">
  <img src="/images/mathematics/calculus/making-coffee.png" width="320px">
</div>

원뿔과 원기둥의 높이 변화에 대한 식을 유도하는 문제다. 뭔가 드립 커피 내려먹을 때 생각날 것 같은 문제라서 풀어봤다 ㅋㅋ

일단 원뿔과 원기둥에 대해 아래 식이 성립한다.

$$
V_1(t) + V_2(t) = \text{const.}
$$

그리고 초기 $t=0$ 때 원뿔에 커피가 가득 차 있었다면, 그 부피는

$$
V_1(t=0) = \pi r^2 \cdot 1/3 \cdot h = 18 \pi
$$

그리고 부피가 $10 t$ 만큼 줄어든다고 했으니 부피에 대한 식은 이렇게 될 것이다.

$$
\begin{aligned}
V_1(t) &= 18 \pi - 10 t \\
V_2(t) &= 10 t
\end{aligned}
$$

이제 위의 두 식을 높이 $h_1(t)$, $h_2(t)$에 대한 식으로 바꿔보자.

원기둥은 아래에서 위로 높이가 증가하기 때문에 더 구하기 쉬우니 먼저 구하자.

$$
\begin{aligned}
V_2(t) &= 10 t = 3^2 \pi \cdot h_2(t) \\
h_2(t) &= \frac{10 t}{9 \pi}
\end{aligned}
$$

이제 원뿔에 대해서 구해보자. 원뿔의 높이 $h_1(t)$는 시간이 지날 수록 감소한다. 게다가 원뿔의 밑면의 반지름도 시간이 지날 수록 감소한다. 다행히 원뿔에서 높이와 반지름이 2:1 비율이니 밑면의 반지름은 $h_1(t)/2$가 되긴 할거다.


$$
V_1(t) = 18 \pi - 10 t = (h_1(t)/2)^2 \pi \cdot 1/3 \cdot h_1(t) = \frac{\pi \cdot (h_1(t))^3}{12}
$$

이때, 원기둥과 원뿔의 높이의 변화율을 확인해보자.

먼저 원기둥의 높이 변화율의 값은 일정하다.

$$
h_2'(t) = \frac{10}{9\pi}
$$

원뿔의 높이 변화율은 원뿔 높이의 제곱에 반비례 한다.

$$
\begin{aligned}
V_1'(t) = - 10 &= \frac{\pi}{12} \cdot 3 (h_1(t))^2 h_1'(t) \\
h_1'(t) &\propto - 1 / h_1(t)^2
\end{aligned}
$$

즉, 원뿔의 높이가 높을 때는 천천히 감소하다가 원뿔의 높이가 낮을 때는 높이가 빠르게 감소한다. $\blacksquare$

사실 $h_1(t)$에 대한 식을 Implicit Function 형태가 아니라 $t$에 대한 정확한 식으로 표현할 수 있었지만, 그렇게 하지 않고 Implicit Function에서 형태에서 바로 미분한게 오히려 높이의 변화율을 아는데 더 도움이 된 것 같다.

# exponential function defined by limit

<div class="notice" markdown="1">

Show that 

$$
\lim_{n\rightarrow \infty} \left( 1 + \frac{x}{n} \right)^n = e^x
$$

for any $x > 0$.

</div>

$1^{\infty}$ 꼴의 부정형이다! 이 경우 $\ln$을 씌우고, 거기에 로피탈 정리를 적용하는 접근을 "교재에서" 언급 했다.

Let $f(n) = \left( 1 + \frac{x}{n} \right)^n$, we will see $\lim \ln f(n)$.

$$
\begin{aligned}
\lim_{n\rightarrow \infty} \ln f(n) 
&= \lim n \cdot \ln (1 + x/n) \\  
&= \lim \frac{\ln(1+x/n)}{1/n}
\end{aligned}
$$

요렇게 두면 $0 / 0$ 꼴의 부정형이기 때문에 로피탈 정리를 적용해 극한을 구할 수 있다!

$$
\lim \frac{\ln(1+x/n)}{1/n} \rightarrow \lim \frac{\frac{1}{1+x/n} \cdot - \frac{x}{n^2}}{- 1 / n^2} = \lim \frac{n x}{n + x} = \lim \ln f(n)
$$

이제 $\ln$을 씌웠던 걸 다시 풀어보면

$$
\begin{aligned}
\lim_{n \rightarrow \infty} f(n) 
&= \lim_{n \rightarrow \infty} e^{\ln f(n)} \\
&= \lim_{n \rightarrow \infty} e^{\frac{nx}{n+x}} \\
&= \lim_{n \rightarrow \infty} e^{x \cdot \frac{n}{n+x}} \\
\end{aligned}
$$

이때 $\frac{n}{n+x}$의 극한은 $1$로 수렴하므로, 최종적으로 $e^x$만 남게 된다. $\blacksquare$

# Newton's Serpentine

![](/images/mathematics/calculus/newton-serpentine.png)

Newton이 정형화한 Cubic Curve. 아래와 같은 방정식을 갖는다.

$$
y(x^2 + a^2) = abx
$$

몇가지 특정을 가지고 있는데

- 원점을 중심으로 반전.
- $x$축이 점근선
- 함수값은 $y = \pm b / 2$ 사이에 존재

## Parametric Equation

Serpentine Curve를 parametric equation으로 표현하면 아래와 같다.

$$
\begin{aligned}
x &= a \tan t \\
y &= b \sin t \cos t
\end{aligned}
$$

이때 식을 조금 변형하고 정리하면 Explicit Form으로 표현할 수도 있다!!

$$
\begin{aligned}
\arctan x/a &= t \\
y = b \sin t \cos t &= b \sin (2t) \\
\end{aligned}
$$

따라서 $y = b \sin (2 \cdot \arctan x / a)$의 형태로 정리된다. 그래프를 그려보면 Implicit Form으로 적은 것과 동일한 것도 확인할 수 있다 ㅎㅎ

# 참고문헌

- Wikipedia
  - [Weierstrass Function](https://en.wikipedia.org/wiki/Weierstrass_function)
  - [Cissoid](https://en.wikipedia.org/wiki/Cissoid)
  - [Serpentine Curve](https://en.wikipedia.org/wiki/Serpentine_curve)
