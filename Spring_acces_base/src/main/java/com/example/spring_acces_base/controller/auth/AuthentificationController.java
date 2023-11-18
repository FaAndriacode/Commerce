package com.example.spring_acces_base.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.departement.services.ChefDeptService;
import com.example.spring_acces_base.entity.service.ChefServices;
import com.example.spring_acces_base.entity.service.services.ChefServicesService;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {
    private ChefDeptService userDepartement;
    private ChefServicesService userService;

    public AuthentificationController(ChefDeptService userDepartement, ChefServicesService userService){
        this.userDepartement = userDepartement;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response login(@RequestParam String email, @RequestParam String password, @RequestParam int etat) {
        Response response = new Response();
        Object temp = null;

        System.out.println(email);
        System.out.println(password);

        if (etat == 1) {
            System.out.println("chef de departement");
            temp = this.userDepartement.findByEmailAndPasswords(email, password);
            response.setErreur(false);
            response.setDonner(temp);
            return response; // renvoie une réponse OK avec le corps de la réponse
        } else if (etat == 0) {
            System.out.println("chef de service");
            temp = this.userService.findByEmailAndPasswords(email, password);
            response.setErreur(false);
            response.setDonner(temp);
            return response; // renvoie une réponse OK avec le corps de la réponse
        }

        return response;
    }
}
