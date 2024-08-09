package com.eath.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "norme_halal")
public class NormeHalal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_norme_halal")
    private Integer idNormeHalal;

    @Column(name = "description_norme_halal", columnDefinition = "TEXT")
    private String descriptionNormeHalal;

    @Column(name = "est_halal")
    private Boolean estHalal;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getIdNormeHalal() {
        return idNormeHalal;
    }

    public void setIdNormeHalal(Integer idNormeHalal) {
        this.idNormeHalal = idNormeHalal;
    }

    public String getDescriptionNormeHalal() {
        return descriptionNormeHalal;
    }

    public void setDescriptionNormeHalal(String descriptionNormeHalal) {
        this.descriptionNormeHalal = descriptionNormeHalal;
    }

    public Boolean getEstHalal() {
        return estHalal;
    }

    public void setEstHalal(Boolean estHalal) {
        this.estHalal = estHalal;
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
