package com.example.spring_acces_base.entity.Article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "idarticle")
    int idArticle;

    @Column(name = "nom")
    String nom;

    @Column(name = "unite")
    String unite;
    
    public int getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) {
        this.unite = unite;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
