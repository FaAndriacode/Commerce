package com.example.spring_acces_base.entity.fournisseur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.fournisseur.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    List<Fournisseur> findAll();

    //nouvelle methode pour sauvegarder 🤣
}
