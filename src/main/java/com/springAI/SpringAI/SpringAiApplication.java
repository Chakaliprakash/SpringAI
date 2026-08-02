package com.springAI.SpringAI;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiApplication.class, args);
	}
	@Value("${OPEN_AI_apikey:NOT_FOUND}")
	private String testKey;

	@PostConstruct
	public void checkEnv() {

    	System.out.println("OPEN_AI_apikey loaded: " + (testKey.equals("NOT_FOUND") ? "NO ❌" : "YES ✅ (" + testKey.substring(0, 10) + "...)"));
	
	}

}
