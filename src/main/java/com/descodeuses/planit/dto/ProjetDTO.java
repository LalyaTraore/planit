package com.descodeuses.planit.dto;

import java.time.LocalDate;

import com.descodeuses.planit.entity.Priorite;

public class ProjetDTO {
private Long id;

    private String title;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut ; 
    private Priorite priorite; 

public ProjetDTO (Long id , String title, String description, LocalDate dateDebut, LocalDate dateFin,String statut ,Priorite priorite ){
this.id=id;
this.title=title;
this.description=description;
this.dateDebut=dateDebut;
this.dateFin=dateFin;
this.statut=statut;
this.priorite=priorite; 

}
  public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

}