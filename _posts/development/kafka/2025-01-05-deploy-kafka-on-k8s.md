---
title: "Deploy Kafka on Kubernetes ☸"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kubernetes 위에 Kafka Cluster 구축하고, Kafka UI 웹 콘솔로 접근하기!"
---

# 들어가며

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️

지금까지 Confluent 위에서 Saas로만 Kafka를 운영해본 것 같아서, 시험도 준비해볼겸! Kubernetes에서 Kafka Cluster를 운영해보며 이리저리 맛보고자 합니다 ㅎㅎ

# 일단 디플로이를 해보자!

저는 주로 Helm Chart로 워크로드를 디플로이하기 관리하고 있습니다. 이번에도 Helm Chart를 이용해 Kafka를 디플로이 해보겠습니다! 아래와 같이 `helmfile.bitnami-kafka.yaml`을 준비합니다.

```yaml
# @helmfile.bitnami-kafka.yaml
repositories:
  - name: bitnami
    url: https://charts.bitnami.com/bitnami

releases:
  - name: bitnami-kafka
    namespace: kafka
    chart: bitnami/kafka
    version: 31.1.1
    values:
      - ./values.bitnami-kafka.yaml
```

`bitnami/kafka` 차트 그대로 디플로이 해도 됩니다만, 그대로 하니 Kraft 모드로 디플로이 되더라구요! 저는 일단 Zookeeper 모드부터 테스트 해보기 위해 커스텀이 필요합니다! 그래서 `values.bitnami-kafka.yaml` 파일을 만들고 아래와 같이 작성합니다.

```yaml
# @values.bitnami-kafka.yaml
zookeeper:
  enabled: true
  replicaCount: 1

kraft:
  enabled: false

broker:
  replicaCount: 3

controller: # Kraft mode
  replicaCount: 0
```

포스트를 처음 작성할 때는 `broker.replicaCount=1`로 설정 했습니다만... 그렇게 하니 나중에 내부용 토픽인 `__consumer_offsets` 토픽 만들때, `replication.factor=3` 때문에 컨슈머 쪽에 오류가 생기더라구요... 그래서 시행착오를 원치 않으신다면 `broker.replicaCount=3`으로 시작하길 권장합니다!
{: .notice }

그리고 아래 명령어로 디플로이 하면

```bash
$ helmfile -f helmfile-kafka.yaml apply`
...
** Please be patient while the chart is being deployed **

Kafka can be accessed by consumers via port 9092 on the following DNS name from within your cluster:

    bitnami-kafka.kafka.svc.cluster.local

Each Kafka broker can be accessed by producers via port 9092 on the following DNS name(s) from within your cluster:

    bitnami-kafka-broker-0.bitnami-kafka-broker-headless.kafka.svc.cluster.local:9092

The CLIENT listener for Kafka client connections from within your cluster have been configured with the following security settings:
    - SASL authentication

To connect a client to your Kafka, you need to create the 'client.properties' configuration files with the content below:

security.protocol=SASL_PLAINTEXT
sasl.mechanism=SCRAM-SHA-256
sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required \
    username="user1" \
    password="$(kubectl get secret bitnami-kafka-user-passwords --namespace kafka -o jsonpath='{.data.client-passwords}' | base64 -d | cut -d , -f 1)";

To create a pod that you can use as a Kafka client run the following commands:

    kubectl run bitnami-kafka-client --restart='Never' --image docker.io/bitnami/kafka:3.9.0-debian-12-r4 --namespace kafka --command -- sleep infinity
    kubectl cp --namespace kafka /path/to/client.properties bitnami-kafka-client:/tmp/client.properties
    kubectl exec --tty -i bitnami-kafka-client --namespace kafka -- bash

    PRODUCER:
        kafka-console-producer.sh \
            --producer.config /tmp/client.properties \
            --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
            --topic test

    CONSUMER:
        kafka-console-consumer.sh \
            --consumer.config /tmp/client.properties \
            --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
            --topic test \
            --from-beginning
