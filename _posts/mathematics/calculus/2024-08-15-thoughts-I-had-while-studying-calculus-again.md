---
title: "미적분학을 다시 공부하며 든 생각들"
author: bluehorn_math
categories: ["Calculus"]
excerpt: "컴공과 학생이 수학과 졸업시험을 위해 고군분투 하는 첫번째 이야기. 그래도 복학 하기 전에 미적분학은 끝냈다는 감동적인 이야기 🥺"
pin: true
---

![](/images/meme/my-military-service-done-sun.jpeg){: .align-center style="max-height: 360px;" }

학부 2학년 때 호기롭게 신청한 수학과 복수전공이, IT산업기능요원으로 3년 간의 복무를 마치고, 부메랑이 되어 나에게 돌아왔다. 🪃 Data Scientist로 근무하던 때에는 그래도 확통도 다시 공부하고, 훈련소에서 알고리즘 책도 다시 읽어보면서 수학적 개념과 사고를 좀 접했던 것 같은데, Data Engineer로 전향하면서 급격히 수학에 멀어졌다... 🙄

암튼 POSTECH 수학과는 졸업 하려면, 졸업 시험을 봐야 한다. 컴공과의 "과제 연구 1·2"에 비하면 시험이 훨씬 낫지만... 그래도 부담인 것... 어영부영 시간을 보내다가 2024년 전역과 복학의 해가 밝았다...!

학교 다니면서 졸시 준비를 절대 못 할 것 같아서 복학 전에 미리 준비를 하는 걸 새해 목표로 잡았다...! 그리고 첫 타깃은 기초 of 기초인 "미적분학"...!! 교재도 집 어딘가에 짱 박아놔서, 책도 없이 유튜브에 어떤 교수님이 찍은 영상을 보면서 공부를 시작했는데... 결국 24년 1학기 졸업 시험 전에 끝마치지도 못 했다... ㅋㅋ (2학기 졸시는 응시할 수 있으려나...)


# 신입생 시절에는

뇌가 말랑 하던 나의 신입생 시절을 잠깐 돌아보고 오자.

![](/images/mathematics/calculus-2/i-passed-the-test.png){: .align-center style="max-height: 100px;" }

사실 나는 미적분학이란 과목을 대학교 때 제대로 공부하지 않았다 (・ω<) 고등학교 때 방과후 AP 수업으로 미적분학을 1년 정도 들었는데, 대학교 입학하고 나서 학점 인정 시험이 있길래 그때 기억을 살려서 바싹 공부하고 학점 인정 시험 봤더니 이수를 인정 받았었다 ✌️

![](/images/mathematics/calculus-2/my-grade.png){: .align-center style="max-height: 160px;" }
시험 보고 통과해서 학점도 `S`로 찍혔다 ✌️
{: .align-caption .text-center .small .gray }

시험을 미적1만 봤어서 미적2는 2학기에 들었어야 하는데... 친구들이랑 술 먹으러 다녀서 그런지 공부도 제대로 안 해서 성적은 `B+`을 받았다... ㅋㅋ 어쩐지 다시 공부하는데, 미적2는 수업에서 뭘 공부 했었는지 진짜 기억이 진짜 하나도 없었다 ㅋㅋ

# 졸업시험을 위해 공부를 시작하다

![](/images/meme/give-me-the-test.jpeg){: .align-center style="max-height: 360px;" }

그동안 회사에서 맨날 코드만 짜고, 컴퓨터 엔지니어링 관련된 아티클만 읽어서 그런지 교재 내용이 머리 너-무 안 들어오는 거다!! (내가 이렇게 멍청했나...) 미적분학을 공부하면 정말 다양한 내용이 나온다. 수학과 2·3학년 과목까지 수강한 나에게 있어 어떤 개념은 2학년 과목을 들으면서 봤던 개념도 있었고, 어떤 것은 인터넷에서 보충 설명을 찾아 읽어도 완전 처음 듣고 전혀 이해를 못 했던 것들도 있었다.

미적이란 과목이 기초 과목으로 포장되어 있으면서, 연습 문제에서는 상위 과목들의 내용이 종종 보였다. 쓱 보기에는 복소함수론과 확률/통계에 대한 내용이 정말 많았다... 공부할 것도 많은데 이렇게 예제 중간에 선행 내용까지 나오니... 예제의 내용을 이해하려고 상위 개념을 찾아보고, 찾아봤는데 이해 안 되서 스트레스 받고의 연속이 좀 있었다.

결국 내가 찾은 방법은 "나의 한계를 인정하는 것"이었다. 아직 나는 이 개념을 공부하기엔 준비가 안 되었다고 말이다. 조금은 무책임 할 수도 있지만, 이해 안 되서 스킵한 부분을 상위 과목을 공부할 때는 어느 정도 해결할 수 있다는 확신이 있었다. **상위 과목을 공부하면 해결된다는 걸 아는 것만으로도 학부 때보단 훨씬 나은 상황이라는 걸 깨닫게 되었다.**

<br/>

> 여러분~~ 각 챕터의 마지막 절이 제일 중요한 파트에요^^ 왜냐하면 이때까지 배운 것들을 종합하는 파트 거든요. 그래서 시험에 나올 확률도 높습니다^^ - 어느 교수님의 말

