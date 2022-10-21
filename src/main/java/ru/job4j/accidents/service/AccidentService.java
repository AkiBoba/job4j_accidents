package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentRepository;

    public AccidentService(AccidentJdbcTemplate accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Collection<Accident> getAll() {
        return accidentRepository.getAll();

    }

    public int save(Accident accident, int typeId) {
        return accidentRepository.save(accident, typeId);

    }

    public Accident findById(int id) {
        return accidentRepository.findById(id);
    }

    public Boolean update(Accident accident, int typeId) {
        return accidentRepository.update(accident, typeId);
    }

    public Boolean delete(int id) {
        return accidentRepository.delete(id);
    }
}
