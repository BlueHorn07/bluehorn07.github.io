---
title: "Python Tips & Tricks"
layout: post
tags: ["Tips & Tricks"]
use_math: true
---

### 서론
이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

<span class="statement-title">Iterable.</span><br>

대충 `list`처럼 iteration을 수행할 수 있는 녀석을 통칭 *iterable* 이라고 함. 또는 `for ... in ... `로 순회할 수 있는 녀석이라고 생각해도 될 듯.

``` py
mylist = [1, 2, 3]
mylist2 = [x * x for x in range(3)] # list comprehension
```

참고로 `range(n)`, 이 녀석도 iterable이다.

<br/>

<span class="statement-title">Generator.</span><br>

generator 역시 `for ... in ...`으로 순회할 수 있음. 그런데 모든 값을 메모리에 담고 있지 않고, 그때그때 값을 생성해서 반환함! 그래서 generator를 쓸 때는 한번에 하나의 값만 순회할 수 있음!

``` py
mygenerator = (x * x for x in range(3)) # tuple comprehension
for i in mygenerator:
  print(i)
```

list comprehension과 달리 tuple comprehension의 경우 generator를 반환한다. 행동 자체는 iterable과 마찬가지로 `for ... in ...`을 통해 순회할 수 있다.

그.러.나. iteration을 두 번 수행할 수는 없다!! 즉, generator는 한번만 사용할 수 있다는 말이다. 그래서 `for i in mygenerator`를 다시 실행해도 다시는 위와 같은 결과를 얻을 수 없다. 한번으로 끝! 💥

이런 generator를 함수로 만들 수도 있는데, 이 경우 `yield` 키워드를 사용한다.

``` py
def createGenerator(n):
  for i in range(n):
    yield i * i
```

<br/>

<span class="statement-title">Iterable과 Iterator.</span><br>

`tuple`, `list`, `str`과 같은 녀석은 *iterable* 이라고 한다. 이런 iterable에 `iter()`를 씌우면 이 녀석들의 *iterator* 을 얻을 수 있다!!

``` py
mytuple = ("apple", "banna", "cherry")
myiter = iter(mytuple)
```

사실 python의 `for ... in ...` 구문은 자체적으로 iterator를 생성하고, 문단의 끝에 `next()`를 호출하는 방식으로 동작한다고 한다! 😲

<br/>

<span class="statement-title">Iterable과 Enumerator.</span><br>

아래의 두 코드는 동치다.

``` py
# iterable with index
i = 0
for value in myIterable:
  print(i, value)
  i += 1
```

``` py
# iterable with enumerate
for i, value in enumerate(myIterable):
  print(i, value)
```

``` py
for i, value in enumerate(myIterable, start=0):
  print(i, value)
```

<hr/>

<span class="statement-title">Lambda Function.</span><br>

JS의 익명 함수와 동치임.

``` py
square_fun = lambda x: x*x
exp_fun = lambda x, y: x**y
sign = lambda x: (1, -1)[x < 0] # "x < 0"의 값을 tuple의 idx로 사용!
```



