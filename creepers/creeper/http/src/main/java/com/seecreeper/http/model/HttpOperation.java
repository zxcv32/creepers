package com.seecreeper.http.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class HttpOperation {
  @NonNull private String id;
  @NonNull private String url;
}
