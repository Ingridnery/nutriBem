package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class UpdateAlimentoUseCase {

    private AlimentoDAO dao;

    public UpdateAlimentoUseCase(AlimentoDAO dao) {
        this.dao = dao;
    }

    public boolean update(Alimento alimento){
        Validator<Alimento> validator = new AlimentoInputRequestValidator();
        Notification notification = validator.validate(alimento);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String nome = alimento.getNome();
        if(dao.findByName(nome).isPresent())
            throw new EntityAlreadyExistsException("O alimento: '"+ nome +"' já está cadastrado.");

        return dao.update(alimento);
    }
}
