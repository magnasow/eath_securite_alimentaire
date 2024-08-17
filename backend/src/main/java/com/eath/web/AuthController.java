package com.eath.web;

import com.eath.Service.UserService;
import com.eath.dao.RoleRepository;
import com.eath.entite.Role;
import com.eath.entite.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Tentative d'authentification pour l'utilisateur : {}", loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Authentification réussie pour l'utilisateur : {}", loginRequest.getUsername());
            return ResponseEntity.ok("Authentification réussie");
        } catch (BadCredentialsException e) {
            logger.error("Échec de l'authentification pour l'utilisateur : {}", loginRequest.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encodage du mot de passe
            user.setEmail(registerRequest.getEmail());

            Set<Role> roles = registerRequest.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseGet(() -> {
                                Role newRole = new Role();
                                newRole.setName(roleName);
                                return roleRepository.save(newRole);
                            }))
                    .collect(Collectors.toSet());

            user.setRoles(roles);

            userService.registerUser(user);
            return ResponseEntity.ok("Utilisateur enregistré avec succès");
        } catch (Exception e) {
            logger.error("Erreur lors de l'enregistrement de l'utilisateur", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'utilisateur");
        }
    }
}
