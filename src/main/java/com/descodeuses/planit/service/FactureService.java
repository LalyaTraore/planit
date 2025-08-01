package com.descodeuses.planit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.model.Facture;
import com.descodeuses.planit.repository.FactureRepository;

@Service
public class FactureService {

    private final FactureRepository factureRepository;

    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    public Optional<Facture> findById(Long id) {
        return factureRepository.findById(id);
    }

    public Facture save(Facture facture) {
        return factureRepository.save(facture);
    }

    public void deleteById(Long id) {
        factureRepository.deleteById(id);
    }
}
