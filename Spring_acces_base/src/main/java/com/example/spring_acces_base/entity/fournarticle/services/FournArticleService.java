package com.example.spring_acces_base.entity.fournarticle.services;

import java.util.List;

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

    public List<FournArticle> findByMultipleConditions(){
        return fournarticleRepository.findByMultipleConditions(1);
    }

}
