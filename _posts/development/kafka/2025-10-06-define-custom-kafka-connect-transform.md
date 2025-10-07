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

## 코드 작성

참고로 현재 Strimzi Kafka Connect의 java 버전은 아래와 같다.

```bash
openjdk 17.0.16 2025-07-15 LTS
OpenJDK Runtime Environment (Red_Hat-17.0.16.0.8-1) (build 17.0.16+8-LTS)
```

커스텀 transformer를 정의하기 위해선 `Transformation`의 인터페이스를 모두 구현해야 합니다.

```java
public interface Transformation<R extends ConnectRecord<R>> {
    R apply(R record);
    ConfigDef config();
    void close();
    void configure(Map<String, ?> configs);
}
```

코드를 작성 해봅시다!

`Echo<R extends ConnectRecord>`에서 `R`은 제네릭 타입을 사용해서 `SourceRecord` 그리고 `SinkRecord`를 모두 다룰 수 있도록 합니다.

```java
package com.example.connect.smt;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Echo<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger log = LoggerFactory.getLogger(Echo.class);

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("Echo SMT configured");
    }

    @Override
    public R apply (R record) {
        log.info("Echo topic={} partition={} offset={} ts={} key={} value={} headers={}",
            record.topic(),
            record.kafkaPartition(),
            null,
            record.timestamp(),
            String.valueOf(record.key()),
            String.valueOf(record.value()),
            String.valueOf(record.headers())
        );
        return record;
    }

    @Override public ConfigDef config() { return new ConfigDef(); }
    @Override public void close() {}
}
```

모니터링 용 코드

```java
@Override
public R apply (R record) {
    if (record.value() == null) return record;

    Struct envelope = (Struct) record.value();
    Schema schema = envelope.schema();
    for (Field field: schema.fields()) {
        log.info("field={} type={}", field.name(), field.schema().type());
    }

    if (envelope.get("before") != null) {
        log.info("before={}", String.valueOf(envelope.get("before")));
    }
    if (envelope.get("after") != null) {
        log.info("after={}", String.valueOf(envelope.get("after")));
    }
    ...
}
```

## Struct 타입을 Json String으로 변환

```java
@Override
    public R apply (R record) {
        if (record.value() == null) return record;

        Struct envelope = (Struct) record.value();
        Schema schema = envelope.schema();
        Map<String, Object> map = structToMap(envelope);
        String json = new Gson().toJson(map);

        return record.newRecord(
            record.topic(),
            record.kafkaPartition(),
            record.keySchema(),
            record.key(),
            record.valueSchema(),
            json,
            record.timestamp()
        );
    }

    private static Map<String, Object> structToMap(Struct struct) {
        Map<String, Object> map = new HashMap<>();

        if (struct == null || struct.schema() == null) return map;

        for (Field field: struct.schema().fields()) {
            Object fieldValue = struct.get(field.name());
            if (fieldValue instanceof Struct) {
                map.put(field.name(), structToMap((Struct) fieldValue));
            } else if (fieldValue instanceof List<?>) {
                map.put(field.name(), convertList((List<?>) fieldValue));
            } else if (fieldValue instanceof Map<?, ?>) {
                map.put(field.name(), convertMap((Map<?, ?>) fieldValue));
            } else {
                map.put(field.name(), fieldValue);
            }
        }

        return map;
    }

    private static List<Object> convertList(List<?> list) {
        List<Object> newList = new ArrayList<>();
        for (Object item: list) {
            if (item instanceof Struct) {
                newList.add(structToMap((Struct) item));
            } else {
                newList.add(item);
            }
        }
        return newList;
    }

    private static Map<String, Object> convertMap(Map<?, ?> map) {
        Map<String, Object> newMap = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry: map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Struct) {
                newMap.put(entry.getKey().toString(), structToMap((Struct) value));
            } else {
                newMap.put(entry.getKey().toString(), value);
            }
        }
        return newMap;
    }
```

## 도커 빌드

로컬에서 jar 빌드 후, 도커 빌드 시점에 `COPY`로 옮겨줍니다.

```dockerfile
...
COPY ./build/libs/ /opt/kafka/plugins/transform
...
```

재배포 후 잘 등록 되었는지 확인

