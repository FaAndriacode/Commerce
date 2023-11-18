package com.example.spring_acces_base.controller.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_acces_base.entity.departement.services.ChefDeptService;
import com.example.spring_acces_base.entity.service.services.ChefServicesService;
import com.example.spring_acces_base.response.Response;

@RestController
@RequestMapping("/auth")
@CrossOrigin
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

        System.out.println("👉 Value --> "+email+"  "+password);

        if (etat == 1) {
            System.out.println("chef de departement connecter");
            temp = this.userDepartement.findByEmailAndPasswords(email, password);
            if (temp != null) {
                response.setErreur(false);
                response.setDonner(temp);
            }else{
                response.setDonner(temp);
            }
        } else if (etat == 0) {
            System.out.println("chef de service connecter");
            temp = this.userService.findByEmailAndPasswords(email, password);
            if (temp != null) {
                response.setErreur(false);
                response.setDonner(temp);
            }else{
                response.setDonner(temp);
            }
        }
        return response;
    }
}
