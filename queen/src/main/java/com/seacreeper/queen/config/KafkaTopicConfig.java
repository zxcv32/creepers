package com.seacreeper.queen.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.lang.Nullable;

@Configuration
public class KafkaTopicConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

//  @Value(value = "${com.seacreeper.queen.kafka.topics}")
//  private String regnant;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    val configs = new HashMap<String, Object>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

//  @Bean
//  public List<NewTopic> newTopic() {
//    val collect = Arrays.stream(regnant.split(","))
//        .filter(this::isNotBlank)
//        .map(String::strip)
//        .map(r -> new NewTopic(r, 1, (short) 1))
//        .collect(Collectors.toList());
//    return collect;
//  }
//
//  private boolean isNotBlank(@Nullable final String str){
//    return !str.isBlank();
//  }
}
