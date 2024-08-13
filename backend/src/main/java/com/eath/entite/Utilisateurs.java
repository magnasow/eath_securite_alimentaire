package com.eath.entite;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "utilisateur")
public class Utilisateurs extends Personne {

    @Column(name = "preference")
    private String preference;

    @Column(name = "niveau_abonnement")
    private String niveauAbonnement;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;

    // Getters and Setters
    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getNiveauAbonnement() {
        return niveauAbonnement;
    }

    public void setNiveauAbonnement(String niveauAbonnement) {
        this.niveauAbonnement = niveauAbonnement;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }
}
