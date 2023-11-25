package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import com.example.spring_acces_base.entity.utils.Article;
import com.example.spring_acces_base.entity.utils.Unite;
import com.example.spring_acces_base.exeption.Different;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class StockIn extends Stock{
    int idEntre;
    Double prix_unitaire;
    private Double quantiteo;
    private int idunite;
    
    public StockIn(){}
    
    public StockIn(String idunite, String quantiteo, String idArticle, String quantite, String date_in, String prix_unitaire, String idMagasin) throws Exception{
        super(idMagasin, idArticle, quantite, date_in);
        this.setPrix_unitaire(prix_unitaire);
        this.setQuantiteo(quantiteo);
        this.setIdunite(idunite);
    }
    
    public StockIn(String idArticle, Double quantite, String date_in, Double prix_unitaire, int idMagasin){
        super(idMagasin, idArticle, quantite, date_in);
        this.setPrix_unitaire(prix_unitaire);
    }
    
    public void insertEntrer(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "INSERT INTO entre_Stocke(idArticle, Quantite, prixUnitaire, idMagasin, Date_Entre, Quantite_o, idunite) VALUES ('"+this.getIdArticle()+"',"+this.getQuantite()+","+this.getPrix_unitaire()+","+this.getIdMagasin()+",'"+this.getDate()+"',"+this.getQuantiteo()+","+this.getIdunite()+")";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            con.commit();
            if(verification) con.close();
        }
    }
    
    public void insertion(Double quantiteo, Double quantite, int idunite, String idarticle, Double prix, String date_in, int idmagasin) throws Exception{
        try {
            Article article = new Article();
            article.setIdArticle(idarticle);
            article = article.whoArticle(null);

            article.verifUnite(idunite);

            Unite u = new Unite();
            u.getUniteDemande(null, idunite);

            quantite = u.quantiteOut(quantite);
            prix = u.prixUnitaire(prix);

            new StockIn(String.valueOf(idunite), quantiteo.toString(), idarticle, quantite.toString(), date_in, prix.toString(), String.valueOf(idmagasin)).insertEntrer(null);

        }catch (Different e) {
            throw new Different();
        } 
        catch (Exception e) {
            throw new Exception();
        }
    }

    public int getIdEntre() {
        return idEntre;
    }
    public void setIdEntre(int idEntre) {
        this.idEntre = idEntre;
    }
    public Double getPrix_unitaire() {
        return prix_unitaire;
    }
    public void setPrix_unitaire(Double prix_unitaire) {
        
        this.prix_unitaire = prix_unitaire;
    }
    public void setPrix_unitaire(String prix_unitaire) throws Exception{
        Double temp =  Double.valueOf(prix_unitaire);
        if(temp >= 0){
            this.prix_unitaire = temp;
        }else{
            throw new Exception("prix negative le");
        }
    }

    public Double getQuantiteo() {
        return quantiteo;
    }

    public void setQuantiteo(Double quantiteo) {
        this.quantiteo = quantiteo;
    }
    
    public void setQuantiteo(String quantiteo) throws Exception {
        Double temp = Double.parseDouble(quantiteo);
        if(temp >= 0){
             this.quantiteo = temp;
        }else{
            throw new Exception("quantiter negative");
        }
    }

    public int getIdunite() {
        return idunite;
    }

    public void setIdunite(int idunite) {
        this.idunite = idunite;
    }
    public void setIdunite(String idunite) {
        this.idunite = Integer.parseInt(idunite);
    }
}
