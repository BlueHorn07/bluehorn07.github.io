---
title: "Deploy Debeizum Mysql Connector"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: ""
---

# Prerequisites

모든 과정은 로컬 Kubernetes의 Ranchder Desktop 환경 위에서 진행 하였습니다.

## Deploy Kafka

이전에 적었던 [Deploy Kafka Cluster using Strimzi](/2025/02/03/deploy-kafka-using-strimzi/) 포스트 참고!

```bash
helm install strimzi-cluster-operator oci://quay.io/strimzi-helm/strimzi-kafka-operator -n strimzi
```

```yaml
# @kafka.kraft.yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: Kafka
metadata:
  name: my-cluster
  namespace: strimzi
  annotations:
    strimzi.io/kraft: enabled
    strimzi.io/node-pools: enabled
spec:
  kafka:
    version: 4.0.0
    listeners:
      - name: plain
        port: 9092
        type: internal
        tls: false
  entityOperator:
    topicOperator: {}
    userOperator: {}
---
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaNodePool
metadata:
  name: controller-nodes
  namespace: strimzi
  labels:
    strimzi.io/cluster: my-cluster  # Kafka 클러스터 이름과 일치해야 함
spec:
  replicas: 3  # 컨트롤러 개수
  roles:
    - controller
  storage:
    type: ephemeral
---
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaNodePool
metadata:
  name: broker-nodes
  namespace: strimzi
  labels:
    strimzi.io/cluster: my-cluster  # Kafka 클러스터 이름과 일치해야 함
spec:
  replicas: 3  # 브로커 개수
  roles:
    - broker
  storage:
    type: ephemeral
```

## Deploy Mysql

ㅠㅠ... 이젠 bitnami mysql helm chart를 쓰지 못 해서 아래와 같이 직접 정의 했습니다.

```yaml
# @mysql.yaml
apiVersion: v1
kind: Pod
metadata:
  name: mysql
  labels:
    app: mysql
spec:
  containers:
  - image: mysql:8
    name: mysql
    env:
    - name: MYSQL_ROOT_PASSWORD
      value: hello_debezium!
    - name: MYSQL_USER
      value: admin
    - name: MYSQL_PASSWORD
      value: hello_debezium!
    ports:
    - containerPort: 3306
      name: mysql
---
apiVersion: v1
kind: Service
metadata:
  labels:
    app: mysql
  name: mysql
spec:
  ports:
  - name: mysql
    port: 3306
    targetPort: 3306
  selector:
    app: mysql
  type: ClusterIP
```

아래와 같이 접속 합니다.

```bash
$ kubectl exec -it mysql-0 -- bash

# 접속 후
$ mysql -uroot -p"$MYSQL_ROOT_PASSWORD"

# mysql 접속 후
mysql> CREATE DATABASE public;
mysql> USE public;
mysql> CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    money INT NOT NULL DEFAULT 0,
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## Data Generator

1초마다 데이터를 생성해서 넣는 python 스크립트를 구성 합니다.

```bash
$ python -m venv venv
$ source venv/bin/activate

$ pip install mysql-connector-python==9.4.0
```

```py
# @mysql-cdc-generator.py
# GPT로 생성 :wink:
import random
import string
import time
import mysql.connector

# -------------------------------
# 1️⃣ DB 연결 설정
# -------------------------------
config = {
    "host": "localhost",
    "user": "root",
    "password": "changeme",
    "database": "testdb",
    "autocommit": True,
}

conn = mysql.connector.connect(**config)
cursor = conn.cursor()

# -------------------------------
# 2️⃣ 헬퍼 함수들
# -------------------------------

def random_name(length=6):
    return ''.join(random.choices(string.ascii_letters, k=length))

def random_money():
    return random.randint(0, 10000)

def user_exists(user_id):
    cursor.execute("SELECT 1 FROM user WHERE id=%s", (user_id,))
    return cursor.fetchone() is not None

def create_user(user_id):
    name = random_name()
    money = random_money()
    try:
        cursor.execute(
            "INSERT INTO user (id, name, money) VALUES (%s, %s, %s)",
            (user_id, name, money)
        )
        print(f"[CREATE] id={user_id}, name={name}, money={money}")
    except mysql.connector.Error as err:
        print(f"[CREATE ERROR] id={user_id}: {err.msg}")

def update_user(user_id):
    # allow upsert!
    # if not user_exists(user_id):
    #     print(f"[SKIP UPDATE] id={user_id} not found.")
    #     return
    money = random_money()
    cursor.execute(
        "UPDATE user SET money=%s WHERE id=%s",
        (money, user_id)
    )
    print(f"[UPDATE] id={user_id}, money={money}")

