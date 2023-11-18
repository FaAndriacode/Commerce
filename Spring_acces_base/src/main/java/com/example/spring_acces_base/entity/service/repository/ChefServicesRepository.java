package com.example.spring_acces_base.entity.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.service.ChefServices;

@Repository
public interface ChefServicesRepository extends JpaRepository<ChefServices, Long>{
    ChefServices findByEmailAndPasswords(String email, String password);
    List<ChefServices> findAll();

     // Nouvelle méthode pour sauvegarder un ChefServices
}
