package com.example.nutribem.domain.entities.cardapio;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;

public class Cardapio {
    private Integer id;
    private Integer numeroDia;


    private PlanoNutricional planoNutricional;

    public Cardapio(Integer numeroDia, PlanoNutricional planoNutricional) {
        this.numeroDia = numeroDia;
        this.planoNutricional = planoNutricional;
    }

    public void setId(Integer id) {
        if(this.id != null)
            throw new IllegalStateException("Id não pode ser alterado!");
        this.id = id;
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
