package com.eath.dao;

import com.eath.entite.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {

}
