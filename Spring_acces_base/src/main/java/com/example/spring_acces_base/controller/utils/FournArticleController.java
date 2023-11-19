package com.example.spring_acces_base.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;
import com.example.spring_acces_base.entity.fournarticle.services.FournArticleService;

@RestController
@RequestMapping("/fournarticle")
@CrossOrigin
public class FournArticleController {
    private FournArticleService fournarticleService;

    public FournArticleController(FournArticleService fournarticleService){
        this.fournarticleService = fournarticleService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertArticle(@RequestBody FournArticle fournarticle) {
        try {
            FournArticle newFournArticle = fournarticleService.insertFournArticle(fournarticle);
            return ResponseEntity.ok("FaournArticle inserted successfully with ID : "+newFournArticle.getIdfournarticle());
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inserting Article");
        }
    }

}
