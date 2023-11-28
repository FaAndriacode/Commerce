package com.example.spring_acces_base.entity.boncommende;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vboncommande")
public class VBonCommande{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boncommande_sequence")
    @SequenceGenerator(name = "boncommande_sequence", sequenceName = "n_idboncommande", allocationSize = 1)
    @Column(name = "idboncommande")
    int idboncommande;

    @Column(name = "idfournisseur")
    int idfournisseur;

    @Column(name = "idarticle")
    int idarticle;

    @Column(name = "prixunitaire")
    double prixunitaire;

    @Column(name = "quantite")
    double quantite;

    @Column(name = "date")
    Date date;
    
    @Column(name = "nomfournisseur")
    String nomfournisseur;

    @Column(name = "nom")
    String nomarticle;

    @Column(name = "pht")
    double pht;

    @Column(name = "total")
    double total;

    @Column(name = "total_final")
    double total_final;

    public double getTotal_final() {
        return total_final;
    }

    public void setTotal_final(double total_final) {
        this.total_final = total_final;
    }

    public String getNomfournisseur() {
        return nomfournisseur;
    }

    public void setNomfournisseur(String nomfournisseur) {
        this.nomfournisseur = nomfournisseur;
    }

    public String getNomarticle() {
        return nomarticle;
    }

    public void setNomarticle(String nomarticle) {
        this.nomarticle = nomarticle;
    }

    public int getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(int idboncommande) {
        this.idboncommande = idboncommande;
    }

    public int getIdfournisseur() {
        return idfournisseur;
    }

    public void setIdfournisseur(int idfournisseur) {
        this.idfournisseur = idfournisseur;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPht() {
        return pht;
    }

    public void setPht(double pht) {
        this.pht = pht;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
