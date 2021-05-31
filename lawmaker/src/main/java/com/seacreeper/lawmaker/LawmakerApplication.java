package com.seacreeper.lawmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LawmakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawmakerApplication.class, args);
	}

}
