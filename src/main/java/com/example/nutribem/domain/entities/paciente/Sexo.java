package com.example.nutribem.domain.entities.paciente;

public enum Sexo {
    MASCULINO('M'),
    FEMININO('F');

    private final Character label;

    Sexo(Character label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
