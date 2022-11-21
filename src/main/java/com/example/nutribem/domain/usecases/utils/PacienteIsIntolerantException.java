package com.example.nutribem.domain.usecases.utils;

public class PacienteIsIntolerantException extends RuntimeException{
    public PacienteIsIntolerantException(String message) {
        super(message);
    }
}
