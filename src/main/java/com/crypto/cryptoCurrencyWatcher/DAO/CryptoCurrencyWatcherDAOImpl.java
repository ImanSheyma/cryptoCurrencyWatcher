package com.crypto.cryptoCurrencyWatcher.DAO;

import com.crypto.cryptoCurrencyWatcher.entity.Coin;
import com.crypto.cryptoCurrencyWatcher.log.Log;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

@Repository
public class CryptoCurrencyWatcherDAOImpl implements CryptoCurrencyWatcherDAO{
    @Autowired
    private EntityManager manager;
    private List<User> users;

    public CryptoCurrencyWatcherDAOImpl(){
        users = new LinkedList<>();
    }

    @Override
    public Coin getCoinById(int id) {
        Session session = manager.unwrap(Session.class);
        return session.get(Coin.class, id);
    }

    @Override
    public void updateCoinsInfo(List<Coin> coins) throws IOException {
        Session session = manager.unwrap(Session.class);
        for(Coin coin: coins) {
            Coin c = session.get(Coin.class, coin.getId());
            if (c == null)
                session.persist(coin);
            else {
                Query q = session.createQuery("update Coin c set c.price_usd = :price where c.id = :id");
                q.setParameter("price", coin.getPrice_usd());
                q.setParameter("id", coin.getId());
                q.executeUpdate();
                makeLog(coin);
            }
        }
    }

    @Override
    public List<Coin> getAllCoins() {
        Session session = manager.unwrap(Session.class);
        session.createQuery("from Coin").getResultList();
        List<Coin> coins = session.createQuery("from Coin").getResultList();
        return coins;
    }

    @Override
    public String logPriceChange(String name, String symbol) {
        Session session = manager.unwrap(Session.class);
        Query q = session.createQuery("from Coin c where c.symbol = :symbol");
        q.setParameter("symbol", symbol);
        List<Coin> coinList = q.getResultList();
        if(coinList == null && coinList.isEmpty()) return "logger not registered";
        Coin coin = coinList.get(0);

        User user = new User(name, coin);
        users.add(user);

        return "logger registered";
    }

    public void makeLog(Coin coin) throws IOException {
        List<User> del = new ArrayList<>();
        for(User user: users){
            double p;
            if(coin.getId() != user.getCoin().getId()) continue;
            double newPrice = coin.getPrice_usd();
            if((p = Math.abs(user.getCoin().getPrice_usd() / newPrice - 1)) > 0.01){
                Log log = new Log("log.txt");
                log.getLogger().setLevel(Level.WARNING);
                log.getLogger().info("symbol: "+user.getCoin().getSymbol()+
                        "name: " + user.getName() + "chg: " + p + "%");
                log.getLogger().warning("symbol: "+user.getCoin().getSymbol()+
                        "name: " + user.getName() + "chg: " + p + "%");
                del.add(user);
            }
        }
        for(User user: del) users.remove(user);
    }

    private class User{
        private String name;
        private Coin coin;

        public User(String name, Coin coin){
            this.name = name;
            this.coin = coin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coin getCoin() {
            return coin;
        }

        public void setCoin(Coin coin) {
            this.coin = coin;
        }
    }
}
