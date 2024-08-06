package com.eath.Service;

import com.eath.entite.Produits_Ingredients;
import com.eath.entite.Utilisateurs;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProdIngredientsService {
    Produits_Ingredients addProdIngredients(Produits_Ingredients prdingredient);
    List<Produits_Ingredients> getAllProdIngredients();
    Produits_Ingredients updateProdIngredients(Integer idProduitIngredient, Produits_Ingredients prdingredient);
    Produits_Ingredients getOneProdIngredients(Integer idProduitIngredient);
}
