---
title: "Parametric Equations: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "Hypocycloid, Trochoids"
---

# The witch of Maria Agnesi

![](/images/mathematics/calculus/the-witch-of-maria-agnesi.png){: style="height: 300px" }

<div class="problem" markdown="1">

직전 $OA$와 $x$축이 이루는 각을 $t$라고 하자. 이때, 점 $P$의 자취에 대한 방정식을 구해야 한다. 점 $P$의 $x$ 좌표는 점 $A$의 $x$ 좌표값과 같고, 점 $P$의 $y$ 좌표는 직전 $OA$와 원이 만나는 점인 $B$의 $y$ 좌표값과 같다.

\* 참고로 요 문제에 나온 마리아 아녜시(Maria Agnesi)는 실제 인물로, 18세기 이탈리아에서 활동한 여성 수학자이다.

</div>

일단 $P$의 $x$ 좌표값부터 구해보자. $OQ$의 길이가 $2$로 고정 되어 있으므로, $\tan t = 2/x$임을 이용해서 $x$ 값을 유도하면,

$$
x = \frac{2}{\tan t} = 2 \cot t
$$

점 $P$의 $y$ 좌표는 직각삼각형의 닮은 성질로 유도 되는 아래 성질을 활용해야 한다.

$$
OA \cdot AB = (AQ)^2
$$

그리고 $y = 2 - AB \cdot \sin t$라는 성질도 사용해야 한다. 이를 위해 $AB$에 대한 식을 유도해보자.

$$
\begin{aligned}
AB
&= \frac{(AQ)^2}{OA} \\
&= \frac{x^2}{\sqrt{x^2 + 4}} \\
&= \frac{4 \cot^2 t}{\sqrt{4 \cot^2 t + 4}} \\
&= \frac{2 \cot^2 t}{\sqrt{\cot^2 t + 1}} \\
&= 2 \cot^2 \sin t
\end{aligned}
$$

이제 유도한 $AB$ 값을 대입하여 $y$ 좌표값을 유도하다.

$$
\begin{aligned}
y
&= 2- 2 \cot^2 t \sin^2 t \\
&= 2(1 - \cos^2 t) \\
&= 2 \sin^2 t
\end{aligned}
$$

종합하면, 점 $P$에 대한 매개방정식은

$$
P = \left( \frac{2}{\tan t}, \, 2 \sin^2 t \right)
$$

# Hypocycloid

![](/images/mathematics/calculus/hypocycloid.png){: style="height: 300px" }

큰 원 안에 작은 원을 굴릴 때 생기는 원의 자취에 대한 방정식이다. 큰 원의 반지름은 $a$, 작은 원의 반지름을 $b$라고 하자.

![](/images/mathematics/calculus/hypocycloid-2.png){: style="height: 300px" }

작은 원의 중심인 $C$의 좌표를 구해보자. $OC$의 길이가 $b - a$이므로 좌표값은 아래와 같다.

$$
C = (x, y) = \left( (a - b) \sin \theta, \, (a-b) \cos \theta \right)
$$

작은 원이 점 $A$에서 출발해서 움직인 각의 크기를 $\theta + \phi$라고 하자. 이때, $\phi > 0$가 되는데, 그 이유는 큰 원과 작은 원으로 둘의 반지름에 차이가 있기 때문이다. 그래서 작은 원이 큰 원보다 더 많은 각을 움직이게 되는데, 그 크기를 $\phi$라고 하자.

이때, 점 $P$의 길이는 작은 원의 중심 $C$의 좌표에서 생각하면 아래와 같다.

$$
\begin{aligned}
x &= (a-b) \cos \theta + b \cos \phi \\
y &= (a-b) \sin \theta - b \sin \phi
\end{aligned}
$$

이$\theta$와 $\phi$ 사이에 아래의 관계식이 성립한다.

$$
a \theta = b (\theta + \phi)
$$

이것은 작은 원이 큰 원을 휩쓸고 지나간 거리이다. 이 두 값은 동일해야 한다. 이것을 기반으로 $\phi$를 $\theta$에 대해 정리하면 아래와 같다.

$$
\phi = \frac{a - b}{b} \theta
$$

따라서 점 $P$에 대한 매개방정식을 아래와 같다.

$$
P = (x, y) = \left((a-b) \cos \theta + b \cos \left( \frac{a-b}{b} \, \theta \right)\,, (a-b) \sin \theta - b \sin \left( \frac{a-b}{b} \, \theta\right) \right)
$$

## Astroid

