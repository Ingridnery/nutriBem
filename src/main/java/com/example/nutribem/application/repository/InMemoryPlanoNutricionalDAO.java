package com.example.nutribem.application.repository;

import com.example.nutribem.domain.entities.alimento.Alimento;
import com.example.nutribem.domain.entities.paciente.Paciente;
import com.example.nutribem.domain.entities.planoNutricional.PlanoNutricional;
import com.example.nutribem.domain.usecases.planoNutricional.PlanoNutricionalDAO;

import java.util.*;

public class InMemoryPlanoNutricionalDAO implements PlanoNutricionalDAO {
    public static final Map<Integer, PlanoNutricional> db = new LinkedHashMap<>();
    public static int idCounter = 0;


    @Override
    public Integer create(PlanoNutricional planoNutricional) {
        planoNutricional.setId(++idCounter);
        db.put(idCounter, planoNutricional);
        return idCounter;
    }

    @Override
    public Optional<PlanoNutricional> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<PlanoNutricional> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(PlanoNutricional planoNutricional) {
        Integer id = planoNutricional.getId();
        if(db.containsKey(id)){
            db.replace(id, planoNutricional);
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
    public boolean delete(PlanoNutricional planoNutricional) {
        return deleteByKey(planoNutricional.getId());
    }

    @Override
    public Optional<PlanoNutricional> findByName(String name) {
        return db.values().stream()
                .filter(planoNutricional -> planoNutricional.getNome().equals(name))
                .findAny();
    }

    @Override
    public Optional<PlanoNutricional> findByIdPaciente(Integer id) {

        return db.values().stream()
                .filter(planoNutricional -> planoNutricional.getIdPaciente().equals(id))
                .findAny();
    }
}
