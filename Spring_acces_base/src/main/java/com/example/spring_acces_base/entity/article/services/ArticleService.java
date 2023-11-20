package com.example.spring_acces_base.entity.Article.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.Article.Article;
import com.example.spring_acces_base.entity.Article.repository.ArticleRepository;

@Service
public class ArticleService implements ArticleRemote {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public Article insertArticle(Article article) {
        return this.articleRepository.save(article);
    }
    
}
