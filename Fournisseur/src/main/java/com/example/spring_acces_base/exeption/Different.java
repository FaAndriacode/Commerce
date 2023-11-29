package com.example.spring_acces_base.exeption;

public class Different extends Exception{
    private final String message = "different id";

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
