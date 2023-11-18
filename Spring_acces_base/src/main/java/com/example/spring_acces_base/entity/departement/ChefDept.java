package com.example.spring_acces_base.entity.departement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="chefdept")
public class ChefDept {
    
    @Id
    @Column(name = "iduserdept")
    long idUserDept;

    @Column(name = "email")
    String email;

    @Column(name = "passwords")
    String passwords;

    @Column(name = "iddept")
    int idDept;

    public ChefDept(){}

    public ChefDept(String email, String passwords, int idDept) {
        this.email = email;
        this.passwords = passwords;
        this.idDept = idDept;
    }
    
    public long getIdUserDept() {
        return idUserDept;
    }
    public void setIdUserDept(long idUserDept) {
        this.idUserDept = idUserDept;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswords() {
        return passwords;
    }
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
    public int getIdDept() {
        return idDept;
    }
    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }

}
