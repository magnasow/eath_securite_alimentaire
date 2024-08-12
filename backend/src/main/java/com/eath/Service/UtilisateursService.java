package com.eath.Service;


import com.eath.entite.Utilisateurs;

import java.util.List;

public interface UtilisateursService {
    List<Utilisateurs> getAllUtilisateurs();
    Utilisateurs getUtilisateursById(Integer id);
    Utilisateurs saveUtilisateurs(Utilisateurs utilisateurs);
    void deleteUtilisateurs(Integer id);
}
