package com.example.nutribem.domain.entities.cardapio;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;

public class Cardapio {
    private Integer id;
    private Integer numeroDia;


    private PlanoNutricional planoNutricional;

    public Cardapio(Integer id, Integer numeroDia, PlanoNutricional planoNutricional) {
        this.id = id;
        this.numeroDia = numeroDia;
        this.planoNutricional = planoNutricional;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(Integer numeroDia) {
        this.numeroDia = numeroDia;
    }

    public PlanoNutricional getPlanoNutricional() {
        return planoNutricional;
    }

    public void setPlanoNutricional(PlanoNutricional planoNutricional) {
        this.planoNutricional = planoNutricional;
    }
}
