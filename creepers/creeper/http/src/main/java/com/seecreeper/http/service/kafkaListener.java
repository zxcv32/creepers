package com.seecreeper.http.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seecreeper.http.workers.Httper;
import com.seecreeper.http.model.HttpOperation;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableAsync
public class kafkaListener {

  @Autowired private Httper httper;

  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  @KafkaListener(id = "httpRegnantListener", topics = "httpRegnant")
  public void listen(String task) {
    log.info("Task received: " + task);
    try {
      val httpOperation = objectMapper.readValue(task, HttpOperation.class);
      httper.query(httpOperation);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
