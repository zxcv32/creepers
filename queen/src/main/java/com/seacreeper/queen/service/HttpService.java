package com.seacreeper.queen.service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import lombok.NonNull;

public class HttpService {

  public static String getWebPageContent(@NonNull final String url) throws IOException {
    HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
    HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(url));
    String rawResponse = request.execute().parseAsString();
    return rawResponse;
  }
}
