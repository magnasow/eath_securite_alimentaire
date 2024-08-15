package com.eath.Service;

import com.eath.entite.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> getUserByUsername(String username);
}
