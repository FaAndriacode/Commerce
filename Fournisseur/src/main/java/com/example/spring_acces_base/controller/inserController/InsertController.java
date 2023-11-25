package com.example.spring_acces_base.controller.inserController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.StockIn;
import com.example.spring_acces_base.entity.StockOut;
import com.example.spring_acces_base.entity.Stock_reste;
import com.example.spring_acces_base.entity.utils.Article;
import com.example.spring_acces_base.exeption.Different;
import com.example.spring_acces_base.exeption.Quantite;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/insert")
@CrossOrigin
public class InsertController {
    private Article article;
    private StockIn entre;
    private StockOut sorti;
    private Stock_reste stock_reste;
    private Response response;

    public InsertController(Article article, StockIn entre, StockOut sorti, Response response, Stock_reste stock_reste) {
        this.article = article;
        this.response = response;
        this.entre = entre;
        this.sorti = sorti;
        this.stock_reste = stock_reste;
    }

    @PostMapping("article")
    public Response insertArticle(@RequestParam String code, @RequestParam String nom, @RequestParam int etat, @RequestParam String idequivalance){
        this.article = new Article(code, nom, etat, idequivalance);
        try {
            this.article.insertArticle(null);
            this.response.setDonner(null);
            this.response.setErreur(false);
            return this.response;
        } catch (Exception e) {
            this.response.setDonner(null);
            this.response.setErreur(false);
            e.printStackTrace();
            return this.response;
        }
    }

    @PostMapping("entre")
    public Response insertEntre(@RequestParam Double quantiteo, @RequestParam Double quantite, @RequestParam int idunite, @RequestParam String idArticle, @RequestParam Double prix, @RequestParam String date_in, @RequestParam int idmagasin){
        try {
            this.entre.insertion(quantiteo, quantite, idunite, idArticle, prix, date_in, idmagasin);
            this.response.setErreur(false);
            this.response.setDonner(null);
            return this.response;
        } catch (Different e) {
            System.out.println(e.getMessage());
            this.response.setErreur(true);
            this.response.setDonner(null);
            return this.response;
        } catch (Exception e){
            e.printStackTrace();
             this.response.setErreur(true);
            this.response.setDonner(null);
            return this.response;
        }
    }

    @PostMapping("sortie")
    public Response insertSortie(@RequestParam Double quantite, @RequestParam int idunite, @RequestParam String idArticle, @RequestParam String date_out, @RequestParam int idmagasin){
        try {
            this.sorti.insertion(quantite, idunite, idArticle, date_out, idmagasin);
            this.response.setErreur(false);
            this.response.setDonner(null);
            return this.response;
        } catch (Different e) {
            System.out.println(e.getMessage());
            this.response.setErreur(true);
            this.response.setDonner(null);
            return this.response;
        } catch (Exception e){
            this.response.setErreur(true);
            this.response.setDonner(null);
            e.printStackTrace();
            return this.response;
        }
        
    }

    @GetMapping("valide")
    public Response insertValide(@RequestParam String idarticle, @RequestParam String idmagasin, @RequestParam String idsortie, @RequestParam String quantite, @RequestParam String dateV, @RequestParam String dateD){
        try {
            this.stock_reste.mouvement(idarticle, idmagasin, idsortie, quantite, dateV, dateD);
            this.response.setDonner(null);
            this.response.setErreur(false);
            return this.response;
        } catch (Quantite e) {
            System.out.println(e.getMessage());
            this.response.setDonner(null);
            this.response.setErreur(true);
            return this.response;
        } catch (Exception e) {
            this.response.setDonner(null);
            this.response.setErreur(true);
            e.printStackTrace();
            return this.response;
        }
   }
}
