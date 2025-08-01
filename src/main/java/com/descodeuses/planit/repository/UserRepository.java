package com.descodeuses.planit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.descodeuses.planit.entity.UtilisateurEntity;

public interface UserRepository extends JpaRepository<UtilisateurEntity, Long> {
    Optional<UtilisateurEntity> findByUsername(String username);
      Optional<UtilisateurEntity> findByNom(String nom); 
}
