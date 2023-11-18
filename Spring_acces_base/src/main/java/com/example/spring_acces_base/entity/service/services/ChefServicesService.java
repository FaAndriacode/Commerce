package com.example.spring_acces_base.entity.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.service.ChefServices;
import com.example.spring_acces_base.entity.service.repository.ChefServicesRepository;

import java.util.List;

@Service
public class ChefServicesService implements ChefServicesRemote{
    @Autowired
    private ChefServicesRepository userRepository;

    @Override
    public ChefServices findByEmailAndPasswords(String email, String password) {
       return userRepository.findByEmailAndPasswords(email, password);
    }

    @Override
    public List<ChefServices> findAll() {
       return userRepository.findAll();
    }
}
