package com.eath.web;

import com.eath.Service.Implement.IngredientsServiceImpl;
import com.eath.entite.Ingredients;
import com.eath.entite.Produits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personnes")
public class IngredientsController {
    private final IngredientsServiceImpl ingredientsService;
    @PostMapping("/addIngredients")
    public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients ingredients) {
        Ingredients ing = ingredientsService.addIngredients(ingredients);
        return ResponseEntity.status(HttpStatus.CREATED).body(ing);
    }
    @GetMapping("/listIngredients")
    public ResponseEntity<List<Ingredients>> listIngredients() {
        return ResponseEntity.ok(ingredientsService.getAllIngredients());
    }

    @PutMapping("/updateIngredients/{idIngredient}")
    public ResponseEntity<Ingredients> updateIngredients(@PathVariable Integer idIngredient, @RequestBody Ingredients ingredients) {
        Ingredients ing = ingredientsService.updateIngredients(idIngredient, ingredients);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ing);
    }



    @GetMapping("/getOneIngredients/{idIngredient}")
    public ResponseEntity<Ingredients> getOneIngredients(@PathVariable Integer idIngredient) {
        return ResponseEntity.ok(ingredientsService.getOneIngredients(idIngredient));
    }
}
