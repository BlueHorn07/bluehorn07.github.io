---
title: "Istio 이것저것 메모들"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "ICA 시험 준비하면서 메모 해둔 짧은 것들 📝"
last_modified_at: 2024-03-22
---

# Traffic Mirroring

https://istio.io/latest/docs/tasks/traffic-management/mirroring/

`VirtualService`를 사용해 istio 워크로드에 들어온 트래픽을 미러링 할 수 있다!!

istio 문서를 잘 따라가보자 ㅎㅎ 본인은 `httpbin`으로 잘 안 되어서 `helloworld` 예제로 다시 구성해서 진행했다.

![](/images/development/istio/traffic-mirroring.png)

Kiali 상에는 50:50으로 분산된 것처럼 나온다!

여기서 놓치지 말아야 할 것은 Mirroring을 하면 response는 누구꺼를 받는지이다! istio 문서에도 나와 있는데, 원래 트래픽을 받기로 한 녀석의 response를 받고, **미러링된 트래픽을 받은 워크로드의 response는 버려진다**고 한다!

# VirtualService과 DestinationRule은 둘 중 하나만 정의할 수도 있다

Istio 예제를 보면, 늘 `VirtualService`와 `DestinationRule`을 함께 정의하는 것 같다. Istio를 처음 공부할 때는 둘을 무조건 같이 써주는 줄 알았는데, 그게 아니라 두 리소스의 쓰임과 필요에 따라 디플로이 하면 되는 거 였다!!

## VirtualService만 단독으로 있다면

https://istio.io/latest/docs/reference/config/networking/virtual-service/

- 트래픽 분할 가능 (weighted routing)
- 트래픽 Mirroring 가능
- 경로 기반 라우팅 가능 (`match.uri`)
- 헤더 및 요청의 속성을 기반으로 라우팅 가능
  - `match.headers`
  - `match.method` (GET, PUT, ...)
  - `match.queryParams`
  - `mathc.gateways`
  - ...
- 헤더 조작 가능
- Retry Policy 세팅 가능
  - ???: 삼세번은 봐준다
  - `DR`에는 outlier detection이 있다.
- CORS Policy 세팅 가능

하지만 모두 K8s Service로 존재하는 워크로드, 또는 엔드포인트가 명확한 타깃(`naver.com`)을 대상으로만 할 수 있다.
subset 레벨로는 하려면 `DR` 리소스가 반드시 정의되어야 한다.

## DestinationRule만 단독으로 있다면

https://istio.io/latest/docs/reference/config/networking/destination-rule/

- Subset 정의 🔥
  - 사실 `DR`이 `VS`랑 엮이는 부분은 요것뿐이다 ㅋㅋ
- Load Balancer
  - Simple LB
    - `RANDOM`
    - `ROUND_ROBIN`
    - `LEAST_REQUEST`
  - Consistent Hash LB
    - header 기준으로 할당
    - cookie 기준으로 할당
    - source ip 기준으로 할당
    - query param 기준으로 할당
- Connection Pool 설정
  - 최대 얼만큼의 Connection을 허용할지를 제한
- Outlier Detection
  - 대상 워크로드에 장애가 발생했을 때(`4xx`, `5xx`) 얼만큼 라우팅 대상에서 제외할 지 (Circuit Breaking)

이렇게 보니 `VS`랑 `DR`는 subset 부분을 빼곤 같이 정의될 일이 거의 없는 것 같다.

또, `VS`는 retry 발생시 얼만큼 다시 요청을 보낼지 세팅할 수 있는데, `DR`은 retry 발생시 장애가 생긴 워크로드를 얼만큼 라우팅에서 제외할지 세팅할 수 있다. (이렇게 보면 `VS`는 아픈 놈을 몇번더 때리는 나쁜(?) 녀석 인 거고, `DR`은 아픈 놈을 잠시 보호해주는 착한(?) 녀석인거네...)

# `istioctl analyze`로 실수한게 없는지 체크하기

istio 리소스를 디플로이 하고 위의 명령어를 실행하면 실수한게 없는지 알려준다!

예를 들어, 다른 네임스페이스의 K8s Service에 접근하려고 할 때, FQDN으로 안 적고 `helloworld.test`로 적어서 동작을 안 했는데, 위 명령어로 원인을 찾고 `helloworld.default.svc.cluster.local`로 바로 잡아 줬다 ㅎㅎ

```bash
$ istioctl analyze -A
```


# Istio ControlZ

![](https://istio.io/latest/docs/ops/diagnostic-tools/controlz/ctrlz.png)

```bash
$ istioctl dashboard controlz deployment/istiod.istio-system
```

`istiod`의 상태를 웹으로 볼 수 있음. 딱히 상태만 보고, Logging 수준을 요기에서 조정할 수 있는데, 그것 외에 큰 인사이트를 주진 않음.

# Mixer Deprecated

https://docs.google.com/document/d/1x5XeKWRdpFPAy7JYxiTz5u-Ux2eoBQ80lXT6XYjvUuQ/edit

Istio가 `1.5`버전에서 Mixer를 Deprecate 하면서 작성한 문서. 본인이 istio를 접했을 땐 이미 Mixer가 없어진 후라서 글을 읽어도 잘 이해는 안 됐음 ㅋㅋㅋ

대충 Mixer 때문에 CPU utilization cost가 컸고, latency에도 영향을 줬다는 정도만 이해함.
