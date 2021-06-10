package com.seecreeper.http.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class HttpScribe {
  @NonNull private String id;
  @NonNull private String data;
}
