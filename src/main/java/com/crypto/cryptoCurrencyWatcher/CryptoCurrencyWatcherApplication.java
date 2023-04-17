package com.crypto.cryptoCurrencyWatcher;

import com.crypto.cryptoCurrencyWatcher.CoinUpdater.CoinPriceUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class CryptoCurrencyWatcherApplication {

	private static ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException {
		applicationContext = SpringApplication.run(CryptoCurrencyWatcherApplication.class, args);
		CoinPriceUpdater cpu = applicationContext.getBean(CoinPriceUpdater.class);
		Thread thread = new Thread(cpu);
		thread.start();
	}

}
