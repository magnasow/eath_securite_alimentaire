package com.eath.Service;

import com.eath.entite.Administrateur;

import java.util.List;
import java.util.Optional;

public interface AdministrateurService {

    List<Administrateur> findAll();

    Optional<Administrateur> findById(Integer id);

    Administrateur save(Administrateur administrateur);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
