package com.seacreeper.queen.controller;

import com.seacreeper.queen.model.WebPage;
import com.seacreeper.queen.service.HttpService;
import java.io.IOException;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WipController {

  @GetMapping("/test")
  public WebPage getPage(@RequestParam(value = "url", defaultValue = "Stranger") String url)
      throws IOException {
    val response = new WebPage(url);
    response.setContent(HttpService.getWebPageContent(url));
    return response;
  }
}
