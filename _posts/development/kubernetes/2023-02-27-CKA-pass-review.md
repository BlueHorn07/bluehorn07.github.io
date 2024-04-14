---
title: "CKA 시험 합격 후기"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
sitemap:
  priority: 0.9
---

회사에서 쓰는 Kubernetes를 제대로 알고 쓰고 싶어서 CKA 시험을 준비해서 응시 했다. 시험 준비하고 응시 하기까지 우여곡절이 많았지만, 결국은 한번에 자격증을 딸 수 있었다!

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/cka-certificate.png" | relative_url }}" width="80%">
</div>

# 왜 CKA 시험을 봐야 겠다고 생각했는가?

회사에서 Kubernetes를 쓰기 때문이다. 회사 슬랙에서나 문서들에 "쿠버네티스, K8s, ..." 용어들이 나오는데, 그게 정확히 뭘 말하는 건지 모르니 업무에 주도적으로 참가하고 의견을 낼 수 없다고 생각했다. 또, K8s 자체 뿐 아니라 K8s에서 파생되는 helm, prometheus, grafana, argo, k-native 등등의 extension이 많아도 느껴졌다. K8s 하나를 제대로 깨우치면 처럼 파생 extension에 대한 것들도 경험하고 실험하는 기회가 생길 거라고 판단했다. **즉, K8s와 CKA가 투입 대비 가치가 가장 컸던 셈이다.**

# 시험은 어떻게 준비했는가?

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/cka-notion.png" | relative_url }}" width="60%">
</div>


A. 흔히 CKA 시험을 준비하는 방식을 그대로 따랐다. Udemy의 mumshad 씨의 강의를 다 듣고, 딸려오는 KodeKloud의 핸즈온도 모두 풀었다. killer.sh의 모의고사 2회까지 풀었다. 어떤 시험이든 오답노트를 적어서 다음엔 어떻게 해야지를 적어둔다. 모의고사 풀고 오답 정리 할 때 실력이 가장 많이 상승한 것 같다.

## 시험 준비 기간은?

A. 11월 초에 시험을 접수하고, 두 달 쯤 공부해서 12월 말에 시험을 볼 생각이었는데.... 이번 시험은 유독 진도가 느렸다 ㅠㅠ 아무래도 Udemy 강의가 영어라는 점 때문에 무한으로 졸음이 쏟아졌고, 강의 후에 핸즈온 랩(Lab)이 있어서 시간이 2배로 걸렸다. 결국 시험을 1월로 다시 계획하고 시험 확정까지 했으나... 그때도 준비가 부족해서 2월로 한번더 미루고 말았다!! 종합하면 3개월 정도 걸린 것 같다.

# 시험 난이도는 어땠는가?

A. Udemy 강의와 killer.sh 모의고사를 착실히 잘 했다면 합격 커트라인(66점)까지는 도달할 것이다. 다만 시험 시간은 좀 부족해서 마지막까지 10초까지 문제를 풀어야 했다. 인터넷에 돌아다니는 시험에서 나올 만한 주제: 사이드카 패턴, etcd 스냅샷 등등은 꼭 미리 연습해보자. 본인은 etcd 스냅샷은 잘 했고, 복원하는 건 과감히 포기 했다! etcd 복원할 때 어떤 side-effect이 나서 다른 문제들에 영향을 줄지 몰라서 포기하는게 더 안전하다. 본인은 100점 만점에 84점으로 패스 했다!


## 시험 때 우여곡절이 있었다고?

A. 시험 시작 전에 웹캠으로 본인 여권을 보여줘야 한다. 그런데 집에 있는 웹캠이 성능이 너무 안 좋아서(780p 였다!) 여권을 카메라 앞에 가져다 줘도 내용이 제대로 안 보였다... 시험 30분 전에 미리 입장했는데 1시간 동안 웹캠 문제 때문에 입실을 못 해서 결국 일주일 뒤로 re-scheduling 했다... 그 사이에 분노에 현질을 해서 1080p에 auto-focus 기능까지 있는 10만원 짜리 웹캠을 질렀다! 미뤄진 시험 때는 본인 인증 절차를 잘 통과해서 무사히 시험을 볼 수 있었다 ㅎㅎ


# 시험 환경이 정말 x같다고...?

<div class="img-wrapper">
  <img src="https://lh6.googleusercontent.com/4w_nO6kc9lM7eLDk5_SVhsIpsOi2TwEXEyi4puR8GTJ5ISbEUDJ4_BdzBwjN0Wfrmfg3h9FmDclAuctGAuOySyFDAXe4gHDgjjWxHpGVKNixgh0BY--eI4HVz2zJz472hnhF9OM7iCPG2lpkwzDIuxc" width="60%">
  <p markdown="1">
    [CKAD, CKA, and CKS - New PSI Exam Environment](https://kodekloud.com/blog/ckad-cka-cks-new-psi-exam-environment/)
  </p>
</div>


A. 시험은 리눅스 가상 데스크탑에서 본다... 윈도우에 익숙하든 맥에 익숙하든 리눅스 가상 데스크탑은 정말정말 불편하고 적응이 필요하다. killer.sh 모의고사에서 시험 환경 그대로 모의고사를 볼 수 있기 때문에 진짜 CKA 시험을 볼거라면 killer.sh 모의고사를 반드시!!! 풀어야 한다. 본인이 찾은 꿀기능은 창전환인데, 윈도우 기준 `Ctrl + Alt + 좌우 화살표`로 창전환을 할 수 있으니 꼭 기억해두자!

# 시험 후엔 어떤 삶을 살고 있는지?

A. CKA로 K8s를 제대로 공부하고 나니까 예전엔 알음알음 하던 배포 작업의 큰 그림과 흐름이 보이게 되었다. 또 K8s extension들도 새로 공부하거나 익히는데 많은 도움이 되어서 처음에 기대했던 효용을 톡톡히 보고 있다. 그런데 시험을 너무 오래 준비해서 그런지 약간의 번아웃이 와버렸다. 시험 준비하는 동안 꾸준히 하던 운동 습관도 엉망이 되어버렸다 ㅠㅠ 역시 시험은 1-2개월 정도 준비하는게 베스트인 것 같다...


# 다음 시험 계획은 무엇인가?

A. 올해 안에 도전할 다음 시험은 [ELK Certificate](https://www.elastic.co/kr/training/certification)다. ELK 스택도 엔지니어 분야에서 표준의 위치이기 때문에 배워둘 가치가 있다고 생각한다. 그외에도 Airflow Certificate나 Kafka Certificate도 고려하고 있다.