<p><a href="https://commons.wikimedia.org/wiki/File:Hypocycloid-4.svg#/media/File:Hypocycloid-4.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/0/05/Hypocycloid-4.svg" alt="k=4 → an astroid" height="340" width="340"></a><br>Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=1768524">Link</a></p>

이때, 큰 원과 작은 원의 반지름이 4배 차이가 난다면, 궤적이 Astroid가 된다. 이때의 매개방정식은 아래와 같다.

$$
\begin{aligned}
x &= a \cos^3 \theta \\
y &= a \sin^3 \theta
\end{aligned}
$$

왜 그런지는 식을 유도해보면 된다.

[$x$ 좌표]

$$
\begin{aligned}
x
&= 3 b \cos \theta + b \cos (3 \theta) \\
&= 3 b \cos \theta + b \left( \cos \theta \cos 2 \theta - \sin \theta \sin 2 \theta \right) \\
&= 3 b \cos \theta + b \left( \cos \theta \cos^2 \theta - 3 \cos \theta \sin^2 \theta - 2 \sin^2 \theta \cos \theta \right) \\
&= 3 b \cos \theta + b \cos^3 \theta - 3b \cos \theta \sin^2 \theta \\
&= b \cos^3 \theta + 3b \cos \theta (1 - \sin^2 \theta) \\
& = b \cos^3 \theta + 3b \cos \theta \cos^2 \theta \\
&= 4b \cos^3 \theta = a \cos^3 \theta
\end{aligned}
$$

[$y$ 좌표]

$$
\begin{aligned}
y
&= 3 b \sin \theta - b \sin (3 \theta) \\
&= 3 b \sin \theta - b \left( \sin \theta \cos 2 \theta + \cos \theta \sin 2 \theta \right) \\
&= 3 b \sin \theta - b \left( \sin \theta \cos 2 \theta + \cos \theta \sin 2 \theta \right) \\
&= 3 b \sin \theta - b \left( \sin \theta \cos^2 \theta - \sin^3 \theta + 2 \cos^2 \theta \sin \theta \right) \\
&= 3 b \sin \theta + b \sin^3 \theta - 3 b \sin \theta \cos^2 \theta \\
&= b \sin^3 \theta - 3 b \sin \theta (\cos^2 - 1) \theta \\
&= b \sin^3 \theta + 3 b \sin^3 \theta \\
&= 4b \sin^3 \theta = a \sin^3 \theta \\
\end{aligned}
$$

# Trochoids

