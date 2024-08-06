package com.eath.Service;

import com.eath.entite.Administrateur;

import java.util.List;
import java.util.Optional;

public interface AdministrateurService {
    List<Administrateur> findAll();
    Optional<Administrateur> findById(Integer id);
    Administrateur save(Administrateur administrateur);
    void deleteById(Integer id);

    boolean existsById(Integer id);
}
