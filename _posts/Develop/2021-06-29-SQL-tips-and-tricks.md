---
title: "SQL Tips & Tricks"
layout: post
tags: ["develop", "Tips & Tricks"]
---

<br/>

<br><span class="statement-title">GROUP BY.</span><br>

- groups rows that have the same values
- 보통 **aggregate function**인 `COUNT()`, `MAX()`, `MIN()`, `SUM()`, `AVG()`와 함께 쓰임!

``` sql
SELECT
    COUNT(CustomerID), Country
FROM
    Customers
GROUP BY
    Country
```

-  `GROUP BY`에 사용한 컬럼이 쪽 `SELECT` 절에 포함될 필요는 없다. 🔥
- `SELECT`절에 사용한 컬럼은 반드시 `GROUP BY`에 입력되어야 한다!! 🔥🔥
  - 단, aggregate function에 사용한 컬럼은 제외임
  - 입력이 `GROUPY BY`에 안 써도 실행은 되지만, 적절한 결과가 아니기 때문에 쓸모가 없음!

- `GROUPY BY`를 사용해 Distinct 요소를 뽑을 수도 있음

``` sql
SELECT
    Country
FROM
    Customers
GROUP BY
    Country
```

- ✨ 보통 "OO별"이라는 표현이 들어가면 `GROUP BY`로 해결이 된다!

<hr/>

## Aggregate Function

- `GROUP BY`와 짝꿍처럼 붙어다니는 녀석
  - 단, Aggregate Function은 `GROUPY BY` 없이 단독으로 쓸 수 있음!

<br><span class="statement-title">COUNT.</span><br>

- `COUNT(*)`
  - null 값에 상관없이 모은 행의 갯수를 카운트
- `COUNT(컬럼)`
  - 해당 컬럼의 값이 null인 것들을 제외하고 카운트

``` sql
SELECT
    COUNT(*)
FROM
    Customers
```

``` sql
SELECT
    COUNT(Country)
FROM
    Customers
```

만약 null을 포함해서 카운트 하고 싶다면, `NVL()`, null 치환 함수를 사용하면 된다.

``` sql
SELECT
    COUNT(NVL(Country, 0))
FROM
    Customers
```

▼ `COUNT` + `GROUP BY` ▼

``` sql
SELECT 
    Country, 
    COUNT(CustomerId) as num_customer
FROM 
    Customers
GROUP BY 
    Country;
```

▼ 컬럼 범위 확인 ▼

``` sql
SELECT 
    MIN(CustomerId), 
    MAX(CustomerId)
FROM 
    Customers
```

▼ **나라별** 컬럼 범위 확인 ▼

``` sql
SELECT 
    MIN(CustomerId), 
    MAX(CustomerId), 
    Country
FROM 
    Customers
GROUP BY 
    Country;
```

<hr/>

<br><span class="statement-title">CAST.</span><br>

- 컬럼의 type을 변환할 때 쓰는 형변환 함수

``` sql
CAST(title as char(30))
```


