package com.crypto.cryptoCurrencyWatcher.log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private Logger logger;
    private FileHandler fh;

    public Log(String fileName) throws IOException {
        File f = new File(fileName);
        fh = new FileHandler(fileName, true);
        logger = Logger.getLogger("priceChangeLogger");
        logger.addHandler(fh);
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public FileHandler getFh() {
        return fh;
    }

    public void setFh(FileHandler fh) {
        this.fh = fh;
    }
}
