---
title: "Fibonacci Number"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<span class="statement-title">TOC.</span><br>

- Brute Force
- DP
- Matrix-based
  - Piasno Period

<hr/>

\<Fibonacci Number\>는 재귀 함수에 대한 근본급의 주제이다. 일단 재귀식 자체가 직관적이고, 또 이것을 최적화 하기 위해 정말 많은 테크닉이 제시되고 또 연구되었다.

$$
f(n) = f(n-1) + f(n-2)
$$

<hr/>

#### Brute Force

가장 먼저 생각해볼 수 있는 방법은 \<Fibonacci Number\>의 정의에 맞게 함수를 짜는 것이다. 그냥 아래와 같이 짜면 된다.

``` cpp
int fibo(n) {
  if (n <= 1) return n;
  return fibo(n-1) + fibo(n-2);
}
```

그러나 위의 알고리즘을 썼을 때의 시간 복잡도는 exponential 하다. 🤦‍♂️

<div class="math-statement" markdown="1">

`fibo()`에 의해 생성되는 recursion tree를 생각해보자. 이때, tree의 leaf는 항상 1을 리턴해주고 이에 따라 `fibo(n)`의 값은 단순히 recusion tree의 leaves 수로 유도할 수 있다.

이때, leaf 단에서는 $O(1)$ 만큼의 시간이 걸리기 때문에 결국, $T(n) = \text{fibo}(n) \cdot O(1)$의 복잡도를 가지게 된다.

따라서, 재귀로 구현된 \<Fibonacci number\>는 결국 \<Fibonacci number\>의 값 자체의 시간 복잡도를 가지게 된다. 따라서, 이것은 대략

$$
\Theta \left( \left(\frac{1+\sqrt{5}}{2} \right)^n \right) = \Theta(1.6^n)
$$

의 복잡도를 가진다. // 보통은 $O(2^n)$라고 부르는 것 같다.

</div>

이렇게 알고리즘이 exponential한 복잡도를 갖게 되면, 애초에 컴퓨터의 계산으로 감당할 수가 없다. 단순히 $\text{Fibo}(100)$을 구하는 데만 해도 12日이 필요하다. 그래서 지금의 방식은 correct 하지만 실전에서 쓸 수는 없다 🤦‍♂️

<hr/>

#### DP

DP는 이 상황을 정말 시원하게 해결해준다. DP에서는 \<memoization\>을 통해 계산한 값을 배열에 기록해둔다. 따라서, 다시 해당 값에 대한 쿼리가 들어왔을 때, 계산을 또 하지 않고 배열에 저장된 값을 바로 사용하기만 하면 된다!! 😁

``` cpp
int memo[MAX];

int fibo(n) {
  if (n <= 1) return n;
  if (memo[n] != -1) return memo[n];
  memo[n] = fibo(n-1) + fino(n-2);
  return memo[n];
}
```

코드를 이렇게 짤 경우, 우리는 $1$부터 $n$까지 추가적인 재귀호출 없이 딱 한번씩 순회하게 되는 것이므로 $O(n)$의 시간이면 충분하다!! 😲

💥 이때, 주의할 점은 아무리 DP를 쓰더라도 재귀로 구현했다면, 재귀 함수 호출이 1,000,000(=백만) 정도 되면 프로그램이 정상적으로 동작하지 않는다! 😲 그래서 왠만하면, DP로 풀더라도 재귀로 구현하기 보다는 `for`문으로 적절히 변형해서 구현하는 걸 강.력. 추천한다.

<hr/>

#### Matrix-based

<div class="notice" markdown="1">

<span class="statement-title">Property.</span> Pisano Period<br>

피보나치 수를 정수 $K$로 나눈 나머지는 항상 주기를 가지게 된다. 이 주기를 \<Pisano Period\>라고 한다.


</div>

Matrix-based 방법은 좀더 큰 범위의 피보나치의 수를 다룰 때 더 빠르게 계산할 수 있는 테크닉이다! 😲

피보나치 수를 행렬로 표현하면 아래와 같다.

$$
\begin{pmatrix}
  F_{n+2} \\
  F_{n+1}
\end{pmatrix} = \begin{pmatrix}
  1 & 1 \\
  1 & 0
\end{pmatrix} \begin{pmatrix}
  F_{n+1} \\
  F_n
\end{pmatrix}
$$

위의 식을 좀더 다듬고 정리하면, 아래와 같은 결과를 얻을 수 있다. 그냥 단순하게 우변의 피보나치에 대한 부분에 식을 대입하면 얻는 식이다.

$$
\begin{pmatrix}
  F_{n+1} & F_n \\
  F_n & F_{n-1}
\end{pmatrix} = \begin{pmatrix}
  1 & 1 \\
  1 & 0
\end{pmatrix}^n
$$

즉, 우리는 피보나치 수를 얻고 싶다면, $$\begin{pmatrix}
  1 & 1 \\
  1 & 0
\end{pmatrix}$$를 $n$번 곱해주면 된다는 것이다. 참고로 이런 행렬의 제곱은 분할 정복을 이용해 $O(\log n)$만에 계산할 수 있다!!

결국, matrix-based 방식을 쓰면, 피보나치 수를 $O(\log n)$의 시간 복잡도로 구할 수 있는 것이다!!! 😁

✨ 이 Matrix-based 방식은 피보나치 수 뿐만 아니라 다른 재귀 함수 식에서도 비슷한 논리로 적용할 수 있다!! 😁

<hr/>

💥 참고로 피보나치 수의 경우, $n=100$만 되어도 `int`의 범위를 벗어나게 된다. 그러니 넉넉하게 생각해 미리 `long long`으로 구현해두자!

<hr/>

- [백준 10870번: 피보나치 수 5](https://www.acmicpc.net/problem/10870)
- [피보나치 수를 구하는 여러가지 방법](https://www.acmicpc.net/blog/view/28) 🔥 피보나치와 관련된 거의 모든 내용이 들어있다 🔥
- [백준 11726번: 2xn 타일링](https://www.acmicpc.net/problem/11726) // 피보나치와 함께 재귀-DP의 대표 문제! ✨