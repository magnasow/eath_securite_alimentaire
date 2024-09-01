package com.eath.Service.Implement;

import com.eath.Service.EmailService;
import com.eath.Service.PasswordResetService;
import com.eath.dao.PasswordResetTokenRepository;
import com.eath.entite.PasswordResetToken;
import com.eath.entite.Utilisateurs;
import com.eath.exception.UtilisateurNotFoundException;
import com.eath.dao.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Override
    public void initiatePasswordReset(String email) {
        // Rechercher l'utilisateur par e-mail
        Utilisateurs user = utilisateursRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé"));

        // Générer un token unique et un code de vérification
        String token = UUID.randomUUID().toString();
        String verificationCode = generateVerificationCode();

        // Créer un nouvel objet PasswordResetToken
        PasswordResetToken resetToken = new PasswordResetToken(token, verificationCode, user);

        // Enregistrer le token dans la base de données
        tokenRepository.save(resetToken);

        // Envoyer le code de vérification par e-mail
        emailService.sendPasswordResetEmail(user.getEmail(), verificationCode);
    }

    private String generateVerificationCode() {
        // Génère un code de 3 chiffres
        return String.format("%03d", new Random().nextInt(1000));
    }

    @Override
    public void resetPassword(String token, String verificationCode, String newPassword) {
        // Trouver le token dans la base de données
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide"));

        // Vérifier si le token a expiré
        if (resetToken.getExpiryDate().before(new Date())) {
            throw new RuntimeException("Token expiré");
        }

        // Vérifier le code de vérification
        if (!resetToken.getVerificationCode().equals(verificationCode)) {
            throw new RuntimeException("Code de vérification invalide");
        }

        // Réinitialiser le mot de passe
        Utilisateurs user = resetToken.getUtilisateur();
        user.setMotDePasse(passwordEncoder.encode(newPassword));
        utilisateursRepository.save(user);

        // Supprimer le token après utilisation
        tokenRepository.delete(resetToken);
    }
}
