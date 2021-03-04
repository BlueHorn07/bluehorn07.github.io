---
title: "`.env` 파일 사용하기"
layout: post
tags: ["NestJS"]
use_math: true
---

<br/>

NestJS를 공부하면서 개인적인 용도로 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

### `.env` 없이 쓰는 현재 상황

현재 NestJS 앱을 `TypeORM`으로 MySQL DB와 연결을 해둔 상태다.

그런데, `app.module.ts`에 아래와 같이 DB 설정이 하드 코딩 되어 있는 상태라 개발 환경에 유연하게 대처하기 힘들었다. 

``` ts
TypeOrmModule.forRoot({
    type: 'mysql',
    host: 'localhost',
    port: 3306,
    username: 'root',
    password: '****',
    database: '****',
    entities: ["dist/**/*.entity{.ts,.js}"],
    synchronize: true,
}),
```

그래서 이번 기회에 `.env`를 만들어 좀더 유연하고, App 내의 설정을 쉽게 관리해보려고 한다.

<hr/>

### `ormconfig.json` 생성

루트 경로에 `ormconfig.json` 파일을 생성해 `forRoot()`에 들어가는 내용을 집어 넣어 사용할 수 있다고 한다. 그러면, `TypeORM`이 알아서 `ormconfig.json` 파일을 우선적으로 읽어들인다!!

``` json
{
  "type": "mysql",
  "host": "localhost",
  "port": 3306,
  "username": "root",
  "password": "****",
  "database": "****",
  "entities": ["dist/**/*.entity{.ts,.js}"],
  "synchronize": true,
}
```

<hr/>

### `ConfigModule`을 만들어 `.env` 도입

`ormconfig.json`을 사용하는 건 여전히 훌륭하고 편하지만, 아직 `.env`를 사용하고 있는 건 아니다.

`.env`를 사용하기 위해서 환경변수를 관리하는 `ConfigModule`을 만들어주자!

제일 먼저 아래의 패키지를 설치해주자.

``` bash
$ npm i @nestjs/config
```

`@nestjs/config` 패키지는 내부적으로 `dotenv`를 사용하고 있다고 한다!!

이제 이걸 `process.env.****`를 사용할 \<모듈\>의 `imports`에 넣어주면 된다!!

``` ts
imports: [
    ConfigModule.forRoot(),
]
```

그리고는 자유롭게 `process.env.****`를 사용하면 된다!!

하지만, 주의할 점은 모듈에서 `process.env.****`를 사용할 때마다 `ConfigModule.forRoot()`를 import 해줘야 한다.

NestJS 공식 Document의 [Configuration](https://docs.nestjs.com/techniques/configuration#use-module-globally)에서 `isGloabl: true` 옵션을 주면 전체 App에서 사용할 수 있다고 하던데... 본인은 옵션을 줘도 안 됬다!! 🤬

<br/>

이제 `.env`를 사용할 수 있게 되었으니 앞에서 만든 `ormconfig.json`에도 `.env`를 적용시켜주자.

이를 위해 `ormconfig.json`을 `ormconfig.js`로 바꾸고 아래와 같이 작성한다.

``` js
module.exports = {
  type: 'mysql',
  host: 'localhost',
  port: process.env.DB_PORT,
  username: process.env.DB_USERNAME,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE,
  entities: ['dist/**/*.entity.js'],
  synchronize: true,
};
```

<br/>

!!! 이걸로 끝!!! 😆


