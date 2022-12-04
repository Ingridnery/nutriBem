package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.List;

public interface CardapioDAO extends DAO<Cardapio, Integer> {

    List<Cardapio> findByNumeroDia(Integer numeroDia);

    List<Cardapio> findByPlanoNutricional(Integer idPlano);
}
