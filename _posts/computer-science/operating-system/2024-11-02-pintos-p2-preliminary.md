---
title: "Pintos P2: Preliminary"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: "Pintos P2 - 사전 작업."
---

3년간 산업기능요원으로 복무 하고, 복학해 컴공과 수업을 듣고 있습니다. 그동안 많이 배웠다고 생각했는데, 여전히 부족한 부분이 많네요 ^^;; 역시 세상도 넓고 공부의 길도 넓은 것 같습니다.
{: .notice--info}

이 글은 제가 Pintos P2를 구현하기 위해 Pintos 코드와 각종 자료를 찾아보며, 정리한 사전 자료 입니다. Pintos P2 코드를 이해하기 위한 흔적이라고 보시면 될 것 같습니다.

# Virtual Memory

사실 Virtual Memory는 Pintos P3에서 구현하는 주제이다. 그런데, 중간고사 준비할 때부터 용어가 튀어 나와서 어쩔 수 없이 미리 공부 했다.

[한빛미디어](https://youtu.be/8ufliWkgqMo?si=lbXR9R0jXnj51l9-)에서 이 주제에 대해 잘 정리한 영상이 올라와 있으니 참고하도록 한다. 여기에는 요약만 적어둔다.

## What is Virtual Memory

일단 "가상 메모리"라는 기술은 실제 물리 메모리보다 큰 프로세스를 실행할 수 있게 하는 기술이다.

<p><a href="https://commons.wikimedia.org/wiki/File:Virtual_memory.svg#/media/File:Virtual_memory.svg">
<img src="https://upload.wikimedia.org/wikipedia/commons/6/6e/Virtual_memory.svg" alt="Virtual memory.svg" height="490" width="310">
</a><br>
By <a href="//commons.wikimedia.org/w/index.php?title=User:Ehamberg&amp;action=edit&amp;redlink=1" class="new" title="User:Ehamberg (page does not exist)">
Ehamberg</a> - <span class="int-own-work" lang="en">Own work</span>, <a href="https://creativecommons.org/licenses/by-sa/3.0" title="Creative Commons Attribution-Share Alike 3.0">CC BY-SA 3.0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=8352077">Link</a>
</p>

구현의 컨셉은 간단한데, 실행하고자 하는 프로그램의 "일부"만 메모리에 적재하고, 실행한다. 이것은 프로그램을 실행할 때, 프로그램이 사용하는 메모리 공간 전체를 메모리에 올려둘 필요가 없기 때문이다. 자주 사용하는 데이터는 메모리에 올려두고 사용하며, 자주 사용하지 않는 데이터는 Disk로 내려서(swap-out) 잠시 보관 했다가 사용할 타이밍이 오면 꺼내서(swap-in) 사용하면 된다.

가상 메모리를 구현하는 방법은 2가지가 있는데, Paging과 Segmentation이다.

## Paging

프로세스의 논리 주소 공간을 "**페이지(page)**"라는 단위로 자른다. 이때, 각 페이지의 크기는 모두 동일하다(ex. 4 kb). 그리고 물리적 메모리의 주소 공간을 "**프레임(frame)**"라는 단위로 자른다. 프레임은 페이지와 동일한 크기를 갖는다.

프로세서의 각 페이지는 프로세스 관점에서 보면, 연속한 주소 공간에 있더라도, 물리 메모리인 프레임 관점에서 보면 불연속적으로 배치 된다.

그리고 이 페이지와 프레임의 매핑 정보를 가지고 있는 것이 "**페이지 테이블(Page Table)**"이다.

이 페이지 테이블은 메모리 접근이 발생할 때마다 접근해야 하는 데이터로, 접근이 매우 빈번하다. 그래서 CPU에 가까운 캐시 메모리에 저장 해두는데, 이 캐시 메모리의 자료 구조를 "**TLB(Translation Lookaside Buffer)**"라고 한다. 물론, TLB에 Page Table의 내용 전체를 두기 어려울 수 있고, 이런 경우 "TLB missed"가 발생할 수도 있다.


## Segmentation

세그먼트(Segment)는 메모리의 특정 구역을 나타낸다. 구역이라고 하면, Code, Data, Stack 구역을 말하며 각각

- Code Segment (CS)
- Data Segment (DS)
- Stack Segment (SS)

라고 한다. 각 세그먼트마다 다른 접근 권한을 부여하는데, 코드 영역은 RX만 가능하고, Write를 불가하다.

그리고 한 프로세스가 다른 프로세스의 메모리 세그먼트에 접근하지 못 하도록 해야 한다.

<br/>

세그먼트와 페이징 기법을 비교하면, 세그먼트의 기본 단위인 "세그먼트"는 그 크기가 정해지지 않고 가변적이다. 그래서 한 프로세스의 Program Code는 물리 메모리 공간 상에 하나의 세그먼트에 모두 들어있으며 연속된 공간에 배치된다.

반면에, 페이징에서는 코드 영역은 여러 페이지로 나누었고, 물리 메모리에서 각 코드 프레임이 연속적으로 존재하지 않는다.

# Kernel Memory vs. User Memory

물리 메모리 공간은 크게, 커널 메모리 공간과 유저 메모리 공간으로 나뉜다. 두 공간은 서로 침범할 수 없는 공간이다. 커널 공간에는 커널 동작에 필요한 code, data, stack, heap 섹션이 존재한다. 유저 공간에는 유저 프로그램이 실행하는 code, data, stack, heap이 저장된다. 유저 공간에는 여러 프로그램이 공간을 점유할 수 있다.

하지만 본인은 직접 확인하지 않으면 찝찝하니... 과연이 이 말이 진짜인지 궁금해졌다 ㅋㅋㅋ
찾아보니 `vm_stats`라는 명령어를 쓰면 커널 메모리 공간으로 얼만큼이 잡혀 있는지 확인할 수 있는데,

```bash
$ vm_stats
Mach Virtual Memory Statistics: (page size of 16384 bytes)
Pages free:                               18228.
Pages active:                            952019.
Pages inactive:                          948651.
Pages speculative:                         2472.
Pages throttled:                              0.
Pages wired down:                        212664.
Pages purgeable:                          57595.
...
```

내가 사용하고 있는 M3 맥북 기준으로는 `212664 * 16384 bytes = 3.48 Gb` 정도가 wired memory, 고정된 메모리 공간으로 잡혀 있다. 참고로 현재 맥북의 전체 메모리 사이즈는 `36 Gb`이다. ~~감사합니다 회사님 🥯~~ 대충 10% 정도가 커널 공간으로 잡혀 있는 셈이다.


# Interrupt Frame

인터럽트가 발생 했을 때, 현재 작업을 멈추고 인터럽트 핸들러가 실행되는 동안 CPU의 상태를 저장해두기 위해 사용함.

인터럽트가 발생하면, CPU는 현재 레지스터의 상태, PC, EFLAS를 모두 스택에 저장한다. 스택에 저장할 때, 그냥 저장하는게 아니라 Interrupt Frame라는 거에 담아서 저장한다.

<br/>

[Stack Frame](/2024/10/23/stack-frame/)에 대해서는 중간고사 공부하면서 따로 정리를 해뒀는데, Interrupt Frame을 공부하고 나니 둘다 프레임인데, 어떤게 다른지 궁금해졌다.

# inline assembly

Pintos P2부터는 C코드에서 어셈블리 코드도 등장한다 ㅎㄷㄷ;; 아래와 같이 나오는데, 이번에 처음보는 코드라 한번 정리해본다.

```c
asm volatile ("movl %0, %%esp; jmp intr_exit" : : "g" (&if_) : "memory");
```

일단 `asm`은 inline assembly를 쓰기 위한 키워드이다. `asm ("INLINE ASM")`으로 실행할 수 있다.

`volatile`은 C언의 최적화 관련 키워드이다. 컴파일러가 이 코드 라인을 최적화 하지 않도록 강제한다. ASM 코드를 쓰는 경우, 컴파일러가 최적화를 통해 ASM 코드를 수정할 수 있는데, 이 경우 의도와 다른 동작을 하도록 수정될 우려가 있다. 이런 컴파일러 최적화를 비활성화 해주는게 `volatile` 키워드라고 한다. `volatile` 키워드는 꼭 ASM 코드랑 같이 써야 하는 건 아니다. 일반적인 C 코드에서도 쓸 수 있다.


# `strtok_r()`

```c
/* Breaks a string into tokens separated by DELIMITERS.  The
   first time this function is called, S should be the string to
   tokenize, and in subsequent calls it must be a null pointer.
   SAVE_PTR is the address of a `char *' variable used to keep
   track of the tokenizer's position.  The return value each time
   is the next token in the string, or a null pointer if no
   tokens remain.

   This function treats multiple adjacent delimiters as a single
   delimiter.  The returned tokens will never be length 0.
   DELIMITERS may change from one call to the next within a
   single string.

   strtok_r() modifies the string S, changing delimiters to null
   bytes.  Thus, S must be a modifiable string.  String literals,
   in particular, are *not* modifiable in C, even though for
   backward compatibility they are not `const'.

   Example usage:

   char s[] = "  String to  tokenize. ";
   char *token, *save_ptr;

   for (token = strtok_r (s, " ", &save_ptr); token != NULL;
        token = strtok_r (NULL, " ", &save_ptr))
     printf ("'%s'\n", token);

   outputs:

     'String'
     'to'
     'tokenize.'
*/
char *
strtok_r (char *s, const char *delimiters, char **save_ptr) 
{
  char *token;
  
  ASSERT (delimiters != NULL);
  ASSERT (save_ptr != NULL);

  /* If S is nonnull, start from it.
     If S is null, start from saved position. */
  if (s == NULL)
    s = *save_ptr;
  ASSERT (s != NULL);

  /* Skip any DELIMITERS at our current position. */
  while (strchr (delimiters, *s) != NULL) 
    {
      /* strchr() will always return nonnull if we're searching
         for a null byte, because every string contains a null
         byte (at the end). */
      if (*s == '\0')
        {
          *save_ptr = s;
          return NULL;
        }

      s++;
    }

  /* Skip any non-DELIMITERS up to the end of the string. */
  token = s;
  while (strchr (delimiters, *s) == NULL)
    s++;
  if (*s != '\0') 
    {
      *s = '\0';
      *save_ptr = s + 1;
    }
  else 
    *save_ptr = s;
  return token;
}
```


실제 코드에서 살펴보자면

```c
tid_t
process_execute (const char *file_name) // 여기
{
  char *fn_copy;
  /* Make a copy of FILE_NAME.
     Otherwise there's a race between the caller and load(). */
  fn_copy = palloc_get_page (0);
  if (fn_copy == NULL)
    return TID_ERROR;
  strlcpy (fn_copy, file_name, PGSIZE);
  ...
}
```

# palloc

'Page allocator'의 약자로 해당 구현은 `src/threads/palloc.c` 파일에서 확인할 수 있다. 아래 내용은 해당 파일에 있는 주석의 내용의 해석.

> Page allocator. Hands out memory in page-size (or page-multiple) chunks.  See malloc.h for an allocator that hands out smaller chunks.

메모리를 페이지 크기(보통 4kb) 또는 그것의 정수배로 할당하는 녀석입니다. 만약, 그것보다 적은 청크로 메모리를 할당해야 한다면, `malloc()`을 쓰라고 하네요.

> System memory is divided into two "pools" called the kernel and user pools. The user pool is for user (virtual) memory pages, the kernel pool for everything else.

시스템의 메모리를 커널 풀과 유저 풀로 나눈다고 한다. 유저 풀은 유저 프로세스가 쓰는 용도이고, 커널 풀은 커널이 쓰기 위한 공간이다.

> The idea here is that the kernel needs to have memory for its own operations even if user processes are swapping like mad.

메모리 스왑(swap)은 메모리가 부족한 상황에서 발생한다. 만약 유저 프로세스가 메모리 부족으로 미친듯이(like mad) 스왑되는 상황이더라도, 커널이 필요로 하는 메모리는 커널 풀에서 항상 확보되도록 한다는 의미이다.

> By default, half of system RAM is given to the kernel pool and half to the user pool. That should be huge overkill for the kernel pool, but that's just fine for demonstration purposes.

시스템 메모리의 절반은 커널 풀, 나머지 절반은 유저 풀로 나눈다는 말. 데모 용도로는 이 정도로 설정해도 충분하다고 한다 ㅎㅎ

TODO: 이게 뭔지

## `palloc_get_page()`

## `palloc_free_page()`

`palloc_get_page()`로 할당 받은 페이지를 해제해주는 명령어.

꼭, 해제 해줘함!!

TDB...

