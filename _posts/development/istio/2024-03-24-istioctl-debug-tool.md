---
title: "istioctl 디버그 도구들"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "알아두면 도움이 되는 istioctl analyze, describe, proxy-status, proxy-config 🛠️"
last_modified_at: 2024-03-24
---


# `istioctl analyze`

생성한 Istio 리소스들(`VirtuslService`, `DestinationRule`) 등이 문제가 없는지 확인하는 용도의 명령어.

```bash
$ istioctl analyze -A
Info [IST0102] (Namespace haha) The namespace is not enabled for Istio injection. Run 'kubectl label namespace haha istio-injection=enabled' to enable it, or 'kubectl label namespace haha istio-injection=disabled' to explicitly mark it as not needing injection.
Info [IST0102] (Namespace prod) The namespace is not enabled for Istio injection. Run 'kubectl label namespace prod istio-injection=enabled' to enable it, or 'kubectl label namespace prod istio-injection=disabled' to explicitly mark it as not needing injection.
Info [IST0118] (Service istio-system/jaeger-collector) Port name jaeger-collector-grpc (port: 14250, targetPort: 14250) doesn't follow the naming convention of Istio port.
Info [IST0118] (Service istio-system/jaeger-collector) Port name jaeger-collector-http (port: 14268, targetPort: 14268) doesn't follow the naming convention of Istio port.
```

클러스터에 존재하는 네임스페이스 중에 `istio-injection` 레이블이 없는 것들도 알려주고, 어떤 두 리소스가 `hosts` 영역이 겹친다거나 하는 것들을 알려줌.

실수 한게 없는지 확인하는 가장 간단한 방법!

# `istioctl x describe`

K8s Pod이나 Service의 상태를 확인하는 용도의 명령어.

## `istioctl x describe pod <pod-name>`

```bash
$ istioctl x describe pod productpage-v1-xxxx-xxxx
Pod: productpage-v1-xxxx-xxxx
   Pod Revision: default
   Pod Ports: 9080 (productpage), 15090 (istio-proxy)
--------------------
Service: productpage
   Port: http 9080/HTTP targets pod port 9080
--------------------
Effective PeerAuthentication:
   Workload mTLS mode: STRICT
Applied PeerAuthentication:
   default.istio-system

Exposed on Ingress Gateway http://192.168.64.2
VirtualService: bookinfo
   Match: /productpage, Match: /static*, Match: /login, Match: /logout, Match: /api/v1/products*
```

## `istioctl x describe svc <svc-name>`

```bash
$ istioctl x describe svc productpage                    
Service: productpage
   Port: http 9080/HTTP targets pod port 9080

Exposed on Ingress Gateway http://192.168.64.2
VirtualService: bookinfo
   Match: /productpage, Match: /static*, Match: /login, Match: /logout, Match: /api/v1/products*
```

K8s Pod과 Service가 현재 어떤 `VirtualService`, `DestinationRule`의 규칙을 적용 받고 있는지 파악 가능.

# `istioctl proxy-status`

Envoy Proxy의 현재 상태를 확인하는 명령어. Envoy xDS와 관련된 부분인데, 현장에선 잘 안 쓰는 듯?

```bash
istioctl proxy-status
NAME                                                   CLUSTER        CDS        LDS        EDS        RDS        ECDS         ISTIOD                      VERSION
details-v1-7745b6fcf4-gmp5l.default                    Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
fortio.default                                         Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
helloworld-v1-6b887df7b7-mx57q.default                 Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
helloworld-v1-77489ccb5f-tbrpr.test                    Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
httpbin-v1-7b56f9959b-b5grp.default                    Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
httpbin-v2-65b8495dfd-t8mx2.default                    Kubernetes     SYNCED     SYNCED     SYNCED     SYNCED     NOT SENT     istiod-6b4c7d75b7-4h5f9     1.20.3
```

예를 들어, [`IstioOperator`의 Canary Upgrade](https://bluehorn07.github.io/2024/03/21/istio-revision-and-canary-upgrade/) 때문에 서로 다른 `revision`의 istio를 운영해는 상황이라, 어떤 것들이 기존 istiod를 쓰고 어떤 것들이 `revision=canary`의 istiod를 쓰는지 확인해야 한다면 유용할 듯.

![](/images/development/istio/istio-canary-revision.png)

요렇게 말이다.

# `istioctl proxy-config`

Envoy Proxy의 구성(config) 관련 정보를 살펴보는 명령어다.

가능한 대상은

```bash
  all            Retrieves all configuration for the Envoy in the specified pod
  bootstrap      Retrieves bootstrap configuration for the Envoy in the specified pod
  cluster        Retrieves cluster configuration for the Envoy in the specified pod
  ecds           Retrieves typed extension configuration for the Envoy in the specified pod
  endpoint       Retrieves endpoint configuration for the Envoy in the specified pod
  listener       Retrieves listener configuration for the Envoy in the specified pod
  log            Retrieves logging levels of the Envoy in the specified pod
  rootca-compare Compare ROOTCA values for the two given pods
  route          Retrieves route configuration for the Envoy in the specified pod
  secret         Retrieves secret configuration for the Envoy in the specified pod
```

istio 공부하면서 아래 케이스 빼고는 써본 적이 없는 듯 ㅇㅅㅇ

## `isitoctl proxy-config secret <pod-name>`

![](/images/development/istio/istioctl-proxy-config-ca-cert.png)

Envoy Proxy가 가진 CA Cert의 상태와 유효기간을 알 수 있다.