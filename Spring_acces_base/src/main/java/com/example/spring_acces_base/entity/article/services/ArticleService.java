package com.example.spring_acces_base.entity.article.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.article.Article;
import com.example.spring_acces_base.entity.article.repository.ArticleRepository;

@Service
public class ArticleService implements ArticleRemote {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    public Article insertArticle(Article article) {
        return this.articleRepository.save(article);
    }
    
}
