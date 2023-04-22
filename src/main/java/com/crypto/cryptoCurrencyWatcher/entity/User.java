package com.crypto.cryptoCurrencyWatcher.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    private int id;
    private String username;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserCoin> userCoins;
}
