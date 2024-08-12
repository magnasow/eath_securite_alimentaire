package com.eath.Service.Implement;

import com.eath.Service.UtilisateursService;
import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateursServiceImpl implements UtilisateursService {

    private final UtilisateursRepository utilisateursRepository;

    @Autowired
    public UtilisateursServiceImpl(UtilisateursRepository utilisateursRepository) {
        this.utilisateursRepository = utilisateursRepository;
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Utilisateurs getUtilisateursById(Integer id) {
        Optional<Utilisateurs> optionalUtilisateurs = utilisateursRepository.findById(id);
        return optionalUtilisateurs.orElse(null);
    }

    @Override
    public Utilisateurs saveUtilisateurs(Utilisateurs utilisateurs) {
        return utilisateursRepository.save(utilisateurs);
    }

    @Override
    public void deleteUtilisateurs(Integer id) {
        utilisateursRepository.deleteById(id);
    }
}
