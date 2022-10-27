package com.example.nutribem.domain.entities.refeicao;

import java.util.Date;

public class Refeicao {

    private Integer id;
    private Date horario;
    private RefeicaoCategoria categoria;

    public Refeicao(Integer id, Date horario, RefeicaoCategoria categoria) {
        this.id = id;
        this.horario = horario;
        this.categoria = categoria;
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
