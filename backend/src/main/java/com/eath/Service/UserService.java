package com.eath.Service;

import com.eath.entite.User;

public interface UserService {
    User saveUser(User user);
    User saveAdmin(User user); // Ajoutez cette méthode

    User findByUsername(String username);
}
