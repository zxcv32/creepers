package com.seacreeper.queen.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@RequiredArgsConstructor
public class WipModel {

  @URL(message = "Must be a valid URL")
  @ApiModelProperty(notes = "Must be a valid URL")
  @NonNull
  private final String url;

  private List arr = new ArrayList<String>();
}
