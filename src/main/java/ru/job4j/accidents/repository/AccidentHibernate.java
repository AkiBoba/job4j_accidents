package ru.job4j.accidents.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;

@Repository
public class AccidentHibernate {
    @Autowired
    private final CrudRepository crudRepository;

    public AccidentHibernate(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public void save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
    }

    public List<Accident> getAll() {
        return crudRepository
                .query("select distinct a from Accident a join fetch a.rules", Accident.class);
    }

    public Accident getById(int id) {
        return crudRepository.get(
                "from Accident where id = :fId", Accident.class,
                Map.of("fId", id)
        );
    }

    public void update(Accident accident) {
        crudRepository.run(session -> session.update(accident));
    }

    public void delete(Accident accident) {
        crudRepository.run(session -> session.delete(accident));
    }

}