package com.example.spring_acces_base.entity.departement.services;

import com.example.spring_acces_base.entity.departement.ChefDept;

import java.util.List;

public interface ChefDeptRemote {
    ChefDept findByEmailAndPasswords(String email, String password);
    List<ChefDept> findAll();
}
