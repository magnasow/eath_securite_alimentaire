package com.eath.dao;

import com.eath.entite.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
