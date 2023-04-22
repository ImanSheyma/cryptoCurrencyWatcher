package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.crypto.cryptoCurrencyWatcher.entity.User;
import com.crypto.cryptoCurrencyWatcher.entity.UserCoin;
import com.crypto.cryptoCurrencyWatcher.exceptions.CoinNotAvailableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NotifyService {
    @Autowired
    private CoinService coinService;
    @Autowired
    private UserCoinService userCoinService;
    @Autowired
    private UserService userService;

    public String register(String username, String symbol){
        Optional<Coin> coinOptional = coinService.findCoinBySymbol(symbol);
        Coin coin = coinOptional.orElseThrow(()-> new CoinNotAvailableException(symbol));

        Optional<User> userOptional = userService.findByUsername(username);
        User user = userOptional.orElseGet(()-> userService.saveUser(username));

        Optional<UserCoin> userCoinOptional = userCoinService.findByUserAndSymbol(user, symbol);
        if(userCoinOptional.isEmpty()){
            userCoinService.saveUserCoin(user, coin.getId(), symbol, coin.getPrice_usd());
            return "User registered";
        }

        UserCoin userCoin = userCoinOptional.get();
        userCoin.setPrice(coin.getPrice_usd());
        userCoinService.updateUserCoin(userCoin);

        return "user coin updated";
    }

    public void notifyUsre(){
        List<UserCoin> userCoins = userCoinService.findAllUserCoins();
        for(UserCoin userCoin: userCoins){
            Optional<Coin> coin = coinService.findCoinById(userCoin.getId());

            Double procent = Math.abs((coin.get().getPrice_usd()/userCoin.getPrice()) - 1);
            if(procent.compareTo(Double.valueOf(0.01)) < 0) continue;
            
            log.info("Actual price for coin " + userCoin.getSymbol() + ": " + coin.get().getPrice_usd()+
                    " user price: " + userCoin.getPrice());
            log.warn("price change for coin " + userCoin.getSymbol() + " registered bu user " + userCoin.getUser().getUsername()+
                    " is more than 1%(" + procent + ").");
            
        }
    }
}
