---
title: "Istio Envoy Logging"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: ""
last_modified_at: 2024-03-20
---

# 사전 준비

```bash
k apply -f https://raw.githubusercontent.com/istio/istio/master/samples/helloworld/helloworld.yaml
```

# Enable Access Logging

## Telemetry API

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: default-ns-logging
  namespace: default
spec:
  accessLogging:
    - providers:
      - name: envoy
```

```bash
$ k exec -it ... -- sh
~ $ curl http://helloworld.default.svc.cluster.local:5000/hello
---
$ k exec -it helloworld... -c istio-proxy -- sh
...
[2024-03-18T23:08:48.020Z] "GET /hello HTTP/1.1" 200 - via_upstream - "-" 0 59 52 51 "-" "curl/8.6.0" "a0698764-a04d-48c8-8914-045808d19898" "helloworld.default.svc.cluster.local:5000" "10.42.0.4:5000" inbound|5000|| 127.0.0.6:60873 10.42.0.4:5000 10.42.0.6:48352 outbound_.5000_._.helloworld.default.svc.cluster.local default
...
[2024-03-18T23:10:51.528Z] "GET /hello HTTP/1.1" 200 - via_upstream - "-" 0 59 38 37 "-" "curl/7.88.1" "5c3abfa9-c806-490b-a5ec-a6659f866490" "helloworld.default.svc.cluster.local:5000" "10.42.0.5:5000" outbound|5000||helloworld.default.svc.cluster.local 10.42.0.4:55914 10.43.135.30:5000 10.42.0.4:41438 - default
...
```

특정 네임스페이스 또는 Workload에 대해서만 Access Log를 살펴봐야 한다면 요 방식을 사용할 수 있다.

## Mesh Config

```yaml
spec:
  meshConfig:
    accessLogFile: /dev/stdout
```

또는

```bash
$ istioctl install --set meshConfig.accessLogFile=/dev/stdout
```

## 둘다 써보기

일단 Telemetry API 방식과 Mesh Config 방식 둘다 독립적이다! 처음엔 둘다 세팅을 해야 로깅이 되는 줄 알았는데, 그게 아니라 둘 중 어떤 방법을 써도 Envoy Logging이 된다!

다만, 차이점은 Telemetry API는 네임스페이스와 워크로드 Selector를 통해 로깅 대상을 선택할 수 있어서 좀더 세밀한(fine-grained) 로그 제어가 가능하다.

# Access Log Format

Access Log가 어떤 정보들을 보여주는지 좀더 살펴보자.

## 트래픽의 방향 구분 가능

Acess Log는 inbound, outbound 트래픽의 흐름을 기록한다.

- inbound
  - 워크로드로 들어오는 트래픽
- outbound
  - 워크로드에서 나가는 트래픽

## upstream, downstream 정보 확인 가능

upstream, downstream 요것들은 envoy의 용어다.

먼저 "upstream"은 Envoy sidecar가 받은 요청을 forward 받는 호스트를 말한다. 기존 워크로드 컨테이너가 upstream 역할을 한다.

"downstream"은 Envoy sidecar에 요청을 보내고, 응답을 받는 호스트를 말한다.

- upstream
  - host
    - ex: `10.42.0.4:5000`
  - cluster
    - ex: `inbound|5000||`, `outbound|5000||helloworld.default.svc.cluster.local`
    - 형식은 `{direction}|{port}|{subset}|{hostname}`
    - "cluster"가 Envoy에서는 "트래픽을 보낼 수 있는 대상 워크로드의 집합"을 말하는데, 흐음 Istio Envoy Log에서는 동일한 의미로 쓰였는지 잘 모르겠다 🤔
  - local address
    - ex: `127.0.0.6:60873`
- downstream
  - local address
    - ex: `10.42.0.4:5000`
  - remote address
    - ex: `10.42.0.6:48352`

## Request 기본 정보 확인 가능

- Method & Path
  - ex: `GET /hello HTTP/1.1`
- X-ENVOY-UPSTREAM-SERVICE-TIME
  - 요청이 Envoy에 도착한 후 업스트림 서비스에 의해 처리가 완료 되고 Envoy로 돌아올 때까지 걸린 전체 시간 (밀리초 단위)
- X-FORWARDED-FOR (XFF 헤더)
  - 요청이 proxy 서버나 로드 밸런서와 같은 중간 서버를 통과할 때, 원래 클라이언트의 IP 주소를 보존하는데 사용
- User Agent
  - ex: `curl/8.6.0`
- X-REQUEST-ID
  - ex: `a0698764-a04d-48c8-8914-045808d19898`
  - 개벌 HTTP 요청을 식별하고, 그것들을 추적(tracing) 하기 위해 사용하는 헤더
- Authority
  - ex: `helloworld.default.svc.cluster.local:5000`
  - 요청이 전송되는, 요청이 타깃으로 하는 호스트 이름과 포트 번호가 담긴 헤더.
  - HTTP/1.x의 `Host` 헤더와 유사한 역할.
  - 서버 또는 프록시가 여러 도메인을 서비스 하는 경우, 예를 들어 `example.com`과 `another.com`이 IP 주소를 공유하는 경우, 요청이 어떤 도메인을 대상으로 하는지 구분하는데 사용된다.

## Response 정보 확인 가능

- Response Code
  - ex: `200`
- Response Code Details
  - Response Code가 생성된 구체적인 이유나 조건을 설명. 디버깅 용도.
- Response Flag
  - ex: `via_upstream`
    - 요건 "The response code was set by the upstream"라는 의미.
  - 요청이 어떻게 처리되었는지 표시.
  - [Envoy 문서](https://www.envoyproxy.io/docs/envoy/latest/configuration/http/http_conn_man/response_code_details)에서 전체 목록 확인 가능.

## Header 정보와 Body 정보는 확인 불가

Envoy Acess Log는 Header와 Body 정보는 보여주지 않는다.

```bash
curl http://helloworld.default.svc.cluster.local:5000/hello --header "haha: hoho"
```

요렇게 요청을 보내도, header 정보는 확인 불가능!

단, 몇몇 헤더(`X-FORWARDED-FOR`, `X-REQUEST-ID`, `AUTHORITY`)는 Acess Log에 표시된다.

# 참고자료

- [istio: Envoy Access Logs](https://istio.io/latest/docs/tasks/observability/logs/access-log/)
- [arisu1000님의 블로그](https://arisu1000.tistory.com/27872)
