package com.example.nutribem.domain.entities.planoNutricional;
import com.example.nutribem.domain.entities.paciente.Paciente;

import java.time.LocalDate;
import java.util.Objects;

public class PlanoNutricional {

    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private Paciente paciente;

    public PlanoNutricional(String nome, LocalDate dataInicio, LocalDate dataFim, Paciente paciente) {
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoNutricional that = (PlanoNutricional) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
