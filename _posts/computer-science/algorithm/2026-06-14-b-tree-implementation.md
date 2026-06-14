---
title: "BST, B-Tree, B+Tree 구현"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---

> xfs는 디렉터리, Extent 관리, free space 관리 등 여러 파일시스템 메타데이터를 B+Tree로 관리한다.

Linux 파일시스템 쪽을 공부하면서 봤던 문구 입니다. 여기에서 B+Tree로 관리한다고 하는 걸 봤는데, 어느날 내가 B+Tree를 한번도 직접 구현해본 적이 없다는 걸 깨닫게 되었어요.

또, 즐겨보는 유튜버 코딩애플의 "[index가 뭔지 설명해보세요 (개발면접시간)](https://youtu.be/iNvYsGKelYs?si=Ejjmhj7x76kcQq34)" 영상에서 DB Index를 설명하면서 B-Tree와 B+Tree를 설명이 있었는데요. B+Tree가 좋은 자료구조인 건 알겠는데, 이게 구체적으로 어떻게 동작하는지 나 스스로 설명할 수 있는지 평가하고 싶었어요.

그래서 이번 주말에 각 잡고 이걸 python으로 구현해봤습니다! 이 포스트는 구현 과정에서의 했던 생각들과 구현 과정을 아카이빙 하는 목적의 포스트예요.

제가 스스로 이해하고 설명하기 위해서 저의 생각의 흐름대로 작성했고, 코드도 재구성하면서 일부 오류가 있을 수도 있습니다.


<br/>

처음 시작은 BST(Binary Search Tree)로 시작했고, 거기서부터 B-Tree, B+Tree 순서대로 구현 했다.

# Binary Search Tree

일단 BST의 노드부터 정의한다.

```python
from __future__ import annotations

class Node:
  # 현재 노드가 저장할 값.
  def __init__(self, key):
    self.key = key
    self.left: Node | None = None
    self.right: Node | None= None
```

`key`보다 크면, right에 배치하고, `key`보다 작으면 left에 배치한다.



```python
class BST():
  def __init__(self):
    self.root = None

  def insert(self, key: int):
    pass

  def search(self, key: int):
    pass

  def remove(self, key: int):
    pass
```

그리고 이렇게 3개 함수를 구현한다.


## Insert


```python
class BST():
  ...
  def insert(self, key: int):
    if self.root is None:
      print(f"Insert {key} as root")
      self.root = Node(key)
      return

    current_node = self.root

    while True:
      if current_node.key == key:
        print(f"{key} already exists in BST")
        return
      elif current_node.key > key:
        if current_node.left is None:
          print(f"Insert {key} as left child of {current_node.key}")
          current_node.left = Node(key)
          return
        current_node = current_node.left
      else:
        if current_node.right is None:
          print(f"Insert {key} as right child of {current_node.key}")
          current_node.right = Node(key)
          return
        current_node = current_node.right
```

`current_node`의 key와 탐색 key를 비교하며, 좌/우 노드로 이동한다.

## Search

```py
class BST():
  ...
  def search(self, key: int):
    current_node = self.root

    while current_node:
      if current_node is None:
        print(f"{key} not found in BST")
        return False
      elif current_node.key == key:
        print(f"{key} found in BST")
        return True
      elif current_node.key > key:
        current_node = current_node.left
      else:
        current_node = current_node.right

    print(f"{key} not found in BST")
    return False
```

마찬가지로 `current_node`의 key와 탐색 key를 비교하며, 좌/우 노드로 이동한다.

## Remove

삽입/탐색 연산보다 약간 복잡하다.

```py
class BST():
  ...
  def remove(self, key: int):
    if self.root is None:
      print("BST is empty")
      return

    parent = None
    current = self.root

    # 어떤 노드를 삭제해야 하는지 탐색
    while True:
      if key == current.key:
        break
      elif key < current.key:
        parent = current
        current = current.left
      else:
        parent = current
        current = current.right

      if current is None:
        print(f"{key} not found in BST")
        return
```

동일하게 삭제할 노드를 Binary Search 방식으로 찾는다.

이제 노드를 삭제해야 하는데, 자식 노드가 몇개 있는지에 따라 과정이 달라진다.

