package com.example.nutribem.domain.entities.paciente;

public enum Sexo {
    MASCULINO('M'),
    FEMININO('F');

    private Character label;

    Sexo(Character label) {
        this.label = label;
    }

    public Character getLabel() {
        return label;
    }
}
