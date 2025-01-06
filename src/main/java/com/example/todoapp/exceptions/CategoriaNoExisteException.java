package com.example.todoapp.exceptions;


public class CategoriaNoExisteException extends Exception{

    public CategoriaNoExisteException(String message) {
        super(message);
    }

    public CategoriaNoExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    protected CategoriaNoExisteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
