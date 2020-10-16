package com.application.shophop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@EnableJpaRepositories(basePackages ="com.application.shophop.repository" )
public class ShophopApplication {


	public static void main(String[] args) {
		SpringApplication.run(ShophopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/erp/*").allowedOrigins("http://localhost:4200").allowedHeaders("*").allowedMethods("*").allowCredentials(true);
			}
		};
	}
}
