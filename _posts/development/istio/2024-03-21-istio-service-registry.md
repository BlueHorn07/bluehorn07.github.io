---
title: "Istio Service Registry"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Istio에서 접근 가능한 엔드포인트를 모아둔 Registry를 확인하는 방법. `ServiceEntry`로 Registry에 엔드포인트 추가하기."
last_modified_at: 2024-03-22
---

# Service Registry란

> Istio maintains an internal service registry containing the set of services, and their corresponding service endpoints, running in a service mesh. Istio uses the service registry to generate Envoy configuration.

Istio는 메쉬에서 접근 가능한 모든 K8s Service 엔드포인트를 모아 Service Registry라는 곳에 저장해둔다.
(참고로 non-istio 워크로드의 K8s Svc 엔드포인트도 Service Registry에 들어가긴 한다.)

요 Service Registry를 확인하려면 아래 커맨드를 통해 istio `pilot`의 `debug/registryz` 엔드포인트에 접근하면 된다.

```bash
$ export PILOT_NAMESPACE=istio-system
$ export PILOT_POD_NAME=$(kubectl get po -n $PILOT_NAMESPACE -l istio=pilot -o jsonpath='{.items[0].metadata.name}')

$ kubectl exec -n $PILOT_NAMESPACE $PILOT_POD_NAME -- curl localhost:15014/debug/registryz | jq '.[].hostname' -r
---
details.default.svc.cluster.local
helloworld.default.svc.cluster.local
httpbin.default.svc.cluster.local
istiod.istio-system.svc.cluster.local
jaeger-collector.istio-system.svc.cluster.local
kiali.istio-system.svc.cluster.local
kube-dns.kube-system.svc.cluster.local
kubernetes.default.svc.cluster.local
metrics-server.kube-system.svc.cluster.local
...
```

# `ServiceEntry`에 등록된 host는 Service Registry에 등록된다

> Istio does not provide service discovery, although most services are automatically added to the registry by Pilot adapters that reflect the discovered services of the underlying platform (Kubernetes, Consul, plain DNS). Additional services can also be **registered manually** using a `ServiceEntry` configuration.

[Istio Egress Gateway](/2024/02/15/istio-egress-gateway/)를 살펴볼 때 만난 `ServiceEntry`라는 리소스는 Istio 워크로드가 메쉬 외부로 나갈 때의 트래픽을 모니터링 하게 한다.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: ServiceEntry
metadata:
  name: google
spec:
  hosts:
  - www.google.com
  ...
  location: MESH_EXTERNAL
EOF
```

이때, `hosts` 필드에 적힌 값은 Istio Service Registry에 등록된다.

# Registry Only

`IstioOperator`의 MeshConfig 중엔 `OutboundTrafficPolicy.Mode`라고 outbound 트래픽에 대한 옵션이 있다. 이걸 `REGISTRY_ONLY`로 설정하면 Istio Service Registry에 등록된 주소로만 요청을 보낼 수 있다.

```bash
# Registry에 등록된 것만
$ istioctl install --set meshConfig.outboundTrafficPolicy.mode=REGISTRY_ONLY

# 전체 허용
$ istioctl install --set meshConfig.outboundTrafficPolicy.mode=ALLOW_ANY
```

```bash
~ $ curl https://naver.com
curl: (35) Recv failure: Connection reset by peer
```
naver는 Istio Service Registry에 등록되지 않아서 접근이 불가!

이전의 [Istio Security 포스트](/2024/03/03/istio-security/)의 `PeerAuthentication` 부분에도 적어뒀는데, 요 `ServiceEntry`는 "istio ➡️ non-istio" 방향의 트래픽 중 non-istio external endpoint로 가는 트래픽을 허용하는 방법이다.

# 참고자료

- [trstringer의 포스트](https://trstringer.com/get-istio-internal-service-registry/)
  - Istio Service Registry를 확인하는 방법을 요 포스트에서 찾았다 💡
