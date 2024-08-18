package com.eath.web;

import com.eath.entite.Utilisateurs;
import com.eath.Service.UtilisateursService;
import com.eath.exception.UtilisateurNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateursController {

    @Autowired
    private UtilisateursService utilisateursService;

    @PostMapping
    public ResponseEntity<Utilisateurs> creerUtilisateur(@RequestBody Utilisateurs utilisateur) {
        Utilisateurs newUtilisateur = utilisateursService.creerUtilisateur(utilisateur);
        return ResponseEntity.ok(newUtilisateur);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateurs>> getAllUtilisateurs() {
        List<Utilisateurs> utilisateurs = utilisateursService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateurById(@PathVariable Integer id) {
        Optional<Utilisateurs> utilisateur = utilisateursService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'id : " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> updateUtilisateur(@PathVariable Integer id, @RequestBody Utilisateurs utilisateur) {
        Utilisateurs updatedUtilisateur = utilisateursService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateursService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
