package com.example.controlerdepessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ControlerDePessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlerDePessoasApplication.class, args);
	}

}
