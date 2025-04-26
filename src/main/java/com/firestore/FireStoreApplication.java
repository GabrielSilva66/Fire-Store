package com.firestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class FireStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FireStoreApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// Placeholder for any startup logic
		System.out.println("FireStore is running...");
	}
}

