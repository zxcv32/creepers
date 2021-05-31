package com.seacreeper.queen.controller.creeper;

import lombok.NonNull;

public class OperationNotFoundException extends
    RuntimeException {

  public OperationNotFoundException(@NonNull final String message){
    super(message);
  }
}