def delete_user(user_id):
    if not user_exists(user_id):
        print(f"[SKIP DELETE] id={user_id} not found.")
        return
    cursor.execute("DELETE FROM user WHERE id=%s", (user_id,))
    print(f"[DELETE] id={user_id}")

# -------------------------------
# 3️⃣ 메인 루프
# -------------------------------

def run_cdc_simulation(iterations=1000, delay=0.5):
    for i in range(iterations):
        user_id = random.randint(1, 100)
        action = random.choice(["CREATE", "UPDATE", "DELETE"])

        if action == "CREATE":
            create_user(user_id)
        elif action == "UPDATE":
            update_user(user_id)
        elif action == "DELETE":
            delete_user(user_id)

        time.sleep(delay)

# -------------------------------
# 4️⃣ 실행
# -------------------------------
if __name__ == "__main__":
    try:
        run_cdc_simulation(iterations=50, delay=0.3)
    finally:
        cursor.close()
        conn.close()
```

## CDC 데이터 생성

```bash
$ kubectl port-forward mysql 3306
```

# Deploy Kafka Connect

Strimzi에선 `KafkaConnect` 리소스를 생성하면 Kafka Connect 클러스터를 디플로이 할 수 있습니다.

```yaml
# @strimzi.kafka-connect.yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaConnect
metadata:
  name: my-connect-cluster
spec:
  replicas: 1
  bootstrapServers: my-cluster-kafka-bootstrap:9092
  config:
    offset.flush.timeout.ms: 10000
    group.id: connect-cluster
    offset.storage.topic: connect-cluster-offsets
    config.storage.topic: connect-cluster-configs
    status.storage.topic: connect-cluster-status
  resources:
    requests:
      cpu: "1"
      memory: 1Gi
    limits:
      cpu: "1"
      memory: 1Gi
```

다만, 이 Kafka Connect에는 Debeizum Connector에 대한 jar 파일이 없기 때문에 직접 이걸 넣어줘야 합니다!

참고로 제가 테스트 하던 버전에서는 Kafka Connect가 `quay.io/strimzi/kafka:0.48.0-kafka-4.1.0` 이미지를 사용하고 있었습니다. 이걸 베이스 이미지로 사용해 아래와 같이 debezium의 플러그인을 추가 합시다. [maven](https://mvnrepository.com/artifact/io.debezium/debezium-connector-mysql)

```dockerfile
# dbz.kafka-connect.Dockerfile
FROM quay.io/strimzi/kafka:0.48.0-kafka-4.1.0

USER root

RUN curl -L -o /tmp/debezium-connector-mysql.zip \
  https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/3.1.3.Final/debezium-connector-mysql-3.1.3.Final-plugin.zip
RUN unzip /tmp/debezium-connector-mysql -d /opt/kafka/plugins/debzium-mysql

USER 1001
```

`nerdctl`로 도커 빌드를 수행합니다. Rancher Desktop에서는 일반 도커 엔진(docker desktop, colima)으로 로컬에서 빌드한 이미지를 읽을 수 없습니다 ㅠㅠ

그래서 `nerdctl`로 빌드 해야 하고, `--namespace k8s.io`를 부여하여 도커 빌드를 해야 합니다.
(이번에 하면서 드디어 방법을 찾아냈네요 ㅎㅎ)

```bash
$ nerdctl --namespace k8s.io build \
  -t my-kafka-connect-debezium:local \
  -f dbz.kafka-connect.Dockerfile .
```

## Re-deploy Kafka Connect with Debezium

```yaml
# @strimzi.kafka-connect.yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaConnect
metadata:
  name: my-connect-cluster
spec:
  ...
  image: my-kafka-connect-debezium:local  # 여기를 추가
  ...
```

```bash
$ kubectl apply -f strimzi.kafka-connect.yaml
```

잘 등록 되었는지 확인해봅니다.

```bash
$ kubectl exec -it my-connect-cluster-connect-0 -- bash

# 접속 후
$ curl http://localhost:8083/connector-plugins
[{"class":"io.debezium.connector.mysql.MySqlConnector","type":"source","version":"3.1.3.Final"},{"class":"org.apache.kafka.connect.mirror.MirrorCheckpointConnector","type":"source","version":"4.1.0"},{"class":"org.apache.kafka.connect.mirror.MirrorHeartbeatConnector","type":"source","version":"4.1.0"},{"class":"org.apache.kafka.connect.mirror.MirrorSourceConnector","type":"source","version":"4.1.0"}]
```

드디어! Debezium Connector를 띄우기 위한 사전 준비가 모두 끝났습니다.... ㅋㅋㅋㅋㅋ

# (Optional) Deploy Kafbat UI

좀더 편하게 디버그 하기 위해서 ㅎㅎ Kafbat UI도 디플로이 합니다.

```yaml
# @values.kafbat-ui.yaml
yamlApplicationConfig:
  kafka:
    clusters:
      - name: my-cluster
        bootstrapServers: my-cluster-kafka-brokers:9092
  auth:
    type: disabled
  management:
    health:
      ldap:
        enabled: false
