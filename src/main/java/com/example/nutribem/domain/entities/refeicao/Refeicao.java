package com.example.nutribem.domain.entities.refeicao;

import com.example.nutribem.domain.entities.cardapio.Cardapio;

import java.net.CacheRequest;
import java.time.LocalTime;

public class Refeicao {

    private Integer id;
    private LocalTime horario;
    private RefeicaoCategoria categoria;

    private Cardapio cardapio;

    public Refeicao(LocalTime horario, RefeicaoCategoria categoria, Cardapio cardapio) {
        this.horario = horario;
        this.categoria = categoria;
        this.cardapio = cardapio;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        if(this.id != null)
            throw new IllegalArgumentException("Id não pode ser alterado!");
        this.id = id;
    }
    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public RefeicaoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(RefeicaoCategoria categoria) {
        this.categoria = categoria;
    }
}
