FROM quay.io/strimzi/kafka:0.48.0-kafka-4.1.0

USER root

RUN curl -L -o /tmp/debezium-connector-mysql.zip \
  https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/3.1.3.Final/debezium-connector-mysql-3.1.3.Final-plugin.zip
RUN unzip /tmp/debezium-connector-mysql -d /opt/kafka/plugins/debzium-mysql


USER 1001
