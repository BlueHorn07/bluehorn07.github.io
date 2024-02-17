---
title: "Istio: Ingress Gateway"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio의 edge proxy인 Ingress Gateway에 대해 꼼꼼하게 살펴보기! 🕵️
---

# Ingress Gateway란?

![](/images/development/istio/istio-network-architecture.png){: .fill }

bookinfo 예제에서 만든 `VirtualService`와 `DestinationRule`은 K8s 클러스터 내부에서 흐르는 트래픽을 제어하는 리소스이다.

그럼 istio 네트워크 상에서 어플리케이션을 K8s 클러스터 외부로 노출 하려고 한다면, 어떻게 해야 할까??

## K8s Ingress랑 이름이 비슷하네요

일단 가장 간단한 방법은 K8s Service나 K8s Ingress를 사용하는 것이다. 그러나 이렇게 접근하면, `VirtualService`와 `DestinationRule`로 설정한 네트워크 규칙이 적용 되지 않는다.

왜냐하면, K8s Ingress와 K8s Service에서 들어오는 네트워크 트래픽은 `istiod`가 아니라 K8s 컨트롤플레인의 `kube-proxy`를 통해 제어되기 때문이다!

## Istio에는 Ingress Gateway라는게 있습니다.

Istio의 `vs`, `dr`의 규칙을 따르게 하려면 방법은 간단하다.

> Envoy Proxy가 네트워크 요청을 하게 하라!

그래서 Istio는 Envoy Proxy 컨테이너 하나만 있는 Ingress Gateway라는 이름의 리소스를 정의했다!!!

Istio는 클러스터 외부에서 들어오는 모든 요청을 요 Ingress Gateway에 위임한다.

![](/images/development/istio/istio-ingressgateway.png){: .fill }

위의 그림을 보면, Ingress Gateway도 "Envoy Proxy"가 있는 걸 알 수 있다. 그러나 다른 Application과 다르게 Service의 컨테이터는 존재하지 않는다!! 오직 Envoy Proxy 컨테이너만 단독으로 존재한다!!

## Ingress Gateway는 어떻게 쓰나요?

요 ingress gateway라는 리소스를 사용하려면, istio의 [`Gateway`](https://istio.io/latest/docs/reference/config/networking/gateway/
)라는 리소스를 정의 하는데

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
      number: 8080 # 요 포트 번호에 대한 설명은 뒤에서 한다!
      name: http
      protocol: HTTP
    hosts:
    - "*"
```

- 어떤 ingress gateway로 트래픽을 받을지
- 어떤 포트에서 (뒤에서 더 자세히 설명)
- 어떤 호스트에서 요청이 들어올 건지 (요것도 뒤에서 더 자세히 설명!)

에 대한 내용을 적어준다.

요렇게 istio `Gateway` 리소스를 만들고 나면, 이제 `VirtualService` 리소스의 `spec.gateway` 항목에 요 리소스를 적어준다.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: bookinfo
spec:
  hosts:
  - "*"
  gateways: # 요기에 적어준다!
  - bookinfo-gateway
```

## Ingress Gateway를 추가하고 싶다!

흐음... 방법을 찾는게 꽤 어려웠다... (╥﹏╥)

일단 Istio 설치 때 자동으로 설치하는 Ingress Gateway 외에 추가 Ingress GW를 띄우고 싶다면, `IstioOperator` 리소스를 수정해줘야 한다.

Istio를 설치하는 방법도 [`istioctl`, helm chart로 설치하기, Istio Operator로 설치하기](https://bluehorn07.github.io/2024/02/02/install-istio-and-addons/) 등 여러 방법이 있지만, 여기서는 `istioctl`와 Istio Operator를 사용해서 Ingress GW를 추가해보겠다.

일단, 우리가 `IstioOperator` 리소스를 수정 했을 때, 그걸 반영해서 띄워 줄 IstioOperator-controller를 띄워야 한다.

```bash
$ istioctl operator init
```

요렇게 하면, 이제 `istio-operator`라는 ns에 IstioOperator-controller가 뜬다! (그런데 이름은 `istio-operator-xxx`이니 주의!)

![](/images/development/istio/istioctl-operator-install.png)

좋다! 이제 `IstioOperator`를 수정해보자! 아래 명령어로 리소스를 확인한다.

```bash
$ kubectl get istiooperator -n istio-system
```

그리고 존재하는 `IstioOperator` 리소스를 수정하는데...

![](/images/development/istio/istio-operator-edit-1.png){: .fill style="max-width: 500px"}

먼저 `annotations`에 있는 `install.istio.io/ignoreReconcile`을 `false`로 바꿔준다. <span class="red">요걸 바꾸지 않으면 리소스를 수정해도 IstioOperator-controller가 반영을 안 한다!</span>

![](/images/development/istio/istio-operator-edit-2.png){: .fill style="max-width: 500px"}

그리고 `spc.components.ingressGateways` 항목에서 새로운 Ingress GW를 추가해주자!

이때 주의할 점은 `label.istio`도 같이 설정해줘야 한다! 만약 설정하지 않으면, default ingress gateway랑 같은 `label.istio = "ingressgateway"`를 쓰게 되어서 두 Ingress GW가 제대로 분리 되지 않게 된다!

![](/images/development/istio/check-new-ingress-gateway.png){: .fill style="max-width: 600px"}

야호!! 새로운 Ingress GW가 잘 떴다!! ദ്ദി ˉ͈̀꒳ˉ͈́ )✧

