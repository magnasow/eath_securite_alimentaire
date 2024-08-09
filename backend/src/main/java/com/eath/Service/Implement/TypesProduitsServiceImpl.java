package com.eath.Service.Implement;


import com.eath.Service.TypesProduitsService;
import com.eath.dao.TypesProduitsRepository;
import com.eath.entite.TypesProduits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypesProduitsServiceImpl implements TypesProduitsService {

    @Autowired
    private TypesProduitsRepository typesProduitsRepository;

    @Override
    public List<TypesProduits> getAllTypesProduits() {
        return typesProduitsRepository.findAll();
    }

    @Override
    public Optional<TypesProduits> getTypesProduitsById(Integer id) {
        return typesProduitsRepository.findById(id);
    }

    @Override
    public TypesProduits createTypesProduits(TypesProduits typesProduits) {
        return typesProduitsRepository.save(typesProduits);
    }

    @Override
    public Optional<TypesProduits> updateTypesProduits(Integer id, TypesProduits typesProduitsDetails) {
        Optional<TypesProduits> typesProduits = typesProduitsRepository.findById(id);
        if (typesProduits.isPresent()) {
            TypesProduits tp = typesProduits.get();
            tp.setNomTypeProduit(typesProduitsDetails.getNomTypeProduit());
            tp.setDescriptionTypeProduit(typesProduitsDetails.getDescriptionTypeProduit());
            return Optional.of(typesProduitsRepository.save(tp));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteTypesProduits(Integer id) {
        if (typesProduitsRepository.existsById(id)) {
            typesProduitsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
