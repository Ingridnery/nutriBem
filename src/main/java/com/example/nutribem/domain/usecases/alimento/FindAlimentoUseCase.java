package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindAlimentoUseCase {
    private AlimentoDAO dao;

    public FindAlimentoUseCase(AlimentoDAO dao) {
        this.dao = dao;
    }

    public Optional<Alimento> findByNome(String name){
        if(Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("O nome da busca não pode ser nulo.");
        return dao.findByName(name);
    }

    public List<Alimento> findAll(){
        return dao.findAll();
    }
}
