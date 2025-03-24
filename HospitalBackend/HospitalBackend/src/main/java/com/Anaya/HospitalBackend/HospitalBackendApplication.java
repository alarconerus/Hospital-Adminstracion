package com.Anaya.HospitalBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Anaya")

public class HospitalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalBackendApplication.class, args);
	}

}
