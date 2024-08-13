package com.eath.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Integer idProduit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_type_produit", nullable = false)
    private TypesProduits typeProduit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
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
    public Integer getIdProduit() { return idProduit; }
    public void setIdProduit(Integer idProduit) { this.idProduit = idProduit; }
    public TypesProduits getTypeProduit() { return typeProduit; }
    public void setTypeProduit(TypesProduits typeProduit) { this.typeProduit = typeProduit; }
    public NormeHalal getNormeHalal() { return normeHalal; }
    public void setNormeHalal(NormeHalal normeHalal) { this.normeHalal = normeHalal; }
    public String getNomProduit() { return nomProduit; }
    public void setNomProduit(String nomProduit) { this.nomProduit = nomProduit; }
    public String getCodeBarre() { return codeBarre; }
    public void setCodeBarre(String codeBarre) { this.codeBarre = codeBarre; }
    public String getDescriptionProduit() { return descriptionProduit; }
    public void setDescriptionProduit(String descriptionProduit) { this.descriptionProduit = descriptionProduit; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public List<ProduitIngredient> getProduitsIngredients() { return produitsIngredients; }
    public void setProduitsIngredients(List<ProduitIngredient> produitsIngredients) { this.produitsIngredients = produitsIngredients; }
}
