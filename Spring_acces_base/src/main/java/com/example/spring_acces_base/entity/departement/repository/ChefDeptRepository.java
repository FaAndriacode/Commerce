package com.example.spring_acces_base.entity.departement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_acces_base.entity.departement.ChefDept;

@Repository
public interface ChefDeptRepository extends JpaRepository<ChefDept, Long>{
    ChefDept findByEmailAndPasswords(String email, String password);
    List<ChefDept> findAll();

    // Nouvelle méthode pour sauvegarder un ChefDept
}
