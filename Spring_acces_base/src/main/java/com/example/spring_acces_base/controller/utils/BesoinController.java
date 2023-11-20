package com.example.spring_acces_base.controller.utils;

import java.sql.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.besoin.Besoin;
import com.example.spring_acces_base.entity.besoin.services.BesoinService;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/list")
@CrossOrigin
public class BesoinController {
    private BesoinService besoinService;

    public BesoinController(BesoinService besoinService){
        this.besoinService = besoinService;
    }

    @GetMapping("/besoin")
    public Response listeBesoin(@RequestParam int etat){
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 Value --> "+etat);
        /*                                                              
         * etat = 1 besion en attente
         * etat = 2 besoin refuser
         * etat = 3 besoin confirmer
        */
        if(etat == 1){
            System.out.println("👉 etat attente");
            temp = this.besoinService.findByEtat(etat);
            response.setDonner(temp);
            response.setErreur(false);
        }else if (etat == 2) {
            System.out.println("👉 etat refuser");
            temp = this.besoinService.findByEtat(etat);
            response.setDonner(temp);
            response.setErreur(false);
        }else if (etat == 3) {
            System.out.println("👉 etat en confirmer");
            temp = this.besoinService.findByEtat(etat);
            response.setDonner(temp);
            response.setErreur(false);
        }
        return response;
    }

    @GetMapping("/confirmer")
    public Response AcceptBesoin(@RequestParam int idbesoin){
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 Value reçue pour l'update de confirmation --> "+idbesoin);
        
        temp = this.besoinService.updateEtatById(idbesoin, 3);
        response.setDonner(temp);
        response.setErreur(false);
        
        return response;
    }

    @GetMapping("/delete")
    public Response RefusBesoin(@RequestParam int idbesoin){
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 Value reçue pour l'update de suppresion --> "+idbesoin);
        
        temp = this.besoinService.updateEtatById(idbesoin, 2);
        response.setDonner(temp);
        response.setErreur(false);
        
        return response;
    }

    @PostMapping("/creation")
    public Response insertBesoin(@RequestParam int idarticle, @RequestParam double quantite, @RequestParam Date date_creation, @RequestParam int idservices){
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 creation de besoin idarticle --> "+idarticle);
        System.out.println("👉 creation de besoin quantite --> "+quantite);
        System.out.println("👉 creation de besoin date_creation --> "+date_creation);
        System.out.println("👉 creation de besoin idservices --> "+idservices);

        Besoin besoin = new Besoin(idarticle, idservices ,quantite, date_creation, 1);
        
        temp = this.besoinService.save(besoin);
        response.setDonner(temp);
        response.setErreur(false);
        
        return response;
    }
    
}