- **삭제할 노드가 자식 노드 없음**
  - 노드를 삭제하고, 부모 노드에서도 연결 해제
- **삭제할 노드가 자식 노드 1개를 가짐**
  - 노드를 삭제하고, 부모 노드에 해당 노드를 연결
- **삭제할 노드가 자식 노드 2개를 가짐**
  - 노드를 삭제하고
  - 케이스가 나뉜다.
    - 자식 노드 중 오른쪽 서브트리에서
      - 가장 작은 값을 찾아서 새로운 루트 노드로 삼음
      - 그리고 새로운 루트 노드를 부모 노드에 연결
    - 자식 노드 중 왼쪽 서브트리에서
      - 가장 큰 값을 찾아서 새로운 루트 노드로 삼음
      - 그리고 새로운 루트 노드를 부모 노드에 연결



```py
class BST():
  ...
  def remove(self, key: int):
    ...
    # current의 자식이 0개인 경우
    if current.left is None and current.right is None:
      print(f"Remove {key} from BST with 0 children")
      if parent is None:
        self.root = None
      elif parent.left == current:
        parent.left = None
      else:
        parent.right = None

    # current의 자식이 1개인 경우
    if current.left is None and current.right is not None:
      print(f"Remove {key} from BST with 1 child")
      if parent is None:
        self.root = current.right
      elif parent.left == current:
        parent.left = current.right
      else:
        parent.right = current.right

    if current.left is not None and current.right is None:
      print(f"Remove {key} from BST with 1 child")
      if parent is None:
        self.root = current.left
      elif parent.left == current:
        parent.left = current.left
      else:
        parent.right = current.left

    # current의 자식이 2개인 경우
    # 오른쪽 서브트리에서 가장 작은 값을 찾아서 교체
    if current.left is not None and current.right is not None:
      print(f"Remove {key} from BST with 2 children")
      right = current.right
      right_parent = current

      # `right.left`을 탐색하면서, 가장 작은 값의 노드를 탐색
      while right.left:
        right_parent = right
        right = right.left

      if right_parent == current:
        # while 루프를 한번도 안 돈 케이스
        right_parent.right = right.right
      else:
        # while 루프를 한번이라도 탄 케이스
        # 가장 작은 값에 대한 right 노드가 존재할 수 있음.
        # 이걸 해당 노드의 left에 연결해줘야 함.
        right_parent.left = right.right

      current.key = right.key
      print(f"Replace {key} with {right.key}")
```

# B-Tree

B-Tree는 BST와 다른 자료구조입니다.

- BST는 노드당 가질 수 있는 key가 1개 입니다.
  - 그리고 자식 노드도 최대 2개가 가능합니다.
- B-Tree는 노드당 key가 최대 `2t - 1`개 가능합니다.
  - 노드 내에서는 key를 정렬된 상태로 저장합니다.
  - 그리고 자식 노드는 key 갯수 `k`일 때, `k+1`이 가능합니다.

예를 들어, B-Tree는 이렇게

```
    [10 | 20 | 30]
   /    |    |    \
  c0    c1   c2    c3
```

B-Tree에서 노드가 얼마나 채워질 수 있는지를 결정하는 파라미터 `t`를 최소 차수(minimum degree)라고 합니다.

`t`가 커질수록 한 노드에 더 많은 키와 자식을 담을 수 있습니다. 그래서 `t=2`일 때와 `t=100`일 때를 비교하면, `t=100`인 B-Tree는 더 얕은 높이를 가집니다.


여기에서는 `t=2`로 고정하고 코드를 작성 했습니다.

```py
from __future__ import annotations

class BTreeNode:
  def __init__(self, leaf: bool = True):
    # leaf 여부는 len(children) == 0으로도 판단할 수 있음.
    self.leaf = leaf
    self.keys: list[int] = []
    self.children: list[BTreeNode] = []

class BTree:
  def __init__(self):
    # 각 노드는 최대 3(=2t-1)개의 키를 가질 수 있음.
    self.t = 2
    self.root = BTreeNode(leaf=True)

  def insert(self, key: int):
    pass

  def search(self, key: int):
    pass
```

기본적인 연산인 `insert()`와 `search()`만 구현 했습니다.

## Insert

