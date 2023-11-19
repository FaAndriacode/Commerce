package com.example.spring_acces_base.entity.fournarticle;

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
@Table(name = "fournarticle")
public class FournArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fournarticle_sequence")
    @SequenceGenerator(name = "fournarticle_sequence", sequenceName = "n_id_fourn_article", allocationSize = 1)
    @Column(name = "idFournArticle")
    int idfournarticle;

    @ManyToOne
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idfournisseur")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "idArticle", referencedColumnName = "idarticle")
    private Article article;

    @Column(name = "prixunitaire")
    int prixunitaire;

    @Column(name = "quantite")
    int quantite;

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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
