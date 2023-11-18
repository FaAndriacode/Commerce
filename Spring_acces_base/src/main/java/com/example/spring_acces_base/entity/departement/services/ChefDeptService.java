package com.example.spring_acces_base.entity.departement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_acces_base.entity.departement.ChefDept;
import com.example.spring_acces_base.entity.departement.repository.ChefDeptRepository;

import java.util.List;

@Service
public class ChefDeptService implements ChefDeptRemote {
    @Autowired
    private ChefDeptRepository userRepository;

    @Override
    public ChefDept findByEmailAndPasswords(String email, String password) {
       return userRepository.findByEmailAndPasswords(email, password);
    }

    @Override
    public List<ChefDept> findAll() {
       return userRepository.findAll();
    }
    
}
