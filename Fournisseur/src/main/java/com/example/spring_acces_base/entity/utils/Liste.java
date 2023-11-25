package com.example.spring_acces_base.entity.utils;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Liste {
    private Double Quantite ;
    private int idMagasin ;
    private int idSortie ;
    private String idArticle;
    private String date;
    private Double Quantite_o;
    private String nom ;

    public Object[] listeSortie(Connection connex) throws Exception {
        Connection con = connex;
        boolean verification = false;
        String requete;
        Liste sortie;
        ArrayList<Liste> object = new ArrayList<>();

        if (con == null) {
            verification = true;
            con = Postgres.connexion();
        }

        requete = "select * from listes";
        System.out.println(requete);
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                sortie = new Liste(); 
                sortie.setIdSortie(res.getInt("idsortie"));
                sortie.setIdArticle(res.getString("idarticle"));
                sortie.setQuantite(res.getDouble("quantite_sortie"));
                sortie.setQuantite_o(res.getDouble("quantite_o_sortie"));
                sortie.setNom(res.getString("unite_nom"));
                sortie.setIdMagasin(res.getInt("idMagasin"));
                sortie.setDate(String.valueOf(res.getDate("date")));
                object.add(sortie);             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.commit();
            if (verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Liste[] conf = new Liste[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        

        return conf;
    }
    
    public Object[] listeEntre(Connection connex) throws Exception {
        Connection con = connex;
        boolean verification = false;
        String requete;
        Liste sortie;
        ArrayList<Liste> object = new ArrayList<>();

        if (con == null) {
            verification = true;
            con = Postgres.connexion();
        }

        requete = "select * from entre";
        System.out.println(requete);
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                sortie = new Liste(); 
                sortie.setIdSortie(res.getInt("idEntre"));
                sortie.setIdArticle(res.getString("idarticle"));
                sortie.setQuantite(res.getDouble("quantite_sortie"));
                sortie.setQuantite_o(res.getDouble("quantite_o_sortie"));
                sortie.setNom(res.getString("unite_nom"));
                sortie.setIdMagasin(res.getInt("idMagasin"));
                sortie.setDate(String.valueOf(res.getDate("date")));
                object.add(sortie);             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.commit();
            if (verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Liste[] conf = new Liste[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        

        return conf;
    }
    
    public Double getQuantite() {
        return Quantite;
    }

    public void setQuantite(Double Quantite) {
        this.Quantite = Quantite;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getQuantite_o() {
        return Quantite_o;
    }

    public void setQuantite_o(Double Quantite_o) {
        this.Quantite_o = Quantite_o;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
    }
}
