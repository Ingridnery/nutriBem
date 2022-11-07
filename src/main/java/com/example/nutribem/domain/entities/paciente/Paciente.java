package com.example.nutribem.domain.entities.paciente;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class Paciente {
    private Integer id;
    private Integer circunferencia;
    private Integer altura;
    private String nome;
    private CPF cpf;
    private String email;
    private String telefone;
    private String historicoClinicoGeral;
    private IntoleranciaLactose intoleranciaLactose;
    private Boolean intoleranciaGluten;
    private Boolean diabetes;
    private String alergias;
    private String observacoesGerais;
    private String objetivos;
    private LocalDate dataNascimento;
    private Double peso;
    private Sexo sexo;
    private Boolean ativado;

    public Paciente(Integer circunferencia, Integer altura, String nome, CPF cpf, String email, String telefone, String historicoClinicoGeral, IntoleranciaLactose intoleranciaLactose, Boolean intoleranciaGluten, Boolean diabetes, String alergias, String observacoesGerais, String objetivos, LocalDate dataNascimento, Double peso, Sexo sexo){
        this.circunferencia = circunferencia;
        this.altura = altura;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.historicoClinicoGeral = historicoClinicoGeral;
        this.intoleranciaLactose = intoleranciaLactose;
        this.intoleranciaGluten = intoleranciaGluten;
        this.diabetes = diabetes;
        this.alergias = alergias;
        this.observacoesGerais = observacoesGerais;
        this.objetivos = objetivos;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.sexo = sexo;
        this.ativado = true;
    }

    public Paciente( Integer circunferencia, Integer altura, String nome, CPF cpf, String email, String telefone, String historicoClinicoGeral, IntoleranciaLactose intoleranciaLactose, Boolean intoleranciaGluten, Boolean diabetes, String alergias, String observacoesGerais, String objetivos, LocalDate dataNascimento, Double peso, Sexo sexo, Boolean ativado) {
        this.circunferencia = circunferencia;
        this.altura = altura;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.historicoClinicoGeral = historicoClinicoGeral;
        this.intoleranciaLactose = intoleranciaLactose;
        this.intoleranciaGluten = intoleranciaGluten;
        this.diabetes = diabetes;
        this.alergias = alergias;
        this.observacoesGerais = observacoesGerais;
        this.objetivos = objetivos;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.sexo = sexo;
        this.ativado = ativado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(this.id != null)
            throw new IllegalArgumentException("O paciente "+ this.nome +" já possui um id associado.");
        this.id = id;
    }

    public Integer getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Integer circunferencia) {
        this.circunferencia = circunferencia;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHistoricoClinicoGeral() {
        return historicoClinicoGeral;
    }

    public void setHistoricoClinicoGeral(String historicoClinicoGeral) {
        this.historicoClinicoGeral = historicoClinicoGeral;
    }

    public IntoleranciaLactose getIntoleranciaLactose() {
        return intoleranciaLactose;
    }

    public void setIntoleranciaLactose(IntoleranciaLactose intoleranciaLactose) {
        this.intoleranciaLactose = intoleranciaLactose;
    }

    public Boolean getIntoleranciaGluten() {
        return intoleranciaGluten;
    }

    public void setIntoleranciaGluten(Boolean intoleranciaGluten) {
        this.intoleranciaGluten = intoleranciaGluten;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public Boolean getAtivado() {
        return ativado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return getId().equals(paciente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
