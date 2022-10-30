package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;

public class ActivatePacienteUseCase {
    private PacienteDAO dao;

    public ActivatePacienteUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public boolean activate(Paciente paciente) {
        if (paciente.isAtivado())
            throw new IllegalStateException("Paciente já está ativado");

        paciente.setAtivado(true);
        return dao.update(paciente);
    }

    public boolean deactivate(Paciente paciente) {
        if (!paciente.isAtivado())
            throw new IllegalStateException("Paciente já está desativado");

        paciente.setAtivado(false);
        return dao.update(paciente);
    }
}
