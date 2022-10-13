package com.example.nutribem.domain.entities.planoNutricional;

import java.util.Date;

public class PlanoNutricional {

    private Integer id;
    private String nome;
    private Date dataInicio;
    private Date dataFim;

    public PlanoNutricional(Integer id, String nome, Date dataInicio, Date dataFim) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
