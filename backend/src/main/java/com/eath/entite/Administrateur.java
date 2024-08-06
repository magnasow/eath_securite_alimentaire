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
public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrateur")
    private Integer idAdministrateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personne", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Personne personne;

    @Column(name = "niveau_admin", length = 50)
    private String niveauAdmin;

    @Column(name = "privileges", columnDefinition = "TEXT")
    private String privileges;

    @Column(name = "date_creation", updatable = false)
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
