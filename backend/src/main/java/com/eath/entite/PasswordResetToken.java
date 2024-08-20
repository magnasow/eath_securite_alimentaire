package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24; // Token valide pour 24 heures

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Utilisateurs.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Utilisateurs utilisateur;

    private Date expiryDate;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(final String token, final Utilisateurs utilisateur) {
        super();
        this.token = token;
        this.utilisateur = utilisateur;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
