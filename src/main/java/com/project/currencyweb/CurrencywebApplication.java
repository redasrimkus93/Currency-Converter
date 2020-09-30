package com.project.currencyweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class CurrencywebApplication {

	public static void main(String[] args)
		{
			SpringApplication.run(CurrencywebApplication.class, args);
		}
	}

