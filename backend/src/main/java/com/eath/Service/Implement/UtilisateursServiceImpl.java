package com.eath.Service.Implement;

import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Utilisateurs;
import com.eath.Service.UtilisateursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UtilisateursServiceImpl implements UtilisateursService {

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Override
    public Utilisateurs creerUtilisateur(Utilisateurs utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Optional<Utilisateurs> getUtilisateurById(Integer id) {
        return utilisateursRepository.findById(id);
    }

    @Override
    public Utilisateurs updateUtilisateur(Integer id, Utilisateurs utilisateur) {
        Optional<Utilisateurs> existingUtilisateur = utilisateursRepository.findById(id);
        if (existingUtilisateur.isPresent()) {
            Utilisateurs updatedUtilisateur = existingUtilisateur.get();
            updatedUtilisateur.setNomPersonne(utilisateur.getNomPersonne());
            updatedUtilisateur.setPrenomPersonne(utilisateur.getPrenomPersonne());
            updatedUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
            updatedUtilisateur.setEmail(utilisateur.getEmail());
            updatedUtilisateur.setPhotoDeProfil(utilisateur.getPhotoDeProfil());
            updatedUtilisateur.setTaille(utilisateur.getTaille());
            updatedUtilisateur.setPoids(utilisateur.getPoids());
            updatedUtilisateur.setAge(utilisateur.getAge());
            updatedUtilisateur.setSexe(utilisateur.getSexe());
            updatedUtilisateur.setConditionsMedicales(utilisateur.getConditionsMedicales());
            updatedUtilisateur.setPreference(utilisateur.getPreference());
            updatedUtilisateur.setNiveauAbonnement(utilisateur.getNiveauAbonnement());
            // Ajoutez ici les autres champs que vous souhaitez mettre à jour
            return utilisateursRepository.save(updatedUtilisateur);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'id : " + id);
        }
    }

    @Override
    public void deleteUtilisateur(Integer id) {
        utilisateursRepository.deleteById(id);
    }
}
