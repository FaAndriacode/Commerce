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
public class StockOut extends Stock{
    int idSortie;
    private Double quantiteo;
    private int idunite;
        
    public StockOut(){}
    
    public StockOut(String unite,String quantiteo,String idArticle, String quantite, String date_out, String idMagasin) throws Exception{
        super(idMagasin, idArticle, quantite, date_out);
        this.setQuantiteo(quantiteo);
        this.setIdunite(unite);
    }
    
    public void insertSortie(Connection connex) throws Exception {
        Connection con = connex;
        boolean verification = false;
        String requete = "";

        if (con == null) {
            verification = true;
            con = Postgres.connexion();
        }

        requete = "INSERT INTO sortie_stocke(idArticle, Quantite, idMagasin, Date_sortie,Quantite_o, idunite) VALUES ('" + this.getIdArticle() + "'," + this.getQuantite() + "," + this.getIdMagasin() + ",'" + this.getDate() + "',"+this.getQuantiteo()+","+this.getIdunite()+")";
        System.out.println(requete);

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.commit();
            if (verification) con.close();
        }
    }
    
     public void insertion(Double quantite, int idunite, String idarticle, String date_out, int idmagasin) throws Different, Exception{
        try {
            Double quantiteo = quantite;
            Article article = new Article();
            article.setIdArticle(idarticle);
            article = article.whoArticle(null);

            article.verifUnite(idunite);

            Unite u = new Unite();
            u.getUniteDemande(null, idunite);

            quantite = u.quantiteOut(quantite);

            new StockOut(String.valueOf(idunite), quantiteo.toString(), idarticle, quantite.toString(), date_out, String.valueOf(idmagasin)).insertSortie(null);

        }catch (Different e) {
            throw new Different();
        } 
        catch (Exception e) {
            throw new Exception();
        }
    }
    
    public int getIdSortie() {
        return idSortie;
    }
    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
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
        this.idunite = Integer.valueOf(idunite);
    }
}
