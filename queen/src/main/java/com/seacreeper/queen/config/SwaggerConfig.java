package com.seacreeper.queen.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  private static final Contact DEFAULT_CONTACT =
      new Contact("Ashwani Sharma", "https://zxcv32.com", "ashwani@zxcv32.com");
  private static final ApiInfo API_INFO =
      new ApiInfo(
          "Sea Creeper Queen Documentation",
          "Sea Creeper Queen Documentation",
          "1.0",
          "urn:tos",
          DEFAULT_CONTACT,
          "Eclipse Public License 2.0",
          "https://github.com/zxcV32/SeaCreeper/blob/main/LICENSE",
          new ArrayList<>());

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30).apiInfo(API_INFO);
  }
}
