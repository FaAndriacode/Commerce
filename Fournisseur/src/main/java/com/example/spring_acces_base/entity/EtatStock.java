package com.example.spring_acces_base.entity;

import com.example.spring_acces_base.connexion.Postgres;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class EtatStock {
    private int idMagasin;
    private String idArticle;
    private String nomMagasin;
    private String nomArticle;
    private Double stockInitial;
    private Double stockFinal;
    private Double montantT;
    private Double prixMoyenne;
    
    public  Object[]  getEtatStock(String dateD, String dateF, String idArticle, int idMagasin) throws Exception{ 
        Connection connex = Postgres.connexion();
        Object[] response = null;
        try {
            String temp;
            if(dateD == null && dateF == null){
                temp = dateMaxSortie(connex).toString();
                dateD = temp;
                dateF = temp;
            }
            response = getListeStock(connex, dateD, dateF, idArticle, idMagasin);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            connex.close();
        }
        return response;
    }

    public Object[] getListeStock(Connection connex, String datedebut, String datefin, String idarticle, int idMagasin) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete;
        EtatStock stock;
        ArrayList<EtatStock> object = new ArrayList<>();
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT e.idarticle,e.idmagasin,e.stock_initial,e.quantite_finale,CASE WHEN e.quantite_finale <> 0 THEN SUM(e.montant) / e.quantite_finale ELSE 0 END as prixmoyen,SUM(e.montant) AS montant,m.nom AS magasin,a.nom AS article FROM etat_stock('"+datedebut+"', '"+datefin+"') e JOIN magasin m ON e.idmagasin = m.idmagasin JOIN article a ON e.idarticle = a.idarticle WHERE e.idarticle LIKE '"+idarticle+"%' AND e.idmagasin = "+idMagasin+" GROUP BY e.idarticle, e.idmagasin, e.stock_initial, e.quantite_finale, m.nom, a.nom";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                stock = new EtatStock();
                stock.setIdMagasin(res.getInt("idMagasin"));
                stock.setNomArticle(res.getString("article"));
                stock.setNomMagasin(res.getString("magasin"));
                stock.setIdArticle(res.getString("idarticle"));
                stock.setStockInitial(res.getDouble("stock_initial"));
                stock.setStockFinal(res.getDouble("quantite_finale"));
                stock.setPrixMoyenne(res.getDouble("prixmoyen"));
                stock.setMontantT(res.getDouble("montant"));
                object.add(stock);             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        Object[] temporaire = object.toArray();
        EtatStock[] conf = new EtatStock[temporaire.length];
        System.arraycopy(temporaire, 0, conf, 0, conf.length); 
        
        return conf;
    }
        
    public Date dateMinEntre(Connection connex) throws Exception{ //entre
        Connection con = connex;
        boolean verification = false;
        String requete = null;
        Date date = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT MIN(Date_Entre) AS Date FROM entre_stocke";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                date = res.getDate("Date");         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        
        return date;
    }
    
    public Date dateMinSortie(Connection connex) throws Exception{ //entre
        Connection con = connex;
        boolean verification = false;
        String requete = null;
        Date date = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT MIN(Date_sortie) AS Date FROM sortie_stocke";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                date = res.getDate("Date");         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        
        return date;
    }
    
    public Date dateMaxEntrer(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete = null;
        Date date = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT MAX(Date_Entre) AS Date FROM entre_stocke";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                date = res.getDate("Date");         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        
        return date;
    }
    
    public Date dateMaxSortie(Connection connex) throws Exception{
        Connection con = connex;
        boolean verification = false;
        String requete = null;
        Date date = null;
        if(con == null){
            verification = true;
            con = Postgres.connexion();
        }
        requete = "SELECT MAX(Date_sortie) AS Date FROM sortie_stocke";
        System.out.println(requete);
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            while(res.next()){
                date = res.getDate("Date");         
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(verification) con.close();
        }
        
        return date;
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
     * @return the nomMagasin
     */
    public String getNomMagasin() {
        return nomMagasin;
    }

    /**
     * @param nomMagasin the nomMagasin to set
     */
    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    /**
     * @return the nomArticle
     */
    public String getNomArticle() {
        return nomArticle;
    }

    /**
     * @param nomArticle the nomArticle to set
     */
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    /**
     * @return the stockInitial
     */
    public Double getStockInitial() {
        return stockInitial;
    }

    /**
     * @param stockInitial the stockInitial to set
     */
    public void setStockInitial(Double stockInitial) {
        this.stockInitial = stockInitial;
    }

    /**
     * @return the stockFinal
     */
    public Double getStockFinal() {
        return stockFinal;
    }

    /**
     * @param stockFinal the stockFinal to set
     */
    public void setStockFinal(Double stockFinal) {
        this.stockFinal = stockFinal;
    }

    /**
     * @return the montantT
     */
    public Double getMontantT() {
        return montantT;
    }

    /**
     * @param montantT the montantT to set
     */
    public void setMontantT(Double montantT) {
        this.montantT = montantT;
    }

    /**
     * @return the prixMoyenne
     */
    public Double getPrixMoyenne() {
        return prixMoyenne;
    }

    /**
     * @param prixMoyenne the prixMoyenne to set
     */
    public void setPrixMoyenne(Double prixMoyenne) {
        this.prixMoyenne = prixMoyenne;
    }
}
