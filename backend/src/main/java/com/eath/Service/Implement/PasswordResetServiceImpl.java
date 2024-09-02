package com.eath.Service.Implement;

import com.eath.Service.PasswordResetService;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Override
    public void initiatePasswordReset(String email) {
        // Implémentez la logique pour initier la réinitialisation du mot de passe
        // Envoyer un e-mail avec le token de réinitialisation
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // Implémentez la logique pour réinitialiser le mot de passe
        // Vérifiez le token et mettez à jour le mot de passe
    }
}
