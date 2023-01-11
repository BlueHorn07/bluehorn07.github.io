---
title: "Monti Hall Problem"
layout: post
tags: ["probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<hr/>

# Monti Hall Problem

<div class="statement" markdown="1">

<div class="img-wrapper">
<img src= "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Monty_open_door.svg/440px-Monty_open_door.svg.png" style="width:200px;">
</div>

당신이 한 게임 쇼에 참여하여 세 문들 중 하나를 고를 기회를 가졌다고 생각해보자. 한 문 뒤에는 자동차가 있으며, 다른 두 문 뒤에는 염소가 있다. 당신은 1번 문을 고르고, 문 뒤에 무엇이 있는지 아는 사회자는 염소가 있는 3번 문을 연다. 그는 당신에게 "2번 문을 고르고 싶습니까?"라고 묻는다. 당신의 선택을 바꾸는 것은 이득이 되는가?

</div>


교수님께서 수업시간에 재미삼아 ~~이걸 재미로?~~ 내신 문제다. 처음에는 이 문제에 대해서 "선택을 바꾸지 않는다."는 답을 냈는데, 틀렸다 ㅠㅠ 아마 "선택을 바꾸면 더 아쉬워 할 것 같음."이라는 심리적 요인이 작용한 것 같다.

문제의 정답은 "선택을 바꿔야 한다."이다. 좀더 정확하게는 

- P(선택을 바꾸지 않고, Win) = 1/3
- P(선택을 바꾸고, Win) = 2/3

이다.

이 문제에 대한 여러가지 설명이 있지만, 내가 이해한 것들만 짧게 정리해서 소개하겠다.

<hr/>

## 해설 1

[1st pick = goat]

먼저 내가 **염소**를 선택한 상황부터 따져보자.

내가 염소를 골랐다면, 사회자는 남은 2개의 문 중에서 자동차가 있는 문 대신에 염소가 있는 문을 보여줘야 한다.

그럼 남은 문에는 반드시 자동차가 있다. 따라서 내가 염소를 선택했다면, 나의 선택을 바꿔야 한다.

<br/>

[1st pick = car]

반대로 내가 **자동차**를 선택한 상황을 따져보자. 

사회자는 남은 2개의 문 중 아무 문을 열어서 염소를 보여준다. 남은 문에는 당연히 염소가 있다. 만약 여기서 내가 선택을 바꾸면 나는 반드시 염소를 얻는다. 따라서 나의 선택을 바꾸지 않아야 한다.

<br/>

자 그럼, 종합해보자.

- 내가 염소를 선택할 확률은 2/3이다. 이 경우 나는 선택을 바꿔야 한다.
- 내가 자동차를 선택할 확률은 1/3이다. 이 경우 나는 선택을 유지해야 한다.

<br/>

문제는 선택을 바꾸는게 이득인지 손해인지를 묻는다.

내가 무조건 바꾼다는 전략을 취한다고 해보자. 그렇다면 처음에 2/3 확률로 염소를 선택하고, 사회자의 오픈 후에는 반드시 자동차를 얻는다. 따라서 **무조건 바꾼다는 전략에서는 2/3 확률로 자동차를 받는다.**

내가 무조건 유지한다는 전략을 취한다고 해보자. 그렇다면 나는 처음에 1/3 확률로 자동차를 선택하고, 사회자의 오픈 후에도 선택을 유지한다. 따라서 **무조건 유지한다는 전략에서는 1/3 확률로 자동차를 받는다.**

따라서, "바꿈" 전략의 우승 확률이 2/3이므로, 우리는 "바꿈" 전략을 취해야 한다! $\blacksquare$


<hr/>

## 해설 2

교수님 曰: 이건 [\<베이즈 정리\>]({{"/2021/03/02/Bayes-Rule.html" | relative_url}})로 풀 수 있어요. ~~베이즈 마스터면 풀 수 있을 듯;;~~

참가자가 고른 문을 $\textsf{A}$, 나머지 두개 문을 $\textsf{B}$, $\textsf{C}$라고 하자.

그리고 "문 $\textsf{X}$에 자동차가 있는 사건"을 $C_{\textsf{X}}$라고 하자.

반대로 "문 $\textsf{Y}$에 염소가 있는 사건"을 $G_{\textsf{Y}}$라고 하자.

우리가 구하고자 하는 것은 사건 $G_{\textsf{Y}}$가 일어난 상태에서, $\textsf{A}$문에 자동차가 있는 사건 $C_{\textsf{A}}$의 조건부 확률, 즉 $P(C_{\textsf{A}} \mid G_{\textsf{Y}})$이다. 사회자의 공개로 염소가 있음을 확인하는 문은 $\textsf{B}$ 아님 $\textsf{C}$ 이므로 논의의 편의를 위해 $G_{\textsf{B}}$가 일어났다고 가정하자.

<br/>

이 사건의 \<역사건\>, 즉 "어떤 문 $\textsf{X}$ 안에 자동차가 있을 때, 사회자가 그 $\textsf{Y}$문을 열어서 염소를 보여주는 사건" $G_{\textsf{Y}} \mid C_{\textsf{X}}$을 생각해보자. 

규칙에 따라 **사회자는 $\textsf{A}$문을 열 수 없고**, $\textsf{B}$, $\textsf{C}$ 중 하나만 고를 수 있으며, **염소가 있는 문을 열어줘야 한다**. 따라서 

- 만약 자동차가 $\textsf{A}$에 있다면
  - 사회자는 $\textsf{B}$, $\textsf{C}$ 중 하나를 골라서 염소를 보여줄 것이다. 
  - 2가지 선택지 중 하나를 무작위로 고르는 확률이므로 
    - $P(G_{\textsf{B}} \mid C_{\textsf{A}}) = \dfrac{1}{2}$
    - $P(G_{\textsf{C}} \mid C_{\textsf{A}}) = \dfrac{1}{2}$
- 만약 자동차가 $\textsf{B}$에 있다면
  - 사회자는 $\textsf{A}$, $\textsf{B}$문을 열 순 없으므로 $\textsf{C}$문을 열 수 밖에 없다. 
  - $P(G_{\textsf{C}} \mid C_{\textsf{B}}) = 1$
- 만약 자동차가 $\textsf{C}$에 있다면
  - 사회자는 $\textsf{A}$, $\textsf{C}$문을 열 순 없으므로 $\textsf{C}$문을 여는 일을 일어날 수 없다.
  - 따라서 $P(G_{\textsf{C}} \mid C_{\textsf{C}}) = 0$

베이즈 정리에 따르면, "어떤 조건부 사건이 일어날 확률은 모든 역사건의 가짓수 중 해당 사건의 역사건이 일어날 확률과 같다."

따라서

$$
P(C_{\textsf{A}} \mid G_{\textsf{B}}) = \frac{P(G_{\textsf{B}} \mid C_{\textsf{A}})}{P(G_{\textsf{B}} \mid C_{\textsf{A}}) + P(G_{\textsf{B}} \mid C_{\textsf{B}}) + P(G_{\textsf{B}} \mid C_{\textsf{C}})} = \frac{0.5}{0.5 + 1 + 0} = \frac{1}{3}
$$

$$
P(C_{\textsf{C}} \mid G_{\textsf{B}}) = \frac{P(G_{\textsf{B}} \mid C_{\textsf{B}}B)}{P(G_{\textsf{B}} \mid C_{\textsf{A}}) + P(G_{\textsf{B}} \mid C_{\textsf{B}}) + P(G_{\textsf{B}} \mid C_{\textsf{C}})} = \frac{1}{0.5 + 1 + 0} = \frac{2}{3}
$$

$P(C_{\textsf{C}} \mid G_{\textsf{B}})$의 확률이 더 높다는 것은, 문 $\textsf{B}$에서 염소를 봤다면, 처음 선택한 문 $\textsf{A}$가 아닌 다른 문 $\textsf{C}$에 자동차가 있을 확률이 크다는 것이다!

따라서, 선택을 바꿔서 $\textsf{A}$문에서 $\textsf{C}$문으로 선택을 바꾸는 전략이 더 이득이다. $\blacksquare$ [출처](https://namu.wiki/w/%EB%AA%AC%ED%8B%B0%20%ED%99%80%20%EB%AC%B8%EC%A0%9C#s-3.3)