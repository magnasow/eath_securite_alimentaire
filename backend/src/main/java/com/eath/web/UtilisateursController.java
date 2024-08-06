package com.eath.web;

import com.eath.entite.Utilisateurs;
import com.eath.Service.IUtilisateursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/personnes")
@RequiredArgsConstructor
public class UtilisateursController {

    private final IUtilisateursService utilisateurService;

    @PostMapping("/addUtilisateurs")
    public ResponseEntity<Utilisateurs> addUtilisateurs(@RequestBody Utilisateurs utilisateurs) {
        Utilisateurs prod = utilisateurService.addUtilisateurs(utilisateurs);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @GetMapping("/listUtilisateurs")
    public ResponseEntity<List<Utilisateurs>> listUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @PutMapping("/updateUtilisateurs/{idUtilisateur}")
    public ResponseEntity<Utilisateurs> updateUtilisateurs(@PathVariable Integer idUtilisateur, @RequestBody Utilisateurs utilisateurs) {
        Utilisateurs prod = utilisateurService.updateUtilisateurs(idUtilisateur, utilisateurs);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @GetMapping("/getOneUtilisateurs/{idUtilisateur}")
    public ResponseEntity<Utilisateurs> getOneUtilisateurs(@PathVariable Integer idUtilisateur) {
        return ResponseEntity.ok(utilisateurService.getOneUtilisateurs(idUtilisateur));
    }
}
