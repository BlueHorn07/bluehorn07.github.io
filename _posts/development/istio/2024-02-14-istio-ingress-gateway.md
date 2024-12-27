---
title: "Istio: Ingress Gateway"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio의 edge proxy인 Ingress Gateway에 대해 꼼꼼하게 살펴보기! 🕵️ Mesh Gateway랑은 뭐가 다른 걸까?
last_modified_at: 2024-03-22
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

요렇게 istio `Gateway` 리소스를 만들고 나면, 이제 요 Gateway 리소스를 사용하는 `ViratualService`를 하나 만들어줘야 한다.

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
  http:
    ...
```

이 `VirtualService`는 Ingress Gateway로 들어온 트래픽을 어떤 곳으로 라우팅 할지를 규칙이 적혀 있다. 이 규칙들을 Ingress Gateway의 Envoy Proxy에서 트래픽이 나갈 때 evaluate 된다.

### Mesh gateway와 비교

Ingress GW와 (곧 살펴볼) Egress GW를 구성하는 Envoy Proxy는 특수한 역할을 한다. 그래서 이들에 대해서는 `spec.gateways`에 직접 `Gateway` 리소스를 명시해서 그들이 사용하는 `VirtualService`를 정의하는 것이다.

물론 이렇게 쓰는 경우도 있다.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
spec:
  gateways:
  - bookinfo-gateway
  - mesh # mesh gateway도 같이 적용할 수도 있다!
  http:
    ...
```

이때 "mesh gateway"란 Ingress/Egress GW를 구성하는 Envoy Proxy를 제외한 Istio Service Mesh 내에 존재하는 다른 모든 Envoy Proxy를 말한다.

처음 `VirtualService`를 배울 때는 Service Mesh 내부에서의 트래픽만을 제어하기 때문에 `spec.gateways`에 아무것도 적지 않았다. 아무것도 안 적으면 `mesh` gateway가 기본으로 들어간다.

## Ingress Gateway를 추가하고 싶다!

흐음... 방법을 찾는게 꽤 어려웠다... (╥﹏╥)

일단 Istio 설치 때 자동으로 설치하는 Ingress Gateway 외에 추가 Ingress GW를 띄우고 싶다면, `IstioOperator` 리소스를 수정해줘야 한다.

Istio를 설치하는 방법도 [`istioctl`, helm chart로 설치하기, Istio Operator로 설치하기](/2024/02/02/install-istio-and-addons/) 등 여러 방법이 있지만, 여기서는 `istioctl`와 Istio Operator를 사용해서 Ingress GW를 추가해보겠다.

일단, 우리가 `IstioOperator` 리소스를 수정 했을 때, 그걸 반영해서 띄워 줄 IstioOperator 컨트롤러를 띄워야 한다.

```bash
$ istioctl operator init
```

요렇게 하면, 이제 `istio-operator`라는 ns에 IstioOperator 컨트롤러가 뜬다! (그런데 이름은 `istio-operator-xxx`이니 주의!)

![](/images/development/istio/istioctl-operator-install.png)

좋다! 이제 `IstioOperator`를 수정해보자! 아래 명령어로 리소스를 확인한다.

```bash
$ kubectl get istiooperator -n istio-system
```

그리고 존재하는 `IstioOperator` 리소스를 수정하는데...

![](/images/development/istio/istio-operator-edit-1.png){: .fill style="width: 100%; max-width: 600px" }

먼저 `annotations`에 있는 `install.istio.io/ignoreReconcile`을 `false`로 바꿔준다. <span class="red">요걸 바꾸지 않으면 리소스를 수정해도 IstioOperator 컨트롤러가 반영을 안 한다!</span>

![](/images/development/istio/istio-operator-edit-2.png){: style="width: 100%; max-width: 400px;"}

그리고 `spc.components.ingressGateways` 항목에서 새로운 Ingress GW를 추가해주자!

이때 주의할 점은 `label.istio`도 같이 설정해줘야 한다! 만약 설정하지 않으면, default ingress gateway랑 같은 `label.istio = "ingressgateway"`를 쓰게 되어서 두 Ingress GW가 제대로 분리 되지 않게 된다!

![](/images/development/istio/check-new-ingress-gateway.png){: .fill style="max-width: 100"}

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

둘을 비교 했을 때 이해가 안 되는 부분은 바로 둘의 `port` 부분이다. `Gateway`는 `8080` 포트를, `VirtualService`는 `9080` 포트로 서로 다른 포트를 명시했기 때문이다!

일단 `Gateway` 리소스의 `port`를 다음과 같이 변경해보았다.

- `8080` → `7070`으로 변경: ❌
- `8080` → `9090`으로 변경: ❌

오직 `8080`으로 설정 했을 때만, Gateway를 통한 라우팅이 정상적으로 처리되었다!! 도대체 왜일까!! /__(‘-‘)__/

