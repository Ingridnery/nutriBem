package com.example.nutribem.domain.entities.planoNutricional;

import com.example.nutribem.domain.entities.paciente.Paciente;

import java.util.Date;

public class PlanoNutricional {

    private Integer id;
    private String nome;
    private Date dataInicio;
    private Date dataFim;

    private Paciente paciente;

    public PlanoNutricional(Integer id, String nome, Date dataInicio, Date dataFim, Paciente paciente) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.paciente = paciente;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
