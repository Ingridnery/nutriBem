package com.example.nutribem.domain.entities.cardapio;

public class Cardapio {
    private Integer id;
    private Integer numeroDia;

    public Cardapio(Integer id, Integer numeroDia) {
        this.id = id;
        this.numeroDia = numeroDia;
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
}
