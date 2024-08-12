package com.eath.Service.Implement;

import com.eath.Service.AdministrateurService;
import com.eath.dao.AdministrateurRepository;
import com.eath.entite.Administrateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministrateurServiceImpl implements AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

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
    public boolean existsById(Integer id) {
        return administrateurRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        administrateurRepository.deleteById(id);
    }
}
