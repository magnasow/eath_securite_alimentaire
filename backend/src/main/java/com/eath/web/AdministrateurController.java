package com.eath.web;

import com.eath.dao.AdministrateurRepository;
import com.eath.entite.Administrateur;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    private final AdministrateurRepository administrateurRepository;

    @GetMapping
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Integer id) {
        Optional<Administrateur> administrateur = administrateurRepository.findById(id);
        return administrateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur savedAdministrateur = administrateurRepository.save(administrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdministrateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Integer id, @RequestBody Administrateur administrateurDetails) {
        Optional<Administrateur> administrateur = administrateurRepository.findById(id);
        if (administrateur.isPresent()) {
            Administrateur a = administrateur.get();
            a.setNiveauAdmin(administrateurDetails.getNiveauAdmin());
            a.setPrivileges(administrateurDetails.getPrivileges());
            return ResponseEntity.ok(administrateurRepository.save(a));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdministrateur(@PathVariable Integer id) {
        try {
            if (administrateurRepository.existsById(id)) {
                administrateurRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erreur de contrainte d'intégrité.");
        }
    }
}
