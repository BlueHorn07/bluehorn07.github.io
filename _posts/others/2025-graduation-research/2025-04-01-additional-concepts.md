---
title: "Graduation Research 1: Additional Concepts"
toc: true
toc_sticky: true
categories: ["Graduation Research"]
excerpt: ""
---

2025년 저는 데이터베이스 랩에 컨택하여 학부 졸업 연구 주제를 받아서 진행하고 있습니다. 저의 주제는 "Continuous Subgraph Matching"과 관련해 코드를 최적화 하고 개선하는 것으로 그래프 쿼리 관련 논문을 읽고, C++ 코드를 튜닝하고 있습니다. 졸업 마지막 학기에 듣는 수업인데 많은 노하우와 경험을 쌓고 졸업하기를 기대하고 있습니다 ㅎㅎ
{: .notice }

# 들어가며

졸업연구를 하면서 곁다리로 찾아본 내용들, 그리고 논문에서 잠깐 언급된 내용들을 모아서 이 포스트에 모아둡니다.

# Sideways Information Passing

SQL에서 성능 최적화를 위해 수행하는 테크닉 입니다.

예를 들어, `Orders`(order_id, customer_id, data) 테이블과 `Customers`(customer_id, name, country) 테이블이 있고, 아래와 같은 쿼리를 작성하였습니다.

```sql
SELECT *
FROM Orders O
JOIN Customers C ON O.customer_id = C.customer_id
WHERE C.country = 'Korea';
```

위의 쿼리를 테이블 Join을 먼저 수행하고, 조인 결과에 대해 `C.country = 'Korea'` 조건을 적용 합니다.

SIP 테크닉은 `WHERE`절을 조인 대상인 `Customers` 테이블에 먼저 적용하고, 테이블 조인을 수행할 수 있도록 합니다.

이렇게 하면 테이블 조인에서 $N \times M$이 아니라 $N \times (M - \alpha)$가 되기 때문에 더 적은 조인 연산으로 같은 결과를 얻을 수 있습니다!

<br/>

SIP는 자동으로 되는겨? 아님 사람이 해야 하는겨?

자동으로 되는 거라고 합니다! 데이터베이스의 옵티마이저나 "내부적으로" 수행하는 쿼리 최적화 기법이라고 합니다. 다만, 사용자도 쿼리 옵티마이저가 구조를 잘 인식할 수 있도록 쿼리 자체를 잘 작성 해줘야 합니다.

신기하게도 Apache Spark에도 SIP 최적화가 적용 되어 있다고 합니다! Spark의 "Catalyst Optimizer"는 SIP를 포함해 다양한 논리/물리 쿼리 최적화를 수행해줍니다.


## vs. Predicate Pushdown

SIP을 알아보면서 헷갈렸던게, Predicate Pushdown 최적화 였습니다.

Predicate Pushdown은 아래와 같은 쿼리에서 수행 되는데,

```sql
SELECT *
FROM Customers
WHERE country = 'Korea';
```

쿼리를 데이터가 파일/테이블 스캔 하는 과정에서 `WHERE` 조건을 먼저 적용합니다. 이것은 메모리에 필요한 행만 로드해서 리소스를 아낄 수 있습니다!

반면에, SIP는 조인 과정에서 이뤄지는 최적화 입니다. 조인 결과에 `WHERE`을 수행하지 않고, 소스 테이블에 `WHERE`을 넘겨버리는 것이 SIP의 컨셉 입니다.

두 최적화 기법은 서로 상충 되는 것이 아닙니다. 그래서 SIP가 적용된 후에 소스 테이블에 대해서 Predicate Pushdown이 일어나는 것도 가능 합니다.

# Database Algebra

놀랍게도... 제가 데이터베이스 수업을 안 듣고 졸업을 합니다... ㅋㅋ 그래서 논문의 표기 중에 첨보는 것들이 있어서 정리합니다 ㅋㅋ (정보처리산업기사 준비할 때 보긴 했었네요)

## Projection $\pi$

SQL의 `SELECT` 연산 같은 것

## Joins

- Theta Join $\Join_{\theta}$
  - Join 조건을 `ON` 절에 직접 명시하는 조인 입니다.
- Natural Join $\Join$
  - 두 소스 테이블에 있는 공통 이름&타입 컬럼을 기준으로 알아서 조인을 수행합니다.


# MVCC, Multi-version Concurrency Control

데이터베잉스나 동적 시스템에서 "동시성"을 제어하기 위해서 여러 버전의 데이터를 동시에 유지하는 테크닉 입니다.

TurboFlux(2018) 논문에서 CSM 문제를 분산처리 환경에서 다루는 방법에 대해 언급할 때, 잠깐 나왔습니다.

회사에서 Delta-lake 포맷을 사용하고 있는데, 이게 대표적인 MVCC 프레임워크라고 합니다.


# Dataset

졸업 프로젝트 실험에 사용한 데이터셋의 목록 입니다.

- LSBench
  - Undirected, Labeled
  - Directed, Labeled
- Livejournal
  - Undirected, Unlabeled
- Netflow
  - Directed, Labeled

## Labeled vs. Unlabeled

Labeled와 Unlabeled의 차이는 Edge에 라벨이 있는지 여부 입니다.

```sh
@lsbench (labeled)
v 0 0
v 1 0
v 2 0
v 3 0
v 4 0
v 5 0
e 0 3 33
e 1 2 42
e 2 3 42
e 3 4 41
e 3 5 32
```

LSBench는 엣지에 라벨이 부여 되어 있습니다: `e v1, v2 label`

```sh
@livejournal (unlabeled)
v 0 26
v 1 8
v 2 9
v 3 26
v 4 8
v 5 14
e 0 2 0
e 0 3 0
e 0 5 0
e 1 3 0
e 3 4 0
```

livejournal은 엣지에는 라벨이 없지만, 노드에는 라벨이 존재합니다.
