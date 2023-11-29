package com.example.spring_acces_base.entity.utils;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Etat {
    private int idetat;
    private String nom;
    
    public Object[] getListeEtat(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        Etat etat;
        ArrayList<Etat> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "select * from etat";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                etat = new Etat();
                etat.setIdetat(res.getInt("idEtat"));
                etat.setNom(res.getString("nom"));
                object.add(etat);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        Object[] temporaire = object.toArray();
        Etat[] conf = new Etat[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        
        return conf;
    }

    /**
     * @return the idetat
     */
    public int getIdetat() {
        return idetat;
    }

    /**
     * @param idetat the idetat to set
     */
    public void setIdetat(int idetat) {
        this.idetat = idetat;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
        
}
