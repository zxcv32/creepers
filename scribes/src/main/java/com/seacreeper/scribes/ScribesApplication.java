package com.seacreeper.scribes;

import com.seacreeper.scribes.service.InfluxDbTalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScribesApplication {
  @Autowired private InfluxDbTalker influxdb;

  public static void main(String[] args) {
    SpringApplication.run(ScribesApplication.class, args);
  }
}
