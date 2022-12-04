package com.example.nutribem.domain.usecases.alimento;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.Optional;

public interface AlimentoDAO extends DAO<Alimento, Integer> {
    Optional<Alimento> findByName(String name);


}
