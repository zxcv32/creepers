package com.seacreeper.queen.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WipController {

  @GetMapping("/test")
  public String getPage(@RequestParam(value = "url", defaultValue = "Stranger") String url)
      throws IOException {
    return "Test Page";
  }
}
