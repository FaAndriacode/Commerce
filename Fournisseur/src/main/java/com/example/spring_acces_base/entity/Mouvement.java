package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Mouvement {
    private int identre;
    private int idsortie;
    private double quantite_f;
    
    public void updateQuantite(Connection connex, Double quantite, int idEntrer, int idSortie) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "INSERT INTO mouvement(Quantite_F, idEntre, idSorti) VALUES ("+quantite+","+idEntrer+","+idSortie+")";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification){
                con.commit();
                con.close();
            } 
        }
    }
    
    public void insertMouvement(Connection connex,ArrayList<Mouvement> mouvement) throws Exception{
        for (Mouvement mv : mouvement) {
            this.updateQuantite(connex, mv.getQuantite_f(), mv.getIdentre(), mv.getIdsortie());
        }
    }
    
    public void fluxMouvement(Connection connex,ArrayList<Stock_reste> mouvement ,String qt, String sortie) throws Exception{
        ArrayList<Mouvement> mouv= this.calculeQuantiter(mouvement, qt, sortie);
        this.insertMouvement(connex, mouv);
    }
    
    public ArrayList<Mouvement> calculeQuantiter(ArrayList<Stock_reste> mouvement ,String qt, String sortie) throws Exception{
        ArrayList<Mouvement> mv = new ArrayList<>();
        Mouvement mx = null;
        Double quantiter = Double.valueOf(qt);
        int idsortie = Integer.parseInt(sortie);
        for (Stock_reste mouv : mouvement) {
            if(quantiter > 0){
                if(mouv.getQuantite_f() <= quantiter){
                    mx = new Mouvement();
                    quantiter = quantiter - mouv.getQuantite_f();
                    mx.setIdentre(mouv.getIdEntrer());
                    mx.setIdsortie(idsortie);
                    mx.setQuantite_f(0.0);
                    mv.add(mx);
                }else{
                    mx = new Mouvement();
                    quantiter = mouv.getQuantite_f() - quantiter;
                    mx.setIdentre(mouv.getIdEntrer());
                    mx.setIdsortie(idsortie);
                    mx.setQuantite_f(quantiter);
                    mv.add(mx);
                    break;
                }
            }else{
                break;
            }      
        }
        return mv;
    }

    /**
     * @return the identre
     */
    public int getIdentre() {
        return identre;
    }

    /**
     * @param identre the identre to set
     */
    public void setIdentre(int identre) {
        this.identre = identre;
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
     * @return the quantite_f
     */
    public double getQuantite_f() {
        return quantite_f;
    }

    /**
     * @param quantite_f the quantite_f to set
     */
    public void setQuantite_f(double quantite_f) {
        this.quantite_f = quantite_f;
    }
}
