package com.eath.entite;

import com.eath.entite.Administrateur;
import com.eath.entite.Utilisateurs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonne;

    private String nomPersonne;
    private String prenomPersonne;
    private String motDePasse;
    private String email;
    private String photoDeProfil;

    @Column(name = "date_creation", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateCreation;

    private Double taille;
    private Double poids;
    private Integer age;
    private String sexe;

    @Lob
    private String conditionsMedicales;

    @OneToOne(mappedBy = "personne", orphanRemoval = true)
    private Administrateur administrateur;
    @OneToOne(mappedBy = "personne",  orphanRemoval = true)
    private Utilisateurs utilisateurs;
    @PrePersist
    protected void onCreate() {
        dateCreation = new Timestamp(System.currentTimeMillis());
    }
}
