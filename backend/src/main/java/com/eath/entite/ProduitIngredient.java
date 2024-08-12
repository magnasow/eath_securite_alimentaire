package com.eath.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "produits_ingredients")
public class ProduitIngredient {

    @EmbeddedId
    private ProduitIngredientKey id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("idProduit")
    @JoinColumn(name = "id_produit", insertable = false, updatable = false)
    private Produits produit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("idIngredient")
    @JoinColumn(name = "id_ingredient", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column(name = "quantite", nullable = false)
    private Double quantite;

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
    public ProduitIngredientKey getId() { return id; }
    public void setId(ProduitIngredientKey id) { this.id = id; }
    public Produits getProduit() { return produit; }
    public void setProduit(Produits produit) { this.produit = produit; }
    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
    public Double getQuantite() { return quantite; }
    public void setQuantite(Double quantite) { this.quantite = quantite; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
}
