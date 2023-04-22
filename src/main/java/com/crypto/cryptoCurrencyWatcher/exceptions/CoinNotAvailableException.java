package com.crypto.cryptoCurrencyWatcher.exceptions;

public class CoinNotAvailableException extends RuntimeException{
    public CoinNotAvailableException(String symbol){
        super("coin with ticker " + symbol + " not found");
    }
}
