package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeHibernate;

import java.util.List;

@Service
public class AccidenTypeService {
    private final TypeHibernate typeHibernate;

    public AccidenTypeService(TypeHibernate typeHibernate) {
        this.typeHibernate = typeHibernate;
    }

    public List<AccidentType> getTypes() {
        return typeHibernate.getAll();
    }

    public AccidentType getById(int typeId) {
        return typeHibernate.getById(typeId);
    }
}
        