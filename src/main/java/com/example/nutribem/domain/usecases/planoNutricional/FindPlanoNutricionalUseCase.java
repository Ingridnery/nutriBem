package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.EntityNotFoundException;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPlanoNutricionalUseCase {
    private final PlanoNutricionalDAO planoNutricionalDAO;

    public FindPlanoNutricionalUseCase(PlanoNutricionalDAO planoNutricionalDAO) {
        this.planoNutricionalDAO = planoNutricionalDAO;
    }

    public Optional<PlanoNutricional> findByNome(String name){
        if (Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("O nome da busca não pode ser nulo.");

        if (planoNutricionalDAO.findByName(name).isEmpty())
            throw new EntityNotFoundException("O plano nutricional: '"+ name +"' não está cadastrado.");

        return planoNutricionalDAO.findByName(name);
    }

    public List<PlanoNutricional> findByPaciente(Integer idPaciente){
        return planoNutricionalDAO.findByIdPaciente(idPaciente);
    }

    public List<PlanoNutricional> findAll(){
        return planoNutricionalDAO.findAll();
    }
}
