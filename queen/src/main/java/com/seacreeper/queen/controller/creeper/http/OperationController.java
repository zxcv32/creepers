package com.seacreeper.queen.controller.creeper.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creeper/http/operation")
public class OperationController {

  @PostMapping("/{id}")
  @PutMapping("/{id}")
  public String getPageContent(@PathVariable("id") long id, @RequestBody String url) {
    return String.format("Operation: %d : | URL: %s", id, url);
  }
}
