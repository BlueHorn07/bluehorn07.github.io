---
title: "Define Custome Kafka Connect Transform"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: ""
---

# 필수 항목들

- `apply()` 함수가 있어야 함.
- `configure()` 함수가 있어야 함.

자세한 인터페이스는 javadoc 참고!

➡️ [javadoc / Transformation](https://docs.confluent.io/platform/current/connect/javadocs/javadoc/org/apache/kafka/connect/transforms/Transformation.html)


# 사전 지식

## fat-jar

일반 jar 파일에는 내가 정의하고 개발한 클래스에 대한 정보만 들어있습니다. 개발에서 사용한 외부 라이브러리의 코드는 포함되지 않습니다. 그래서 실행할 때 `ClassNotFoundException`이 나는 경우가 있습니다.

fat-jar는 내 코드와 코드에서 사용하는 라이브러리의 `.class`들을 모두 하나의 jar에 통째로 묶은 jar를 말합니다. 그래서 이 파일 하나만 있어도 어플리케이션의 실행과 배포가 가능합니다!

## shadowJar

https://github.com/GradleUp/shadow


```bash
./gradlew clean jar
# build/libs/fatjar-hello-0.1.0.jar 파일이 생성됨.

./gradlew clean shadowJar
# build/libs/fatjar-hello-0.1.0-all.jar 파일이 생성됨.
```

## fat-jar 여부 확인 방법

```bash
$ jar tf build/libs/your-app.jar
```

- 일반 jar
  - `com/example/...` 같은 내가 정의한 패키지의 클래스만 있음.
- fat-jar
  - `com/google/gson/`, `org/apache/commons/` 등 외부 라이브러리 클래스가 함께 있음.

```bash
$ jar tf fatjar-hello-0.1.0.jar
META-INF/
META-INF/MANIFEST.MF
com/
com/example/
com/example/App.class
```

```bash
$ jar tf fatjar-hello-0.1.0-all.jar
META-INF/MANIFEST.MF
com/example/App.class
META-INF/maven/com.google.code.gson/gson/pom.properties
META-INF/maven/com.google.code.gson/gson/pom.xml
META-INF/proguard/gson.pro
com/google/gson/ExclusionStrategy.class
com/google/gson/FieldAttributes.class
com/google/gson/FieldNamingPolicy$1.class
...
```


# Echo Transform

참고로 현재 Strimzi Kafka Connect의 java 버전은 아래와 같다.

```bash
openjdk 17.0.16 2025-07-15 LTS
OpenJDK Runtime Environment (Red_Hat-17.0.16.0.8-1) (build 17.0.16+8-LTS)
```

```dockerfile
COPY ./build/libs/ /opt/kafka/plugins/transform
```

재배포 후 잘 등록 되었는지 확인

```bash
$ curl -s http://localhost:8083/connector-plugins?connectorsOnly=false
```

이 엔드포인트는

```java
public interface Transformation<R extends ConnectRecord<R>> {
    R apply(R record);
    ConfigDef config();
    void close();
    void configure(Map<String, ?> configs);
}
```

`Echo<R extends ConnectRecord>`에서 `R`은 제네릭 타입을 사용해서 `SourceRecord` 그리고 `SinkRecord`를 모두 다룰 수 있도록 합니다.

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
    "value.converter.schemas.enable": "false",


    "transforms": "echo",
    "transforms.echo.type": "com.example.connect.smt.Echo"
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
      "value.converter.schemas.enable": "false",

      "transforms": "echo",
      "transforms.echo.type": "com.example.connect.smt.Echo"
    }
  }'
```

# With Schema Registry




https://docs.confluent.io/platform/current/connect/transforms/custom.html

kafka connect transform 직접 개발해보기

특히 json 값들을 핸들링 하는 transform