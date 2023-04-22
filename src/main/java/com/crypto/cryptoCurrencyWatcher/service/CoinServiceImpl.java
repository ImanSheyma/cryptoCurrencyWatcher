package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.DAO.CoinRepository;
import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService{
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${crypto.api.url}")
    private String url;

    @Override
    public Optional<Coin> findCoinById(int id) {
        return coinRepository.findById(id);
    }

    @Override
    public Optional<Coin> findCoinBySymbol(String symbol) {
        return coinRepository.findBySymbol(symbol);
    }

    @Override
    public List<Coin> findAllCoins() {
        return coinRepository.findAll();
    }

    public void update() throws Exception {
        List<Coin> coins = findAllCoins();
        for(Coin coin: coins){
            String coinUrl = url + "?id=" + coin.getId();
            String response = restTemplate.getForObject(coinUrl, String.class);
            JsonNode jsonNode = mapper.readTree(response);
            Double newPrice = Double.parseDouble(jsonNode.get(0).get("price_usd").textValue());
            coin.setPrice_usd(newPrice);
            coinRepository.save(coin);
        }
    }
}
