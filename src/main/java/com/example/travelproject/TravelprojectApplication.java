package com.example.travelproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TravelprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelprojectApplication.class, args);
	}//

}
