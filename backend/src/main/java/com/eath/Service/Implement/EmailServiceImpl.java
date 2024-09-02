package com.eath.Service.Implement;

import com.eath.Service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Réinitialisation de mot de passe";
        String resetUrl = "http://localhost:8080/api/password-reset/reset?token=" + token;

        String text = "Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " + resetUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // Pour le débogage
        }
    }
}
