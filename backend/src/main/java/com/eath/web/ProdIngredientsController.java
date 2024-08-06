package com.eath.web;

import com.eath.Service.ProdIngredientsService;
import com.eath.entite.Produits_Ingredients;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prodIngredients") // Changer le mapping pour refléter la ressource gérée
public class ProdIngredientsController {

    private final ProdIngredientsService prodIngredientsService;

    @PostMapping("/add")
    public ResponseEntity<Produits_Ingredients> addProdIngredients(@RequestBody Produits_Ingredients produits_ingredients) {
        Produits_Ingredients prod = prodIngredientsService.addProdIngredients(produits_ingredients);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Produits_Ingredients>> listProdIngredients() {
        return ResponseEntity.ok(prodIngredientsService.getAllProdIngredients());
    }

    @PutMapping("/update/{idProduitIngredient}") // Assurez-vous que l'URL corresponde au PathVariable
    public ResponseEntity<Produits_Ingredients> updateProdIngredients(@PathVariable Integer idProduitIngredient, @RequestBody Produits_Ingredients produits_ingredients) {
        Produits_Ingredients prod = prodIngredientsService.updateProdIngredients(idProduitIngredient, produits_ingredients);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @GetMapping("/getOne/{idProduitIngredient}") // Assurez-vous que l'URL corresponde au PathVariable
    public ResponseEntity<Produits_Ingredients> getOneProdIngredients(@PathVariable Integer idProduitIngredient) {
        return ResponseEntity.ok(prodIngredientsService.getOneProdIngredients(idProduitIngredient));
    }
}