유튜브에 미적 강의를 올리시는 교수님이 영상 도중에 남긴 말이다. 영상에서 잠깐 스쳐가는 말이었지만, 공부 하는게 힘들었을 때 정신을 다 잡는데 도움이 된 말이다. 챕터의 시작은 언제나 쉬웠지만, 끝부분에서는 언제나 고전 했다. 그래도 마지막 부분이 시험 단골 손님이라는 말이 졸업 시험을 봐야 하는 나에게는 큰 스포가 되었다는 사실 ㅋㅋ

<br/>

대학교 수업들은 실물 교재가 있는게 공부하기 훨씬 편하다는 생각도 들었다. 미적 공부할 때는 실물 교재가 본가에 있어서 어쩔 수 없이 pdf 파일이랑 유튜브 강의만 보고 공부 했다. 무거운 교재를 안 들고 다녀도 되어서 가방은 가벼웠지만, 오히려 종이 교재가 있을 때보단 공부가 잘 안 되는 느낌이 있었다. 그래서 결국엔 챕터 별로 출력해서 읽고 다녔다 ㅎㅎ 어떤 프로그래밍 언어를 새로 익힌다거나, AWS나 K8s를 익힐 때는 노트북이나 핸드폰으로 아티클이나 문서 보는게 편했어서 나는 역시 디지털 체질인가 했는데... 어떤 걸 공부하는지에 따라 다른 것 같다.

![](/images/mathematics/calculus-2/my-notion.png){: .align-center style="max-height: 360px;" }
이번에도 노션은 야무지게 썼다 ㅋㅋ
{: .align-caption .text-center .small .gray }

공부한 내용은 노션에 두서 없이 기록하고, 깃헙 블로그에 정리 했다. 미적1은 연습문제 위주로, 미적2는 개념 위주로 정리 했다. 오랜만에 $\LaTeX$을 쓰니 좀 어색하기도 했지만, 하는 김에 그동안 썼던 포스트들도 좀 검수하고 다듬는 작업도 많이 한 것 같다.

- Calculus 1
  - [Limit and Continuity: Problem Solving](/2024/05/01/limit-and-continuity-problem-solving/)
  - [Derivatives: Problem Solving](/2024/05/05/derivatives-problem-solving/)
  - [Application of Derivatives: Problem Solving](/2024/05/20/application-of-derivatives-problem-solving/)
  - [Techniques of Integrals: Problem Solving](/2024/05/30/techniques-of-integrals-problem-solving/)
  - [Taylor Series & Macluarin Series](/2022/10/29/talyor-series-and-maclaurin-series/)
  - [급수의 극한을 판정하는 법](/2024/06/08/determine-the-limit-of-a-series/)
  - [교대 급수의 극한을 판정하는 법](/2024/06/08/determine-the-limit-of-an-alternating-series/)
  - [Sequence and Series: Problem Solving](/2024/06/08/sequence-and-series-problem-solving/)
- Calculus 2
  - [Parametric Equations: Problem Solving](/2024/06/16/parametric-equations-problem-solving/)
  - [Vectors and Space: Problem Solving](/2024/06/30/vectors-and-space-problem-solving/)
  - [Partial Derivatives and Differentiability](/2024/07/06/prtial-derivatives-and-differentiability/)
  - [Lagrange Multiplier](/2024/07/14/lagrange-multiplier/)
  - [Multiple Integrals](/2024/07/16/multiple-integrals/)
  - [Vector Fields](/2024/07/20/vector-fields-and-line-integrals/)
    - [Arc Length와 Line Integral](/2024/06/30/arc-length-and-line-integral/)
  - [Green Theorem](/2024/07/21/green-theorem/)
  - [Divergence and Curl](/2024/07/24/curl-and-divergence/)
  - [Parametric Surface, and Surface Integral](/2024/08/10/paramteric-surface/)
  - [Stokes' Theorem](/2024/08/11/stokes-theorem/)
  - [Divergence Theorem](/2024/08/14/divergence-theorem/)


# 미적분학을 드디어 완주 하다

![](/images/meme/omedeto.gif){: .align-center style="max-height: 360px;" }

나의 미적분학 공부는 3월에 시작해 8월이 되어서야 끝났다 ㅋㅋㅋ 대충 5개월 정도 걸렸는데, 이런 페이스라면 24년 2학기 졸업시험도 물 건너 간 것 같다 ㅋㅋ;;

어느 순간 졸업 시험에 공부할 과목이 5과목이 된다는 게, 마음을 조급 하게 한 것 같다. 마음이 불안정 하니 지식을 받아 들이는 것도 잘 안 되었던 것 같고, 늘어지는 페이스에 슬럼프도 좀 있었다. 그러다가 깨달은게 내게 필요한 건 미적분학과 수학에 대한 이해 100%가 아니라는 사실. 졸업 시험에 통과하기 위해선 80-90% 정도만 이해해도 충분한 시험이다라고 마음 것도 페이스를 조절하는데 큰 도움이 되었다. 위에서도 적었던 "한계를 인정하기" 전략이랑도 일맥상통 하는 것 같다.

그래도 5과목 중 한 과목을 포기 하지 않고, 잘 마무리 한 것이 참 뿌듯하다. 😁 한 과목을 해냈다는 건, 그 다음 과목도 할 수 있다는 거고, 결국 다섯 과목 모두 해낼 수 있다는 것!

다음으로 공부할 과목 "미분방정식"을 할 것 같다. 담학기에 복학하면 "상미분방정식"을 들어야 하기 때문... OTL... 암튼 다음 과목도 포기 하지 않고 잘 해보자...! 🏃

\- 평화 시장이 보이는 카페 안에서