우선 B-Tree가 최초로 만들어지는, root 노드가 생성되는 단계부터 시작합니다.

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if root.leaf and len(node.keys) < 3:
      if key in node.keys:
        print(f"Key {key} already exists in the tree.")
        return
      node.keys.append(key)
      node.keys.sort()
      return
    else:
      pass
```

루트 노드만 존재하고, 루트 노드에 저장된 키가 3보다 작다면, 루트 노드에 데이터를 넣으면 됩니다.

경계는 루트 노드의 키 갯수가 3인 순간부터 입니다. B-Tree는 `노드의 키가 2t-1을 넘지 않도록 해야 한다`는 걸 반드시 지켜야 합니다. 노드가 너무 많은 키를 가지게 된다면, `_split()` 연산으로 노드를 분할합니다.

```
     [ 10, 20, 30 ]
---
새로운 root 생성
old root를 new_root의 0번째 child로 연결
        [     ]        <- new_root
           |
           |
     [ 10, 20, 30 ]    <- old root
---
가운데 키 20을 parent로 올립니다.
          [ 20 ]
         /      \
    [ 10 ]      [ 30 ]
```

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if root.leaf and len(root.keys) < 3:
      if key in root.keys:
        print(f"Key {key} already exists in the tree.")
        return
      root.keys.append(key)
      root.keys.sort()
      return
    elif root.leaf and len(root.keys) == 3:
      # 신규 루트를 발급
      new_root = BTreeNode(leaf=False)

      # 지금의 루트를 신규 루트에 붙임
      new_root.children.append(root)

      self.root = new_root
      self._split_child(new_root, 0)

      ...
    else:
      pass
```

당연하게도 지금 꽉찬 루트를 대신할 새로운 루트 노드 `new_root`를 마련 합니다.

이제 노드 분할 로직을 구성합니다.

```py
class BTree:
  ...
  def _split_child(self, parent: BTreeNode, idx: int):
    # parent의 idx 번째 자식 노드를 분할해 parent의 자식 노드로 추가하는 함수
    child = parent.children[idx]

    mid_key = child.keys[1]

    new_child = BTreeNode(leaf=child.leaf)

    # key 분할
    new_child.keys = child.keys[2:]
    child.keys = child.keys[:1]

    # 부모 노드의 idx 번째 자식 노드에 mid key를 삽입
    parent.keys.insert(idx, mid_key)

    # 부모의 자식 목록에 새 child를 삽입
    parent.children.insert(idx + 1, new_child)
```

이제 `insert()` 함수를 완성 합시다.

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if root.leaf and len(root.keys) < 3:
      if key in root.keys:
        print(f"Key {key} already exists in the tree.")
        return
      root.keys.append(key)
      root.keys.sort()
      return
    elif root.leaf and len(root.keys) == 3:
      # 신규 루트를 발급
      new_root = BTreeNode(leaf=False)

      # 지금의 루트를 신규 루트에 붙임
      new_root.children.append(root)

      self.root = new_root
      self._split_child(new_root, 0)

      # 이제 새로운 key를 insert
      ## 어느 child로 내려갈지 찾기
      idx = -1
      for i, n_key in enumerate(new_root.keys):
        if key == n_key:
          print(f"Key {key} already exists in the tree.")
          return

        if key < n_key:
          idx = i
          break

      if idx == -1:
        idx = len(new_root.keys)

      ## 적절한 child에 insert
      node = new_root.children[idx]
      node.keys.append(key)
      node.keys.sort()
      return
    else:
      pass
```

지금까지는 루트 노드가 최초로 채워질 때, 그리고 루트 노드가 최초로 꽉 차서 분할이 일어나는 상황이었습니다. 이제 코드를 일반화 해야 합니다.

<br/>

가장 먼저, 노드에 key를 추가하는 부분을 함수화 할 수 있습니다. 이를 `_insert_non_full()`로 공통화 합니다.

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if root.leaf and len(root.keys) < 3:
      self._insert_non_full(root, key)  # 여기
    elif root.leaf and len(root.keys) == 3:
      pass

  def _insert_non_full(self, node: BTreeNode, key: int):
    if len(node.keys) >= 3:
      raise ValueError("Node is full.")

    if node.leaf:
      if key in node.keys:
        print(f"Key {key} already exists in the tree.")
        return
      node.keys.append(key)
      node.keys.sort()
      return
    pass
```

