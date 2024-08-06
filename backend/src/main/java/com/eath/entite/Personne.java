package com.eath.entite;

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
    private Timestamp dateCreation;
    private Double taille; // Utilisation de Double pour les valeurs décimales
    private Double poids;  // Utilisation de Double pour les valeurs décimales
    private Integer age;
    private String sexe;

    @Lob
    private String conditionsMedicales;

}
