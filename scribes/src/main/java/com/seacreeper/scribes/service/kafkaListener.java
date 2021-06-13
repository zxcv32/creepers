package com.seacreeper.scribes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seacreeper.scribes.model.HttpScribe;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class kafkaListener {

  @Autowired private InfluxDbTalker influxDbTalker;

  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  @KafkaListener(id = "httpScribeListener", topics = "httpScribe")
  public void listen(String data) {
    log.info("Data received: " + data.substring(0, Math.min(100, data.length())) + "...");
    try {
      val httpScribe = objectMapper.readValue(data, HttpScribe.class);
      influxDbTalker.write(httpScribe);
      log.info("Pushed HTTP data");
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
