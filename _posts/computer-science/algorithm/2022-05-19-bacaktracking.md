---
title: "Backtracking"
layout: post
tags: ["algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

<div class="img-wrapper">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Backtracking-with-backjumping.svg/1024px-Backtracking-with-backjumping.svg.png" width="400px">
  <p><a href="https://commons.wikimedia.org/wiki/File:Backtracking-with-backjumping.svg">Wikimedia Commons, Tizio</a></p>
</div>

DFS 트리 탐색을 생각해보자. 만약 현재 방문한 노드에서 앞으로 더 탐색해도 원하는 최적해가 나오지 않을 것 같다면, 더 깊이 방문할 필요가 없다! 그래서 해당 노드에서 더 확장하지 않고, 다음 노드로 넘어가는 탐색 기법이 바로 \<Backtracking\>이다.

어떤 노드에서 멈추는 행위를 **\<pruning; 가지치기\>**라고 하는데, 이를 통해 탐색 할 Search Space를 줄여 탐색에 드는 비용을 줄일 수 있다!

보통 \<N-queen\> 문제가 \<Backtracking\> 기법을 쓰는 대표적인 문제다. 그러나 수업에서 안 다뤘으니 넘어가고, 대신 \<SAT\> 문제를 \<Backtracking\>으로 풀어보자.

<hr/>

## SAT with Backtracking

6개의 clause와 4개의 variable이 있는 아래의 SAT 문제를 살펴보자.

$$
\phi(w, x, y, z) = (w \lor x \lor y \lor z) (w \lor \bar{x}) (x \lor \bar{y}) (y \lor \bar{z}) (z \lor \bar{w}) (\bar{w} \lor \bar{z})
$$

위 formula의 $(w \lor \bar{x})$ clause를 통해 $w = 0, x = 1$이 존재하는 assignment는 모두 pruning 할 수 있다. 즉, not-satisfying partial assignment를 통해 탐색해야 하는 Search Space를 줄여나갈 수 있다.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/backtracking-1.png" | relative_url }}" width="100%">
</div>

그러나 다른 partial assignment인 $w = 0, x = 0$로 탐색을 계속해도 satisfying assignment는 찾을 수 없다.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/backtracking-2.png" | relative_url }}" width="100%">
</div>

\<SAT\> 문제를 Backtracking으로 해결하려 할 때 생각할 부분이 하나 더 있다. 우리는 <span style="color: red">다음 스텝에서 어떤 variable을 체크해 partial assn을 확장할 것인가?</span> 쉽지 않은 문제이지만, Greedy 하게 생각해보면 아래와 같은 규칙이 가능하다.

<div class="statement" markdown="1">

"Choose the subproblem that contains <span style="color: red">the smallest clause</span>, and then branch on a variable in that clause."

</div>

SAT 문제에서 가장 작은 clause라 하면 $(z)$와 같은 singleton clause이다. singleton clause의 경우, assn T/F 중 하나는 무조건 non-satisfying이 되기 때문이다.

이런 \<Backtracking\> 알고리즘을 정리하면 아래와 같다.

<div class="statement" markdown="1">

Start with some problem $P_0$

Let $S = \\{ P_0 \\}$, the set of active subproblems.

while $S$ is non-empty:<br/>
&nbsp;&nbsp;**<u>choose</u>** a subproblem $P \in S$ and remove it from $S$.<br/>
&nbsp;&nbsp;**<u>expand</u>** it into smaller subproblems $P_1, P_2, \dots, P_k$<br/>
&nbsp;&nbsp;for each $P_i$:<br/>
&nbsp;&nbsp;&nbsp;&nbsp;if $\text{test}(P_i)$ succeed: halt and report it as a solution.<br/>
&nbsp;&nbsp;&nbsp;&nbsp;if $\text{test}(P_i)$ succeed: halt and report it as a solution.<br/>
&nbsp;&nbsp;&nbsp;&nbsp;otherwise: add $P_i$ to $S$.

Announce that there is no solution.

</div>

<hr/>

\<Backtracking\>의 핵심은 가망이 없는 subproblem을 지워버리는 **검색** 방식이다. 이를 통해 Search Space를 줄여 Exponential Time이 걸릴 탐색을 획기적으로 줄일 수 있다! 그러나 문제의 어떤 instance가 나오느냐에 따라, 또는 partial solution을 어떻게 확장할 건지에 따라 계산 속도가 달라지기 때문에 input $N$이 너무 크다면, 사용하기 적절하지 않을 수 있다.

다음 포스트에선 \<Backtracking\> 알고리즘의 파생격인 \<Branch-and-Bound\> 알고리즘을 살펴본다 🙏

👉 [Branch and Bound]({{"/2022/05/20/branch-and-bound.html" | relative_url}})
