---
title: "Istio Circular Virtual Service"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: 꼬리에 꼬리는 무는 Virtual Service를 구성 했을 때 어떻게 동작할까?
---

![](https://upload.wikimedia.org/wikipedia/commons/f/fa/Ouroboros.png){: .fill }

Istio의 `VirtualService`의 동작을 이해하려고 할 때, 이해가 안 되는게 `spec.hosts`에도 host 주소를 적고, `destination` 속성에도 host 주소를 적는 거 였다.

그런 나에게 이런 질문이 떠올랐는데...

- "만약 자기 자신을 destination으로 하는 VirtualService를 띄우면, 이 VirtualService는 무한번 evaluation 되는 걸까?"
- "만약 VirtualService로 host A의 트래픽을 host B로 보내고, 또 다른 VirtualService로는 host B -> host A로 트래픽을 보낸다면, 이 경우도 evaluation이 무한번 되는 걸까?"

# 다시 helloworld 예제를 띄우자

```bash
$ kubectl apply -n default -f https://raw.githubusercontent.com/istio/istio/1.20.2/samples/helloworld/helloworld.yaml
service/helloworld created
deployment.apps/helloworld-v1 created
deployment.apps/helloworld-v2 created
```

그런데 이때 `svc/helloworld`가 있으니 트래픽 분산이 뭔가 잘 안 되는 것 같아서 `svc/helloworld`는 지워두자!!

```bash
$ kubectl delete svc/helloworld
```

하고 `v1`, `v2` 버전에 API call을 보낼 수 있도록 각각의 K8s Service 리소스를 만들어두자.

```bash
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

아, 그리고 helloworld `v1`, `v2`에 트래픽을 흘려보낼 테스트용 Pod도 하나 띄우자.

```bash
$ k run nginx --image=nginx
$ k exec -it nginx -- sh
# <on some pod>
while true; do curl "http://helloworld.default:5000/hello"; done
```

좋았어!! 준비는 끝났다!!

# 무한 Evaluation이 일어날지 실험

## 자기 자신으로 라우팅 하는 Virtual Service

"만약 자기 자신을 destination으로 하는 VirtualService를 띄우면, 이 VirtualService는 무한번 evaluation 되는 걸까?"

```yaml
# self-destination.yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: self-destination
spec:
  hosts:
  - "helloworld-v1.default.svc.cluster.local"
  http:
  - route:
    - destination:
        host: helloworld-v1.default.svc.cluster.local
```

요런 VirtualService를 만들고, `nginx` Pod에 접속해서 트래픽을 흘려보자.

```bash
$ k exec -it nginx -- sh
# <on some pod>
while true; do curl "http://helloworld-v1.default.svc.cluster.local:5000/hello"; done
```

![](/images/development/istio/self-destination-virtual-service.png)

흐음...? 자기 자신으로 트래픽이 잘 가고 있다!

## 서로에게 트래픽을 보내 버리려는 Virtual Service

![](/images/meme/duplicated.jpeg)

```yaml
# circular-traffic-shift.yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: traffic-shift-v1-to-v2
spec:
  hosts:
  - "helloworld-v1.default.svc.cluster.local"
  http:
  - route:
    - destination:
        host: helloworld-v2.default.svc.cluster.local
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: traffic-shift-v2-to-v1
spec:
  hosts:
  - "helloworld-v2.default.svc.cluster.local"
  http:
  - route:
    - destination:
        host: helloworld-v1.default.svc.cluster.local
```

![](/images/development/istio/circular-traffic-shift-virtual-service.png)

뭔가 예상하기로는 `v1`이랑 `v2`가 번갈아 가면서 나올 줄 알았는데, `vs/traffic-shift-v1-to-v2`에 명시한 대로 트래픽이 오직 `v2`로만 잘 가고 있다!!

# 결론

https://istio.io/latest/docs/ops/diagnostic-tools/proxy-cmd/


VirtualService가 Envoy에서 어떻게 동작하는지 설명 필요할 듯


# 추가 실험: 그럼 Self-destination Virtual Service은 언제 씀??


