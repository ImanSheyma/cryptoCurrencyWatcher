package com.crypto.cryptoCurrencyWatcher.config;

import com.crypto.cryptoCurrencyWatcher.DAO.CoinRepository;
import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DBInitializer implements CommandLineRunner {
    @Value("${crypto.api.currencies}")
    private String configCoins;
    @Autowired
    private CoinRepository coinRepository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Coin> coins = mapper.readValue(configCoins, new TypeReference<List<Coin>>() {});
        for(Coin configCoin: coins){
            Optional<Coin> coin = coinRepository.findBySymbol(configCoin.getSymbol());
            if(coin.isEmpty())
                coinRepository.save(configCoin);
        }
    }
}
