---
title: "Istio 이것저것 메모들"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "ICA 시험 준비하면서 메모 해둔 짧은 것들 📝"
last_modified_at: 2024-03-22
---

# `istioctl analyze`로 실수한게 없는지 체크하기

istio 리소스를 디플로이 하고 위의 명령어를 실행하면 실수한게 없는지 알려준다!

예를 들어, 다른 네임스페이스의 K8s Service에 접근하려고 할 때, FQDN으로 안 적고 `helloworld.test`로 적어서 동작을 안 했는데, 위 명령어로 원인을 찾고 `helloworld.default.svc.cluster.local`로 바로 잡아 줬다 ㅎㅎ

```bash
$ istioctl analyze -A
```

# Traffic Management 관련

## Traffic Mirroring

https://istio.io/latest/docs/tasks/traffic-management/mirroring/

`VirtualService`를 사용해 istio 워크로드에 들어온 트래픽을 미러링 할 수 있다!!

istio 문서를 잘 따라가보자 ㅎㅎ 본인은 `httpbin`으로 잘 안 되어서 `helloworld` 예제로 다시 구성해서 진행했다.

![](/images/development/istio/traffic-mirroring.png)

Kiali 상에는 50:50으로 분산된 것처럼 나온다!

여기서 놓치지 말아야 할 것은 Mirroring을 하면 response는 누구꺼를 받는지이다! istio 문서에도 나와 있는데, 원래 트래픽을 받기로 한 녀석의 response를 받고, **미러링된 트래픽을 받은 워크로드의 response는 버려진다**고 한다!

## VirtualService과 DestinationRule은 둘 중 하나만 정의할 수도 있다

Istio 예제를 보면, 늘 `VirtualService`와 `DestinationRule`을 함께 정의하는 것 같다. Istio를 처음 공부할 때는 둘을 무조건 같이 써주는 줄 알았는데, 그게 아니라 두 리소스의 쓰임과 필요에 따라 디플로이 하면 되는 거 였다!!

### VirtualService만 단독으로 있다면

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

### DestinationRule만 단독으로 있다면

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

## Delegate VirtualService

https://istio.io/latest/docs/reference/config/networking/virtual-service/#Delegate

Istio에서 `VirtualService` 리소스를 작은 VS로 분할하는 방법이 Delegate(위임) VS임.

이 Delegate VS는 루트 VS에 의해 참조되며, `hosts` 필드가 비어있다는게 특징임.

```yaml
# 루트 VS
...
spec:
  hosts:
  - "bookinfo.com"
  gateways:
  - mygateway
  http:
  - match:
    - uri:
        prefix: "/productpage"
    delegate: # 다른 VS에 위임한다!
       name: productpage
       namespace: nsA
  - ...
---
kind: VirtualService
metadata:
  name: productpage
  namespace: nsA
spec:
  # host 필드가 없다는게 특징!!
  http:
  - match:
     - uri:
        prefix: "/productpage/v1/"
    route:
    - destination:
        host: productpage-v1.nsA.svc.cluster.local
  - route:
    - destination:
        host: productpage.nsA.svc.cluster.local
```

# 동일 host에 2개 VirtualService가 있는 경우

```yaml
# duplicate-host-vs.yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: helloworld-v1
spec:
  hosts:
  - helloworld.default.svc.cluster.local
  http:
    - route:
      - destination:
          host: helloworld.default.svc.cluster.local
          subset: v1
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: helloworld-v2
spec:
  hosts:
  - helloworld.default.svc.cluster.local
  http:
    - route:
      - destination:
          host: helloworld.default.svc.cluster.local
          subset: v2
```

일단 `VS`는 생성되지만, `istioctl analyze`로 확인해보면 바로 에러가 난다!

```bash
$ istioctl analze
Error [IST0109] (VirtualService default/helloworld-v1) The VirtualServices default/helloworld-v1,default/helloworld-v2 associated with mesh gateway define the same host */helloworld.default.svc.cluster.local which can lead to undefined behavior. This can be fixed by merging the conflicting VirtualServices into a single resource.
Error [IST0109] (VirtualService default/helloworld-v2) The VirtualServices default/helloworld-v1,default/helloworld-v2 associated with mesh gateway define the same host */helloworld.default.svc.cluster.local which can lead to undefined behavior. This can be fixed by merging the conflicting VirtualServices into a single resource.
Error: Analyzers found issues when analyzing namespace: default.
See https://istio.io/v1.20/docs/reference/config/analysis for more information about causes and resolutions.
```

즉, Istio는 `hosts` 필드가 겹치는 `VirtualService`를 권장하지 않으며, 이런 경우 둘 중에 어떤 `VS`가 적용될지는 복불복인 것 같다. 힘들여 작성한 `VS`의 규칙이 무시되길 원하지 않는다면, `hosts` 필드가 겹치지 않도록 주의할 것!!

만약 `VS`가 거대해서 몇개의 파일로 분할해야 한다면, 바로 위에 적은 Delegate(위임) VS를 활용해보자!

# Mesh Config

https://istio.io/latest/docs/reference/config/istio.mesh.v1alpha1/

## enableAutoMtls

istio는 서비스메쉬를 도입하면, 바로 워크로드-워크로드 사이 통신이 mTLS로 암호화 된다는게 장점이다! 요 `enableAutoMtls` 옵션은 그것에 대한 옵션으로 만약 mTLS로 통신하는 걸 원하지 않다면 요걸 `false`로 세팅하면 되는 것 같다.

일단 기본값은 `true`이니 istio 서비스 메쉬를 적용하면 mTLS 통신이 바로 적용 되는 것!!

## enableEnvoyAccessLogService

Envoy에 `gRPC` Access Log를 활성화 하려면 켜는 옵션이라고 한다. Envoy Access Log는 `accessLogFile` 옵션에 경로를 넣어주면 활성화 되는데, gRPC 로그까지 수집하고 싶을 때 키는 옵션인 듯??

처음에 Envoy Access Log 켜려면 요걸 `true`로 만들어줘야 되는 줄 알고 좀 헤맸다 ㅋㅋ

# Istio Ingress Controller

https://istio.io/latest/docs/tasks/traffic-management/ingress/kubernetes-ingress/

https://kubebyexample.com/learning-paths/istio/ingress-control

Istio에서는 외부로 서비스를 노출하기 위해 Ingress Gateway를 사용한다. 그런데, K8s에서는 서비스를 노출할 때 (보통은) `Ingress`를 사용한다. 늘 이 두 개념이 상충된다고 생각하고 있었는데,  

이거 로컬에서 Ingress 세팅 가능한지 체크하기.

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
