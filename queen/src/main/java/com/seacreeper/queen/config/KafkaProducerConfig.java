package com.seacreeper.queen.config;

import com.seacreeper.queen.model.HttpOperation;
import com.seacreeper.queen.service.HttpOperationService;
import java.util.HashMap;
import java.util.Map;
import lombok.val;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public ProducerFactory<String, HttpOperation> producerFactory() {
    return new DefaultKafkaProducerFactory<>(buildCommonProperties());
  }

  private Map<String, Object> buildCommonProperties() {
    val properties = new HashMap<String, Object>();
    if (this.bootstrapServers != null) {
      properties.put("bootstrap.servers", this.bootstrapServers);
    }
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return properties;
  }

  @Bean
  public KafkaTemplate<String, HttpOperation> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
