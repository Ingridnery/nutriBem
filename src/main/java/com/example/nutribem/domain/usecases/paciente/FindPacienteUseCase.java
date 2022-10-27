package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPacienteUseCase {
    private PacienteDAO dao;

    public FindPacienteUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public Optional<Paciente> findByOne(Integer id) {
        if (Validator.nullOrEmpty(id))
            throw new IllegalArgumentException("ID não pode ser nulo.");

        return dao.findOne(id);
    }

    public List<Paciente> findAll() {
        return dao.findAll();
    }

    public Optional<Paciente> findByCpf(String cpf) {
        if (Validator.nullOrEmpty(cpf))
            throw new IllegalArgumentException("CPF não pode ser nulo.");

        return dao.findByCpf(cpf);
    }

    public Optional<Paciente> findByNome(String nome) {
        if (Validator.nullOrEmpty(nome))
            throw new IllegalArgumentException("Nome não pode ser nulo");

        return dao.findByNome(nome);
    }
}
