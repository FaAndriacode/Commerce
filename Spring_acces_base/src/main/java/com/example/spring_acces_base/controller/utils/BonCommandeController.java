package com.example.spring_acces_base.controller.utils;

import java.sql.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.boncommende.BonCommande;

@RestController
@RequestMapping("/boncommande")
@CrossOrigin
public class BonCommandeController {

    public BonCommandeController(){

    }

    @PostMapping("/calcul")
    public void calculBonCommande(@RequestParam Date date) {

        try {
            BonCommande bc = new BonCommande();
            bc.calculBonCommande(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
