package com.eath.Service.Implement;

import com.eath.Service.UtilisateursService;
import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Utilisateurs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateursServiceImpl implements UtilisateursService {

    private final UtilisateursRepository utilisateurRepository;

    @Override
    public Utilisateurs addUtilisateur(Utilisateurs utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateurs updateUtilisateur(Integer idPersonne, Utilisateurs utilisateur) {
        if (utilisateurRepository.existsById(idPersonne)) {
            utilisateur.setIdPersonne(idPersonne);  // Utilisez setIdPersonne
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new RuntimeException("Utilisateur not found with id " + idPersonne);
        }
    }

    @Override
    public void deleteUtilisateur(Integer idPersonne) {
        if (utilisateurRepository.existsById(idPersonne)) {
            utilisateurRepository.deleteById(idPersonne);
        } else {
            throw new RuntimeException("Utilisateur not found with id " + idPersonne);
        }
    }

    @Override
    public Utilisateurs getOneUtilisateur(Integer idPersonne) {
        return utilisateurRepository.findById(idPersonne)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id " + idPersonne));
    }
}
