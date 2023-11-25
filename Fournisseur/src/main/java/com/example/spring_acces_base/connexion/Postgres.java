package com.example.spring_acces_base.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Postgres {
    public static Connection connexion() throws Exception {
        Connection connect = null;
        try{
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fournisseur","postgres","root");
            connect.setAutoCommit(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        return connect;
    }
}