![](https://mathworld.wolfram.com/images/eps-svg/Trochoid_1000.svg)

직선 위를 둥근 원이 둘러갈 때의 자취를 일반화한 것이 "Trochoids"이다. 원의 반지름을 $a$라고 하고, 원의 중심에서 뻗어나온 바큇살(spoke of wheel)의 길이를 $b$라고 하자. 이때, $a = b$인 상황이 많이 살펴본 "Cycloid"의 경우다. 이름은 그리스어로 "바퀴"를 뜻하는 *trochos*에서 유래했다고 한다.

매개방정식은 Cycloid의 것을 구했던 접근과 비슷하게 유도하면 된다.

$$
\begin{aligned}
x &= a \theta - b \sin \theta \\
y &= a - b \cos \theta
\end{aligned}
$$

# Complete Elliptic Integral

아래와 같은 타원의 둘레를 계산해보자.

$$
\frac{x^2}{a^2} + \frac{y^2}{b^2} = 1
$$

이를 매개변수로 표현하면 아래와 같다.

$$
\begin{aligned}
x &= a \cos t \\
y &= b \sin t
\end{aligned}
$$

매개변수 방정식의 arc length를 계산하는 식에 따라 적분을 수행하면 아래와 같다.

$$
P = 4 \cdot \int_0^{\pi/2} \sqrt{a^2 \sin^2 t + b^2 \cos^2 t} \, dt
$$

식을 $a$를 기준으로 정리하면...


$$
\begin{aligned}
P
&= 4 \cdot \int_0^{\pi/2} \sqrt{a^2 \sin^2 t + b^2 \cos^2 t} \, dt \\
&= 4 \cdot \int_0^{\pi/2} \sqrt{b^2  - (b^2 - a^2)\sin^2 t} \, dt \\
&= 4 \cdot \int_0^{\pi/2} b \sqrt{1  - \frac{(b^2 - a^2)}{b^2}\sin^2 t} \, dt \\
&= 4 \cdot \int_0^{\pi/2} b \sqrt{1  - k^2 \sin^2 t} \, dt \\
\end{aligned}
$$

이때, $k$는 이심률(eccentricity)로 $0 \le k^2 \le 1$의 값을 갖는다.

요 타원 적분이 완전(complete)인 이유는 $0$부터 $\pi/2$까지 적분하기 때문이다. 만약 적분 범위가 $\pi/2$가 아니라 변수라면, 불완전 타원 적분이라고 한다.

$$
F(\theta, k) = \int_0^{\theta} \sqrt{1 - k^2 \sin^2 t} \, dt
$$

## 풀이

타원의 둘레를 구하는 적분은 non-elementry integral임이 알려져 있다. 그래도 그 적분값을 못 구하는게 아닌데, 어떻게 구하는지 살펴보자.

$\sqrt{1 - x}$를 일반화된 이항정리를 적용해 전개하면 아래와 같다.

$$
\sqrt{1-x} = \sum_{n=0}^{\infty} \binom{1/2}{n} x^n = 1 - \frac{1}{2} x - \frac{1}{8} x^2 - \cdots
$$

이걸 $\sqrt{1 - k^2 \sin^2 t}$에 적용해보자.

$$
\sqrt{1-k^2 \sin^2 t} = 1 - \frac{1}{2} k^2 \sin^2 t - \frac{1}{8} k^4 \sin^4 t - \cdots
$$

이제 $[0, \pi/2]$ 범위에 대해 적분해보자.

$$
\begin{aligned}
&\int_0^{\pi/2} \sqrt{1-k^2 \sin^2 t} \, dt \\
&=\int_0^{\pi/2} 1 - \frac{1}{2} k^2 \sin^2 t - \frac{1}{8} k^4 \sin^4 t - \cdots \, dt \\
\end{aligned}
$$

이때, $n$이 짝수일 때, 적분 $\int_0^{\pi/2} \sin^n t \, dt$에 대해서 아래가 성립한다.

$$
\begin{aligned}
&\int_0^{\pi/2} \sin^n t \, dt \\
&= \frac{1 \cdot 3 \cdot 5 \cdots (n-1)}{2 \cdot 4 \cdot 6 \cdots n} \cdot \frac{\pi}{2} \\
&=\frac{(n-1)!!}{n!!} \cdot \frac{\pi}{2}
\end{aligned}
$$

적분식을 정리하면 아래와 같다.

$$
\begin{aligned}
&\int_0^{\pi/2} \sqrt{1-k^2 \sin^2 t} \, dt \\
&=\int_0^{\pi/2} 1 - \frac{1}{2} k^2 \sin^2 t - \frac{1}{8} k^4 \sin^4 t - \cdots \, dt \\
&= \frac{\pi}{2} - \frac{1}{2} \cdot \frac{1}{2} \cdot \frac{\pi}{2}\cdot k^2 - \frac{1}{2 \cdot 4} \cdot \frac{1 \cdot 3}{2 \cdot 4} \frac{\pi}{2} k^4 - \cdots \\
&= \frac{\pi}{2} \left( 1 - \left( \frac{1}{2} \right)^2 k^2 - \left( \frac{1 \cdot 3}{2 \cdot 4} \right)^2 \frac{k^4}{3} - \cdots \right)
\end{aligned}
$$

위의 적분은 수렴하는지는 $k^2$의 등비 급수와 비교했을 때, 위의 적분이 더 가파르게 감소하기 때문에, Complete Eliptical Integral이 수렴한다고 말할 수 있다.

# Limaçon Curve

<p><a href="https://commons.wikimedia.org/wiki/File:EpitrochoidIn1.gif#/media/File:EpitrochoidIn1.gif"><img src="https://upload.wikimedia.org/wikipedia/commons/2/28/EpitrochoidIn1.gif" alt="EpitrochoidIn1.gif" height="446" width="453"></a><br>By Sam Derbyshire - <a class="external free" href="https://en.wikipedia.org/wiki/Image:EpitrochoidIn1.gif">http://en.wikipedia.org/wiki/Image:EpitrochoidIn1.gif</a>, <a href="http://creativecommons.org/licenses/by-sa/3.0/" title="Creative Commons Attribution-Share Alike 3.0">CC BY-SA 3.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=3974765">Link</a></p>

리마송(Limacon)은 프랑스어로 달팽이🐌라는 뜻이다.

심장형 곡선(Cardioid)가 리마송 곡선의 특수한 케이스라고 함.

https://en.wikipedia.org/wiki/Lima%C3%A7on

# Lissajous Figures

# The nephroid of Freeth