```

```bash
$ helm repo add kafbat-ui https://kafbat.github.io/helm-charts
$ helm install kafbat-ui kafbat-ui/kafka-ui -f values.kafbat-ui.yaml
```

```bash
$ kubectl --namespace strimzi port-forward deploy/kafbat-ui-kafka-ui 8080:8080
```


# Deploy Debezium Connector

아래와 같이 Connector Config를 준비한다.

```json
# @source.debezium-mysql.json
{
  "name": "source.debezium-mysql",
  "config": {
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",

    "database.hostname": "mysql-0.mysql",
    "database.port": "3306",
    "database.user": "root",
    "database.password": "hello_debezium!",

    "database.server.id": "20251005",
    "database.server.name": "my-mysql",

    "database.include.list": "public",
    "table.include.list": "user",

    "schema.history.internal.kafka.bootstrap.servers": "my-cluster-kafka-brokers:9092",
    "schema.history.internal.kafka.topic": "__debezium_mysql_history",

    "include.schema.changes": "false",
    "snapshot.mode": "initial",

    "key.converter": "org.apache.kafka.connect.json.JsonConverter",
    "value.converter": "org.apache.kafka.connect.json.JsonConverter",
    "key.converter.schemas.enable": "true",
    "value.converter.schemas.enable": "true"
  }
}
```

아래 명령어로 json 파일로 생성

```bash
$ k exec -it my-connect-cluster-connect-0 -- bash

$ cat <<EOF > source.debezium-mysql.json
...
EOF
```

아래의 `config/validate` 엔드포인트에서 검수 먼저 진행

```bash
$ curl -X PUT localhost:8083/connector-plugins/io.debezium.connector.mysql.MySqlConnector/config/validate \
  -H "Content-Type: application/json" \
  -d '{
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",

    "database.hostname": "mysql.strimzi.svc.cluster.local",
    "database.port": "3306",
    "database.user": "root",
    "database.password": "hello_debezium!",

    "database.server.id": "20251005",
    "database.server.name": "my-mysql",
    "topic.prefix": "my-mysql",

    "database.include.list": "public",
    "table.include.list": "public.user",

    "schema.history.internal.kafka.bootstrap.servers": "my-cluster-kafka-bootstrap:9092",
    "schema.history.internal.kafka.topic": "__debezium_mysql_history",

    "include.schema.changes": "false",
    "snapshot.mode": "initial",

    "key.converter": "org.apache.kafka.connect.storage.StringConverter",
    "value.converter": "org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable": "false",
    "value.converter.schemas.enable": "false"
  }'
```

```bash
$ curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "source.debezium-mysql",
    "config": {
      "connector.class": "io.debezium.connector.mysql.MySqlConnector",
      "tasks.max": "1",

      "database.hostname": "mysql.strimzi.svc.cluster.local",
      "database.port": "3306",
      "database.user": "root",
      "database.password": "hello_debezium!",

      "database.server.id": "20251005",
      "database.server.name": "my-mysql",
      "topic.prefix": "my-mysql",

      "database.include.list": "public",
      "table.include.list": "public.user",

      "schema.history.internal.kafka.bootstrap.servers": "my-cluster-kafka-bootstrap:9092",
      "schema.history.internal.kafka.topic": "__debezium_mysql_history",

      "include.schema.changes": "false",
      "snapshot.mode": "initial",

      "key.converter": "org.apache.kafka.connect.storage.StringConverter",
      "value.converter": "org.apache.kafka.connect.storage.StringConverter",
      "key.converter.schemas.enable": "false",
      "value.converter.schemas.enable": "false"
    }
  }'
```

몇 번의 시도 끝에 겨우 성공 했습니다... ㅋㅋ

- `table.include.list`는 `{schema}.{table}` 포맷으로 풀네임을 적어야 합니다.
- Schema Registry가 있어야 `JsonConverter`를 쓸 수 있습니다.

```bash
$ curl -X GET http://localhost:8083/connectors
$ curl -X GET http://localhost:8083/connectors/source.debezium-mysql/status

# 디버그 용
$ curl -X POST http://localhost:8083/connectors/source.debezium-mysql/restart
$ curl -X DELETE http://localhost:8083/connectors/source.debezium-mysql
```

이제 kafbat-ui에 접속해서 확인해보면...!

![](/images/development/kafka/debezium-mysql-source-connector-topic.png){: .align-center}

CDC 데이터가 잘 들어옵니다!

