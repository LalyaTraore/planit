package com.descodeuses.planit.dto;

import com.descodeuses.planit.entity.UtilisateurEntity;

public class UtilisateurDTO {
    private Long id;
    private String username;
    private String password;  // ajouté
    private String role;
    private String nom;

    public UtilisateurDTO() {}

    // Constructeur depuis l'entité
    public UtilisateurDTO(UtilisateurEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.role = entity.getRole();
        this.nom=entity.getNom();
        // Ne pas exposer le mot de passe ici !
    }

    // Getters et setters

    public Long getId() {
         return id; 
        }

    public void setId(Long id) {
         this.id = id; 
        }

    public String getUsername() {
         return username;
         }

    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) {
         this.password = password; 
        }

    public String getRole() { 
        return role; 
    }
    public void setRole(String role) {
        this.role = role != null ? role.toUpperCase() : null;
         }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    
}
