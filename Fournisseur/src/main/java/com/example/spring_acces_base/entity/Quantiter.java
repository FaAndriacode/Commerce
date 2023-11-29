package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class Quantiter{
    private int idsortie;
    private String date_sortie_valide;
    private String date_sortie;
    private Double quantite;
    private String idArticle;
    private int idMagasin;    
    
    public Quantiter dateanterieur(Connection connex,String idarticle, String idmagasin) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Quantiter stock = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT * FROM sortie WHERE (idarticle, idmagasin, date_sortie_valide) = (SELECT idarticle, idmagasin, MAX(date_sortie_valide) AS date_sortie_valide FROM sortie WHERE idarticle = '"+idarticle+"' AND idmagasin = "+idmagasin+" GROUP BY idarticle, idmagasin )";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                stock = new Quantiter(); 
                stock.setIdArticle(res.getString("idArticle"));
                stock.setDate_sortie_valide(String.valueOf(res.getDate("date_sortie_valide")));
                stock.setDate_sortie(String.valueOf(res.getDate("date_sortie")));
                stock.setQuantite(res.getDouble("quantite"));  
                stock.setIdMagasin(res.getInt("idMagasin"));
                stock.setIdsortie(res.getInt("idsortie"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        return stock;
    }

    /**
     * @return the idsortie
     */
    public int getIdsortie() {
        return idsortie;
    }

    /**
     * @param idsortie the idsortie to set
     */
    public void setIdsortie(int idsortie) {
        this.idsortie = idsortie;
    }

    /**
     * @return the date_sortie_valide
     */
    public String getDate_sortie_valide() {
        return date_sortie_valide;
    }

    /**
     * @param date_sortie_valide the date_sortie_valide to set
     */
    public void setDate_sortie_valide(String date_sortie_valide) {
        this.date_sortie_valide = date_sortie_valide;
    }

    /**
     * @return the date_sortie
     */
    public String getDate_sortie() {
        return date_sortie;
    }

    /**
     * @param date_sortie the date_sortie to set
     */
    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    /**
     * @return the quantite
     */
    public Double getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the idArticle
     */
    public String getIdArticle() {
        return idArticle;
    }

    /**
     * @param idArticle the idArticle to set
     */
    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    /**
     * @return the idMagasin
     */
    public int getIdMagasin() {
        return idMagasin;
    }

    /**
     * @param idMagasin the idMagasin to set
     */
    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
  
}
