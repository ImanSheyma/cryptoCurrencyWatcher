package com.crypto.cryptoCurrencyWatcher.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Config {
    private List<CryptoCurrency> cryptoCurrencies;
    private String[] objects = {"{\"id\":80, \"symbol\":\"ETH\"}",
                                "{\"id\":90, \"symbol\":\"BTC\"}",
                                "{\"id\":48543, \"symbol\":\"SOL\"}"};

    public Config() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        cryptoCurrencies = new ArrayList<>();
        for(String o:objects)
            cryptoCurrencies.add(m.readValue(o, CryptoCurrency.class));
    }

    public Config(List<CryptoCurrency> cryptoCurrencies) {
        this.cryptoCurrencies = cryptoCurrencies;
    }

    public List<CryptoCurrency> getCryptoCurrencies() {
        return cryptoCurrencies;
    }

    public void setCryptoCurrencies(List<CryptoCurrency> cryptoCurrencies) {
        this.cryptoCurrencies = cryptoCurrencies;
    }

    @Override
    public String toString() {
        return "Config{" +
                "cryptoCurrencies=" + cryptoCurrencies +
                '}';
    }
}
