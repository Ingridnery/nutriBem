package com.example.nutribem.domain.entities.refeicao;

import java.util.Arrays;

public enum RefeicaoCategoria {
    CAFE_MANHA ("Café da manhã"),
    LANCHE_MANHA ("Lanche da manhã"),
    ALMOCO("Almoço"),
    LANCHE_TARDE("Lanche da tarde"),
    JANTAR("Jantar"),
    CEIA("Ceia");


    private String label;


    RefeicaoCategoria(String label){
        this.label=label;
    }

    public static RefeicaoCategoria toEnum(String categoria) {
        return Arrays.stream(RefeicaoCategoria.values())
                .filter(c -> categoria.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }


}
