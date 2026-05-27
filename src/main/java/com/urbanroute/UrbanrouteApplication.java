package com.urbanroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UrbanrouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanrouteApplication.class, args);
	}

}
