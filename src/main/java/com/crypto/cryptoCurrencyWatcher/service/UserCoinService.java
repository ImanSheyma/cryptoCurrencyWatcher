package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.DAO.UserCoinRepository;
import com.crypto.cryptoCurrencyWatcher.entity.User;
import com.crypto.cryptoCurrencyWatcher.entity.UserCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCoinService {
    @Autowired
    private UserCoinRepository userCoinRepository;

    public List<UserCoin> findAllUserCoins(){
        return userCoinRepository.findAll();
    }

    public UserCoin saveUserCoin(User user, Integer id, String symbol, Double price){
        UserCoin userCoin = new UserCoin();
        userCoin.setUser(user);
        userCoin.setCoinId(id);
        userCoin.setSymbol(symbol);
        userCoin.setPrice(price);
        userCoinRepository.save(userCoin);
        return userCoin;
    }

    public Optional<UserCoin> findByUserAndSymbol(User user, String symbol){
        return userCoinRepository.findUserCoinByUserAndSymbol(user, symbol);
    }

    public UserCoin updateUserCoin(UserCoin userCoin){
        return userCoinRepository.save(userCoin);
    }
}
