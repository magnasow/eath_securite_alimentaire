package com.eath.Service.Implement;

import com.eath.Service.ProdIngredientsService;
import com.eath.dao.ProdIngredientsRepository;
import com.eath.entite.Produits_Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdIngredientsServiceImpl implements ProdIngredientsService {

    private final ProdIngredientsRepository prodIngredientsRepository;

    @Autowired
    public ProdIngredientsServiceImpl(ProdIngredientsRepository prodIngredientsRepository) {
        this.prodIngredientsRepository = prodIngredientsRepository;
    }

    @Override
    public Produits_Ingredients addProdIngredients(Produits_Ingredients prdingredient) {
        return prodIngredientsRepository.save(prdingredient);
    }

    @Override
    public List<Produits_Ingredients> getAllProdIngredients() {
        return prodIngredientsRepository.findAll();
    }

    @Override
    public Produits_Ingredients updateProdIngredients(Integer idProduitIngredient, Produits_Ingredients prdingredient) {
        Produits_Ingredients existingPers = getOneProdIngredients(idProduitIngredient);
        existingPers.setQuantite(prdingredient.getQuantite());
        existingPers.setDateCreation(prdingredient.getDateCreation());
        existingPers.setDateModification(prdingredient.getDateModification());
        return prodIngredientsRepository.save(existingPers);
    }

    @Override
    public Produits_Ingredients getOneProdIngredients(Integer idProduitIngredient) {
        return prodIngredientsRepository.findById(idProduitIngredient).orElseThrow(()->
                new RuntimeException("Le produit recherché n'existe pas"));
    }
}