```

```bash
$ kgp
NAME                        READY   STATUS    RESTARTS      AGE
bitnami-kafka-zookeeper-0   1/1     Running   0             100s
bitnami-kafka-broker-0      1/1     Running   2 (35s ago)   100s
bitnami-kafka-broker-2      1/1     Running   2 (35s ago)   100s
bitnami-kafka-broker-3      1/1     Running   2 (35s ago)   100s
```

짜잔! zookeeper 하나와 broker 하나가 디플로이 되었습니다!

이게 zookeeper랑 broker가 같이 뜨게 되는데, zookeeper가 아직 준비되지 않은 상태에서 broker가 디플로이 되어서 broker가 몇번 `RESTART` 하게 되는데, zookeeper가 디플로이 되면 broker도 곧 `RUNNING` 상태가 됩니다 ㅎㅎ

그리고 뭔가 설명들이 쭉 나오는데요! 디플로이 한 Kafka 클러스터에 접속하는 방법이 적혀 있습니다 ㅎㅎ

## Let's Access the cluster!

디플로이한 Kafka 클러스터에 접속해봅시다! Kafka 클러스터에 접속하는 방법은 여러 가지가 있지만! 제가 Kafka CLI 대신 편하게 사용하고 있는 [`kcat`](https://github.com/edenhill/kcat)을 통해 한번 접속해보겠습니다.

```bash
# port-forward를 먼저 해줘야 합니다.
$ kubectl port-forward svc/bitnami-kafka -n kafka 9092:9092
...
$ kcat -b localhost:9092 -L \
-X security.protocol=SASL_PLAINTEXT \
-X sasl.mechanisms=SCRAM-SHA-256 \
-X sasl.username=user1 \
-X sasl.password=PASSWORD
Metadata for all topics (from broker -1: sasl_plaintext://localhost:9092/bootstrap):
 1 brokers:
  broker 100 at bitnami-kafka-broker-0.bitnami-kafka-broker-headless.kafka.svc.cluster.local:9092 (controller)
 0 topics:
```

`sasl.password` 값은 아까 bitnami/kafka를 디플로이 한 후에 나온 가이드의 명령어로 가져오면 됩니다.

```bash
$ kubectl get secret bitnami-kafka-user-passwords --namespace kafka -o jsonpath='{.data.client-passwords}' | base64 -d
```


# Kafka UI 디플로이

<div class="img-wrapper" markdown="1">
<img src="/images/development/kafka/kafka-ui-logo.png" style="width: 100px"/>
<p class="small gray text-center">
Kafka UI
</p>
</div>

디플로이한 Kafka 클러스터를 좀더 편하고 쉽게 관리하기 위해 [Kafka UI](https://docs.kafka-ui.provectus.io/)를 추가로 디플로이 합니다.
요건 Apache Kafka의 공식 컴포넌트는 아니지만! Confluent 플랫폼 처럼 Kafka 클러스터를 웹 콘솔에서 관리할 수 있게 도와줍니다! 🫰

디플로이를 위해 일단 helmfile을 아래와 같이 구성합니다.

```yaml
# @helmfile.kafka-ui.yaml
repositories:
  - name: kafka-ui
    url: https://provectus.github.io/kafka-ui-charts

releases:
  - name: kafka-ui
    namespace: kafka
    chart: kafka-ui/kafka-ui
    version: 0.7.6
    values:
      - ./values.kafka-ui.yaml

```

그리고 `values.kafka-ui.yaml`에 bitnami-kafka에 접속하기 위한 SASL 로그인 정보를 기입 합니다.

```yaml
# @values.kafka-ui.yaml
yamlApplicationConfig:
  kafka:
    clusters:
      - name: bitnami-kafka
        bootstrapServers:  bitnami-kafka.kafka.svc.cluster.local:9092
        properties:
          security.protocol: SASL_PLAINTEXT
          sasl.mechanism: SCRAM-SHA-256
          sasl.jaas.config: org.apache.kafka.common.security.scram.ScramLoginModule required username="user1" password="PASSWORD";
  auth:
    type: disabled

  management:
    health:
      ldap:
        enabled: false
```

마찬가지로 SASL password는 bitnami-kafka 클러스터 생성 때, 발급한 K8s Secret의 값을 가져와 사용합니다.

그리고 `helmfile apply`로 디플로이 한 후에! `port-forward`로 kafka-ui를 `http://localhost:8080`에서 접속해봅시다!

```bash
$ kubectl get svc -n kafka
NAME                               TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
bitnami-kafka-broker-headless      ClusterIP   None            <none>        9094/TCP,9092/TCP            122m
bitnami-kafka-zookeeper-headless   ClusterIP   None            <none>        2181/TCP,2888/TCP,3888/TCP   122m
bitnami-kafka-zookeeper            ClusterIP   10.43.190.250   <none>        2181/TCP                     122m
bitnami-kafka                      ClusterIP   10.43.176.10    <none>        9092/TCP                     122m
kafka-ui                           ClusterIP   10.43.177.29    <none>        80/TCP                       7s

$ kubectl port-forward svc/kafka-ui -n kafka 8080:80
```

![](/images/development/kafka/hello-kafka-ui.png){: style="max-width: 100%" }
Kafka UI
{: .small .gray .text-center }

ㅎㅎ 요렇게 Kafka 클러스터에 대한 정보를 웹 콘솔로 확인할 수 있습니다!

Kafka UI에 Kafka 브로커 서버 외에도 Zookeeper나 Schema Registry, Ksql도 연결할 수 있는 것 같습니다.
저도 이번 포스트를 작성하면서 처음 사용해보았는데요! 추후에 Kafka UI를 요리조리 뜯어보고 포스트를 또 하나 작성해보겠습니다 ㅎㅎ

Kafka UI와 Kafka 클러스터에 연결에 대한 더 세부적인 내용이 필요하다면, Kafka UI의 요 문서를 참고하시길 바랍니다 👉 [kafka-ui/Misc configuration properties](https://docs.kafka-ui.provectus.io/configuration/misc-configuration-properties)


# 동작 테스트

디플로이한 Kafka 클러스터가 잘 동작하는지 테스트 해봅시다!

처음에 bitnami-kafka 클러스터를 디플로이 한 후에 나오는 가이드를 따라 kubernetes에 `kafka-client`라는 pod을 하나 더 디플로이 합니다.
이 Pod은 `bitnami/kafka` 이미지를 사용하지만, `--command sleep infinity` 때문에 Kafka 클러스터로 동작하지는 않습니다. (이 과정에 귀찮다면, 그냥 클러스터를 이루는 브로커 중 하나를 사용하셔도 무방합니다 ㅋㅋ)

제가 CCDAK 자격증을 준비하면서 느낀 점은 로컬에 Kafka Shell을 실행할 수 있도록 세팅 해뒀더라도, 요렇게 컨테이너로 격리된 환경에서 Kafka Shell을 실행하는게 가장 정확한다는 것 입니다. 맥북 로컬의 Kafka Shell에서는 Exception을 뿜으며 제대로 동작하지 않던 것이 Kafka Broker pod에서는 제대로 동작하는 경우가 종종 있었습니다!

```bash
$ kubectl run bitnami-kafka-client --restart='Never' --image docker.io/bitnami/kafka:3.9.0-debian-12-r4 --namespace kafka --command -- sleep infinity
```

가이드에 따라 `client.properties`라는 파일을 만들어 아래와 같이 채웁니다.

```toml
security.protocol=SASL_PLAINTEXT
sasl.mechanism=SCRAM-SHA-256
sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required \
    username="user1" \
    password="PASSWORD";
```

`PASSWORD` 값은 `kubectl get secret bitnami-kafka-user-passwords --namespace kafka -o jsonpath='{.data.client-passwords}' | base64 -d` 명령어로 가져옵니다.

생성한 `client.properties` 파일을 `kafka-client` Pod 안에 복사합니다.

```bash
$ kubectl cp --namespace kafka ./client.properties bitnami-kafka-client:/tmp/client.properties
```

그리고 아래 명령어로 Kafka 클러스터에 접속해봅시다!

```bash
$ kubectl exec -it bitnami-kafka-client --namespace kafka -- sh
```

이제 아래 과정을 따라하며 실습 해봅시다!

## Topic CRUD

아래 명령어로 토픽을 생성합니다.

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --command-config /tmp/client.properties \
  --create \
  --replication-factor 1 \
  --partitions 2 \
  --topic test-topic
Created topic test-topic.
```

보통 `replication.factor=3` 정도로 설정하지만, 지금 띄운 Kafka 클러스터에는 브로커가 하나 뿐이기 때문에 `replication.factor=1`만 가능합니다.

그리고 `--list`로 확인해보면

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --command-config /tmp/client.properties \
  --list
test-topic
```

방금 생성한 토픽이 잘 출력 됩니다 ㅎㅎ

토픽 삭제는 아래의 명령어로 수행합니다.

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --command-config /tmp/client.properties \
  --delete \
  --topic test-topic
```

다시 `--list`로 확인해보면, 토픽이 출력에서 사라져 있습니다 ㅎㅎ

## Console Producer/Consumer

다시 `--create`로 `test-topic`을 만들어줍니다.

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --command-config /tmp/client.properties \
  --create \
  --replication-factor 1 \
  --partitions 2 \
  --topic test-topic
Created topic test-topic.
```

`kafka-console-producer.sh`를 사용하면, 콘솔 입력으로 Topic에 데이터를 보낼 수 있습니다 ㅎㅎ

```bash
$ kafka-console-producer.sh \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --producer.config /tmp/client.properties \
  --topic test-topic
>hahaha
>hhohohoh
>merong
```

앞에서 디플로이한 Kafka UI에서 해당 토픽에 데이터가 잘 들어가는지 확인 해볼수 있습니다 ㅎㅎ


![](/images/development/kafka/kafka-ui-messages.png){: style="max-width: 100%" }
Kafka UI에서 확인한 모습
{: .small .gray .text-center }


이번에는 `kafka-console-consumer.sh`로 해당 토픽에 데이터를 콘솔에 출력하도록 Subscribe 해봅시다. 👓

```bash
$ kafka-console-consumer.sh \
  --consumer.config=/tmp/client.properties \
  --bootstrap-server bitnami-kafka.kafka.svc.cluster.local:9092 \
  --from-beginning \
  --topic=test-topic
hahaha
hhohohoh
merong
```

# Deploy without Auth

그후에 로컬에서 Kafka 클러스터를 디플로이 해 연습을 해보니, 로컬 Kafka 클러스터에선 SASL Auth 없이 접속하는게 Kafka를 익히는데 더 도움이 되는 것 같습니다... 그래서 bitnami-kafka를 디플로이 할 때 사용한 `values.bitnami-kafka.yaml` 파일에 `listerners` 부분을 아래와 같이 추가합니다.

```yaml
# @values.bitnami-kafka.yaml
zookeeper:
  enabled: true
  replicaCount: 1

broker:
  replicaCount: 3

controller:
  replicaCount: 0

kraft:
  enabled: false

listeners:
  client:
    protocol: 'PLAINTEXT'
  controller:
    protocol: 'PLAINTEXT'
```

그리고 디플로이 하면, SASL 인증 없이도 Kafka 클러스터에 접속할 수 있습니다 ㅎㅎ

# 더 나아가기

이번 포스트에서는 bitnami의 helm chart를 사용해 Kafka 클러스터를 Kubernetes에서 구축 해보았습니다!
물론 저는 Confluent 플랫폼에서 Kafka를 쓰기 때문에 요걸 업무에서 해볼 기획는 없겠지만! 역시 실습에서 다양한 정보와 노하우들이 생겨나는 것 같습니다 ㅎㅎ

아래는 이번 포스트 이후에 진행해보려고 하는 것들 입니다!

- [Strimzi](https://strimzi.io/)의 Strimzi Operator 방식으로 Kafka on K8s 구현하기
  - Kafka 쪽에서는 요 Strimzi의 도움을 받아 디플로이 하는 경우가 많이 보였습니다.
  - 이번 기회에 한번 써보면 재밌을 것 같네요 ㅎㅎ
- [KRaft](https://developer.confluent.io/learn/kraft/) 모드
  - 이번에는 Zookeeper 모드로 Kafka 클러스터를 디플로이 하였습니다.
  - Zookeeper를 쓰지 않고 Kafka 클러스터를 운영하는 KRaft 모드도 한번 실습 해보겠습니다 ㅎㅎ
- Kafka UI Deep Dive
  - 요 포스트를 작성하면서, Kafka UI를 처음 사용해보았는데요!
  - 이것저것 만져보면서 Kafka 클러스터 운영에 대한 감을 좀 잡아보고자 합니다 ㅎㅎ

저는 저번주에 A형 독감에 걸리고 말았는데요! 🤒
요양한다고 밖에 안 나가서 그런지 오랫동안 묵혀 뒀던 요 글을 작성할 수 있었습니다 ㅋㅋ
2025년 올 한해도 재밌는 것들 많이하고, 건강한 한 해가 될 수 있길! 🙏
