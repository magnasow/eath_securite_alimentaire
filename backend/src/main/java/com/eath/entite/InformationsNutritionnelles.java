package com.eath.entite;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "informations_nutritionnelles")
public class InformationsNutritionnelles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_information")
    private Integer idInformation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produits produit;

    @Column(name = "calories", precision = 10, scale = 2)
    private BigDecimal calories;

    @Column(name = "proteines", precision = 10, scale = 2)
    private BigDecimal proteines;

    @Column(name = "graisses", precision = 10, scale = 2)
    private BigDecimal graisses;

    @Column(name = "glucides", precision = 10, scale = 2)
    private BigDecimal glucides;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.dateCreation = now;
        this.dateModification = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModification = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getIdInformation() {
        return idInformation;
    }

    public void setIdInformation(Integer idInformation) {
        this.idInformation = idInformation;
    }

    public Produits getProduit() {
        return produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getProteines() {
        return proteines;
    }

    public void setProteines(BigDecimal proteines) {
        this.proteines = proteines;
    }

    public BigDecimal getGraisses() {
        return graisses;
    }

    public void setGraisses(BigDecimal graisses) {
        this.graisses = graisses;
    }

    public BigDecimal getGlucides() {
        return glucides;
    }

    public void setGlucides(BigDecimal glucides) {
        this.glucides = glucides;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}
