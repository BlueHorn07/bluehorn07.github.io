---
title: "Install Knative Serving"
toc: true
toc_sticky: true
categories: ["Kubernetes", "knative"]
excerpt: "좌충우돌! Knative 설치 수난기. Istio를 네트워킹 백엔드로 사용하는 Knative ⛵️"
last_modified_at: 2025-06-10
---

이 글을 작성하는 25년 6월엔 Knative [`v1.18.0`](https://github.com/knative/serving/releases/knative-v1.18.0) 버전이 최신 버전입니다.
Apple M3 맥북(Sequoia 15.5)에서 Rancher Desktop(`1.18.2`) 통해 Local Kubernetes Cluster 구성하여 진행하였습니다. K8s 버전은 `v1.31.0`입니다.
{: .notice }

# Check Knative Compatibility

![](https://avatars.githubusercontent.com/u/35583233?v=4)
{: style="width: 200px" }

Knative를 설치하거나 버전 업그레이드 할 때는 K8s와의 호환성을 고려해야 한다. Knative의 [Release Support](https://github.com/knative/community/blob/main/mechanics/RELEASE-SCHEDULE.md) 문서를 보면, 호환되는 minimum k8s version이 명시되어 있다.

이번 글에서 사용하는 Knative 버전은 `1.18.0` 버전이다. 요 버전은 최소 K8s `1.31` 버전을 요구한다. 아마, K8s 버전이 올라가면서 K8s API 규격이나 기능들이 추가되거나 변경 되는데, Knative 버전마다 해당 K8s API 버전부터 지원하는 것들이 있는가보다.

# Using YAMLs

## Install knative CRDs

```bash
kubectl apply -f https://github.com/knative/serving/releases/download/knative-v1.18.0/serving-crds.yaml
```

## Install Serving Core

```bash
kubectl apply -f https://github.com/knative/serving/releases/download/knative-v1.18.0/serving-core.yaml
```

위의 명령어를 통해 Knative의 serving core 컴포넌트를 설치한다. core 컴포넌트는 `knative-serving` 네임스페이스에 디플로이 된다.

```bash
$ kgp -n knative-serving
NAME                         READY   STATUS    RESTARTS   AGE
webhook-5676f857c4-vp4bw     1/1     Running   0          75s
autoscaler-d57d974cb-8fnxb   1/1     Running   0          75s
controller-b8b47f9b9-jd8fw   1/1     Running   0          75s
activator-5d7f9fc58d-bwtk2   1/1     Running   0          75s
```

디플로이된 각 컴포넌트들이 어떤 기능을 하는지 자세한 내용은 Knative Architecture에 대해 따로 정리한 포스트를 참고하자.

## Deploy a Knative Service

YAML 파일로 정의해서 Knative Service를 디플로이 하는 방법도 있는데, 여기에선 Knative CLI인 `kn`을 사용해서 연습해보겠다.

```bash
kn service create hello \
    --image ghcr.io/knative/helloworld-go:latest \
    --port 8080 \
    --env TARGET=World
```

`kn` CLI로 Knative 리소스를 제어하는게 편리할 때가 있다.

예를 들어, Knative의 Service는 K8s Service와 이름이 똑같아서 `kubectl`로 리소스롤 확인할 때 `k get service`로 하면 안 되고, `k get services.serving.knative.dev`로 풀네임을 적어줘야 한다. <small>(최근에 알게 된 건데, `k get ksvc`를 해도 됩니다!)</small>

`kn`을 사용하면 `kn service list`만 하면 되니 훨씬 간결하다!

본인은 `IngressNotConfigured`에 `Ingress has not yet been reconciled.` 라는 메시지로 Knative Service가 생성되다가 말았다... 🤔 요 에러는 Knative Service가 사용할 Ingress가 제대로 구성되지 않았을 때 발생한다. 이때의 Ingress는 [K8s의 Ingress](https://kubernetes.io/ko/docs/concepts/services-networking/ingress/) 리소스가 아니라 Istio의 Ingress Gateway와 같이 라우팅 타깃을 동적으로 제어할 수 있는 Ingress 컴포넌트를 말한다. 자세한 내용은 별도의 포스트에서 정리해보겠다. 일단은 마음 불편하게 하는 에러 메시지를 해결하기 위해 아래의 단계를 수행하자.

## Install Istio

Knative의 네트워킹 백엔드를 구성하기 위해 서비스 메쉬인 Istio를 설치하자!! Istio를 설치하는 방법으로 여러 가지가 있지만, 여기서는 `istioctl`로 진행한다. Istio를 설치하는 다른 방법이 궁금하다면, "[Install Istio and Addons](/2024/02/02/install-istio-and-addons/)" 포스트를 참고하자.

```bash
istioctl install -y
```

위의 명령어로 istio를 설치한다. `istio-system` 네임스페이스에 `istiod`와 `istio-ingressgateway`가 하나 설치된 걸 확인한다.
참고로 저는 `istioctl==1.26.1` 버전을 사용하고 있어서, istio도 `1.26.1` 버전으로 설치 되었습니다!

## Install net-istio

Istio를 설치 했다고 끝나는게 아니라, Knative에서 Istio의 리소스와 CRD API를 사용할 수 있도록 `net-istio`라는 별도의 컴포넌트를 설치해야 한다. ~~설치할 것도 많다;;~~

`net-istio`는 Knative의 Networking Layer를 이루는 컴포넌트로 Istio의 Ingress GW를 통해 트래픽을 Knative Service 리소스로 라우팅 하고, Istio의 `DestinationRule`과 `VirtualService`를 사용해 Knative의 각 Revision으로 트래픽을 분배한다. 게다가 트래픽 기준으로 auto-scaling을 하게 될 경우도 Istio의 트래픽 메트릭을 기반으로 동작하게 된다.

암튼 `net-istio`에 대한 내용은 별도 포스트에서 좀더 다뤄보도록 하고 일단 `net-istio`를 아래 커맨드로 설치하자.

```bash
kubectl apply -f https://github.com/knative/net-istio/releases/download/knative-v1.18.0/net-istio.yaml
```

`knative-serving` 네임스페이스에 기존 Knative의 컨트롤 컴포넌트외에 `net-istio`의 컨트롤 컴포넌트들이 디플로이 된다.

```
NAME                                    READY   STATUS    RESTARTS   AGE
webhook-5676f857c4-vp4bw                1/1     Running   0          3h43m
autoscaler-d57d974cb-8fnxb              1/1     Running   0          3h43m
controller-b8b47f9b9-jd8fw              1/1     Running   0          3h43m
activator-5d7f9fc58d-bwtk2              1/1     Running   0          3h43m
net-istio-webhook-7dcd9cd55d-5nfsz      1/1     Running   0          13m
net-istio-controller-67c556df5f-g4j9q   1/1     Running   0          13m
```

## Knative Service Re-deploying

Networking Layer인 Istio도 세팅 했겠다. 다시 Knative Serving을 디플로이 해보자.

```bash
kn service create hello \
    --image ghcr.io/knative/helloworld-go:latest \
    --port 8080 \
    --env TARGET=World
---
  ...
  0.014s The Route is still working to reflect the latest desired specification.
  0.023s ...
  0.037s Configuration "hello" is waiting for a Revision to become ready.
  3.525s ...
  3.531s Ingress has not yet been reconciled.
  3.566s Ready to serve.

Service 'hello' created to latest revision 'hello-00001' is available at URL:
http://hello.default.svc.cluster.local
```

이제는 Ingress 셋업에서 막히지 않고 Knative 리소스가 잘 디플로이 된다 ㅎㅎ

## Clean-up

진행한 순서의 역순으로 리소스를 정리한다. 참고로 Destroy 할 때 약간의 딜레이가 있으니, 리소스가 잘 삭제 되었는지 확인하고 다음 단계를 진행하자.

```bash
# destroy Knative Service
kn service delete hello

# destroy net-istio
kubectl delete -f https://github.com/knative/net-istio/releases/download/knative-v1.18.0/net-istio.yaml

# destroy istio (optional)
istioctl uninstall --purge

# destroy knative core components
kubectl delete -f https://github.com/knative/serving/releases/download/knative-v1.18.0/serving-core.yaml

# remove knative CRDs
kubectl delete -f https://github.com/knative/serving/releases/download/knative-v1.18.0/serving-crds.yaml
```


# Using Knative Operator

Knative를 Operator 패턴으로 디플로이할 수도 있다. Knative 공식 문서에서도 해당 내용을 확인할 수 있습니다!

➡️ [Knative: Install by using the Knative Operator](https://knative.dev/docs/install/operator/knative-with-operators/)

## Install Knative Operator

일단 Knative Operator를 먼저 디플로이 하자.

```bash
kubectl apply -f https://github.com/knative/operator/releases/download/knative-v1.18.1/operator.yaml
```

`knative-operator`라는 네임스페이스에 knative operator의 리소스가 생성된다.

```bash
NAME                                READY   STATUS    RESTARTS   AGE
operator-webhook-7cc7b89bdf-b5z76   1/1     Running   0          47s
knative-operator-fdbbd86d4-rqnvg    1/1     Running   0          47s
```

## Deploy Knative Serving

아래의 YAML을 디플로이 하여, `KnativeServing` 리소스를 `knative-service` 네임스페이스에 띄운다.

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: knative-serving
---
apiVersion: operator.knative.dev/v1beta1
kind: KnativeServing
metadata:
  name: knative-serving
  namespace: knative-serving
```

적용이 된 후에 아래 명령어로 확인해보면,

```bash
kubectl get knativeservings -A
---
NAMESPACE         NAME              VERSION   READY   REASON
knative-serving   knative-serving   1.18.0    True
```

그리고 `knative-serivng` 네임스페이스를 확인해보면 아래와 같이 K8s Pod들이 떠있다.

```bash
kubectl get pods -n knative-serving
---
NAME                                                     READY   STATUS      RESTARTS   AGE
controller-b8b47f9b9-b68b4                               1/1     Running     0          95s
autoscaler-d57d974cb-d78ct                               1/1     Running     0          95s
webhook-5676f857c4-h5wwv                                 1/1     Running     0          94s
net-istio-controller-67c556df5f-5h4cl                    1/1     Running     0          92s
activator-5d7f9fc58d-t9jpp                               1/1     Running     0          96s
net-istio-webhook-7dcd9cd55d-xwgwk                       1/1     Running     0          92s
autoscaler-hpa-54586c6544-gkm2t                          1/1     Running     0          94s
storage-version-migration-serving-serving-1.18.0-lpbwj   0/1     Completed   0          93s
```

따로 명시하지 않았는데도 Networking Layer인 `net-istio` 컴포넌트가 설치되었다!! 😲

## Deploy a Knative Serving

`kn` 명령어를 사용해 Knative Serving을 하나 디플로이 해보자.

```bash
kn service create hello \
    --image ghcr.io/knative/helloworld-go:latest \
    --port 8080 \
    --env TARGET=World
---
  ...
  0.014s The Route is still working to reflect the latest desired specification.
  0.023s ...
  0.037s Configuration "hello" is waiting for a Revision to become ready.
  3.525s ...
  3.531s Ingress has not yet been reconciled.
  3.566s Ready to serve.

Service 'hello' created to latest revision 'hello-00001' is available at URL:
http://hello.default.svc.cluster.local
```

디플로이가 잘 된다~~~

## Clean-up

디플로이 한 역순으로 지워주면 된다. 참고로 Destroy 할 때 약간의 딜레이가 있으니, 리소스가 잘 삭제 되었는지 확인하고 다음 단계를 진행하자.

```bash
# destroy Knative Service
kn service delete hello

# destroy knative serving
kubectl delete knativeserving knative-serving -n knative-serving

# destroy knative serving operator
kubectl delete -f https://github.com/knative/operator/releases/download/knative-v1.14.3/operator.yaml

# destroy istio
istioctl uninstall --purge
```

# 마무리 하며

회사에서 Knative로 Serverless Application을 배포하고 운영하는데, 이미 구축된 K8s 인프라 위에서 운영하고 있던 거라 자율보다는 타성으로 운영하고 있다는 생각이 들었다. 이번에 Knative Serving을 직접 로컬에 설치 해보면서 왜 그런지 어렴풋이 알게 된 것 같다.

Knative를 사용하면서 또 이번에 로컬에서 구축하면서 궁금했던 건 왜 Networking Layer가 별도로 필요한 걸까를 생각하게 되었다. 짧은 추측이지만 Knative가 Serverless Application을 배포/운영하는 과정에서 Native K8s 보다 더 정교한 레벨의 Network 제어와 트래픽 제어가 필요한 것 같다. 아무래도 여러 Revision에 트래픽은 원하는 비율로 라우팅 한다거나, 트래픽 기반으로 auto-scaling 하기 위해 트래픽 지표들을 수집해야 하는데, 서비스 메쉬가 있어야 가능한 기능들이다. 그래서 Networking Layer가 필요한 것 같다.

Knative는 Istio를 Default Networking Layer로 사용하고 있고, 그외에도 [Kourier](https://github.com/knative-extensions/net-kourier), [Contour](https://github.com/projectcontour/contour) 등을 Networking Layer로도 지원한다고 한다. Kourier는 Knative에서 만든 경량 서비스 메쉬인데, Istio보다 가벼운 Ingress GW를 제공한다고 한다. Contour도 서비스 메쉬의 일종으로 Envoy Proxy를 기반으로 한다. Istio, Kourier, Contour 셋다 **Envoy Proxy를 사용한다**는 공통점을 갖는다.

이 글을 적으며 Knative 공부의 첫걸음을 땠다. 요즘엔 수학과 졸업시험 준비한다고 공부에 쓸 수 있는 리소스가 자꾸만 분산되고 있다. 그래도 오랜만에 K8s에 새로운 워크로드들을 올려보고 튜닝 하면서 재밌었다 ㅎㅎ 역시 수학과 문제 푸는 것보단 이런 쪽이 잘 맞는 것 같다. ~~그러니까 컴공과를 갔겠지..?~~

<br/>

후속 포스트로 Knative Component에 대해 정리해보았습니다 🙏

➡️ [Knative Components](/2025/03/10/knative-components/)

# References

- Knative Document
  - [Installing Knative Serving using YAML files](https://knative.dev/docs/install/yaml-install/serving/install-serving-with-yaml/)
  - [Installing Istio for Knative](https://knative.dev/docs/install/installing-istio/)
  - [Install by using the Knative Operator](https://knative.dev/docs/install/operator/knative-with-operators/)
