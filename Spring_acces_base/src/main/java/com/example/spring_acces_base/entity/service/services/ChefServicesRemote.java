package com.example.spring_acces_base.entity.service.services;

import com.example.spring_acces_base.entity.service.ChefServices;

import java.util.List;

public interface ChefServicesRemote {
    ChefServices findByEmailAndPasswords(String email, String password);
    List<ChefServices> findAll();
}
