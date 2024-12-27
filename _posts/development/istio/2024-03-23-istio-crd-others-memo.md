---
title: "Istio CRD 중에 지엽적인 나머지 것들 정리"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "아니 Istio에 이런 리소스도 있었어 싶은 것들을 아주 간단히 정리해봤다: "
last_modified_at: 2024-03-24
---

```bash
$ kubectl get crd | grep istio
authorizationpolicies.security.istio.io    2024-03-22T07:07:06Z
destinationrules.networking.istio.io       2024-03-22T07:07:07Z
envoyfilters.networking.istio.io           2024-03-22T07:07:07Z
gateways.networking.istio.io               2024-03-22T07:07:07Z
istiooperators.install.istio.io            2024-03-22T07:07:07Z
peerauthentications.security.istio.io      2024-03-22T07:07:07Z
proxyconfigs.networking.istio.io           2024-03-22T07:07:07Z
requestauthentications.security.istio.io   2024-03-22T07:07:07Z
serviceentries.networking.istio.io         2024-03-22T07:07:07Z
sidecars.networking.istio.io               2024-03-22T07:07:07Z
telemetries.telemetry.istio.io             2024-03-22T07:07:07Z
virtualservices.networking.istio.io        2024-03-22T07:07:07Z
wasmplugins.extensions.istio.io            2024-03-22T07:07:07Z
workloadentries.networking.istio.io        2024-03-22T07:07:07Z
workloadgroups.networking.istio.io         2024-03-22T07:07:07Z
```

ICA 시험 준비할 때, 위의 명령어를 쳐서 나온 Istio 리소스들을 모두 공부하겠다는 다짐을 했다 ㅋㅋㅋ `VirtualService` `DestinationRule`, `Gateway`은 Istio를 처음 공부할 때부터 봤던 것들이고, `IstioOperator`는 istio 설치랑 Mesh Config 살펴보면서 깊게 공부 했고, `ServiceEntry`는 Egress Gateway 때 공부했고,  `AuthorizationPolicy`, `PeerAuthentication`, `RequestAuthentication`, `Sidecar`는 Istio의 Security 관련해서 공부할 때 살펴봤다.

이제 나머지 남은 것들은 진짜진짜 접할 기회도 거의 없었고, Istio 공부하면서 본 테크 영상이나 블로그에서 거의 등장 안 하던 것들이다. 그런데 시험에는 뭐가 나올지 모르니... 일단 공부는 다 해봤다... (장하다 나 자신...)

이미 살펴본 것들은
- `VirtualService`, `DestinnationRule`, `Gateway`
- `IstioOperator`
- `ServiceEntry`
- `AuthorizationPolicy`, `PeerAuthentication`, `RequestAuthentication`, `Sidecar`

진짜 쩌리들로 ICA 시험에 안 나올 것 같은 것들...

- `WorkloadGroup`, `WorkloadEntry`
- `Telemtry`
- `WasmPlugin`
- `EnvoyFilter`
- `ProxyConfig`

그래도 한번씩 문서는 다 살펴봤으니!! 공부한게 아까워서라도 여기에 정리해두겠다!! ❤️‍🔥

# WorkloadGroup & WorkloadEntry`

요건 Istio의 [Virtual Machine Architecture](/2024/03/23/istio-virtual-machine-architecture/) 살펴보면서 공부하고 정리 해뒀다 ㅎㅎ

# Telemtry

https://istio.io/latest/docs/tasks/observability/telemetry/

Istio의 Metrics, Access Logs, Tracing 기능을 세팅하고 커스텀하는데 사용하는 컴포넌트다. Istio에선 위의 기능을 담당하는 여러 Provider를 정의하고, 그것을 Telemetry API에서 세부 설정을 커스텀 한다.

Istio에서 편의를 위해 기본으로 구성해둔 Provider도 있는데

- `prometheus`
  - Metrics
- `evoy`
  - Access Logging
  - 특정 워크로드나 네임스페이스에 Envoy Access Logging를 활성화 하고 싶을 때 `Telemtry` 리소스를 사용했다. 자세한 내용은 정리해둔 [Istio Envoy Access Logging 포스트](/2024/03/16/istio-envoy-access-logging/) 참조.
- `stackdriver`
  - Metrics, Tracing, Access Logging
  - default provider 지만, `default` profile로 설치하면 `enabled: false`로 세팅 되어 있음.

default provider 외에 다른 Provider, 예를 들어 zipkin, datadog 등을 추가하고 싶다면 `IstioOperator`의 `meshConfig.extensionProviders` 항목에 추가하면 된다. 참고로 [Jaeger addon을 핸즈온 할 때](/2024/03/18/istio-distributed-tracing-jaeger/)는 `meshConfig.defaultConfig.tracing.zipkin.address` 필드를 수정해 envoy가 trace 데이터를 보낼 수 있도록 세팅 했었다.

Telemtry API 문서에는 `extensionProviders`를 이렇게 세팅하라던데

```yaml
...
extensionProviders:
  - name: "localtrace" # istio 메쉬 내부에서 tracing 운영
    zipkin:
      service: "zipkin.istio-system.svc.cluster.local"
      port: 9411
      maxTagLength: 56
  - name: "cloudtrace" # 클라우드 서비스에서 tracing 운영
    stackdriver:
      maxTagLength: 256
...
```

뭔가 둘다 가능한 것 같기도?

# WasmPlugin

https://istio.io/latest/docs/reference/config/proxy_extensions/wasm-plugin/

WebAssembly 플러그인?이라는데, WebAssembly는 아직 뭔지도 잘 모르고 써본 적도 없어서 WASM을 공부했음 ㅋㅋ

[니코쌤의 WASM 설명](https://youtu.be/KjgDxBLv0bM?si=9In7rTUrV6FAE3TQ)

# EnvoyFilter

https://istio.io/latest/docs/reference/config/networking/envoy-filter/

Istio Pilot이 만든 Envoy Configuration을 커스텀할 수 있는 리소스. 단, 잘못 건드리면 재앙이 벌어질 수도 있으니 주의할 것!

![](https://miro.medium.com/v2/resize:fit:2000/format:webp/0*RM-Rif51UiVlZmYC)

이때, "filter"는 Envoy에서 유래만 용어로 Envoy가 트래픽을 처리하는 프로세스 중 하나를 일컫는다. Envoy 안에 filter는 여러개가 존재할 수 있으며 이들은 순서대로 처리된다. 그래서 filter chain라고 부른다.

# ProxyConfig

https://istio.io/latest/docs/reference/config/networking/proxy-config/

요건 Envoy Proxy 관련해 몇가지 커스텀을 할 수 있는데, 커스텀 하는 것들이 단순해서 이해는 쉽다 ㅋㅋ

`concurrency`를 조정해 Envoy Proxy가 몇개의 thread를 사용할 수 있을지 커스텀. 기본값은 `2`, 2개 쓰레드다.

`environmentVariables`는 Envoy Porxy에 추가 Env Variable를 정의할 수 있는 필드.

`image`는 Envoy Proxy의 이미지 타입을 명시하는 부분임. `default` 값에서 `distroless`, `debug` 등으로 바꿀 수 있다. 참고로 `distroless`는 리눅스 배포판(distribution, distro)에 포함되는 소프트웨어가 포함되지 않은 이미지임. 그래서 완전 초-경량임!!

