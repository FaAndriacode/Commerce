package com.example.spring_acces_base.controller.utilController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.EtatStock;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/etat")
@CrossOrigin
public class UtilController {
    private EtatStock etatStock;
    private Response response;

    public UtilController(EtatStock etatStock, Response response) {
        this.response = response;
        this.etatStock = etatStock;
    }

    @PostMapping("stock")
    public Response etatStock(@RequestParam(required = false) String dateD, @RequestParam(required = false) String dateF, @RequestParam String idArticle, @RequestParam int idMagasin){
        try {
            System.out.println(dateD+" "+dateF+" "+idArticle+" "+idMagasin);            
            this.response.setErreur(false);
            this.response.setDonner( etatStock.getEtatStock(dateD, dateF, idArticle, idMagasin));
            return this.response;
        } catch (Exception e) {
            this.response.setErreur(false);
            this.response.setDonner( null);
            return this.response;
        }
    }
    
}
