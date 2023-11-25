package com.example.spring_acces_base.controller.frontController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.utils.Article;
import com.example.spring_acces_base.entity.utils.Etat;
import com.example.spring_acces_base.entity.utils.Liste;
import com.example.spring_acces_base.entity.utils.Magasin;
import com.example.spring_acces_base.entity.utils.Unite;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/init")
@CrossOrigin
public class FrontController {
    private Article article;
    private Magasin magasin;
    private Unite unite;
    private Etat etat;
    private Liste liste;

    public FrontController(Article article, Magasin magasin, Unite unite, Etat etat, Liste liste){
        this.article = article;
        this.magasin = magasin;
        this.unite = unite;
        this.etat = etat;
        this.liste = liste;
    }

    @GetMapping("magasin")
    public Response getListMagasin(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(magasin.getListeMagasin(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    @GetMapping("article")
    public Response getListArticle(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(article.getListeArticle(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    @GetMapping("unite")
    public Response getListeUnite(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(unite.getListeUnite(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    @GetMapping("etat")
    public Response getListeEtat(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(etat.getListeEtat(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    @GetMapping("sortie")
    public Response getListeSortie(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(liste.listeSortie(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    @GetMapping("entre")
    public Response getListeEntre(){
        Response response = new Response();
        try {
            response.setErreur(false);
            response.setDonner(liste.listeEntre(null));
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }
}
