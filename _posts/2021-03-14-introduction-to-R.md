---
title: "R 입문"
layout: post
tags: []
use_math: true
---

### 서론
이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

이번 학기에 듣게 된 \<통계적 데이터 마이닝\> 수업에서 `R` 언어에 대한 역량을 요구하여 한번 공부해보게 되었다. 😆 사실 파이썬의 `pandas`를 주로 쓰긴 하는데, `R`도 언젠가 도움이 되겠지...??

### R-studio 설치

친구로부터 `Docker`의 미담을 듣고, `Docker`로 `R`을 써볼 수 있지 않을까 하는 호기심이 생겼다. 🤩 찾아보니 `R`의 구동 프로그램인 R-studio를 정말로 `Docker`에서 돌릴 수 있어서 R-studio를 도커로 설치해 사용하고 있다. 설치는 아래의 포스트를 참고했다.

👉 [Docker로 RStudio Server 환경 설치](https://emflant.tistory.com/240)

<div class="img-wrapper">
  <img src="{{ "/images/r-tutorial/r-tutorial-0.jpg" | relative_url }}" width="90%">
</div>

도커로 R-studio를 돌린 선택은 정말 **대만족**이다!! 도커, 앞으로도 자주 사용할 것 같다 ㅎㅎ 😘

### R을 배워보자!

경희대 이상준 교수님의 유튜브 강좌을 보고 `R` 언어를 익혔다. 

👉 [YouTube 강좌 링크](https://youtube.com/playlist?list=PLaqQvlCBe8vL739pc-jESsucndheGmQIZ)

강좌를 통해 아래와 같은 `R` 패키지들을 사용해볼 수 있었다.

- `dplyr`: 데이터를 쉽게 전처리 하게 도와주는 패키지
- `ggplot2`: 데이터를 쉽게 시각화; 산점도, 그래프, 박스 플롯 등등을 쉽게 그릴 수 있게 도와주는 패키지
- `KoNLP`: 한국어 NLP 패키지

### R로 추리 통계!!

\<기술 통계\>가 \<평균\>, \<표준편차\>과 같은 기초적인 통계량에 대한 접근이라면, \<**추리 통계**\>는 추출한 표본에서 좀더 각 요소들 사이 관계; Correlation를 살펴보거나, 모집단의 특징을 추론하는 통계다. `R` 강좌에서는 자동차 연비를 중심으로 거리-연비 관계에 대한 \<산점도 scatter plot\>를 확인해보았다.

<div class="img-wrapper" style="display:flex; justify-content:center; align-items:center;">
  <img src="{{ "/images/r-tutorial/r-tutorial-1.jpg" | relative_url }}"  style="float:left; width:48%;"> <img src="{{ "/images/r-tutorial/r-tutorial-5.jpg" | relative_url }}" style="float:left; width:48%;">
</div>

<div class="img-wrapper">
  
</div>

### R로 텍스트 마이닝!

R의 `KoNLP` 라는 패키지를 사용해 간단한 텍스트 마이닝을 시도해보았다. 처음에 `KoNLP` 설치가 원활하지 않아 [이곳](https://hs5555.tistory.com/71)의 포스트를 참고해 설치했다. 그러나 너무 기초적인 수준의 실습을 해서 그런지 `R`로 하는 텍스트 마이닝은 그렇게 유익하진 않았다 😥

<div class="img-wrapper">
  <img src="{{ "/images/r-tutorial/r-tutorial-3.jpg" | relative_url }}" width="400px">
  <p><small>트윗에서 최빈도 단어에 대한 그래프다. 아쉽게도 한국어 인코딩이 깨졌다 😥</small></p>
</div>

### R로 데이터 시각화!!

그 외에 `ggplot2`를 이용해 "년도별 실직자수"의 그래프를 그리거나, 미국 내 범죄율 빈도를 지도로 시각화하는 등의 작업을 `R`로 진행해보았다.

<div class="img-wrapper" style="display:flex; justify-content:center; align-items:center;">
  <img src="{{ "/images/r-tutorial/r-tutorial-2.jpg" | relative_url }}"  style="float:left; width:48%;"> <img src="{{ "/images/r-tutorial/r-tutorial-4.jpg" | relative_url }}" style="float:left; width:48%;">
</div>

<hr/>

### R에 대한 인상

예전에 `pandas`를 사용해본 적이 있었는데, `R`에서나 `pandas`에서도 둘다 `DataFrame`이라는 개념은 동일하게 가지고 있었다! 그래서 그런지 `R`의 개념들이 그렇게 어렵지는 않았다.

이번에 `R`을 실제로 배워보고 느낀 점은... 🙄 `R`은 \<매트랩 Matlab\> 같다는 느낌이 들었다. 사실 매트랩이라는 프로그램과 언어가 유용하고, 본인도 고등학생 때 잠깐 배워본 적은 있지만, 현재는 잘 쓰진 않는다. 그리고 요즘은 파이썬으로 대부분의 기능들은 다 잘 수행할 수 있기 때문에 굳이 여러 개의 언어와 프로그램의 사용법을 익혀서 \<폴리글랏 polyglot\>을 추구하기 보다는 하나의 언어와 프레임워크에서 왠만큼의 기능을 수행할 수 있다면, 하나의 언어로 통합하는게 좋지 않나 생각한다. 

그래서 내가 내린 결론은 이번에 `R`을 배우긴 했지만, 그다지 매력적이지는 않다는게 내 결론이다. 😥

