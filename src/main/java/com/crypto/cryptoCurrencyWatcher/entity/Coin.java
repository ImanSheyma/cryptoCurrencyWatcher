package com.crypto.cryptoCurrencyWatcher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coin {
    @Id
    private int id;
    @Column(name = "symbol", unique = true)
    private String symbol;
    private double price_usd;
}
