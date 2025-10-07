---
title: "Define Custome Kafka Source Connector"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: ""
---

# 들어가며

요즘에 Kafka에 수집한 데이터를 여러 타겟 DB로 적재하는 것을 많이 수행하고 있습니다. 그런데 몇몇 오픈소스 Connector의 동작이 마음에 안 들어서 수정 하고 싶은 적들이 있더라구요...;; 그래서 이번 기회에 커스텀 커넥터는 어떻게 만드는지, Kafka의 Connector API를 좀 익혀보려고 합니다.

# HttpPollSourceConnector

주기적으로 REST API를 호출해 응답값을 받은 다음 이것을 Kafka 토픽에 적재하는 Source Connector를 만들고자 합니다.

입력 받는 값으로는

- REST API의 엔드포인트
- REST API 호출 주기

Java에서 REST API에 대한 HTTP 호출이 필요합니다. Java에서는 `okhttp`를 사용하면 된다고 합니다.

## 구성

- `SourceConnector`의 서브클래스
  - 커넥터 전체의 수명주기, Config 정의, 태스크 분배를 담당
- `SourceTask`의 서브클래스
  - 실제 소스 시스템에서 데이터를 읽어와 `SourceRecord`를 만드는 실행 단위

필수 구현해야 하는 함수들

- `SourceConnector`
  - `String version()`
  - `void start(Map<String, String> props)`
    - 커넥터 시작 시 Config 검증 및 초기화
  - `Class<? extends Task> taskClass()`
    - 커넥터 실행에 사용할 태스크 클래스를 반환
    - 보통 구현한 `SourceTask` 서브클래스에 대해 `{...SourceTask}.class`로 처리
  - `List<Map<String, String>> taskConfigs(int maxTasks)`
    - 태스크 갯수 만큼 config 슬라이스 생성
  - `ConfigDef config()`
    - 커넥터 config에 대한 스키마 (타입, 필수 여부, 기본값, 설명)
  - `void stop()`
    - 리소스 정리(쓰레드/클라이언트 종료 등)
- `SourceTask`
  - `String version()`
  - `void start(Map<String, String> props)`
    - 소스 시스템에 연결, 오프셋 복원, 클라이언트 준비
  - `List<SourceRecord> poll() throws InterruptedException`
    - 주기적으로 소스에서 데이터 읽어서 레코르를 뭉치를 반환
    - 예외 처리
      - 재시도 가능한 상황은 `RetriableException`으로 처리
  - `void stop()`
    - 풀링 루프/IO 정리
  - `void commitRecord(SourceRecord record)`
    - 해당 레코드가 카프카에 안전히 커밋 되었을 때 실행할 콜백
    - 외부에 커밋 해야 하거나 체크포인트 연동이 필요할 때 사용할 것
  - `void commit()`
    - 배치 단위 커밋 훅 (프레임워크가 호출)

```java
public class MySourceConnector extends SourceConnector {
  @Override public String version() { return "0.1.0"; }
  @Override public void start(Map<String,String> props) { /* validate/init */ }
  @Override public Class<? extends Task> taskClass() { return MySourceTask.class; }
  @Override public List<Map<String,String>> taskConfigs(int maxTasks) { /* shard */ }
  @Override public void stop() { /* cleanup */ }
  @Override public ConfigDef config() { return new ConfigDef()/* .define(...) */; }
}

public class MySourceTask extends SourceTask {
  @Override public String version() { return "0.1.0"; }
  @Override public void start(Map<String,String> props) { /* client + restore offset */ }
  @Override public List<SourceRecord> poll() throws InterruptedException {
    // fetch -> build SourceRecord(partitionMap, offsetMap, topic, key, value)
  }
  @Override public void commitRecord(SourceRecord record) { /* optional */ }
  @Override public void stop() { /* cleanup */ }
}
```

## 커넥터 등록 체크

```bash
$ curl -s http://localhost:8083/connector-plugins

$ curl -s http://localhost:8083/connector-plugins | jq .
[
  {
    "class": "com.example.connect.HttpPollSourceConnector",
    "type": "source",
    "version": "0.1.0"
  },
  {
    "class": "io.debezium.connector.mysql.MySqlConnector",
    "type": "source",
    "version": "3.1.3.Final"
  },
  ...
]
```

## 커넥터 디플로이

```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "source.custom-http-poll",
    "config": {
      "connector.class": "com.example.connect.HttpPollSourceConnector",
      "tasks.max": "1",

      "http.url": "https://jsonplaceholder.typicode.com/todos",
      "topic": "todos",
      "poll.interval.ms": "500",

      "key.converter": "org.apache.kafka.connect.storage.StringConverter",
      "value.converter": "org.apache.kafka.connect.storage.StringConverter",
      "key.converter.schemas.enable": "false",
      "value.converter.schemas.enable": "false"
    }
  }'

# 디플로이 후
$ curl -s http://localhost:8083/connectors | jq .
$ curl -s http://localhost:8083/connectors/source.custom-http-poll | jq .
$ curl -s http://localhost:8083/connectors/source.custom-http-poll/status | jq .
```

## `taskConfigs()` 함수

Connector가 여러 Task로 나뉘어 실행될 때, 각 Task에 전달할 설정값을 만들어주는 메서드.

Kafka Connector를 등록하면, 내부적으로 여러 개의 Task를 병렬로 띄워서 데이터를 처리 합니다.

`taskConfigs()`는 생성된 Task들이 각각 어떤 설정을 가지고 실행되는지를 정의하는 함수 입니다.

만약 `tasks.max=3`이라면, Connect는 `taskConfigs(3)`을 호출합니다.

## Source Offset

Connect 워커는 소스 시스템에서 데이터를 어디까지 읽었는지를 기록 해둡니다. 이 기록은 `SourceRecord()`를 만들 때 기록 되며, 내부 토픽인 `__connect_offsets`에 커밋 되어 저장 됩니다.

## 성공!

![](/images/development/kafka/custom-http-poll-source-connector.png){: .align-center}

