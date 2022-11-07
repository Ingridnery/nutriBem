package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.refeicao.RefeicaoDAO;
import com.example.nutribem.domain.usecases.utils.DAO;

import java.util.*;

public class InMemoryRefeicaoDAO implements RefeicaoDAO{

    public static final Map<Integer,Refeicao> db = new LinkedHashMap<>();
    private static Integer idCounter=0;

    @Override
    public Integer create(Refeicao refeicao) {
        refeicao.setId(++idCounter);
        db.put(idCounter,refeicao);

        return idCounter;
    }

    @Override
    public Optional<Refeicao> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Refeicao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Refeicao refeicao) {
        Integer id = refeicao.getId();
        if(db.containsKey(id)){
            db.replace(id, refeicao);
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
    public boolean delete(Refeicao refeicao) {
        return deleteByKey(refeicao.getId());
    }

    @Override
    public List<Refeicao> findByCardapio(Integer cardapio) {
        return db.values().stream()
                .filter(refeicao -> refeicao.getCardapio().getId()
                        .equals(cardapio))
                .toList();
    }
}
