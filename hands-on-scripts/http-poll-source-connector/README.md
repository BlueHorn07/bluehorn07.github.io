```bash
nerdctl --namespace k8s.io build \
  -t my-kafka-connect-debezium:httpPollSourceTask \
  -f http-poll.source.connector.Dockerfile .
```
