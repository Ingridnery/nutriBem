package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.Optional;

public interface PlanoNutricionalDAO extends DAO<PlanoNutricional,Integer> {

    Optional<PlanoNutricional> findByName(String name);
    Optional <PlanoNutricional> findByIdPaciente(Integer id);



}
