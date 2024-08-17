package com.eath.Service.Implement;

import com.eath.Service.UserService;
import com.eath.dao.RoleRepository;
import com.eath.dao.UserRepository;
import com.eath.entite.Role;
import com.eath.entite.User;
import com.eath.exception.EmailAlreadyInUseException;
import com.eath.exception.UserNotFoundException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        logger.info("Attempting to register user with email: {}", user.getEmail());

        if (user.getRoles() != null && user.getRoles().size() > 1) {
            throw new IllegalArgumentException("Un utilisateur ne peut avoir qu'un seul rôle.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyInUseException("Email already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<Role> persistedRoles = user.getRoles().stream()
                    .map(role -> roleRepository.findByName(role.getName())
                            .orElseGet(() -> roleRepository.save(new Role(role.getName()))))
                    .collect(Collectors.toSet());
            user.setRoles(persistedRoles);
        }

        User savedUser = userRepository.save(user);
        logger.info("User registered with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        logger.info("Fetching user by username: {}", username);

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Hibernate.initialize(user.get().getRoles());
            logger.info("User found with username: {}", username);
        } else {
            logger.warn("User not found with username: {}", username);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        logger.info("Attempting to update user with ID: {}", user.getId());

        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID must be provided for update.");
        }

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + user.getId()));

        if (userRepository.existsByEmail(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyInUseException("Email already in use.");
        }

        existingUser.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setEmail(user.getEmail());

        if (user.getRoles() != null) {
            if (user.getRoles().size() > 1) {
                throw new IllegalArgumentException("Un utilisateur ne peut avoir qu'un seul rôle.");
            }
            Set<Role> persistedRoles = user.getRoles().stream()
                    .map(role -> getOrCreateRole(role.getName()))
                    .collect(Collectors.toSet());
            existingUser.setRoles(persistedRoles);
        }

        User updatedUser = userRepository.save(existingUser);
        logger.info("User updated with ID: {}", updatedUser.getId());
        return updatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
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
