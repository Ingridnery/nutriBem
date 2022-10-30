package com.example.nutribem.domain.usecases.planoNutricional;

import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;

import java.util.List;
import java.util.Optional;

public class FindPlanoNutricionalByIdPacienteUseCase {
    private final PlanoNutricionalDAO planoNutricionalDAO;

    public FindPlanoNutricionalByIdPacienteUseCase(PlanoNutricionalDAO planoNutricionalDAO) {
        this.planoNutricionalDAO = planoNutricionalDAO;
    }

    public Optional<PlanoNutricional> findByPaciente(Integer idPaciente){

        return planoNutricionalDAO.findByIdPaciente(idPaciente);
    }

    public List<PlanoNutricional> findAll(){
        return planoNutricionalDAO.findAll();
    }
}
