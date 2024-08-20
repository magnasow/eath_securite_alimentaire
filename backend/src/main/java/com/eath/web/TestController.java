package com.eath.web;

import com.eath.Service.UtilisateurAdministrateurVueService;
import com.eath.entite.UtilisateurAdministrateurVue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UtilisateurAdministrateurVueService utilisateurAdministrateurVueService;

    @GetMapping("/findByEmail")
    public ResponseEntity<Optional<UtilisateurAdministrateurVue>> findByEmail(@RequestParam String email) {
        System.out.println("Recherche de l'utilisateur avec l'email: " + email);
        Optional<UtilisateurAdministrateurVue> user = utilisateurAdministrateurVueService.findByEmail(email);
        if (user.isPresent()) {
            System.out.println("Utilisateur trouvé: " + user.get());
        } else {
            System.out.println("Aucun utilisateur trouvé pour cet email.");
        }
        return ResponseEntity.ok(user);
    }

}

