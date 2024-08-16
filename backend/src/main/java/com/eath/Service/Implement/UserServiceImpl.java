package com.eath.Service.Implement;

import com.eath.Service.UserService;
import com.eath.dao.RoleRepository;
import com.eath.dao.UserRepository;
import com.eath.entite.Role;
import com.eath.entite.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                Set<Role> persistedRoles = user.getRoles().stream()
                        .map(role -> roleRepository.findByName(role.getName())
                                .orElseGet(() -> roleRepository.save(new Role(role.getName()))))
                        .collect(Collectors.toSet());
                user.setRoles(persistedRoles);
            }

            return userRepository.save(user);
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Failed to register user", e);
        }
    }


    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Hibernate.initialize(user.get().getRoles());
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID must be provided for update.");
        }

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user.getId()));

        existingUser.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setEmail(user.getEmail());

        if (user.getRoles() != null) {
            Set<Role> persistedRoles = user.getRoles().stream()
                    .map(role -> getOrCreateRole(role.getName()))
                    .collect(Collectors.toSet());
            existingUser.setRoles(persistedRoles);
        }

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user.get();
    }

    private Role getOrCreateRole(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });
    }
}
