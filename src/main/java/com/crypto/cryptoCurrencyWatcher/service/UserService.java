package com.crypto.cryptoCurrencyWatcher.service;

import com.crypto.cryptoCurrencyWatcher.DAO.UserRepository;
import com.crypto.cryptoCurrencyWatcher.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(String name){
        User user = new User();
        user.setUsername(name);
        userRepository.save(user);
        return user;
    }

    public Optional<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }
}
