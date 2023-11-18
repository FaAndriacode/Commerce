package com.example.spring_acces_base.entity;

import java.sql.Date;

public class Besoin {
    int idBesoin;
    int idArticle;
    double quantite;
    Date date_creation;
    
    public int getIdBesoin() {
        return idBesoin;
    }
    public void setIdBesoin(int idBesoin) {
        this.idBesoin = idBesoin;
    }
    public int getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
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
}
