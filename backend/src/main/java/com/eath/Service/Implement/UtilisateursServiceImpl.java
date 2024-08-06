package com.eath.Service.Implement;

import com.eath.Service.IUtilisateursService;
import com.eath.dao.PersonneRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Personne;
import com.eath.entite.Produits;
import com.eath.entite.Utilisateurs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateursServiceImpl implements IUtilisateursService {
    private final UtilisateursRepository utilisateursRepository;

    @Override
    public Utilisateurs addUtilisateurs(Utilisateurs utilisateurs) {
        return utilisateursRepository.save(utilisateurs);
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Utilisateurs updateUtilisateurs(Integer idUtilisateur, Utilisateurs utilisateurs) {
        Utilisateurs existingpers = getOneUtilisateurs(idUtilisateur);
        existingpers.setPreference(utilisateurs.getPreference());
        existingpers.setNiveauAbonnement(utilisateurs.getNiveauAbonnement());
        existingpers.setPersonne(utilisateurs.getPersonne());
        existingpers.setDateCreation(utilisateurs.getDateCreation());
        existingpers.setDateModification(utilisateurs.getDateModification());
        return utilisateursRepository.save(existingpers);
    }

    @Override
    public Utilisateurs getOneUtilisateurs(Integer idUtilisateur) {
        return utilisateursRepository.findById(idUtilisateur).orElseThrow(()->
                new RuntimeException("Le Utilisateur  recherché n'existe pas")) ;
    }


}
