package com.example.spring_acces_base.entity.fournarticle.repository;

import java.sql.Date;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;

@Repository
public interface FournArticleRepository extends CrudRepository<FournArticle, Integer> {
    
    @Query("SELECT e FROM FournArticleTemp e WHERE e.idarticle = :value1 and e.quantite > 0 and DATE_TRUNC('month', e.date) = DATE_TRUNC('month', :value2 ) and e.prixunitaire = (SELECT MIN(f.prixunitaire) FROM FournArticleTemp f WHERE f.idarticle = :value1)")
    FournArticle findByMultipleConditions(
        @Param("value1") int value1,
        @Param("value2") Date value2
    );

    @Modifying
    @Query("DELETE FROM FournArticleTemp")
    void deleteAllTempRecords();

    @Modifying
    @Query("INSERT INTO FournArticleTemp SELECT * FROM FournArticle")
    void copyDataToTempTable();

    @Modifying
    @Query("UPDATE FournArticleTemp SET quantite = :newQuantite WHERE idfournarticle = :fournarticleId")
    void updateQuantite(
        @Param("fournarticleId") int fournarticleId,
        @Param("newQuantite") double newQuantite
    );

}
