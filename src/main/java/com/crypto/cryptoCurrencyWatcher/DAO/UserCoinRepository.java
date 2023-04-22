package com.crypto.cryptoCurrencyWatcher.DAO;

import com.crypto.cryptoCurrencyWatcher.entity.User;
import com.crypto.cryptoCurrencyWatcher.entity.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCoinRepository extends JpaRepository<UserCoin, Integer> {
    Optional<UserCoin> findUserCoinByUserAndSymbol(User user, String symbol);
}
