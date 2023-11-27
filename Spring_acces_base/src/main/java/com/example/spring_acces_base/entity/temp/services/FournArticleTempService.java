package com.example.spring_acces_base.entity.temp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.fournarticle.FournArticle;
import com.example.spring_acces_base.entity.fournarticle.repository.FournArticleRepository;
import com.example.spring_acces_base.entity.temp.FournArticleTemp;
import com.example.spring_acces_base.entity.temp.repository.FournArticleTempRepository;

@Service
public class FournArticleTempService implements FournArticleTempRemote {

    @Autowired
    private FournArticleRepository fournarticleRepository;

    @Autowired
    private FournArticleTempRepository fournarticletempRepository;

    public List<FournArticleTemp> selectAll() {
        return fournarticletempRepository.findAll();
    }

    public void deleteAllTempRecords() {
        fournarticletempRepository.deleteAll();
    }

    public FournArticleTemp updateEntity(FournArticleTemp updatedEntity) {
        // Perform validation or additional logic if needed
        return fournarticletempRepository.save(updatedEntity);
    }

    public void copyEntities() {
        Iterable<FournArticle> sourceEntities = fournarticleRepository.findAll();

        for (FournArticle sourceEntity : sourceEntities) {
            // Create a new destination entity with the same values
            FournArticleTemp destinationEntity = new FournArticleTemp();
            destinationEntity.setIdfournarticle(sourceEntity.getIdfournarticle());
            destinationEntity.setIdfournisseur(sourceEntity.getIdfournisseur());
            destinationEntity.setIdarticle(sourceEntity.getIdarticle());
            destinationEntity.setPrixunitaire(sourceEntity.getPrixunitaire());
            destinationEntity.setQuantite(sourceEntity.getQuantite());
            destinationEntity.setDate(sourceEntity.getDate());
            // Set other fields as needed

            // Save the destination entity to the destination table
            fournarticletempRepository.save(destinationEntity);
        }
    }

}
