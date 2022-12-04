package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class UpdatePlanoNutricionalUseCase {

    private final PlanoNutricionalDAO planoNutricionalDAO;

    public UpdatePlanoNutricionalUseCase(PlanoNutricionalDAO planoNutricionalDAO) {
        this.planoNutricionalDAO = planoNutricionalDAO;
    }


    public Boolean update(PlanoNutricional planoNutricional) {
        Validator<PlanoNutricional> validator = new PlanoNutricionalInputRequestValidator();
        Notification notification = validator.validate(planoNutricional);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());
        String nome = planoNutricional.getNome();
        if (planoNutricionalDAO.findByName(nome).isPresent())
            throw new EntityAlreadyExistsException("O plano nutricional: '" + nome + "' já está cadastrado.");

        planoNutricionalDAO.findByIdPaciente(planoNutricional.getPaciente().getId());


        return planoNutricionalDAO.update(planoNutricional);


    }
}
