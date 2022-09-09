---
title: "Basic Statistical Concepts"
layout: post
use_math: true
tags: ["Time Series Analysis"]
---

## Covariate

> “Covariate” is a **continues independent variable** in a regression or similar model. For instance, if you were modeling number of animals in a given area, you might have covariates such as temperature, season, latitude, altitude, time of day and so on.

통계모델에는 독립 변수(independent variable)와 종속 변수(dependent variable)가 있다. 처음 실험을 계획할 때는 정확히 어떤 요인이 종속 변수에 영향을 주는지 모르기 때문에 수집할 수 있는 모든 요인을 모으게 된다. 그래서 온도, 위도, 경도, 시간 등등 어쩌면 종속 변수에는 전혀 쓸모가 없을지도 모르는 값들을 수집한다.

\<Covariate\>는 그렇게 수집한 모든 independent variable을 말한다. 구체적으론 continuous independent variable을 \<Covariate\>라고 한다.

\<Covariate\>는 종속 변수에 영향을 주는 변수가 될 수도 있고, 또는 전혀 쓸모 없는 변수가 될 수도 있다. 그것을 판단하기 위해 실험 결과를 분석하는 것이다.

요약하면, \<Covariate\>는 "feature"라고 말할 수 있을 것 같다.

### Covariate Shift

딥러닝 쪽에서 쓰는 용어인데, "training set과 test set이 다른 분포를 가지는 상황"을 말한다. 사실 trianing set과 test set을 단순한 테크닉으로 나눴다면 이런 현상을 마주할 가능성이 크다. 또, 모델을 학습 했을 때와 모델을 서비스 할 때 입력되는 데이터 사이에는 Gap이 있을 수 밖에 없다.

이런 상황을 해결하기 위해 딥러닝에선 Batch Normalization이 등장했는데... 잘 모르겠다면 지금은 넘어가자! 😉
