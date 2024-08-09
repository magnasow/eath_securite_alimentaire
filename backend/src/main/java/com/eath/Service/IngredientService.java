package com.eath.Service;

import com.eath.entite.Ingredient;
import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredients();
    Ingredient getIngredientById(Integer idIngredient);
    Ingredient updateIngredient(Integer idIngredient, Ingredient ingredient);
    void deleteIngredient(Integer idIngredient);
}
