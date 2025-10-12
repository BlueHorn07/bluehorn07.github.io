---
title: "Istio Distributed Tracing with Jaeger"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Jeager로 살펴보는 Istio의 분산 추적 시스템과 그 원리 🦌 요청의 응답이 내게 돌아오기까지의 여정을 보여주는 길잡이."
last_modified_at: 2024-03-24
---

![](https://upload.wikimedia.org/wikipedia/en/a/ab/J%C3%A4germeister_logo.svg){: .align-center style="max-width: 240px"}
얘거마이스터, 허브향이 나는 리큐르. 예거밤으로 해서 먹으면 맛있다!
{: .small .text-center .gray }

"**Distributed Tracing(분산 추적)**"이란 분산 시스템에서 요청이 시스템의 다양한 서비스를 거치며 진행될 때, 요청의 경로를 추적하고 시각화 하는 기술을 말한다.

Tracing을 통해 MSA 구조를 이루는 복잡한 분산 시스템이 서로 어떻게 연결 되어 있는지 파악하고, 요청을 디버그 하는데에 도움이 된다.

이번 포스트에서는 "Jaeger(예거)"라는 Tracing 도구를 사용해 Istio의 분산 추적에 대해 살펴보고자 한다. 전체적인 과정은 Istio 문서의 [Jeager 예제](https://istio.io/latest/docs/tasks/observability/distributed-tracing/jaeger/)를 따라한 것임을 밝힌다.

# 사전 준비

![](https://istio.io/latest/docs/examples/bookinfo/withistio.svg)

Tracing을 잘 살펴보기 위해 MSA 구조를 가진 어플리케이션을 띄우자. Istio 예제인 "bookinfo"를 띄운다. (예제 핸즈온은 [예전에 써둔 포스트](/2024/02/10/istio-book-info-demo/) 참고.)

```bash
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/platform/kube/bookinfo.yaml
```

요청은 pod에 접속해 아래 명령어로 보낼 수도 있고,

```bash
$ k exec -it ... -- sh
~ $ for i in $(seq 1 100); do curl -s -o /dev/null "http://productpage.default.svc.cluster.local:9080/productpage"; done
```

Istio `Gateway`와 `VirtualService`를 띄워서 External IP로 요청을 보낼 수도 있다.

```bash
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/networking/bookinfo-gateway.yaml
```

```bash
$ sh
sh-3.2$ for i in $(seq 1 100); do curl -s -o /dev/null "http://192.168.64.2/productpage"; done
```

요청을 100번씩 반복해서 보내는 이유는 istio의 Trace Sampling Rate 때문이다. istio 클러스터를 띄울 때 따로 설정해주지 않았다면 기본값으로 1%의 트래픽만을 샘플링 하게 된다. 그래서 최소 100번의 요청은 보내야 하는 것!

# Jaeger Addon 띄우기

![](https://www.jaegertracing.io/img/jaeger-logo.png){: .align-center style="max-width: 360px"}

Istio의 다른 addon인 "Prometheus", "Kiali"를 띄울 때 했던 것처럼 istio에서 제공하는 addon sample 코드를 사용하면 손쉽게 Jeager를 띄울 수 있다.

```bash
$ kubectl apply \
    -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/jaeger.yaml
```

Jaeger를 띄우고 나면, istio에게 앞으로 요 주소로 tracing 데이터를 보내라고 명시해줘야 한다.

```bash
istioctl \
  --set meshConfig.defaultConfig.tracing.zipkin.address=zipkin.istio-system.svc.cluster.local:9411
```

(왜 Jaeger인데, zipkin 필드에 설정하는지 당황하는 부분이다. istio 설명에 따르면 Jaeger가 zipkin의 포맷을 따르기 때문이라고 한다 ㅇㅅㅇ)

이제 Jaeger 대시보드에 접속 해보자! `istioctl`로 손쉽게 가능하다.

```bash
$ istioctl dashboard jaeger
```

![](/images/development/istio/jaeger-trace-1.png)

그리고 위의 방식대로 100번 요청을 보내면, 이렇게 내가 보낸 요청은 어떻게 처리되는지 그 흐름을 간트 차트 느낌으로 확인할 수 있다!!

캡쳐의 요청은 `productpage`라는 웹페이지에 요청을 보낸 것으로 이 요청이 처리되기 위해서 `details`와 `reviews` 워크로드에 요청을 보냈고, `reviews` 워크로드는 또 `ratings` 워크로드에 요청을 보낸 걸 확인할 수 있다!!

![](/images/development/istio/jaeger-trace-2.png)

이렇게 Kiali처럼 트래픽 그래프도 확인할 수 있고

![](/images/development/istio/jaeger-trace-3.png)

Ingress Gateway를 통해 보낸 요청도 Jaeger로 추적이 가능하다!

## Header와 Body 정보는 확인 불가

[Istio Envoy Access Logging 포스트](/2024/03/16/istio-envoy-access-logging/)에서도 그랬는데, 요청의 Header와 Body 정보는 Tracing에서도 확인 불가능 했다.

# Zipkin

Istio 예제에서 Zipkin도 세팅해볼 수 있었다.

https://istio.io/latest/docs/ops/integrations/zipkin/#installation

```bash
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/extras/zipkin.yaml
```

그런데 써보니 뭔가 Jaeger 보다 샘플링이 잘 안 되는 느낌이었고, UI/UX도 Jaeger가 좀더 마음에 들었다!!

# Tracing Config

## Trace Sampling

Istio의 샘플링 기본값은 1%(`1.0`)이다. 만약 이 값을 조정하고 싶다면, istio를 설치할 때 `IstioOperator`에 있는 Config를 조정하면 된다.

```yaml
...
spec:
  meshConfig:
    defaultConfig:
      tracing:
        sampling: 100.0
```

또는 Pod Annotation으로 Sampling Rate를 조정할 수도 있다.

```yaml
...
metadata:
  annotations:
    proxy.istio.io/config: |
      tracing:
        sampling: 10
```

### (Deprecated) Pilot Trace

과거에는 pilot의 `traceSampling` 값을 조정해서 샘플링 했으나, 이제는 `MeshConfig`의 값을 통해 조정해야 한다고 한다.

아직 두 방식 모두 지원하고 있으나, 만약 둘다 값이 정의되어 있다면 `MeshConfig`의 `tracing.sampling`으로 덮어씌워 진다고 한다.

## Extension Provider

Istio 예제에서는 Jaeger를 Trace Provider로 사용했는데, Jaeger 외에도 zipkin, Datadog, stackdriver, Apache SkyWalking 등을 사용할 수 있다고 한다.

![](https://skywalking.apache.org/images/home/ui_ServiceMesh.png){: .align-center style="max-width: 600px"}

나열된 것들은 전부다 아는 건 아니지만 Datadog은 Tracing 뿐만 아니라 모니터링과 서비스 디스커버리, Anomaly Detection 등 다양한 기능이 많고(하지만 비싸다...!) Apache SkyWakling은 이번에 처음 봤는데 Datadog과 UI도 비슷하고 기능도 거의 비슷한 것 같다. (오 좋은 것 같은데??)

# Trace Context Propagation

우리의 갓갓 Istio 서비스메쉬를 적용하기만 하면 "분산 추적"을 누릴 수 있을 것 같지만, 현실은 그렇지 않다 ㅠㅠ

Tracing을 구현하려면 요청을 받은 Application에서 요청을 추적하기 위해 디자인된 Tracing Header를 다음 요청을 포워드(forward) 해주는 코드가 구현되어 있어야 한다!!

어떤 Tracing Provider를 쓰느냐에 따라서 포워드를 구현해야 하는 헤더가 다르지만, 일단 `x-request-id` 헤더는 무조건 포워딩 해야 한다.

만약, Zipkin, Jaeger 등을 Tracing 도구로 채택 했다면 아래의 B3 헤더들을 포워드 해야 한다. (B3가 약자라거나 특별한 의미가 있는건 아니다.)

- `x-b3-traceid`
- `x-b3-spanid`
- `x-b3-parentspanid`
- `x-b3-sampled`
- `x-b3-flags`

참고로 Jaeger도 b3 헤더 형식을 따르기 때문에, 요청을 무조건 Tracing 해야 한다면 `--header "x-b3-sampled: 1"`로 세팅하면 Sample Rate를 무시하고 추적이 된다 ㅋㅋ

Istio 예제에 구현 코드도 살짝 나와있는데 Java에선 이런 느낌이다.

```java
@GET
@Path("/reviews/{productId}")
public Response bookReviewsById(
    @PathParam("productId") int productId,
    @Context HttpHeaders requestHeaders // 요청의 헤더를
) {
  // ...
  if (ratings_enabled) {
    JsonObject ratingsResponse = getRatings(
      Integer.toString(productId),
      requestHeaders // 그대로 넘겨줬다!!
    );
```

# 참고자료

- [istio: Observability Overview](https://istio.io/latest/docs/tasks/observability/distributed-tracing/overview/)
- [istio: Jaeger](https://istio.io/latest/docs/tasks/observability/distributed-tracing/jaeger/)
- [istio: Zipkin](https://istio.io/latest/docs/ops/integrations/zipkin/)
- [istio: Configure tracing using MeshConfig and Pod annotations](https://istio.io/latest/docs/tasks/observability/distributed-tracing/mesh-and-proxy-config/#using-proxyistioioconfig-annotation-for-trace-settings)
