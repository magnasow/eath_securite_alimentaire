package com.eath.entite;

import com.eath.entite.TypesProduits;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "produits")
public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Integer idProduit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_produit", nullable = false)
    private TypesProduits typeProduit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_norme_halal")
    private NormeHalal normeHalal;

    @Column(name = "nom_produit", nullable = false, length = 255)
    private String nomProduit;

    @Column(name = "code_barre", unique = true, length = 50)
    private String codeBarre;

    @Column(name = "description_produit", columnDefinition = "TEXT")
    private String descriptionProduit;

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

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public TypesProduits getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypesProduits typeProduit) {
        this.typeProduit = typeProduit;
    }

    public NormeHalal getNormeHalal() {
        return normeHalal;
    }

    public void setNormeHalal(NormeHalal normeHalal) {
        this.normeHalal = normeHalal;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
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
