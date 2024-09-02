package com.eath.web;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email; // Pour initier la réinitialisation
    private String token; // Pour réinitialiser le mot de passe
    private String newPassword; // Pour réinitialiser le mot de passe
}
