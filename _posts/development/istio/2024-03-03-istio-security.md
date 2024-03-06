---
title: "Istio Security"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio", "Security"]
excerpt: "`PeerAuthentication`으로 Istio 워크로드의 접근만 허용하기, `AuthorizationPolicy`로 엔드포인트 접근 제어하기, `Sidecar`로 Envoy Sidecar 구성 커스텀 하기"
last_modified_at: 2024-03-06
---

![](https://www.asylas.com/wp-content/uploads/2020/12/9-Awareness-Training.jpg){: .align-center }

분산 시스템 위에서 "완벽한" 서비스 메쉬를 제공하기 위해서 Istio는 "보안" 관련된 여러 기능들을 제공한다!!


# `PeerAuthentication`

요건 istio 워크로드에 `mTLS`의 트래픽만 접근만 허용할 것인지(`STRICT`) 아니면 mTLS가 적용되지 않은 트래픽 접근도 허용할 것인지(`PERMISSIVE`) 결정하는 정책이다.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: security.istio.io/v1beta1
kind: PeerAuthentication
metadata:
  name: only-mtls-allow
  namespace: default
spec:
  mtls:
    mode: STRICT
EOF
```

이렇게 설정할 경우, `default` 네임스페이스로 향하는 트래픽은 모두 mTLS로 암호화 되어야 한다. 그런데, mTLS 암호화가 되었다는 말이 곧 그 워크로드가 Istio 서비스 메쉬 위에 있다는 말이기 때문에 결국 Istio 서비스 메쉬의 워크로드의 접근만 허용하겠다는 것이다.

```bash
$ kubectl exec ... -n non-istio -- sh
~# curl helloworld.default:5000/hello
curl: (56) Recv failure: Connection reset by peer
```

만약 istio 워크로드가 아닌 곳에서 요청을 보낸다면 이렇게 접근이 불가능하다.

# `AuthorizationPolicy`

Istio의 `AuthorizationPolicy`는 서비스의 접근 규칙을 미세한 수준을 제어하는 리소스이다. 요청(request)의 출발지(source) 속성을 기준으로 제어할 수도 있고, 요청의 도착지(to) 속성을 기준으로 제어할 수 있다. 그외에 요청이 가진 속성들(ex: request headers, JWT token principal 등)을 기준으로 제어할 수도 있다.

여기서 살펴보는 3가지 리소스 중에서 요 녀석이 제일 어려웠다 ㅠㅠ

가장 간단히 `default` 네임스페이스의 워크로드를 `test` 네임스페이스로부터 보호하는 `AuthorizationPolicy`부터 살펴보자.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: security.istio.io/v1
kind: AuthorizationPolicy
metadata:
  name: protect-from-test
  namespace: default
spec:
  action: DENY
  rules:
  - from:
    - source:
        namespaces: ["test"]
EOF
```

위와 같은 AuthPolicy가 생성되었다면, `test` 네임스페이스는 워크로드에 접근하려고 할 때, 이런 에러를 받는다.

```bash
$ kubectl exec -n test ... -- sh
~# curl helloworld.default:5000/hello
RBAC: access denied
```

그런데 위의 AuthPolicy는 `test` 네임스페이스의 접근을 막았을 뿐 다른 네임스페이스에서의 접근은 잘 이뤄진다!

만약, 반대로 특정 네임스페이스의 접근만을 허용하고 싶다면 리소스를 이렇게 만들어보자.

```bash
$ kubectl delete authorizationpolicy -n default protect-from-test
```

```yaml
$ kubectl apply -f - <<EOF
apiVersion: security.istio.io/v1
kind: AuthorizationPolicy
metadata:
  name: only-allow-from-test
  namespace: default
spec:
  action: ALLOW
  rules:
  - from:
    - source:
        namespaces: ["test"]
EOF
```

이렇게 하면, 반대로 `test` 네임스페이스의 접근은 허용하지만, 다른 네임스페이스로부터의 접근은 거부 된다.

단, **같은 네임스페이스인 `default` 네임스페이스의 리소스들 간의 통신도 거부**되기 때문에 주의할 것!!

## Request의 출발지 속성을 기준으로

네임스페이스 외에도 다른 몇가지 조건들로 접근을 제어할 수 있다.

트래픽의 출발지를 기준으로

- `namespace`
- `ipBlocks`
- `remoteIpBlocks`

## Request의 도착지 속성을 기준으로

- `hosts`
- `ports`
- `methods`
- `paths`

## Request가 가진 속성을 기준으로

https://istio.io/latest/docs/reference/config/security/conditions/

# `Sidecar`

Istio 서비스메쉬에서 Envoy SideCar에 대한 네트워크 구성을 명시하는 리소스이다.

## Egress Listener

예를 들어, 아래의 `Sidecar` 리소스를 만들면, `default` 네임스페이스에서 `default`와 `test`의 워크로드로만 접근할 수 있다.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: Sidecar
metadata:
  name: default-egress
  namespace: default
spec:
  egress:
  - hosts:
    - "default/*"
    - "test/*"
EOF
```

만약 명시되지 않은 워크로드에 `curl` 명령을 날리면 아무 response도 받지 못 하고 필터링 된다.

요렇게 `Sidecar`의 `egress` 규칙을 활용하면, 해당 네임스페이스나 워크로드에서 접근 가능한 K8s 또는 Istio 서비스 메시를 제한할 수 있다. **즉, 최소한의 필요로 하는 보안 접근만을 허용하는 것이다.** (Zero-Trust Network의 조건을 충족!)

아 그리고 만약 같은 네임스페이스의 워크로드로만 egress host를 허용하려면 아래와 같이 작성할 수도 있다.

```yaml
...
spec:
  egress:
  - hosts:
    - "./*"
    - "./helloworld"
```

이렇게 상대경로 표현식 처럼 `./`를 사용하면 같은 네임스페이스의 워크로드만 허용하게 된다.

## Ingress Listener

이번에는 반대로 요 워크로드에 접근 가능한 port를 제한해보자.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: Sidecar
metadata:
  name: default-ingress
  namespace: default
spec:
  ingress:
  - port: 5000
EOF
```

이렇게 하면, `default` 네임스페이스의 모든 워크로드에 대해서 `5000` 포트만 열려있게 된다.

왜 ingress는 port를 제한할까 생각을 좀 해봤는데...

보통 K8s의 워크로드에 접근하려고 하면 K8s Service 리소스의 CoreDNS 주소로 접근 한다. 그런데, 이건 편의를 위해 쓰던 것이고 사실은 각 Pod이 부여 받은 Private IP로도 워크로드에 접근할 수 있다.

K8s Service를 접근할 때는 K8s Service를 정의하면서 워크로드의 어떤 포트를 노출할지 결정을 하게 되지만, 어떤 포트는 K8s Svc로 노출하고 싶지 않을 수도 있다.

Istio `Sidecar`는 이렇게 Native K8s가 갖는 원치 않는 워크로드의 포트가 노출되지 않도록 Envoy Sidecar 수준에서 ingress port를 제한하는 것 같다. 어떻게 보면, AWS SG의 Ingress Rule처럼 Inbound 포트를 제한하는 것으로 보이기도 한다.

## Workload Selector

위의 `Sidecar` 예시들은 `metadata.namespace`의 모든 Envoy Sidecar에 적용되는 규칙이었다. 만약 특정 워크로드의 Envoy Sidecar에만 적용하고 싶다면, 아래와 같이 `workflowSelector`를 포함하면 된다.

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: Sidecar
metadata:
  name: default-egress
  namespace: default
spec:
  workloadSelector:
    labels:
      app: helloworld
  egress:
  - hosts:
    - "./*"
EOF
```

와우! 위와 같이 `Sidecar`를 구성하면 helloworld 워크로드는 오직 같은 네임스페이스의 워크로드로만 접근할 수 있다!!

# Istio와 Zero-trust Network

Istio를 공부하면서 "Zero-trust Network"(이하 ZTN)라는 용어를 처음 보게 되었다. 본인이 보안 전문가는 아니라서 쉽게 이해 되지는 않았는데...

[토스ㅣSLASH 23 - 고객 불안을 0으로 만드는 토스의 Istio Zero Trust](https://youtu.be/4sJd6PIkP_s?si=FUrtUOR3u_x9jEMK)의 영상이 Istio ZTN를 이해하는데 큰 도움이 되었다.

토스는 아무래도 금용 기업이니 워크로드를 운영할 때 높은 수준의 보안을 요구할 것 같다. Istio는 토스의 K8s 클러스터에 ZTN를 제공하는 솔루션으로 잘 기능하는 것 같다.

지금부터는 위의 영상의 내용 요약이다!

- 토스는 모든 워크로드에 mTLS `STRICT` 모드이다. 즉, Istio 서비스 메쉬 밖에서는 서비스 메쉬로 접근할 수 없다.
- 토스는 Istio의 `Sidecar` 리소스를 사용해서 워크로드가 접근할 수 있는 egress host를 제한한다. 따로 설정해주지 않으면 어떤 워크로드에도 접근할 수 없다. (Least Privilliage 사례)
- 토스는 Istio의 `AuthorizationPolicy` 리소스를 설정하여 예상치 못한 워크로드의 접근을 막고, 워크로드의 접근은 Endpoint 레벨까지 제한한다. 

즉, 워크로드 A가 워크로드 B에 접근하고자 한다면, (1) 둘다 Istio 서비스 메쉬 안에 있어야 하고, (2) 워크로드 A의 `Sidecar`를 설정해서 워크로드 B에 egress 할 수 있게 허용해야 하고, (3) 워르코드 B에 `AuthorizationPolicy`를 설정해 워크로드 A의 ingress 트래픽을 허용해줘야 한다.

![](/images/development/istio/toss-sidecar-monitoring.png)

토스의 경우, `Sidecar`의 egress host 목록이 변경 된다거나, 아님 등록되지 않은 egress host로 요청이 발생한다면 Slack 메시지도 오는 등 Istio를 세밀하게 모니터링 하고 있다. (최근 코드 커미터 보여주는 것도 신기하네... 👀)

처음에는 Istio를 서비스 메쉬? 네트워크 트래픽을 모니터링 하고, 또 카나리 배포를 할 수 있는 거구나~~ 라고만 생각했는데, 분산처리 시스템과 그것의 보안을 위해 잘 고안된 서비스 메쉬라는 생각이 든다. (역시 CNCF!)

# 참고자료

- Istio 공식 문서들
  - [`PeerAuthentication`](https://istio.io/latest/docs/reference/config/security/peer_authentication/)
  - [`AuthroizationPolicy`](https://istio.io/latest/docs/reference/config/security/authorization-policy/)
    - [Authorization Policy Conditions](https://istio.io/latest/docs/reference/config/security/conditions/)
  - [`Sidecar`](https://istio.io/latest/docs/reference/config/networking/sidecar/)
- Toss Slash 23
  - [토스ㅣSLASH 23 - 고객 불안을 0으로 만드는 토스의 Istio Zero Trust](https://youtu.be/4sJd6PIkP_s?si=FUrtUOR3u_x9jEMK)
  - 그외에도 토스에서 Istio 관련해서 많은 사례 케이스를 제시하고 있다!! (나중에 쭉 봐야지...)
