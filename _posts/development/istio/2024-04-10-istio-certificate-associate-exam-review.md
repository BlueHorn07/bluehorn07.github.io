---
title: "Istio Certified Associate 시험 후기 ⭐️"
toc: true
toc_sticky: true
categories: ["Develop", "Istio"]
sitemap:
    priority: 0.9
excerpt: "2024년의 첫 자격증으로 Istio 자격증을 따고, 성장한 나의 이야기"
---

![](/images/development/istio/ICA-certificate.png)

CKA 시험에 이어서 2번째로 시험 본 CNCF 자격증이다 ㅎㅎ 올해 세운 목표 중에 처음으로 달성한 녀석인데, 드디어 올해도 한 발자국 성장한 느낌이 들어서 기쁘다!!!

# 왜 ICA 시험을 봐야 겠다고 생각 했는지?

회사에서 데이터 파이프라인을 넘겨 받게 되었는데, 이 파이프라인이 Istio를 서비스 메쉬로 채택해 사용하고 있다. 인수인계 받을 때 Istio에 대해서도 설명을 듣고, `VirtualService`나 `InressGateway` 리소스도 떠있는 걸 확인 했는데, 이게 어떻게 동작하는지 내가 요걸 건드려도 될지 안 될지 전혀 감이 안 잡혔다.

이제는 내가 주도적으로 관리하는 파이프라인이기도 하고, 문제가 생겼을 때 잘 진단하고 대응하기 위해서 Istio를 공부해야 겠다고 생각하고 있었고, 우연히 CNCF에서 Istio 자격증을 출시했다는 소식을 듣고 2024년 올해 첫 자격증으로 Istio를 공부하기로 결심 했다!!

# 시험을 준비하면서 공부가 많이 됐다고?

진짜진짜 공부가 많이 됐다!! Istio에 대해서도 공부를 많이 했지만, 그외에 Istio에 사용된 처음 보는 엔지니어링 테크닉들도 많이 만난 것 같다.

