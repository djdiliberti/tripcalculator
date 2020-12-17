package com.dansoft.tripcalculator;

import com.dansoft.tripcalculator.calculator.ExpenseCoordinator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripcalculatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TripcalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// first argument should be the location of the file

		if (args.length > 0) {
			String inputFile = args[0];

			ExpenseCoordinator coordinator = new ExpenseCoordinator(inputFile);
			coordinator.run();
		} else {
			System.out.println("No file provided to process");
		}
	}
}