```bash
$ curl -s http://localhost:8083/connector-plugins?connectorsOnly=false
$ curl -s http://localhost:8083/connector-plugins?connectorsOnly=false | jq .
```

`connectorsOnly=false`까지 추가해줘야 source, sink, converter, transformation 모두 나옵니다.

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

그리고 디플로이 한 kafka connect Pod의 로그를 확인해보면, logger로 기록한 것이 표시됩니다.

```bash
$ kubectl logs my-connect-cluster-connect-0
2025-10-06 09:47:21 INFO  [task-thread-source.debezium-mysql-0] Echo:21 - Echo topic=my-mysql.public.user partition=null offset=null ts=null key=Struct{id=36} value=Struct{before=Struct{id=36,name=TcIQHp,money=8978,created_ts=2025-10-06T09:42:53Z,updated_ts=2025-10-06T09:42:53Z},after=Struct{id=36,name=TcIQHp,money=6401,created_ts=2025-10-06T09:42:53Z,updated_ts=2025-10-06T09:43:33Z},source=Struct{version=3.1.3.Final,connector=mysql,name=my-mysql,ts_ms=1759743813000,db=public,ts_us=1759743813000000,ts_ns=1759743813000000000,table=user,server_id=1,file=binlog.000002,pos=101897,row=0,thread=56},op=u,ts_ms=1759744040681,ts_us=1759744040681781,ts_ns=1759744040681781775} headers=ConnectHeaders(headers=)
2025-10-06 09:47:21 INFO  [task-thread-source.debezium-mysql-0] Echo:21 - Echo topic=my-mysql.public.user partition=null offset=null ts=null key=Struct{id=46} value=Struct{after=Struct{id=46,name=wgThpR,money=2367,created_ts=2025-10-06T09:43:33Z,updated_ts=2025-10-06T09:43:33Z},source=Struct{version=3.1.3.Final,connector=mysql,name=my-mysql,ts_ms=1759743813000,db=public,ts_us=1759743813000000,ts_ns=1759743813000000000,table=user,server_id=1,file=binlog.000002,pos=102243,row=0,thread=56},op=c,ts_ms=1759744040681,ts_us=1759744040681933,ts_ns=1759744040681933275} headers=ConnectHeaders(headers=)
```

## 레코드의 스키마 체크

`import org.apache.kafka.connect.data.Schema`

# Transform Config

Transform에서 파라미터로 전달받아 사용하는 것 가능 합니다.

`SAMPLE_RATE_CONFIG`를 정의하고, 이를 바탕으로 Echoing을 얼마나 할지 제어 해봅시다.

```java
    private static final String SAMPLE_RATE_CONFIG = "sample.rate";
    private static final double DEFAULT_SAMPLE_RATE = 0.01;

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
        .define(
            SAMPLE_RATE_CONFIG,
            ConfigDef.Type.DOUBLE,
            DEFAULT_SAMPLE_RATE,
            ConfigDef.Range.between(0.0, 1.0),
            ConfigDef.Importance.LOW,
            "Sample rate for the echo transform"
        );

    private double sampleRate;
    private final Random random = new Random();


    @Override
    public void configure(Map<String, ?> configs) {
        AbstractConfig config = new AbstractConfig(CONFIG_DEF, configs);
        this.sampleRate = config.getDouble(SAMPLE_RATE_CONFIG);

        log.info("Echo SMT configured with sample rate: {}", this.sampleRate);

    }

    @Override
    public R apply (R record) {
        if (record.value() == null) return record;

        Struct envelope = (Struct) record.value();
        Schema schema = envelope.schema();
        Map<String, Object> map = structToMap(envelope);
        String json = new Gson().toJson(map);

        if (this.random.nextDouble() < this.sampleRate) {
            log.info("Echoing record: {}", json);
        }
        ...
    }
```

# With Schema Registry

TBD



# 맺음말

이어지는 포스트에선 간단한 커스텀 Source Connector를 정의해보겠습니다!

➡️ [Define Custome Kafka Source Connector](/2025/10/07/define-custom-kafka-connector/)


# References

- [Create Custom Kafka Connect Single Message Transforms for Confluent Platform](https://docs.confluent.io/platform/current/connect/transforms/custom.html)
