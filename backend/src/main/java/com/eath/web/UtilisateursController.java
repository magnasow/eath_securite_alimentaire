package com.eath.web;

import com.eath.Service.UtilisateursService;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateursController {

    private final UtilisateursService utilisateursService;

    @Autowired
    public UtilisateursController(UtilisateursService utilisateursService) {
        this.utilisateursService = utilisateursService;
    }

    @GetMapping
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateursById(@PathVariable Integer id) {
        Utilisateurs utilisateurs = utilisateursService.getUtilisateursById(id);
        return utilisateurs != null ? ResponseEntity.ok(utilisateurs) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Utilisateurs> createUtilisateurs(@Validated @RequestBody Utilisateurs utilisateurs) {
        Utilisateurs createdUtilisateurs = utilisateursService.saveUtilisateurs(utilisateurs);
        return ResponseEntity.status(201).body(createdUtilisateurs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateurs(@PathVariable Integer id) {
        utilisateursService.deleteUtilisateurs(id);
        return ResponseEntity.noContent().build();
    }
}
