---
title: "NestJS: Mocking & Spy"
toc: true
toc_sticky: true
categories: [NestJS]
---


본 글은 제가 `NestJS` 프레임워크를 통해 개발하면서 깨달은 노하우를 기록한 것입니다. 제가 제시한 방법보다 더 좋은 방법이 있을 수도 있습니다. 지적은 언제나 환영입니다 :)

<br>
<hr>

<div class="img-wrapper">
  <img src="https://jestjs.io/img/opengraph.png" width="280vw">
</div><br>



`NestJS`에선 Javascript의 테스트 프레임워크인 `jest`<small>[link](https://jestjs.io/)</small>를 기본으로 하는 테스트 프레임워크를 지원한다.

물론 약간의 변형은 있겠지만, 그냥 지원만 하는 수준이 아니라 `NestJS`의 테스트를 `jest`로 한다.

```
npm i --save-dev @nestjs/testing
```

<br>

### 테스팅 기초 코드

`NestJS CLI`를 이용해 NestJS Object를 생성하게 되면 자동으로 테스팅 파일인 `.spec.ts`가 생성된다.

이 `.sepc.ts` 파일은 **<u>Controller</u>**와 **<u>service</u>**를 `NestJS CLI`로 생성할 때에만 자동으로 생성된다.

사실 둘의 차이는 거의 없는데, 만약 차이를 보고 싶다면 펼쳐보기에 기술은 해두겠다.

<details markdown="1">
<summary>controller.spec.ts vs. serivice.spec.ts</summary>

<br>

- `app.controller.spec.ts`

``` ts
import { Test, TestingModule } from '@nestjs/testing';
import { CatController } from './cat.controller';
import { CatService } from './cat.service';

describe('CatController', () => {
  let controller: CatController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [CatController],
      service: [CatService]
    }).compile();

    controller = module.get<CatController>(CatController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
```

- `app.service.spec.ts`

``` ts
import { Test, TestingModule } from '@nestjs/testing';
import { CatService } from './cat.service';

describe('CatService', () => {
  let service: CatService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [CatService],
    }).compile();

    service = module.get<CatService>(CatService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});

```

정말 차이가 없지 않은가? :smile:

</details>

<br>

사실 둘의 차이가 거의 없고, 다른 포스트를 찾아봐도 보통 Controller를 기준으로 작성되어 있어서 여기에서도 controller를 기준으로 `controller.spec.ts`를 작성해보겠다.

`NestJS` App을 생성할 때 자동으로 생성되는 `app.controller.spec.ts` 파일이다. 우선 이 녀석을 도해(圖解)해보자.

``` ts
import { Test, TestingModule } from '@nestjs/testing';
import { AppController } from './app.controller';
import { AppService } from './app.service';

describe('AppController', () => {
  let appController: AppController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      controllers: [AppController],
      providers: [AppService],
    }).compile();

    appController = app.get<AppController>(AppController);
  });

  describe('root', () => {
    it('should return "Hello World!"', () => {
      expect(appController.getHello()).toBe('Hello World!');
    });
  });
});
```

`jestjs`는 테스트 케이스를 `describe` 내부에 정의한다.

위의 코드에서는 `describe('AppController', ...)`와 내부에 `describe('root', ...)`가 정의되어 있다.

`describe()`의 인자로 들어가는 String은 단순히 Testing을 정의하는 이름에 불과하다. 테스트 로직에는 아무 관계가 없다.

`beforeEach()`에는 각 테스트 케이스 실행 이전에 선행할 내용이 정의되어 있다.

위의 코드는 `TestingModule`인 `app`을 생성한다.

<br>

실제 테스팅 코드는 `it()`에서 정의된다. `it()`에도 String 인자가 들어가는데, `describe()`의 그것이 테스트 케이스을 정의하는 이름이라면, `it()`의 String은 테스트 케이스에 대한 설명을 의미한다.

<br>

그래서 요약하면 아래와 같다!

``` ts
describe('test title', () => {
  it('test description', () => {
    expect("value-wanting-to-test").tobe("value-wanting-to-get")
  }
})
```

<br>
<hr>

### 데이터베이스 테스트

`Jest` 라이브러리를 이용해 데이터베이스 테스트를 할 수 있는 두 가지 방법을 알아보자!

1. [MockRepository](#mockrepository)
2. [Jest: spyOn](#jest-spyon)

#### MockRepository

앞의 상황은 문자열 비교 수준의 간단한 테스팅이지만, 실제 서버를 테스팅하기 위해선 데이터베이스에 접근하는 API들을 테스트해야 한다!

하지만, <span style="color:red">데이터베이스를 직접 조작하여 테스트 환경을 만드는 것은 아주아주 비효율적이며, Unit Test의 원칙과도 맞지 않는다.</span>

그래서 데이터베이스를 직접 조작하는 것이 아니라 테스트할 데이터베이스를 모사한 `Mock` 객체를 만들어 해당 `Mock` 객체에서 테스트 상황을 만들어 테스트를 진행해야 한다!!

<div class="notice" markdown="1">

#### Mocking & Mock

&nbsp; "운영 환경 대비 제약이 많은 테스트 환경에서는 실제 데이터베이스와 연동하거나 실제 외부 API를 호출하기가 불가능한 경우가 많습니다. 가령 가능하더라도, 이렇게 외부 서비스에 의존하는 테스트는 해당 서비스에 문제가 있을 경우 깨질 수 있으며 실행 속도도 느릴 수 밖에 없습니다.

&nbsp; 따라서 단위 테스트를 작성할 때 외부에 의존하는 부분을 임의의 가짜(Mock)로 대체하는 기법이 자주 사용되는데 이를 **<u>모킹(Mocking)</u>**이라고 합니다. 다시 말해, 모킹(Mocking)은 외부 서비스에 의존하지 않고 독립적으로 실행이 가능한 단위 테스트를 작성하기 위해서 사용되는 테스팅 기법입니다." - article from [here](https://www.daleseo.com/python-unittest-mock/)

<hr>

&nbsp; "예를 들어, 데이터베이스에서 데이터를 삭제하는 코드에 대한 단위 테스트를 작성할 때, 실제 데이터베이스를 사용한다면 여러가지 문제점이 발생할 수 있습니다.

- 데이테베이스 접속과 같이 Network이나 I/O 작업이 포함된 테스트는 실행 속도가 현저히 떨어질 수 밖에 없습니다.
- 프로젝트의 규모가 켜져서 한 번에 실행해야 할 테스트 케이스가 많이지면 이러한 작은 속도 저하들이 모여 큰 이슈가 될 수 있으며, CI/CD 파이프라인의 일부로 테스트가 자동화되어 자주 실행되야 한다면 더 큰 문제가 될 수 있습니다.
- 테스트 자체를 위한 코드보다 데이터베이스와 연결을 맺고 트랜잭션을 생성하고 쿼리를 전송하는 코드가 더 길어질 수 있습니다. 즉, 배보다 배꼽이 더 커질 수 있습니다.
- 만약 테스트 실행 순간 일시적으로 데이터베이스가 오프라인 작업 중이었다면 해당 테스트는 실패하게 됩니다. 따라서 테스트가 인프라 환경에 영향을 받게됩니다. (non-deterministic)
- 테스트가 종료 직 후, 데이터베이스에서 변경 데이터를 직접 원복하거나 트렌잭션을 rollback 해줘야 하는데 상당히 번거로운 작업이 될 수 있습니다.

무엇보다 이런 방식으로 테스트를 작성하게 되면 <span style="color:red">특정 기능만 분리해서 테스트하겠다는 단위 테스트(Unit Test)의 근본적인 사상에 부합하지 않게 됩니다.</span>" - article from [here](https://www.daleseo.com/jest-fn-spy-on/)
</div>

그래서 서버의 유닛 테스트는 데이터베이스를 직접 조작하는 것이 아닌 서버를 흉내내는 `MockRepository`를 만들어 진행한다.

<br>

본래 service에선 Repository 변수를 만들어 해당 Repository를 이용해 데이터베이스에 접근한다. 테스트에서는 아래와 같이 Repository를 모사한 "**MockRepository**"를 만든다.

예를 들어 UserRepository를 모사한 `MockRepository`를 만들어보자.

``` ts
class MockRepository {
  async findOneOrFail(query) {
    const user: User = new User();
    user.uuid = query.uuid;
    return user;
  }
}

describe('User', () => {
  let userService: UserService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        UserService,
        {
          provide: getRepositoryToken(User),
          useClass: MockRepository,
        },
      ],
    }).compile();
    userService = module.get<UserService>(UserService);
  });

  it('should', async () => {
    const userId = '42';
    const result = await userService.findUserById(userId);
    expect(result.uuid).toBe(userId);
  });
});
```

Repository를 모사한 `MockRepository`를 운용하기 위해서는 Repository에 정의된 함수들을 선언하고 모사해줘야 한다. 만약 테스트 하려는 `service`의 특정 함수, 예를 들면 `findUserById` 같은 함수가 내부에서 Repository의 `repository.findOne()`과 같은 함수를 사용한다면, `MockRepository`에서 해당 함수를 선언해줘야 한다는 말이다!

<br>
<hr>

#### Jest: spyOn

참고자료: [link](https://www.daleseo.com/jest-fn-spy-on/)

1\. `jest.fn()`; Mock function

<details markdown="1">

`jest.fn()`을 이용해 Mock function을 생성할 수 있다.

``` js
const mockFn = jest.fn();
```

**<u>Mock function</u>**은 일반 함수와는 달리 모킹을 이용한 테스트에 특화한 함수를 모사한 '객체'입니다.

\* 인자 입력

``` js
mockFn()
mockFn(1)
mockFn("Lorem")
mockFn({ name: "Lorem", id: "Ipsum" })
```

\* 리턴 값 설정

``` js
mockFn.mockReturnValue("Lorem Ipsum");
console.log(mockFn()); // "Lorem Ipsum"
```

\* Mock 비동기 함수

``` js
mockFn.mockResolvedValue("Async resolve value");
mockFn.then((result) => {
  console.log(result); // "Async resolved value"
})
```

\* Mock function 구현

``` js
mockFn.mockImplementation((name) => `I am ${name}!`);
console.log(mockFn("Cantor")); // "I am Cantor!"
```

Mock function의 유용성은 Mock function은 호출에 대한 정보를 모두 기억하고 있다는 점이다!!

``` js
mockFn("a")
mockFn(["b", "c"])

expect(mockFn).toBeCalledTimes(2)
expect(mockFn).toBeCalledWith("a")
expect(mockFn).toBeCalledWith(["b", "c"])
```
</details>

<br>

2\. `jest.spyOn()`; Spy Function

지금까지는 모두 기존 객체을 대신하는 '대역(代役)'인 **<u>Mock</u>**을 이용한 테스트를 살펴봤다. 하지만 몇몇 경우에는 기존 객체를 Mock로 대체하지 어려울 수도 있다. 이 경우 사용하는 것이 바로 "**<u>Spy Function</u>**"이다!

예를 들어 아래와 같이 `calculator`에 정의된 `add`의 Spy Function을 만들어 사용할 수 있다.

``` js
const calculator = {
  add: (a, b) => a + b,
}

const spyFn = jest.spyOn(calculator, "add")

const result = calculator.add(2, 3)

expect(spyFn).toBeCalledTimes(1)
expect(spyFn).toBeCalledWith(2, 3)
expect(result).toBe(5)
```

앞에서 Spy function은 Mock 할 수 없을 때 사용한다고 했다. Mock 할 수 없는 경우는, "테스팅 대상 함수 A가 다른 함수 B 내부에서 호출되며 사용되는 상황이라 함수 A를 mocking할 경우, 함수 B를 테스트할 수 없기 때문에 원본은 그대로 두고 Spy 한다."라고 한다.

\> Mock vs. Spy [link](https://stackoverflow.com/a/57645643)

[이곳](https://jhyeok.com/nestjs-unit-test/)에 게시된 `.spyon()` 함수의 예시를 살펴보자.

``` ts
describe('UserService', () => {
  describe('유저 정보 수정', () => {
    it('존재하지 않는 유저 정보를 수정할 경우 BadRequestError 발생한다.', async () => {
      const userId = faker.random.uuid();

      const updateUserDto: UpdateUserDto = {
        firstName: faker.lorem.sentence(),
        lastName: faker.lorem.sentence(),
        isActive: false,
      };

      const userRepositoryFindOneSpy = jest
        .spyOn(userRepository, 'findOne')
        .mockResolvedValue(null);

      try {
        await userService.updateUser(userId, updateUserDto);
      } catch (e) {
        expect(e).toBeInstanceOf(BadRequestException);
        expect(e.message).toBe(Message.NOT_FOUND_USER_ITEM);
      }

      expect(userRepositoryFindOneSpy).toHaveBeenCalledWith({
        where: {
          id: userId,
        },
      });
    });
  });
})
```

여기서도 `.spyOn()`으로 repository의 함수를 모사하더라도 함수의 로직을 수정하는 작업이 필요하다!!

또한 `.spyOn()`의 리턴으로 얻은 Spy Function 객체는 `.toHaveBeenCalled...()` 함수 등으로 테스팅할 함수 내부에서 모사한 함수를 올바르게 사용했는지를 검증하는 데에 사용된다. <span style="color:red">우리가 검증할 대상은 `.spyOn()`으로 Mocking한 대상이 아니다. `.spyOn()`을 포함한 Mocking은 단지 의존성을 끊기 위한 수단일 뿐이다!!</span>

결국 `.spyOn()`을 사용하더라도 결국엔 기존 Mocking과 비슷한 맥락으로 테스팅이 진행된다는 것이다! ~~조삼모사~~

### Reference
- [Nest.js 의 유닛 테스트(Unit test)](https://dailybook-with.tistory.com/entry/Nestjs-%EC%9D%98-%EC%9C%A0%EB%8B%9B-%ED%85%8C%EC%8A%A4%ED%8A%B8Unit-test)
- [NestJS에서 단위 테스트 작성하기](https://jhyeok.com/nestjs-unit-test/)
- [[Jest] jest.fn(), jest.spyOn() 함수 모킹](https://www.daleseo.com/jest-fn-spy-on/)
- [Nest + Jest unit test (5) spy function, mockImplementation](https://darrengwon.tistory.com/m/1047)