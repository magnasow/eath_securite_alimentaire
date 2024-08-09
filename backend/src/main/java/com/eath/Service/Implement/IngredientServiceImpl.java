package com.eath.Service.Implement;

import com.eath.Service.IngredientService;
import com.eath.dao.IngredientRepository;
import com.eath.entite.Ingredient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Integer idIngredient) {
        return ingredientRepository.findById(idIngredient)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public Ingredient updateIngredient(Integer idIngredient, Ingredient ingredient) {
        Ingredient existingIngredient = getIngredientById(idIngredient);
        existingIngredient.setNomIngredient(ingredient.getNomIngredient());
        existingIngredient.setDescriptionIngredient(ingredient.getDescriptionIngredient());
        existingIngredient.setNormeHalal(ingredient.getNormeHalal());
        return ingredientRepository.save(existingIngredient);
    }

    @Override
    public void deleteIngredient(Integer idIngredient) {
        ingredientRepository.deleteById(idIngredient);
    }
}
