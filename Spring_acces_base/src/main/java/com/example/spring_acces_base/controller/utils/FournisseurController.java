package com.example.spring_acces_base.controller.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.article.services.ArticleService;
import com.example.spring_acces_base.entity.fournisseur.Fournisseur;
import com.example.spring_acces_base.entity.fournisseur.services.FournisseurServices;

@RestController
@RequestMapping("/fournisseur")
@CrossOrigin
public class FournisseurController {

    private FournisseurServices fournisseurService;

    public FournisseurController(FournisseurServices fournisseurService){
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fournisseur>> getAllArticles() {
        try {
            List<Fournisseur> fournisseur = fournisseurService.findAll();
            return ResponseEntity.ok(fournisseur);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
