package com.example.spring_acces_base.entity.boncommende;

import java.sql.Date;
import java.util.List;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.besoin.Besoin;
import com.example.spring_acces_base.entity.besoin.services.BesoinService;
import com.example.spring_acces_base.entity.boncommende.services.BonCommandeService;
import com.example.spring_acces_base.entity.fournarticle.FournArticle;
import com.example.spring_acces_base.entity.fournarticle.services.FournArticleService;
import com.example.spring_acces_base.entity.fournisseur.Fournisseur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "boncommande")
public class BonCommande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boncommande_sequence")
    @SequenceGenerator(name = "boncommande_sequence", sequenceName = "n_idboncommande", allocationSize = 1)
    @Column(name = "idboncommande")
    int idboncommande;

    @Column(name = "idfournisseur")
    int idfournisseur;

    @ManyToOne
    @JoinColumn(name = "idfournisseur", insertable= false, updatable = false)
    private Fournisseur fournisseur;

    @Column(name = "idarticle")
    int idarticle;

    @ManyToOne
    @JoinColumn(name = "idarticle", insertable= false, updatable = false)
    private Article article;

    @Column(name = "prixunitaire")
    double prixunitaire;

    @Column(name = "quantite")
    double quantite;

    @Column(name = "date")
    Date date;

    public int getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(int idboncommande) {
        this.idboncommande = idboncommande;
    }

    public int getIdfournisseur() {
        return idfournisseur;
    }

    public void setIdfournisseur(int idfournisseur) {
        this.idfournisseur = idfournisseur;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void calculBonCommande(Date date){

        BesoinService besoinService = new BesoinService();
        List<Besoin> lb = besoinService.findByEtat(3);

        FournArticleService fs = new FournArticleService();
        fs.deleteAllTempRecords();
        fs.copyDataToTempTable();

        BonCommandeService bs = new BonCommandeService();

        double qtt = 0;

        BonCommande bc = new BonCommande();

        for (Besoin besoin : lb) {
            FournArticle f = fs.findByMultipleConditions(besoin.getIdarticle(), date);
            while(besoin.getQuantite() > 0){
                if(f.getQuantite() < besoin.getQuantite()){
                    besoin.setQuantite(besoin.getQuantite()-f.getQuantite());
                    
                    bc.setIdarticle(besoin.getIdarticle());
                    bc.setIdfournisseur(f.getIdfournisseur());
                    bc.setQuantite(f.getQuantite());
                    bc.setPrixunitaire(prixunitaire);
                    bc.setDate(date);
                    bs.insertBonCommande(bc);

                    fs.updateQuantite(f.getIdfournarticle(), 0);
                }else{
                    qtt = f.getQuantite() - besoin.getQuantite();
                    
                    bc.setIdarticle(besoin.getIdarticle());
                    bc.setIdfournisseur(f.getIdfournisseur());
                    bc.setQuantite(qtt);
                    bc.setPrixunitaire(prixunitaire);
                    bc.setDate(date);
                    bs.insertBonCommande(bc);

                    fs.updateQuantite(f.getIdfournarticle(), qtt);
                    besoin.setQuantite(0);
                }
            }
        }

    } 
    
}
