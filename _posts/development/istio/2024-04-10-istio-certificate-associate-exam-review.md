---
title: "Istio Certificate Associate 시험 합격 후기"
toc: true
toc_sticky: true
categories: ["Develop", "Istio"]
---

![](/images/development/istio/ICA-certificate.png)

CKA 시험에 이어서 2번째로 시험 본 CNCF 자격증이다 ㅎㅎ 올해 세운 목표 중에 처음으로 달성한 녀석인데, 드디어 올해도 한 발자국 성장한 느낌이 들어서 기쁘다!!!

# 왜 ICA 시험을 봐야 겠다고 생각 했는지?

회사에서 운영하는 데이터 파이프라인을 넘겨 받게 되었는데, 이 파이프라인이 Istio를 서비스 메쉬로 채택해 사용하고 있다. 인수인계 받을 때, Istio에 대해서도 설명을 듣고, `VirtualService`나 `InressGateway` 리소스도 떠있는 걸 확인 했는데, 어떻게 동작하는지 내가 요걸 건드려도 될지 안 될지 전혀 감이 안 잡혔다.

이제는 내가 주도적으로 관리하는 파이프라인이기도 하고, 문제가 생겼을 때 잘 진단하고 대응하기 위해서 Istio를 공부할 필요성을 느끼고 있었다. 그리고 우연히 돌아다니다가 CNCF에서 Istio 자격증을 출시했다는 소식을 듣고 2024년 올해 첫 자격증으로 Istio를 공부하기로 결심 했다!!

# 시험은 어떻게 준비 했는가?

## Udemy 강좌 듣기

![](/images/development/istio/udemy-istio-course.png)

