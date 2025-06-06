---
title: "미시경제학 헷갈리는 것 메모"
toc: true
toc_sticky: true
disable: true
---

졸업을 위해 마지막 학기에 "미시경제학" 수업을 듣게 되었습니다.
경제학원론 수업을 재밌게 들어서 경제 쪽이랑 궁합이 좋은 줄 알고 신청 했는데, 웬걸... 이 과목은 사실상 수학과 과목 이었습니다.. ㅋㅋ 그래도 수학과 복수전공도 하고 있으니, 이 수업도 힘내서 잘 들어봅시다!
전체 포스트는 "[미시경제학](/categories/micro-economics)" 카테고리에서 확인하실 수 있습니다.
{: .notice }

# 성질

각 성질이 어떤 의미인지 바로 떠올릴 수 있도록 훈련

- 선호에서 정의하는 성질
  - completeness
  - reflective
  - symmetric
  - transitive
  - 여기에서 한번 헷갈리던게, reflective랑 symmetric인데,
    - reflective는 모든 $x \in X$에 대해서 $x \succcurlyeq x$를 만족한다는 것
      - 수학적으로는 모든 재화는 거울처럼 자기 자신과 그 관계를 맺을 수 있다는 것임. $x R x$
    - symmetric은 모든 무차별한 $x \sim y$에 대해서, 양방향으로 성립한다는 것 $y \sim x$
- 선택 함수에서 정의하는 성질
  - rationalizable
    - completeness
    - transitivity
    - consistency
  - property $\alpha$
- 복권 선호에서 정의하는 성질
  - continuity
  - independence
  - monotonicity
- 번들 선호에서 정의하는 성질
  - Monotone
  - Convexity
    - 사람들은 재화를 섞는 걸 더 좋아한다.
  - Differentiability

# 종류

- 번들 선호의 종류
  - Indifference
  - Constant tradeoff
  - Only preference
  - stepwise preference
  - complementary goods
  - Ideal bundle
  - Lexicographic preference
  - 번외편 (youtube 강좌에서만 나옴)
    - Quas-linear preference
    - Cobb-Douglas preference
    - Kinky preference

# MRS

- $\succcurlyeq$ is monotone
  - MRS < 0 everywhere
- $\succcurlyeq$ is convex
  - decreasing MRS

# (번외) 예산 집합의 솔루션

Youtube 강좌에서만 나왔습니다. [[youtube](https://youtu.be/kEgg_pAhyLs?si=8tsx1Oyv3c1ih7WJ&t=1471)]

- Interior Solution
- Corner Solution


# (번외) 수요의 소득탄력성

아주 작은 변화에 대해 수요량이 얼마나 민감하게 반응하는지에 대한 수치.

"**탄력성**"이라는 지표는 항상 아래와 같이 계산 됩니다.

$$
\text{Elasticity} = \frac{\text{\% change in A}}{\text{\% change in B}}
$$

그래서 수요의 소득 탄력성은 아래와 같이 표현 됩니다.

$$
\mathcal{E} = \frac{\text{\% change in Demand}}{\text{\% change in Income}}
$$

그리고 이걸 변수와 함께 표현하면,

$$
\mathcal{E} = \frac{\Delta x / x}{\Delta w / w}
= \frac{\Delta x}{\Delta w} \cdot \frac{w}{x}
$$

수요의 소득 탄력성에 따라 재화를 구분할 수 있습니다.

- $\mathcal{E}_w > 0$
  - 정상재 입니다.
- $\mathcal{E}_w < 0$
  - 열등재 입니다.

그리고 정상재는 탄력성의 값 범위에 따라 2가지로 또 분류 하는데,

- 필수재
  - $0 < \mathcal{E}_w < 1$
  - 예를 들어, 음식과 전기는 소득이 늘어나면 수요가 늘긴 하지만 급격히 오르는 것은 아닙니다.
- 사치재
  - $\mathcal{E}_w > 1$
