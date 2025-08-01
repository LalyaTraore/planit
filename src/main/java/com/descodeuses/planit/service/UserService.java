package com.descodeuses.planit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UtilisateurEntity createUser(UtilisateurEntity utilisateur) throws Exception {
        if (emailExista(utilisateur.getUsername())) {
            throw new Exception("user already exist");
        }
        UtilisateurEntity user = new UtilisateurEntity();
        user.setUsername(utilisateur.getUsername());
        user.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        user.setRole(utilisateur.getRole().toUpperCase());
        user.setNom(utilisateur.getNom());
        return utilisateurRepository.save(user);
    }

    private boolean emailExista(String email) {
        System.out.println("email: " + email);
        return utilisateurRepository.findByUsername(email).isPresent();
    }
    // private boolean nomExiste(String nom) {
    //     System.out.println("nom: " + nom);
    //     return utilisateurRepository.findByNom(nom).isPresent();
    // }


    public List<UtilisateurEntity> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public UtilisateurEntity getUserById(Long id) {
        Optional<UtilisateurEntity> user = utilisateurRepository.findById(id);
        return user.orElse(null);
    }

public UtilisateurEntity updateUser(Long id, UtilisateurEntity utilisateur) {
    Optional<UtilisateurEntity> existingUser = utilisateurRepository.findById(id);
    if (existingUser.isPresent()) {
        UtilisateurEntity userToUpdate = existingUser.get();
        userToUpdate.setUsername(utilisateur.getUsername());
        if (utilisateur.getPassword() != null && !utilisateur.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        }
        userToUpdate.setRole(utilisateur.getRole().toUpperCase());
        userToUpdate.setNom(utilisateur.getNom());  
        return utilisateurRepository.save(userToUpdate);
    }
    return null;
}
public UtilisateurEntity findByUsername(String username) {
    return utilisateurRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'username: " + username));
}


    public boolean deleteUser(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
