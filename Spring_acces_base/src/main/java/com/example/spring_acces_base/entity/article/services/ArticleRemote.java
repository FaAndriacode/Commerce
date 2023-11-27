package com.example.spring_acces_base.entity.article.services;

import java.util.List;

import com.example.spring_acces_base.entity.article.Article;

public interface ArticleRemote {
    List<Article> findAll();
}
