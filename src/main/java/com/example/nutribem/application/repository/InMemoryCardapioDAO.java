package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.cardapio.Cardapio;
import com.example.nutribem.domain.entities.nutricionista.Nutricionista;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.cardapio.CardapioDAO;

import java.util.*;

public class InMemoryCardapioDAO implements CardapioDAO {

    public static final Map<Integer,Cardapio> db = new LinkedHashMap<>();

    @Override
    public Integer create(Cardapio cardapio) {
        db.put(cardapio.getId(),cardapio);
        return cardapio.getId();
    }

    @Override
    public Optional<Cardapio> findOne(Integer key) {
        return db.values().stream()
                .filter(cardapio -> cardapio.getId().equals(key))
                .findAny();
    }

    @Override
    public List<Cardapio> findAll() {
        return new ArrayList<>(db.values());
    }



    @Override
    public boolean update(Cardapio cardapio) {
        Integer id = cardapio.getId();
        if(db.containsKey(id)){
            db.replace(id, cardapio);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Cardapio cardapio) {
        InMemoryRefeicaoDAO inMemoryRefeicaoDAO = new InMemoryRefeicaoDAO();

        List<Refeicao> refeicaoList = inMemoryRefeicaoDAO.findAll();

        for (Refeicao refeicao : refeicaoList) {
            if (refeicao.getCardapio().getId().equals(cardapio.getId())){
                inMemoryRefeicaoDAO.deleteByKey(refeicao.getId());
            }
        }
        return deleteByKey(cardapio.getId());
    }

    @Override
    public Optional<Cardapio> findByNumeroDia(Integer numeroDia) {
        return db.values().stream()
                .filter(cardapio -> cardapio.getNumeroDia().equals(numeroDia))
                .findAny();
    }

    @Override
    public List<Cardapio> findByPlanoNutricional(Integer idPlano) {
        List<Cardapio> cardapios = new ArrayList<>();
        for(Cardapio cardapio : db.values()){
            if(cardapio.getPlanoNutricional().getId() == idPlano){
                cardapios.add(cardapio);
            }
        }

        return cardapios;
    }
}
