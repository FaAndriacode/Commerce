package com.example.spring_acces_base.entity.boncommende.repository;

import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.boncommende.BonCommande;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface BonCommandeRepository extends CrudRepository<BonCommande, Integer>{
    List<BonCommande> findAll();
}