가장 먼저, 루트 노드가 최초로 채워질 때의 코드를 옮겼습니다. 이제, 루트 노드 분할 후의 동작도 옮겨줍니다.

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if root.leaf and len(root.keys) < 3:
      self._insert_non_full(root, key)  # 여기
    elif root.leaf and len(root.keys) == 3:
      # 신규 루트를 발급
      new_root = BTreeNode(leaf=False)

      # 지금의 루트를 신규 루트에 붙임
      new_root.children.append(root)

      self.root = new_root
      self._split_child(new_root, 0)

      # 이제 새로운 key를 insert
      self._insert_non_full(new_root, key)  # 여기

  def _insert_non_full(self, node: BTreeNode, key: int):
    if len(node.keys) >= 3:
      raise ValueError("Node is full.")

    if node.leaf:
      if key in node.keys:
        print(f"Key {key} already exists in the tree.")
        return
      node.keys.append(key)
      node.keys.sort()
      return

    ## 어느 child로 내려갈지 찾기
    idx = -1
    for i, n_key in enumerate(node.keys):
      if key == n_key:
        print(f"Key {key} already exists in the tree.")
        return

      if key < n_key:
        idx = i
        break

    if idx == -1:
      idx = len(node.keys)

    ## 적절한 child에 insert
    node = node.children[idx]
    node.keys.append(key)
    node.keys.sort()
```

이렇게 했으면, 이제 `insert()` 코드를 루트를 넘어, non-root 노드에도 insert 하는 코드로 바꿀 수 있습니다.

```py
class BTree:
  ...
  def insert(self, key: int):
    root = self.root

    if len(root.keys) < 3:
      self._insert_non_full(root, key)
    elif len(root.keys) == 3:
      # 신규 루트를 발급
      new_root = BTreeNode(leaf=False)

      # 지금의 루트를 신규 루트에 붙임
      new_root.children.append(root)

      self.root = new_root
      self._split_child(new_root, 0)

      # 이제 새로운 key를 insert
      self._insert_non_full(new_root, key)
```

다만, `_insert_non_full()`에서 이 부분을 추가해야 합니다.

```py
class BTree:
  ...
  def _insert_non_full(self, node: BTreeNode, key: int):
    if len(node.keys) >= 3:
      raise ValueError("Node is full.")

    if node.leaf:
      if key in node.keys:
        print(f"Key {key} already exists in the tree.")
        return
      node.keys.append(key)
      node.keys.sort()
      return

    ## 어느 child로 내려갈지 찾기
    idx = -1
    for i, n_key in enumerate(node.keys):
      if key == n_key:
        print(f"Key {key} already exists in the tree.")
        return

      if key < n_key:
        idx = i
        break

    if idx == -1:
      idx = len(node.keys)

    # 여기
    if len(node.children[idx].keys) == 3:
      # 내려갈 child가 이미 꽉 차 있다면, 먼저 분할해야 함.
      self._split_child(node, idx)

      # 분할 후에 다시 어느 child로 내려갈지 찾아야 함.
      idx = ...

    # 재귀 호출로 변경
    self._insert_non_full(node.children[idx], key)
```

어느 child로 내려갈지 idx를 찾는 코드도 자주 사용하기 때문에 함수화 해서 편하게 씁시다.

```py
class BTree:
  ...
  def _find_child_index(self, node: BTreeNode, key: int) -> tuple[int, bool]:
    """
    internal node에서 어느 child로 내려갈지 결정.
    """

    for i, n_key in enumerate(node.keys):
      if key == n_key:
        return i, True

      if key < n_key:
        return i, False

    return len(node.keys), False

  def _insert_non_full(self, node: BTreeNode, key: int):
    if len(node.keys) >= 3:
      raise ValueError("Node is full.")

    if node.leaf:
      if key in node.keys:
        print(f"Key {key} already exists in the tree.")
        return
      node.keys.append(key)
      node.keys.sort()
      return

    # leaf가 아니라면, 어느 child로 내려갈지 찾아야 함.
    idx, key_exists = self._find_child_index(node, key)  # 여기

    if key_exists:
      print(f"Key {key} already exists in the tree.")
      return

    if len(node.children[idx].keys) == 3:
      # 내려갈 child가 이미 꽉 차 있다면, 먼저 분할해야 함.
      self._split_child(node, idx)

      # 분할 후에 다시 어느 child로 내려갈지 찾아야 함.
      idx, key_exists = self._find_child_index(node, key)  # 여기

      if key_exists:
        print(f"Key {key} already exists in the tree.")
        return

    # 재귀 호출로 변경
    self._insert_non_full(node.children[idx], key)
