---
title: "Istio Envoy Discovery Service"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "xDS로 Envoy가 Istio와 싱크를 맞추는 방법."
last_modified_at: 2024-03-20
---


![](/images/development/istio/istioctl-proxy-status.png)

`istioctl`의 명령어를 살펴보던 중에 `istioctl proxy-status`를 치니 아래와 같이 `ooDS`로 끝나는 컬럼의 표가 보이는 걸 발견 했다. 무슨 내용인지 궁금해서 좀더 찾아봤고, 그 결과 요게 Envoy의 Discovery Service(이하 DS)과 관련 있다는 사실을 깨달았다. 이번 포스트에는 Envoy의 DS에 대해 살펴보고자 한다.

이 주제를 이해하는데 [arisu1000님의 블로그 글](https://arisu1000.tistory.com/27872)이 많은 도움이 되었습니다. 글로나마 감사의 인사를 드립니다. 🙇

# Envoy에 Config를 설정하는 법

![](https://github.com/envoyproxy/artwork/raw/main/PNG/Envoy_Logo_Final_PANTONE.png){: .align-center style="max-width: 400px"}

Envoy도 간단하게 생각하면, nginx, apache 같은 proxy 서버이기 때문에 Config가 필요하다. Envoy Config는 2가지 방식으로 설정 할 수 있는데

- 정적(static)으로 설정하기
  - Envoy Proxy가 뜰 때 읽는 것.
  - 변경 사항이 있다면 Envoy Proxy Restart가 필요함.
- **API 통해서 동적으로 설정하기**
  - Istio에서 쓰는 방식!

2가지 방식이 있다. 이중 API 통해 정적으로 Envoy Config를 설정하는게 Istio에서 채택한 방식이다. 그리고 이걸 xxx Discovery Service, 줄여서 xDS라고 지칭한다.

동적으로 설정하는 xDS 방식은 Envoy가 Config를 관리하는 서버에 Call을 하면서 지속적으로 Config를 싱크하는 방식이다. Istio에서는 Config 서버가 `istiod`일 것이다. Envoy를 Istio 맥락 안에서 살펴보고 있으니 앞으로 Config 서버를 `istiod`라고 특정하겠다.

# Envoy xDS 방식

Envoy가 xDS 방식으로 구성을 싱크하기 위해서 `istiod`는 xDS API를 구현해 열어둬야 한다. 그 종류로는 

- CDS(Cluster DS)
  - Envoy 용어로 
- EDS(Endpoint DS)
  - Envoy 용어로 Cluster를 이루는 멤버를 endpoint라고 말함. 
- RDS(Route DS)
  - 특정 경로로 들어온 트래픽을 어디로 보내야 하는지에 대해
- LDS(Listener DS)
  - 특정 포트로 들어온 트래픽을 어디로 보내야 하는지에 대해
- ECDS(Extension Configuration DS)

Envoy xDS에는 이것 외에도 몇개가 더 있는데, istio의 `istioctl proxy-status`에서는 저 다섯 가지 xDS API만 보여주고, 아마 이 다섯 가지만 구현된 것 같다. Envoy xDS의 전체 목록을 보고 싶다면, [Envoy 공식 문서](https://www.envoyproxy.io/docs/envoy/latest/api-docs/xds_protocol#rpc-services-and-methods-for-each-variant)에 나와 있다.

만약 `istiod`를 직접 구현해야 한다면, Envoy의 요 xDS API들을 구현해줘야 할 것이다.

# Istio Envoy가 싱크하는 과정

## 시작 단계(Bootstrap)

Envoy 인스턴스는 먼저 bootstrap 구성을 로드한다. 초기 구성에는 xDS 서버(`istiod`)의 주소와 초기 연결을 위한 정보가 포함되어 있다.

## CDS & EDS 세팅

먼저 Envoy가 proxy하는 집합(Envoy: 클러스터)의 워크로드(Envoy: 엔드포인트)의 상태를 체크하고 구성을 싱크한다.

순서는 CDS로 클러스터 정보를 싱크하고, CDS로 싱크한 정보를 활용해 EDS 싱크를 진행한다.

만약 워크로드가 헬스체크를 제공한다면, 해당 헬스체크를 사용해 상태를 체크한다.

## RDS & LDS 세팅

`istiod`로부터 RDS/LDS 세팅을 싱크 한다. 이 과정이 완료 되면, Envoy는 트래픽을 받을 수 있는 상태가 된다!

## 기존 커넥션을 대체하기 시작

만약 이미 Envoy가 트래픽을 받고 있는 중에 xDS 싱크가 이뤄질 경우도 있을 것이다. 꼼꼼한 Envoy는 xDS 싱크 상황에서도 커넥션 유실 없이 코드와 설정을 reload 할 수 있도록 구성 되어 있다.

# 참고자료

- [arisu1000님의 블로그](https://arisu1000.tistory.com/27872)
- [alice_k106님의 블로그](https://blog.naver.com/alice_k106/222000680202)
- [Envoy: Service Discovery](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/upstream/service_discovery#arch-overview-service-discovery-types-eds)
- [Envoy: Initialization](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/operations/init)

