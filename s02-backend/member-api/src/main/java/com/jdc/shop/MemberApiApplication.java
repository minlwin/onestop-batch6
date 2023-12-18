package com.jdc.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.shop.model.BaseRepositoryImpl;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = "/jwt-token.properties")
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class MemberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApiApplication.class, args);
	}

}
