---
title: "Bonferroni's Inequality"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
excerpt: ""
---

2025년 마지막 학기 수업인 "확률개론(MATH431)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Introduction to Probability Theory](/categories/introduction-probability-theory)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# 들어가며

# Inclusive-Exclusive Rule



# Boole's Inequality

부울의 부등식은 Countable 가능한 이벤트 집합에서 아래의 부등식이 성립한다는 명제이다. (부울 대수의 그 부울 선생님이다.)

$$
P\left( \bigcup A_i \right) \le \sum_{i=1}^{\infty} P(A_i)
$$

직관적으로 생각하면, $A_i$와 $A_j$ 사이에 교집합 $A_i \cap A_j$이 존재하면, $P(A_i \cup A_j) \le P(A_i) + P(A_j)$가 성립하기 때문이다.

# Bonferroni's Inequality

부울의 부등식을 일반화 한 것이 본페로니 부등식이다.

For $n$ number of events space.

Let $S_1 := \sum P(A_i)$, $S_2 := \sum P(A_i \cap A_j)$, $S_3 := \sum P(A_i \cap A_j \cap A_k)$, ..., $S_k := \sum P(A_{i_1} \cap \cdots \cap A_{i_k})$

Then,

$$
\begin{aligned}
P \left( \bigcup A_i \right) &\le S_1 \\
P \left( \bigcup A_i \right) &\ge S_1 - S_2 \\
P \left( \bigcup A_i \right) &\le S_1 - S_2 + S_3 \\
P \left( \bigcup A_i \right) &\ge S_1 - S_2 + S_3 - S_4 \\
&\dots
\end{aligned}
$$

이렇게 이어지는 부등식을 말한다. 부울의 부등식은 본페르니 부등식에서 제일 첫번째 부등식에 해당한다. 그리고 이 부등식은 포함-배제의 원리에서 도출된 것이다.

이 부등식은 현실에서 이벤트 집합의 갯수 $n$이 너무 많고, $S_1$과 $S_2$만 구할 수 있을 때, $P \left( \bigcup A_i \right)$의 확률을 상한/하한을 통해 대략적으로 유추할 수 있도록 도와준다. 어떻게 보면 샌드위치 정리와 같은 셈. 만약 제대로된 확률을 구하려고 했다면 포함-배제 원리에 따라 아주 많은 에빈트 교집합의 확률을 모두 구했어야 할 것이다. 일종의 확률에 대한 근사 테크닉!!

# References

- [Wikipedia: Bonferroni](https://en.wikipedia.org/wiki/Boole%27s_inequality#Bonferroni_inequalities)