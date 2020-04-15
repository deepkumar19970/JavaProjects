package com.application.shophop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages ="com.application.shophop.repository" )
public class ShophopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShophopApplication.class, args);
	}

}
