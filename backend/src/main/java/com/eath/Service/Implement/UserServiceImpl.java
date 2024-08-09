package com.eath.Service.Implement;

import com.eath.Service.UserService;

import com.eath.entite.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    public User registerUser(User user) {

        return user; // Remplacer par le code réel
    }

    @Override
    public Optional<User> getUserByUsername(String username) {

        return Optional.empty(); // Remplacer par le code réel
    }
}