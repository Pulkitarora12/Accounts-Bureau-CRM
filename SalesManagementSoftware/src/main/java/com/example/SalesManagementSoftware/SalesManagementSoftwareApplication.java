package com.example.SalesManagementSoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@EnableScheduling
public class SalesManagementSoftwareApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagementSoftwareApplication.class, args);
	}

}
