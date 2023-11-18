package com.example.spring_acces_base.entity;

public class Services {
    int idServices;
    String nom;
    int idDept;
    
    public int getIdServices() {
        return idServices;
    }
    public void setIdServices(int idServices) {
        this.idServices = idServices;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getIdDept() {
        return idDept;
    }
    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }
        
}
