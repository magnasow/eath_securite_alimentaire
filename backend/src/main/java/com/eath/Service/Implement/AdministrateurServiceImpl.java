package com.eath.Service.Implement;

import com.eath.Service.AdministrateurService;
import com.eath.entite.Administrateur;
import com.eath.dao.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Override
    public List<Administrateur> findAll() {
        return administrateurRepository.findAll();
    }

    @Override
    public Optional<Administrateur> findById(Integer id) {
        return administrateurRepository.findById(id);
    }

    @Override
    public Administrateur save(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    @Override
    public void deleteById(Integer id) {
        administrateurRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
