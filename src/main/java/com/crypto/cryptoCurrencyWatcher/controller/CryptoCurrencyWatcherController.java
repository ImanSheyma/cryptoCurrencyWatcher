package com.crypto.cryptoCurrencyWatcher.controller;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.crypto.cryptoCurrencyWatcher.exceptions.CoinNotAvailableException;
import com.crypto.cryptoCurrencyWatcher.service.CoinService;
import com.crypto.cryptoCurrencyWatcher.service.NotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CryptoCurrencyWatcherController {

    private final CoinService service;
    private final NotifyService notifyService;

    @GetMapping ("/api/cryptowatcher/{id}")
    public ResponseEntity<Optional<Coin>> getCoin(@PathVariable("id") int id){
        Optional<Coin> coin = service.findCoinById(id);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @GetMapping("/api/cryptowatcher/symbol/{symbol}")
    public ResponseEntity<Optional<Coin>> getCoinBySymbol(@PathVariable("symbol") String symbol){
        Optional<Coin> coin = service.findCoinBySymbol(symbol);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @GetMapping("/api/cryptowatcher/coins")
    public ResponseEntity<List<Coin>> getAllCoins(){
        List<Coin> coins = service.findAllCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @PostMapping("/notify")
    public ResponseEntity<String> notify(@RequestParam("name") String name, @RequestParam("symbol") String symbol) throws CoinNotAvailableException {
        String msg = notifyService.register(name, symbol);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(CoinNotAvailableException.class)
    public ResponseEntity<String> handleCoinNotAvailableException(CoinNotAvailableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
