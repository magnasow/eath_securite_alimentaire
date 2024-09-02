package com.eath.Service.Implement;

import com.eath.Service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://yourdomain.com/api/password-reset/reset?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Réinitialisation du mot de passe");
        message.setText("Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " + resetUrl);
        mailSender.send(message);
    }
}
