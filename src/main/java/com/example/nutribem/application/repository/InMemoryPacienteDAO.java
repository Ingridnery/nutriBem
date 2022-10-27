package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.paciente.PacienteDAO;

import java.util.*;

public class InMemoryPacienteDAO implements PacienteDAO {
    public static final Map<Integer, Paciente> db = new LinkedHashMap<>();
    public static int idCounter = 0;

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return db.values().stream()
                .filter(paciente -> paciente.getCpf()
                        .getNumber()
                        .equals(cpf))
                .findAny();
    }

    @Override
    public Optional<Paciente> findByNome(String nome) {
        return db.values().stream()
                .filter(paciente -> paciente.getNome().equals(nome))
                .findAny();
    }

    @Override
    public Integer create(Paciente paciente) {
        paciente.setId(++idCounter);
        db.put(idCounter, paciente);
        return idCounter;
    }

    @Override
    public Optional<Paciente> findOne(Integer id) {
        if (db.containsKey(id))
            return Optional.of(db.get(id));
        return Optional.empty();
    }

    @Override
    public List<Paciente> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Paciente paciente) {
        Integer id = paciente.getId();
        if (!db.containsKey(id)) return false;

        db.replace(id, paciente);
        return true;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (!db.containsKey(id)) return false;

        db.remove(id);
        return true;
    }

    @Override
    public boolean delete(Paciente paciente) {
        return deleteByKey(paciente.getId());
    }
}
