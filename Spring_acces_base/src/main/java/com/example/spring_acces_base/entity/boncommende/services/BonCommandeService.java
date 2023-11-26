package com.example.spring_acces_base.entity.boncommende.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.boncommende.BonCommande;
import com.example.spring_acces_base.entity.boncommende.repository.BonCommandeRepository;

@Service
public class BonCommandeService {
    @Autowired
    private BonCommandeRepository bonCommandeRepository;

    public void insertBonCommande(BonCommande bonCommande) {
        this.bonCommandeRepository.save(bonCommande);
    }

    public List<BonCommande> selectAll() {
        return bonCommandeRepository.findAll();
    }

}
