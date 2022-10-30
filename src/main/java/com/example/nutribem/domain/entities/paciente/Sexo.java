package com.example.nutribem.domain.entities.paciente;

import java.util.Arrays;

public enum Sexo {
    MASCULINO('M'),
    FEMININO('F');

    private final Character label;

    Sexo(Character label) {
        this.label = label;
    }

    public static Sexo ofString(String sexo) {
        return Arrays.stream(Sexo.values())
                .filter(c -> sexo.equals(c.toString()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Sexo '" + sexo + "' nao reconhecido"));
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
