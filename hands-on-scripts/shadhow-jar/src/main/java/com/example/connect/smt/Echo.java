package com.example.connect.smt;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Field;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;

public class Echo<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger log = LoggerFactory.getLogger(Echo.class);

    private static final String SAMPLE_RATE_CONFIG = "sample.rate";
    private static final double DEFAULT_SAMPLE_RATE = 0.01;

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
        .define(
            SAMPLE_RATE_CONFIG,
            ConfigDef.Type.DOUBLE,
            DEFAULT_SAMPLE_RATE,
            ConfigDef.Range.between(0.0, 1.0),
            ConfigDef.Importance.LOW,
            "Sample rate for the echo transform"
        );

    private double sampleRate;
    private final Random random = new Random();


    @Override
    public void configure(Map<String, ?> configs) {
        AbstractConfig config = new AbstractConfig(CONFIG_DEF, configs);
        this.sampleRate = config.getDouble(SAMPLE_RATE_CONFIG);

        log.info("Echo SMT configured with sample rate: {}", this.sampleRate);

    }

    @Override
    public R apply (R record) {
        if (record.value() == null) return record;

        Struct envelope = (Struct) record.value();
        Schema schema = envelope.schema();
        Map<String, Object> map = structToMap(envelope);
        String json = new Gson().toJson(map);

        if (this.random.nextDouble() < this.sampleRate) {
            log.info("Echoing record: {}", json);
        }

        return record.newRecord(
            record.topic(),
            record.kafkaPartition(),
            record.keySchema(),
            record.key(),
            record.valueSchema(),
            json,
            record.timestamp()
        );
    }

    @Override public ConfigDef config() { return CONFIG_DEF; }
    @Override public void close() {}

    private static Map<String, Object> structToMap(Struct struct) {
        Map<String, Object> map = new HashMap<>();

        if (struct == null || struct.schema() == null) return map;

        for (Field field: struct.schema().fields()) {
            Object fieldValue = struct.get(field.name());
            if (fieldValue instanceof Struct) {
                map.put(field.name(), structToMap((Struct) fieldValue));
            } else if (fieldValue instanceof List<?>) {
                map.put(field.name(), convertList((List<?>) fieldValue));
            } else if (fieldValue instanceof Map<?, ?>) {
                map.put(field.name(), convertMap((Map<?, ?>) fieldValue));
            } else {
                map.put(field.name(), fieldValue);
            }
        }

        return map;
    }

    private static List<Object> convertList(List<?> list) {
        List<Object> newList = new ArrayList<>();
        for (Object item: list) {
            if (item instanceof Struct) {
                newList.add(structToMap((Struct) item));
            } else {
                newList.add(item);
            }
        }
        return newList;
    }

    private static Map<String, Object> convertMap(Map<?, ?> map) {
        Map<String, Object> newMap = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry: map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Struct) {
                newMap.put(entry.getKey().toString(), structToMap((Struct) value));
            } else {
                newMap.put(entry.getKey().toString(), value);
            }
        }
        return newMap;
    }
}
