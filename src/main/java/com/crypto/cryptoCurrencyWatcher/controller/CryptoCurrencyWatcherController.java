package com.crypto.cryptoCurrencyWatcher.controller;

import com.crypto.cryptoCurrencyWatcher.config.Config;
import com.crypto.cryptoCurrencyWatcher.config.CryptoCurrency;
import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.crypto.cryptoCurrencyWatcher.service.CryptoCurrencyWatcherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class CryptoCurrencyWatcherController {

    @Autowired
    private CryptoCurrencyWatcherService service;

    @GetMapping ("/api/cryptowatcher/{id}")
    public Coin getCoin(@PathVariable int id, Model model) throws MalformedURLException {
        Coin coin = service.getCoinById(id);
        return coin;
    }

    @GetMapping("/api/cryptowatcher/")
    public List<CryptoCurrency> getAllCoins() throws JsonProcessingException {
        Config c = new Config();
        return c.getCryptoCurrencies();
    }

    @PutMapping (value = "/api/cryptowatcher/")
    public List<Coin> updateCoinPrice(@RequestBody String coinsString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Coin> coins = mapper.readValue(coinsString, new TypeReference<List<Coin>>() {});
        service.updateCoinsInfo(coins);
        return coins;
    }

    @RequestMapping(value = "/api/cryptowatcher/notify")
    public String logPriceChange(@RequestParam(value="name") String name,
                                 @RequestParam(value="symbol") String symbol){
        return service.logPriceChange(name, symbol);
    }
}
