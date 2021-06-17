package com.seacreeper.scribes.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.seacreeper.scribes.model.HttpScribe;
import com.seacreeper.scribes.model.HttpScribeMeasurement;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InfluxDbTalker {

  @Value("${com.seacreeper.scribes.influxdb.host}")
  private String influxdbHost;

  @Value("${com.seacreeper.scribes.influxdb.port}")
  private String influxdbPort;

  @Value("${com.seacreeper.scribes.influxdb.bucket}")
  private String influxdbBucket;

  @Value("${com.seacreeper.scribes.influxdb.token}")
  private String influxdbToken;

  @Value("${com.seacreeper.scribes.influxdb.org}")
  private String influxdbOrg;

  private InfluxDBClient influxDbClient;

  public InfluxDbTalker() {}

  @Async
  public void write(HttpScribe httpScribe) {
    this.influxDbClient =
        InfluxDBClientFactory.create(
            influxdbHost + ":" + influxdbPort,
            influxdbToken.toCharArray(),
            influxdbOrg,
            influxdbBucket);
    try (WriteApi writeApi = influxDbClient.getWriteApi()) {
      log.info("Data is here with length: " + httpScribe.getData().length());
      val httpScribeMeasurement = new HttpScribeMeasurement();
      httpScribeMeasurement.setId(httpScribe.getId());
      httpScribeMeasurement.setData(httpScribe.getData());
      httpScribeMeasurement.setTimestamp(Instant.now());
      writeApi.writeMeasurement(WritePrecision.MS, httpScribeMeasurement);
    }
  }
}
