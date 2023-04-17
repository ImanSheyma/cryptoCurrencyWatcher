package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface CryptoCurrencyWatcherService {
    public Coin getCoinById(int id) throws MalformedURLException;
    public void updateCoinsInfo(List<Coin> coins) throws IOException;
    public List<Coin> getAllCoins();

    public String logPriceChange(String name, String symbol);
}
