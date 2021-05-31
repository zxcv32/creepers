package com.seacreeper.queen;

import com.seacreeper.queen.controller.creeper.OperationNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class StandardResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleStandardException(Exception ex, WebRequest request) {
    val exceptionResponse = new StandardException(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OperationNotFoundException.class)
  public final ResponseEntity<Object> operationNotFoundException(
      OperationNotFoundException ex, WebRequest request) {
    val exceptionResponse = new StandardException(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    val message = new ArrayList<String>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              message.add(error.getDefaultMessage());
            });
    val exceptionResponse =
        new StandardException(
            "Validation Failed", message.stream().collect(Collectors.joining(", ")));
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
