package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.paciente.PacienteDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPacienteDAO implements PacienteDAO {
    public static final Map<Integer, Paciente> db = new LinkedHashMap<>();
    public static int idCounter = 0;

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Paciente>> findByNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Integer create(Paciente paciente) {
        paciente.setId(++idCounter);
        db.put(idCounter, paciente);
        return idCounter;
    }

    @Override
    public Optional<Paciente> findOne(Integer id) {
        if(db.containsKey(id))
            return Optional.of(db.get(id));
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
