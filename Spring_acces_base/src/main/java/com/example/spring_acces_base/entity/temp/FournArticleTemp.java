package com.example.spring_acces_base.entity.temp;

import java.sql.Date;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.fournisseur.Fournisseur;

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
@Table(name = "fournarticletemp")
public class FournArticleTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fournarticle_sequence")
    @SequenceGenerator(name = "fournarticle_sequence", sequenceName = "n_idfournarticletemp", allocationSize = 1)
    @Column(name = "idfournarticle")
    int idfournarticle;


    @Column(name = "idfournisseur")
    int idfournisseur;

    @ManyToOne
    @JoinColumn(name = "idfournisseur", insertable= false, updatable = false)
    private Fournisseur fournisseur;

    @Column(name = "idarticle")
    int idarticle;

    @ManyToOne
    @JoinColumn(name = "idarticle", insertable= false, updatable = false)
    private Article article;

    @Column(name = "prixunitaire")
    int prixunitaire;

    @Column(name = "quantite")
    double quantite;

    @Column(name = "date")
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdfournarticle() {
        return idfournarticle;
    }

    public void setIdfournarticle(int idfournarticle) {
        this.idfournarticle = idfournarticle;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur=fournisseur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article=article;
    }

    public int getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(int prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
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
}
