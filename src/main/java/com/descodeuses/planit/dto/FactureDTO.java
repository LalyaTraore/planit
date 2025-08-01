package com.descodeuses.planit.dto;

public class FactureDTO {

    private Long id;
    private String numero;
    private String client;
    private double montantHT;
    private double tva;
    private double montantTTC;

    public FactureDTO() {
    }

    public FactureDTO(Long id, String numero, String client, double montantHT, double tva, double montantTTC) {
        this.id = id;
        this.numero = numero;
        this.client = client;
        this.montantHT = montantHT;
        this.tva = tva;
        this.montantTTC = montantTTC;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }
}
