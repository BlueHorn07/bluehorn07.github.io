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
- Fault Injection
  - delay
  - abort
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

## 동일 host에 2개 VirtualService가 있는 경우

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

# Across the Mesh

Istio 메쉬 전체에 트래픽 규칙을 적용하는게 가능하다!! 아래 리소스들을 istio 메쉬의 root namespace, 보통 `istio-system`에 생성하면 메쉬의 모든 워크로드에 해당 규칙이 적용된다!

- `DestinationRule`
- `EnvoyFilter`
- `ProxyConfig`

이때, 리소스를 사용하는 순서는 보통 워크로드가 떠있는 곳의 네임스페이스에 존재하는 Istio 리소스스를 먼저 확인하고, 그게 없으면 root ns의 Istio 리소스를 보게 된다고 한다.

## DestinationRule

[Istio Circuit Breaking을 연습 했던 포스트](https://bluehorn07.github.io/2024/03/23/istio-circuit-breaking/)에서 작성 했던 Outlier Detection 코드를 가져와 루트 네임스페이스인 `istio-system`에 디플로이 해보자.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: default-dr
  namespace: istio-system
spec:
  host: "*"
  trafficPolicy:
    connectionPool:
      http: # 동시에 접속할 수 있는 connection을 제한
        http1MaxPendingRequests: 1
        maxRequestsPerConnection: 1
      tcp:
        maxConnections: 1
    outlierDetection:
      consecutive5xxErrors: 3 # 503 service unavailable
      interval: 1m
      baseEjectionTime: 5m
      maxEjectionPercent: 100
EOF
```

그러면 모든 네임스페이스에 Outlier Detection이 적용된다.

### DestinationRule Inheritance

root 네임스페이스에 정의한 DR과 워크로드 네임스페이스에 정의한 DR의 평가 순서는 항상 워크로드 네임스페이스의 DR이 우선권을 갖는다. 그래서 워크로드 네임스페이스에 DR이 있다면, root 네임스페이스의 DR는 곧바로 무시된다.

그런데 root 네임스페이스의 DR이 무시되지 않고, 여전히 default 케이스로 동작하길 바란다면, pilot에 `PILOT_ENABLE_DESTINATION_RULE_INHERITANCE` 환경 변수를 `true`로 설정하면 된다고 한다!

[learncloudnative의 블로그 글](https://learncloudnative.com/blog/2023-02-03-global-dr)을 참고했음을 밝힌다.

# `istioctl kube-inject`로 sidecar 주입하기

TODO: ...

# Merged Prometheus telemetry

https://istio.io/latest/docs/ops/integrations/prometheus/#option-1-metrics-merging

Istio의 Envoy Proxy들은 그들의 metric을 프로메테우스 metric 형식으로 노출함. Envoy Proxy의 메트릭은 Pod의 `15090` 포트로 노출됨.

그런데 어떤 Pod들은 isito proxy를 도입하기 이전부터 자체적으로 Prometheus 메트릭을 노출하고 있었을 수도 있음. 그렇게 되면, istio proxy의 메트릭도 노출되고 App이 원래 노출하던 메트릭도 같이 있게 됨. (이렇게 되면 뭔가 간섭이 있나봄??)

그래서 istio는 친절하게도(?) 기존 App이 노출하는 프로메테우스 메트릭이 있다면 그걸 병합(merge)해서 "merged prometheus telemtry"의 포트인 `15020`에 쏴준다고 함.

이렇게 두 프메 메트릭을 병합하는 옵션은 IstioOp를 설치할 때 `meshConfig.enablePrometheusMerge` 옵션에 의해 설정되는데, 기본값이 `true`라서 Istio proxsy가 주입되었다면 기존 App 메트릭이 병합되게 될 것임. 그리고 위의 옵션에 따라 istio proxy가 주입된 Pod은 프메 Scrape을 위한 `prometheus.io/xxx` annot이 이것저것 붙게 된다고 함. 만약, 기존에 이미 `prometheus.io/xxx` annot이 있었다면 overwrite 됨.... ㅋㅋ

# Istio Ingress Controller

https://istio.io/latest/docs/tasks/traffic-management/ingress/kubernetes-ingress/

https://kubebyexample.com/learning-paths/istio/ingress-control

Istio에서는 외부로 서비스를 노출하기 위해 Ingress Gateway를 사용한다. 그런데, K8s에서는 서비스를 노출할 때 (보통은) `Ingress`를 사용한다. 공부를 하면서 늘 이 두 개념이 상충된다고 생각하고 있었는데, Istio Ingress Gateway와 K8s Ingress를 같이 엮어서 쓸수도 있는 것 같다.

Istio 문서에 따르면, Istio Ingress Controller라는게 있어서 이걸로 `IngressClass` 리소스를 생성하면 되는 것 같다.

```yaml
apiVersion: networking.k8s.io/v1
kind: IngressClass
metadata:
  name: istio
spec:
  controller: istio.io/ingress-controller
```

그리고 K8s Ingress를 만들때, `ingressClassName: istio`로 적으면 Istio Ingress Controller의 제어를 받는 듯 하다.

Istio [Mesh Config](https://istio.io/latest/docs/reference/config/istio.mesh.v1alpha1/)에도 관련된 필드들이 있다.

- `ingressClass`
- `ingressService`
- `ingressControllerMode`
  - `DEFAULT`: istio ingress controller를 k8s 클러스터 전체의 default ingress controller로 사용
  - `STRICT`(default): `ingressClass`에 명시한 값과 동일한 annot 또는 `ingressClassName`을 가진 Ingress만 처리
- `ingressSelector`

TODO: ICA 시험 때 안 물어볼 것 같아서 핸즈온은 스킵!

# Kubernetes Gateway API

이번에 istio 공부하면서 처음 본 K8s 리소스임. K8s의 Ingress API의 기능을 보완한 새로운 K8s API라고 함.

K8s Gateway API를 사용할 때, istio 리소스를 어떻게 호환 시켜야 하는지도 istio 문서에 잘 나와 있음. 잠깐 살펴보니 Istio 리소스를 하는게 아니라 전부 Gateway API 리소스를 정의하는 걸로 대체되는 것 같음.

- Istio `Gateway` ➡️ Gateway `Gateway`
- Istio `VirtualService` ➡️ Gateway `HTTPRoute`

TODO: 요건 아직 K8s 표준 API가 아닌 것 같아서 ICA 시험에 안 나올 것 같음 ㅋㅋㅋ 나중에 다시 찾아보는 걸로!

# Istio Reserved Ports

https://istio.io/latest/docs/ops/deployment/requirements/

외부로 직접 노출된 port만 적음. internal port는 생략! 대부분이 `150xx` 포트임.

## Envoy Proxy

- `15001`: Envoy outbound
- `15006`: Envoy inbound
- `15021`: Health checks
- `15020`: Merged Prometheus telemetry from Istio agent, Envoy, and application	
  - 왜 프메 메트릭을 병합(?)한 포트가 있는지는 요 포스트의 위쪽에 문단에 적어둠!
- `15090`: Envoy Prometheus telemetry	

## Istiod

- `15014`: Control plane monitoring
- `15017`: Webhook container port
- `15010`, `15012`: XDS and CA services

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

# Killercoda ICA 모의 시험

https://killercoda.com/ica

요걸로 뜨는 가상 환경이 너무너무 느리고 답답하지만, 내가 공부하다가 놓친게 없는지 찾는데 도움이 됐다!!

요걸로 `istioctl kube-inject`랑 Istio의 `WorkloadGroup` 등의 리소스를 내가 놓쳤다는 걸 깨달았음!!

특히 요 모의 테스트가 좋다!

https://killercoda.com/nashwan
