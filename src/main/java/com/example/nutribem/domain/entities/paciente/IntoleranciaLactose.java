package com.example.nutribem.domain.entities.paciente;

import java.util.Arrays;

public enum IntoleranciaLactose {
    APTO ("Apto"),
    INTOLERANTE ("Intolerante"),
    RESTRITO ("Restrito");

    private final String label;

    IntoleranciaLactose(String label) {
        this.label = label;
    }

    public static IntoleranciaLactose ofString(String categoria) {
        return Arrays.stream(IntoleranciaLactose.values())
                .filter(c -> categoria.equals(c.toString()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Categoria de intolerancia '" + categoria + "' nao reconhecido"));
    }

    @Override
    public String toString() {
        return label;
    }
}
