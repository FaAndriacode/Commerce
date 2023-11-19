package com.example.spring_acces_base.entity.service;

import com.example.spring_acces_base.entity.besoin.Besoin;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @Column(name = "idservices")
    int idServices;

    @Column(name = "nom")
    String nom;

    @Column(name = "iddept")
    int idDept;

    @OneToMany(mappedBy = "service")
    List<Besoin> besoins;
    
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
