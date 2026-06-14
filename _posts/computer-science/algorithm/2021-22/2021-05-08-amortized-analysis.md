---
title: "Amortized Analysis"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :) 전체 포스트는 [Algorithm](/categories/algorithm) 포스트에서 확인하실 수 있습니다.

\<Amortized Analysis\>에 대해 살펴보기 전에 간단한 Introduction인 \<Array Doubling\>에 대해 살펴보고 오길 바란다. 'Zedd0202'님의 포스트를 추천한다!

👉 [Array Doubling](https://zeddios.tistory.com/62)

<hr/>

<span class="statement-title">Definition.</span><br>

자료구조에서 하나의 Operation을 수행하는 시간을 "전체 Operation을 수행할 때의 평균 시간"으로 바꾸어 표현하는 분석 방식을 말함. <span class="half_HL">a sequence of operations 관점에서 최악의 경우를 분석한다.</span> 다르게 표현하면, <span class="half_HL">연속된 연산에서 가-끔 일어날 수 있는 비싼 비용의 연산을 분산시켜서 연산의 '평균 비용'을 계산</span>하는 방식이다.

\<Amortized Analysis\>는 흔히 "**분할-상환 방식**"으로도 불린다.

<br/>

우리가 어떤 연산을 수행하는 상황을 상상해보자. 그 연산이 '평소'에는 비용이 $1$만 든다고 하자. 그러나 어떤 아주아주 드문 경우에 이 연산이 $N$번 수행되고, 그때 비용이 $N$만큼 든다고 하자.

만약 \<Asymptotic Analysis\>로 접근한다면, 최악의 경우를 고려하기 때문에 위의 연산은 $O(N)$의 비용이 든다고 분석하게 된다.

\<Asymptotic Analysis\>에서는 최악의 경우 때문에 '평소'의 비용과 괴리가 생겼다. \<Amortized Analysis\>는 이런 경우를 해결하기 위해 사용한다! 😁

위의 경우에서 \<Amortized Analysis\>를 수행하면, 그 결과는 "Amortized $O(1)$"이다! (이때, 그냥 $O(1)$라고 하면 안 되고, 꼭 "**Amoritzed**"를 붙여줘야 한다.)

<br/>

"Stack" 자료구조의 `push`를 예시로 \<Amortized Analysis\>를 수행해보자. 우리는 \<amortized cost\>를 아래와 같이 정의한다.

<div class="statement" markdown="1" align="center">

amortized cost = actual cost + accounting cost

</div>

이때, \<amoritzed cost\>가 우리가 구할 대상인 "평균 비용"을 의미한다.

\<**actual cost**\>는 우리가 Stack에서 `push` 연산을 수행할 때의 실제로 드는 비용을 의미한다. \<**accounting cost**\>는 "적립 비용"이라고 한다. 어떤 연산을 수행할 때, 가-끔 일어나는 비싼 연산을 무리없이 수행하기 위해, 비용을 계좌에 저금해두는 용도의 비용이라고 생각하면 된다. (이렇게 비용을 미리 저금하면, 나중에 가-끔 일어나는 비싼 연산이 발생했을 때, 계좌(account)에 저축된 비용을 사용하면 된다!🤩)

<br/>

다시 Stack의 예시로 돌아와보자. Stack은 평소에는 `push`, `pop` 수행할 때, $1$의 비용이 든다. 그런데 만약 Stack의 용량(capacity)가 꽉 차게 되면, \<Array Doubling\>이 발생하게 된다!! 😲

이 Array Doubling을 수행하기 위해 "transferring cost"가 발생한다. 만약 원소 하나를 옮기는 데 드는 비용이 $t$라고 했을 때, $n$개 원소를 옮길 때에는 $n \times t$의 비용이 든다. 만약 이전의 "transferring cost"까지 모두 고려한다면, 전체 transferring cost는 $\le 2 \cdot n t$이다.

꽉 찬 스택을 더 넓은 스택으로 옮겨보자. 만약 현재 스택에 $n$개 원소가 존재한다면, 이것으로 새로운 스택으로 옮기는 데에 $n \times t$의 비용이 든다. 그리고 이 Array Doubling을 트리거한 원소 역시 넣어야 하기 때문에 또 $1$이라는 비용이 든다. 따라서, Doubling 상황에서의 actuacl cost는 $1 + nt$가 된다.

우리는 윗윗 문단에서 transferring cost의 총 비용이 $2tn$ 쯤 된다는 것을 알았다. 만약 우리가 총 비용을 알고 있다면, 이 비용에 맞춰 계좌(account)에 꾸준히 비용을 저금해야 한다. 만약 계좌의 잔고가 0이라면, 우리는 `push`, `pop` 어떤 연산도 수행할 수 없다.

그래서 <span class="half_HL">총 $n$번의 `push`를 수행한다고 했을 때, $\frac{2tn}{n} = 2t$ 만큼의 비용을 미리 저금해둬야 한다.</span> 그래야 평소에 일어나는 비용 $1$의 연산과 가-끔 일어나는 비용 $1+nt$의 연산을 모두 버틸 수 있다.

$2t$라는 비용은 Doubling 발생 여부와 상관없이 계속 지불하여 계좌에 쌓이게 된다. 만약 Doudling이 실제로 일어난다면, accounting cost는 $- nt + 2t$가 된다.

상황을 종합해서 "amortized cost"로 표현해보자.

"amortized cost  = actual cost + accounting cost"

- Doubling X (평소): $1 + 2t$
- Doubling O : $(1+nt) + (-nt + 2t) = 1 + 2t$

그래서! Array Doubling을 수행하는 Stack에서의 `push` 연산은 "**amortized** $O(1+2t)$"의 비용, 즉! "**amortized** $O(1)$"을 갖는다!! Asymptotic Analysis에서 최악의 시간을 분석한 $O(n)$ 비용보다는 좀더 유용할 것 같지 않은가?? 😁

<br/>

위와 같은 방식은 \<Accounting Method\>라고 한다. \<Amortized Analysis\>를 수행하는 방식으로는 3가지가 있는데, 아래와 같다.

- Accounting Method
- Aggregate Method
- Potential Method

<br/>

\<Amortized Analysis\>는 Advanced Data Structure를 다룰 때, 꼭 등장하는 테크닉이기 때문에 반드시 숙지해야 하는 테크닉이다!! 😎

<br/>

<hr/>

#### 참고자료

- ['Zedd0202'님의 포스트 - Amortized Analysis](https://zeddios.tistory.com/60)
- ['Zedd0202'님의 포스트 - Array Doubling](https://zeddios.tistory.com/62)
- ['blackblight'님의 포스트](https://blog.naver.com/blackblight/40098147530)
- [cs.cornell.edu](http://www.cs.cornell.edu/courses/cs3110/2011sp/Lectures/lec20-amortized/amortized.htm)
  - Amortized Analysis의 3가지 방식이 잘 설명 되어 있다!