```

아직 멀었습니다... ㅋㅋ 노드를 분할하는 `_split_child()`도 internal node를 분할하는 경우, 자식 노드를 분할해줘야 해요.

```py
class BTree:
  ...
    def _split_child(self, parent: BTreeNode, idx: int):
    # parant의 idx 번째 자식 노드를 분할해 parent의 자식 노드로 추가하는 함수
    child = parent.children[idx]

    mid_key = child.keys[1]

    new_child = BTreeNode(leaf=child.leaf)

    # key 분할
    new_child.keys = child.keys[2:]
    child.keys = child.keys[:1]

    # 여기가 추가됨.
    # child가 내부 노드라면, children도 분할
    if not child.leaf:
      new_child.children = child.children[2:]
      child.children = child.children[:2]

    # 부모 노드의 idx 번째 자식 노드에 mid key를 삽입
    parent.keys.insert(idx, mid_key)

    # 부모의 자식 목록에 새 child를 삽입
    parent.children.insert(idx + 1, new_child)
```


### 4에 도달하면 노드를 분할하는게 아니라, 왜 3까지 차면 노드를 분할할까?

> 왜 keys가 3이 되면 선제적으로 split 하는가?

코드를 구현하면서 들었던 생각이예요.

```
    [10 | 20 | 30]
     /  |  |  \
    5  15   25  35
```

이 상황에서 40을 삽입하면 가능한 선택지는 1번/2번이예요.


1번: 노드를 분할하고 insert 한다.

```
         [20]
      /        \
    [10]       [30]
   /   \        /   \
   [5]  [15] [25] [35|40]
```

2번: 실제 삽입이 되는 대상 노드가 한계(=4)에 도달하면 split node

```
    [10 | 20 | 30]
     /  |  |  \
    5  15   25   [35|40]
```

1번과 2번 둘다 B-Tree 구조를 충족합니다.


1번 방식을 top-down이라고 합니다. 삽입 연산을 하기 위해 트리를 순회할 때마다,
`if node.key is full: split node`를 체크하고, 노드 분할을 수행합니다.

즉, full(=3) 상태의 노드 탐색 과정에서 모두 사전 분할(pre-split) 됩니다.

반면에, 2번은 내가 실제로 삽입하는 노드가 한계에 노달하면 split node를 수행합니다. 이 경우, split 하면서 child -> parent로 mid_key를 올려주게 되는데, 만약 parent 노드가 꽉찬 상태였다면, parent도 노드 분할을 해야 합니다.


```
        [40|80|120]
      /    |    |    \
 [10|20|30] [50|60|70] [90|100|110] [130]
```

이 상태에서 `5`를 insert 하게 되면,

```
        [40|80|120]
      /    |    |    \
 [5|10|20|30] [50|60|70] [90|100|110] [130]
 (overflow)
---
        [20 | 40 | 80 | 120]
         (overflow)
      /     |     |     |     \
   [5|10]   [30] [50|60|70] [90|100|110] [130]
  (20이 부모로 올라감.)
---
          [80]
        /         \
      [20|40]         [120]
     /   |   \          /  \
   [5|10]  [30] [50|60|70] [90|100|110] [130]
