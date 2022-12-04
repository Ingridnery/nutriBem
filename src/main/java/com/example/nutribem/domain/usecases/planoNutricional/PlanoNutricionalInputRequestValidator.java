package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Objects;

public class PlanoNutricionalInputRequestValidator extends Validator<PlanoNutricional> {
    @Override
    public Notification validate(PlanoNutricional planoNutricional) {

        Notification notification = new Notification();
        if (Objects.isNull(planoNutricional)) {
            notification.addError("Plano nutricional is null!");
            return notification;
        }

        if (nullOrEmpty(planoNutricional.getNome()))
            notification.addError("Nome is null or empty!");

        if (Objects.isNull(planoNutricional.getDataFim()))
            notification.addError("Data fim is null!");

        if (Objects.isNull(planoNutricional.getDataInicio()))
            notification.addError("Data inicio is null!");

        if (Objects.isNull(planoNutricional.getPaciente()))
            notification.addError("Paciente is null!");

        return notification;
    }
}
