---
title: "Segmented Least Squares"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

사실 이번에 다룰 \<Segmented Least Squares\>는 정규 수업의 수업 PPT에 한 페이지 등장한게 전부인 알고리즘 입니다. 교재인 『Algorithms(Dasgupta)』에는 기술되어 있지 않은 주제입니다. 🤪

<hr/>

\<Linear Regression Problem\>의 가장 기본적인 접근인 \<Least Square Method\>는 잔차제곱합(residual square sum)이 최소가 되도록 regression 함수를 유도한다.

<div class="img-wrapper">
  <img src="https://kartikkukreja.files.wordpress.com/2013/10/bestfit.png" width="360px">
  <p>Image from <a href="https://kartikkukreja.wordpress.com/2013/10/21/segmented-least-squares-problem/)">kartikkukreja' article</a></p>
</div>

\<LS Method\>에 대해 더 궁금하다면, "[Introduction to Linear Regression]({{"/2021/06/06/introduction-to-linear-regression" | relative_url}})" 아티클을 읽을 것을 추천한다 😉

그.러.나. 때로는 주어진 데이터에 대한 regression 식을 하나의 선형 모델로 표현하기 어려운 경우가 많다. 이 경우는 차수(degree)를 높여 곡선으로 모델을 fitting 하거나 구간을 나누어 각 구간 별로 regression fitting을 하는 방법이 있다.

이번에 다룰 주제인 \<Segmented Least Squares\> 방법은 후자의 방식으로 도메인을 분할해 얻은 segment에 대해 regression fitting을 하는 방식이다.

<div class="img-wrapper">
  <img src="https://kartikkukreja.files.wordpress.com/2013/10/segmented.png?w=300&h=231" width="360px">
  <p>Image from <a href="https://kartikkukreja.wordpress.com/2013/10/21/segmented-least-squares-problem/)">kartikkukreja' article</a></p>
</div>

위의 그림은 도메인을 3개의 segment로 분할해 regression fitting 한 결과이다. 일반적으로 도메인을 분할해 fitting 하는 경우, 도메인을 얼마나 나눌지 그리고 어디에서 나눌지 미리 정하고 fitting을 진행한다. 즉, segment selection을 진행해야 한다는 말이다. 그러나 \<Segmented Least Squares\> 기법을 사용하면 얼마나 나눌지 어디에서 나눌지를 알아서 결정해 fitting 한 결과를 보여준다!! 😲

<hr/>

\<Segmented Least Squares\> 문제를 기술하면 아래와 같다.

<div class="notice" markdown="1">

For the set of $n$ points in the plane $(x_1, y_1), ..., (x_n, y_n)$ with $x_1 \le \cdots \le x_n$.

Let $S(a, b)$ the subset of points where $S(a, b) = \\{ (x_i, y_i) \mid a \le i < b \\}$, and then let $E(S(a, b))$ be the residual sum of the fitting for $S(a, b)$.

Then, the total residual error is

$$
E = \sum_i E(S(t_i, t_{i+1})) \quad \text{where} \quad [t_i, t_{i+1}) \in [x_1, x_n]
$$

Find a poly-line that minimizes

$$
f(x) = E + c \cdot L
$$

where $L$ is the number of segments, and $c$ is a positive constant.

</div>

오차합 $E$를 최소화 하려면 segment를 전체 데이터의 수인 $n$ 만큼 나누어 fitting을 진행하면 된다. 그러나 이런 모델은 generalization 측면에서 결코 좋은 모델이 아니다. 반대로 데이터를 $n$개로 나누지 않고 $n$개 점 중에 combination을 구하려고 한다면 그것 역시 조합폭발(combinatorial explosion)에 직면하고 만다.

결국 \<Segmented Least Squares\> 문제는 tradeoff function $f(x)$를 통해 accuracy(goodness of fit)과 parsimony(number of lines) 중 적절한 균형을 찾는 최적화 문제라고 할 수 있다.

<br/>

\<Segmented Least Squares\> 문제는 DP를 사용하면 아주 쉽게 해결할 수 있다!

$DP[j]$는 points $p_1, ..., p_j$까지 사용했을 때의 최적해의 cost를 말한다. $e(i, j)$는 "minimum squared from $p_i$ to $p_j$"를 의미하며 \<OLS; Ordinary Least Square\>로 유도되는 값이다.

알고리즘의 아이디어는 <span class="half_HL">"추가되는 점 $p_j$는 어떤 점 $p_i$로부터 시작하는 segmented fitting에 속할 것이다"</span>에서 출발한다.  만약 우리가 $DP[i-1]$에 대한 값을 알고 있다면, 점 $p_j$를 추가해 $\\{ p_i, ..., p_j\\}$로 fitting 한 결과와 $p_{i-1}$에서의 optimal fitting 결과를 종합해 최적해를 유도할 수 있다!

이를 기술하면 아래와 같다.

$$
DP[j] = \min_{1 \le i \le j} \left\{ e(i, j) + C + DP[i-1] \right\}
$$

최적해의 결과를 복원할 때는 $DP[j]$를 구하는 과정에서 사용된 index $i$를 별도로 저장한 후에 back-tracking 하여 복원하면 된다!

<br/>

시간복잡도를 유도하면,

- 전체 과정에서 $O(n^2)$개 만큼의 $e(i, j)$를 구해야 함.
- $e(i, j)$ 하나의 값을 계산할 때 $O(n)$ 만큼의 시간이 걸림.

따라서 시간복잡도는 $O(n^3)$이다. naive한 접근에서 exponential time이 걸렸던 걸 생각하면 정말 획기적으로 줄어든 셈이다! 🤩

<hr/>

\<Segmented Least Squares\>는 정규 수업을 들을 때 과제로 한번 구현해봤던 기억이 있는데, 구현이 그렇게 어렵지는 않았던 걸로 기억한다.

\<Segmented Least Squares\> 알고리즘을 공부하면서 ["통계적 데이터마이닝(IMEN472)"]({{"/category/statistical-data-mining" | relative_url}})에서 들었던 [\<Regression Spline\>](/computer_science/2021/04/18/regression-spline)이 대해 떠올랐다. 이 알고리즘 역시 도메인을 분할해 regression fitting 하는 알고리즘이다.

다만 \<Regression Spline\>의 경우 곡률(curverture)까지 고려해 fitting을 진행하며, 구간을 얼마나 나눌지 선택하는 knot selection을 cross validation 과정을 통해 진행한다는 점이 \<Segmented Least Squares\> 알고리즘과 다르다! 🤩


<hr/>

### reference

- [bab2min님의 아티클](https://bab2min.tistory.com/629)
- [kartikkukreja' article](https://kartikkukreja.wordpress.com/2013/10/21/segmented-least-squares-problem/)
- [cs.washington.edu](https://homes.cs.washington.edu/~jrl/teaching/cse312au10/lec25.pdf)