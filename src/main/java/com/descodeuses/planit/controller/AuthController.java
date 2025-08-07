package com.descodeuses.planit.controller;

import com.descodeuses.planit.dto.AuthRequest;
import com.descodeuses.planit.dto.AuthResponse;
import com.descodeuses.planit.dto.UtilisateurDTO;
import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.security.JwtUtil;
import com.descodeuses.planit.service.LogDocumentService;
import com.descodeuses.planit.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private LogDocumentService logService; // ✅ Injection correcte


    
    // 🔐 Endpoint Login

     @GetMapping("/login/hello")
    public String sayHello() {
        return "Hello";
    }
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
    try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UtilisateurEntity user = userService.findByUsername(request.getUsername());

        String token = jwtUtil.generateToken(user.getUsername(), user.getNom(), user.getRole());

        // ✅ Log enrichi
        logService.addLog("login executée", httpRequest, request.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));

    } catch (Exception ex) {
        logService.addLog("Login failed for user: " + request.getUsername(), httpRequest, request.getUsername());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}

    // 🔐 Endpoint Signup
    @PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody UtilisateurDTO utilisateurDTO, HttpServletRequest httpRequest) {
    try {
        UtilisateurEntity userEntity = new UtilisateurEntity();
        userEntity.setUsername(utilisateurDTO.getUsername());
        userEntity.setPassword(utilisateurDTO.getPassword());
        userEntity.setRole(utilisateurDTO.getRole());
        userEntity.setNom(utilisateurDTO.getNom());

        UtilisateurEntity newUser = userService.createUser(userEntity);

        // ✅ Log enrichi
        logService.addLog("New user registered: " + newUser.getUsername(), httpRequest, newUser.getUsername());

        String token = jwtUtil.generateToken(newUser.getUsername(), newUser.getNom(), newUser.getRole());

        return ResponseEntity.ok(new AuthResponse(token));
    } catch (Exception e) {
        logService.addLog("Signup failed: " + e.getMessage(), httpRequest, utilisateurDTO.getUsername());

        Map<String, String> error = new HashMap<>();
        error.put("error", "Registration failed: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

}
