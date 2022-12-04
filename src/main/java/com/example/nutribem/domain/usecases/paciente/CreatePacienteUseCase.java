package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CreatePacienteUseCase {
    private final PacienteDAO pacienteDAO;

    public CreatePacienteUseCase(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public Integer insert(Paciente paciente) {
        Validator<Paciente> validator = new PacienteInputRequestValidator();
        Notification notification = validator.validate(paciente);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = paciente.getCpf().getNumber();
        if (pacienteDAO.findByCpf(cpf).isPresent())
            throw new EntityAlreadyExistsException("Cpf '" + cpf + "' já está cadastrado.");

        return pacienteDAO.create(paciente);
    }
}
