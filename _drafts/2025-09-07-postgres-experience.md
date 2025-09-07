---
title: "Postgres를 Datawarehouse로 쓸 수 있을까?"
toc: true
toc_sticky: true
categories: ["Develop", "Database"]
excerpt: "시작해보지 않았다면 몰랐을 Postgres의 이야기"
---

# 들어가며

작은 규모의 DW를 구축해야 할 일이 있어서 어떤 엔진으로 구축해볼까... 고민 하고 조언을 듣다가 "요즘엔 Postgres로 DW로 구축하던데요? 이 정도 규모면 PG로 충분할 것 같은데 한번 어떠세요??"라는 말을 듣고 "좋아! Postgres로 한번 해보자구!"라고 시작 했다가 피를 본 경험이 있습니다... ㅋㅋ

{% include figure image_path="/images/meme/hell-kitty.JPG" alt="hell kitty" caption="Hell?o" %}{: .fill style="width: 400px" .align-center .text-center }

그래도 덕분에 Postgres라는 DB 엔진은 한달 정도 경험해볼 수 있었고, 그 과정에서 PG라는 존재가 뭔지 그리고 왜 이걸 DW로 쓰면 안 되는지 등등을 깨다는 좋은 경험이었습니다 😓


# Mysql과의 차이는?

사실 Postgres가 Mysql보다 먼저 출시된 DB 엔진 걸 알고 있나요?? 저도 이번에 글을 쓰면서 알게 되었는데 ㅋㅋ Postgres가 1986년 UC Berkely의 프로젝트에서 시작했고, Mysql은 1995년 스웨덴의 Mysql AB에서 개발했다고 합니다. 그래서 PG는 순수 오픈소스인 반면, Mysql은 오픈소스 라이선스와 상업 라이선스가 둘다 있는 엔진 입니다. 2009년 MariaDB가 출범한 이유도 이런 Mysql의 상업화를 우려하여 나오게 된 것 입니다.

Postgres는 `ANSI SQL` 표준을 엄격히 준수하는 반면, Mysql은 `ANSI SQL`과 다른 부분이 종종 있다고 합니다.

또, Postgres는 `JSON` 객체에 대한 처리를 일찍이 지원 했지만, Mysql은 비교적 최근에 와서야 지원하기 시작 했습니다.


# 특수한 커맨드가 있었어요

PG에는 SQL이 아닌 특수 명령어들이 있습니다. 이걸 "메타커맨드"라고 하고 이걸로 테이블 목록을 조회하거나, 테이블 명세를 조회할 수 있습니다.

```bash
\dn # schema 조회
\dt # table 조회
\dv # view 조회
\d  # table 명세 조회
```

정확히 말하자면, SQL은 아니고 이 메타커맨드는 `psql`라는 Postgres의 전용 SQL 인터페이스에서만 사용할 수 있는 특수한 명령어 입니다.


## Postgres는 다양한 인덱스를 지원해요

Postgres은 다양하면서도 강력한 인덱스 기능들을 제공합니다.

대표적으로 "**블룸 필터(Bloom Filter)**"는 해시 기반으로 특정 원소가 존재하는지 아닌지를 상수 시간 만에 조회할 수 있습니다. 단순 B-Tree 기반의 인덱스보다 훨씬 빠르죠!

그리고 "**GIN(Generalized Inverted Index)**"를 지원합니다. 일반화된 역인덱스로 빠른 검색과 키워드 기반 검색을 지원하며, 이를 바탕으로 JSON 객체에 대해서도 빠른 검색과 필터링을 지원합니다.

참고로 요 블룸 필터와 GIN 인덱싱을 MySQL에선 아직 지원하지 않습니다.


# 이걸 DW로 쓸 수 있는거 맞아?

이렇게 좋은 점들이 있으니 Postgres 이 녀석 Data Warehouse로 써볼 수 있을 것 같기도...!? 라는 생각이 듭니다!

하지만... 실제로 Postgres로 DW를 구축해보니...

생각보다 성능이 잘 나오지 않는 현상을 경험 했습니다. Aggregation은 좀 괜찮은데 특히 Join 쿼리는 세월이 지나도 끊나지 않는 느낌이었어요. 이 정도 규모에서 빅쿼리나 스노플레이크, Databricks에선 이렇지 않았는데...

아무래도 열기반 엔진과 행기반 엔진의 차이가 큰 것 같습니다. 데이터를 로딩하는 방식에서 필요한 컬럼만 가져오느냐 아니면 행의 뭉치를 가져오느냐에 따라 부하가 다르게 걸리는 것 같아요.

또, 이런 부하 때문에 여러 쿼리를 동시에 수행하는 것도 어려움이 있었습니다. 예를 들어, 빅쿼리에서는 매번의 실행이 완전 독립적이기 때문에 동시성을 크게 신경 쓰지 않아도 되었는데요. Postgres DW는 하나의 머신이 유입되는 쿼리를 나눠서 처리하는 방식이기 때문에 머신에 부하가 더 많이 걸리지 않을까요. 뭐 이런 건 스노플이나 Databricks도 비슷할 것 같지만... (생각보다 빅쿼리가 괜찮은 느낌.)


## Index를 잘 걸어주면 그래도 좀 나아요

그래도 Postgres의 Index를 촘촘히 걸어주면 어찌저찌 쿼리가 끝나는 건 볼 수 있었어요. 예를 들어,

```sql
SELECT
    user.id AS uid,
    user.gender,
    purchase.id AS pid,
    purchase.amount
    purchase.ts
FROM
    purchase
    LEFT JOIN user
    ON purchase.user_id = user.id
WHERE
    purchase.ts >= '2025-08-01'
```

와 같은 쿼리가 있을 때는

- Join 기준이 되는 `purchase.user_id`와 `user.id` 컬럼에 블룸 필터 Index를 걸어주고,
- 시계열 컬럼인 `purchase.ts`에는 B-Tree 인덱스를 걸어주면

Join 성능이 크게 개선 되는 걸 발견 했습니다. (희망이!!)

다만, 이 인덱싱을 DA 분들이 모두 직접 관리해주어야 하고, 인덱스 튜닝이 무조건적으로 필요한게 미래에 허들이 될거라고 판단 했습니다.
(애초에 Postgres로 운영하는 DW도 길어야 1년 정도 사용하고 다른 DW 대안을 찾지 않을까 싶긴 했습니다.)


## 열 기반 Extension은 AWS RDS Postgres에선 쓸 수 없어요

"Postgres를 DW를 쓸 수 있다!"고 주장하는 글들을 보면, "이제는 PG도 Columnar를 지원해요!"라는 이야기가 따라나오는 것 같습니다. 실제로 PG는 Columnar를 지원합니다! 그런데 AWS RDS Postgres에서 지원을 안 합니다 ㅠㅠ

[cstore_fdw](https://github.com/citusdata/cstore_fdw)라는 Columnar Storage Extension을 쓰라고 하는 것 같아요! 그런데, [AWS RDS Postgres에서 지원하는 Extension 목록](https://docs.aws.amazon.com/ko_kr/AmazonRDS/latest/UserGuide/PostgreSQL.Concepts.General.FeatureSupport.Extensions.html#PostgreSQL.Concepts.General.Extensions.Trusted)에 해당 Extension이 없습니다... ㅠㅠ;;

이젠 정말 마지막 희망이 없어진 느낌....;;

# 이런 거를 좀더 알아봤으면

Postgres의 조인 방식과 MySQL의 조인 방식이 다르다고 했던 것 같기도...

- https://github.com/citusdata/citus
  - Distributed PostgreSQL as an extension
