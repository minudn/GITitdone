package com.devops.gamestore;

/**
 * Main application class for the Game Store application.
 */
public final class GamestoreApplication {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private GamestoreApplication() {
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * Main method to start the application.
	 *
	 * @param args the command line arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(GamestoreApplication.class, args);
	}

}