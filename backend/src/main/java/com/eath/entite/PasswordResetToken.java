package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String verificationCode;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateurs utilisateur;

    private Timestamp expiryDate;

    // Constructeurs, getters, et setters

    public PasswordResetToken() {}

    public PasswordResetToken(String token, String verificationCode, Utilisateurs utilisateur) {
        this.token = token;
        this.verificationCode = verificationCode;
        this.utilisateur = utilisateur;
        this.expiryDate = new Timestamp(System.currentTimeMillis() + 3600000); // 1 heure
    }

    // Getters et Setters
}
