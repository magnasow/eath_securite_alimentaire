package com.eath.web;

import com.eath.Service.ProduitIngredientService;
import com.eath.entite.ProduitIngredient;
import com.eath.entite.ProduitIngredientKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produit-ingredients")
@RequiredArgsConstructor
public class ProduitIngredientController {

    private final ProduitIngredientService produitIngredientService;

    @PostMapping
    public ResponseEntity<ProduitIngredient> addProduitIngredient(@RequestBody ProduitIngredient produitIngredient) {
        ProduitIngredient createdProduitIngredient = produitIngredientService.addProduitIngredient(produitIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduitIngredient);
    }


    @GetMapping
    public ResponseEntity<List<ProduitIngredient>> getAllProduitIngredients() {
        return ResponseEntity.ok(produitIngredientService.getAllProduitIngredients());
    }

    @GetMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<ProduitIngredient> getProduitIngredientById(@PathVariable Integer idProduit, @PathVariable Integer idIngredient) {
        ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
        return ResponseEntity.ok(produitIngredientService.getProduitIngredientById(id));
    }

    @PutMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<ProduitIngredient> updateProduitIngredient(@PathVariable Integer idProduit,
                                                                     @PathVariable Integer idIngredient,
                                                                     @RequestBody ProduitIngredient produitIngredient) {
        ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
        return ResponseEntity.ok(produitIngredientService.updateProduitIngredient(id, produitIngredient));
    }

    @DeleteMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<Void> deleteProduitIngredient(@PathVariable Integer idProduit, @PathVariable Integer idIngredient) {
        ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
        produitIngredientService.deleteProduitIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
