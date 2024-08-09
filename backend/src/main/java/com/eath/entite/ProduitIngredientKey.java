package com.eath.entite;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProduitIngredientKey implements Serializable {

    private Integer idProduit;
    private Integer idIngredient;

    // Default constructor
    public ProduitIngredientKey() {}

    public ProduitIngredientKey(Integer idProduit, Integer idIngredient) {
        this.idProduit = idProduit;
        this.idIngredient = idIngredient;
    }

    // Getters and Setters
    public Integer getIdProduit() { return idProduit; }
    public void setIdProduit(Integer idProduit) { this.idProduit = idProduit; }
    public Integer getIdIngredient() { return idIngredient; }
    public void setIdIngredient(Integer idIngredient) { this.idIngredient = idIngredient; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitIngredientKey that = (ProduitIngredientKey) o;
        return Objects.equals(idProduit, that.idProduit) && Objects.equals(idIngredient, that.idIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit, idIngredient);
    }
}
