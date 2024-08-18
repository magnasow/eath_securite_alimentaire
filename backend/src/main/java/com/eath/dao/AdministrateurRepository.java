package com.eath.dao;

import com.eath.entite.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {

}
