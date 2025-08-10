package com.example.springboot_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.ApplicationModule;

@SpringBootApplication
@ApplicationModule(displayName = "Spring Boot Demo Application")
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
