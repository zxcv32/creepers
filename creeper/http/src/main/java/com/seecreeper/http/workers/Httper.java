package com.seecreeper.http.workers;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.seecreeper.http.model.HttpOperation;
import com.seecreeper.http.model.HttpScribe;
import java.io.IOException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class Httper {

  @Autowired private KafkaTemplate<String, HttpScribe> scribeKafkaTemplate;

  @Value(value = "${com.seacreeper.kafka.topic.creeper.http:httpScribe}")
  private String scribe;

  @Async
  public void query(@NonNull final HttpOperation httpOperation) throws IOException {
    val url = httpOperation.getUrl();
    val requestFactory = new NetHttpTransport().createRequestFactory();
    val request = requestFactory.buildGetRequest(new GenericUrl(url));
    val rawResponse = request.execute().parseAsString();
    log.info("Data: " + rawResponse.substring(0, Math.min(20, rawResponse.length())));
    val scribeData = new HttpScribe();
    scribeData.setId(httpOperation.getId());
    scribeData.setData(rawResponse);
    val future = scribeKafkaTemplate.send(scribe, scribeData);
    future.addCallback(
        new KafkaSendCallback<>() {

          /**
           * Called when the {@link ListenableFuture} completes with success.
           *
           * <p>Note that Exceptions raised by this method are ignored.
           *
           * @param result the result
           */
          @Override
          public void onSuccess(SendResult<String, HttpScribe> result) {
            log.debug(
                "Sent data=["
                    + scribeData
                        .toString()
                        .substring(0, Math.min(50, scribeData.toString().length()))
                    + "] with offset=["
                    + result.getRecordMetadata().offset()
                    + "]");
          }

          /**
           * Called when the send fails.
           *
           * @param ex the exception.
           */
          @Override
          public void onFailure(KafkaProducerException ex) {
            log.error("Unable to send data=[" + scribeData + "] due to: " + ex.getMessage());
          }
        });
  }
}
