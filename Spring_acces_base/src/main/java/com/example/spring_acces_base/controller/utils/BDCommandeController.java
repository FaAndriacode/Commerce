package com.example.spring_acces_base.controller.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.fournarticle.repository.FournArticleRepository;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/bondecommande")
@CrossOrigin
public class BDCommandeController {
    @Autowired
    private FournArticleRepository fournarticleRepository;

    @GetMapping("/all")
    public Response getAllBonCommande() {
        Response response = new Response();
        try {
            response.setDonner(fournarticleRepository.findByMultipleConditions(1));
            response.setErreur(false);
            return response;
        } catch (Exception e) {
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;  
        }
    }
}