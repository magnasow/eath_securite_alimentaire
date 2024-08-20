package com.eath.Service.Implement;

import com.eath.entite.UtilisateurAdministrateurVue;
import com.eath.dao.UtilisateurAdministrateurVueRepository;
import com.eath.Service.UtilisateurAdministrateurVueService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurAdministrateurVueServiceImpl implements UtilisateurAdministrateurVueService {

    private final UtilisateurAdministrateurVueRepository repository;

    public UtilisateurAdministrateurVueServiceImpl(UtilisateurAdministrateurVueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UtilisateurAdministrateurVue> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
