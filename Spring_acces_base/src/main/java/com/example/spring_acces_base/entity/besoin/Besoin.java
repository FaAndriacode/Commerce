package com.example.spring_acces_base.entity.besoin;

import java.sql.Date;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.service.Services;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "besoin")
public class Besoin {

    @Id
    @Column(name = "idbesoin")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_idbesoin_seq")
    @SequenceGenerator(name = "n_idbesoin_seq", sequenceName = "n_idbesoin", allocationSize = 1)
    int idBesoin;

    @Column(name = "quantite")
    double quantite;

    @Column(name = "date_creation")
    Date date_creation;

    @Column(name = "etat")
    int etat;

    @Column(name = "idservices")
    int idservices;

    @Column(name = "idarticle")
    int idarticle;

    @ManyToOne
    @JoinColumn(name = "idservices", insertable= false, updatable = false) // Nom de la colonne qui référence l'id du service dans la table des besoins
    Services service;

    @ManyToOne
    @JoinColumn(name = "idarticle", insertable = false, updatable = false)
    Article article;
    
    public Besoin() {}

    public Besoin(int idArticle, int idServices, double quantite, Date date_creation, int etat) {
        this.quantite = quantite;
        this.date_creation = date_creation;
        this.etat = etat;
        this.idservices = idServices;
        this.idarticle = idArticle;

        System.out.println("idarticle ->" +this.idarticle);
        System.out.println("idservices ->" +this.idservices);
        System.out.println("etat ->" +this.etat);
        System.out.println("date_creation ->" +this.date_creation);
        System.out.println("quantite ->" +this.quantite);
    }

    public Besoin(double quantite, Date date_creation, int etat) {
        this.quantite = quantite;
        this.date_creation = date_creation;
        this.etat = etat;
    }

    public int getIdBesoin() {
        return idBesoin;
    }
    public void setIdBesoin(int idBesoin) {
        this.idBesoin = idBesoin;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public Date getDate_creation() {
        return date_creation;
    }
    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getIdservices() {
        return idservices;
    }

    public void setIdservices(int idservices) {
        this.idservices = idservices;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    } 
}
