package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class RemovePlanoNutricionalUseCase {
    private PlanoNutricionalDAO planoNutricionalDAO;

    public RemovePlanoNutricionalUseCase(PlanoNutricionalDAO planoNutricionalDAO) {
        this.planoNutricionalDAO = planoNutricionalDAO;
    }

    public boolean remove (PlanoNutricional planoNutricional){
        Validator<PlanoNutricional> validator = new PlanoNutricionalInputRequestValidator();
        Notification notification = validator.validate(planoNutricional);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if(planoNutricionalDAO.findOne(planoNutricional.getId()).isEmpty())
            throw new EntityAlreadyExistsException("O plano nutricional: '"+ planoNutricional.getNome() +"' não está cadastrado.");
        return planoNutricionalDAO.delete(planoNutricional);
    }
}
