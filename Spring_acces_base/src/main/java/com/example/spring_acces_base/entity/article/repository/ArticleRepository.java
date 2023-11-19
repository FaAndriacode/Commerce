package com.example.spring_acces_base.entity.article.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.article.Article;

@Repository
public interface ArticleRepository  extends CrudRepository<Article, Integer>{
    
}