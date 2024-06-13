---
title: "R Tips & Tricks"
toc: true
toc_sticky: true
categories: []
---


이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

## Basics

- `<-`와 `=`의 차이점
  - [자니 님의 포스트](https://newstars.cloud/429)
  - 요약하면, `<-`나 `=`나 동일한 기능을 하지만, 암묵적으로 `<-`를 권장한다고 함. 그리고 `<-`는 할당 이후 변수를 반환하지만, `=`는 할당만 하고 변수를 반환하지 않음.

- 데이터프레임 파악
  - `names(data)`: 데이터프레임의 column 값들을 get
  - `dim(data)`: 데이터프레임의 차원을 리턴; `[# rows] [# columns]`
  - `table(data$y)`: 값의 분포를 테이블의 형태로 보여준다.

- R 데이터 타입
  - `class(obj)`로 데이터 타입 확인 가능!
  - `factor`는 카테고리형 변수
  - `character`는 문자형 변수 // `factor`랑 다름!

``` R
> table(vowel.train$y)

 1  2  3  4  5  6  7  8  9 10 11
48 48 48 48 48 48 48 48 48 48 48
```

- R에서의 `for`문

``` R
> for(i in 1:9) {
+   print(2 * i)
+ }
```

- `predict()`로 모델 evaluation
  - `lm()`으로 모델을 만들었으면 `predict()`로 새로운 데이터를 넣어 Acc를 구한다.
  - ['더북(TheBook)'님의 포스트](https://thebook.io/006723/ch08/02/03/)

``` R
model <- lm(dist ~ speed, cars.train)
predict(model, newdata=cars.test)
```

- 데이터프레임에서 `X`, `y` 분리

``` R
X <- subset(data, select=-Salary)
y <- subset(data, select=Salary)
```

이때, 대상이 되는 열에 대해 따옴표("")를 붙이지 않아야 한다. 또한, `subset` 함수의 출력은 항상 DataFrame이다!

👉 ['훈데이텀'님의 포스트](https://vvwwvw.tistory.com/27)


## Regression

`lm(formula, data, ...)`

``` R
vowel.fit <- lm(y ~., vowel.train)
```

## References

- [STHDA; Statistical Tools for High-throughput Data Analysis](http://www.sthda.com/english/)
  - `R`을 사용한 여러 통계 접근을 친절하게 설명해줌.
- [RPubs](https://rpubs.com/)
  - R 생태계의 github 같은 느낌.
- [rdrr.io](https://rdrr.io/)
  - R package documentation
  - 새로운 R 패키지를 익힐 때 유용!!
