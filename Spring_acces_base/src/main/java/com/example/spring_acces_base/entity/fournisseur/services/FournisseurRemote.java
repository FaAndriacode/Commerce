package com.example.spring_acces_base.entity.fournisseur.services;

import com.example.spring_acces_base.entity.fournisseur.Fournisseur;

import java.util.List;

public interface FournisseurRemote {
     List<Fournisseur> findAll();
}
