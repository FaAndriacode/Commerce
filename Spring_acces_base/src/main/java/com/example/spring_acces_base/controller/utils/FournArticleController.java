package com.example.spring_acces_base.controller.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;
import com.example.spring_acces_base.entity.fournarticle.services.FournArticleService;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/fournarticle")
@CrossOrigin
public class FournArticleController {
    private FournArticleService fournarticleService;

    public FournArticleController(FournArticleService fournarticleService){
        this.fournarticleService = fournarticleService;
    }

    @PostMapping("/insert")
    public Response insertArticle(@RequestBody FournArticle fournarticle) {
        Response response = new Response();
        try {
            response.setDonner(fournarticleService.insertFournArticle(fournarticle));
            response.setErreur(false);
            return response;
        } catch (Exception e) {
            response.setDonner(null);
            response.setErreur(true);
            e.printStackTrace();
            return response;
        }
    }

}
