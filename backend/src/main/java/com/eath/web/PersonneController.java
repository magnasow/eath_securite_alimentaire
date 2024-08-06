package com.eath.web;

import com.eath.Service.Implement.PersonneServiceImpl;
import com.eath.entite.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personnes")
public class PersonneController {
    private final PersonneServiceImpl personneService;

    @PostMapping("/addPersonne")
    public ResponseEntity<Personne> addPersonne(@RequestBody Personne personne) {
        Personne pers = personneService.addPersonne(personne);
        return ResponseEntity.status(HttpStatus.CREATED).body(pers);
    }

    @GetMapping("/listPersonne")
    public ResponseEntity<List<Personne>> listPersonne() {
        return ResponseEntity.ok(personneService.getAllPersonne());
    }

    @PutMapping("/updatePersonne/{idPersonne}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Integer idPersonne, @RequestBody Personne personne) {
        Personne pers = personneService.updatePersonne(idPersonne, personne);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pers);
    }


    @DeleteMapping("/deletePersonne/{idPersonne}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Integer idPersonne) {
        personneService.deletedPersonne(idPersonne);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOnePersonne/{idPersonne}")
    public ResponseEntity<Personne> getOnePersonne(@PathVariable Integer idPersonne) {
        return ResponseEntity.ok(personneService.getOnePersonne(idPersonne));
    }

}
