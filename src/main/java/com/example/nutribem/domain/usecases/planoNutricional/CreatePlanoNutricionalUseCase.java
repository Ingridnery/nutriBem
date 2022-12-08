package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Objects;

public class CreatePlanoNutricionalUseCase {

    private final PlanoNutricionalDAO dao;

    public CreatePlanoNutricionalUseCase(PlanoNutricionalDAO dao) {
        this.dao = dao;
    }

    public Integer insert(PlanoNutricional planoNutricional) {

        Validator<PlanoNutricional> validator = new PlanoNutricionalInputRequestValidator();
        Notification notification = validator.validate(planoNutricional);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if (Objects.nonNull(planoNutricional.getId()) && dao.findOne(planoNutricional.getId()).isPresent())
            throw new EntityAlreadyExistsException("O plano nutricional: '" + planoNutricional.getNome() + "' já está cadastrado.");
        return dao.create(planoNutricional);
    }
}
