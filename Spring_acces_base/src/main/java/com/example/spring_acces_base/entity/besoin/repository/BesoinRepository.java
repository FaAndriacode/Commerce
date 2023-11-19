package com.example.spring_acces_base.entity.besoin.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.besoin.Besoin;


@Repository
public interface BesoinRepository  extends CrudRepository<Besoin, Integer>{
    List<Besoin> findByEtat(int etat);
    Besoin findById(int idbesoin);
    //nouvelle methode
}
