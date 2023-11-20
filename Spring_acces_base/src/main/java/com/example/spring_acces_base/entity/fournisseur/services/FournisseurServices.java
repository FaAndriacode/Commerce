package com.example.spring_acces_base.entity.fournisseur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.fournisseur.Fournisseur;
import com.example.spring_acces_base.entity.fournisseur.repository.FournisseurRepository;

import java.util.List;

@Service
public class FournisseurServices implements FournisseurRemote{
    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Override
    public List<Fournisseur> findAll() {
       return this.fournisseurRepository.findAll();
    }
}
