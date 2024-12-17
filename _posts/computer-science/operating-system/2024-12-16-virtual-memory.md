---
title: "[OS] Virtual Memory"
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
    - $2^{32} = 2^{10} \cdot 2^{10} \cdot 2^{12}$
  - 64-bit 시스템은 4단계 페이지 테이블로 나눔
    - $2^{64} = 2^{32} \cdot 2^{10} \cdot 2^{10} \cdot 2^{12}$

# Kernel Memory is not swapped out

커널 메모리를 Demand Paging의 대상이 되지 않습니다. 즉, 지금까지 Paging과 관련되어 살펴본 모든 것은 User 메모리 공간에 대한 테크닉 입니다.

커널의 동작은 항상 신속하게 수행 되어야 합니다. 메모리 데이터를 디스크에 내리거나 올리는 Demand Paging 기법은 커널 동작을 느리게 만들어 전체 시스템의 퍼포먼스를 떨어뜨리게 됩니다. 따라서, 커널 메모리 영역은 물리 메모리에 상주(resident)하게 됩니다.

# Page Address Extension

PAE

# Buddy System

# Slab Allocator

커널 객체들을 대상으로 합니다.

`PCB`나 `inode`와 같은 커널 객체들을 프로그램을 운영하면서 자주 할당 되거나 할당 해제 됩니다. 만약 `PCB` 하나를 생성할 때마다, Page를 하나 할당 하게 된다면, 이것은 비효율적이고 내부 단편화가 일어나게 됩니다. 그래서 같은 종류의 객체를 하나의 페이지에 할당할 수 있는 슬랩(slab)을 운영합니다.

예시를 통해 이해 해봅시다. 파일 시스템에서 파일이나 디렉토리를 관리하는 `inode`라는 구조체가 있습니다. 이것의 크기가 128 바이트라고 하겠습니다.

만약 Slab Allocator를 사용하지 않았다면, `inode` 객체를 할당하기 위한 할당 요청을 할 것 입니다. 메모리는 버디 시스템에 의해 할당 되는데, `inode`는 128 바이트이기 때문에 최소 단위인 4 Kb의 페이지 하나가 할당 됩니다. 그러면 3 Kb 정도의 남은 메모리를 낭비 되고, 이는 내부 단편화가 됩니다.

Slab Allocator는 `inode` 사이즈에 맞는 128 바이트 크기를 위한 메모리 블록을 미리 준비하고 이를 "슬랩"이라고 합니다. 슬랩은 4 Kb 페이지 하나를 여러 128 바이트 객체로 나누어 관리합니다.

새로운 `inode` 할당 요청이 들어오면, OS는 슬랩에 미리 준비된 128 바이트 슬롯을 하나 꺼내고, 그 슬롯에 `inode` 객체를 할당 합니다.

# Buddy System vs. Slab Allocator

Buddy System과 Slab Allocator는 배타적이지 않고, 둘을 같이 쓰기도 합니다.

# No Swapping in mobile platform

Android OS의 경우, 물리 메모리의 내용을 디스크로 내리는 Swapping을 채택하지 않습니다.

대신, 가용 메모리가 부족해지면 현재 실행되는 앱을 종료 하는데, 종료 전에 앱의 상태들을 Flash 저장소에 기록하고, 앱을 재시작 했을 때 Flash 저장소에서 데이터를 읽어서 빠른 재시작을 지원 합니다.

# TLB Miss Handling

Virtual Memory의 주소 변환이 메모리에 접근하지 않고도, 더 빠르게 변환하기 위해 도입한게 TLB 캐시이고, 요건 HW임.

TLB Hit 상황에서야 당연히 TLB 캐시가 동작을 잘 하겠지만, TLB Miss가 발생하면 그때부터 상황이 좀 복잡해짐.

일단 TLB Miss가 발생하면, MMU(Memory Management Unit)에서 페이지 테이블을 메모리에서 검색함. 주소 변환을 성공적으로 수행하면 그 값을 TLB 캐시에 채워넣음.

그런데, 최신의 아키텍처에서는 TLB Miss가 발생하면, MMU 장치가 아니라 "Kernel Trap"을 발생시켜서 소프트웨어(커널)이 이를 핸들링 하도록 하는 경우가 있음!! 임베디드 시스템에과, 일부 RISC 아키텍처의 CPU에서는 하드웨어 복잡성을 최소화 하기 위해 이렇게 처리하는 경우도 있다고 함.

뒷 내용이 아직도 남았다니... 82p부터 보자!

