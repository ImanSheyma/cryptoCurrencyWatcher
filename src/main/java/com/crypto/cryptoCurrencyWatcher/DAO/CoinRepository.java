package com.crypto.cryptoCurrencyWatcher.DAO;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
    @Override
    Optional<Coin> findById(Integer integer);
    Optional<Coin> findBySymbol(String symbol);
    List<Coin> findAll();
}
