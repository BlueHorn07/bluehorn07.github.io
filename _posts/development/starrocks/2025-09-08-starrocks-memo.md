---
title: "Starrocks Memo"
toc: true
toc_sticky: true
categories: ["Starrocks"]
excerpt: ""
---

Starrocks 작업하면서 자꾸 까먹는 것 같아서 이번에 한번에 좀 정리해두려고 합니다!

# 버전 체크

```bash
-- mysql client의 버전
SELECT VERSION();

-- starrocks 엔진의 버전
SELECT CURRENT_VERSION();
```

# FE 노드

```sql
-- FE 노드 조회
SHOW FRONTENDS;
```

FE 노드는 Leader, Follower, Observer로 나뉨.

- 공통
  - 모든 FE 노드는 읽기 쿼리를 처리함. (읽기 요청에 대한 부하를 분산)
- Leader
  - 전체 클러스터의 메타데이터를 관리함.
  - 테이블 생성/변경/삭제를 실제로 수행함.
  - 쓰기 작업을 수행함.
- Follower
  - 메타 데이터를 리더로부터 복제하고 동기화.
  - 리더 장애시 Raft 선출을 통해 새로운 리더가 될 수 있음.
- Observer
  - 투표권이 없음.
  - 메타데이터를 리더로부터 비동기 복제함. (좀더 느슨한 복제)
  - 주로 읽기 요청 붓나을 위해 사용함.


# BE 노드

```sql
-- BE 노드 조회
SHOW BACKENDS;
```

# Materialized Views

## 조회

```sql
SHOW MATERIALIZED VIEWS;
```

## MV Refresh 작업 현황 확인

```sql
SELECT *
FROM information_schema.task_runs
WHERE DEFINITION LIKE '%{mv_name}%'
ORDER BY CREATE_TIME DESC
LIMIT 20;
```

## Refresh 전략에 대해

MV 최초 생성 때는 `REFRESH DEFERRED MANUAL`로 만들고, 특정 파티션 범위를 지정하면서 하나씩 REFRESH 처리하는게 안전함.

```sql
REFRESH MATERIALIZED VIEW {mv_name}
PARTITION START ("2025-01-01") END ("2025-02-01");
```

## auto_refresh_partitions_limit

`auto_refresh_partitions_limit` 이걸 걸어줘야 백그라운드 리프레시가 최신 데이터를 대상으로만 진행됨.

```sql
ALTER MATERIALIZED VIEW {name}
SET ("auto_refresh_partitions_limit" = "1");
```

그런데 `REFRESH ASYNC EVERY ...`인 경우에만 적용 가능합니다. `DEFERRED MANUAL`인 경우는 적용 안 됩니다.


# 클러스터 스펙

https://docs.starrocks.io/docs/deployment/plan_cluster/#cpu-and-memory

- FE
  - CPU는 조금만 있어도 됨.
  - CPU 8 core, Memory 16 Gb가 권장
  - 100 Gb HDD 권장
- BE
  - CPU와 Memory를 많이 씀.
  - CPU 16 core, Memory 64 Gb
  - 저장하는 용량에 따라 다름.
