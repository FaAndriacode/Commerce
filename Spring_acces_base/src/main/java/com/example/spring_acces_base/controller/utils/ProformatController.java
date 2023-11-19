package com.example.spring_acces_base.controller.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.fournisseur.services.FournisseurServices;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/proformat")
@CrossOrigin
public class ProformatController {
    private FournisseurServices fournisseurService;

    public ProformatController(FournisseurServices fournisseurService){
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("/fournisseur")
    public Response listFournisseur(){
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 Demande liste de fournisseur");
        
        temp = this.fournisseurService.findAll();
        response.setDonner(temp);
        response.setErreur(false);
        
        return response;
    }
}
