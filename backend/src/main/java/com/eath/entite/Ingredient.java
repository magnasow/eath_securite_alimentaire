package com.eath.entite;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private Integer idIngredient;

    @NotNull
    @Size(max = 255)
    @Column(name = "nom_ingredient", nullable = false, length = 255)
    private String nomIngredient;

    @Column(name = "description_ingredient", columnDefinition = "TEXT")
    private String descriptionIngredient;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_norme_halal")
    private NormeHalal normeHalal;

    @ManyToMany
    @JoinTable(
            name = "ingredients_substances_nocives",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_substance")
    )
    private Set<SubstanceNocive> substancesNocives = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ingredients_allergenes",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_allergene")
    )
    private Set<Allergene> allergenes = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitIngredient> produitsIngredients = new ArrayList<>();

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
    public Integer getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Integer idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public String getDescriptionIngredient() {
        return descriptionIngredient;
    }

    public void setDescriptionIngredient(String descriptionIngredient) {
        this.descriptionIngredient = descriptionIngredient;
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

    public NormeHalal getNormeHalal() {
        return normeHalal;
    }

    public void setNormeHalal(NormeHalal normeHalal) {
        this.normeHalal = normeHalal;
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public List<ProduitIngredient> getProduitsIngredients() {
        return produitsIngredients;
    }

    public void setProduitsIngredients(List<ProduitIngredient> produitsIngredients) {
        this.produitsIngredients = produitsIngredients;
    }

    public Set<SubstanceNocive> getSubstancesNocives() {
        return substancesNocives;
    }

    public void setSubstancesNocives(Set<SubstanceNocive> substancesNocives) {
        this.substancesNocives = substancesNocives;
    }
}
