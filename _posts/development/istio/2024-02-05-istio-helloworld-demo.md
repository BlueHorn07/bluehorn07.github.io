---
title: "Istio 'helloworld' 데모"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio 첫걸음. Hello Istio! 그리고 Vritual Service, Destination Rule도 안녕!
---

{% include figure image_path="/images/meme/hell-kitty.JPG" alt="hell kitty" caption="Hell?o" %}{: .fill style="width: 400px" .align-center .text-center }

이번 포스트에서는 Istio에서 제공하는 [helloworld 예제](https://github.com/istio/istio/tree/master/samples/helloworld)를 활용해 Istio의 Virtual Service와 Destination Rule의 기능들을 직접 실험해본다. 🧪

# 사전 준비

일단 hello 예제를 띄울 네임스페이스에 label을 부여해서 envoy sidecar가 붙을 수 있도록 만들자.

```bash
kubectl label ns default istio-injection=enabled
```

그리고 앞선 포스트를 참고해 Istio와 Kiali, Prometheus Addon을 설치한다.

➡️ [Install Istio and Addons(Prometheus, Kiali)](https://bluehorn07.github.io/2024/02/02/install-istio-and-addons/)


# helloworld 어플리케이션

istio의 예제 중 가장 심플한 녀석으로 `/hello` 엔드포인트에 요청을 보내면, 자신의 버전 정보를 리턴한다.

```bash
$ curl -X GET localhost:5000/hello
Hello version: v1, instance: helloworld-v1-867747c89-n6sl2
```

우선 요 어플리케이션을 배포해보자!

```bash
$ export HELLOWORLD_1=https://raw.githubusercontent.com/istio/istio/1.20.2/samples/helloworld/helloworld.yaml
$ kubectl apply -n default -f $HELLOWORLD_1
$ kubectl apply -n default -f https://raw.githubusercontent.com/istio/istio/1.20.2/samples/helloworld/helloworld.yaml
service/helloworld created
deployment.apps/helloworld-v1 created
deployment.apps/helloworld-v2 created
```

그러면, 버전 v1, v2의 `helloworld` 어플리케이션이 배포된다.

지금 만들어진 `service/helloworld`는 v1, v2를 round robin으로 트래픽을 분산한다.

테스트를 위해 아래 명령어로 임시 Pod을 띄워서 트래픽 라우팅이 어떻게 되는지 확인해보자. (이때, k port-forward한 걸로 접속하면 안 된다. 그 이유는 [요 깃헙 이슈](https://github.com/kubernetes/kubectl/issues/881)를 참고)

```bash
$ k run nginx --image=nginx
$ k exec -it nginx -- sh
# <on some pod>
while true; do curl "http://helloworld.default:5000/hello"; done
```

![](/images/development/istio/helloworld-check-native-service.png){: .fill }
`v1` 5회, `v2` 8회. 거의 반반 정도로 라우팅 된다!
{: .small .gray .text-center }

# 안녕, Virtual Service!

그럼 이제 istio의 Virtual Service로 v1과 v2의 트래픽 비율을 조절해보자!

일단 지금은 `v1`, `v2` 둘을 같이 쓰는 K8s Service 하나만 구성했는데, 각각으로 라우팅 하는 새로운 K8s Service 둘을 구성하자.

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

그리고 아래와 같이 Vritual Service를 구성해서, `v1`에는 10% 트래픽이, `v2`에는 90%의 트래픽이 흐를 수 있도록 구성해보자.

```yaml
# simple-virtual-service
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: helloworld-vs
spec:
  hosts:
  - "helloworld.default.svc.cluster.local"
  http:
  - route:
    - destination:
        host: helloworld-v1.default.svc.cluster.local
        port:
          number: 5000
      weight: 10 # 10% 트래픽
    - destination:
        host: helloworld-v2.default.svc.cluster.local
        port:
          number: 5000
      weight: 90 # 90% 트래픽
```



## Gateway와 함께 Vritual Service를 구성해보자


