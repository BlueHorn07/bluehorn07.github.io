---
title: "Istio Circuit Breaking"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Circuit Breaker로 문제 있는 워크로드를 제외시키기 🚧"
last_modified_at: 2024-03-23
---

# 사전 준비

```bash
$ kubectl apply -n default -f https://raw.githubusercontent.com/istio/istio/1.20.2/samples/helloworld/helloworld.yaml
service/helloworld created
deployment.apps/helloworld-v1 created
deployment.apps/helloworld-v2 created
```

아 그런데 위의 helloworld v1/v2 둘다 resource limits가 안 걸려 있어서 직접 `k edit deploy`로 수정해 resource limits를 넣어주자. (`k edit pod ...`으로 하려고 하니 Pod에서는 resource 수정이 안 됨 ㅠㅠ)

```yaml
...
      resources:
        requests:
          cpu: "100m" # default
        limits:
          cpu: "100m"
          memory: "200Mi"
...
```

그리고 각 버전별 K8s svc도 추가로 deploy 하자.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: v1
kind: Service
metadata:
  name: helloworld-v1
  labels:
    app: helloworld
    service: helloworld
    version: v1
spec:
  ports:
  - port: 5000
    name: http
  selector:
    app: helloworld
    version: v1
---
apiVersion: v1
kind: Service
metadata:
  name: helloworld-v2
  labels:
    app: helloworld
    service: helloworld
    version: v2
spec:
  ports:
  - port: 5000
    name: http
  selector:
    app: helloworld
    version: v2
EOF
```

```bash
$ kubectl get svc
NAME            TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
helloworld      ClusterIP   10.43.135.30    <none>        5000/TCP   ...
helloworld-v1   ClusterIP   10.43.70.96     <none>        5000/TCP   ...
helloworld-v2   ClusterIP   10.43.248.127   <none>        5000/TCP   ...
```

Helloworld 워크로드를 모두 디플로이 하고 나면, 테스트용 Pod에 접속해서 아래 명령어를 날리면서 응답을 모니터링 하고 있자.

```bash
$ k exec ... -it -- sh
~ $ while true; do curl "http://helloworld.default.svc.cluster.local:5000/hello"; sleep 1; done;
```

# Circuit Breaker란

트래픽을 받는 워크로드 집합 중에 장애가 발생했을 때, 해당 워크로드를 트래픽 라우팅에서 제외함으로써 네트워크의 안정성을 높이는 패턴이다.

Istio에서 Network Resiliency(탄력성)과 Fault Tolerance를 갖추기 위해 사용한다.

# fortio로 Pod 부하 생성하기

사전 작업에서 만들어둔 Helloworld 워크로드 중에 v1에 강제로 부하를 줄 것이다. 이를 위해 로드 테스트 툴인 `fortio`를 디플로이 하자.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: v1
kind: Pod
metadata:
  name: fortio
  labels:
    app: fortio
spec:
  containers:
  - image: docker.io/fortio/fortio:latest_release
    imagePullPolicy: IfNotPresent
    name: fortio
    ports:
    - containerPort: 8080
      name: http-fortio
    - containerPort: 8079
      name: grpc-ping
EOF
```

그리고 아래 명령어를 입력해 helloworld v1 워크로드에 부하를 주자.

```bash
$ kubectl exec -it fortio  -- /usr/bin/fortio load -c 2 -t 30m -qps 0 http://helloworld-v1.default:5000/hello
```

이때, `-c 2`는 concurrent thread 2개에, `-t 30m`은 30분 동안, `-qps 0`은 기다림 없이 계속 요청을 보내도록 만든다.

그러면 이제 1초마다 `curl` 보내던게 급격히 딜레이가 걸리기 시작한다!!! 반면에 `helloworld-v2`에서 오는 응답은 바로바로 받게 된다.

![](/images/development/istio/fortio-load-test.png)

시뻘게진 `helloworld-v1`... ❤️‍🔥

# `DestinationRule`로 Outlier Detection 수행하기

이제 이렇게 부하 때문에 장애가 생긴 워크로드를 자동으로 제외할 수 있도록 `DestinationRule`의 Outlier Detection 기능을 통해 구현해보자!! 아래와 같이 `DestinationRule`을 작성해 디플로이 한다.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: hellowrold
spec:
  host: helloworld.default.svc.cluster.local
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

이렇게 할 경우, `helloworld-v1` 워크로드에서 connection pool 제한으로 인해 `503`에러를 받게 되고, 처음에 `while ... curl ...`로 보내던 호출도 이제는 `helloworld-v2`의 응답만을 받게 된다!!

# 참고자료

- [istio: Circuit Breaking](https://istio.io/latest/docs/tasks/traffic-management/circuit-breaking/)
- [itnp님의 블로그](https://itnp.kr/post/istio-circuit-break)
