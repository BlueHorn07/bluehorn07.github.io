---
title: "Kafka `listeners` vs. `advertised.listeners`"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "카프카 클러스터 외부의 클라이언트가 클러스터에 접속하기 위해 꼭 설정해야 하는 옵션!"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️ 전체 포스트는 [여기](/categories/kafka)에서 확인할 수 있습니다.
{: .notice }

# 들어가며

로컬 Kubernetes 환경에 Kafka 클러스터를 구성하여 사용하고 있습니다. 컨트롤러 노드 3개, 브로커 노드 3개로 구성 했는데요. 이럴수가! KsqlDB를 연습하기 위해 띄운 `ksqldb-server`가 브로커에 제대로 접속을 하지 못하는 현상을 경험하게 되었습니다.

```bash
$ curl -X GET  http://localhost:8088/info
curl: (7) Failed to connect to localhost port 8088: Connection refused
```

사실 [bitnami-kafka](https://artifacthub.io/packages/helm/bitnami/kafka)의 helm chart의 REAEDME를 잘 읽어보면, 아래와 같은 구절이 있습니다.

> In order to access Kafka Brokers from outside the cluster, an additional listener and advertised listener must be configured. Additionally, a specific service per kafka pod will be created. - [bitnami-kafka helm chart](https://artifacthub.io/packages/helm/bitnami/kafka#accessing-kafka-brokers-from-outside-the-cluster)

즉, "Advertised Listener"를 제대로 설정하지 않아 외부 접속이 원활하지 않았던 것 입니다.
그동안은 클러스터의 브로커에 접속해서 Kafka CLI를 실행해서 이 현상을 발견하지 못 했던 것 같습니다.

이것을 계기로 카프카 클러스터에 세팅하는 `listeners`와 `advertised.listeners` 옵션에 대해 살펴보게 되었고, 그 내용을 제 방식대로 정리해보고자 합니다.

# Broker Listeners

## `listeners`

```toml
listeners=PLAINTEXT://0.0.0.0:9092
```

브로커가 바인딩할 네트워크 인터페이스와 포트를 정의하는 Config 입니다. 브로커가 어떤 IP와 포트에서 트래픽을 수신할지 정의 합니다.

이때, `PLAINTEXT`는 카프카에서 사용하는 리스너 프로토콜 중 하나로 암호화 없이 기본 TCP 통신하는 프로토콜 입니다. 세부적으로는 아래와 같은 리스너 프로토콜을 지원합니다.

| 프로토콜 | 설명 |
|-|-|
| `PLAINTEXT` | 암호화 없이 기본 TCP 통신 (기본값) |
| `SSL` | TLS/SSL을 사용하여 암호화된 통신 |
| `SASL_PLAINTEXT` | SASL 인증 사용, 하지만 메시지는 암호화되지 않음 |
| `SASL_SSL` | SASL 인증 + TLS/SSL 암호화 |

`0.0.0.0`은 모든 네트워크 인터페이스를 말합니다. 이것은 어떤 IP 주소로 요청이 오더라도 모든 요청을 수락한다는 것을 말합니다.
`0.0.0.0` 외에도 아래와 같이 네트워크 인터페이스 설정이 가능합니다.

| 설정 값 | 의미 |
| `0.0.0.0` | 모든 네트워크 인터페이스에서 연결 허용 (내부, 외부 포함) |
| `127.0.0.1` | 오직 localhost(루프백)에서만 연결 가능 (외부 접근 불가) |
| `192.168.1.10` | 특정 내부 네트워크에서만 연결 가능 |
| `10.0.0.5` | 특정 VPC 네트워크에서만 연결 가능 |
| `203.0.113.15` | 특정 공인 IP에서만 연결 가능 |

## `advertised.listeners`

```toml
advertised.listeners=PLAINTEXT://my.kafka.broker.com:9092
```

클라이언트(프로듀서, 컨슈머)가 브로커에 접속할 때, 어떤 주소로 접근해야 하는지 알려주는 역할을 합니다. 브로커와 클라이언트가 소통하는 과정을 살펴보면 아래와 같습니다.

1. 클라는 설정된 `bootstrap.servers` 주소를 사용해 Kafka 클러스터에 연결을 시도 합니다.
2. 브로커는 클러스터의 전체 정보를 포함하 메타데이터 응답을 클라에게 보냅니다.
   1. 이때, 각 브로커의 `advertised.listeners` 값을 포함해 보내줍니다.
3. 클라는 리더 파티션을 소유한 브로커를 찾고, 그 브로커와 통신하기 위해 각 브로커에 담겨 있던 `advertised.listeners` 주소를 사용합니다.

이 `advertised.listeners` 값이 잘 설정되어 있어야 클러스터 외부의 클라이언트가 카프카 클러스터와 정상적으로 소통할 수 있습니다.

# bitnami-kafka

저는 [bitnami-kafka](https://artifacthub.io/packages/helm/bitnami/kafka)의 helm chart를 이용해 Kubernetes on Kafka 클러스터를 구축하였습니다. `advertised.listeners`를 설정하기 위해 `values.yaml` 파일을 아래와 같이 세팅하였습니다.

```yaml
listeners:
  client:
    protocol: 'PLAINTEXT'  # no auth
  controller:
    protocol: 'PLAINTEXT'  # no auth

externalAccess:
  enabled: true
  controller:
    service:
      type: NodePort
  broker:
    service:
      type: NodePort
  autoDiscovery:
    enabled: true
serviceAccount:
  create: true
rbac:
  create: true
```

bitnami-kafka에서는 외부 접속을 하기 위해선 `externalAccess.enabled=true`로 설정해줘야 합니다.
이때, Kubernetes Service의 타입을 뭐로 할지 지정해야 하는데, 저는 `NodePort`로 사용하고 있습니다.

```bash
NAME                                  TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
bitnami-kafka                         ClusterIP   10.43.165.141   <none>        9092/TCP,9095/TCP            11h
bitnami-kafka-broker-0-external       NodePort    10.43.209.77    <none>        9094:31770/TCP               11h
bitnami-kafka-broker-1-external       NodePort    10.43.227.177   <none>        9094:30447/TCP               11h
bitnami-kafka-broker-2-external       NodePort    10.43.207.221   <none>        9094:31014/TCP               11h
bitnami-kafka-broker-headless         ClusterIP   None            <none>        9094/TCP,9092/TCP            11h
bitnami-kafka-controller-0-external   NodePort    10.43.240.235   <none>        9094:31401/TCP               11h
bitnami-kafka-controller-1-external   NodePort    10.43.222.244   <none>        9094:31073/TCP               11h
bitnami-kafka-controller-2-external   NodePort    10.43.178.28    <none>        9094:30316/TCP               11h
bitnami-kafka-controller-headless     ClusterIP   None            <none>        9094/TCP,9092/TCP,9093/TCP   11h
```

`externalAccess.enabled=true`로 설정하면 각 브로커와 컨트롤러마다 `NodePort` 타입의 Kubernetes Service가 생성 됩니다.

제 경우는 로컬 Kubernetes이기 때문에, `Ingress` 리소스를 사용하기 어려웠습니다. 만약 EKS 환경이었다면, 브로커 별로 Ingress를 만들고 브로커에 할당된 public DNS 값을 활용할 수도 있을 것 같습니다.

<br/>

이렇게 세팅 한 후에는 KsqlDB Pod이 카프카 클러스터에 정상적으로 통신하게 되었습니다!
다만, 로컬에서 실행하는 Kafka-Streams는 Kubernetes 환경이 아니라 로컬 IntelliJ에서 실행했기 때문에 여전히 bitnami-kafka 클러스터에 연결되지 못하는 현상을 겪었습니다.


# Advertised Listeners가 필요한 카프카 컴포넌트들

카프카에는 브로커 말고도 Advertised Listeners 설정이 필요한 컴포넌트들이 존재합니다.

## Kafka Broker

- `advertised.listeners`

클라이언트가 카프카 클러스터에 접속 했을 때, 어떤 리더 파티션과 통신해야 하는지 알기 위해 Advertised Listeners 설정이 필요합니다.

## Kafka Connect

- `CONNECT_REST_ADVERTISED_HOST_NAME`
- `CONNECT_REST_ADVERTISED_PORT`

Kafka Connect를 멀티 워커로 운영할 때 필요한 옵션입니다. Kafka Connect가 멀티 워커로 동작할 때는 각 워커가 잘 살아있는지 확인이 필요하기 때문에 Advertised Listeners가 필요합니다. 그래야 Task가 워커 노드들에게 올바르게 분배됩니다.

Kafka Connect 클러스터는 리더 노드와 팔로워 노드로 이뤄집니다. 이때, Advertised Listeners가 제대로 설정되어 있어야 리더 노드가 다른 워커 노드를 찾을 수 있습니다. 만약 적절히 설정되어 있지 않았다면, 태스크가 워커 노드에 제대로 할당되지 않습니다.


## KsqlDB

- `KSQL_ADVERTISED_LISTENERS`

KsqlDB에 접속하는 클라이언트들 `ksql-cli`와 Kafka Connect가 KsqlDB에 접근할 때 사용하는 주소 입니다. 이 값이 제대로 설정되어 있지 않다면, `ksql-cli`가 제대로 동작하지 않습니다

위의 과정에서 bitnami-kafka의 Advertised Listeners를 제대로 설정하는 과정에 대해서 소개 했는데요. bitnami-kafka의 Advertised Listeners만 제대로 설정 했을 때는 `ksqldb-server`는 정상 접근 하였지만, `ksqldb-cli`는 `ksqldb-server`에 접근하지 못하는 문제를 겪었습니다.

그래서 `ksqldb-server`를 디플로이 할 때, 아래와 같이 설정이 필요 했습니다.

```yaml
...
      containers:
        - name: ksqldb-server
          # https://hub.docker.com/r/confluentinc/ksqldb-server
          image: confluentinc/ksqldb-server:0.29.0
          ports:
            - containerPort: 8088
          env:
            - name: KSQL_LISTENERS
              value: "http://0.0.0.0:8088"
            - name: KSQL_ADVERTISED_LISTENERS
              value: "http://0.0.0.0:8088,http://ksqldb-service.kafka.svc.cluster.local:8088"
            - name: KSQL_BOOTSTRAP_SERVERS
              value: "bitnami-kafka.kafka.svc.cluster.local:9092"
            - name: KSQL_SECURITY_PROTOCOL
              value: "PLAINTEXT"
            - name: KSQL_KSQL_LOGGING_PROCESSING_STREAM_AUTO_CREATE
              value: "true"
            - name: KSQL_KSQL_LOGGING_PROCESSING_TOPIC_AUTO_CREATE
              value: "true"
```

# 부록

## Empty Advertised Listeners

만약 Advertised Listeners 값이 설정되지 않으면, 기본적으로 Listeners에 설정된 값을 사용하게 됩니다.

> If this is not set, the value for `listeners` will be used. Unlike `listeners`, it is not valid to advertise the `0.0.0.0` meta-address.

그러나 `listeners`에는 네트워크 인터페이스를 적기 때문에, Advertised Listener가 그 값을 그대로 사용하게 되면 제대로 동작하지 않게 됩니다.


## `INTERNAL`, `EXTERNAL` 프로토콜

종종 `listeners`의 값이 아래와 같이 세팅되는 경우도 있습니다.

```toml
listeners=INTERNAL://192.168.1.10:9092,EXTERNAL://0.0.0.0:9093
advertised.listeners=INTERNAL://192.168.1.10:9092,EXTERNAL://public-broker.mydomain.com:9093
```

`INTERNAL`? `EXTERNAL`? 앞에서는 `PLAINTEXT://...`로 설정되는 경우를 보았는데, 이런 경우는 어떤 경우인지 궁금해졌습니다.

카프카에서는 내부 서비스와 외부 클라이언트의 접근을 분리해 관리할 수 있다고 합니다. `listeners`에 작성된 내용을 해석해보면

- `INTERNAL://192.168.1.10:9092`
  - 내부 서비스는  `9092` 포트를 통해 접속할 수 있다.
- `EXTERNAL://0.0.0.0:9093`
  - 외부 클라이언트는 `9093` 포트를 통해서 접속할 수 있다.

그리고 `advertised.listeners`의 내용을 해석하면

- `INTERNAL://192.168.1.10:9092`
  - 내부 서비스는 VPC 망 내의 Cluster IP로 접근해야 한다.
- `EXTERNAL://public-broker.mydomain.com:9093`
  - 외부 접근은 위의 hostname으로 접속해야 한다.

`advertised.listeners`에 `INTERNAL`과 `EXTERNAL` 2개의 주소가 설정 되었는데요. 클라이언트가 메타데이터 핸드쉐이크 과정에서 전달 받은 `advertised.listeners`의 주소 값을 기반으로 자신이 `INTERNAL`에 속한다면, `INTERNAL`로 아니라면 `EXTERNAL`을 사용한다고 합니다.

그리고 원한다면, `INTERNAL`만 정의해서 내부 트래픽만 받도록 만들 수도 있고, `EXTERNAL`만 정의해서 외부 트래픽 받는 것도 가능하다고 합니다.

(이렇게 적었는데도 아직 완전히 이해한 것 같지는 않습니다...;;)
