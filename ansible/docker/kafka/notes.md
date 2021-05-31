Deploy zookeeper

docker run -d \
--net=confluent \
--name=zookeeper \
-e ZOOKEEPER_CLIENT_PORT=2181 \
confluentinc/cp-zookeeper:5.4.4


Deploy Kafka

docker run -d \
--net=confluent \
--name=kafka \
-e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
-p 9092:9092 \
confluentinc/cp-kafka:5.4.4


Create Topic

docker run \
--net=confluent \
--rm confluentinc/cp-kafka:5.4.4 \
kafka-topics --create --topic httpRegnant --partitions 1 --replication-factor 1 \
--if-not-exists --zookeeper zookeeper:2181


Describe Kafka topics

docker run \
--net=requirements_confluent \
--rm \
confluentinc/cp-kafka:5.4.4 \
kafka-topics --describe --topic httpRegnant --zookeeper zookeeper:2181


Show Kafka console logs

docker run \
--net=requirements_confluent \
--rm \
confluentinc/cp-kafka:5.4.4 \
kafka-console-consumer --bootstrap-server kafka:9092 --topic httpRegnant --from-beginning --max-messages 42


List Topics

docker run \
--net=requirements_confluent \
--rm \
confluentinc/cp-kafka:5.4.4 \
kafka-topics --list --zookeeper zookeeper:2181

Delete Topics

docker run \
--net=requirements_confluent \
--rm \
confluentinc/cp-kafka:5.4.4 \
kafka-topics --delete --topic httpRegnant-x --zookeeper zookeeper:2181