```

2번 방식은 split 후, parent로 올라간 mid_key로 인해 parent overflow가 발생하고, split 될 수 있습니다. 그리고 또, mid_key가 올라가면서 overflow -> split을 유발합니다. 즉, split 이라는 행위 자체가 연쇄적으로 bottom-up으로 전파 됩니다.

하지만, 1번은 split이 연쇄적으로 발생하지 않고 전파되지도 않습니다. 상위 노드를 순회할 때, 이미 full 상태인 노드를 정리하고 왔기 때문입니다. 그래서 중간/리프 노드에서 split이 발생하더라도, 그게 연쇄적으로 전파되지 않습니다.

## Search

탐색은 그대로 쉽습니다 ㅎㅎ

```py
class BTree:
  ...
  def search(self, key: int):
    return self._search_node(self.root, key)

  def _search_node(self, node: BTreeNode, key: int):
    # 어떤 child를 탐색할지 결정
    idx, key_exists = self._find_child_index(node, key)

    # 만약 key가 node에 있다면, True를 반환
    if key_exists:
      return True

    if node.leaf:
      return False

    return self._search_node(node.children[idx], key)
```



# B+Tree

자 이제, 이 글을 작성하게 만든 주인공 B+Tree 입니다...!

B-Tree와 B+Tree는 트리의 형상은 동일합니다. 둘다 `t` 계수를 가지고 있고, 노드의 키 갯수는 `2t-1`까지 가능합니다. 노드가 차면 노드를 분할해 제약 조건을 만족하게 합니다.

일단 스켈레톤 부터 마련 합시다.

```py
class BPlusTreeNode:
  def __init__(self, leaf: bool = True):
    self.leaf = leaf
    self.keys: list[int] = []

    # internal node에서만 사용
    self.children: list[BPlusTreeNode] = []

    # leaf node에서만 사용
    self.next: BPlusTreeNode | None = None

class BPlusTree:
  def __init__(self):
    self.t = 2
    self.root = BPlusTreeNode(leaf=True)

  def insert(self, key: int):
    pass

  def search(self, key: int):
    pass
```

B+Tree는 리프 노드를 Linked List로 연결합니다. 그래서 `self.next` 변수가 추가되었습니다.


# Insert

```py
class BPlusTree:
  ...
  def insert(self, key: int):
    root = self.root
    if len(root.keys) == 3:
      # root가 꽉 차 있다면, 새로운 root를 만들고 split
      new_root = BPlusTreeNode(leaf=False)

      # 지금의 루트를 신규 루트에 붙임
      new_root.children.append(root)

      self.root = new_root
      self._split_child(new_root, 0)

      # 이제 새로운 key를 insert
      self._insert_non_full(root, key)
    else:
      self._insert_non_full(root, key)

  def _insert_non_full(self, node: BPlusTreeNode, key: int):
    # B-Tree와 동일
```

B+Tree는 B-Tree 트리와 형상이 동일하기 때문에, 노드를 추가하는 과정이 큰 틀에서 동일합니다.

하지만, `_find_child_index()`와 `_split_child()` 단계에서 차이가 있습니다.

<br/>

```py
class BPlusTree:
  ...
  def _find_child_index(self, node: BPlusTreeNode, key: int) -> int:
    # B+tree에서는 key == n_key인 경우, 오른쪽 child로 내려감.
    # B+tree에서 internal node의 key는 길 안내용 key이기 때문임.

    # internal node 전용 함수
    if node.leaf:
      raise ValueError("Node is a leaf, cannot find child index.")

    for i, n_key in enumerate(node.keys):
      if key < n_key:
        return i

    return len(node.keys)
```

B-Tree에선 `key == n_key`로 일치하는 경우, 탐색을 중단하고 노드의 위치를 반환하거나 노드 삽입을 중단 했습니다.

하지만, B+Tree에선 internal node에서 `key == n_key`로 일치한다면, 그걸 기준으로 다음 순회 노드를 결정 합니다.

```
      [20    |   40]
    /        |       \
 [5|10] -> [20|30] -> [40|50]
```

그 이유는 B+Tree에선 오직 리프 노드를 기준으로 Point Query, Range Query의 결과를 반환하는 규칙이 있기 때문입니다.
그래야 `WHERE id BETWEEN 10 AND 20`과 같은 Range Query가 리프 노드 `10`에서 시작해 Linked List로 `20`까지 순회할 수 있기 때문입니다.

중간에 internal node에서 탐색을 멈춘다면, 이런 Range Query를 위해 별도 구현을 마련해야 할 것 입니다.


<br/>

```py
class BPlusTree:
  ...
  def _split_child(self, parent: BPlusTreeNode, idx: int):
    child = parent.children[idx]

    if child.leaf:
      self._split_child_leaf(parent, idx)
    else:
      self._split_child_internal(parent, idx)
