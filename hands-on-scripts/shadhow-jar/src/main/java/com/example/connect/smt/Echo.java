package com.example.connect.smt;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Echo<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger log = LoggerFactory.getLogger(Echo.class);

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("Echo SMT configured");
    }

    @Override
    public R apply (R record) {
        log.info("Echo topic={} partition={} offset={} ts={} key={} value={} headers={}",
            record.topic(),
            record.kafkaPartition(),
            null,
            record.timestamp(),
            String.valueOf(record.key()),
            String.valueOf(record.value()),
            String.valueOf(record.headers())
        );
        return record;
    }

    @Override public ConfigDef config() { return new ConfigDef(); }
    @Override public void close() {}
}
