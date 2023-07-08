---
title: "R 입문"
toc: true
toc_sticky: true
categories: []
---


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

(p.s. 위 포스트의 명령어에서 `-d`로 데몬 옵션을 줘야, 프롬프트가 종료되어도 나중에 재시작 할 수 있다!)

### R을 배워보자!

경희대 이상준 교수님의 유튜브 강좌을 보고 `R` 언어를 익혔다.

👉 [YouTube 강좌 링크](https://youtube.com/playlist?list=PLaqQvlCBe8vL739pc-jESsucndheGmQIZ)

강좌를 통해 아래와 같은 `R` 패키지들을 사용해볼 수 있었다.

- `dplyr`: 데이터를 쉽게 전처리 하게 도와주는 패키지
- `ggplot2`: 데이터를 쉽게 시각화; 산점도, 그래프, 박스 플롯 등등을 쉽게 그릴 수 있게 도와주는 패키지
- `KoNLP`: 한국어 NLP 패키지

### R로 추리 통계!!

\<기술 통계\>가 \<평균\>, \<표준편차\> 같은 기초적인 통계량에 대한 접근이라면, \<**추리 통계**\>는 추출한 표본에서 각 요소들 사이 관계; Correlation를 살펴보거나, 모집단의 특징을 추론하는 통계다. `R` 강좌에서는 자동차 연비를 중심으로 거리-연비 관계에 대한 \<산점도 scatter plot\>를 확인해보았다.

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

지금 `R`을 배우고, HW 문제를 풀면서 자주 쓰고 있는데, 생각보다는 쓸만한 것 같다. 물론 `python`과 비교하면 확장성이 많이 떨어지지만, 그래도 데이터셋을 분석하고, 통계적으로 접근하기에는 여전히 `R`이 좋은 것 같다. 물론 대세는 `python`이 분명하지만, 아직 `R`도 함께 다룰 수 있어야 하는 것 같다.

때로는 `R`의 패키지가 좋을 때도 있고, `python`의 패키지가 좋을 때도 있었다. 결국은 둘 다 잘하는게 best 인 것 같다 😁

