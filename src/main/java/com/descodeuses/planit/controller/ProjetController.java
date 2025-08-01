package com.descodeuses.planit.controller;

import com.descodeuses.planit.dto.ProjetDTO;

import com.descodeuses.planit.service.ProjetService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private final ProjetService service;

    public ProjetController(ProjetService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello Projet", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getById(@PathVariable Long id) {
        ProjetDTO dto = service.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjetDTO>> getAll() {
        List<ProjetDTO> projets = service.getAll();
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjetDTO> create(@RequestBody ProjetDTO requestDTO) {
        ProjetDTO createdDTO = service.create(requestDTO);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDTO> update(@PathVariable Long id, @RequestBody ProjetDTO requestDTO) {
        ProjetDTO updatedDTO = service.update(id, requestDTO);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
