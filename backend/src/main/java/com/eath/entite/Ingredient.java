package com.eath.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private Integer idIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_norme_halal")
    private NormeHalal normeHalal;

    @Column(name = "nom_ingredient", nullable = false, length = 255)
    private String nomIngredient;

    @Column(name = "description_ingredient", columnDefinition = "TEXT")
    private String descriptionIngredient;

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

    // Getters and setters
    public Integer getIdIngredient() { return idIngredient; }
    public void setIdIngredient(Integer idIngredient) { this.idIngredient = idIngredient; }
    public NormeHalal getNormeHalal() { return normeHalal; }
    public void setNormeHalal(NormeHalal normeHalal) { this.normeHalal = normeHalal; }
    public String getNomIngredient() { return nomIngredient; }
    public void setNomIngredient(String nomIngredient) { this.nomIngredient = nomIngredient; }
    public String getDescriptionIngredient() { return descriptionIngredient; }
    public void setDescriptionIngredient(String descriptionIngredient) { this.descriptionIngredient = descriptionIngredient; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
}
