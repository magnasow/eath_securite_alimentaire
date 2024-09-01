package com.eath.web;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email; // pour initier la réinitialisation
    private String newPassword; // pour réinitialiser le mot de passe
}
