package com.crypto.cryptoCurrencyWatcher.CoinUpdater;

import com.crypto.cryptoCurrencyWatcher.config.Config;
import com.crypto.cryptoCurrencyWatcher.config.CryptoCurrency;
import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CoinPriceUpdater implements Runnable{
    private String url;
    private URL cryptowatcherURL;
    @Autowired
    private Config config;
    private List<Integer> coins_id = Arrays.asList(90,80,48543);

    public CoinPriceUpdater() throws MalformedURLException {
        url = "https://api.coinlore.net/api/ticker/?id=";
        cryptowatcherURL = new URL("http://localhost:8080/api/cryptowatcher/");
    }

    @Override
    public void run() {
        try {
            while(true) {
                List<Coin> coins = new ArrayList<>();
                for (CryptoCurrency c: config.getCryptoCurrencies()) {
                    coins.add(getCoinById(c.getId()));
                }
                updateCoins(coins);
                TimeUnit.MINUTES.sleep(1);
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Coin getCoinById(int id) throws IOException {
        URL url = new URL(this.url+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(1000);
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonString = in.readLine();
        JSONObject jsonObject = new JSONObject(jsonString.substring(1, jsonString.length()-1));
        Coin coin = new Coin();
        coin.setId(jsonObject.getInt("id"));
        coin.setSymbol(jsonObject.getString("symbol"));
        coin.setName(jsonObject.getString("name"));
        coin.setPrice_usd(jsonObject.getDouble("price_usd"));
        return coin;
    }

    public void updateCoins(List<Coin> coins) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) cryptowatcherURL.openConnection();
        String obj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(coins);
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(obj);
        osw.flush();
        osw.close();
        os.close();
        connection.connect();
        connection.getResponseCode();
    }
}
