package com.devops.gamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Game Store application.
 * This is the entry point of the Spring Boot application.
 */
@SpringBootApplication
public class GamestoreApplication {

	/**
	 * Main method which serves as the entry point for the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		System.out.println("Postgres URL: " + System.getenv("POSTGRES_URL"));
		System.out.println("Postgres User: " + System.getenv("POSTGRES_USER"));
		SpringApplication.run(GamestoreApplication.class, args);
	}

}