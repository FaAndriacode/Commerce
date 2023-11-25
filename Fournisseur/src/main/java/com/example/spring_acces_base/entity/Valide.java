package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class Valide {
    private int idsortie;
    private String date_sortie_valide;
    
    public Valide(){}
    
    public Valide(int idsortie, String date_sortie_valide){
        this.idsortie = idsortie;
        this.date_sortie_valide = date_sortie_valide;
    }
    
    public Valide(String idsortie, String date_sortie_valide){
        this.setIdsortie(idsortie);
        this.setDate_sortie_valide(date_sortie_valide);
    }
    
    public void insertValidation(Connection connex) throws Exception {
        Connection con = connex;
        boolean verification = false;
        String requete;

        if (con == null) {
            verification = true;
            con = Postgres.connexion();
        }
        if(this.getDate_sortie_valide() == null){
            requete = "INSERT INTO sortie_valide(idSortie, Date_sortie_valide) VALUES (" + this.getIdsortie()+ ",CURRENT_DATE)";
        }else{
            requete = "INSERT INTO sortie_valide(idSortie, Date_sortie_valide) VALUES (" + this.getIdsortie()+ ",'"+this.getDate_sortie_valide()+"')";
        }
        System.out.println(requete);
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if (verification){
                con.commit();
                con.close();
            }
        }
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
    
    public void setIdsortie(String idsortie) {
        this.idsortie = Integer.parseInt(idsortie);
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
    
}