일단 Udemy 강좌를 하나 구매해서 쭉 들었다. [Istio Hands-On for Kubernetes](https://www.udemy.com/course/istio-hands-on-for-kubernetes/?couponCode=GENAISALE24) 요 강좌를 들었는데 설명도 괜찮고 난이도도 무난 했다.

Udemy 강좌를 다 들은 후에는 아래 2개를 계속 반복 했는데

1. 이해 안 되서 표시하고 넘어갔던 것들 다시 보기
2. Istio 공식 문서에서 찾아보기

![](/images/development/istio/ICA-cert-prepare-my-notion.png)
요렇게 모르는 것들을 메모해두고, 찾아보면서 공부했다!!
{: .small .text-center .gray }

## 로컬 K8s에서 핸즈온!

처음 Istio를 공부할 때는 `VirtualService`, `DestinationRule` 둘의 기능이 서로 헷갈려서 반대로 기억하는 경우도 종종 있었다 ㅋㅋㅋ `Gateway` 리소스도 Ingress Gateway가 K8s `Ingress`랑 이름이 비슷해서 둘이 어떻게 다른건지, 어떻게 동작하는 건지. 그리고 또 `Gateway`랑 `VirtualService`랑 같이 생각하는 것도 처음에 갈피를 잘 못 잡았었다 ㅠㅠ

이럴 때 도움이 많이 된 건 핸즈온이다!! 로컬에 구축해둔 K8s 클러스터에 이것저것 핸즈온들을 해보면서 각 리소스들이 이해되고 나아가서 그들이 서로 어떻게 상호작용하는지도 터득하게 된 것 같다 😁

## 슬랙 커뮤니티에 질문하기!

Istio 시험 공부를 할수록 이해 되는 것들도 있었지만, 어떤 것들은 도저히 이해가 끙끙 댔던 것들이 있다 ㅠㅠ 아무리 구글링을 하고, Istio 문서를 뒤져봐도 답이 안 보였는데 그러다가 문득 Istio도 다른 오픈소스들처럼 슬랙 커뮤니티가 있지 않을까? 하고 떠오르게 되었고 찾아냈다!! ㅎㅎ

슬랙 커뮤니티는 CNCF 슬랙 커뮤니티와 Istio 슬랙 커뮤니티 둘다 활용했다 ㅎㅎ

CNCF 슬랙에는 `#istio-exam-study-group`이란 채널이 있는데, ICA 시험 후기들이 올라와서 시험 꿀팁들을 전수 받을 수 있다 ㅎㅎ

![](/images/development/istio/cncf-slack-ica-exam-group.png)
나도 시험 후기를 올려뒀다 ㅎㅎ
{: .small .text-center .gray }

Istio 슬랙은 좀더 Istio 개념적인 이야기들이 오고가는 곳으로 잘 모르는 개념이 있을 때는 이곳의 `#general` 채널에 물어봤다!!

![](/images/development/istio/istio-slack-my-question-1.png)

![](/images/development/istio/istio-slack-my-question-2.png)

![](/images/development/istio/istio-slack-my-question-3.png)

질문을 올려두면 어느샌가 친절하신 분들이 나타나 답변을 주곤 하신다 💙

## Killacoda로 모의 테스트!

CKA 시험 볼 때는 [killer.sh](https://killer.sh/)라고 CKA 시험과 거의 동일한 환경과 난이도에서 보는 모의 테스트가 있었는데, Istio 시험에서는 그런게 없었다 ㅠㅠ

어찌저찌 커뮤니티의 도움을 받아 [killercoda.com](https://killercoda.com/)라는 사이트에 모의 테스트가 있다는 정보를 입수해 시험 직전에 몇몇 문제를 풀어왔다.

일단 확실히 점검에는 확실히 도움이 된다!!! 본인도 시험 직전에 풀어보고 내가 부족한 걸 찾았을 정도 ㅎㅎ

➡️ https://killercoda.com/nashwan

Istio 모의 테스트가 2개 있는데, 요 테스트가 더 퀄리티가 좋았다!! 👍

## 공부한 거 블로그 글로 쓰기!

ㅋㅋㅋ 요즘엔 노션(notion)을 많이 활용하고 있긴 한데, 그래도 공부한 걸 블로그 글로 적어야 온전히 이해한 것 같은 느낌이 들어서 이번에 Istio 시험 준비할 때도 공부한 내용들을 블로그 글로 정리해뒀다 ㅎㅎ (노션은 나만 보는 거라 꽤 대충 적는 느낌?? 사실상 그냥 메모장 같은 공간이다 ㅋㅋ)

이번에는 24.02.02부터 24.03.24 시험 직전까지 **총 21개** 포스트를 작성했다!!! Istio를 공부하는 다른 사람들에게 내 글이 조금이나마 도움이 되면 좋겠다 ㅎㅎ

# 시험 준비 기간은?

'시험을 봐야지!!' 결심만 하고 공부를 열심히 안 해서 1월 1일부터 공부를 시작했지만 차일피일 미루다가 결국 3월 24일이 되어서야 시험을 봤다 ㅋㅋㅋ 거의 3개월 정도 공부한 셈인데 예전에 CKA 시험 공부할 때도 3개월 정도 걸렸으니 내가 시험 준비할 땐 대충 이 정도 시험이 필요한 것 같다 ㅋㅋ

그래도 CKA 때보단 실력이 많이 늘어서 금방 시험 볼 줄 알았다 ㅋㅋㅋ 왜 이렇게 오래 걸렸는지 생각해보면, 이번에는 핸즈온을 전부 내 로컬에서 진행해서 그런 것 같다!!! CKA 때는 Udemy 강좌에 핸즈온 랩(Lab)이 같이 딸려 있었는데, Istio 시험 준비할 때는 그런게 없었다 ㅠㅠ

# 시험 난이도는?

생각보단 평이한 난이도다!! 일단 CKA 보단 체감 난이도는 쉬운 편 ㅋㅋㅋㅋ 그래도 완전 쉬운 건 아니고 공부는 다 해야 한다 ㅋㅋㅋㅋ

Istio에 정의된 모든 CRD 리소스는 어떤 역할을 하는 건지 알고 있어야 하고, 그중에서 핵심이 되는 리소스들

- `IstioOperator`
- `VirtualService`
- `DestinationRule`
- `Gateway`

그리고 AAA(AuthN & AuthZ & Audit) 관련 리소스들

- `PeerAuthentication`
- `RequestAuthentication`
- `AuthorizationPolicy`
- `Sidecar`

이것들은 빠삭하게 알고 있어야 한다!!

또, VM 위에서 Istio 메쉬를 구성하는 방법도 알아야 해서 `WorkloadGroup`와 `WorkloadEntry` 리소스까지 공부해야 한다!

난이도는 평이하지만, Istio 리소스 yaml들을 일일이 작성하고 `k apply` 해야 해서, 2시간이 생각보다 빡빡했다. 본인은 체크해둔 한두 문제만 검토하고 끝났음 ㅠㅠ

## 시험 플랫폼은 어땠는지?

CKA 시험 보던 그 가상환경 플랫폼이다!! CKA 시험 봤다면 익숙할 것 ㅎㅎ

대신 Istio 시험에서는 VS code가 내장되어 있다. [시험 안내문](https://docs.linuxfoundation.org/tc-docs/certification/important-instructions-ica)에도 설치되어 있다고 나와있다.

인터넷은 Istio 공식 문서 외에는 아무것도 못 본다!! 그런데 Istio 문서에서 검색 기능도 막혀 있어서 리소스에 대해서 찾으려면 무조건 본인이 직접 찾아야 했다... ㅠㅠ

# 시험 후엔 어떤 삶을?

오래 준비 했던 시험이 끝나서 해방감을 만끽 하고 있다 (얏호~!~!) 4월초에 3박 5일로 🇹🇭 태국 방콕 여행을 다녀왔다 ㅎㅎ 방콕이 지금 여름인 시즌이라 기온이 38도나 되서 진짜 너무너무너무 더웠다 😣 그래도 맛있는거 많이 먹고, 수영장에서 수영도 많이 하고 진짜 재밌게 놀고 또 쉬고 왔다 ㅎㅎ 그리고 회사 돌아와서는 그동안에 생겨난 일들을 열심히 처리하는 중... ~~으악~~ ㅋㅋㅋ

# 다음 시험 계획은??

시험이 있어야 공부를 좀 한다는 사실을 알게 되어서 ~~스스로 인생을 하드 모드로 살고 있다~~ 다음 시험도 벌써 생각해뒀다 ㅋㅋ

일단 올해 9월에 병특이 끝나면 학교에 돌아가서 졸업을 해야 하는데, 19년도의 내가 수학과 복전 하겠다고 신청을 해버려서 수학과 졸업 시험을 준비해야 한다... 수학 안 한지 꽤 되어 공부할 양이 진짜 어마어마 하다... 아마 이번 1학기에 보기는 어려울 것 같고 2학기 시험을 목표로 계속 공부할 것 같다.

수학과 졸업 시험은 아직 많이 남기도 했고 그 사이 심심할 것 같아서 "Databricks Certificate"를 따보려고 한다!! ㅎㅎ 원래 Databricks는 회사에서 쓰고 있었는데, 좀더 잘 써보고 싶기도 했고 아직 나 자신이 spark, hadoop을 제대로 이해하고 있다고 생각하지 못 하고 있어서 시험을 준비하면서 spark, hadoop도 제대로 공부해 볼 것 같다.

그럼 이만~~
