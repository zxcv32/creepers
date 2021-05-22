package com.seecreeper.http;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

@Slf4j
public class GoogleHttpClientTest {

  @Test
  public void gTest() throws IOException {
    val pageContent = GoogleHttpClient.getWebPageContent("https://google.com");
    assertNotNull(pageContent);
    log.info(String.valueOf(pageContent.length()));
  }
}
