package com.example.spring_acces_base.exeption;

public class Quantite extends Exception{
    
    private final String message = "quantiter insuffisante";

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
    
}
