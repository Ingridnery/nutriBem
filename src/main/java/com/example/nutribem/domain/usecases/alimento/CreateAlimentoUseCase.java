package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

public class CreateAlimentoUseCase {

    private final AlimentoDAO alimentoDAO;

    public CreateAlimentoUseCase(AlimentoDAO alimentoDAO) {
        this.alimentoDAO = alimentoDAO;
    }

    public Integer insert(Alimento alimento) {
        Validator<Alimento> validator = new AlimentoInputRequestValidator();
        Notification notification = validator.validate(alimento);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String nome = alimento.getNome();
        if (alimentoDAO.findByName(nome).isPresent())
            throw new EntityAlreadyExistsException("O alimento: '" + nome + "' já está cadastrado.");

        return alimentoDAO.create(alimento);
    }
}
