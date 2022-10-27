package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PacienteDAO extends DAO<Paciente, Integer> {
    Optional<Paciente> findByCpf(String cpf);

    Optional<List<Paciente>> findByNome(String nome);
}