<hr/>

# Ingress Gateway 더 자세히 살펴보기

## Gateway의 `port`와 IngressGateway의 `containerPort`

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

## `host`-`port` 조합은 하나의 Gateway만 가능

![](/images/meme/duplicated.jpeg){: .fill style="height:300px" .align-center }

이번에는 Bookinfo 예제를 `default` ns에도 띄우고, `test` ns에도 띄워보자!! 📘📙

그리고 둘다 모두 istio의 default ingress-gateway를 사용해 `Gateway` 리소스를 외부에 노출 해보자!

`default` ns에는 이미 bookinfo 어플리케이션을 띄웠다고 생각하고, `test` ns에 bookinfo 어플리케이션을 띄워보겠다.

```bash
$ kubectl create ns test
$ kubectl label ns test istio-injection=enabled
$ kubectl apply -n test -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/platform/kube/bookinfo.yaml
$ kubectl apply -n test -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/networking/bookinfo-gateway.yaml
```

그런데 이렇게 띄우게 되면, 이상하게도 `test` ns에 띄운 bookinfo 어플리케이션에는 접속이 안 된다!!!

그리고 Kiali에서 이런 에러도 확인할 수 있다.

![](/images/development/istio/gateway-one-host-one-port.png)

> [KIA0301: More than one Gateway for the same host port combination](https://kiali.io/docs/features/validations/#kia0301---more-than-one-gateway-for-the-same-host-port-combination)

즉, 하나의 ingress-gateway에서 어떤 Gateway가 (`host`, `port`)의 쌍을 사용하고 있으면, 나중에 생긴 다른 Gateway는 그 쌍을 사용하지 못한다!

이 경우 해결하려면,

- 다른 ingress-gateway를 사용하기
- ingress-gateway의 다른 containerPort 사용하기 (ex: `9443`)

의 방법을 사용해야 한다.

## egress-gateway를 ingress 용도로 사용할 수 있을까?

![](/images/meme/sonny.png){: .fill .align-center style="max-width: 400px"}

뿌슝빠슝?? 이 무슨 변태적인(?) 생각인가 ㅋㅋ 나가는(egress) 곳으로 들어올(ingress) 수 있을까? 뭔 이런 생각인가 ㅋㅋ

일단 답은 "불가능"이다!! ❌

![](/images/development/istio/egress-gateway-is-cluster-ip.png)

그 이유는 Egress GW 리소스는 K8s Service가 `LoadBalancer` 타입이 아니라 `ClusterIP`이기 때문이다!!

생각해보면, 원래도 클러스터 밖으로 나가는 건 할 수 있는데, 그걸 Egress GW라는 이름으로 트래픽을 제어하려는 것 뿐이다. 그래서 Egress GW는 `CluterIP`를 사용해도 충분하다!!}

<hr/>

# 맺음말

드디어 Istio의 Ingress Gateway와 `Gateway` 리소스도 쭉 살펴봤다!! 이게 둘다 "Gateway"라는 워딩을 쓰니까 Istio 처음 공부할 때 진짜 헷갈리게 만들었다 O=('-'Q)

게다가 Ingress Gateway는 K8s Pod이라도 있는데, `Gateway` 리소스는 Pod도 없지... 결국 `VirtualService`, `DestinationRule`, `Gateway` 모두 Istio가 Envoy Proxying 할 때 사용하는 Config 리소스라는 걸 알기 전까진 정말 헷갈렸다.

자! 이젠 바로 위에서 봤던 "Egress Gateway"를 살펴볼 차례다!! 그럼 안녕~~
