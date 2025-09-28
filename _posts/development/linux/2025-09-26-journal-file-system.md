---
title: "Journal File System"
toc: true
toc_sticky: true
categories: ["Linux"]
excerpt: ""
---

회사에서 Kafka 용량 증설 할 때, `xfs` 파일 시스템을 쓰는데, 요 녀석의 특징 중 하나로 저널링이 나오더라구요!! 그래서 이번에 한번 뭔지 알아보려고 합니다


데이터 무결성을 보장하기 위해 변경 내용을 "저널"이라는 로그 파일에 먼저 기록하는 파일 시스템 방식임.

파일 쓰기나 수정 도중에 전원 꺼짐이나 커널 패닉 등으로 인해 디스크의 메타데이터 손상이 발생할 수 있습니다. 만약 정말로 디스크의 메타데이터 손상이 발생하게 된다면, 파일 전체가 꼬이게 되면서 복구가 안 되거나, 복구 프로그램을 직접 돌려야 합니다.

이런 위험을 완화 하기 위해 파일 쓰기/수정에 트랜잭션과 같은 개념을 도입한 것이 저널링 파일 시스템 입니다.

# 과정

1. 쓰기 요청이 발생
   - 파일이나 디렉토리의 내용, 메타데이터(inode, 블록 할당 테이블)을 바꿔야 하는 상황
2. 실제 변경을 하기 전에 저널 영역에 작성
   - 이 블록을 A -> B 상태로 바꾸거나
   - 파일을 A 위치에서 B 위치로 옮기거나
3. 커밋
   - 저널 영역에 기록이 성공적으로 저장된 후에야
   - 실제 파일 시스템이 데이터 블록과 메타 데이터 수정을 시작합니다.
4. 종료와 정리
   - 변경이 완료되면, 저널에서 해당 기록을 지워줍니다.

# 장점

빠른 복구가 가능 합니다! 쓰기/수정 작업 도중 시스템이 죽더라도, 재부팅 때 저널 영역만 확인해, "완료" 처리 되지 않은 작업만 롤백 또는 재적용 하면 됩니다.

# 단점

성능 오버헤드를 겪어야 합니다. 데이터를 저널에 한번, 실제 영역에 2번씩 쓰기 때문에 디스크에 쓰기 부하가 걸립니다.

SSD 환경에서는 버틸만한 성능 저하였고, HDD에선 크게 체감 되는 정도라고 합니다.

메타데이터만 저널링 한다면, 여전히 데이터 유실 가능성이 있습니다. 별도로 설정하지 않으면 기본 세팅으로 메타데이터만 저널링 합니다. 이 경우, 시스템 장애시 파일 내용 중 일부가 손상되는 경우가 있습니다. 다만, 파일 시스템 자체에 대한 손상으로 이어지지는 않습니다.

# 대표 예시들

Linux 계열: `ext3`, `ext4`, `xfs`

확인 방법은

```bash
$ mount | grep "^/dev"
/dev/nvme0n1p1 /etc/hosts xfs ...
/dev/nvme0n1p1 /dev/etermination-log xfs ...
...
```

또는 `lsblk`(list block devices) 명령어 사용

```bash
$ lsblk -f
NAME          FSTYPE FSVER LABEL UUID                                 FSAVAIL FSUSE% MOUNTPOINTS
nvme0n1
├─nvme0n1p1   xfs          /     xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx   31.3G    37% /
└─nvme0n1p128 vfat   FAT16       xxxx-xxxx                               8.6M    14% /boot/efi
```

위의 결과에서 `efi`는 시스템 파티션으로 부트 로더가 있는 곳임. `vfat` 파일 시스템은 저널링 지원하지 않음. 다만 부팅용 파티션이기에 파일 변경이 거의 없고, 따라서 손상 위험도 아주 낮음.

`xfs`에서는 저널을 "log"라고 부르며, 파일시스템 생성 시 크기가 정해짐.

```bash
$ sudo xfs_info /
meta-data=/dev/nvme0n1p1         isize=512    agcount=13, agsize=1047168 blks
         =                       sectsz=4096  attr=2, projid32bit=1
         =                       crc=1        finobt=1, sparse=1, rmapbt=0
         =                       reflink=1    bigtime=1 inobtcount=1
data     =                       bsize=4096   blocks=13104379, imaxpct=25
         =                       sunit=128    swidth=128 blks
naming   =version 2              bsize=16384  ascii-ci=0, ftype=1
log      =internal log           bsize=4096   blocks=16384, version=2
         =                       sectsz=4096  sunit=4 blks, lazy-count=1
realtime =none                   extsz=4096   blocks=0, rtextents=0
```

