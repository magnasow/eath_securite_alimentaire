package com.eath.Service;


public interface PasswordResetService {
    void initiatePasswordReset(String email);
    void resetPassword(String token, String verificationCode, String newPassword);  // Assurez-vous que les paramètres sont corrects
}

