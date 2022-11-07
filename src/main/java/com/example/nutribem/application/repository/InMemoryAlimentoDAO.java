package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.refeicao.Refeicao;
import com.example.nutribem.domain.usecases.alimento.AlimentoDAO;

import java.util.*;

public class InMemoryAlimentoDAO implements AlimentoDAO{

    public static final Map<Integer, Alimento> db = new LinkedHashMap<>();
    public static int idCounter = 0;

    @Override
    public Integer create(Alimento alimento) {
        alimento.setId(++idCounter);
        db.put(idCounter, alimento);
        return idCounter;
    }

    @Override
    public Optional<Alimento> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public Optional<Alimento> findByName(String name) {
        return db.values().stream()
                .filter(alimento -> alimento.getNome().equals(name))
                .findAny();
    }

    @Override
    public boolean isInAnyRefeicao(Alimento alimento) {
        //TODO
        return false;
    }

    @Override
    public List<Alimento> findAlimentosFromRefeicao(Refeicao refeicao) {
        return db.values().stream()
                .filter(alimento -> alimento.getRefeicao().equals(refeicao))
                .toList();
    }


    @Override
    public List<Alimento> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Alimento alimento) {
        Integer id = alimento.getId();
        if(db.containsKey(id)){
            db.replace(id, alimento);
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
    public boolean delete(Alimento alimento) {
        return deleteByKey(alimento.getId());
    }



}
