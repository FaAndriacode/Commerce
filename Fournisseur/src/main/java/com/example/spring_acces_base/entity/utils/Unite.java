package com.example.spring_acces_base.entity.utils;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Unite {
    private int idUnite;
    private String nom;
    private Double quantite;
    
    public Object[] getListeUnite(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Unite unite;
        ArrayList<Unite> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from unite";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                unite = new Unite(); 
                unite.setIdUnite(res.getInt("idunite"));
                unite.setNom(res.getString("nom"));
                unite.setQuantite(res.getDouble("quantite"));
                object.add(unite);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Unite[] conf = new Unite[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        
        return conf;
    }
    
    public Double quantiteOut(Double quantiteD){
        System.out.println(this.getQuantite() +" * "+quantiteD);
        return this.getQuantite() * quantiteD;
    }
    
    public Double prixUnitaire(Double prixUnitaire){
        System.out.println(this.getQuantite() +" / "+prixUnitaire);
        return  prixUnitaire / this.getQuantite();
    }
    
    public void getUniteDemande(Connection connex,int uniteD) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from unite WHERE idUnite ="+uniteD;
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                this.setIdUnite(res.getInt("idunite"));
                this.setNom(res.getString("nom"));
                this.setQuantite(res.getDouble("quantite"));            
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
}
