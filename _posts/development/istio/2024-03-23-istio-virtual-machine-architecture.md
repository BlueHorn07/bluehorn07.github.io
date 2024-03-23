---
title: "Istio Virtual Machine Architecture"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "non-kubernetes 워크로드를 Istio 서비스 메쉬에 추가하는 방법. `WorkloadEntry`와 `WorkloadGroup`으로 VM 워크로드 관리하기! 🤝"
last_modified_at: 2024-03-23
---

솔직히 이 부분은 제대로 이해 못 했습니다 ㅠㅠ 직접 실습 해보기도 어려운 주제라서 일단 Istio에서 제공한 문서들을 꼼꼼히 그리고 충실히 읽고 제가 이해한 걸 최대한 설명해보았습니다. 혹시나 오류나 보충이 필요한 부분을 발견하신다면 알려주심 감사하겠습니다 🙏
{: .notice }

![](https://istio.io/latest/docs/ops/deployment/vm-architecture/single-network.svg)

Istio는 신기하게도 서비스 메쉬를 K8s 클러스터 뿐만 아니라 non-kubernetes 워크로드까지 지원을 한다!! Istio 문서에서는 이런 non-kubernetes 워크로드의 환경을 "**Virtual Machine**"(이하 VM)라고 부른다.

처음에는 'k8s 클러스터 바깥의 워크로드까지 신경 쓰는건 뇌절 아니야?'라고 생각 했는데, 천천히 문서를 읽어보니 아무리 K8s가 좋아도 어떤 컴포넌트는 보안이나 규제 때문에 K8s 환경에서 운영하지 못하고, Virtual Machine에서 운영할 수 밖에 없는 상황 생길 수 있는 것 같다. Istio는 이런 상황까지 고려한 것!!

생각해보니 울 회사도 DB나 Kafka 같은 서비스는 K8s가 아니라 별도의 managed 서비스에 띄워서 운영하고 있기 하네...

# Bookinfo with a Virtual Machine

![](https://istio.io/latest/docs/examples/virtual-machines/vm-bookinfo.svg)

Istio에서 VM Architecture를 이해하기 위해 친절히 예제도 마련해뒀다. [istio](https://istio.io/latest/docs/examples/virtual-machines/) 위의 그림에 따르면 "MySQL" 데이터베이스만 K8s 바깥의 VM에서 돌리고 있다.

# Virtual Machine Installation

https://istio.io/latest/docs/setup/install/virtual-machine/

그림에서도 보이듯 istio가 VM 워크로드를 서비스 메쉬에 통합하기 위해선 **VM 워크로드에도 istio를 설치해야** 한다!! (역시 그냥 되는게 아니다... ㅋㅋ)

일단 VM에 전달할 Istio 서비스메쉬의 정보를 입력해야 한다. 이때, Istio의 `WorkloadGroup` 리소스가 필요한데, 자세한 내용은 뒤에서 좀더 상세히 다루겠다.

암튼 `WorkloadGroup`이 생성된 상태라면, `isitoctl x workload entry configure ...` 명령어를 통해 VM에 전달할 Istio 서비스메쉬 정보가 담긴 파일을 생성한다.

그리고 각종 파일들을 VM 쪽으로 옮겨준다.

VM에 istio의 VM integration runtime을 설치해준다.

```bash
curl -LO https://storage.googleapis.com/istio-release/releases/1.21.0/deb/istio-sidecar.deb
sudo dpkg -i istio-sidecar.deb
```

VM에 istio runtime를 동작시킨다.

```bash
sudo systemctl start istio
```

이렇게 VM 쪽에 istio runtime을 돌리게 되면, k8s 클러스터에서 하던 것처럼 CoreDNS의 FQDN으로 요청을 보내면 그걸 istio runtime이 알아듣고 잘 라우팅 해준다고 한다!!

```bash
# VM에서 k8s 네트워크의 워크로드로 요청!!
$ curl helloworld.sample.svc:5000/hello
Hello version: v1, instance: helloworld-v1-578dd69f69-fxwwk
```

진짜 신기하다!!

이제 반대로 K8s에서 VM에 있는 mysql에 접근하기 위해선 아래와 같이 K8s svc를 만든 후, 접근하면 된다.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: mysqldb
  namespace: vm
  labels:
    app: mysqldb
spec:
  ports:
  - port: 3306
    name: tcp
  selector:
    app: mysqldb
```

```bash
$ k exec -it ... -- sh
~ $ mysql -h http://mysqldb.vm.svc.cluster.local ...
```

# VM Service Association

앞에서 `istioctl x workload entry configure ...` 명령어로 VM에 전달할 istio 서비스 메쉬 정보가 담긴 파일을 생성한다는 것 기억하는가? 이때, Istio의 `WorkloadEntry` 또는 `WorkloadGroup`이 필요하다고 말했는데, 이 리소스가 뭔지 살펴보자.

일단 `WorkloadEntry`와 `WorkloadGroup` 둘다 Istio에서 VM workload를 표현하기 위한 리소스이다. Istio 문서에서는 둘이 각각 K8s Pod과 K8s Deployment에 대응된다고 소개하고 있다. `WorkloadGroup`를 재료로 `istioctl x workload entry configure ...`에서 서비스메쉬 정보 파일을 만들고, 실제 VM이 서비스 메쉬에 추가되면 `WorkloadEntry` 리소스가 자동으로 뜨게 된다고 한다!! (신기신기)

## WorkloadGroup 만들기

일단 `WorkloadGroup` 리소스가 필요하다. 만드는 방법은 `istioctl x workload`를 사용하는 것과 그냥 yaml 파일 만들어서 생성하는 것 2가지가 있다.

`istioctl`로 만드는 방법은 아래와 같다.

```bash
istioctl x workload group create \
    --name product-vm \
    --namespace default \
    --labels app=product \
    --ports http=8080 \
    --serviceAccount default
---
# WorkloadGroup이 생성되진 않고, yaml만 프린트 해준다!
apiVersion: networking.istio.io/v1alpha3
kind: WorkloadGroup
metadata:
  name: product-vm
  namespace: default
spec:
  metadata:
    labels:
      app: product
  template:
    ports:
      http: 8080
    serviceAccount: default
```

와우! 바로 yaml 파일이 출력 됐다!! 저걸 그대로 저장하고 만들기만 하면 된다 ㅎㅎ

## VM을 추가하면 `WorkloadEntry` 리소스가 생성

암튼 위에서 만든 `WorkloadGroup`으로 VM을 추가하면 대응하는 `WorkloadEntry`가 생성된다!!

```yaml
apiVersion: networking.istio.io/v1beta1
kind: WorkloadEntry
metadata:
  annotations:
    istio.io/autoRegistrationGroup: product-vm # 만들었던 것!
  labels:
    app: product
  name: product-vm-1.2.3.4
spec:
  address: 1.2.3.4 # 자동으로 담긴!
  labels:
    app: product
  serviceAccount: default
```

이렇게 만든 `WorkloadEntry`에 접근하려면 K8s Service를 만들어서 주면 된다.

이때, 신기한 점은 K8s Service를 만들 때, K8s Pod이랑 VM workload랑 같이 묶을 수도 있다는 거다!!

![](https://istio.io/latest/docs/ops/deployment/vm-architecture/service-selector.svg){: .align-center style="max-width: 360px" }

K8s Svc가 이렇게 구성되면, 트래픽은 K8s Pod과 Istio `WorkloadEntry`에 두 곳에 라우팅 할 수 있게 된다!! (놀라워!!)

# 이걸 공부해야 하나?

![](/images/development/istio/ica-cert-advanced-scenarios.png)

아놔 이걸 공부해야 하나 말아야 하나 고민을 좀 했는데, ICA 시험의 시험 주제 목록에 "non-kubernetes 워크로드에 대해서도 물어볼 수 있습니다"라고 적혀있어서 이 악물고 공부했다... 😬

# 참고자료

- istio 문서
  - [Bookinfo with a Virtual Machine](https://istio.io/latest/docs/examples/virtual-machines/)
  - [Virtual Machine Architecture](https://istio.io/latest/docs/ops/deployment/vm-architecture/)
  - [Virtual Machine Installation](https://istio.io/latest/docs/setup/install/virtual-machine/)
- [solo.io: Istio architecture](https://www.solo.io/topics/istio/istio-architecture/)