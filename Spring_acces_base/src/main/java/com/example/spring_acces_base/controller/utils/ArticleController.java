package com.example.spring_acces_base.controller.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.article.services.ArticleService;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public Response getAllArticles() {
        Response response = new Response();
        Object temp = null;

        System.out.println("👉 Demande liste de article");
        
        temp = this.articleService.findAll();
        response.setDonner(temp);
        response.setErreur(false);
        
        return response;
    }

    @PostMapping("/insert")
    public Response insertArticle(@RequestBody Article article) {
        Response response = new Response();
        Object temp = null;
        try {
            temp = articleService.insertArticle(article);
            response.setDonner(temp);
            response.setErreur(false);
            return response;
        } catch (Exception e) {
            // Log the exception for debugging
            response.setErreur(true);
            response.setDonner(null);
            e.printStackTrace();
            return response;
        }
    }

    
}
