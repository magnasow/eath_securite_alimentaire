package com.eath.Service;

import com.eath.entite.Utilisateurs;
import java.util.List;

public interface UtilisateursService {
    Utilisateurs addUtilisateur(Utilisateurs utilisateur);
    List<Utilisateurs> getAllUtilisateurs();
    Utilisateurs updateUtilisateur(Integer idUtilisateur, Utilisateurs utilisateur);
    void deleteUtilisateur(Integer idUtilisateur);
    Utilisateurs getOneUtilisateur(Integer idUtilisateur);
}
