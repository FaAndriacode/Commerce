package com.example.spring_acces_base.controller.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import com.example.spring_acces_base.entity.boncommende.services.BonCommandeService;
import com.example.spring_acces_base.entity.temp.FournArticleTemp;
import com.example.spring_acces_base.entity.temp.services.FournArticleTempService;

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
    public ResponseEntity<String> calculBonCommande(@RequestParam String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            calculBonCommande(sqlDate);
            return ResponseEntity.ok("Calculation successful"); // Or any other success message
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Calculation failed"); // Or any other error message
        }
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

}
