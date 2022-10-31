package com.example.nutribem.domain.entities.refeicao;

import com.example.nutribem.domain.entities.cardapio.Cardapio;

import java.net.CacheRequest;
import java.util.Date;

public class Refeicao {

    private Integer id;
    private Date horario;
    private RefeicaoCategoria categoria;

    private Cardapio cardapio;

    public Refeicao(Integer id, Date horario, RefeicaoCategoria categoria, Cardapio cardapio) {
        this.id = id;
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
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public RefeicaoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(RefeicaoCategoria categoria) {
        this.categoria = categoria;
    }
}