```

B+Tree는 노드 분할 때, leaf인지 internal node인지 구분해서 수행합니다.

```py
  def _split_child_leaf(self, parent: BPlusTreeNode, idx: int):
    # B-tree에서는 leaf node가 꽉 차면, 가운데 key를 parent로 올리고, 오른쪽 절반을 새로운 leaf node로 만듦.
    # B+Tree에서는 leaf node가 꽉 차면, 오른쪽 절반을 새로운 leaf node로 만들고, parent에는 오른쪽 절반의 첫 번째 key를 올림.

    child = parent.children[idx]

    new_child = BPlusTreeNode(leaf=True)

    # [10|20|30] -> [10], [20|30]
    new_child.keys = child.keys[1:]  # 여기가 다름.
    child.keys = child.keys[:1]

    # [B+Tree] leaf linked list 연결
    new_child.next = child.next
    child.next = new_child

    # parent에 new_child 추가
    parent.children.insert(idx + 1, new_child)

    # 부모의 자식 목록에 새 child를 삽입
    parent.keys.insert(idx, new_child.keys[0])
```

B+Tree에선 리프 노드의 Linked List 구조가 분할 후에도 이어져야 합니다. 그래서 paraent의 key 목록에 mid_key를 추가만 할뿐, mid_key 자체는 여전히 리프 노드의 key 목록에 존재합니다.


```py
  def _split_child_internal(self, parent: BPlusTreeNode, idx: int):
    # 내부 노드는 부모로 key를 이동 시켜야 함(move-up).
    child = parent.children[idx]

    new_child = BPlusTreeNode(leaf=False)

    # [10|20|30] -> [10], 20은 부모로, [30]
    promote_key = child.keys[1] # move-up 대상
    new_child.keys = child.keys[2:] # move-up으로 인해 삭제
    child.keys = child.keys[:1]

    # child 분할
    new_child.children = child.children[2:]
    child.children = child.children[:2]

    # parent에 new_child 추가
    parent.children.insert(idx + 1, new_child)

    # 부모의 자식 목록에 새 child를 삽입
    parent.keys.insert(idx, promote_key)
```

하지만, 내부 노드는 B-Tree와 동일하게 노드 분할이 이뤄집니다. parent에 mid_key를 이동시킵니다(move-up).

# Search

탐색은 좀더 쉽게 구현 합니다.

```py
class BPlusTree:
  ..
  def search(self, key: int):
    # internal node에 있는 key는 길 안내용 key임.
    # 중간에 key와 일치하는 값이 internal node에 있더라도, 항상 leaf node 끝까지 내려가야 함.

    leaf = self._find_leaf(self.root, key)
    return key in leaf.keys

  def _find_leaf(self, node: BPlusTreeNode, key: int):
    """
    key가 들어있을 가능성이 있는 leaf node까지 내려가는 함수
    B+Tree는 검색이 항상 leaf에서 끝남.
    """

    if node.leaf:
      return node
    else:
      idx = self._find_child_index(node, key)
      return self._find_leaf(node.children[idx], key)
```

중간 노드에 있는 keys들을 참고해 리프 노드까지 도달합니다. 리프 노드에 도달 했을 떄, 찾고자 하는 key가 리프 노드에 존재하는지를 확인 합니다.



# 맺음말

직접 구현해보니 지금까지 애매하고 이해하고 받아들였던 것들이 구체화 되었다. BST와 B-Tree의 차이점, 그리고 B-Tree와 B+Tree의 차이점. 이런 것들은 이전에는 다 비슷한 거라고 생각하고 넘어갔었다.

하지만, 이제 시작이다!! 본래 `xfs`, `ext4` 파일 시스템을 이해하는 과정에서 이걸 만났기 때문에, 들인 시간이 온전히 가치를 내려면 `xfs`, `ext4`를 이해하는데 이걸 써먹어야 한다.

자료구조 자체를 구현하는 능력은 지금 시대에 중요하지 않다. 중요한 건 도구를 언제/왜/어떻게 써야 하는지를 아는 것이다.
