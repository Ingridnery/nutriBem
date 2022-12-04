package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PlanoNutricionalDAO extends DAO<PlanoNutricional, Integer> {
    Optional<PlanoNutricional> findByName(String name);

    List<PlanoNutricional> findByIdPaciente(Integer id);
}