<br/>

일단 결론부터 말하면, `istio-system` ns에 떠있는 default IngressGateway Pod에서 특정 포트들만 허용하기 때문이다!!

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

![](/images/meme/duplicated.jpeg){: .fill style="max-height:300px" .align-center }

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

## Ingress로 특정 host 주소로 들어오는 트래픽 핸들링하기

우리가 `Gateway` 리소스를 처음 만들 땐, 아래와 같이 `hosts`를 와일드카드 `"*"`로 설정 했었다.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: bookinfo-gateway
spec:
  servers:
  - ...
    hosts:
    - "*" # 와일드카드!
```

그런데 요기에 특정 host 주소를 넣어서 해당 주소로만 오는 트래픽을 받을 수도 있다. 아래와 같이 말이다!

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: bookinfo-gateway
spec:
  servers:
  - ...
    hosts:
    - "mybookinfo.com" # host를 명시했다!
```

이렇게 할 경우, `mybookinfo.com`라는 주소로 들어온 트래픽만 `Gateway` 리소스가 핸들링하게 된다!

본인은 로컬 맥북에서 Rancher Desktop으로 K8s 클러스터를 돌려 실험하고 있는데, 이렇게 `Gateway` 리소스에 Host를 명시하게 되면, `curl` 명령어에 `-HHost` 속성을 더 넣어줘야 했다. (`--header "Host: ..."`로 넣어줘도 된다!)

```bash
# https://istio.io/latest/docs/tasks/traffic-management/ingress/ingress-control/#using-node-ports-of-the-ingress-gateway-service
$ export INGRESS_NS=istio-system
$ export INGRESS_NAME=istio-ingressgateway
$ export INGRESS_HOST="192.168.64.2"

# 성공!
$ curl -s -I --header "Host:mybookinfo.com" "http://$INGRESS_HOST/productpage"
HTTP/1.1 200 OK
server: istio-envoy

# 실패...
$ curl -s -I "http://$INGRESS_HOST/productpage"
HTTP/1.1 404 Not Found
```

로컬 맥북이라 Internal IP가 붙어서 Host가 없기 때문에 `Host` 헤더가 값을 넣어서 우회하는 방법을 썼다.

### 하나의 Ingress가 여러 Host로 들어오는 트래픽 받기

`Gateway`에 host를 지정할 수 있다는 사실은 하나의 Ingress로 여러 서비스를 노출할 수 있다는 걸 말하기도 한다!! 방금 `mybookinfo.com`을 위해 만들었던 `Gateway`, `VirtualService`는 그대로 두고, helloworld 워크로드를 노출하기 위한 리소스를 추가로 만들어 띄워보자.

그러고 요청을 보내보면

```bash
$ curl -s --header "Host:mybookinfo.com" "http://$INGRESS_HOST/productpage"
# ...bookinfo web html...

$ curl -s --header "Host:myhelloworld.com" "http://$INGRESS_HOST/hello"
Hello version: v2, instance: helloworld-v2-77f98b76b-zq5ch
```

요렇게 Host 정보에 따라서 서로 다른 워크로드의 결과를 받을 수 있다!! ✌️

## egress-gateway를 ingress 용도로 사용할 수 있을까?

![](/images/meme/sonny.png){: .fill .align-center style="max-width: 360px"}

뿌슝빠슝?? 이 무슨 변태적인(?) 생각인가 ㅋㅋ 나가는(egress) 곳으로 들어올(ingress) 수 있을까? 뭔 이런 생각인가 ㅋㅋ

일단 답은 "불가능"이다!! ❌

![](/images/development/istio/egress-gateway-is-cluster-ip.png)

그 이유는 Egress GW 리소스는 K8s Service가 `LoadBalancer` 타입이 아니라 `ClusterIP`이기 때문이다!!

생각해보면, 원래도 클러스터 밖으로 나가는 건 할 수 있는데, 그걸 Egress GW라는 이름으로 트래픽을 제어하려는 것 뿐이다. 그래서 Egress GW는 `ClusterIP`를 사용해도 충분하다!!

<hr/>

# 맺음말

드디어 Istio의 Ingress Gateway와 `Gateway` 리소스도 쭉 살펴봤다!! 이게 둘다 "Gateway"라는 워딩을 쓰니까 Istio 처음 공부할 때 진짜 헷갈리게 만들었다 O=('-'Q)

게다가 Ingress Gateway는 K8s Pod이라도 있는데, `Gateway` 리소스는 Pod도 없지... 결국 `VirtualService`, `DestinationRule`, `Gateway` 모두 Istio가 Envoy Proxying 할 때 사용하는 Config 리소스라는 걸 알기 전까진 정말 헷갈렸다.

자! 이젠 바로 위에서 봤던 "Egress Gateway"를 살펴볼 차례다!! 그럼 안녕~~
