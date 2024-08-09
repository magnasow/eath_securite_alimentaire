package com.eath.Service;

import com.eath.entite.Personne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonneService {
    Personne addPersonne(Personne personne);
    List<Personne> getAllPersonne();
    Personne updatePersonne(Integer idPersonne, Personne personne);
    void deletePersonne(Integer idPersonne);
    Personne getOnePersonne(Integer idPersonne);
    Personne save(Personne personne); // Méthode à implémenter
}
