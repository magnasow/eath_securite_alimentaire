package com.eath.Service;

import com.eath.entite.Personne;
import com.eath.entite.Utilisateurs;

import java.util.List;

public interface IUtilisateursService {
    Utilisateurs addUtilisateurs(Utilisateurs utilisateurs);
    List<Utilisateurs> getAllUtilisateurs();
    Utilisateurs updateUtilisateurs(Integer idUtilisateur, Utilisateurs utilisateurs);
    Utilisateurs getOneUtilisateurs(Integer idUtilisateur);

}
