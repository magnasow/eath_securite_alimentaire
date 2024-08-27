package com.eath.Service;

public interface PasswordResetService {
    void initiatePasswordReset(String username);
    void resetPassword(String token, String newPassword);
}
