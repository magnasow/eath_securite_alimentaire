package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
@DiscriminatorValue("UTILISATEUR")
@Data
public class Utilisateurs extends Personne {

    @Column(name = "preference")
    private String preference;

    @Column(name = "niveau_abonnement")
    private String niveauAbonnement;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;


}
