package com.example.spring_acces_base.controller.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.besoin.Besoin;
import com.example.spring_acces_base.entity.besoin.services.BesoinService;
import com.example.spring_acces_base.entity.boncommende.BonCommande;
import com.example.spring_acces_base.entity.boncommende.VBonCommande;
import com.example.spring_acces_base.entity.boncommende.services.BonCommandeService;
import com.example.spring_acces_base.entity.temp.FournArticleTemp;
import com.example.spring_acces_base.entity.temp.services.FournArticleTempService;
import com.example.spring_acces_base.response.Response;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@RestController
@RequestMapping("/boncommande")
@CrossOrigin
public class BonCommandeController {

    @PersistenceContext
    private EntityManager entityManager;

    private BesoinService besoinService;
    private FournArticleTempService fournArticletempService;
    private BonCommandeService bonCommandeService;

    

    public BonCommandeController(BesoinService besoinService, FournArticleTempService fournArticletempService,
            BonCommandeService bonCommandeService) {
        this.besoinService = besoinService;
        this.fournArticletempService = fournArticletempService;
        this.bonCommandeService = bonCommandeService;
    }

    @PostMapping("/calcul")
    public Response calculBonCommande(@RequestParam String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Response response = new Response();
        try {
            java.util.Date utilDate = dateFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            calculBonCommande(sqlDate);
            List<List<VBonCommande>> lbc = getBonCommande(sqlDate); 

            Object temp = lbc;
            response.setDonner(temp);
            response.setErreur(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void calculBonCommande(Date date){

        List<Besoin> lb = this.besoinService.findByEtat(3);

        System.out.println("TONGASOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ==== "+lb.size());

        this.fournArticletempService.deleteAllTempRecords();
        this.fournArticletempService.copyEntities();

        double qtt = 0;
        double qttbesoin = 0;

        for (Besoin besoin : lb) {
            qttbesoin = besoin.getQuantite();
            System.out.println("QTTT BESOIN == "+ qttbesoin);
            while(qttbesoin > 0){
                FournArticleTemp f = findFournArticleTemp(besoin.getIdarticle(), date);
                if(f.getQuantite() < qttbesoin){
                    qttbesoin=qttbesoin-f.getQuantite();
                    System.out.println("QTTT BESOIN 11111== "+ qttbesoin);

                    BonCommande bc = new BonCommande();
                    bc.setIdarticle(besoin.getIdarticle());
                    bc.setIdfournisseur(f.getIdfournisseur());
                    bc.setQuantite(f.getQuantite());
                    System.out.println("QUANTITEEEEEEE 1 == "+ bc.getQuantite());
                    bc.setPrixunitaire(f.getPrixunitaire());
                    bc.setDate(date);
                    bonCommandeService.insertBonCommande(bc);

                    f.setQuantite(0);
                    this.fournArticletempService.updateEntity(f);
                }else{
                    qtt = f.getQuantite() - qttbesoin;
                    
                    BonCommande bc = new BonCommande();
                    bc.setIdarticle(besoin.getIdarticle());
                    bc.setIdfournisseur(f.getIdfournisseur());
                    bc.setQuantite(qttbesoin);
                    System.out.println("QUANTITEEEEEEE 2 == "+ bc.getQuantite());
                    bc.setPrixunitaire(f.getPrixunitaire());
                    bc.setDate(date);
                    bonCommandeService.insertBonCommande(bc);

                    f.setQuantite(qtt);
                    this.fournArticletempService.updateEntity(f);

                    qttbesoin=0; 
                }
            }
        }

    } 

    public FournArticleTemp findFournArticleTemp(int idArticle, Date targetDate) {
        String sqlQuery = "SELECT idfournarticle, idarticle, date, idfournisseur, prixunitaire, quantite " +
                "FROM fournarticletemp " +
                "WHERE idarticle = :idarticle AND quantite > 0 AND extract(month from date) = extract(month from CAST(:targetdate AS timestamp)) " +
                "ORDER BY prixunitaire ASC " +
                "FETCH FIRST 1 ROWS ONLY";
    
        Query query = entityManager.createNativeQuery(sqlQuery, FournArticleTemp.class);
        query.setParameter("idarticle", idArticle);
        query.setParameter("targetdate", targetDate);
    
        try {
            return (FournArticleTemp) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<List<VBonCommande>> getBonCommande(Date targetDate){

        Response response = new Response();
        Object temp = null;

        String sqlQuery = "SELECT * from v_boncommande where extract(month from date) = extract(month from CAST(:targetDate AS timestamp)) AND idfournisseur=1";
        
        Query query = entityManager.createNativeQuery(sqlQuery, VBonCommande.class);
        query.setParameter("targetDate", targetDate);

        String sqlQuery2 = "SELECT * from v_boncommande where extract(month from date) = extract(month from CAST(:targetDate AS timestamp)) AND idfournisseur=2";
        
        Query query2 = entityManager.createNativeQuery(sqlQuery2, VBonCommande.class);
        query2.setParameter("targetDate", targetDate);

        String sqlQuery3 = "SELECT * from v_boncommande where extract(month from date) = extract(month from CAST(:targetDate AS timestamp)) AND idfournisseur=3";
        
        Query query3 = entityManager.createNativeQuery(sqlQuery3, VBonCommande.class);
        query3.setParameter("targetDate", targetDate);

        List<VBonCommande> un = query.getResultList();
        List<VBonCommande> deux = query2.getResultList();
        List<VBonCommande> trois = query3.getResultList();

        List<List<VBonCommande>> bc = new ArrayList<>();
        bc.add(un);
        bc.add(deux);
        bc.add(trois);

        try {
            return bc;
        } catch (Exception e) {
            return null;
        }

    }

}
