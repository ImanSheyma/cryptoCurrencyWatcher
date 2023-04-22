package com.crypto.cryptoCurrencyWatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class CryptoCurrencyWatcherApplication {

	private static ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException {
		applicationContext = SpringApplication.run(CryptoCurrencyWatcherApplication.class, args);
	}

}
