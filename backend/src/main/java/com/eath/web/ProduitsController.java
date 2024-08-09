package com.eath.web;

import com.eath.Service.Implement.ProduitsServiceImpl;
import com.eath.entite.Produits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produits")
public class ProduitsController {
    private final ProduitsServiceImpl produitsService;

    @PostMapping("/addProduits")
    public ResponseEntity<Produits> addProduits(@RequestBody Produits produits) {
        Produits prod = produitsService.addProduits(produits);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @GetMapping("/listProduits")
    public ResponseEntity<List<Produits>> listProduits() {
        return ResponseEntity.ok(produitsService.getAllProduits());
    }

    @PutMapping("/updateProduits/{idProduit}")
    public ResponseEntity<Produits> updateProduits(@PathVariable Integer idProduit, @RequestBody Produits produits) {
        Produits prod = produitsService.updateProduits(idProduit, produits);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }



    @GetMapping("/getOneProduits/{idProduit}")
    public ResponseEntity<Produits> getOneProduits(@PathVariable Integer idProduit) {
        return ResponseEntity.ok(produitsService.getOneProduits(idProduit));
    }
}
