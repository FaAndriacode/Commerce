package com.example.spring_acces_base.entity.besoin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.besoin.Besoin;
import com.example.spring_acces_base.entity.besoin.repository.BesoinRepository;

@Service
public class BesoinService implements BesoinRemote {
    @Autowired
    private BesoinRepository besoinRepository;

    @Override
    public List<Besoin> findByEtat(int etat) {
        return this.besoinRepository.findByEtat(etat);
    }

    @Override
    public Besoin updateEtatById(int idBesoin, int etat) {
        Besoin besoin = this.besoinRepository.findById(idBesoin);
        besoin.setEtat(etat);
        return this.besoinRepository.save(besoin);
    }
    
}
