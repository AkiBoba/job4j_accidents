package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRep;

import java.util.List;

@Service
public class AccidenTypeService {
    private final AccidentTypeRep accidentTypeRep;

    public AccidenTypeService(AccidentTypeRep accidentTypeRep) {
        this.accidentTypeRep = accidentTypeRep;
    }

    public List<AccidentType> getTypes() {
        return accidentTypeRep.getTypes();
    }

    public AccidentType getById(int typeId) {
        return accidentTypeRep.getById(typeId);
    }
}
        