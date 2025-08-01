package com.descodeuses.planit.controller;

import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UtilisateurEntity>> getAllUsers() {
        List<UtilisateurEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurEntity> getUserById(@PathVariable Long id) {
        UtilisateurEntity user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UtilisateurEntity> createUser(@RequestBody UtilisateurEntity utilisateur) {
        try {
            UtilisateurEntity newUser = userService.createUser(utilisateur);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurEntity> updateUser(@PathVariable Long id, @RequestBody UtilisateurEntity utilisateur) {
        UtilisateurEntity updatedUser = userService.updateUser(id, utilisateur);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
