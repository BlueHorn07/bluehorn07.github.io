---
title: "Istio Envoy Logging"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: ""
last_modified_at: 2024-03-18
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

...

# Access Log Format

Access Log가 어떤 정보들을 보여주는지 좀더 살펴보자.

## inbound, outbound 구분 가능

Envoy 용어들 잠깐 정리

- upstream
  - host
  - cluster
  - local address
- downstream
  - local address
  - remote address

## Request 기본 정보 확인 가능

## Response 정보 확인 가능


```bash
[2024-03-18T23:08:48.020Z] "GET /hello HTTP/1.1" 200 - via_upstream - "-" 0 59 52 51 "-" "curl/8.6.0" "a0698764-a04d-48c8-8914-045808d19898" "helloworld.default.svc.cluster.local:5000" "10.42.0.4:5000" inbound|5000|| 127.0.0.6:60873 10.42.0.4:5000 10.42.0.6:48352 outbound_.5000_._.helloworld.default.svc.cluster.local default
...
[2024-03-18T23:10:51.528Z] "GET /hello HTTP/1.1" 200 - via_upstream - "-" 0 59 38 37 "-" "curl/7.88.1" "5c3abfa9-c806-490b-a5ec-a6659f866490" "helloworld.default.svc.cluster.local:5000" "10.42.0.5:5000" outbound|5000||helloworld.default.svc.cluster.local 10.42.0.4:55914 10.43.135.30:5000 10.42.0.4:41438 - default
```

# 참고자료

- https://istio.io/latest/docs/tasks/observability/logs/access-log/