package com.example.connect;

import org.apache.kafka.connect.source.SourceTask;
import org.apache.kafka.connect.errors.RetriableException;
import org.apache.kafka.connect.source.SourceRecord;

import java.io.IOException;
import java.util.*;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.connect.HttpPollSourceConnector.*;

public class HttpPollSourceTask extends SourceTask{
    private String url;
    private long pollIntervalMs;
    private String topic;

    private OkHttpClient http;

    private Logger logger;

    @Override
    public String version() { return "0.1.0"; }

    @Override
    public void start(Map<String, String> props) {
        this.url = props.get(HTTP_URL);
        this.pollIntervalMs = Long.parseLong(props.getOrDefault(POLL_INTERVAL_MS, "1000"));
        this.topic = props.get(TOPIC);

        this.http = new OkHttpClient();
        this.logger = LoggerFactory.getLogger(HttpPollSourceTask.class);
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        Thread.sleep(pollIntervalMs);

        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(this.url)).newBuilder();
        Request req = new Request.Builder().url(builder.build()).get().build();

        try (Response res = http.newCall(req).execute()) {
            if (!res.isSuccessful()) {
                logger.error("Failed to fetch data from {}: {}", this.url, res.code());
                throw new RetriableException("Failed to fetch data from " + this.url + ": " + res.code());
            }

            String responseBody = Objects.requireNonNull(res.body()).string();

            SourceRecord sourceRecord = new SourceRecord(
                null,
                null,
                this.topic,
                null,
                null,
                responseBody
            );
            return Collections.singletonList(sourceRecord);
        } catch (IOException e) {
            logger.error("Failed to fetch data from {}: {}", this.url, e.getMessage());
            throw new RetriableException("Failed to fetch data from " + this.url + ": " + e.getMessage());
        }
    }

    @Override
    public void stop() {}
}
