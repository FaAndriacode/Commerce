package com.example.spring_acces_base.entity.besoin;

import java.sql.Date;

import com.example.spring_acces_base.entity.Article.Article;
import com.example.spring_acces_base.entity.service.Services;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "besoin")
public class Besoin {

    @Id
    @Column(name = "idbesoin")
    int idBesoin;

    @Column(name = "quantite")
    double quantite;

    @Column(name = "date_creation")
    Date date_creation;

    @Column(name = "etat")
    int etat;

    @ManyToOne
    @JoinColumn(name = "idservices") // Nom de la colonne qui référence l'id du service dans la table des besoins
    Services service;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    Article article;
    
    public Besoin() {}

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
}
