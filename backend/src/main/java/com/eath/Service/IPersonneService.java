package com.eath.Service;

import com.eath.entite.Personne;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPersonneService {
    Personne addPersonne(Personne personne);

    List<Personne> getAllPersonne();
    Personne updatePersonne(Integer idPersonne, Personne personne);
    void  deletedPersonne(Integer idPersonne);
    Personne getOnePersonne(Integer idPersonne);
}

