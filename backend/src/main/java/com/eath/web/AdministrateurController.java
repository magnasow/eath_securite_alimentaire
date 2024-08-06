package com.eath.web;

import com.eath.entite.Administrateur;
import com.eath.Service.AdministrateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    private final AdministrateurService administrateurService;

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        List<Administrateur> administrateurs = administrateurService.findAll();
        return ResponseEntity.ok(administrateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Integer id) {
        return administrateurService.findById(id)
                .map(administrateur -> ResponseEntity.ok(administrateur))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur createdAdministrateur = administrateurService.save(administrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdministrateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Integer id, @RequestBody Administrateur administrateur) {
        if (!administrateurService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        administrateur.setIdAdministrateur(id);
        Administrateur updatedAdministrateur = administrateurService.save(administrateur);
        return ResponseEntity.ok(updatedAdministrateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Integer id) {
        if (!administrateurService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        administrateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
