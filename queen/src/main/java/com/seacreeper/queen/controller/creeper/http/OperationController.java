package com.seacreeper.queen.controller.creeper.http;

import com.seacreeper.queen.controller.creeper.OperationNotFoundException;
import com.seacreeper.queen.model.HttpOperation;
import com.seacreeper.queen.service.HttpOperationService;
import javax.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/creeper/http/operation")
public class OperationController {

  @Autowired private HttpOperationService httpOperationService;

  @RequestMapping(
      value = "/",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      method = {RequestMethod.POST, RequestMethod.PUT})
  public ResponseEntity<Object> submitUrl(@Valid @RequestBody HttpOperation httpOperation) {
    httpOperationService.publishToMq(httpOperation);
    val location = ServletUriComponentsBuilder.fromCurrentRequest().path("/status").build().toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}/status")
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public String submitStatus(@PathVariable("id") String id) {
    //    if (id.equals("1")) {
    //      throw new OperationNotFoundException(String.format("Operation with id: '%s' not found",
    // id));
    //    }

    return "TODO: look through scribes";
  }
}
