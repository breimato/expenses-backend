package com.expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** The Class Expenses Application. */
@SpringBootApplication
@EnableScheduling
public class ExpensesApplication {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {

        SpringApplication.run(ExpensesApplication.class, args);
    }
}
