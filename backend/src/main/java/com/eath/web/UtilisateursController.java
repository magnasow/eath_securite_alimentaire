package com.eath.web;

import com.eath.Service.UtilisateursService;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateursController {

    @Autowired
    private UtilisateursService utilisateurService;

    @GetMapping
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateurById(@PathVariable Integer id) {
        Utilisateurs utilisateur = utilisateurService.getOneUtilisateur(id);
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping
    public ResponseEntity<Utilisateurs> createUtilisateur(@RequestBody Utilisateurs utilisateur) {
        // On initialise les champs non fournis avec des valeurs par défaut
        utilisateur.setDateCreation(new Timestamp(System.currentTimeMillis()));
        utilisateur.setDateModification(new Timestamp(System.currentTimeMillis()));

        Utilisateurs savedUtilisateur = utilisateurService.addUtilisateur(utilisateur);
        return new ResponseEntity<>(savedUtilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> updateUtilisateur(@PathVariable Integer id, @RequestBody Utilisateurs utilisateurDetails) {
        Utilisateurs updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateurDetails);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
