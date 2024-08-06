package com.eath.entite;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Produits_Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit_ingredient")
    private Integer idProduitIngredient;

    @Column(name = "quantite", nullable = false)
    private Double quantite;

    @Column(name = "date_creation", updatable = false)
    private Timestamp dateCreation;

    @Column(name = "date_modification")
    private Timestamp dateModification;

    // Getters et Setters
    public Integer getIdProduitIngredient() {
        return idProduitIngredient;
    }

    public void setIdProduitIngredient(Integer idProduitIngredient) {
        this.idProduitIngredient = idProduitIngredient;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }
}
