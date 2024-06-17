---
title: "Parametric Equations: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
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

# Troichoids

