---
title: "Matrix Multiplication: Strassen Algorithm"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

$n \times n$ 행렬 $X$와 행렬 $Y$를 곱하는 연산을 그냥 계산한다면, $O(n^3)$의 비용이 든다. 그런데 분할정복의 기법을 적절히 활용한다면 행렬 곱셈을 좀더 싼 비용으로 계산할 수 있다!! 이것을 \<**슈트라센(Strassen) 알고리즘**\>이라고 한다.

먼저 행렬 $X$는 4등분 하여 $A$, $B$, $C$, $D$로 이름을 붙인다. 마찬가지로 행렬 $Y$도 4등분 하여 $E$, $F$, $G$, $H$로 이름 붙인다.

$$
X = \begin{bmatrix}
 A & B \\
 C & D
\end{bmatrix}
\qquad
Y = \begin{bmatrix}
 E & F \\
 G & H
\end{bmatrix}
$$

그 후에 $XY$ 곱셈을 진행하면 아래와 같을 것이다.

$$
XY = \begin{bmatrix}
  AE + BG & AF + BH \\
  CE + DG & CF + DH
\end{bmatrix}
$$

사실 $n/2 \times n/2$ 크기의 두 행렬의 곱셈을 8번 하기 때문에 여전히 시간 복잡도는 $O(n^3)$이다. 그러나 여기에서 약간의 트릭을 쓰면, 1/4 행렬 곱셈을 7번만 수행해서 행렬 곱셈을 수행할 수 있다!! 🙌

아래와 같이 $P_1, ..., P_7$ 7개의 1/4 행렬 곱셈을 정의한다. $P_i$ 정의 자체를 이해할 필요는 없으니 정의한 슬쩍 보고 넘어가면 된다.

$$
\begin{aligned}
P_1 = A(F - H) &\qquad P_5 = (A + D)(E + H)\\
P_2 = (A + B)H &\qquad P_6 = (B - D)(G + H)\\
P_3 = (C + D)E &\qquad P_7 = (A - C)(E + F)\\
P_4 = D(G - E)
\end{aligned}
$$

우리는 $P_i$를 조합해 $XY$를 아래와 같이 유도할 수 있다.

$$
XY = \begin{bmatrix}
  P_5 + P_4 - P_2 + P_6 & P_1 + P_2 \\
  P_3 + P_4 & P_1 + P_5 - P_3 - P_7
\end{bmatrix}
$$

와우! 이렇게 하면 총 7번의 곱셈과 8번의 $O(n^2)$의 덧셈 만으로 행렬 곱셈을 수행할 수 있다! 이것을 \<슈트라센 알고리즘\>이라고 한다 😎

점화식을 기술하면 아래와 같은데

$$
T(n) \le 7 \cdot T(n/2) + O(n^2)
$$

\<Master Theorem\>을 사용해서 $T(n)$을 유도하면

$$
T(n) = O(n^{\log_2 7}) \approx O(n^{2.81})
$$

로 원래의 $O(n^3)$보다 개선된 성능을 얻을 수 있다!! 😁 어떻게 보면 분할정복 파트의 초반에 다뤘던 ["두 정수의 곱셈 알고리즘"]({{"/2021/02/26/multiplication-algorithm" | relative_url}})과도 비슷한 맥락이었다.

<hr/>

행렬곱셈에 대한 슈트라센 알고리즘은 1969년 제시 되었는데, 그 이후에 더 개선된 알고리즘들이 제시되었다고 한다. 더 자세한 내용은 Wikipedia의 해당 아티클을 읽어보자. 👀

👉 [Wikipedia - Strassen Algorithm](https://en.wikipedia.org/wiki/Strassen_algorithm#History)

<br/>

행렬 곱셈과 관련해서는 몇가지 문제들이 더 있다. 행렬 $A^m$의 값을 빠르게 찾기 위한 문제도 있고([백준 10830번 - 행렬 제곱](https://www.acmicpc.net/problem/10830)), 행렬 곱셈 $A_1 \times A_2 \times \cdots \times A_n$에서의 최소 연산 수를 갖는 행렬 곱셈 순서를 찾는 [Chain Matrix Multiplication]({{"/2021/05/02/chain-matrix-multiplication" | relative_url}}) 문제도 있다.
