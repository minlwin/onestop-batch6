package com.jdc.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApiApplication.class, args);
	}

}
