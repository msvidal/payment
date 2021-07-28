package com.nubank.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PaymentApplication.class, args);

	}
}
