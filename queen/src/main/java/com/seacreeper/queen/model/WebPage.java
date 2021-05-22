package com.seacreeper.queen.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
public class WebPage {

  @NonNull private String url;
  @Nullable private String content;
}
