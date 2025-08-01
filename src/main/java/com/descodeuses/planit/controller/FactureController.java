package com.descodeuses.planit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.descodeuses.planit.model.Facture;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getById(@PathVariable Long id) {
        Facture facture = new Facture();
        facture.setId(id);
        facture.setNumero("FAC2025-00" + id);
        facture.setClient("Client " + id);
        facture.setMontantHT(100.0);
        facture.setId(20.0);
        facture.setMontantHT(120.0);

        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Facture>> getAll() {
        ArrayList<Facture> list = new ArrayList<>();

        Facture f1 = new Facture();
        f1.setId(1L);
        f1.setNumero("FAC2025-001");
        f1.setClient("Client A");
        f1.setMontantHT(200.0);
        f1.setId(40.0);
        f1.setMontantHT(240.0);

        Facture f2 = new Facture();
        f2.setId(2L);
        f2.setNumero("FAC2025-002");
        f2.setClient("Client B");
        f2.setMontantHT(150.0);
        f2.setId(30.0);
        f2.setMontantHT(180.0);

        list.add(f1);
        list.add(f2);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public String create(@RequestBody Facture facture) {
        return "create";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Facture facture) {
        return "update";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "delete";
    }
}
