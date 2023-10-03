---
title: "Cyclic group and its subgroup"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

### Cyclic group and its subgroup

<div class="notice" markdown="1">

Every subgroup of cyclic group is also cyclic.

</div>

Let $G = \left<a\right>$ be a cyclic group, and $H \leq G$.

To show $H=\left<b\right>$ for some $b = a^n \in H$, for some $n \in \mathbb{Z}$. w.l.o.g. we assume $n > 0$.

Then, we will check that $H=\\{(a^n)^k \mid k \in \mathbb{Z} \\}$.

Let's think of a set $S$ s.t. $S := \\{ n \in \mathbb{N} \mid a^n \in H\\} \subset \mathbb{N}$, and $S \neq \emptyset$ ($\because$ $a^0=e \in H$).

Because $S \subset \mathbb{N}$ we can pick $n$ as the smallest elt from $S$ by Well-ordered Set Principle.

And it is trivial that $\left<{a^n}\right> \leq H$.

<br>

Every $h\in H$ it is $h \in G$, so $h = a^m$ for some $m \in \mathbb{Z}$.

By division algorithm, $m = nq + r$ ($0 \leq r < n$). Then,

<div>
$$a^m = a^{nq+r} = {(a^n)^q}a^r$$
</div>

Because $h=a^m \in H$ and $a^n \in H$, $a^r$ also be $a^r \in H$.

But, we set $n$ as the smallest elt in $H$, $r$ should be zero.

$\therefore$ $a^m = a^{nq} = (a^n)^q$, and we finally checked that every elts in $H$ are expressed by some elt $a^n \in H$. This means $H \leq \left<{a^n}\right>$.

$\left<{a^n}\right> \leq H$ and $H \leq \left<{a^n}\right>$, so $H = \left<{a^n}\right>$. $\blacksquare$