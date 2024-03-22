---
title: "Istio Revision and Canary Upgrade"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "동시에 2개 Istio를 운영하는 방법. Revision으로 Istio를 Canary Upgrade 하기 🐣"
last_modified_at: 2024-03-22
---

![](https://images.unsplash.com/photo-1586861256152-6c7e7ce3895d?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)
귀여운 카나리아 ([unsplash](https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%ED%9A%8C%EC%83%89-%EB%B0%94%EC%9C%84%EC%97%90-%EB%85%B8%EB%9E%80%EC%83%89%EA%B3%BC-%EA%B2%80%EC%9D%80-%EC%83%89-%EC%83%88-60WkGpWyadY?utm_content=creditShareLink&utm_medium=referral&utm_source=unsplash))
{: .small .text-center .gray }


# Istio Canary Upgrade

K8s 클러스터에 Istio 메쉬를 구축해둬도 Istio 버전이 신규 기능이 추가되어 출시되고, K8s 버전을 올리면 세팅한 Istio 메쉬의 버전과 호환이 안 될 수도 있다. Istio는 MSA의 네트워크 트래픽을 다루는 도구이기 때문에 Istio 문서에서도 업그레이드를 매우 신중히 알 것을 권장한다.

새로운 Istio 버전으로 메쉬를 세팅해야 할 때, Istio는 `revision`라는 태그 정보를 통해 컨트롤 플레인을 일부만 교체하는, **Canary Upgrade** 방식을 지원한다.

원랜 Istio에서 동시에 2개의 컨트롤 플레인이 뜨는 걸 허용하지 않지만, 아래와 같이 `revision`을 지정하면 2개 컨트롤 플레인 동시에 존재하게 된다.

```bash
$ istioctl install --revision=canary
```

만약 삭제하고 싶다면, 설치 했던 `revision`을 그대로 주면 된다.

```bash
$ istioctl uninstall --revision=canary
```

## 데이터 플레인을 신규 revision으로 대체하기

![](https://istio.io/latest/docs/setup/upgrade/canary/revision-tags-before.svg)

`test-ns`라는 네임스페이스에 `istio-inejction=enabled`라는 레이블이 붙어있어서 해당 네임스페이스에 뜬 Pod들은 sidecar가 붙은 상태로 세팅해보자.

```bash
$ kubectl create ns test-ns
$ kubectl label namespace test-ns istio-injection=enabled
$ kubectl apply -n test-ns -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/sleep/sleep.yaml
---
NAME                    READY   STATUS    RESTARTS   AGE
sleep-bc9998558-ph8kw   2/2     Running   0          5s
```

이 경우는 `revision=canary` istio를 설치하기 전에 있던 istio 메쉬의 제어를 받는다. 요 `test-ns` 네임스페이스의 Pod이 새로 띄운 `canary` istio 메쉬의 제어를 받도록 수정해보자.

일단 기존의 `istio-injection` 레이블을 제거하고 `istio.io/rev=canary`라는 레이블을 추가한다.

```bash
$ kubectl label namespace test-ns istio-injection- istio.io/rev=canary
```

그리고 Pod들을 다시 띄워준다.

```bash
$ kubectl rollout restart deployment -n test-ns
---
NAME                    READY   STATUS    RESTARTS   AGE
sleep-bbc457487-k5pdt   2/2     Running   0          9s
```

그리고 `istioctl proxy-status` 커맨드로 istio의 proxy 상태들을 확인해보면

![](/images/development/istio/istio-canary-revision.png)

`sleep` Pod이 `canary` istio를 컨트롤 플레인으로 사용하는 걸 확인할 수 있다!!

`istio-injection` 레이블을 제거한 이유는 요 레이블이 `istio.io/rev` 레이블보다 우선권을 갖는 레이블이기 때문이다. 별도로 띄운 istio 컨트롤 플레인을 사용하고 싶다면 위의 과정처럼 기존 레이블을 제거하고 `istio.io/rev` 레이블도 추가해야 한다.

## istioctl tags

`istioctl`에서는 "tags"라고 revision의 별명(alias)를 지정하는 기능을 제공한다.

아직은 왠지 잘 안 쓸 것 같아서 문서만 보고 넘기겠다 🙇

➡️ [istio: istioctl tag](https://istio.io/latest/docs/reference/commands/istioctl/#istioctl-tag)

# 참고자료

- [istio: Revision Tag](https://istio.io/latest/blog/2021/revision-tags/)
- [istio: Canary Upgrade](https://istio.io/latest/docs/setup/upgrade/canary/)
