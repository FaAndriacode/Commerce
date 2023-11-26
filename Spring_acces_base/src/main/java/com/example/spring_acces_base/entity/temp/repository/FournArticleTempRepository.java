package com.example.spring_acces_base.entity.temp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.temp.FournArticleTemp;

@Repository
public interface FournArticleTempRepository extends CrudRepository<FournArticleTemp, Integer> {

    List<FournArticleTemp> findAll();

    void deleteAll();

}
