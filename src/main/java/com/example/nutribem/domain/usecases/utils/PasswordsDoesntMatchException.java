package com.example.nutribem.domain.usecases.utils;

public class PasswordsDoesntMatchException extends RuntimeException{
    public PasswordsDoesntMatchException(String message) {
        super(message);
    }
}
