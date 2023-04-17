package com.crypto.cryptoCurrencyWatcher.DAO;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;

import java.io.IOException;
import java.util.List;

public interface CryptoCurrencyWatcherDAO {

    public Coin getCoinById(int id);
    public void updateCoinsInfo(List<Coin> coins) throws IOException;
    public List<Coin> getAllCoins();

    public String logPriceChange(String name, String symbol);

}
