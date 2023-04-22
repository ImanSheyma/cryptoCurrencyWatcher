package com.crypto.cryptoCurrencyWatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    @Autowired
    private CoinService coinService;
    @Autowired
    private NotifyService notifyService;

    @Scheduled(fixedRate = 60000)
    public void updateAndNotify() throws Exception {
        coinService.update();
        notifyService.notifyUsre();
    }
}
