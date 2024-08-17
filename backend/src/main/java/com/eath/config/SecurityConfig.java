package com.eath.config;

import com.eath.dao.RoleRepository;
import com.eath.dao.UserRepository;
import com.eath.Service.Implement.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl(userRepository, roleRepository, passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/personnes/**").permitAll()
                                .requestMatchers("/api/utilisateurs/**").permitAll()
                                .requestMatchers("/api/administrateurs/**").hasRole("ADMIN")
                                .requestMatchers("/api/prodIngredients/**").permitAll()
                                .requestMatchers("/api/historique-scans/**").permitAll()
                                .requestMatchers("/api/types-produits/**").permitAll()
                                .requestMatchers("/api/normes-halal/**").permitAll()
                                .requestMatchers("/api/produits/**").permitAll()
                                .requestMatchers("/api/ingredients/**").permitAll()
                                .requestMatchers("/api/produits-ingredients/**").permitAll()
                                .requestMatchers("/api/allergenes/**").permitAll()
                                .requestMatchers("/api/substances-nocives/**").permitAll()
                                .requestMatchers("/api/informations-nutritionnelles/**").permitAll()
                                .requestMatchers("/api/commentaires/**").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/resultats-analyse/**").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}
