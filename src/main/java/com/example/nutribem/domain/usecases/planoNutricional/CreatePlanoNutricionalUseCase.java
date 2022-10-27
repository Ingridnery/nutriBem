package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.alimento.AlimentoInputRequestValidator;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CreatePlanoNutricionalUseCase {

    private PlanoNutricionalDAO dao;

    public CreatePlanoNutricionalUseCase(PlanoNutricionalDAO dao) {
        this.dao = dao;
    }

    public Integer insert(PlanoNutricional planoNutricional){

        Validator<PlanoNutricional> validator = new PlanoNutricionalInputRequestValidator();
        Notification notification = validator.validate(planoNutricional);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        if(dao.findOne(planoNutricional.getId()).isPresent())
            throw new EntityAlreadyExistsException("O plano nutricional: '"+ planoNutricional.getNome() +"' já está cadastrado.");

        return dao.create(planoNutricional);
    }
}
