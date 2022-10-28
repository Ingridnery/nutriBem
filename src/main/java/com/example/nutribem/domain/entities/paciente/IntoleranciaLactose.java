package com.example.nutribem.domain.entities.paciente;

public enum IntoleranciaLactose {
    APTO ("Apto"),
    INTOLERANTE ("Intolerante"),
    RESTRITO ("Restrito");

    private final String label;

    IntoleranciaLactose(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
