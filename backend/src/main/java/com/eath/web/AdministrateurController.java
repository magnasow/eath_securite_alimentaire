package com.eath.web;

import com.eath.Service.AdministrateurService;
import com.eath.entite.Administrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurService.getAllAdministrateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Integer id) {
        Administrateur administrateur = administrateurService.getOneAdministrateur(id);
        return ResponseEntity.ok(administrateur);
    }

    @PostMapping
    public Administrateur createAdministrateur(@RequestBody Administrateur administrateur) {
        return administrateurService.addAdministrateur(administrateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Integer id, @RequestBody Administrateur administrateurDetails) {
        Administrateur updatedAdministrateur = administrateurService.updateAdministrateur(id, administrateurDetails);
        return ResponseEntity.ok(updatedAdministrateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Integer id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }
}
