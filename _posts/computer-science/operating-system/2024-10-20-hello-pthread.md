---
title: "Hello, pthread!"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: "C에서 지원하는 POSIX thread 체험기."
---

3년간 산업기능요원으로 복무 하고, 복학해 컴공과 수업을 듣고 있습니다. 그동안 많이 배웠다고 생각했는데, 여전히 부족한 부분이 많네요 ^^;; 역시 세상도 넓고 공부의 길도 넓은 것 같습니다.
{: .notice--info}

# 들어가며

학교 운영체제 수업의 강의 슬라이드에 "요게 thread를 사용하는 코드 예시에요^^"라며 코드 조각이 덩그러니 있었다. 지금까지 나의 각종 자격증 시험과 코테 경험에 미루어봤을 때, 코드를 그냥 읽는 건 머릿속에 잘 남지 않았었다;; 그래서 강의 슬라이드에 나온 코드 조각들을 C로 옮겨서 직접 짜보려고 한다!!

![](/images/computer-science/operating-system/hello-thread.png){: .align-center style="max-width: 500px" }

요게 원래 강의 슬라이드에 있던 코드 예시이다. C언어와 비슷하지만, 완전히 동일하진 않다. 이번 포스트의 목표는 아래 함수들을 경험해보는 것이다.

- `thread_create()`
- `thread_exit()`
- `thread_join()`


# Hello, pthread!

제일 먼저 `pthread_create()`으로 쓰레드를 만들어보자.

```c
#include <stdio.h>
#include <pthread.h>

#define NTHREADS 10

pthread_t threads[NTHREADS];
```

그전에 먼저 pthread 사용을 위해 필요한 라이브러리 `pthread.h`를 불러오고, 각종 전역 변수를 선언해둔다. `pthread_t`는 pthread 정보를 담는 구조체이다. 앞에 "p"가 붙는 이유는 POSIX에서 요구하는 쓰레드 기능을 갖추었기 때문이라고 한다.

## Callee Function

```c
void* go (void* args) {
  int* n = (int*) args;
  printf("Hello from thread %d\n", *n);
}
```

일단 쓰레드로 실행시킬 `go()`라는 함수를 정의해야 하는데, 이 함수는 리턴과 함수 인자가 둘다 `void*` 타입이다. `void*` 타입을 처음 마주하여 어색할 수 있으니 첨언하자면... (본인도 `void*` 타입은 이번에 OS 들으면서 처음 봤다 ㅋㅋ)

<div class="statement" markdown="1">

`int` 타입과 `char` 타입은 각각 4 byte와 1 byte로 다른 사이즈를 갖습니다. 반면에 포인터 변수는 모두 참고하는 변수의 주소값(address)를 담기 때문에 모두 4 bytes 데이터를 저장합니다. 그래서 모든 포인터 변수는 사실 같은 타입이라고 볼 수 있습니다.

포인터 변수를 선언할 때 `int*`, `char*`로 타입을 곁들여 사용하지 않냐?라고 의문을 제시할 수 있습니다만, 저기에 붙는 `int`와 `char`는 포인터 변수가 참조하는 변수의 타입을 말할 뿐입니다. 그래서 아래와 같은 코드도 유효하게 동작합니다.

```c
int a = 10;
char* p = (char*) &a;
```

`(char*)`라고 포인터 타입에 대한 캐스팅이 들어가지만, `int` 변수의 주솟값이 `long*` 타입 포인터의 값으로 들어가는게 가능합니다.

`void*`는 이런 포인터 변수의 특성을 활용해, 아예 포인터 변수가 참조하는 변수가 무엇인지 명시하지 않는 패턴입니다. 그래서 `int*`가 될 수도 있고, `float*`가 될 수도 있고, 그 어떤 포인터 타입도 될 수 있습니다. 요걸 "void 포인터"라고 부릅니다.

void 포인터를 쓰면 아래와 같은 코드를 짤 수 있습니다.

```c
int a = 10;
void* p = &a;
*((int*) p) = 20;
```

만약 타입 캐스팅 없이 `*p = 20;`로 쓰려고 한다면, void 포인터가 참조하는 변수가 어떤 사이즈인지 몰라서 데이터를 RW 할 수 없을 것 입니다. 그래서 `(int*)` 캐스팅을 해줘야 합니다.

</div>

## pthread_create

이제 main 함수에서 `pthread_create()`로 쓰레드를 생성해보자.

```c
int main(void) {
  int thread_args[NTHREADS];

  for (int i=0; i < NTHREADS; i++) {
    thread_args[i] = i;
    pthread_create(&threads[i], NULL, go, &thread_args[i]);
  }

  return 0;
}
```

