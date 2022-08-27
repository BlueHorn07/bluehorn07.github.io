---
title: "TypeORM - DB Relationship"
layout: post
tags: [NestJS]
use_math: true
---

<br/>

NestJS를 공부하면서 개인적인 용도로 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>
- One-to-One
- One-to-Many / Many-to-One
- Many-to-Many

<hr/>

### Set-up

시작 전에 몇가지 선행 개념을 언급하고 시작하자.

- \<**Primary Key**\>: 해당 테이블의 식별자 역할을 하는 제약조건으로 테이블에 하나만 설정할 수 있음.
- \<**Unique Key**\>: 해당 컬럼에 입력되는 데이터가 유일함을 보장하기 위한 제약조건으로한 테이블에 여러개 설정할 수 있다. 당연히 Primary Key는 Unique Key이기도 하다.

물론 경우에 따라서는 하나의 테이블에 두 개 이상의 \<Pimary Key\>를 설정하기도 한다. 이 경우, 두 개의 Key 모두에 대해서 중복에 대한 검사를 실시하게 된다!


<hr/>

## DB Relationship

일반적으로 DB의 각 Entity들을 서로 완전히 격리되어 있는 것이 아니라, 어느 정도 "relationship"을 가진다. (이때, 도입되는 개념이 \<Foreign Key\>다!) 서로 다른 두 Entity가 함께 사용되기 위해서는 \<**Join**\>이라는 하게 되는데, 이 Join을 수행하기 위해서는 각 Entity 사이에 적절한 \<Relationship\>이 정의되어 있어야 한다.

### One-to-One

\<One-to-One\> 관계는 아래와 같이 기술된다.

> $A$ contains only one instance of $B$, and $B$ contains only one instance of $A$.

TypeORM 공식 에선 `User`와 `Profile`의 예를 제시한다.

``` ts
@Entity()
export class Profile {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    gender: string;

    @Column()
    photo: string;
}
```

``` ts
@Entity()
export class User {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    name: string;

    @OneToOne(() => Profile)
    @JoinColumn()
    profile: Profile;
}
```

각 `User`는 "단 하나"의 `Profile` 객체를 갖는다. 그리고 `Profile` 객체는 오직 "단 하나"의 `User`에 대해서만 연관된다. 코드를 살펴보면, `User` Entity가 `Profile` 객체를 갖고 있음을 확인할 수 있다.

또, `User` Entity에 `@JoinColumn()`이라는 데코가 붙어있는데, 이 경우, `User` Table에 `profileId`가 자동으로 \<Foreign Key\>로 설정된다!

```
+-------------+--------------+----------------------------+
|                          user                           |
+-------------+--------------+----------------------------+
| id          | int(11)      | PRIMARY KEY AUTO_INCREMENT |
| name        | varchar(255) |                            |
| profileId   | int(11)      | FOREIGN KEY                |
+-------------+--------------+----------------------------+
```

주의할 점은 One-to-One 아래에서는 `@JoinColumn()`이 단방향으로 설정된다는 것이다. 논리적으로도 Target Table을 객체로 갖는 쪽에서 `@JoinColumn()` 데코를 쓰는 것이 옳다!

이렇게 설정했을 경우, `find()`로 `User` 정보를 불러올 때, 아래와 같은 `FindOptions`를 주면, Foreign Table인 `Profile`의 정보까지 함께 불러올 수 있다!

``` ts
const users = await userRepository.find({ relations: ["profile"] });
```

그 외에도 `Profile`에서 `User` 정보를 참조할 수 있도록, \<양방향 Bi-direction\>으로 설정해줄 수도 있다. 코드는 TypeORM의 공식 문서를 참고하길 바란다. 👉 [link](https://typeorm.io/#/one-to-one-relations/)

주의할 점은 One-to-One으로 생성되는 Foreign Key는 \<Unique Key\>라는 점이다! 만약 Unique 조건을 만족되지 않는 DB를 설계하고 있다면, One-to-One이 아닌 아래의 One-to-Many 방식을 써야 한다!

### One-to-Many / Many-to-One

\<One-to-Many\> 또는 \<Many-to-One\> 관계는 아래와 같이 기술된다.

> $A$ contains multiple instances of $B$, but $B$ contains only one instance of $A$.

TypeORM 공식 문서에선 `User`와 `Photo`의 예를 들고 있다. 각 `Photo`는 오직 "단 하나"의 `User`를 갖지만, `User`는 여러 `Photo`를 가질 수 있다. 이 경우, `Photo`가 Many, `User`가 One이 된다.

코드는 TypeORM의 공식 문서를 참고하길 바란다. 👉 [link](https://typeorm.io/#/many-to-one-one-to-many-relations)

One-to-Many / Many-to-One 에서 주의할 점은 "`@OneToMany` cannot exist without `@ManyToOne`."라는 점이다. 즉, 한쪽에서만 Relationship을 필요로 하는 상황이더라도 두 Entity 모두에 대해서 `@OneToMany`, `@ManyToOne`을 설정해줘야 한다는 말이다! 참고로 `@OneToOne`에선 한쪽에서만 설정해줘도 괜찮았다!

그외엔 One-to-One과 거의 비슷하다.

### Many-to-Many

\<Many-to-Many\> 관계는 아래와 같이 기술된다.

> $A$ contains multiple instances of $B$, and $B$ contains multiple instances of $A$.

TypeORM 공식 문서에선 `Question`과 `Category`의 예를 들고 있다. 각 `Question`은 여러 개의 `Category`를 가질 수 있다. 반대로 각 `Category`는 여러 개의 `Question`을 가질 수 있다!

코드는 TypeORM의 공식 문서를 참고하길 바란다. 👉 [link](https://typeorm.io/#/many-to-many-relations)

Many-to-Many는 기존의 relation과 달리 작동 양상이 조금 다르다!!

Many-to-Many에선 `@JoinTable`을 통해 두 Table에 대한 Foregin Key가 담긴 **<u>새로운 Table이 생성</u>**된다!!

사실 개인적인 경험으론 Many-to-Many는 뭔가 제약이 많아서 DB를 설계하는데에 좋은 선택이 아니었던 것 같다. 만약 Many-to-Many를 사용하다가 막힌다면, 과감히 Many-to-Many 방식을 포기하고 우회하는 방법을 찾아보는 것도 좋은 접근일 것 같다! 😥

<hr/>

### 맺음말

사실 모든 DB relationship을 위에서 소개한 데코레이터 없이 그냥 직접 구현해도 할 수는 있다!! 하지만, 이렇게 DB를 설계하다보면 언젠가는 DB relationship에 대한 니즈가 필요하게 되고, 때에 따라서는 DB relationship을 적극 활용하는게 구현을 더 가볍게 가져갈 수도 있다. 😊
