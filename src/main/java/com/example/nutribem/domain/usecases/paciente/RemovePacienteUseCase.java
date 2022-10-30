package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import com.example.nutribem.domain.usecases.utils.Validator;

public class RemovePacienteUseCase {
    private PacienteDAO dao;

    public RemovePacienteUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public boolean remove(Integer id) {
        if (dao.findOne(id).isEmpty())
            throw new EntityNotFoundException("Paciente não encontrado.");

        return dao.deleteByKey(id);
    }

    public boolean remove(Paciente paciente) {
        Integer id = paciente.getId();

        if (Validator.nullOrEmpty(id))
            throw new IllegalArgumentException("ID não pode ser nulo.");

        return remove(id);
    }

    public boolean removeByCpf(String cpf) {
        Paciente paciente = dao.findByCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException("Paciente não encontrado")
        );

        return remove(paciente.getId());
    }
}
