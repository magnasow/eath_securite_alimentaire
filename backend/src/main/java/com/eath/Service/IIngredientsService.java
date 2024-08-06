package com.eath.Service;

import com.eath.entite.Ingredients;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IIngredientsService {
    Ingredients addIngredients(Ingredients ingredients);

    List<Ingredients> getAllIngredients();
    Ingredients updateIngredients(Integer idIngredient, Ingredients ingredients);

    Ingredients getOneIngredients(Integer idIngredient);
}
