---
title: "Istio: Gateway"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: 작성중...
---

# Gateway의 `port`와 IngressGateway의 `containerPort`

우선, 실습에서 사용했던 `Gateway`와 `VirtualService`의 yaml 파일부터 다시 보자!!

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: bookinfo-gateway
spec:
  # The selector matches the ingress gateway pod labels.
  selector:
    istio: ingressgateway # use istio default controller
  servers:
  - port:
      number: 8080 # 요기!
      name: http
      protocol: HTTP
    hosts:
    - "*"
```

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: bookinfo
spec:
  hosts:
  - "*"
  gateways:
  - bookinfo-gateway
  http:
  - match:
    - uri:
        exact: /productpage
    ...
    route:
    - destination:
        host: productpage
        port:
          number: 9080 # 요기!!
```

둘을 비교 했을 때 이해가 안 되는 부분은 바로 둘의 `port` 부분이다. `Gateway`는 `8080` 포트를, `VritualService`는 `9080` 포트로 서로 다른 포트를 명시했기 때문이다!

일단 `Gateway` 리소스의 `port`를 다음과 같이 변경해보았다.

- `8080` → `7070`으로 변경: ❌
- `8080` → `9090`으로 변경: ❌

오직 `8080`으로 설정 했을 때만, Gateway를 통한 라우팅이 정상적으로 처리되었다!! 도대체 왜일까!! /__(‘-‘)__/

<br/>

일단 결론부터 말하면, `istio-system` ns에 떠있는 default IngressGateway Pod에서 특정 포트들만 허용하기 떄문이다!!

오직 아래 두 포트의 경우로 설정했을 때만 Gateway가 제대로 동작한다.

- `8080`와 `80`(http) 포트
- `8443`와 `443`(https) 포트

일단 그 이유는 IngressGateway의 `containerPort`와 `Service`에 있다.

<br/>

IngressGateway Pod의 정보를 보면 아래와 같이 설정 되어 있다.

```yaml
# default ingress gateway pod
...
image: docker.io/istio/proxyv2:1.20.2
imagePullPolicy: IfNotPresent
name: istio-proxy
ports:
- containerPort: 15021
  protocol: TCP
- containerPort: 8080
  protocol: TCP
- containerPort: 8443
  protocol: TCP
- containerPort: 15090
  name: http-envoy-prom
  protocol: TCP
...
```

IngressGateway Service의 정보를 보면 요렇다.

```yaml
# default ingress gateway service
...
ports:
  - name: status-port
    nodePort: 31782
    port: 15021
    protocol: TCP
    targetPort: 15021
  - name: http2
    nodePort: 32514
    port: 80
    protocol: TCP
    targetPort: 8080
  - name: https
    nodePort: 32051
    port: 443
    protocol: TCP
    targetPort: 8443
...
```

즉, 우리가 만든 `Gateway`가 사용하는 default ingressGateway의 컨테이너가 `8080`과 `8443` 포트만 열려 있기 때문에, `Gateway`도 `8080` 포트를 사용한 것이다.

> The specification describes a set of ports that should be **exposed**, the type of protocol to use, SNI configuration for the load balancer, etc.

`Gateway` 리소스에 명시된 port는 ingressGateway에서 들어오는 목적으로도 사용하지만, 반대로 Gateway를 사용해 서비스를 외부에 노출할 때도 사용한다. (단, 이 경우와 Egress Gateway는 다른 개념이니 주의!)

# `host`-`port` 조합은 하나의 Gateway만 가능

이런 상상을 해보자. Bookinfo 예제를 `default` ns에도 띄우고, `test` ns에도 띄우는 거다!! 그리고 그 둘 모두 default ingressGateway를 사용하는 각각의 `Gateway` 리소스를 외부에 노출하려고 한다!

`default` ns에는 이미 bookinfo 어플리케이션을 띄웠다고 생각하고, `test` ns에 bookinfo 어플리케이션을 띄워보겠다.

```bash
$ kubectl create ns test
$ kubectl label ns test istio-injection=enabled
$ kubectl apply -n test -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/platform/kube/bookinfo.yaml
$ kubectl apply -n test -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/networking/bookinfo-gateway.yaml
```

Kiali에서 이런 에러도 확인할 수 있다.

> KIA0301: More than one Gateway for the same host port combination

> Gateway describes a load balancer operating at **the edge of the mesh** receiving incoming or outgoing HTTP/TCP connections.



https://istio.io/latest/docs/reference/config/networking/gateway/


