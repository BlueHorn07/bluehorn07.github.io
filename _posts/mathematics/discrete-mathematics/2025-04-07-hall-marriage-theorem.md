---
title: "Hall's Marriage Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Discrete Mathematics"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 시험 과목으로 "이산수학"을 선택하였습니다. 학부 2학년 때 컴공과 수업으로 들었던 기억이 있는데... 감각만을 믿을 수는 없으니! 다시 도전 해봅시다! 전체 포스트는 "[Discrete Mathematics](/categories/discrete-mathemtics)"에서 확인할 수 있습니다.
{: .notice }

# Set-up

어떤 부족에 남자 N명과 여자 M명이 존재한다고 합시다.

각 남자는 결혼하고 싶은 여자를 지목합니다. 한 명의 여자를 지목해도 되고, 여러 명의 여자를 지목 해도 됩니다. (원한다면, 아무도 지목하지 않아도 괜찮지만, 그런 남자 부족민은 우리의 관심 대상이 아닙니다.)

이제, 족장은 이런 생각이 들었습니다. "모든 남자 부족민이 만족하는 결혼이 이뤄질 수 있을까? 이뤄질 수 있도록 하는 조건은 무엇일까?"

홀의 결혼 정리는 이런 상황을 일반화하여 조건을 얘기해줍니다.

# 매칭은 언제 존재할 수 있는가?

일단 가장 간단한 케이스 몇 개를 살펴봅시다.

![](/images/mathematics/discrete-mathematics/hall-condition.png){: .align-center}

(a) 만약 남자는 단 1명만 있고, 여자는 충분히 많다면, 남자가 한 명 이상의 여자를 지목하기만 해도 "매칭"이 성립 합니다.

(b) 만약 남자가 3명이고, 여자도 3명인데, 모든 남자는 어떤 여자와 결혼해도 괜찮은 상황이라면, "매칭"이 성립 합니다.

(c) 만약 남자가 3명이고, 여자는 2명이라면, 적어도 한 명의 남자는 결혼 하지 못 합니다.

(c) 상황에서 매칭에 대한 중요한 힌트를 얻을 수 있습니다. 만약 매칭될 수 있는 여자 사람 수가 적다면 매칭은 확인할 필요도 없이 불가능 합니다! 이것을 수학적으로 적어보면,

<div class="theorem" markdown="1">

For given boys set $B$, and girls set $G$. Let's say $I(B)$ as image of boys preference.

If $\vert B \vert > \vert I(B) \vert$, then there's no matching.

</div>

# Hall's Marriage Theorem

위의 아이디어를 확장한 것이 이 포스트에서 다루는 정리 입니다. 정리를 먼저 적어보면,


<div class="theorem" markdown="1">

For given boys set $B$, and girls set $G$.

For every subset of $S \subseteq B$, let's say $I(S)$ as image of preference.

then, if $\vert S \vert > \vert I(S) \vert$, then there's no matching.

</div>

즉, 남자 집합에 대한 모든 부분 집합 $S$에 대해서 매칭이 존재하기 위한 최소 조건을 확인 합니다.
만약 모든 부분 집합이 이 최소 조건을 만족한다면, 전체 집합에서도 매칭이 존재합니다!


남자/여자 같은 표현을 빼고, 다시 기술하면 아래와 같습니다.

<div class="theorem" markdown="1">

Let $G = (V, E)$ be a bipartite graph with $V = L \cup R$. Then $G$ has an $L$-matching if and only if

For ever subset $S \subseteq L$, $\vert S \vert \le \vert N_G(S) \vert$.

($N_G(S)$ is a neighborhood of $S$.)

</div>

그리고 "For ever subset $S \subseteq L$, $\vert S \vert \le \vert N_G(S) \vert$." 이 조건을 "**Hall's Condition**"이라고 합니다.

<!-- # Proof: Sufficient Direction

$L$-matching이 존재할 때, Hall's Condition이 만족하는지를 확인 합시다.

이 방향은 간단한데요! $L$-matching이 존재하기 때문에, 어떤 subset $S \subseteq L$을 잡아도 $S$-matching이 존재합니다. 이때, $S$-matching은 $L$-matching의 부분 집합 입니다.

# Proof: Necessary Condition

Hall's Condition이 성립할 때, $L$-matching이 성립하도록 하는지를 증명해봅시다.

TODO: 지금은 증명이 머리에 안 들어오는 군요... ㅠㅠ 아래 Reference의  -->

# References

- [Graphs: Proof of Hall’s Theorem](https://www.cs.dartmouth.edu/~deepc/LecNotes/cs30/lec25supp.pdf)