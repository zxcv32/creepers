package com.seacreeper.scribes.model;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import java.time.Instant;
import lombok.Data;

@Data
@Measurement(name = "httpScribe")
public class HttpScribeMeasurement {
  @Column(tag = true)
  private final String scribe = "http";

  @Column(tag = true)
  private String id;

  @Column private String data;

  @Column(timestamp = true)
  private Instant timestamp;
}
