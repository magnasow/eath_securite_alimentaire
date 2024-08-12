package com.eath.Service.Implement;

import com.eath.Service.UserService;
import com.eath.dao.UserRepository;
import com.eath.entite.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        // Ajoutez ici la logique pour enregistrer l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        // Ajoutez ici la logique pour récupérer un utilisateur par son nom d'utilisateur
        return userRepository.findByUsername(username);
    }
}
