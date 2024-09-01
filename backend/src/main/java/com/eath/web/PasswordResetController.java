package com.eath.web;

import com.eath.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePasswordReset(@RequestParam String email) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.ok("Code de vérification envoyé par e-mail");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String verificationCode,
                                                @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, verificationCode, newPassword);
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
    }
}

