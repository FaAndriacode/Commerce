package com.example.spring_acces_base.entity.utils;

import com.example.spring_acces_base.connexion.Postgres;
import com.example.spring_acces_base.exeption.Different;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Article {
    String idArticle;
    String nom;
    int etat;
    private int idunitaire;
    private int idequivalance;
    
    
    public Article(){}
    
    public Article(String idArticle, String nom, int etat, String idequivalance){
        this.setIdArticle(idArticle);
        this.setEtat(etat);
        this.setNom(nom);
        this.setIdequivalence(idequivalance);
    }
    
    public void verifUnite(int iduniteD) throws Different{
        if(this.getIdequivalance() != iduniteD){
            throw new Different();
        }
    }
    
    
    public Article whoArticle(Connection connex)throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Article article = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from article where idarticle = '"+this.getIdArticle()+"'";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                article = new Article(); 
                article.setIdArticle(res.getString("idArticle"));
                article.setNom(res.getString("nom"));
                article.setEtat(res.getInt("etat"));
                article.setIdunitaire(res.getInt("idunitaire"));
                article.setIdequivalence(res.getInt("idequivalance"));           
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        
        return article;
    }
    public Object[] getListeArticle(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Article article;
        ArrayList<Article> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from article";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                article = new Article(); 
                article.setIdArticle(res.getString("idArticle"));
                article.setNom(res.getString("nom"));
                article.setEtat(res.getInt("etat"));
                article.setIdunitaire(res.getInt("idunitaire"));
                article.setIdequivalence(res.getInt("idequivalance"));
                object.add(article);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Article[] conf = new Article[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        
        return conf;
    }
    
    public int getEtat(Connection connex,String idArticle) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        int etat = 0;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select etat from article where idArticle = '"+idArticle+"'";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                etat = res.getInt("etat");            
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        return etat;
    }
    
    public void insertArticle(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "INSERT INTO article(idArticle, nom, etat, unite) VALUES ('"+this.getIdArticle()+"','"+this.getNom()+"',"+this.getEtat()+","+this.getIdunitaire()+","+this.getIdequivalance()+")";
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
    
    public String getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdunitaire() {
        return idunitaire;
    }

    public void setIdunitaire(int idunitaire) {
        this.idunitaire = idunitaire;
    }
    
    public void setIdunitaire(String idunitaire) {
        this.idunitaire = Integer.parseInt(idunitaire);
    }

    public int getIdequivalance() {
        return idequivalance;
    }

    public void setIdequivalence(int idequivalance) {
        this.idequivalance = idequivalance;
    }
    
    public void setIdequivalence(String idequivalance) {
        this.idequivalance = Integer.parseInt(idequivalance);
    }
}
