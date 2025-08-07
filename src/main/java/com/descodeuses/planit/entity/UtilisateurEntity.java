package com.descodeuses.planit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur") // nom table en base (optionnel)
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private String nom;



    public UtilisateurEntity() {}

    // Constructeur avec paramètres
   public UtilisateurEntity(String username, String password, String role, String nom) {
    this.username = username;
    this.password = password;
    this.setRole(role); 
    this.nom = nom;
}


    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { // il manque setter id si besoin
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

     public String  getNom(){
        return nom;  
    }

    public void setNom(String nom){
      this.nom= nom;
    }

 
   
}
