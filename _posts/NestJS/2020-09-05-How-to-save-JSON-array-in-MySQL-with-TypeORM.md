---
title: "How to save JSON array in MySQL with TypeORM"
layout: post
tags: [TypeORM, MySQL, NestJS]
---

## 서론
본 글은 제가 `NestJS` 프레임 워크를 통해 개발하면서 깨달은 노하우를 기록한 것입니다. 제가 제시한 방법보다 더 좋은 방법이 있을 수도 있습니다. 지적은 언제나 환영입니다 :)

**주의!**: 이 글은 `MySQL` DB에 대한 내용을 다룹니다.

<hr>

`NestJS` 프레임워크는 `TypeORM` 라이브러리를 통해 DB를 관리한다. 본 글은 `TypeORM`을 통해 JSON 배열을 MySQL DB에 어떻게 저장할지에 대한 내용을 다룬다.

## When we save 'array' in DB column?
DB Column에 array 타입을 저장하는 경우는 생각보다 자주 등장한다.

### Foreign Key Relation (OneToMany/ManyToOne)[^1]
`Person`에 대한 Entity와 `Email` Entity가 존재하는 경우를 생각해보자. 이때, 한 사람이 여러 이메일을 가질 수 있으므로 그 둘의 관계는 'OneToMany/ManyToOne'이다. MySQL의 테이블 상에는 다음과 같이 저장된다.

``` bash
+----+--------------------+--------------+
| id | person             | email_id(fk) |
+----+--------------------+--------------+
| 1  | Tom                | 1            |
| 2  | Evans              | 2, 3         |
+----+--------------------+--------------+

+----+------------------+----------------+
| id | email            | person_id(fk)  |
+----+------------------+----------------+
| 1  | tom@mail.com     | 1              |
| 2  | evans@mail.com   | 2              |
| 3  | evans@univ.ac.kr | 2              |
+----+------------------+----------------+
```

두 테이블을 보면, 각각 `email_id`와 `person_id`라는 foreign key를 가지고 있다. `Person`의 `Evans` 경우 특별하게 두 개의 이메일을 가지고 있어 `email_id`를 배열을 가지게 된다. 

즉, OneToMany/ManyToOne 관계 아래에서 fk_id Array를 발견할 수 있다.

### `simple-array`
Foreign key가 아니더라도, MySQL의 컬럼에는 배열을 저장할 수 있다. `TypeORM`은 `simple-array`를 통해서 MySQL에 간단한 형태의 배열을 저장할 수 있게 도와준다.

``` typescript
@Column('simple-array')
num_arr: number[];

@Column('simple-array')
str_arr: string[];
```

하지만 `simple-array` 방식으로 `string[]`을 저장하게 되면, 어떤 문제가 발생한다. 이것은 뒷부분에서 더 상세히 다룬다.

## How to save JSON array?
지금까지 MySQL 상에서 배열이 저장되는 경우를 살펴봤다. 그럼 이번엔 JSON 배열을 MySQL에 어떻게 저장할 수 있을지 살펴보자.

### `simple-array`를 사용해보자!

``` typescript
@Column('simple-array')
json_arr: JSON[];
```

`@Column`에 `simple-array` 속성을 주고, 이전과 마찬가지로 배열 형태로 선언하였다. 이 경우는 과연 저장할 수 있을까?? NOPE! 이 경우는 오류가 난다. 오류 메시지를 살펴보자.

``` bash
DataTypeNotSupportedError: Data type "Array" in "example.json_arr" is not supported by "mysql" database.
```

?? 앞에서 우리는 MySQL이 배열을 사용하는 경우를 살펴봤다. 그런데 지금의 오류 메시지는 MySQL에서 `Array` 타입을 지원하지 않는다고 말한다.

오류메시지의 말이 맞다. MySQL은 `Array` 타입을 지원하지 않는다. 어떤 방법을 통해서 우리에게 배열을 지원하는 것 같은 느낌을 주는 것일 뿐이다!!

### `simple-array`를 사용해보자! - `string[]` 버전
지금의 문제를 우회하는 방법을 살펴보자. 우리의 목적은 여전히 JSON 배열을 MySQL에 저장하는 것이다. 하지만 `JSON[]` 형태가 불가능하니, 이전에 살펴본 `string[]` 방식으로 우회해보자.

``` typescript
@Column('simple-array')
json_arr: string[];
```

javascript에서는 `JSON.stringify()`라는 함수를 통해서 `JSON`을 `string` 형태로 serialize 해줄 수 있다. 우리의 계획은 다음과 같다.

1. API에서는 JSON 배열로 입력을 받는다.
2. 입력받은 JSON 배열을 순회하며 모든 JSON에 `JSON.stringify()`를 적용해서 serialize
3. MySQL에 `string[]`로 저장
4. `Get` 요청이 들어올 때마다 `string[]`로 저장된 string 배열의 문자열을 `JSON.parse()`로 파싱해서 리턴

이렇게 한다면 JSON 배열을 string 배열로 변환하여 오류 메시지 없이 DB에 저장할 수 있다.

