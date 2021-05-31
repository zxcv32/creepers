package com.seacreeper.queen.service;

import com.seacreeper.queen.model.HttpOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class HttpOperationService {

  @Autowired private KafkaTemplate<String, HttpOperation> kafkaTemplate;

  @Value(value = "${com.seacreeper.queen.kafka.topic.http}")
  private String TOPIC;

  public Status publishToMq(@NonNull final HttpOperation httpOperation) {
    val future = kafkaTemplate.send(TOPIC, httpOperation);
    future.addCallback(
        new ListenableFutureCallback<>() {
          @Override
          public void onFailure(Throwable ex) {
            log.error("Unable to Task=[" + httpOperation + "] due to: " + ex.getMessage());
          }

          @Override
          public void onSuccess(SendResult<String, HttpOperation> result) {
            log.debug(
                "Sent Task=["
                    + httpOperation
                    + "] with offset=["
                    + result.getRecordMetadata().offset()
                    + "]");
          }
        });
    return Status.PUBLISHED;
  }
}
