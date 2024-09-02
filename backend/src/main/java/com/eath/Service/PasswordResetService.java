package com.eath.Service;

public interface PasswordResetService {

    void initiatePasswordReset(String email);

    void resetPassword(String token, String newPassword);
}
