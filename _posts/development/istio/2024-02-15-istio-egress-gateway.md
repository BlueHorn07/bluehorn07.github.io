---
title: "Istio: Egress Gateway"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio로 Service Mesh를 나가는 트래픽도 모니터링 하기 💂
---

![](/images/meme/let-me-out.png){: .fill .align-center style="max-width: 400px"}

어림없지! Istio를 쓰면 서비스 메쉬를 나가는 트래픽도 모니터링 한다!!
{: .gray .small .text-center }

<br/>

# Egress Gateway란?

![](/images/development/istio/istio-network-architecture.png){: .fill }

Istio로 서비스 메쉬를 구성하면, 클러스터 **바깥으로 나가는 트래픽** 흐름도 모니터링 할 수 있다!! 그 과정에서 사용하는게 egress-gateway 컴포넌트와 istio의 `ServiceEntry` 리소스다!

# 일단 데모부터 해보죠!!

이번 예제는 Istio 공식 문서의 "[Egress Gateway](https://istio.io/latest/docs/tasks/traffic-management/egress/egress-gateway/)" 문서를 바탕으로 실험해보았다.

## egress-gateway 컴포넌트 띄우기

`istioctl`에서 `"default"` Profile로 istio를 설치했다면, egress-gateway 컴포넌트가 없는 상태일 것이다. 그래서 아래 명령어로 직접 egress-gateway 컴포넌트를 띄워줘야 한다. `"demo"` Profile로 설치 했다면, egress-gateway는 이미 있을 것이다!

```bash
$ istioctl install \
    --set "spec.components.egressGateways[0].name=istio-egressgateway" \
    --set "spec.components.egressGateways[0].enabled=true"
```

## 이제 Egress Gateway를 테스트할 Pod을 띄우자.

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/sleep/sleep.yaml
```

위의 커맨드를 실행하면, 잠만 자는 ~~잠만보~~ Pod이 하나 뜬다. 요 Pod에 접속해서 요청들을 보낼 것이다.

아래 커맨드로 Pod에 접속하고 무한 `curl` 요청을 보내보자.

```bash
$ kubectl exec -it deploy/sleep -- sh
~ $ while true; do curl -s -o /dev/null https://edition.cnn.com/politics; done
```

이때는 Kiali에서 확인해봐도, 어떤 트래픽 흐름도 보이지 않는다.

## `ServiceEntry`로 나가는 트래픽 모니터링하기

아래 명령어로 우리가 요청을 보내는 `edition.cnn.com`으로 가는 트래픽들을 모니터링 할 수 있게 해보자.

```bash
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: ServiceEntry
metadata:
  name: cnn
spec:
  hosts:
  - edition.cnn.com
  ports:
  - number: 443
    name: tls
    protocol: TLS
  resolution: DNS
EOF
```

그러고 `curl`로 무한 요청을 보내면...

![](/images/development/istio/service-entry-only.png){: .fill }

와우!! 이제는 `edition.cnn.com` 사이트로 가는 트래픽을 모니터링 할 수 있게 되었다!!

해당 사이트에 초당 몇번 쿼리하는지도 알 수 있는건 덤!!

참고로 `PassthroughCluster`는 `kubectl exec`나 `port-forward` 접속 했을 때 저렇게 뜬다.

## Egress Gateway를 통해 트래픽이 나가도록 설정

단순히 `ServiceEntry` 리소스를 만들어서 운영해도 충분 할 수도 있지만, egress-gateway 리소스를 사용해서, 클러스터 바깥으로 나가는 출구(exit point)를 지정할 수도 있다.

이를 위해 `Gateway`, `DestinationRule`, `VirtualService` 리소스를 추가로 만들어 주자.

```bash
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: istio-egressgateway
spec:
  selector:
    istio: egressgateway
  servers:
  - port:
      number: 443 # egress gateway의 포트
      name: tls
      protocol: TLS
    hosts:
    - edition.cnn.com
    tls:
      mode: PASSTHROUGH # TODO: 첨언 필요?
EOF
```

```bash
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: egressgateway-for-cnn
spec:
  host: istio-egressgateway.istio-system.svc.cluster.local
  subsets:
  - name: cnn
