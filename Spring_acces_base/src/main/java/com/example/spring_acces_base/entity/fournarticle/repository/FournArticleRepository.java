package com.example.spring_acces_base.entity.fournarticle.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;

@Repository
public interface FournArticleRepository extends CrudRepository<FournArticle, Integer> {
    @Query("SELECT e.* FROM fournarticle e WHERE e.idarticle = :value1 AND e.prixunitaire = MIN(prixunitaire) group by idfournisseur")
    List<FournArticle> findByMultipleConditions(
        @Param("value1") int value1
    );
}
