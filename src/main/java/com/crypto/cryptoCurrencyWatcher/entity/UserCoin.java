package com.crypto.cryptoCurrencyWatcher.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="usercoin")
@Data
public class UserCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int coinId;
    private double price;
    private String symbol;
    @ManyToOne()
    @JsonIgnoreProperties("userCoins")
    private User user;
}
