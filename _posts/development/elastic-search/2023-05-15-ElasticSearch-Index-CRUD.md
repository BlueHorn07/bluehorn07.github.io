---
title: "Index CRUD"
layout: post
tags: ["elastic-search"]
---

Index는 ElasticSearch에서 테이블의 역할을 한다. 먼저 Index에 대한 CRUD를 살펴보고, 레코드에 대응되는 Document의 CRUD를 살펴보자.

# Index 조회

```
GET _cat/indices

# include column headings
GET _cat/indices?v
```

```text
health status index     uuid   pri rep docs.count docs.deleted store.size pri.store.size
green  open   my-index  xxxx   1   1        35961            0       56mb           28mb
...
```

ES 클러스터에 존재하는 모든 Index의 정보를 확인한다. (1) 샤드에 대한 정보, (2) Document에 대한 정보, (3) Storage에 대한 정보를 확인할 수 있다.

<br/>

```
GET _cat/indicies/<index-name>
```

끝에 `<index-name>`를 추가해서 특정 Index 하나만 조회할 수도 있다.


# Create

Index 생성하기 위해선 Index 명세를 함께 전달해야 한다. Index 명세에는 `settings`, `mappings` 정보가 포함된다.

```
PUT <index-name>
```

참고로 Create/Update 둘다 `PUT`으로 해야 한다!

Index는 명세 없이도 바로 만들 수도 있다!

## Index Settings

Index를 처음 만들 때의 정보가 담긴다.

```json
PUT <index-name>
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 1
  }
}
```

- 샤드 수: `number_of_shards`, `number_of_replicas`
- 생성일: `creation_date`
- Analyzer 정보

## Index Mappings

Index에 저장되는 Document의 구조에 대한 정보가 담긴다.

```json
PUT <index-name>
{
  "mappings": {
    "properties": {
      "id": { "type": "text" },
      "name": { "type": "text" },
      "count": { "type": "integer" }
    }
  }
}
```

Index에 입력되는 JSON 정보를 도큐먼트로 변환해 저장할 때 이 `mapping` 정보를 참고해 적용한다.

뒤에 Document의 CRUD에서 살펴볼 것이지만, ES는 "Dynamic Mapping"을 지원하기 때문에, `mapping`에 없는 필드가 들어오더라도 Document로 저장된다.

즉, 처리하는 필드에 대한 정보가 `mapping`에 명시되어 있으면 해당 타입을 사용하고, 명시되어 있지 않으면, 해당 필드가 포함되는 가장 넓은 범위의 데이터 타입을 선택한 후 Index의 `mapping`에 추가한다.

"Dynamic Mapping"의 예를 좀더 살펴보면,

- `"123"`이 들어온다면, 해당 필드는 `long` 타입으로 세팅되고,
- `"3.14"`로 들어온다면, 필드는 `double` 타입으로,
- `"haha hoho"`가 들어온다면 해당 필드는 `text` 타입으로 세팅된다.


# Read

생성한 Index의 상세 정보를 보는 방법이다.

```
GET <index-name>
```

```json
{
  "my-index" : {
    "aliases" : { },
    "mappings" : { },
    "settings" : {
      "index" : {
        "creation_date" : "1684160351512",
        "number_of_shards" : "5",
        "number_of_replicas" : "1",
        "uuid" : "PIgxLmgdS42BhS2nEoycoQ",
        "version" : {
          "created" : "7090199"
        },
        "provided_name" : "my-index"
      }
    }
  }
}
```

원한다면 `settings`, `mappings` 정보만 조회할 수도 있다.

```
GET <index-name>/_settings
GET <index-name>/_mappings
```

# Update

변경하고자 하는 Index의 `settings`, `mappings`를 다시 적어주면 된다.

```json
PUT <index-name>/_settings
{
  "index": {
    "number_of_replicas": 2
  }
}
```

단, `number_of_shards`는 변경할 수 없다. 

`mapping`의 경우 필드 명세를 추가하는 것만 가능하다.

# Delete

삭제는 간단하다!

```
DELETE <index-name>
```