로그 부분은 `bsize=4096`(블록사이즈 4kb) 그리고 `blocks=16384`(로그 블록 갯수). 따라서 로그 저널 전체 크기는 `4096 * 16384 = 67mb`

이 공간을 고정 크기로 사용해 메타데이터의 변경을 트랙잭션 단위로 기록하는데 사용.

Windows: `NTFS`

macOS: `hfs`, `apfs`

지금 쓰는 맥북 M2 Air에서는 `apfs` 시스템을 사용 중 입니다. `Disk Utility`에서 확인할 수 있습니다.


# 저널 영역은 구체적으로 뭔가?

> 내가 저널 용량을 확인할 수 있을까?

파일 시스템에 따라서 저널 영역의 크기가 고정인 경우가 있고, 동적인 경우가 있습니다.

애플의 `apfs`의 경우, 동적 영역 입니다. 대신 Copy-on-Write 방식으로 파일을 생성/수정 합니다. 즉, 수정할 블록에 직접 접근하는 것이 아니라 새로운 블록에 기록 한 후, 메타 데이터 업데이트가 이뤄집니다.


# 정말 저널링은 장애 안전한가?

> 저널 영역이 손상되는 경우는 없을까?

저널링 기법을 쓴다고 해서 장애 상황에 절대적으로 안전한 것은 아닙니다.

저널 영역도 결국 디스크 위의 데이터 이기 때문에 손상될 수 있습니다. 디스크 장애가 발생 했지만 저널 영역이 안전하다는 가정 아래에 저널 재생이 이뤄집니다.

그리고 메타 데이터 저널링만 켠다면, 쓰기를 요청한 것 중 일부 데이터만 복구될 수 있습니다.
더 강력한 보호를 위해서는 데이터 저널링 모드까지 활성화 하여 사용해야 합니다.

<br/>

또는 RAID 방식으로 운영하는 것도 방법 입니다. 데이터를 이중으로 저장함으로써 하나의 디스크 장애가 발생해도 다른 디스크에서 복구할 수 있도록 하는 것이 안전합니다.

또, 정기적인 백업도 같이 수행해 보완해야 합니다.


# 디스크 볼륨 확장이 가능해요

저는 이 개념을 Kafka 브로커의 용량 증설 때, 브로커의 디스크를 `xfs` 방식으로 마운트 해서 디스크 용량 증설이 가능하다고 들었습니다. 그 이유가 뭘까요? 왜 `xfs`는 디스크 증설이 쉬운 걸까요?

찾아보니까 그 이유는 `xfs`의 파일 시스템 구현 때문이었스빈다. `xfs`는 Extent 기반 할당에 `B+ Tree` 인덱스를 사용하기 때문에 디스크 확장이 좀더 빠르다고 합니다.

디스크 확장은 `ext4` 포맷도 지원 합니다! 그러나 `ext4`는 블록 그룹 기반으로 디스크를 관리하기 때문에 ㅎ디스크 확장 때 블록 그룹과 메타데이터 갱신이 더 오래 걸린다고 합니다.

그래서 대규모 웨어하우스, 로그 저장소에는 디스크 확장을 고려해 `xfs` 포맷이 선호되는 것 입니다.

## Kubernetes PV도 증설이 가능해요

만약 K8s에서 사용하는 `StorageClass`가 `AllowVolumeExpansion: True` 값을 가지고 있었다면, 용량 증설이 가능합니다!

K8s PVC 리소스를 생성한 후에 `k edit pvc ...`로 용량을 증설 하면 됩니다!

이때, `ext4`, `xfs` 파일시스템이라면, `kubelet`이 자동으로 Pod에 마운트된 PV 볼륨을 리사이즈 해준다고 합니다!

참고로 EKS에서 사용할 수 있는 `ebs.csi.aws.com` driver는 `StorageClass`를 정의할 때, 아래와 같이 파일 시스템의 종류를 지정할 수 있습니다.

```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
...
provisioner: ebs.csi.aws.com
allowVolumeExpansion: true
volumeBindingMode: WaitForFirstConsumer
parameters:
  type: gp3
  csi.storage.k8s.io/fstype: xfs # 여기!
  encrypted: "{false/true}"
  iops: "1234"
  throughput: "123"
```
