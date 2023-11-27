package com.example.spring_acces_base.entity.fournarticle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;

@Repository
public interface FournArticleRepository extends CrudRepository<FournArticle, Integer> {

}