하지만...

#### `string[]` 버전의 문제
JSON을 저장하는 우리에겐 이 방법도 문제가 있다.

다음과 같은 JSON 배열이 저장된다고 해보자.

``` bash
[{name: 'Baker', job: 'musician'}, {name: 'Euler', job: 'mathematician'}]
```

이것을 `JSON.stringify()`로 바꾸면,
``` bash
["{name: 'Baker', job: 'musician'}", "{name: 'Euler', job: 'mathematician'}"]
```
가 된다.

DB에 저장도 잘 된다. 하지만 `Get`으로 DB에 저장된 것을 확인해보면...

``` bash
["{name: 'Baker'", 
 "job: 'musician'}",
 "{name: 'Euler'", 
 "job: 'mathematician'}"]
```

다음과 같은 괴상한 형태가 된다...!!

그 이유는...

#### MySQL의 pseudo-Array
우리는 MySQL이 JSON 배열은 저장이 안 되더라도, number 배열이나 string 배열 정도는 지원한다고 여기고 있었다. 하지만... 그건 `TypeORM`의 눈속임이었다!!

사실 MySQL은 `Array` 타입을 전혀 지원하지 않는다!! (이미 오류 메시지로 확인도 했다.) 그럼 우리가 `simple-array` 속성을 써서 저장한 건 어떻게 한 것인가??

`simple-array`는 모든 배열을 `string`으로 저장한다. 만약, number 배열이라면, `"1, 2, 3"`, string 배열이라면, `"Bill, John, Baker"`. 그래서 string 중간에 콤마(,)라도 들어가면, DB에는 이렇게 저장된 것이다.

``` bash
"'In other words, please be true', 'In other words, I love you'"
```

즉, string 배열이더라도 DB에는 그냥 하나의 string만 저장된다는 것이다. 그리고 `TypeORM`은 콤마(,)를 기준으로 `simple-array`로 선언된 Column의 정보를 파싱한다. 그렇기에 원래 2개의 string을 저장했더라도, DB 값을 읽으면 4개의 string을 얻게 되는 것이다 ㅠㅠ

사실 `MySQL`은 잘못이 없다. `TypeORM`의 `simple-array` 방식이 `MySQL`에서 유독 이런 문제를 일으키는 것일 수도 있다. 다른 DB에서는 이렇게 하나의 string으로 저장되지 않을 수도 있다.[^2] MySQL에는 이렇다는 것이다... ㅠㅠ

JSON의 경우 Object를 나열하기 위해 콤마(,)가 등장할 수 밖에 없지만, 일반 string에서는 콤마가 등장하는 경우가 드물다. 그래서 `TypeORM` 개발자가 알아채지 못 했을 수도 있다. 그취만 이런 사소한 오류도 개발에서는 큰 걸림돌이 되거나 오류를 발생시킬 수도 있다. (이것 때문에 반나절을 날렸다...)

<hr>

### [Solution] 그냥 string으로 저장하라!
몇번의 시행착오를 통해 TypeORM의 `simple-array`가 MySQL 상에서 어떻게 배열을 저장하는지 확인하였다...

그럼 이걸 활용해서 코드를 다시 구성해보자.

``` typescript
@Column()
json_arr: string;
```

Column은 그냥 `string`으로 설정한다.

JSON 배열의 JSON 하나하나는 `JSON.stringify()` 함수로 serialize 해준다. 그리고 그것들을 `JSON.parse()`가 가능한 형태로 변형하여 저장한다.

``` typescript
// save JSON array into MySQL DB
saveJSONArray(json_arr: JSON[]) {
  let jsonArr_string = "[";
  for(let i=0; i < json_arr.lenght; i++){
    jsonArr_string += JSON.stringify(json_arr[i]);
    if(i < json_arr.lenght-1){
      jsonArr_string += ",";
    }
  }
  jsonArr_string += "]";
  this.yourRepository.save({ json_arr: jsonArr_string });
}

// load JSON array from MySQL DB
async findJSONArray(): Promise<YourEntry[]> {
  const entry_arr = await this.yourRepository.find();
  for(let i=0; i < entry_arr.lenght; i++){
    entry_arr[i].json_arr = JSON.parse(entry_arr[i].json_arr);
  }
  return entry_arr;
}
```

이렇게 아예 `json_arr: string`으로 설정하고, DB의 값을 읽을 때 `JSON.parse()`로 파싱해 JSON으로 리턴해주는 방법이 최선이라고 생각한다. (물론 누군가는 이것보다 더 좋은 방법을 찾아 잘 사용하고 있을 수도 있다!)

<hr>

[^1]: ManyToMany는 OneToMany/ManyToOne와 다르게 Array 기반이 아니다. ManyToManyo의 경우 **function table**을 만들어 Relation을 관리하고 기록한다.

[^2]: 본인이 직접 확인해본 것은 아니지만, TypeORM에서 ProgressDB에 대해서는  `jonb`를 통해 JSON 배열 저장 방식을 구현한 것 같았다. 