특히 Istio의 컨트롤 플레인인 `istiod`를 공부할 때 실력이 많이 성장한 것 같다. 처음에는 Sidecar Injection만 이해하고 있었는데, 이후에 Envoy 구성 정보 컴파일과 Certificate 관리에 대한 내용을 공부하면서 Istio의 전체적인 구조를 깨달은 것 같다. 또, 인터넷에서 Istio 자료들을 찾다보면 몇몇 자료가 Istio 구버전(istio 1.5 이하)의 구조로 설명하고 있는데, Mixer, Galley, Citadel이 나오는 이 구조를 이해하고 싶다는 생각이 갑자기 들어서 Istio 블로그 글이랑 패치 노트를 열심히 찾아본 것도 기억에 남는다 ㅎㅎ [[삽질의 흔적 ㅎㅎ]](https://bluehorn07.github.io/2024/03/07/istio-control-plane-detail-examine/)

Istio를 공부하면서 새로운 개념들도 많이 만나게 되었는데, mTLS와 WebAssembly도 Istio에서 처음 들어봤고, Zerㄴo-trust Network에 대해서도 처음에 잘 이해가 안 됐는데 관련 자료를 찾아보니 Istio로 쉽게 K8s ZTN를 구축할 수 있다는 것도 알게 됐다. (토스의 ["고객 불안을 0으로 만드는 토스의 Istio Zero Trust" 영상](https://youtu.be/4sJd6PIkP_s?si=aYMdO52OFKBGied9)이 많이 도움이 됐다.)

Istio 공부하면서 Envoy를 만나게 되는데, Istio가 Envoy를 사용하기만 하는거라 처음엔 Envoy가 되게 블랙 박스처럼 느껴졌다. 이것도 `istiod`의 Envoy Configuration 컴파일 기능을 자세히 공부하면서 Istio가 Envoy를 쓰기 위해 어떻게 동작하는지, 그리고 Config를 모든 Envoy 사이드카에 전파하기 위해 Service Discovery API 이런 부분들이 조금 어려웠지만 재밌게 느껴졌다 ㅎㅎ

그외에도  Istio가 Sidecar 기반 메쉬의 다음 세대(Generation)로 준비중인 [Ambient Mesh](https://istio.io/latest/docs/ops/ambient/getting-started/)에 대한 부분, Istio를 K8s가 아닌 Virtual Machine에 대해서도 서비스메쉬를 구축하는 `WorkloadGroup` 리소스에 대해서도, 그리고 트래픽을 추적(Tracing)하는 Jaeger, 그리고 서비스메쉬의 트래픽을 모니터링 하는 Kiali도 모두 정말 재밌는 주제들이었다.

처음엔 파이프라인을 잘 관리하고 싶어서 그리고 내가 맡은 일을 내가 잘 모르고 있다는 불안감 때문에 Istio 공부를 시작한 것 같은데, 공부를 하면서 정말 다양한 엔지니어링 도구들과 MSA 구조를 운영할 때의 고민들과 그것들을 해결하기 위해 어떤 테크닉들을 살펴보고 온 것 같다.

이젠 자신이 생겨서 Istio가 떠있는 K8s를 업그레이드 할 때도, Istio 업그레이드를 할 때도 자신 있게 하고 있다 ㅎㅎ '장애가 나도 나는 금방 디버그하고 해결할 수 있는 실력이 있는걸!'라는 생각이 갖춰진 것 같다. 이젠 예전만큼 Istio 관련 작업들이 힘들게 느껴지지 않는다 ㅎㅎ

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

이런 혼란의 과정을 극복하는데 도움이 된 건 핸즈온이다!! 로컬 맥북에 띄운 K8s 클러스터에 이것저것 핸즈온들을 해보면서 각 리소스들이 이해되고 나아가서 그들이 서로 어떻게 상호작용하는지도 터득하게 된 것 같다 😁 Istio에서 제공하는 예제인 [helloworld 예제](https://bluehorn07.github.io/2024/02/05/istio-helloworld-demo/)와 [Bookinfo 예제](https://bluehorn07.github.io/2024/02/10/istio-book-info-demo/)를 활용하면서 Istio의 기능들을 익혀나갔다.

## 슬랙 커뮤니티에 질문하기!

Istio 시험 공부를 할수록 이해 되는 것들도 있었지만, 어떤 것들은 도저히 이해가 안 되서 끙끙 댔던 것들이 있다 ㅠㅠ 구글링을 하고 Istio 문서를 뒤져봐도 답이 안 보였는데 그러다가 문득 Istio도 다른 오픈소스들처럼 슬랙 커뮤니티가 있지 않을까? 하고 떠오르게 되었고 찾아냈다!! ㅎㅎ

슬랙 커뮤니티는 CNCF 슬랙 커뮤니티와 Istio 슬랙 커뮤니티 둘다 활용했다!!

CNCF 슬랙에는 `#istio-exam-study-group`이란 채널이 있는데, ICA 시험 후기들이 올라와서 시험 꿀팁들을 전수 받을 수 있다 ㅎㅎ

![](/images/development/istio/cncf-slack-ica-exam-group.png)
나도 시험 후기를 올려뒀다 ㅎㅎ
{: .small .text-center .gray }

Istio 슬랙은 좀더 Istio 개념적인 이야기들이 오고가는 곳으로 잘 모르는 개념이 있을 때는 이곳의 `#general` 채널에 물어봤다!!

![](/images/development/istio/istio-slack-my-question-1.png)

![](/images/development/istio/istio-slack-my-question-2.png)

![](/images/development/istio/istio-slack-my-question-3.png)

질문을 올려두면 어느샌가 친절하신 고수분들이 나타나 답변을 주곤 하신다 💙

## Killacoda로 모의 테스트!

CKA 시험 볼 때는 [killer.sh](https://killer.sh/)라고 CKA 시험과 거의 동일한 환경과 난이도에서 보는 모의 테스트가 있었는데, Istio 시험에서는 그런게 없었다 ㅠㅠ

어찌저찌 커뮤니티의 도움을 받아 [killercoda.com](https://killercoda.com/)라는 사이트에 모의 테스트가 있다는 정보를 입수해 시험 직전에 몇몇 문제를 풀어왔다.

일단 확실히 점검에는 확실히 도움이 된다!!! 본인도 시험 직전에 풀어보고 내가 부족한 걸 찾았을 정도 ㅎㅎ

➡️ https://killercoda.com/nashwan

Istio 모의 테스트가 2개 있는데, 요 테스트가 더 퀄리티가 좋았다!! 👍

## 공부한 거 블로그 글로 쓰기!

ㅋㅋㅋ 요즘엔 노션(notion)을 많이 활용하고 있긴 한데, 그래도 공부한 걸 블로그 글로 적어야 온전히 이해한 것 같은 느낌이 들어서 이번에 Istio 시험 준비할 때도 공부한 내용들을 블로그 글로 정리해뒀다 ㅎㅎ (노션은 나만 보는 거라 꽤 대충 적는 느낌?? 사실상 그냥 메모장 같은 공간이다 ㅋㅋ)

이번에는 24.02.02부터 24.03.24 시험 직전까지 **총 21개** 포스트를 작성했다!!! Istio를 공부하는 다른 사람들에게 내 글이 조금이나마 도움이 되면 좋겠다 ㅎㅎ

[전체 목록]

- [Install Istio and Addons(Prometheus, Kiali)](https://bluehorn07.github.io/2024/02/02/install-istio-and-addons/)
- [Istio ‘helloworld’ 데모](https://bluehorn07.github.io/2024/02/05/istio-helloworld-demo/)
- [Istio `Bookinfo` 데모](https://bluehorn07.github.io/2024/02/10/istio-book-info-demo/)
- [Istio: Ingress Gateway](https://bluehorn07.github.io/2024/02/14/istio-ingress-gateway/)
- [Istio: Egress Gateway](https://bluehorn07.github.io/2024/02/15/istio-egress-gateway/)
- [Istio TLS Network 관련 사전 지식](https://bluehorn07.github.io/2024/02/24/istio-pre-requisites-tls-network/)
- [Istio circular Virtual Service](https://bluehorn07.github.io/2024/02/28/istio-circular-virtual-service/)
- [Istio Security](https://bluehorn07.github.io/2024/03/03/istio-security/)
- [Istio Operator 꼼꼼히 살펴보기](https://bluehorn07.github.io/2024/03/05/istio-operator-detail-examine/)
- [Istio의 컨트롤 플레인 꼼곰히 살펴보기](https://bluehorn07.github.io/2024/03/07/istio-control-plane-detail-examine/)
- [Istio의 Authentication & Authorization](https://bluehorn07.github.io/2024/03/14/istio-authentication-and-authorization/)
- [Istio Envoy Access Logging](https://bluehorn07.github.io/2024/03/16/istio-envoy-access-logging/)
- [Istio Distributed Tracing with Jaeger](https://bluehorn07.github.io/2024/03/18/istio-distributed-tracing-jaeger/)
- [Istio Envoy Discovery Service](https://bluehorn07.github.io/2024/03/20/istio-envoy-service-discovery/)
- [Istio Revision and Canary Upgrade](https://bluehorn07.github.io/2024/03/21/istio-revision-and-canary-upgrade/)
- [Istio Service Registry](https://bluehorn07.github.io/2024/03/21/istio-service-registry/)
- [Istio 이것저것 메모들](https://bluehorn07.github.io/2024/03/22/istio-memo-collections/)
- [Istio Circuit Breaking](https://bluehorn07.github.io/2024/03/23/istio-circuit-breaking/)
- [Istio CRD 중에 지엽적인 나머지 것들 정리](https://bluehorn07.github.io/2024/03/23/istio-crd-others-memo/)
- [Istio Virtual Machine Architecture](https://bluehorn07.github.io/2024/03/23/istio-virtual-machine-architecture/)
- [istioctl 디버그 도구들](https://bluehorn07.github.io/2024/03/24/istioctl-debug-tool/)

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

나의 두서없는 이 글이 Istio를 공부하는 다른 사람들에게 조금 도움이 되었으면 좋겠다 ㅎㅎ 그럼 이만~~~