EOF
```

TODO: 왜 `host`가 egress의 svc인 destinationrule은 만듬?

```bash
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: direct-cnn-through-egress-gateway
spec:
  hosts:
  - edition.cnn.com
  gateways:
  - mesh
  - istio-egressgateway
  tls:
  - match:
    - gateways:
      - mesh
      port: 443
      sniHosts:
      - edition.cnn.com
    route:
    - destination:
        host: istio-egressgateway.istio-system.svc.cluster.local
        subset: cnn
        port:
          number: 443
  - match:
    - gateways:
      - istio-egressgateway
      port: 443
      sniHosts:
      - edition.cnn.com
    route:
    - destination:
        host: edition.cnn.com
        port:
          number: 443
      weight: 100
EOF
```

TODO: 얜 또 왜 이렇게 복잡함?

![](/images/development/istio/egress-gateway-with-service-entry.png){: .fill }


# Out-bound 트래픽에 대한 정책 설정하기

https://istio.io/latest/docs/tasks/traffic-management/egress/egress-control/#envoy-passthrough-to-external-services

istio가 적용된 pod들의 모든 in/out 트래픽은 해당 Pod의 envoy sidecar를 통해 제어된다. istio는 unknown host/service로 향하는 트래픽의 경우, **그냥 흘려보내는(Passthrough)게 기본 동작**이다. 그러나 때에 따라서는(예를 들어 강한 네트워크 보안 조건이 필요하다면) 이런 unknown 목적지로 가는 트래픽을 제어해야 할 때가 있다.

Istio에서는 밖으로 나가는 트래픽에 3가지 정책을 세울 수 있다.

1. mesh 내에 정의되지 않은 목적지로 가는 트래픽을 모두 허용하기
2. `ServiceEntry`를 구성한 외부 목적지만 허용하기
3. 특정 IP 대역으로 가는 외부 트래픽만 허용하기

## isito 클러스터의 트래픽 제어 옵션

istio 클러스터를 구성할 때, IstioOperator의 `meshConfig.outboundTrafficPolicy.mode` 옵션을 조정해서 외부로 나가는 트래픽을 전면 허용할지, 등록한 것만 허용할지 정할 수 있다.

- `ALLOW_ANY` (default)
- `REGISTRY_ONLY`

`ALLOW_ANY`는 전면 허용이므로 이미 알 것이고, 아래 커맨드로 `REGISTRY_ONLY`로 옵션을 바꾼 후, 실험해보자.

```bash
$ istioctl install \
  --set "spec.components.egressGateways[0].name=istio-egressgateway" \
  --set "spec.components.egressGateways[0].enabled=true" \
  --set meshConfig.outboundTrafficPolicy.mode=REGISTRY_ONLY
```

앞에서 `edition.cnn.com` 주소에 대해서는 실험을 해봤으니, 이번에는 `google.com`을 등록하지 않은 상태에서 ping을 날려보자.

```bash
$ kubectl exec -it deploy/sleep -- sh
~ $ while true; do curl -s -o /dev/null https://google.com; done
```

![](/images/development/istio/outbound-traffic-registry-only.png){: .fill }

Kiali로 확인하보면, `BlackHoleCluster`라는 이상한 곳으로 트래픽이 모아져버린다!

아래와 같이 단일 요청을 보내면, `Connection reset by peer`라는 이상한 에러도 뱉는다.

![](/images/development/istio/outbound-traffic-reset-by-peer.png){: .fill }

이런 현상은 `google.com`을 등록해주지 않아서 그런 거라서, 아래와 같이 `ServiceEntry`로 등록하고 나면, 더이상 해당 에러는 뜨지 않는다!!

```bash
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: ServiceEntry
metadata:
  name: google
spec:
  hosts:
  - google.com
  ports:
  - number: 443
    name: tls
    protocol: TLS
  resolution: DNS
EOF
```

![](/images/development/istio/outbound-traffic-when-registered-1.png)

암튼 등록된 외부 호스트로만 트래픽을 흘려야 한다면, `REGISTRY_ONLY` 옵션으로 설정하자!!

# ServiceEntry 속성들 뜯어보기

