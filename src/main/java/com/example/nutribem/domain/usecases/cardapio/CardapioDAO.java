package com.example.nutribem.domain.usecases.cardapio;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.Optional;

public interface CardapioDAO extends DAO<Cardapio,Integer> {

    Optional<Cardapio> findByNumeroDia(Integer numeroDia);

}
