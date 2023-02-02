---
title: "Pod and Volume"
layout: post
tags: ["develop", "kubernetes"]
---

컨테이너 내의 파일은 모두 일시적인 존재다. 컨테이너가 종료되면, 컨테이너에 기록된 데이터 모두 사라진다. 이것을 방지하기 위해 Docker와 K8s는 Volume 기능을 제공한다.

이글은 Docker 컨테이너의 Volume 개념은 이미 아는 것을 전제로 한다. K8s의 Volume은 Docker 컨테이너 Volume보다 더 다양하고, 유연하다.

# Volume의 기본 문법

`spec.volumes[*]`에 볼륨을 정의한다. 그리고 `spec.containers[*]`에 정의된 컨테이너에 `volumeMounts[*]`로 볼륨을 연결해준다.

```yaml
apiVersion: v1
kind: Pod
spec:
  volumes:
    - name: name-of-volume
      ...
  containers:
    - image: ...
      name: ...
      volumeMounts:
        - name: name-of-volume
          mountPath: /container-dir
```

<hr/>

# Volume 유형들

## emptyDir 유형

비어있는(empty) 볼륨을 제공하는 유형이다. Pod이 제거되면, `emptyDir` 볼륨에 **담겨있던 데이터는 영구적으로 삭제**된다. 단, 컨테이너 크래시 같이 컨테이너 오류의 경우에는 `emptyDir`의 데이터가 삭제되지 않는다. Pod이 완전히 내려간 경우에만 데이터가 삭제되는 것이다. 보통 캐싱(caching) 등의 임시로 사용하는 데이터를 담는데 적합하다.


```yaml
...
spec:
  volumes:
    - name: cache-volume
      emptyDir: {}
  containers:
    ...
```

## hostPath 유형

호스트 노드의 디스크에 볼륨을 생성해 Pod이 제거되더라도 볼륨의 데이터는 유지하는 유형이다. 단, 호스트의 디스크를 Pod 컨테이너에 노출하는 것이기 때문에 왠만하면 사용하지 않는 것이 좋으며, 사용에 매우매우 유의해야 한다.

```yaml
...
spec:
  volumes:
    - name: node-volume
      hostPath:
        # 호스트의 디렉토리 위치
        path: /data
        type: Directory # 주어진 경로에 디렉터리가 있어야 함
        type: DirectoryOrCreate # 경로에 디렉터리가 없다면, kubelet의 권한과 같은 권한을 가진 디렉토리를 생성
        type: ...
  containers:
    ...
```

`hostPath.type`으로 타입을 지정할 수 있으며, 이에 따라 볼륨 접근과 생성에 대해 제어할 수 있다. 그외 타입들은 k8s 문서를 참조할 것.

## ConfigMap & Secret 유형

K8s의 ConfigMap 리소스를 볼륨으로 사용할 수도 있다. 이 경우, ConfigMap에 정의된 key를 파일 이름으로 파일이 생성되고, value가 파일 안에 작성된다.

```yaml
...
spec:
  volumes:
    - name: config-volume
      configMap:
        name: my-config
  containers:
    ...
```

마찬가지로 Secret 리소스도 컨테이너 볼륨으로 사용할 수 있다.

## PersistentVolumeClaim 유형

뒤에서도 살펴볼 K8s 리소스로 Persistent Volume을 사용할 수 있도록 바인딩 하는 리소스이다. 지금은 어떻게 Pod의 Volume으로 쓸 수 있는지만 확인하자.

```yaml
...
spec:
  volumes:
    - name: pv-storage
      persistentVolumeClaim:
        claimName: my-pvc
  containers:
    ...
```

<hr/>

# References

- [[kubernetes] 볼륨](https://kubernetes.io/ko/docs/concepts/storage/volumes/)
