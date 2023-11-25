package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import com.example.spring_acces_base.entity.utils.Article;
import com.example.spring_acces_base.exeption.DateAnterieur;
import com.example.spring_acces_base.exeption.Quantite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class Stock_reste {
    private int idEntrer;
    private String date_in;
    private Double prix_unitaire;
    private Double quantite_f;
    private String idArticle;
    private int idMagasin;
    
    public void mouvement(String idarticle, String idmagasin, String idsortie, String quantite, String dateV, String dateD) throws Quantite, Exception{
        Connection connex = Postgres.connexion();
        try {
            //controlle de date basics
            this.controlleDatebasic(dateD, dateV);
            
            //verification de date anterieur avant tout sode misy tsy norme
            this.dateAnterieur(connex, dateD, idarticle, idmagasin);
            
            //get etat du article selectionner c-a-d FIFO ou LIFO
            int etat = new Article().getEtat(connex,idarticle);
          
            //get stock associer a cette article et a cette magasin
            ArrayList<Stock_reste> mouvement = this.getStock(connex, etat, idarticle,idmagasin);
            
            System.out.println(mouvement.size());
            //verification de mon stock si il y entre
            this.verifQuantiter(mouvement, quantite, connex);
            
            //insertion du validation
            Valide valide = new Valide(idsortie, dateV);
            valide.insertValidation(connex);

            //update quantiter transaction
            Mouvement mouv = new Mouvement();
            mouv.fluxMouvement(connex, mouvement,quantite, idsortie);
            
            //raha tsy misy blem de commiteko
            connex.commit();
        }catch (Quantite ex) {
            connex.rollback();
            throw new Quantite();
        }catch (DateAnterieur ex) {
            connex.rollback();
            throw new DateAnterieur();
        }catch (Exception ex) {
            connex.rollback();
            ex.printStackTrace();
        }finally {
            connex.close();
        }

    }
    
    public Quantiter getDateMaxArticleMagasin(Connection connex,String idArticle, String idmagasin) throws Exception{
        Quantiter quantite = new Quantiter();
        quantite = quantite.dateanterieur(connex, idArticle, idmagasin);
        return quantite;
    }
    
    public void controlleDatebasic(String date_demande, String date_validation) throws DateAnterieur, Exception{
        Date date_d = Date.valueOf(date_demande);
        Date date_v = Date.valueOf(date_validation);

        System.out.println("Demande "+date_demande);
        System.out.println("Valider "+date_validation);

        if(date_v.before(date_d)){
            throw new DateAnterieur();
        }
    }
    
    public void dateAnterieur(Connection connex, String date_demande, String idArticle, String idmagasin ) throws DateAnterieur, Exception{
        Quantiter stock = this.getDateMaxArticleMagasin(connex, idArticle, idmagasin); 
        
        if(stock != null){
            Date date_d = Date.valueOf(date_demande);
            Date date_m = Date.valueOf(stock.getDate_sortie_valide());

            System.out.println("Demande "+date_demande);
            System.out.println("sortie "+stock.getDate_sortie_valide());

            if(date_d.before(date_m)){
                throw new DateAnterieur();
            }
        } 
    }
    
    public void verifQuantiter(ArrayList<Stock_reste> mv, String quantite, Connection connex) throws Quantite, Exception{
        Double StockQt= sumQuantiterDemander(connex,mv);
        if(Double.valueOf(quantite)>StockQt){
            throw new Quantite();
        }
    }
    
    public Double sumQuantiterDemander(Connection connex,ArrayList<Stock_reste> mv) throws Exception{
        Double valeur = 0.0;    
        Double temp = 0.0;
        for (Stock_reste mouvement : mv) {
            if(mouvement.getQuantite_f() == 0){
                temp = this.getQuantiteEntreStock(connex, mouvement.getIdEntrer());
                valeur += temp;
                mouvement.setQuantite_f(temp);
            }else{
                valeur += mouvement.getQuantite_f();
            }            
        }
        return valeur;
    }
    
    public ArrayList<Stock_reste> getStock(Connection connex,int etat, String idArticle, String idMagasin) throws Exception{
        return getMouvementEntrer(connex, fifolifo(etat, idArticle, Integer.parseInt(idMagasin)));
    }
    
    public String fifolifo(int etat, String idArticle, int idMagasin){
        // 1 FiFo
        // 2 LiFo
        if(etat == 1){
            return "SELECT * FROM stock_reste WHERE idArticle = '"+idArticle+"' AND idMagasin = "+idMagasin+" ORDER BY Date_Entre ASC, idEntre ASC";
        }else{
            return "SELECT * FROM stock_reste WHERE idArticle = '"+idArticle+"' AND idMagasin = "+idMagasin+" ORDER BY Date_Entre DESC, idEntre DESC";
        }
    }
    
    public Double getQuantiteEntreStock(Connection connex, int identre) throws Exception{
        Connection con = connex;
        boolean verification = false;
        Double quantiter = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        String requete = "SELECT quantite FROM entre_stocke WHERE idEntre = "+identre ;
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                quantiter = res.getDouble("quantite");         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        return quantiter;
    }
    
    public ArrayList<Stock_reste> getMouvementEntrer(Connection connex, String requete) throws Exception{
        Connection con = connex;
        boolean verification = false;
        Stock_reste article;
        ArrayList<Stock_reste> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                article = new Stock_reste(); 
                article.setIdEntrer(res.getInt("idEntre"));
                article.setIdArticle(res.getString("idArticle"));
                article.setQuantite_f(res.getDouble("reste"));
                article.setPrix_unitaire(res.getDouble("prixUnitaire"));
                article.setIdMagasin(res.getInt("idmagasin"));
                article.setDate_in(String.valueOf(res.getDate("Date_Entre")));
                object.add(article);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        return object;
    }

    /**
     * @return the idEntrer
     */
    public int getIdEntrer() {
        return idEntrer;
    }

    /**
     * @param idEntrer the idEntrer to set
     */
    public void setIdEntrer(int idEntrer) {
        this.idEntrer = idEntrer;
    }

    /**
     * @return the date_in
     */
    public String getDate_in() {
        return date_in;
    }

    /**
     * @param date_in the date_in to set
     */
    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    /**
     * @return the prix_unitaire
     */
    public Double getPrix_unitaire() {
        return prix_unitaire;
    }

    /**
     * @param prix_unitaire the prix_unitaire to set
     */
    public void setPrix_unitaire(Double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    /**
     * @return the quantite_f
     */
    public Double getQuantite_f() {
        return quantite_f;
    }

    /**
     * @param quantite_f the quantite_f to set
     */
    public void setQuantite_f(Double quantite_f) {
        this.quantite_f = quantite_f;
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
