package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentHibernate accidentsRepostiory;

    public void create(Accident accident, int typeId) {
        accidentsRepostiory.save(accident, typeId);
    }

    public List<Accident> getAll() {
        return accidentsRepostiory.getAll();
    }

    public Accident getById(int id) {
        return accidentsRepostiory.getById(id);
    }

    public void delete(Accident accident) {
        accidentsRepostiory.delete(accident);
    }

    public void update(Accident accident) {
        accidentsRepostiory.update(accident);
    }
}