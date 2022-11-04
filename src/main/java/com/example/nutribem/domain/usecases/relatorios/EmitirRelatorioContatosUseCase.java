package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.usecases.paciente.PacienteDAO;

import java.util.ArrayList;
import java.util.List;

public class EmitirRelatorioContatosUseCase {
    private PacienteDAO dao;

    public EmitirRelatorioContatosUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public List<String> emitir(){
        List<String> relatorio = new ArrayList<>();
        dao.findAll().forEach(paciente -> {
            relatorio.add(paciente.getNome() + ": Telefone: "+ paciente.getTelefone() + ", Email: "+ paciente.getEmail());
        });
        return relatorio;
    }
}
