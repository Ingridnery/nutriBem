package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.EntityAlreadyExistsException;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import com.example.nutribem.domain.usecases.utils.Notification;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.Optional;

public class FindPlanoNutricionalByNomeUseCase {

    private final PlanoNutricionalDAO planoNutricionalDAO;


    public FindPlanoNutricionalByNomeUseCase(PlanoNutricionalDAO planoNutricionalDAO) {
        this.planoNutricionalDAO = planoNutricionalDAO;
    }
    public Optional<PlanoNutricional> findByNome(String name){

        if(Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("O nome da busca não pode ser nulo.");
        if(planoNutricionalDAO.findByName(name).isEmpty())
            throw new EntityNotFoundException("O plano nutricional: '"+ name +"' não está cadastrado.");


        return planoNutricionalDAO.findByName(name);
    }
}
