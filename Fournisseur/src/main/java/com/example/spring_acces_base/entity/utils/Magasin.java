package com.example.spring_acces_base.entity.utils;

import com.example.spring_acces_base.connexion.Postgres;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class Magasin {
    int idMagasin;
    String nom;
    
    public Object[] getListeMagasin(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Magasin magasin;
        ArrayList<Magasin> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from magasin";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                magasin = new Magasin();
                magasin.setIdMagasin(res.getInt("idMagasin"));
                magasin.setNom(res.getString("nom"));
                object.add(magasin);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Magasin[] conf = new Magasin[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        
        return conf;
    }
    
    public int getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
