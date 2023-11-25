package com.example.spring_acces_base.exeption;

public class DateAnterieur extends Exception {
    private final String message = "date anterieur";

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
