package com.example.nutribem.domain.usecases.paciente;

import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.util.Objects;

public class PacienteInputRequestValidator extends Validator<Paciente> {
    @Override
    public Notification validate(Paciente paciente) {
        Notification notification = new Notification();

        if (paciente == null) {
            notification.addError("Paciente is null");
            return notification;
        }

        if (nullOrEmpty(paciente.getCpf().getNumber()))
            notification.addError("CPF null or empty");

        if (nullOrEmpty(paciente.getNome()))
            notification.addError("Nome is null or empty");

        if (nullOrEmpty(paciente.getEmail()))
            notification.addError("Email is null or empty");

        if (nullOrEmpty(paciente.getTelefone()))
            notification.addError("Telefone is null or empty");

        if (nullOrEmpty(String.valueOf(paciente.getSexo())))
            notification.addError("Sexo is null or empty");

        if (paciente.getDataNascimento().isAfter(LocalDate.now()))
            notification.addError("Data de nascimento is invalid");

        if (nullOrEmpty(paciente.getPeso()))
            notification.addError("Peso is null or empty");

        if (nullOrEmpty(paciente.getCircunferencia()))
            notification.addError("Circunferencia is null or empty");

        if (nullOrEmpty(paciente.getAltura()))
            notification.addError("Altura is null or empty");

        if (Objects.isNull(paciente.getIntoleranciaLactose()))
            notification.addError("Intolerancia a lactose is null or empty");

        if (nullOrEmpty(paciente.getIntoleranciaGluten()))
            notification.addError("Intolerancia a gluten is null or empty");

        if (nullOrEmpty(paciente.getDiabetes()))
            notification.addError("Diabetes is null or empty");

        if (nullOrEmpty(paciente.getObjetivos()))
            notification.addError("Objetivos is null or empty");

        return notification;
    }
}
