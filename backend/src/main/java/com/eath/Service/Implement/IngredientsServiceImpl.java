package com.eath.Service.Implement;

import com.eath.Service.IIngredientsService;
import com.eath.dao.IngredientsRepository;
import com.eath.entite.Ingredients;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IIngredientsService {
    private final IngredientsRepository ingredientsRepository;

    @Override
    public Ingredients addIngredients(Ingredients ingredients) {
        return ingredientsRepository.save(ingredients);
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @Override
    public Ingredients updateIngredients(Integer idIngredient, Ingredients ingredients) {

        Ingredients existingpers = getOneIngredients(idIngredient);
        existingpers.setNomIngredient(ingredients.getNomIngredient());
        existingpers.setDescriptionIngredient(ingredients.getDescriptionIngredient());
        existingpers.setDateCreation(ingredients.getDateCreation());
        return ingredientsRepository.save(existingpers);
    }

    @Override
    public Ingredients getOneIngredients(Integer idIngredient) {
        return ingredientsRepository.findById(idIngredient).orElseThrow(()->
                new RuntimeException("L'ingredient recherché n'exixte pas"));
    }
}
