package com.eath.dao;

import com.eath.entite.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
}
