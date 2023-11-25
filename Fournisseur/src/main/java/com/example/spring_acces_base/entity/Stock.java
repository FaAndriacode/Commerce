package com.example.spring_acces_base.entity;

import org.springframework.stereotype.Service;

@Service
public class Stock {
    private int idMagasin;
    private String idArticle;
    private Double quantite;
    private String date;

    public Stock(){
    }
    
    public Stock(int idmagasin, String idarticle, Double quantite, String date){
        this.setDate(date);
        this.setIdArticle(idarticle);
        this.setIdMagasin(idmagasin);
        this.setQuantite(quantite);
    }
    
    public Stock(String idmagasin, String idarticle, String quantite, String date) throws Exception{
        this.setDate(date);
        this.setIdArticle(idarticle);
        this.setIdMagasin(idmagasin);
        this.setQuantite(quantite);
    }
    
    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
    
    public void setIdMagasin(String idMagasin) {
        this.idMagasin = Integer.parseInt(idMagasin);
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
    
    public void setQuantite(String quantite) throws Exception {
        Double temp = Double.parseDouble(quantite);
        if(temp >= 0){
             this.quantite = temp;
        }else{
            throw new Exception("quantiter negative");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
