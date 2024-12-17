---
title: "운영체제 이것저것 메모들 (final)"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: ""
---

3년간 산업기능요원으로 복무 하고, 복학해 컴공과 수업을 듣고 있습니다. 그동안 많이 배웠다고 생각했는데, 여전히 부족한 부분이 많네요 ^^;; 역시 세상도 넓고 공부의 길도 넓은 것 같습니다.
{: .notice--info}

# 들어가며

요 포스트는 운영체제를 공부하면서 짧게 메모 했던 것들을 모은 글 입니다. OS 내용을 복습하면서 살펴보면 좋을 것 같습니다 🙂

# Interrupt Frame

Interrupt가 발생하면, 실행 중인 쓰레드는 CPU 자원을 Kernel(Interrupt Handler)에게 뺏이게 됩니다. 이때, 쓰레드는 Interrupt 발생 당시의 레지스터 값들을 모두 Memory에 저장해두어야 합니다.

이때, 사용하는 데이터 구조가 "**Interrupt Frame**" 입니다! Pintos P2에서 많이 다룬 녀석이죠!

# Gate Mechanism

['sunshout'님의 "[Gate] Task Gate, Trap Gate, Interrupt Gate, Call Gate"](https://sunshout.tistory.com/1524) 글이 개념을 이해하는데 도움이 되었습니다.

> 낮은 특권(Ring 3)에서 높은 특권(Ring 0)으로 전환하는 통로(Gate)

- Trap Gate
  - System call이 요 방식임.
  - Exception Handling도 요 방식임.
  - 인터럽트 처리 도중에 다른 인터럽트가 발생하여 중첩 될 수 있음!
- Interrupt Gate
  - 하드웨어의 인터럽트를 처리하기 위해 사용함.
  - Interrupt Flag를 비활성화 하여, 추가적인 인터럽트를 방지함.
    - 왜냐하면, 하드웨어 인터럽트를 처리할 땐, 인터럽트 중첩을 방지해야 하기 때문임!

# Virtual Memory

Segment 기법이든 Paging 기법이든 Code와 Data 영역은 다른 프로세스와 공유 가능함. 그런데, Stack 영역은 다른 프로세스와 공유 불가임!

# System Call for Heap Allocation

- `int brk(void *addr)`
  - 힙 영역의 끝을 지정된 주소로 확장
- `void sbrk(intptr_t increment)`
  - 현재 힙의 break를 기준으로 increment 크기 만큼 힙을 확장하거나 축소함.

"break"란? 힙 영역의 끝을 말함. 이 주소를 조정해 힙 영역을 확장하거나 축소함.

`malloc()`과 같은 메모리 할당 함수는 내부적으로 `brk` sys call을 통해 메모리를 관리한다고 함. 그러나 `brk`, `sbrk`를 직접 호출하는 건 권장되지 않음!


# UNIX fork

process에서 `fork()`를 실행하면, 모든 것이 똑같은 복제본을 만듦. 순서는 아래와 같음.

1. 부모의 Segment Table을 자식에게 Copy
   1. 즉, 메모리 상으로는 아무런 Copy가 일어나지 않음.
2. 공유 중인 부모와 자식의 모든 세그먼트를 **RO 권한**으로 변경
3. 자식 프로세스 시작
4. 만약 자식/부모 프로세스가 어떤 세그먼트(stack, heap)든 write 작업이 일어난다면
   1. Trap into OS Kernel
   2. Trap(Exception) Handler가 핸들링 하면서 해당 세그먼트에 대한 Copy 본을 만들고 실행을 이어감.

요게 Paging 버전으로 바뀌어도 비슷함.

1. 부모의 Page Table을 자식에게 Copy
2. 공유 중인 부모와 자식의 모든 Page를 **RO 권한**으로 변경
3. 자식 프로세스 시작
4. 만약 자식/부모 프로세스가 어떤 Page(stack, heap)든 write 작업이 일어난다면
   1. Trap into OS Kernel
   2. Trap(Exception) Handler가 핸들링 하면서 해당 Page에 대한 Copy 본을 만들고 실행을 이어감.


# Zero-on-reference

그냥 Stack 확장이 일어날 때, 새로 할당 받은 영역을 처음에 0으로 초기화 한다는 말임.

이유는 우연히라도 정보가 유출되는 걸 원하지 않아서, 참조 전에 0으로 초기화 하는 것임.

# Big Page: Pros & Cons

[장점]

- 페이지 테이블 크기가 감소
  - PTE 갯수가 줄기 때문
- 디스크 I/O 효율성 증가
  - Page Fault 발생시 더 많은 데이터를 가져옴
- TLB 히트율 증가
  - 저장된 페이지가 더 넓은 주소를 커버하면서, TLB에서 더 많은 주소를 조회할 수 있음

[단점]

- 내부 단편화가 커짐
- 간헐적으로 사용하는 데이터를 위해 큰 페이지를 로드 해야 함
- Page Fault 처리가 느려짐
  - 디스크 I/O로 불러올 데이터가 더 많아져서

# Big Virtual Address: Pros & cons

"Sparse Address Space", 주소 공간이 연속적이지 않고 드문드문 존재해야 하는 이유에 대해서 저 나름 대로 이해 해본 내용입니다.

[필요성]

- per-thread stacks
  - 멀티쓰레드 환경에서 각 쓰레드는 독립적인 스택 공간을 필요로 합니다.
  - 각 쓰레드의 스택 공간이 겹치지 않기 위해선 주소 공간이 충분히 넓어야 합니다.
- Memory-mapped files
  - 파일의 내용을 유저 주소 공간에 매핑할 때, 주소 공간이 충분히 넓어야 합니다.
  - 그래야 파일 크기가 커도 그것을 유저 공간에 매핑할 수 있습니다.

[단점]

- 페이지 테이블 사이즈가 아주 커짐
  - PTE 갯수가 아주 많아지기 때문

[해결방법]

- 다단계 페이지 테이블 사용
  - 페이지 테이블 전체를 Memory에 올려둘 필요 없음.
  - 지금 쓰는 부분에 대한 페이지 테이블만 올려두는 기법
- 인버스 페이지 테이블
  - 현재 사용 중인 페이지만 테이블에 저장

# x86 Multi-level PAged Segmentation

세그멘테이션과 다단계 페이지 테이블을 결합한 기법임. (자주 보이게 되는 x86는 Intel 모델에서 도입 되었다는 뜻)

- Global Descriptor Table (GDT)
  - 세그먼트 테이블임
  - 각 행에 담긴 정보는 아래와 같음.
    - 각 세그먼트 관리하는 페이지 테이블의 포인터를 가리킴
    - 세그먼트의 길이
    - 세그먼트의 접근 권한 (RWXA)
  - GDTR(GDT Registry)라는 레지스터에 저장된 포인터를 변경함으로써 다른 프로세스의 GDT로 전환됨.
  - Context Switch 과정은 요 GDTR 레지스터의 포인터가 바뀌는 것임.
- Multi-level Page Table
  - 페이지 크기는 4 Kb ($2^12$)
    - offset bit가 12 bit로 고정
  - 32-bit 시스템은 2단계 페이지 테이블로 나눔
    - $2^32 = 2^10 \cdot 2^10 \cdot 2^12$
  - 64-bit 시스템은 4단계 페이지 테이블로 나눔
    - $2^64 = 2^32 \cdot 2^10 \cdot 2^10 \cdot 2^12$

# Page Address Extension

PAE

# Buddy System

# Slab Allocator

음... 뭔가 요게 잘 이해가 안 되넹

# Buddy System vs. Slab

