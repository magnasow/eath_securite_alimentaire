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

import java.time.LocalDateTime;
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

        // Générer un token unique
        String token = UUID.randomUUID().toString();

        // Créer un nouvel objet PasswordResetToken
        PasswordResetToken resetToken = new PasswordResetToken(token, user);

        // Définir la date d'expiration du token (par exemple, 1 heure à partir de maintenant)
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        // Enregistrer le token dans la base de données
        tokenRepository.save(resetToken);

        // Envoyer le lien de réinitialisation par e-mail
        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // Trouver le token dans la base de données
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide"));

        // Vérifier si le token a expiré
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expiré");
        }

        // Réinitialiser le mot de passe
        Utilisateurs user = resetToken.getUtilisateur();
        user.setMotDePasse(passwordEncoder.encode(newPassword));
        utilisateursRepository.save(user);

        // Supprimer le token après utilisation
        tokenRepository.delete(resetToken);
    }
}
