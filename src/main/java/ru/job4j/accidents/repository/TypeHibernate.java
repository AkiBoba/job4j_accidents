package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class TypeHibernate {
    @Autowired
    private final CrudRepository crudRepository;

    public List<AccidentType> getAll() {
        return crudRepository
                .query("from AccidentType", AccidentType.class);
    }

    public AccidentType getById(int id) {
        return crudRepository.get(
                "from AccidentType where id = :fId", AccidentType.class,
                Map.of("fId", id)
        );
    }
}
