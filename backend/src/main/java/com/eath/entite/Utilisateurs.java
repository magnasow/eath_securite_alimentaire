package com.eath.entite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personne", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Personne personne;

    @Column(name = "preference")
    private String preference;

    @Column(name = "niveau_abonnement")
    private String niveauAbonnement;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private Timestamp dateCreation;

    @Column(name = "date_modification")
    private Timestamp dateModification;

    @PrePersist
    protected void onCreate() {
        dateCreation = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = Timestamp.from(Instant.now());
    }
}
