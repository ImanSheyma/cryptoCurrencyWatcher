package com.crypto.cryptoCurrencyWatcher.service;
import com.crypto.cryptoCurrencyWatcher.DAO.CryptoCurrencyWatcherDAO;
import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CryptoCurrencyWatcherServiceImpl implements CryptoCurrencyWatcherService{
    @Autowired
    private CryptoCurrencyWatcherDAO repository;

    @Override
    public Coin getCoinById(int id){
        return repository.getCoinById(id);
    }

    @Override
    public void updateCoinsInfo(List<Coin> coins) throws IOException {
        repository.updateCoinsInfo(coins);
    }

    @Override
    public List<Coin> getAllCoins() {
        return repository.getAllCoins();
    }

    @Override
    public String logPriceChange(String name, String symbol) {
        return repository.logPriceChange(name, symbol);
    }
}
