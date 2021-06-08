package com.seecreeper.http;

import com.seecreeper.http.workers.Httper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpApplication {

  @Autowired private Httper httper;

  public static void main(String[] args) {
    SpringApplication.run(HttpApplication.class, args);
  }
}
