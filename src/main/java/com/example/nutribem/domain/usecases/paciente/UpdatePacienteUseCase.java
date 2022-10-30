package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class UpdatePacienteUseCase {
    private PacienteDAO dao;

    public UpdatePacienteUseCase(PacienteDAO dao) {
        this.dao = dao;
    }

    public boolean update(Paciente paciente) {
        Validator<Paciente> validator = new PacienteInputRequestValidator();
        Notification notification = validator.validate(paciente);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return dao.update(paciente);
    }
}
