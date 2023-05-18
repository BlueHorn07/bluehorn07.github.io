---
title: "Document CRUD"
layout: post
tags: ["elastic-search"]
---

ElasticSearch에서 레코드의 역할을 하는 Document의 CRUD에 대해 살펴본다. 테이블 역할을 하는 Index에 대한 CRUD는 [이전 포스트]({{"/2023/05/15/ElasticSearch-Index-CRUD.html" | relative_url}})에서 다뤘다.

# Create

```json
POST <index-name>/_doc
{
  "id": 11,
  "name": "foo"
}
```

```json
{
  "_index" : "my-index",
  "_type" : "_doc",
  "_id" : "nuJMKogBDapnWhkqYzBD",
  "_version" : 1,
}
```

생성할 Document의 ID를 지정할 수도 있다.

```json
POST <index-name>/_doc/<id>
{
  "id": 11,
  "name": "foo"
}
```

```json
{
  "_index" : "my-index",
  "_type" : "_doc",
  "_id" : "123",
  "_version" : 1,
}
```

# Read

Index에 저장된 전체 Document를 조회하는 API는 아래와 같다.

```
GET <index-name>/_search
```

```json
{
  "hits" : {
    "total" : {
      "value" : 4,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      { ... },
      { ... },
      { ... },
      { ... },
    ]
  }
}
```

만약 Document 하나만 단독으로 얻고 싶다면, 아래의 API를 사용하라.

```
GET <index-name>/_doc/<document-id>
```

## Query DSL

ES는 이름 그래도 검색에 특화된 플랫폼이다. Domain Specific Language, DSL를 통해 Document를 검색할 수 있다.

DSL의 문법은 크게 Filter Context와 Query Context로 나뉜다. 둘의 차이는 아래와 같다.

- Filter Context
  - 정확한 검색을 제공한다.
  - 단, 검색 키워드에 대한 유사도(score)는 제공하지 않는다.
- Query Context
  - 검색 키워드를 바탕으로 유사도 방식의 검색을 제공한다.
  - 검색 결과가 부정확 할 수 있다.

Filter Context, Query Context 둘을 함께 사용해 DSL을 날릴 수도 있다!

Query DSL의 구조는 아래와 같다.

```json
{
  // 반환 결과 갯수
  "size": xx
  
  // 문서의 시작 시점. 기본값은 0
  "from": xx

  // 기본값은 무한대
  "timeout": xx

  // 검색의 조건문: Filter Context, Query Context
  "query": { ... }

  // 결과 정렬 방식
  "sort": { ... }

  "_source": { ... } // 검색 결과에 노출할 필드
  "aggs": { ... } // 통계/집계 데이터
}
```

이번에는 Query DSL의 기본인 Filter Context, Query Context만 훑어보자.

```json
POST <index-name>/_search
{
  "query": {
    "bool": { ... } // Filter Context
    "match": { ... } // Query Context
  }
}
```

### Filter Context

```json
POST <index-name>/_search
{
  "query": {
    "bool": {
      "filter": {
        "match": { "name": "foo" }
      }
    }
  }
}
```

### Query Context

```json
POST <index-name>/_search
{
  "query": {
    "match": {
		  "name": "foo"
    }
	}
}
```

# Update

```json
POST <index-name>/_doc/<id>
{
  "id": 11,
  "name": "foo"
}
```

`id`를 지정해서 Document를 생성하는 걸 한번 더 하면, 기존 Document의 값이 갱신된다.

단, 이것은 기존에 있던 Document가 삭제 되는 것은 아니다. 새로운 `Document`를 추가하고, 그것을 해당 `id`의 Document의 새로운 버전(`version`)으로 올리는 것이다. 즉, Overwrite가 아니라 Replace인 셈이다.

그러나 이 방식은 Document 자체를 새로 만드는 것이다. 필드 하나의 값만 변경하고 싶다면, 어떻게 해야 할까?

```json
POST <index-name>/_doc/<id>/_update
{
  "doc": {
    "id": 11,
    "name": "foo"
  }
}
```

이렇게 하면, 기존 Document에 해당 필드가 있다면, 필드 값을 갱신하고, 없던 필드는 추가된다. 물론 이 경우에도 문서의 `version`은 올라간다.

# Delete

삭제는 간단하다.

```json
DELETE <index-name>/_doc/<id>
```

# Bulk API

여러 명령을 배치(batch)로 수행하는 방법이다. 각 명령을 따로 수행하는 것보다 훨씬 빠르고, 오버헤드가 적다. 대용량의 indexing, update, delete 작업이 필요할 때는 Bulk API를 사용하자.

```json
POST _bulk
{"index": {"_index": "bluehorn-test", "_id": "1"}}
{"title": "bulk test", "name": "haha"}

{"update": {"_index": "bluehorn-test", "_id": "2"}}
{"doc": {"title": "bulk test", "name": "haha"}}

{"delete": {"_index": "bluehorn-test", "_id": "3"}}
```

