package com.eath.dao;

import com.eath.entite.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {

    // Déclaration de la méthode pour trouver un utilisateur par email
    Optional<Utilisateurs> findByEmail(String email);

    // Déclaration de la méthode pour vérifier si un utilisateur existe par email
    boolean existsByEmail(String email);
}
