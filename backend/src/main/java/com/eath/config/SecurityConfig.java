package com.eath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration // Indique que cette classe contient des configurations pour Spring.
@EnableWebSecurity // Active la sécurité web pour l'application.
public class SecurityConfig {

    // Déclare un bean pour l'encodage des mots de passe avec BCrypt.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Déclare un service pour gérer les détails des utilisateurs en mémoire.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user") // Nom d'utilisateur "user"
                .password(passwordEncoder().encode("password")) // Mot de passe encodé "password"
                .roles("USER") // Rôle attribué à l'utilisateur
                .build();

        UserDetails admin = User.builder()
                .username("admin") // Nom d'utilisateur "admin"
                .password(passwordEncoder().encode("admin")) // Mot de passe encodé "admin"
                .roles("ADMIN") // Rôle attribué à l'administrateur
                .build();

        // Stocke les utilisateurs dans une mémoire temporaire.
        return new InMemoryUserDetailsManager(user, admin);
    }

    // Configure la sécurité des requêtes HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactive la protection CSRF.
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/**").permitAll() // Autorise l'accès à l'API d'authentification.
                                .requestMatchers("/api/personnes/**").permitAll() // Autorise l'accès à l'API des personnes.
                                .requestMatchers("/api/utilisateurs/**").permitAll() // Autorise l'accès à l'API des utilisateurs.
                                .requestMatchers("/api/administrateurs/**").hasRole("ADMIN") // Accès limité aux administrateurs.
                                .requestMatchers("/api/prodIngredients/**").permitAll() // Autorise l'accès à l'API des ingrédients de produits.
                                .requestMatchers("/api/historique-scans/**").permitAll() // Autorise l'accès à l'API de l'historique des scans.
                                .requestMatchers("/api/types-produits/**").permitAll() // Autorise l'accès à l'API des types de produits.
                                .requestMatchers("/api/normes-halal/**").permitAll() // Autorise l'accès à l'API des normes halal.
                                .requestMatchers("/api/produits/**").permitAll() // Autorise l'accès à l'API des produits.
                                .requestMatchers("/api/ingredients/**").permitAll() // Autorise l'accès à l'API des ingrédients.
                                .requestMatchers("/api/produits-ingredients/**").permitAll() // Autorise l'accès à l'API des relations produits-ingrédients.
                                .requestMatchers("/api/allergenes/**").permitAll() // Autorise l'accès à l'API des allergènes.
                                .requestMatchers("/api/substances-nocives/**").permitAll() // Autorise l'accès à l'API des substances nocives.
                                .requestMatchers("/api/informations-nutritionnelles/**").permitAll() // Autorise l'accès à l'API des informations nutritionnelles.
                                .requestMatchers("/api/commentaires/**").permitAll() // Autorise l'accès à l'API des commentaires.
                                .requestMatchers("/api/resultats-analyse/**").permitAll() // Autorise l'accès à l'API des résultats d'analyse.
                                .anyRequest().authenticated() // Tout autre accès nécessite une authentification.
                )
                .httpBasic(); // Utilise l'authentification HTTP Basic.

        return http.build(); // Retourne la configuration de la chaîne de filtres de sécurité.
    }

    // Configure le gestionnaire d'authentification pour l'application.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
