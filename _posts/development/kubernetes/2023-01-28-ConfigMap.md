---
title: "ConfigMap"
layout: post
tags: ["develop", "kubernetes"]
---

기밀이 아닌 데이터를 키-값의 쌍으로 저장하는데 쓰는 오브젝트. ConfigMap에 저장된 값은 Pod이 사용한다.

Pod은 (1) 환경 변수로 받거나 (2) 볼륨 마운트를 통해 ConfigMap 리소스를 사용할 수 있다.

# ConfigMap 생성

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: my-config
data:
  db_host: "xxxxx.amazon.com"
  db_username: "xxxx"
  db_password: "xxxx"
```

`data` 값을 CLI 인자로 받는 방법들도 있지만, 본인은 직접 yaml 파일에 명시하는 걸 선호한다.

# Pod의 ConfigMap 사용

## 환경 변수로 받기

Pod은 `spec.containers[*].env[*]` 항목에서 환경 변수를 설정하는데, 이때, `valueFrom.configMapKeyRef`에 ConfigMap의 이름을 적어주면 된다.

```yaml
...
spec:
  containers:
    - name: ...
      image: ...
      env:
        - name: DB_HOST
          valueFrom:
            configMapKeyRef:
              name: my-config # ConfigMap name
              key: db_host
```

## 전체 값을 환경 변수로 받기

`spec.containers[*].envFrom[*]` 항목에 `configMapRef`를 설정해 ConfigMap의 값 전체를 받아올 수 있다. 하나씩 나열하기 귀찮거나, 하나씩 나열하다가 실수 할 것 같으면(휴먼 에러) `envFrom[*].configMapRef`를 쓰자.

```yaml
...
spec:
  containers:
    - name: ...
      image: ...
      envFrom:
        - configMapRef: # load all config values
            name: my-config
```


## 볼륨 마운트를 통해 받기

ConfigMap에 마운트할 파일의 내용을 적는다.

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: my-config
data:
  db_host: "xxxxx.amazon.com"
  db_username: "xxxx"
  db_password: "xxxx"
  nginx.conf: | # indentation 주의!
    user  nginx;
    worker_processes  2;

    err_log    /var/log/nginx/err.log warn;
    pid         /var/run/nginx.pid;

    http {
        include         /etc/nginx/mime.types;
        default_type  application/octet-stream;
        include        /etc/nginx/conf.d/*.conf;
    }
```

단, 파일 값을 `data`로 넘겨줄 때, indentation이 안 맞으면 파싱할 때 오류가 발생하니 주의해야 한다!!


```yaml
...
spec:
  containers:
    - name: ...
      image: python-alpine
      volumeMounts: # declare volume for config file
        - name: config-volume
          mountPath: /etc/config
          readOnly: true # (optional)
  volumes:
    - name: config-volume
      configMap:
        name: my-config # ConfigMap name
        items:
          - key: "nginx.conf"
            path: "nginx.conf"
```

## 볼륨 마운트를 통해 전체 받기

```yaml
...
spec:
  containers:
    - name: ...
      image: python-alpine
      volumeMounts: # declare volume for config file
        - name: config-volume
          mountPath: /etc/config
          readOnly: true # (optional)
  volumes:
    - name: config-volume
      configMap:
        name: my-config # ConfigMap name
```

ConfigMap에 정의된 키가 파일 이름이 되어 모든 키-값이 파일로 생성된다.

<hr/>

# 마운트된 ConfigMap의 업데이트

Pod에서 ConfigMap을 볼륨 마운트로 사용할 경우, ConfigMap의 값이 업데이트 되면 프로젝션된 키도 마찬가지로 업데이트 된다. 단, 값이 전파되는 데 지연 시간이 있고, 캐싱이 있기 때문에 바로 업데이트 되지 않을 수 있다.

ConfigMap의 값이 자동으로 업데이트 되는 건 Pod에서 볼륨 마운트로 연결했을 경우 뿐이다. 환경 변수로 전달한 ConfigMap의 값은 자동으로 업데이트 되지 않기 때문에, Pod을 재시작 해야 한다.

<hr/>

# References

- [[kubernetes] ConfigMap](https://kubernetes.io/ko/docs/concepts/configuration/configmap/)
- [[kubernetes] Configure a Pod to Use a ConfigMap](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/)