package com.crypto.cryptoCurrencyWatcher.config;


public class CryptoCurrency {
    private int id;
    private String symbol;

    public CryptoCurrency(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public CryptoCurrency(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
