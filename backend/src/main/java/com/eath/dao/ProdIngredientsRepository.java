package com.eath.dao;

import com.eath.entite.Produits;
import com.eath.entite.Produits_Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdIngredientsRepository extends JpaRepository<Produits_Ingredients, Integer> {
}
