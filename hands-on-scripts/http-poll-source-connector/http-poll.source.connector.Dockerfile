FROM quay.io/strimzi/kafka:0.48.0-kafka-4.1.0

USER root

# jq 버전
ARG JQ_VERSION=1.8.1

# 아키텍처에 따라 바이너리 선택
RUN ARCH=$(uname -m) && \
    if [ "$ARCH" = "x86_64" ]; then \
        JQ_URL="https://github.com/jqlang/jq/releases/download/jq-${JQ_VERSION}/jq-linux-amd64"; \
    elif [ "$ARCH" = "aarch64" ]; then \
        JQ_URL="https://github.com/jqlang/jq/releases/download/jq-${JQ_VERSION}/jq-linux-arm64"; \
    else \
        echo "Unsupported architecture: $ARCH" && exit 1; \
    fi && \
    curl -L $JQ_URL -o /usr/local/bin/jq && \
    chmod +x /usr/local/bin/jq && \
    jq --version

RUN curl -L -o /tmp/debezium-connector-mysql.zip \
  https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/3.1.3.Final/debezium-connector-mysql-3.1.3.Final-plugin.zip
RUN unzip /tmp/debezium-connector-mysql -d /opt/kafka/plugins/debzium-mysql

# plugin 위치에 복사
COPY ./build/libs/ /opt/kafka/plugins/http-poll-source-connector

USER 1001
