package com.eath.Service.Implement;

import com.eath.Service.IPersonneService;
import com.eath.dao.PersonneRepository;
import com.eath.entite.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements IPersonneService {
    private final PersonneRepository personneRepository;
    @Override
    public Personne addPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public List<Personne> getAllPersonne() {
        return personneRepository.findAll();
    }

    @Override
    public Personne updatePersonne(Integer idPersonne, Personne personne) {
        Personne existingpers = getOnePersonne(idPersonne);
        existingpers.setNomPersonne(personne.getNomPersonne());
        existingpers.setPrenomPersonne(personne.getPrenomPersonne());
        existingpers.setMotDePasse(personne.getMotDePasse());
        existingpers.setEmail(personne.getEmail());
        existingpers.setPhotoDeProfil(personne.getPhotoDeProfil());
        existingpers.setDateCreation(personne.getDateCreation());
        existingpers.setTaille(personne.getTaille());

        existingpers.setPoids(personne.getPoids());
        existingpers.setAge(personne.getAge());
        existingpers.setSexe(personne.getSexe());
        existingpers.setConditionsMedicales(personne.getConditionsMedicales());
        return personneRepository.save(existingpers);
    }

    @Override
    public void deletedPersonne(Integer idPersonne) {
        personneRepository.deleteById(idPersonne);
    }
    @Override
    public Personne getOnePersonne(Integer idPersonne) {
        return personneRepository.findById(idPersonne).orElseThrow(()->
                 new RuntimeException("La personne recherché n'exixte pas"));
    }
}
