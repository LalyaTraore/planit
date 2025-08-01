package com.descodeuses.planit.dto;

public class ContactDTO {
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    private Long id;
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    private String name;
    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    private String email;
    public String getTelephone() {
        return telephone;
    }



    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }



    private String telephone;
    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }



    private String message;

    

    public ContactDTO(Long id, String name, String email, String telephone, String message) {
        this.id = id;
        this.name= name;
        this.email = email;
        this.telephone = telephone;
        this.message = message;
    }

    
}
