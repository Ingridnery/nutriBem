package com.example.nutribem.application.repository.sqlite;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.paciente.PacienteDAO;

import java.util.List;
import java.util.Optional;

public class SQLitePacienteDAO implements PacienteDAO {
    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return Optional.empty();
    }

    @Override
    public List<Paciente> findByNome(String nome) {
        return null;
    }

    @Override
    public Integer create(Paciente type) {
        return null;
    }

    @Override
    public Optional<Paciente> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Paciente> findAll() {
        return null;
    }

    @Override
    public boolean update(Paciente type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Paciente type) {
        return false;
    }
}
