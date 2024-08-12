package com.eath.web;

import com.eath.dao.AdministrateurRepository;
import com.eath.entite.Administrateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurRepository administrateurRepository;

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
    public Administrateur createAdministrateur(@RequestBody Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
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
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Integer id) {
        if (administrateurRepository.existsById(id)) {
            administrateurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

