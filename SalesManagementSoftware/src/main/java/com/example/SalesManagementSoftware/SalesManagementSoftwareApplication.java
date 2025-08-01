package com.example.SalesManagementSoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesManagementSoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagementSoftwareApplication.class, args);
	}

	@GetMapping("/")
	public static String hello() {
		return "hello world !";
	}

}
