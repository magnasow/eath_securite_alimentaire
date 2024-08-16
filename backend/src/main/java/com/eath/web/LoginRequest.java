package com.eath.web;

import lombok.Data;

import java.util.Set;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String email;  // Ajoutez ce champ pour le mail
    private Set<String> roles; // Ajouter ce champ pour les rôles

}
