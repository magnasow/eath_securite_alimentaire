package com.eath.dao;

import com.eath.entite.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
}
