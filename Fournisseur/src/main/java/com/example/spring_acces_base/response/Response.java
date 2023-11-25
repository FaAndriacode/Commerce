package com.example.spring_acces_base.response;

import org.springframework.stereotype.Service;

@Service
public class Response {
    boolean erreur = true;
    Object information;
    Object donner;

    public boolean isErreur() {
        return erreur;
    }
    public void setErreur(boolean erreur) {
        this.erreur = erreur;
    }
    public Object getDonner() {
        return donner;
    }
    public void setDonner(Object donner) {
        this.donner = donner;
    }
    public Object getInformation() {
        return information;
    }
    public void setInformation(Object information) {
        this.information = information;
    }
}