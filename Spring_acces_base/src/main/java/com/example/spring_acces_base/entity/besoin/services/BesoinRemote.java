package com.example.spring_acces_base.entity.besoin.services;

import java.util.List;

import com.example.spring_acces_base.entity.besoin.Besoin;

public interface BesoinRemote {
    List<Besoin> findByEtat(int etat);
    Besoin updateEtatById(int idBesoin, int etat);
    Besoin save(Besoin besoin);
}
