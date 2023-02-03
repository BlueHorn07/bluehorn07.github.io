---
title: "Persistent Volume & Persistent Volume Claim"
layout: post
tags: ["develop", "kubernetes"]
---

Pod 컨테이너의 볼륨을 직접 정의할 수도 있다. 그러나 클러스터로 관리되는 K8s에선 이 방식을 권장하지 않는다. 대신, 컨테이너 볼륨 개념을 추상화하여 **클러스터 리소스**인 Persistent Volume을 통해 Pod 컨테이너가 쓸 수 있는 볼륨을 관리한다.

# 컨테이너 볼륨은 노드 디스크를 사용한다

컨테이너 볼륨 중 `emptyDir` 유형과 `hostPath` 유형은 Pod이 실행되는 노드의 디스크를 사용한다. 그러나 K8s는 여러 노드가 모인 클러스터로 관리되기 때문에 Pod 역시 하나의 노드에 묶인 존재가 아니라 유동적이다. 그러나 데이터가 노드의 디스크에 존재한다면, 노드 장애 등의 상황에서 데이터가 유실 될 수 있다.

# Persistent Volume이란

컨테이너 볼륨은 Pod과 동일한 생명 주기를 갖는다. 그러나 Persistent PV, 이하 PV는 Pod과 별개의 생명 주기를 갖는다. 따라서, Pod이 종료되어도 PV 리소스는 남아있다.

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: my-pv
spec:
  capacity:
    storage: 5Gi
  volumeMode: FileSystem
  accessMode:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Recycle
  storageClassName: manual
  ...
```

## PV 세부 옵션

### volumeMode

`FileSystem` 값은 볼륨을 파일 시스템으로 사용한다. 다른 옵션으로 `Block`이 있는데, 지금은 알 필요가 없는 듯 하다. volumeMode 옵션이 지정되지 않으면 `FileSystem`이 기본값이다.

### accessMode

스토리지 볼륨에 접근하는 방법이다.

- `ReadWriteOnce`
  - 하나의 노드에서만 해당 볼륨이 RW로 마운트 됨.
  - 단, 동일 노드에서 여러 pod이 구동되는 경우는 복수의 Pod이 해당 노드의 볼륨에 접근 가능
- `ReadOnlyMany`
  - 볼륨이 다수의 노드에서 읽기 전용으로 마운트.
- `ReadWriteMany`
  - 볼륨이 다수의 노드에서 RW로 마운트.
- `ReadWriteOncePod`
  - `ReadWriteOnce`에서 좀더 강화된 옵션. 전체 클러스터에서 오직 단 하나의 Pod만 해당 볼륨에 접근할 수 있다.

스토리지 리소스 Provider에 따라 가능한 옵션이 다르니 주의할 것. 예를 들어 AWS EBS 볼륨은 `ReadWriteOnce`만 가능하다.

### persistentVolumeReclaimPolicy

볼륨 사용 이후에 해당 스토리지에 대한 처리 정책이다.

- `Retain`
  - 볼륨 사용이 끝나도 데이터가 유지되므로 수동 반환 해야 한다.
- `Recycle`
  - 볼륨 사용이 끝나면, 데이터가 삭제된다.
- `Delete`
  - 볼륨 사용이 끝나면, 관련 스토리지 자산이 삭제된다. (AWS EBS 볼륨이 삭제됨)

### storageClassName

K8s의 Storage Class 리소스 이름을 적는 곳이다.
Storage Class 리소스를 사용할 것이라면, 해당 SC의 이름을 적으면 되고, 그렇지 않다면 `manual` 값으로 설정한다.
지정하지 않으면 K8s 클러스터의 Default 설정으로 되어 있는 SC를 사용한다.

이후에 Storage Class 리소스를 다룰 때 다시 돌아오자.

<hr/>

# Persistent Volume Claim

PV로 정의된 스토리지 리소스를 Pod이 사용하도록 요청하는 리소스이다.
PVC에 Pod이 사용할 스토리지의 조건을 작성하고, PVC의 이름을 Pod의 yaml 파일에 적음으로서 스토리지를 할당한다.

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: my-pvc
spec:
  resources:
    requests:
      storage: 8Gi
```

PVC는 스토리지 요구사항일 뿐이다. Pod 입장에서 필요로 하는 스펙을 적어주면 된다.

K8s 컨트롤플레인은 PVC에 적힌 요구사항을 만족하는 PV를 찾아서 바인딩 해준다. 만족하는 PV가 없다면, `NOT BOUND` 상태로 남게 된다.

<br/>

PVC에 적힌 요구사항을 좀더 자세히 적을 수도 있다. PV를 정의할 때 썼던 옵션들이 많이 등장한 것이 보인다.

```yaml
...
spec:
  resources:
    requests:
      storage: 8Gi
  accessMode:
    - ReadWriteOnce
  storageClassName: manual
```

<hr/>

# Pod에 PVC 연결




Q. 하나의 PV는 하나의 PVC만 연결할 수 있는가?



<hr/>

# Persistent Volume 워크 플로우

1. PV를 생성하고, 상태 체크.
2. PVC를 생성하고, 상태 체크.
3. Pod의 볼륨으로 PVC를 연결한다.

<hr/>

# References

- [[kubernetes] 퍼시스턴트 볼륨](https://kubernetes.io/ko/docs/concepts/storage/persistent-volumes/)
- [[kubernetes] 스토리지로 퍼시스턴트볼륨(PersistentVolume)을 사용하도록 파드 설정하기](https://kubernetes.io/ko/docs/tasks/configure-pod-container/configure-persistent-volume-storage/)