---
title: "Introduction to Boosting"
layout: post
tags: [applied_statistics]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

💥 Boosting의 파워는 강력하지만, 배우고 이해하기는 어려운 테크닉입니다 ㅠㅠ 미리 마음의 준비를 하고 입장하길 바랍니다!

<hr/>

\<**Boosting**\>은 Ensemble Method의 일환으로 weak learniner들을 연속적(sequential)으로 여러 개를 결합하여 Prediction 성능을 높이는 기법이다. 

먼저 \<**Ensemble Method**\>란 모델이 학습 데이터에 overfitting 되지 않도록, overfit 되지 않은 약한 모델 여러개를 결합해 사용한다는 것이다.

\<Boosting\>은 여기에 "sequential"이 추가된다. 직접 weak leaner의 Error를 고려해 새로운 weak learner를 잡아 모델에 추가한다. 이 과정이 Sequential 하게 진행하는 것이 \<Boosting\>의 특징이다. 이 접근은 추후에 등장할 Loss를 줄이는 방향으로 새로운 weak learner를 잡는 \<Gradient Boosting\>으로 확장된다.

<div class="img-wrapper">
  <a href="https://commons.wikimedia.org/wiki/File:Ensemble_Boosting.svg#/media/File:Ensemble_Boosting.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Ensemble_Boosting.svg/1200px-Ensemble_Boosting.svg.png" alt="Ensemble Boosting.svg" width="700px"></a>
  <p>
  By <a href="//commons.wikimedia.org/wiki/User:Sirakorn" title="User:Sirakorn">Sirakorn</a> - <span class="int-own-work" lang="en">Own work</span>, <a href="https://creativecommons.org/licenses/by-sa/4.0" title="Creative Commons Attribution-Share Alike 4.0">CC BY-SA 4.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=85888769">Link</a>
  </p>
</div>

\<Boosting\>의 학습은 기존 모델에 weak learner를 연속적으로 추가하는 방식으로 이뤄진다. learner가 추가될 때, data weights가 조정되는데, 이 과정을 "**re-weighting**"이라고 한다. 잘못 분류된 input data가 더 큰 weight를 갖도록 조정되며, 올바르게 분류된 data는 낮은 weight를 갖게 된다. 이를 통해 다음 learner는 이전 learner에서 약점이었던 부분에 좀더 집중할 수 있다.

<br/>

\<Boosting\>은 "off-the-shelf procedure", 즉 바로 꺼내 써도 될 정도로 좋은 성능을 보이는 알고리즘이라고 한다.

- [AdaBoost; Adaptive Boosting]({{"/2021/05/10/AdaBoost.html" | relative_url}})
- Gradient Boosting; GBM
- XGBoost


<hr/>

#### 참고자료

- ['Hyunlee103'님의 포스트](https://hyunlee103.tistory.com/25)
- [Wikipedia - Boosting (Machine Learning)](https://en.wikipedia.org/wiki/Boosting_(machine_learning))