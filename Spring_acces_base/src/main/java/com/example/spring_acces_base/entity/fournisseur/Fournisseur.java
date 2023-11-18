package com.example.spring_acces_base.entity.fournisseur;

public class Fournisseur {
    int idFournisseur;
    String nom;
    String adresse;
    String email;

    public int getIdFournisseur() {
        return idFournisseur;
    }
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
 
}
