package com.seacreeper.queen.controller;

import com.seacreeper.queen.model.WipModel;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WipController {

  @GetMapping("/test")
  public WipModel getPage(@RequestParam(value = "url", defaultValue = "Stranger") String url)
      throws IOException {
    return new WipModel(url);
  }
}
