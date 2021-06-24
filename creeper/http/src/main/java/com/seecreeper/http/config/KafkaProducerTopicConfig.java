package com.seecreeper.http.config;

import java.util.HashMap;
import java.util.Optional;
import lombok.val;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaProducerTopicConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value(value = "${com.seacreeper.kafka.topic.creeper.http:httpScribe}")
  private String scribe;

  @Value(value = "${com.seacreeper.kafka.topic.creeper.http.partitions:#null}")
  private Optional<Integer> scribePartition;

  @Value(value = "${com.seacreeper.kafka.topic.creeper.http.replicas:#null}")
  private Optional<Integer> scribeReplication;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    val configs = new HashMap<String, Object>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic newTopic() {
    return TopicBuilder.name(scribe)
        .partitions(scribePartition.orElse(1))
        .replicas(scribeReplication.orElse(1))
        .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "snappy")
        .build();
  }
}
