package com.example.connect;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.source.SourceConnector;
import org.apache.kafka.connect.connector.Task;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpPollSourceConnector extends SourceConnector {
    public static final String HTTP_URL = "http.url";
    public static final String POLL_INTERVAL_MS = "poll.interval.ms";
    public static final String TOPIC = "topic";

    private Logger logger;
    private Map<String, String> configProps;

    @Override
    public String version() { return "0.1.0"; }

    @Override
    public void start(Map<String, String> props) {
        this.logger = LoggerFactory.getLogger(HttpPollSourceConnector.class);
        this.logger.info("Starting HttpPollSourceConnector");
        this.configProps = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return HttpPollSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        return List.of(new HashMap<>(configProps));
    }

    @Override
    public void stop() {
        this.logger.info("Stopping HttpPollSourceConnector");
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef()
            .define(HTTP_URL, ConfigDef.Type.STRING, ConfigDef.NO_DEFAULT_VALUE, ConfigDef.Importance.HIGH, "URL to poll")
            .define(POLL_INTERVAL_MS, ConfigDef.Type.INT, 1000, ConfigDef.Importance.MEDIUM, "Poll interval in milliseconds")
            .define(TOPIC, ConfigDef.Type.STRING, ConfigDef.NO_DEFAULT_VALUE, ConfigDef.Importance.HIGH, "Topic to produce to");
    }
}
