package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;

import java.util.List;
import java.util.Optional;

public interface CoinService {
    public Optional<Coin> findCoinById(int id);
    public Optional<Coin> findCoinBySymbol(String symbol);
    public List<Coin> findAllCoins();
    public void update() throws Exception;
}
