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

    private final PersonneRepository personneRepository; // Assurez-vous d'avoir un repository pour Personne

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
        if (personneRepository.existsById(idPersonne)) {
            personne.setIdPersonne(idPersonne);
            return personneRepository.save(personne);
        } else {
            throw new RuntimeException("Personne not found with id " + idPersonne);
        }
    }

    @Override
    public void deletePersonne(Integer idPersonne) {
        if (personneRepository.existsById(idPersonne)) {
            personneRepository.deleteById(idPersonne);
        } else {
            throw new RuntimeException("Personne not found with id " + idPersonne);
        }
    }

    @Override
    public Personne getOnePersonne(Integer idPersonne) {
        return personneRepository.findById(idPersonne).orElseThrow(() -> new RuntimeException("Personne not found with id " + idPersonne));
    }

    @Override
    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }
}
