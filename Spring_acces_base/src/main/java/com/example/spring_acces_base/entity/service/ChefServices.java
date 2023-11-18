package com.example.spring_acces_base.entity.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chefservices")
public class ChefServices {

    @Id
    @Column(name = "iduserservices")
    int idUserServices;

    @Column(name = "email")
    String email;

    @Column(name = "passwords")
    String passwords;

    @Column(name = "idservices")
    int idServices;
    
    public int getIdUserServices() {
        return idUserServices;
    }
    public void setIdUserServices(int idUserServices) {
        this.idUserServices = idUserServices;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswords() {
        return passwords;
    }
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
    public int getIdServices() {
        return idServices;
    }
    public void setIdServices(int idServices) {
        this.idServices = idServices;
    }

}
