package com.example.admin_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdminDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminDashboardApplication.class, args);
	}

}
