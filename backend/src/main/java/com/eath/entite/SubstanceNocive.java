package com.eath.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "substances_nocives")
public class SubstanceNocive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_substance")
    private Integer idSubstance;

    @Column(name = "nom_substance", nullable = false, length = 255)
    private String nomSubstance;

    @Column(name = "type_substance", length = 50)
    private String typeSubstance;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    @ManyToMany(mappedBy = "substancesNocives")
    private Set<Ingredient> ingredients = new HashSet<>();

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
    public Integer getIdSubstance() { return idSubstance; }
    public void setIdSubstance(Integer idSubstance) { this.idSubstance = idSubstance; }
    public String getNomSubstance() { return nomSubstance; }
    public void setNomSubstance(String nomSubstance) { this.nomSubstance = nomSubstance; }
    public String getTypeSubstance() { return typeSubstance; }
    public void setTypeSubstance(String typeSubstance) { this.typeSubstance = typeSubstance; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public Set<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(Set<Ingredient> ingredients) { this.ingredients = ingredients; }
}
