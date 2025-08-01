package com.descodeuses.planit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.descodeuses.planit.model.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    // JpaRepository fournit toutes les méthodes CRUD de base
}
