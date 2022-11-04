package com.example.nutribem.domain.usecases.relatorios;

import com.example.nutribem.domain.usecases.paciente.PacienteDAO;
import com.example.nutribem.domain.usecases.planoNutricional.PlanoNutricionalDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmitirRelatorioPlanosVencidosUseCase {
    private PlanoNutricionalDAO dao;

    public EmitirRelatorioPlanosVencidosUseCase(PlanoNutricionalDAO dao) {
        this.dao = dao;
    }

    public List<String> emitir(){
        List<String> relatorio = new ArrayList<>();
        dao.findAll().forEach(plano -> {
            if(plano.getDataFim().isBefore(LocalDate.now())){
                relatorio.add(plano.getPaciente().getNome() + ": Telefone: "+ plano.getPaciente().getTelefone() + ", Email: "+ plano.getPaciente().getEmail()
                + ", Plano Nutricional: "+ plano.getNome() + ", venceu em "+ plano.getDataFim());
            }
        });

        Collections.sort(relatorio);
        return relatorio;
    }
}
