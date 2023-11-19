package com.example.spring_acces_base.controller.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.article.services.ArticleService;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        try {
            List<Article> articles = articleService.getAllArticles();
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertArticle(@RequestBody Article article) {
        try {
            Article newArticle = articleService.insertArticle(article);
            return ResponseEntity.ok("Article inserted successfully with ID: " + newArticle.getIdArticle());
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inserting Article");
        }
    }

    
}
