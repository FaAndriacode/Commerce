package com.example.spring_acces_base.entity.fournarticle.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;
import com.example.spring_acces_base.entity.fournarticle.repository.FournArticleRepository;

@Service
public class FournArticleService implements FournArticleRemote{
    @Autowired
    private FournArticleRepository fournarticleRepository;

    public FournArticle insertFournArticle(FournArticle fournarticle) {
        return fournarticleRepository.save(fournarticle);
    }

    public FournArticle findByMultipleConditions(int idarticle, Date date){
        return fournarticleRepository.findByMultipleConditions(idarticle, date);
    }

    public void deleteAllTempRecords(){
        fournarticleRepository.deleteAllTempRecords();
    }

    public void copyDataToTempTable(){
        fournarticleRepository.copyDataToTempTable();
    }

    public void updateQuantite(int idfournarticle, double quantite){
        fournarticleRepository.updateQuantite(idfournarticle, quantite);
    }

}
