package com.crypto.cryptoCurrencyWatcher.entity;

import javax.persistence.*;

@Entity
@Table(name = "coin")
public class Coin {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "price_usd")
    private double price_usd;

    public Coin() {
    }

    public Coin(int id, String symbol, String name, double price_usd) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price_usd = price_usd;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", symbol=\'" + symbol + '\'' +
                ", name=\'" + name + '\'' +
                ", price_usd=" + price_usd +
                '}';
    }
}
