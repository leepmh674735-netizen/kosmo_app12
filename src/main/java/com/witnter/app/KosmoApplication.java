package com.witnter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KosmoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KosmoApplication.class, args);
	}

}
