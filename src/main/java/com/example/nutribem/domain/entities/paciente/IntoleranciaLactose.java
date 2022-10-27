package com.example.nutribem.domain.entities.paciente;

public enum IntoleranciaLactose {
    APTO ("Apto"),
    INTOLERANTE ("Intolerant"),
    RESTRITO ("Restrito");

    public final String label;

    IntoleranciaLactose(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