`pthread_create(&threads[i], NULL, go, &thread_args[i])`와 같이 사용하였는데, 각 인자의 의미를 적으면 아래와 같다. 명세는 [linux man page](https://man7.org/linux/man-pages/man3/pthread_create.3.html)를 참고했다.

- `pthread_t *restrict thread`
  - `pthread_t` 포인터 변수
  - `restrict`는 그 포인터가 참고하는 객체를 가리키는 다른 포인터가 존재하지 않는다는 걸 말한다. C언어 컴파일 때 최적화에 사용하는 키워드다.
- `const pthread_attr_t *restrict attr`
  - 새로 만들어지는 쓰레드의 특성값(attribution)을 정의한다.
  - 이번 포스트에서는 사용하지 않아서 `NULL`로 처리했다.
- `void *(*start_routine)(void *)`
  - 쓰레드로 실행할 함수를 전달한다. 인자와 리턴 타입이 둘다 `void*`여야 한다.
- `void *restrict arg`
  - `start_routine` 함수에 전달할 함수 인자를 정의한다. 요것도 포인터로 전달한다!

이렇게 정의한 상태에서 코드를 컴파일 하고 실행해보면,

```bash
$ gcc threadHello.c
$ ./a.out
Hello from thread 0
Hello from thread 1
Hello from thread 7
Hello from thread 3
```

응?? 10개 쓰레드를 만드려고 했는데, 4개 정도 만들고 실행이 종료 되었다 🤔 그 이유는 Thread Join을 하지 않았기 떄문이다!!

### Argument passing

Thread create 때, 어떤 함수를 쓰레드로 실행할지도 전달하지만, 그 함수에 어떤 인자로 실행할지도 `void* args` 함수로 전달한다.


```c
void* go (void* args) {
  int* n = (int*) args; // get value from args
  printf("Hello from thread %d\n", *n);
}

int main(void) {
  int thread_args[NTHREADS];

  for (int i=0; i < NTHREADS; i++) {
    thread_args[i] = i;
    pthread_create(&threads[i], NULL, go, &thread_args[i]); // pass args
  }

  return 0;
}
```

여기 코드에서는 정수값 `i`의 값을 인자로 넘겨주려고 한다. 이때, `&i`로 바로 넘기지 않고, `thread_args[i]`에 담아서 값을 전달했다. 그 이유는 `&i`로 바로 넘기면, `i` 변수를 모든 쓰레드에서 접근하는 꼴이 되기 때문이다.


## pthread_join

```c
int main(void) {
  // Thread Create
  ...

  for (int i=0; i < NTHREADS; i++) {
    pthread_join(threads[i], NULL);
    printf("Thread %d returned\n", i);
  }
  printf("Main thread done\n");
}
```

`pthread_join()`도 어떤 쓰레드의 signal을 받을 건지 정해줘야 한다.

- `pthread_t thread`
  - 앞에서 `pthread_create()` 때는 `pthread_t`의 포인터를 사용 했는데, join에서는 쓰레드 변수 그 자체를 사용한다.
- `void **retval`
  - 뒤에서 다룰 거지만, `pthread_exit()`으로 부모 쓰레드에게 signal을 줄 수 있는데, 그떄 전달 받은 값을 기록하기 위한 인자이다.
  - 지금은 `NULL`을 사용했지만, 곧 뒤에서 `retval`을 쓰는 예시도 볼 예정!

그리고 코드를 컴파일 해서 살펴보면...

```bash
$ ...
Hello from thread 0
Hello from thread 4
Hello from thread 6
Hello from thread 8
Hello from thread 1
Hello from thread 5
Hello from thread 2
Hello from thread 7
Hello from thread 3
Hello from thread 9
Thread 0 returned
Thread 1 returned
Thread 2 returned
...
Thread 7 returned
Thread 8 returned
Thread 9 returned
Main thread done
```

이번에는 생성한 쓰레드가 모두 실행되었다!! 실행 순서는 제각각이지만, `pthread_join()` 후에 `printf()` 출력 되는 건 순서대로 진행된다!

쓰레드가 생성 된 후, 실행은 동시에 진행된다. 실행 과정에서의 순서는 정해진 바가 없다. 다만, `pthread_join()`으로 종료 signal을 받기 전엔 쓰레드의 자원이 해제 되지 않고 남아있다. 멀티 프로세스를 운영할 때도 wait으로 자식 프로세스의 signal을 꼭 받아야 한다. 그렇지 않으면 *zombie process*[^1]와 같은 상황이 발생한다. 쓰레드를 운영할 때도 비슷하다. 꼭 join을 통해 자식 쓰레드의 signal을 받고, 자식 쓰레드를 할당 해제 처리해줘야 한다.

## pthread_exit

이번에는 자식 쓰레드에서 `pthread_exit()`으로 쓰레드를 종료하면서, 부모 쓰레드에게 결과를 넘겨주는 예시를 살펴보자.

```c
void* go (void* args) {
  int* n = (int*) args;
  printf("Hello from thread %d\n", *n);
  *n += 100;
  pthread_exit(n); // return value to parent thread
}

int main(void) {
  // Thread Create
  ...

  for (int i=0; i < NTHREADS; i++) {
    void* return_value; // pointer to get returned value
    pthread_join(threads[i], &return_value);
    printf("Thread %d returned %d\n", i, *((int*) return_value));
  }
  printf("Main thread done\n");
  return 0;
}
```

주목할 부분은 `pthread_exit(n);` 부분과 `pthread_join(threads[i], &return_value);` 부분이다.

자식 쓰레드는 `pthread_exit(n)`으로 쓰레드를 종료하며, 부모 쓰레드에게 값을 넘겨준다. 이때, 값 자체를 넘겨주는게 아니라 포인터 변수 `int* n`을 넘겨준다.

부모 쓰레드는 `pthread_join()`으로 자식 쓰레드의 종료 상태를 회수한다. 이때, `void* return_value`를 사용해 해당 값에 데이터를 기록하는데, 종료 상태를 기록할 주소 위치 받아서 데이터를 저장하는 것이다. 그래서 `&return_value`로 사용한 것.


# References

- [Pardue University](https://engineering.purdue.edu/~eigenman/ECE563/Handouts/helloworld-pthreads.c)
- [후앙님의 블로그](https://m.blog.naver.com/whtie5500/221692793640)
- [SUS Ver2](https://pubs.opengroup.org/onlinepubs/7908799/xsh/pthread_join.html)
- Linux man pages
  - [pthread_create](https://man7.org/linux/man-pages/man3/pthread_create.3.html)
  - [pthread_join](https://man7.org/linux/man-pages/man3/pthread_join.3.html)


[^1]: 자식 프로세스가 부모 프로세스 보다 먼저 종료 되었지만, 부모 프로세스가 wait으로 종료 상태를 회수 하지 않은 상태
