
package com.example.spring_acces_base.entity.fournisseur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fournisseur")
public class Fournisseur {
    
    @Id
    @Column(name = "idfournisseur")
    int idFournisseur;

    @Column(name = "nom")
    String nom;

    @Column(name = "adresse")
    String adresse;

    @Column(name = "email")
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
